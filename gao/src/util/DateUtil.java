package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static void main(String[] args) {
		try {
			stringToLong("");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Long stringToLong(String dates) throws ParseException{
		String str="2015-08-31 21:08:06";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = (Date) sdf.parse(str);
        System.out.println(date.toString()+"   "+date.getTime());
		
        return date.getTime();
        		
	}
	
	/**
	 * 日期类型转换成字符串类型
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date, String format){
		if(null == date){
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date); 
		 
	}
}
