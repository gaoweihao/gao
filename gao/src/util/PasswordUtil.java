package util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordUtil {

	public static void main(String[] args) {
		//资源库创建密码
		//createPassword("111111", "xushanshan");
		
	}
	/*public static void main(String[] args) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String md5pw = md5.encodePassword("111111", "xushanshan");
		System.out.println(md5pw);
	}*/
	
	/**
	 * 资源库创建密码
	 * */
	public static String createPassword(String userName,String password){
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String md5pw = md5.encodePassword(password, userName);
		System.out.println(md5pw);
		return md5pw;
	}
}
