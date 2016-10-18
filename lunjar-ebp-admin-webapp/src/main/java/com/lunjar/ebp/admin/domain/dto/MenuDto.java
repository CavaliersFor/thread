package com.lunjar.ebp.admin.domain.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MenuDto implements Serializable{
	private String url;
	private String compareUrl;//用来比较的url
	private String menuName;
	private String isChecked;//1表示选中 ，2表示没有选中
	private String type;//1表示横向菜单，2表示纵向菜单
	
	private List<MenuDto> childMenus;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<MenuDto> getChildMenus() {
		return childMenus;
	}
	public void setChildMenus(List<MenuDto> childMenus) {
		this.childMenus = childMenus;
	}
	public String getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}
	public String getCompareUrl() {
		return compareUrl;
	}
	public void setCompareUrl(String compareUrl) {
		this.compareUrl = compareUrl;
	}
}
