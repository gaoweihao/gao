package upload;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import util.DateUtil;
import vo.UnitVo;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


public class UploadOtherExcel {
	private static Long ID=1L;
	public static void main(String[] args) {
		Long i=System.currentTimeMillis();
		uploadExcelOther();
		//System.out.println(System.currentTimeMillis()-i);
	}
	/**
	 * 导入具有合并单元格的excel表格
	 * */
	public static void uploadExcelOther(){
		
		try {
			UnitVo unit=new UnitVo();
			List<UnitVo> list=Lists.newArrayList();
			Map<Integer, Long> map=Maps.newHashMap();
			map.put(0, -1l);
			
			File file= new File("E:\\梳理事项汇总表(吉林市交通局汇总)导入模板.xls");
			InputStream is = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(is);  
			Sheet sheet = workbook.getSheetAt(0);//读取Excel Sheet页 
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			//获取excel表格的合并单元格
			List<CellRangeAddress> listCombineCell=getCombineCell(sheet);
			String value="";
			
			//读取excel表格中的根目录
			Row row = sheet.getRow(0);//当前行
			Cell cell = row.getCell(0);
			cell.getCellComment();
			if(null!=cell){
				value=getCellValue(evaluator,cell);
				if(!Strings.isNullOrEmpty(value)){
					if(null!=cell.getCellComment()){
						//获取批注里面的信息
						String comment=cell.getCellComment().getString().getString();
						unit.setComment(comment);
					}
					unit.setId(ID);
					unit.setName(value);
					unit.setPid(map.get(0));
					list.add(unit);
					map.put(1, ID);
					ID++;
				}
			}
		//	System.out.println(unit.toString());
			int rowNum = sheet.getLastRowNum() + 1;//行数
			int colNum =sheet.getRow(0).getPhysicalNumberOfCells();//获取0行的列数
			readExcel(list, 2, rowNum,1,2, sheet, evaluator, listCombineCell, map);
			/*for(int i=2;i< rowNum;i++){
				 row =sheet.getRow(i);//当前行
				for(int j=1;j<colNum;j++){
					cell=row.getCell(j);
					if(null!=cell){
						value=getCellValue(evaluator,cell);
						 if(!Strings.isNullOrEmpty(value)){
						 		unit= new UnitVo();
								unit.setId(ID);
								unit.setName(value);
								unit.setPid(map.get(j));
								map.put(j+1,ID );
								ID++;
								list.add(unit);
							if(isCombineCell(listCombineCell, row.getCell(j), sheet)){//判断该单元格式是否为合并单元格
								//j=lastColumn;
								i=lastRow;
							}
						}else{
							map.put(j+1,map.get(j));
						}
					}
				}
			}*/
			//拼接ztree 串
			//pellZtree(list);
			pellMindmap(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 递归拼接字知识地图字符串
	 * 			标题 和 root
	 * */
	public static  void pellMindmap(List<UnitVo> list){
		StringBuffer json= new StringBuffer("{");
		if(null != list && list.size() > 0){
			for(int i = 0 ; i< list.size() ; i++){
				if(-1==list.get(i).getPid()){
					json.append("\"id\":\"").append(list.get(i).getId())
					    .append("\",\"title\":\"").append(list.get(i).getName())
						.append("\",\"dates\":{\"created\":1520844616960,\"modified\":1520844616960 },")
						.append("\"dimensions\":{\"x\":\"1000\",\"y\":\"2000\"},")
						.append("\"mindmap\":{");
					json.append("\"root\": {").append("\"id\": \"").append(list.get(i).getId()).
							append("\",\"parentId\": \"<null>\",\"branchColor\": \"#000000\"," +
							"\"text\": {\"caption\": \"").append(list.get(i).getName())
							.append("\",\"font\": {\"size\": \"20\",\"color\": \"#000000\",\"weight\": \"bold\"," +
									"\"style\": \"normal\",\"decoration\": \"none\"}},\"offset\": {\"x\": \"100\"," +
									"\"y\": \"100\"}");
					//递归拼接字符串
					pellRecursion(list,json,list.get(i).getId());
				}
			}
		}
		json.append("}}}");
		System.out.println(json.toString());
		//write(json.toString());
	}
	/**
	 * 递归拼接字知识地图字符串
	 * 			children
	 * 	@param  list 知识地图拼接对象集合
	 * 	@param  json 拼接json 对象
	 * 	@param  pid 上级id（下级的父id）
	 * */
	public static void pellRecursion(List<UnitVo> list ,StringBuffer json,Long pid){
		if(null != list && list.size() > 0) {
			//查询要拼接的当前对象
			List<UnitVo> clist = getList(list, pid);
			if (null != clist && clist.size() > 0) {
				json.append(",\"children\":[");
				for (int i= 0 ; i < clist.size() ; i++){
					UnitVo unit =clist.get(i);
					if(i!=0)json.append("},");
					json.append("{\"id\":\"").append(unit.getId())
							.append("\",\"parentId\":\"").append(unit.getPid())
							.append("\",\"branchColor\":\"#000000\"")
							.append(",\"text\":{")
							.append("\"caption\":\"").append(unit.getName())
							.append("\",\"font\":{")
							.append("\"size\": \"20\",\"color\": \"#000000\",\"weight\": \"bold\",\"style\": \"normal\",\"decoration\": \"none\"}},")
							.append("\"offset\": {\"x\": \"1000\",\"y\": \"1000\"}");
					pellRecursion(list , json, unit.getId());

				}
				json.append("}],\"foldChildren\": \"true\"");

			}else{
				json.append(",\"foldChildren\": \"false\"");
			}
		}
	}

	/**
	 * 获取下级集合
	 * 	@param  list 知识地图拼接对象集合
	 * 	@param  pid 上级id（下级的父id）
	 * */
	public static List<UnitVo> getList(List<UnitVo> list,Long pid){
		List<UnitVo> cList= Lists.newArrayList();
		if(null != list && list.size() > 0){
			for(UnitVo u:list){
				if(null!=pid && u.getPid()==pid){
					cList.add(u);
				}
			}
		}
		return cList;
	}

	/**
	 * 拼接ztree json树
	 * */
	public static  void pellZtree(List<UnitVo> list){
		StringBuffer json= new StringBuffer("[");
		//拼接json串
		if(null!=list && list.size() > 0){
			for(int i=0;i<list.size();i++){
				json.append("{\"id\":\"").append(list.get(i).getId())
						.append("\",\"name\":\"").append(list.get(i).getName())
						.append("\",\"pid\":").append(list.get(i).getPid()).append("},");
			}
		}
		json.setCharAt(json.lastIndexOf(","), ']');
		write(json.toString());
		System.out.println(json.toString());
	}

	/**
	 * 递归读取excel表格
	 * @param list b 保存excel表格中内容的对象集合
	 * @param rowNums 开始行数
	 * @param rowNume 结束行数
	 * @param  colNums 开始列数
	 * @param colNume 结束列数
	 * @param sheet 读取的excel表格当前页
	 * @param  evaluator 表格内数据类型判断依据
	 * @param  listCombineCell 本excel表格页合并单元格的集合
	 * @param  map 存放对象的父级Id（其对应关系为 key = 当前列，value=父级id（前一列的id））
	 * @throws Exception 
	 * */
	public static void readExcel(List<UnitVo> list,Integer rowNums,Integer rowNume,Integer colNums,Integer colNume,
								 Sheet sheet,FormulaEvaluator evaluator,
								 List<CellRangeAddress> listCombineCell,Map<Integer, Long> map) throws Exception{
		if(colNume >sheet.getRow(0).getPhysicalNumberOfCells()){
			colNume=sheet.getRow(0).getPhysicalNumberOfCells();
		}
		for(int i = rowNums ; i < rowNume ; i++ ){
			Row row =sheet.getRow(i);//当前行
			for(int j = colNums ; j < colNume ; j++ ){
				Cell cell=row.getCell(j);
				if(null!=cell){
					String value=getCellValue(evaluator,cell);
					if(!Strings.isNullOrEmpty(value)){
						UnitVo unit= new UnitVo();
						if(null!=cell.getCellComment()){
							//获取批注里面的信息
							String comment=cell.getCellComment().getString().getString();
							unit.setComment(comment);
						}
						unit.setId(ID);
						unit.setName(value);
						unit.setPid(map.get(j));
						map.put(j+1,ID );
						ID++;
						list.add(unit);
					}else{
						map.put(j+1,map.get(j));
					}
					//判断该单元格式是否为合并单元格
					if(isCombineCell(listCombineCell, cell, sheet)){
						/*System.out.println(lastRow); //结束行
						System.out.println(firstRow);//开始行
						System.out.println(firstColumn);//开始列
						System.out.println(lastColumn); // 结束列*/
						i=lastRow;
						/**
						 * 读取合并单元格的下一列数据时：
						 * 读取的开始行不变，结束行要+1，不然读取不到最后一行的数据
						 * 读取的开始列要本列的 最后列数+1，结束列数=本行最后列数+2
						 * */
						readExcel(list, firstRow, lastRow+1, lastColumn+1,lastColumn+2, sheet, evaluator, listCombineCell, map);
					}else{
						readExcel(list, i, i+1,j+1,j+2, sheet, evaluator, listCombineCell, map);
					}
				}
			}
		}
	}

	public static void write(String json){
		FileWriter fw = null;
		File f = new File("E:\\a.txt");
		try {
			if(!f.exists()){
				f.createNewFile();
			}
			fw = new FileWriter(f);
			BufferedWriter out = new BufferedWriter(fw);
			out.write(json, 0, json.length()-1);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/** 
     * 合并单元格处理,获取合并行 
     *  
     * @param sheet 
     * @return List<CellRangeAddress> 
     */  
    private static List<CellRangeAddress> getCombineCell(Sheet sheet) {  
        List<CellRangeAddress> list = Lists.newArrayList();  
        // 获得一个 sheet 中合并单元格的数量  
        int sheetmergerCount = sheet.getNumMergedRegions();  
        // 遍历合并单元格  
        for (int i = 0; i < sheetmergerCount; i++) {  
            // 获得合并单元格加入list中  
            CellRangeAddress ca = sheet.getMergedRegion(i);  
            list.add(ca);  
        }  
        return list;  
    }
	/** 
     * 判断单元格是否为合并单元格，是的话则将单元格的值返回
     * @param listCombineCell  存放合并单元格的list
     * @param cell  需要判断的单元格
     * @param sheet sheet
     * @return
     */  
    static int firstColumn = 0;  
    static int lastColumn = 0;  
    static int firstRow = 0;  
    static int lastRow = 0;  
    private static boolean isCombineCell(List<CellRangeAddress> listCombineCell, Cell cell, Sheet sheet) throws Exception {
        for (CellRangeAddress ca : listCombineCell) {  
            // 获得合并单元格的 起始列, 结束列,起始行, 结束行,
            firstColumn = ca.getFirstColumn();  
            lastColumn = ca.getLastColumn();  
            firstRow = ca.getFirstRow();  
            lastRow = ca.getLastRow();  
            if (cell.getRowIndex() >= firstRow && cell.getRowIndex() <= lastRow) {  
                if (cell.getColumnIndex() >= firstColumn && cell.getColumnIndex() <= lastColumn) {  
                    return true;  
                }  
            }  
        }
        return false;  
    }
	/**
	 * 功能： 获取单元格的值
	 * @param evaluator
	 * @param cell
	 * @return
	 * @author:Liubaofeng
	 * @create date:2012-12-19
	 * @modified:Liubaofeng
	 * @modified date:2012-12-19
	 */
	public static String getCellValue(FormulaEvaluator evaluator, Cell cell) {
		String fieldValue = "";
		if (cell != null) {
			switch (evaluator.evaluateInCell(cell).getCellType()) {
			case Cell.CELL_TYPE_STRING: // 字符串
				fieldValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN: // bool型
				fieldValue = cell.getBooleanCellValue() + "";
				break;
			case Cell.CELL_TYPE_NUMERIC: // 数值型
				if (HSSFDateUtil.isCellDateFormatted(cell)) { // 日期类型
					// 把Date转换指定格式的日期字符串
					fieldValue = DateUtil.dateToString(cell.getDateCellValue(),
							"yyyy-MM-dd");
				} else {
					BigDecimal bc = new BigDecimal(cell.getNumericCellValue());
					fieldValue = bc.toString();
				}
				break;
			case Cell.CELL_TYPE_BLANK: // 空
				fieldValue = "";
				break;
			case Cell.CELL_TYPE_ERROR:
				fieldValue = cell.getErrorCellValue() + "";
				break;
			case Cell.CELL_TYPE_FORMULA:
				fieldValue = "";
				break;
			}
		}
		return fieldValue;
	}
}