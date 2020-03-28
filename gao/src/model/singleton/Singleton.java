package model.singleton;

/**
 * 单例模式的通用代码
 * */
public class Singleton {
	
	private static final Singleton leton = new Singleton(); 
	
	private Singleton(){
		
	};
	
	public static Singleton getSingleton(){
		return leton;
	}
	//其他的方法尽量 static
	public static void doSomething(){

	}

}
