package model.dynamicProxy.client;

import java.lang.reflect.Proxy;
import java.util.Date;

import model.dynamicProxy.handler.GamePlayHandler;
import model.dynamicProxy.igame.IGamePlayer;
import model.dynamicProxy.igame.impl.GamePlayer;

public class DynamicProxy {
	public static void main(String[] args) {
		IGamePlayer player= new GamePlayer("小明");
		GamePlayHandler gamePlayHandler = new GamePlayHandler(player);
		System.out.println("要开始打游戏了,时间是："+ new Date());
		ClassLoader classLoader=player.getClass().getClassLoader();
		IGamePlayer proxy=
				(IGamePlayer)Proxy.newProxyInstance(classLoader, new Class[]{IGamePlayer.class}, gamePlayHandler);
		proxy.login("xiaoming", "shazi");
		proxy.killBoss();
		proxy.upgrade();
		System.out.println("小明升级了，小明game over 了！！！");
	
	
	}
}
