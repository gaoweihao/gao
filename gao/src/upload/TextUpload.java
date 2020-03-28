package upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.helper.StringUtil;

import util.DateUtil;
import vo.UnitVo;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
/**
 * java 通过 poi读取excel表格的时候是从
 * 第0行第0 列开始读取
 * 
 * */
public class TextUpload {
	private static Long SUM=1l;
	private static Long id=1l;
	public static void main(String[] args) {
		//导入excel
		//uploadExcelMyself();
		//导入other --->梳理事项汇总表(吉林市交通局汇总)导入模板.xls
		uploadExcelOther();
		//测试导入excel
		//testExcelss();
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
			Sheet sheet = workbook.getSheetAt(1);//读取Excel Sheet页 
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			//获取excel表格的合并单元格
			List<CellRangeAddress> listCombineCell=getCombineCell(sheet);
			int rowNum = sheet.getLastRowNum() + 1;//行数
			String value="";
			Row row = sheet.getRow(0);//当前行
			Cell cell = row.getCell(0);
			if(null!=cell){
				value=getCellValue(evaluator,cell);
				if(!Strings.isNullOrEmpty(value)){
					unit.setId(id);
					unit.setName(value);
					unit.setPid(map.get(0));
				}
			}
			
			
			for(int i=1;i< rowNum;i++){
				 row =sheet.getRow(i);//当前行
				for(int j=0;j<sheet.getRow(0).getPhysicalNumberOfCells()+1;j++){
					//判断该单元格式是否为合并单元格
					if(isCombineCell(listCombineCell, row.getCell(i), sheet)){
						
					}else{
						
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试导入excel
	 * */
	public static void testExcelss(){
		try {
			File file= new File("E:\\2.xlsx");
			InputStream is = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(is);  
			Sheet sheet = workbook.getSheetAt(0);//读取Excel Sheet页 
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			int rowNum = sheet.getLastRowNum() + 1;//行数
			List<CellRangeAddress> listCombineCell=getCombineCell(sheet);
			Row row = sheet.getRow(0);//当前行
			System.out.println("总列数 "+(row.getPhysicalNumberOfCells()+1));
			for(int i= 1;i<row.getPhysicalNumberOfCells()+1;i++){
				if(isCombineCell(listCombineCell, row.getCell(i), sheet)){//至判断第八列为起始列的数据
					System.out.println("进来了    "+i);
					String value = sheet.getRow(firstRow).getCell(firstColumn).getStringCellValue();
					System.out.println("---"+value);
					System.out.print("起始列 -->" +firstColumn);
					System.out.print("	结束列 -->" +lastColumn);
					System.out.print("	起始行 -->" +firstRow);
					System.out.print("	结束行 -->" +lastRow);
				}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 一	二	三	四	五
	 * 	1
	 *		12
	 *			13
	 *	2
	 *		21
	 *			22
	 *	3	
	 *		31
	 *			32
	 *				33
	 *					34
	 *	4
	 *	5
	 *		51
	 *			52
	 *				53
	 *					54
	 *					54
	 *					54
	 *		511
	 *			521
	 *			521
	 *				531
	 *				531
	 *					534
	 *					534
	 * */
	public static void uploadExcelMyself(){
		try {
			File file= new File("E:\\2.xlsx");
			InputStream is = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(is);  
			Sheet sheet = workbook.getSheetAt(1);//读取Excel Sheet页 
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			int rowNum = sheet.getLastRowNum() + 1;//行数
			//Row row = sheet.getRow(1);//当前行
			
			UnitVo unit=new UnitVo();
			List<UnitVo> list=Lists.newArrayList();
			Map<Integer, Long> map=Maps.newHashMap();
			map.put(0, -1l);
			
			for(int i=1;i< rowNum;i++){
				Row row =sheet.getRow(i);//当前行
				for(int j=0;j<sheet.getRow(0).getPhysicalNumberOfCells()+1;j++){
					Cell cell = row.getCell(j);
					String value="";
					if(null!=cell){
						value=getCellValue(evaluator,cell);
						if(!Strings.isNullOrEmpty(value)){
							if(j==0){
								unit=new UnitVo();
								unit.setId(SUM);
								unit.setName(value);
								unit.setPid(map.get(j));
								map.put(j+1, SUM);
								SUM++;
								list.add(unit);
							}else{
								unit=new UnitVo();
								unit.setId(SUM);
								unit.setName(value);
								unit.setPid(map.get(j));
								map.put(j+1, SUM);
								SUM++;
								list.add(unit);
							}
						}
					}
				}
			}
			
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
			System.out.println(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void textExcels(){
		try {	
			File file= new File("E:\\2.xlsx");
			InputStream is = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(is);  
			//读取Excel Sheet页  
			Sheet sheet = workbook.getSheetAt(0);  
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			int rowNum = sheet.getLastRowNum() + 1;//行数
			List<CellRangeAddress> listCombineCell=getCombineCell(sheet);
			//读取每一行  
			for(int i = 7 ; i < rowNum ; i++){//从第七行开始读取每一列的数据
				Row row = sheet.getRow(i);//当前行
				System.out.println("总列数 "+(row.getPhysicalNumberOfCells()+1));
				System.out.println("-------------->" +i);
				if(isCombineCell(listCombineCell, row.getCell(8), sheet)){//至判断第八列为起始列的数据
					System.out.println("j进来了    "+i);
					String value = sheet.getRow(firstRow).getCell(firstColumn).getStringCellValue();
					System.out.println("---"+value);
					System.out.println("起始列 -->" +firstColumn);
					System.out.println("结束列 -->" +lastColumn);
					System.out.println("起始行 -->" +firstRow);
					System.out.println("结束行 -->" +lastRow);
				}
			/*	//领取码	
				Cell cell0 = row.getCell(0);
				String value = "";
				if(null!=cell0){
					value = getCellValue(evaluator,cell0);
					if(!Strings.isNullOrEmpty(value));
				}else{
					result="0";
				}*/
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * poi测试遍历合并单元格并读取单元格中的内容
	 * */
	public static void textExcel() {
		try {
			File inputFile = new File("E:\\2.xlsx");
		    InputStream is = new FileInputStream(inputFile);
		    Workbook wb = new XSSFWorkbook(is);
		    Sheet sheet = wb.getSheetAt(0);
		    
		    // 遍历合并区域
		    for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
		        CellRangeAddress region = sheet.getMergedRegion(i); // 
		        int colIndex = region.getFirstColumn();             // 合并区域首列位置
		        int rowNum = region.getFirstRow();                 // 合并区域首行位置
		        System.out.println("首列位置"+colIndex);
		        System.out.println("首行位置"+rowNum);
		        System.out.println("第[" + i + "]个合并区域：" +  sheet.getRow(rowNum).getCell(colIndex).getStringCellValue());
		    }
		    
		    // 直接调用，我知道合并单元格的位置：
		    System.out.println(sheet.getRow(0).getCell(0).getStringCellValue());
		    
		    System.out.println(sheet.getRow(3).getCell(2).getStringCellValue());
		    
		    ((FileInputStream) wb).close();
		    is.close();
		} catch (Exception e) {
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
     *  
     * @param listCombineCell 
     *            存放合并单元格的list 
     * @param cell 
     *            需要判断的单元格 
     * @param sheet 
     *            sheet 
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
	 * 
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
