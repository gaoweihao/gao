package model.dynamicProxy.avdice.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler{

	//被代理的对象
	public Object target=null;//被代理的对象
	//通过构造方法传递一个对象
	public MyInvocationHandler(Object obj){
		this.target=obj;
	}
	//被代理的方法
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		return method.invoke(target, args);
	}

}
