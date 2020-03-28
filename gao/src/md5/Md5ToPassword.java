package md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class Md5ToPassword {
	public static void main(String[] args) {
		System.out.println(getPwd("111111", "ywry"));
	}
	
	public static String getPwd (String pwd,String account){
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String password = md5.encodePassword(StringToMD5(pwd),account);
		return password;
	}
	
	/**
	 * 将字符串md5加密
	 */
	public static String StringToMD5(String plainText) {
		String returnValue = "";
		  try {
		   MessageDigest md = MessageDigest.getInstance("MD5");
		   md.update(plainText.getBytes());
		   byte b[] = md.digest();

		   int i;

		   StringBuffer buf = new StringBuffer("");
		   for (int offset = 0; offset < b.length; offset++) {
		    i = b[offset];
		    if (i < 0)
		     i += 256;
		    if (i < 16)
		     buf.append("0");
		    buf.append(Integer.toHexString(i));
		   }
		   returnValue = buf.toString();
		  } catch (NoSuchAlgorithmException e) {
		   e.printStackTrace();

		  }
		  return returnValue;
		 }

}
