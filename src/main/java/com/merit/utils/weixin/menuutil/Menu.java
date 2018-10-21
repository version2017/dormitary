package com.merit.utils.weixin.menuutil;
/**
 * 菜单
 * 菜单对象的封装，菜单对象包含多个菜单项（最多只能有3个），这些菜单项即可以是子菜单项（不含二级菜单的一级菜单），也可以是父菜单项（包含二级菜单的菜单项）
 * @author 杨建冬
 * @date 2013-09-10
 */
public class Menu {
	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}
}