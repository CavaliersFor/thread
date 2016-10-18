package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类
 */
public class Product implements Serializable {
	private static final long serialVersionUID = 14701809135232L;

	private Long id;// 主键
	private String name;// 商品名称
	private BigDecimal price;// 价格，精确到分
	private BigDecimal salePrice;// 产品销售价格，实际销售价格，精确到分
	private String productDesc;// 产品描述
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private String pathUrl;// 主图片地址
	private Integer saleNum;// 产品的销量
	private Integer cid;// 产品类目id
	private String catName;// 产品类目名称
	private Integer status;// 产品状态1：销售中2：仓库中
	private Integer productNum;// 产品总数
	private String propsName;// 商品属性名称(p1:p1_name;p2:p2_name)
	private String propsAlias;// 属性值名称(p1:v1:v1_name;p1:v2:v2_name;p1:v3:v3_name;p2:v1:v1_name;p2:v2:v2_name)
	private Integer type;// 商品类型 1：普通 2：推荐 3：新品4：热销
	private Integer distributionMode;// 配送方式1：快递2：自取
	private Integer isPost;// 是否包邮1：是 2：否
	private Long expressId;// 快递模板id
	private Long discountId;// 优惠id(为空则不享受优惠)
	private BigDecimal volume;// 体积
	private BigDecimal weight;// 重量
	private String enterpriseProductNo;// 商家系统编号
	private Long enterpriseId;// 商家id
	private String relativePath;//相对路径
	public Product() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键
	 */
	public Product(Long id) {
		this.id = id;
	}

	/** 主键 */
	public Long getId() {
		return id;
	}

	/** 主键 */
	public void setId(Long id) {
		this.id = id;
	}

	/** 商品名称 */
	public String getName() {
		return name;
	}

	/** 商品名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 价格，精确到分 */
	public BigDecimal getPrice() {
		return price;
	}

	/** 价格，精确到分 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/** 产品销售价格，实际销售价格，精确到分 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	/** 产品销售价格，实际销售价格，精确到分 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	/** 产品描述 */
	public String getProductDesc() {
		return productDesc;
	}

	/** 产品描述 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/** 创建时间 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/** 创建时间 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/** 修改时间 */
	public Date getGmtModify() {
		return gmtModify;
	}

	/** 修改时间 */
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	/** 主图片地址 */
	public String getPathUrl() {
		return pathUrl;
	}

	/** 主图片地址 */
	public void setPathUrl(String pathUrl) {
		this.pathUrl = pathUrl;
	}

	/** 产品的销量 */
	public Integer getSaleNum() {
		return saleNum;
	}

	/** 产品的销量 */
	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	/** 产品类目id */
	public Integer getCid() {
		return cid;
	}

	/** 产品类目id */
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	/** 产品类目名称 */
	public String getCatName() {
		return catName;
	}

	/** 产品类目名称 */
	public void setCatName(String catName) {
		this.catName = catName;
	}

	/** 产品状态1：销售中2：仓库中 */
	public Integer getStatus() {
		return status;
	}

	/** 产品状态1：销售中2：仓库中 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 产品总数 */
	public Integer getProductNum() {
		return productNum;
	}

	/** 产品总数 */
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	/** 商品属性名称(p1:p1_name;p2:p2_name) */
	public String getPropsName() {
		return propsName;
	}

	/** 商品属性名称(p1:p1_name;p2:p2_name) */
	public void setPropsName(String propsName) {
		this.propsName = propsName;
	}

	/**
	 * 属性值名称(p1:v1:v1_name;p1:v2:v2_name;p1:v3:v3_name;p2:v1:v1_name;p2:v2:
	 * v2_name)
	 */
	public String getPropsAlias() {
		return propsAlias;
	}

	/**
	 * 属性值名称(p1:v1:v1_name;p1:v2:v2_name;p1:v3:v3_name;p2:v1:v1_name;p2:v2:
	 * v2_name)
	 */
	public void setPropsAlias(String propsAlias) {
		this.propsAlias = propsAlias;
	}

	/** 商品类型 1：普通 2：推荐 3：新品4：热销 */
	public Integer getType() {
		return type;
	}

	/** 商品类型 1：普通 2：推荐 3：新品4：热销 */
	public void setType(Integer type) {
		this.type = type;
	}

	/** 配送方式1：快递2：自取 */
	public Integer getDistributionMode() {
		return distributionMode;
	}

	/** 配送方式1：快递2：自取 */
	public void setDistributionMode(Integer distributionMode) {
		this.distributionMode = distributionMode;
	}

	/** 是否包邮1：是 2：否 */
	public Integer getIsPost() {
		return isPost;
	}

	/** 是否包邮1：是 2：否 */
	public void setIsPost(Integer isPost) {
		this.isPost = isPost;
	}

	/** 快递模板id */
	public Long getExpressId() {
		return expressId;
	}

	/** 快递模板id */
	public void setExpressId(Long expressId) {
		this.expressId = expressId;
	}

	/** 优惠id(为空则不享受优惠) */
	public Long getDiscountId() {
		return discountId;
	}

	/** 优惠id(为空则不享受优惠) */
	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	/** 体积 */
	public BigDecimal getVolume() {
		return volume;
	}

	/** 体积 */
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	/** 重量 */
	public BigDecimal getWeight() {
		return weight;
	}

	/** 重量 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	/** 商家系统编号 */
	public String getEnterpriseProductNo() {
		return enterpriseProductNo;
	}

	/** 商家系统编号 */
	public void setEnterpriseProductNo(String enterpriseProductNo) {
		this.enterpriseProductNo = enterpriseProductNo;
	}

	/** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Override
	public String toString() {
		return "Product [ id=" + id + ", name=" + name + ", price=" + price + ", salePrice=" + salePrice
				+ ", productDesc=" + productDesc + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify
				+ ", pathUrl=" + pathUrl + ", saleNum=" + saleNum + ", cid=" + cid + ", catName=" + catName
				+ ", status=" + status + ", productNum=" + productNum + ", propsName=" + propsName + ", propsAlias="
				+ propsAlias + ", type=" + type + ", distributionMode=" + distributionMode + ", isPost=" + isPost
				+ ", expressId=" + expressId + ", discountId=" + discountId + ", volume=" + volume + ", weight="
				+ weight + ", enterpriseProductNo=" + enterpriseProductNo + ", enterpriseId=" + enterpriseId + "]";
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
}
