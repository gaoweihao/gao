package model.factory.abstractFactory;

import model.factory.product.Product;

/**
 * 抽象的工厂
 * */
public abstract class AbstractFactory {
	public abstract<T extends Product>T createProduct(Class<T>c);
}
