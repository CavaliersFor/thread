package com.lunjar.ebp.admin.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class GetMenu {
	
	
	private List<MenuDto> getMenu(){
		List<MenuDto> parentMenus = new ArrayList<>();	
		
		List<MenuDto> childMenus = new ArrayList<>();
		
		MenuDto c1 = new MenuDto();
		c1.setIsChecked("2");
		c1.setUrl("/product/list");
		c1.setCompareUrl("/product/list");
		c1.setMenuName("商品管理");
		c1.setType("2");
		childMenus.add(c1);
		
		MenuDto c2 = new MenuDto();
		c2.setIsChecked("2");
		c2.setUrl("/product/add");
		c2.setCompareUrl("/product/add");
		c2.setMenuName("发布商品");
		c2.setType("2");
		childMenus.add(c2);
		
		MenuDto c3 = new MenuDto();
		c3.setIsChecked("2");
		c3.setUrl("/combinationProduct/list");
		c3.setCompareUrl("/combinationProduct/list,/combinationProduct/add");
		c3.setMenuName("组合商品");
		c3.setType("2");
		childMenus.add(c3);
		
		MenuDto m = new MenuDto();
		m.setIsChecked("2");
		m.setUrl("/product/list");
		m.setMenuName("商品管理");
		m.setType("1");
		m.setChildMenus(childMenus);
		parentMenus.add(m);
		
		
		/**<!--订单管理-->***/
		List<MenuDto> childMenus2 = new ArrayList<>();
		MenuDto m2C1 = new MenuDto();
		m2C1.setIsChecked("2");
		m2C1.setUrl("/trade/list");
		m2C1.setCompareUrl("/trade/list,/trade/detail/");
		m2C1.setMenuName("订单管理");
		m2C1.setType("2");
		childMenus2.add(m2C1);
		
		MenuDto m2 = new MenuDto();
		m2.setIsChecked("2");
		m2.setUrl("/trade/list");
		m2.setMenuName("订单管理");
		m2.setType("1");
		m2.setChildMenus(childMenus2);
		parentMenus.add(m2);
		
		/****<!---物流管理--->***/
		List<MenuDto> childMenus3 = new ArrayList<>();
		MenuDto m3C1 = new MenuDto();
		m3C1.setIsChecked("2");
		m3C1.setUrl("/express/list");
		m3C1.setCompareUrl("/express/list,/express/add");
		m3C1.setMenuName("快递设置");
		m3C1.setType("2");
		childMenus3.add(m3C1);
		
		MenuDto m3C2 = new MenuDto();
		m3C2.setIsChecked("2");
		m3C2.setUrl("/collectPlace/list");
		m3C2.setCompareUrl("/collectPlace/list");
		m3C2.setMenuName("自提设置");
		m3C2.setType("2");
		childMenus3.add(m3C2);
		
		MenuDto m3 = new MenuDto();
		m3.setIsChecked("2");
		m3.setUrl("/express/list");
		m3.setMenuName("物流管理");
		m3.setType("1");
		m3.setChildMenus(childMenus3);
		parentMenus.add(m3);
		
		/***优惠设置**/
		List<MenuDto> childMenus4 = new ArrayList<>();
		MenuDto m4C1 = new MenuDto();
		m4C1.setIsChecked("2");
		m4C1.setUrl("/discount/list");
		m4C1.setCompareUrl("/discount/list,/discount/add");
		m4C1.setMenuName("优惠设置");
		m4C1.setType("2");
		childMenus4.add(m4C1);
		
		
		MenuDto m4 = new MenuDto();
		m4.setIsChecked("2");
		m4.setUrl("/discount/list");
		m4.setMenuName("优惠设置");
		m4.setType("1");
		m4.setChildMenus(childMenus4);
		parentMenus.add(m4);
		
		/***基本信息**/
		List<MenuDto> childMenus5 = new ArrayList<>();
		MenuDto m5C1 = new MenuDto();
		m5C1.setIsChecked("2");
		m5C1.setUrl("/enterprise/info");
		m5C1.setCompareUrl("/enterprise/info");
		m5C1.setMenuName("基本信息");
		m5C1.setType("2");
		childMenus5.add(m5C1);
		
		MenuDto m5 = new MenuDto();
		m5.setIsChecked("2");
		m5.setUrl("/enterprise/info");
		m5.setMenuName("基本信息");
		m5.setType("1");
		m5.setChildMenus(childMenus5);
		parentMenus.add(m5);
		
		return parentMenus;
	}
	
	public static List<MenuDto> getMenuS(){
		GetMenu g = new GetMenu();
		return g.getMenu();
	}
}
