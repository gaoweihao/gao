package model.dynamicProxy.avdice.acvice.impl;

import model.dynamicProxy.avdice.acvice.Advice;

public class BeforeAdvice implements Advice{

	@Override
	public void exec() {
		System.out.println("我是前置通知，我被提前执行了！！");
	}

}
