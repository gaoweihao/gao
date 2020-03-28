package model.factory.factory;

import model.factory.abstractFactory.AbstractFactory;
import model.factory.product.Product;

public class Factory extends AbstractFactory{
	
	@SuppressWarnings("unchecked")
	public <T extends Product>T createProduct(Class<T>c){
		Product product=null;
		try {
			product=(Product)Class.forName(c.getName()).newInstance();
		} catch (Exception e) {
			System.out.println("人类创建出错");
		}
		return (T)product;
	}
		
}
