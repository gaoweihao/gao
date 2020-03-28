package model.dynamicProxy.avdice.client;

import java.lang.reflect.InvocationHandler;

import model.dynamicProxy.avdice.handler.MyInvocationHandler;
import model.dynamicProxy.avdice.proxy.DynamicProxy;
import model.dynamicProxy.avdice.proxy.SubjectDynamicProxy;
import model.dynamicProxy.avdice.subject.Subject;
import model.dynamicProxy.avdice.subject.impl.RealSubject;

public class Cline {
	public static void main(String[] args) {
		Subject subject= new RealSubject();
		
		InvocationHandler handler = new MyInvocationHandler(subject);
		
		Subject proxy=DynamicProxy
				.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), handler);
		proxy.doSomeThing("你想干嘛？！！");
		
		//定义主题的代理

		//Subject proxy1=SubjectDynamicProxy.newProxyInstance(subject);
		//代理的行为
		//proxy1.doSomeThing("Finish");
	}
}
