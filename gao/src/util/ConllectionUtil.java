package util;


import java.util.Collection;
import java.util.HashSet;

public class ConllectionUtil {
	public static void main(String[] args) {
		System.out.println(isNotEmpty(new HashSet<String>()));
	}
	
	/**
	 * 判断集合不为null
	 * */
	public static boolean isNotEmpty(Collection<?> list){
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断集合为空
	 * */
	public static boolean isEmpty(Collection<?> list){
		if(null!=list&&list.size()>0){
			return false;
		}
		return true;
		
	}
}