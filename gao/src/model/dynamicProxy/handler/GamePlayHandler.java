package model.dynamicProxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GamePlayHandler implements InvocationHandler{
	
	Class cla=null;  //被代理者
	
	Object object=null;//别代理的实体类
	
	//要代理的对象的构造方法
	public GamePlayHandler(Object obj) {
		this.object=obj;
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result=method.invoke(this.object, args);
		if(method.getName().equalsIgnoreCase("login")){
			System.out.println("小明你好！！你的账号被盗了！！！");
		}
		return result;
	}

}
