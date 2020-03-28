package model.factory.client;

import model.factory.abstractFactory.AbstractFactory;
import model.factory.factory.Factory;
import model.factory.product.ConcreteProduct1;
import model.factory.product.Product;
/**
 * 场景类
 * */
public class Client {
	public static void main(String[] args) {
		AbstractFactory factory = new Factory(); 
		Product product = factory.createProduct(ConcreteProduct1.class);
	}

}
