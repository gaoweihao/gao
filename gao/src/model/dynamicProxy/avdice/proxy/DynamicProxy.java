package model.dynamicProxy.avdice.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import model.dynamicProxy.avdice.acvice.impl.BeforeAdvice;

public class DynamicProxy<T> {

	@SuppressWarnings("unchecked")
	public static <T>T newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler handler){
		for(Class cla:interfaces){
			if(cla.getClass().getMethods().length>0){
				for(int i =0;i<cla.getMethods().length;i++){
					System.out.println(cla.getMethods()[i].getName());
					if(cla.getMethods()[i].getName().equalsIgnoreCase("doSomeThing")){
						//System.out.println(method.getName());
						System.out.println("你是不是傻");
					}	
				}	
			}
		}
		
		if(true){
			(new BeforeAdvice()).exec();
		}
		return (T)Proxy.newProxyInstance(loader, interfaces, handler);
	}
}
