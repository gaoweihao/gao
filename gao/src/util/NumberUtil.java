package util;

import java.util.Random;

public class NumberUtil {
	
	/**
	 * 随机生成密码
	 * */
	public static String  findRandomPassword(){
		String pwd="";
	    String[] str =
	    		
    	   {"~","@","#","$","%","^","&","*","_","=","a",
	    	"b","c","d","e","g","h","i","j","k","l","m",
	    	"n","o","p","q","r","s","t","u","v","w","x",
	    	"y","z","0","1","2","3","4","5","6","7","8",
	    	"9",};
       Random rand = new Random();
       for (int i = str.length; i > 0; i--) {
           int index = rand.nextInt(i);
           pwd+=str[index];
           if(pwd.length()>=20){
        	   break;
           }
       }
       return pwd;
	}
		
	/**
	 * 随机生成 0--100 之间的float 数字
	 * */
	public static float randomFloat(){
		Random r= new Random();
		float sum=5.0f+r.nextFloat()*100;
			if(sum>100){
				sum=sum-5.0f;
			}
		return (float)(Math.round(sum*100))/100;
		
	}
	/**
	 * 随机生成 min-max之间的整数
	 * */
	public static int randomInt(int min,int max){
		Random r= new Random();
		return r.nextInt(max-min)+min;
	}
	
	/**
	 * 判断一个是几位数(只计算小数点之前的位数)
	 * */
	public static int getDigit(String num){
		if(num.contains(".")){
			return num.substring(0, num.indexOf(".")).length();
		}
		return num.length();
	}

	
	public static void main(String[] args) {
		double sum=0;
		 for(int i=0;i<23;i++){
			 if(sum<100){
				 sum=sum+8;
			 }else if(sum>=100&&sum<150){
				 sum=sum+6.4;
			 }else{
				 sum=sum+4;
			 }
		 }
		
		 System.out.println(sum);
		//System.out.println(randomInt(5,100));
		//System.out.println(getDigit("456548.3336"));
	}
}
