package model.dynamicProxy.avdice.subject.impl;

import model.dynamicProxy.avdice.subject.Subject;

public class RealSubject implements Subject{

	@Override
	public void doSomeThing(String str) {
		System.out.println("你要尝试着去改变一些事情！！例如："+str);
		
	}

}
