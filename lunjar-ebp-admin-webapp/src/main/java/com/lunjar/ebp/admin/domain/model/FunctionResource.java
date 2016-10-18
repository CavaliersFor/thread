package com.lunjar.ebp.admin.domain.model;

import java.io.Serializable;
import java.util.List;

/**
 * 功能资源实体类
 * 
 * @author ShenWei
 *
 */
public class FunctionResource implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id; // 资源Id
	private String text; // 资源名称
	private String alias; // 资源别名,用于显示在tab上
	private String url; // URL链接地址
	private Integer level; // 级别
	private String icon; // 图标路径
	private String iconCls; // 图标样式
	private String desc; // 资源描述
	private FunctionResource parentResouece;
	private List<FunctionResource> children;
	private List<FunctionResource> actions;
	private boolean leaf;
	private boolean isAction;
	private String subSystemUrl;// 子系统url
	private String isOpenNewWin;// 是否打开新窗口
	private String isOutsideUrl;// 是否外部链接
	private String isAlwaysEnable;// 是否不受权限控制，永远有限

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean equals(Object obj) {
		if (obj instanceof FunctionResource) {
			FunctionResource otherResource = (FunctionResource) obj;
			return this.getId().equals(otherResource.getId());
		}
		return false;
	}

	public boolean isAction() {
		return isAction;
	}

	public void setAction(boolean isAction) {
		this.isAction = isAction;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public FunctionResource getParentResouece() {
		return parentResouece;
	}

	public void setParentResouece(FunctionResource parentResouece) {
		this.parentResouece = parentResouece;
	}

	public List<FunctionResource> getChildren() {
		return children;
	}

	public void setChildren(List<FunctionResource> children) {
		this.children = children;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getIsOpenNewWin() {
		return isOpenNewWin;
	}

	public void setIsOpenNewWin(String isOpenNewWin) {
		this.isOpenNewWin = isOpenNewWin;
	}

	public String getIsOutsideUrl() {
		return isOutsideUrl;
	}

	public void setIsOutsideUrl(String isOutsideUrl) {
		this.isOutsideUrl = isOutsideUrl;
	}

	public String getIsAlwaysEnable() {
		return isAlwaysEnable;
	}

	public void setIsAlwaysEnable(String isAlwaysEnable) {
		this.isAlwaysEnable = isAlwaysEnable;
	}

	public String getSubSystemUrl() {
		return subSystemUrl;
	}

	public void setSubSystemUrl(String subSystemUrl) {
		this.subSystemUrl = subSystemUrl;
	}

	public List<FunctionResource> getActions() {
		return actions;
	}

	public void setActions(List<FunctionResource> actions) {
		this.actions = actions;
	}

	@Override
	public String toString() {
		return "FunctionResource [id=" + id + ", text=" + text + ", alias=" + alias + ", url=" + url + ", level="
				+ level + ", icon=" + icon + ", iconCls=" + iconCls + ", desc=" + desc + ", parentResouece="
				+ parentResouece + ", children=" + children + ", actions=" + actions + ", leaf=" + leaf + ", isAction="
				+ isAction + ", subSystemUrl=" + subSystemUrl + ", isOpenNewWin=" + isOpenNewWin + ", isOutsideUrl="
				+ isOutsideUrl + ", isAlwaysEnable=" + isAlwaysEnable + "]";
	}

}
