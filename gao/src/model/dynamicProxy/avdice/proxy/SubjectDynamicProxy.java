package model.dynamicProxy.avdice.proxy;

import java.lang.reflect.InvocationHandler;

import model.dynamicProxy.avdice.handler.MyInvocationHandler;
import model.dynamicProxy.avdice.subject.Subject;

public class SubjectDynamicProxy extends DynamicProxy{
	
	public static <T>T newProxyInstance(Subject subject){
		//获得类加载器
		ClassLoader loader=subject.getClass().getClassLoader();
		//获得类所实现的所有接口
		Class[] interfaces=subject.getClass().getInterfaces();
		//获得handler
		InvocationHandler handler=new MyInvocationHandler(subject);
	
		return newProxyInstance(loader, interfaces, handler);
	}
}
