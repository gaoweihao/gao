package model.dynamicProxy.igame.impl;

import model.dynamicProxy.igame.IGamePlayer;

public class GamePlayer implements IGamePlayer{
	
	private String name="";
	
	public GamePlayer(String name){
		this.name=name;
	}
	@Override
	public void login(String user, String password) {
		System.out.println("你好"+this.name+"你的账号"+user+"登录游戏了！！");
		
	}

	@Override
	public void killBoss() {
		System.out.println(this.name+"我要开始狩猎了！！嘎嘎嘎。。。。。");
		
	}

	@Override
	public void upgrade() {
		System.out.println(this.name+"我又升级了！！！嘎嘎嘎。。。");
		
	}

}
