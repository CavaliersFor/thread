package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class ProductQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14701809135231L;

	private Long[] idArray;// 主键
	private String name;// 商品名称
	private BigDecimal priceFrom;// 价格，精确到分
	private BigDecimal priceTo;// 价格，精确到分
	private BigDecimal salePriceFrom;// 产品销售价格，实际销售价格，精确到分
	private BigDecimal salePriceTo;// 产品销售价格，实际销售价格，精确到分
	private String productDesc;// 产品描述
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private String pathUrl;// 主图片地址
	private Integer saleNum;// 产品的销量
	private Integer cid;// 产品类目id
	private String catName;// 产品类目名称
	private Integer status;// 产品状态1：销售中2：仓库中
	private Integer[] statusArray;// 产品状态1：销售中2：仓库中
	private Integer productNum;// 产品总数
	private String propsName;// 商品属性名称(p1:p1_name;p2:p2_name)
	private String propsAlias;// 属性值名称(p1:v1:v1_name;p1:v2:v2_name;p1:v3:v3_name;p2:v1:v1_name;p2:v2:v2_name)
	private Integer type;// 商品类型 1：普通 2：推荐 3：新品4：热销
	private Integer distributionMode;// 配送方式1：快递2：自取
	private Integer isPost;// 是否包邮1：是 2：否
	private Long expressId;// 快递模板id
	private Long discountId;// 优惠id(为空则不享受优惠)
	private BigDecimal volumeFrom;// 体积
	private BigDecimal volumeTo;// 体积
	private BigDecimal weightFrom;// 重量
	private BigDecimal weightTo;// 重量
	private String enterpriseProductNo;// 商家系统编号
	private Long enterpriseId;// 商家id
	private Map<String,Object> properties;//查询属性

	/*** 主键 */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键 */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 商品名称 */
	public String getName() {
		return name;
	}

	/*** 商品名称 */
	public void setName(String name) {
		this.name = name;
	}

	/*** 价格，精确到分 */
	public BigDecimal getPriceFrom() {
		return priceFrom;
	}

	/*** 价格，精确到分 */
	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	/*** 价格，精确到分 */
	public BigDecimal getPriceTo() {
		return priceTo;
	}

	/*** 价格，精确到分 */
	public void setPriceTo(BigDecimal priceTo) {
		this.priceTo = priceTo;
	}

	/*** 产品销售价格，实际销售价格，精确到分 */
	public BigDecimal getSalePriceFrom() {
		return salePriceFrom;
	}

	/*** 产品销售价格，实际销售价格，精确到分 */
	public void setSalePriceFrom(BigDecimal salePriceFrom) {
		this.salePriceFrom = salePriceFrom;
	}

	/*** 产品销售价格，实际销售价格，精确到分 */
	public BigDecimal getSalePriceTo() {
		return salePriceTo;
	}

	/*** 产品销售价格，实际销售价格，精确到分 */
	public void setSalePriceTo(BigDecimal salePriceTo) {
		this.salePriceTo = salePriceTo;
	}

	/*** 产品描述 */
	public String getProductDesc() {
		return productDesc;
	}

	/*** 产品描述 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/*** 创建时间 */
	public Date getGmtCreateFrom() {
		return gmtCreateFrom;
	}

	/*** 创建时间 */
	public void setGmtCreateFrom(Date gmtCreateFrom) {
		this.gmtCreateFrom = gmtCreateFrom;
	}

	/*** 创建时间 */
	public Date getGmtCreateTo() {
		return gmtCreateTo;
	}

	/*** 创建时间 */
	public void setGmtCreateTo(Date gmtCreateTo) {
		this.gmtCreateTo = gmtCreateTo;
	}

	/*** 修改时间 */
	public Date getGmtModifyFrom() {
		return gmtModifyFrom;
	}

	/*** 修改时间 */
	public void setGmtModifyFrom(Date gmtModifyFrom) {
		this.gmtModifyFrom = gmtModifyFrom;
	}

	/*** 修改时间 */
	public Date getGmtModifyTo() {
		return gmtModifyTo;
	}

	/*** 修改时间 */
	public void setGmtModifyTo(Date gmtModifyTo) {
		this.gmtModifyTo = gmtModifyTo;
	}

	/*** 主图片地址 */
	public String getPathUrl() {
		return pathUrl;
	}

	/*** 主图片地址 */
	public void setPathUrl(String pathUrl) {
		this.pathUrl = pathUrl;
	}

	/*** 产品的销量 */
	public Integer getSaleNum() {
		return saleNum;
	}

	/*** 产品的销量 */
	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	/*** 产品类目id */
	public Integer getCid() {
		return cid;
	}

	/*** 产品类目id */
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	/*** 产品类目名称 */
	public String getCatName() {
		return catName;
	}

	/*** 产品类目名称 */
	public void setCatName(String catName) {
		this.catName = catName;
	}

	/*** 产品状态1：销售中2：仓库中 */
	public Integer getStatus() {
		return status;
	}

	/*** 产品状态1：销售中2：仓库中 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 产品状态1：销售中2：仓库中 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 产品状态1：销售中2：仓库中 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 产品总数 */
	public Integer getProductNum() {
		return productNum;
	}

	/*** 产品总数 */
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	/*** 商品属性名称(p1:p1_name;p2:p2_name) */
	public String getPropsName() {
		return propsName;
	}

	/*** 商品属性名称(p1:p1_name;p2:p2_name) */
	public void setPropsName(String propsName) {
		this.propsName = propsName;
	}

	/***
	 * 属性值名称(p1:v1:v1_name;p1:v2:v2_name;p1:v3:v3_name;p2:v1:v1_name;p2:v2:
	 * v2_name)
	 */
	public String getPropsAlias() {
		return propsAlias;
	}

	/***
	 * 属性值名称(p1:v1:v1_name;p1:v2:v2_name;p1:v3:v3_name;p2:v1:v1_name;p2:v2:
	 * v2_name)
	 */
	public void setPropsAlias(String propsAlias) {
		this.propsAlias = propsAlias;
	}

	/*** 商品类型 1：普通 2：推荐 3：新品4：热销 */
	public Integer getType() {
		return type;
	}

	/*** 商品类型 1：普通 2：推荐 3：新品4：热销 */
	public void setType(Integer type) {
		this.type = type;
	}

	/*** 配送方式1：快递2：自取 */
	public Integer getDistributionMode() {
		return distributionMode;
	}

	/*** 配送方式1：快递2：自取 */
	public void setDistributionMode(Integer distributionMode) {
		this.distributionMode = distributionMode;
	}

	/*** 是否包邮1：是 2：否 */
	public Integer getIsPost() {
		return isPost;
	}

	/*** 是否包邮1：是 2：否 */
	public void setIsPost(Integer isPost) {
		this.isPost = isPost;
	}

	/*** 快递模板id */
	public Long getExpressId() {
		return expressId;
	}

	/*** 快递模板id */
	public void setExpressId(Long expressId) {
		this.expressId = expressId;
	}

	/*** 优惠id(为空则不享受优惠) */
	public Long getDiscountId() {
		return discountId;
	}

	/*** 优惠id(为空则不享受优惠) */
	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}

	/*** 体积 */
	public BigDecimal getVolumeFrom() {
		return volumeFrom;
	}

	/*** 体积 */
	public void setVolumeFrom(BigDecimal volumeFrom) {
		this.volumeFrom = volumeFrom;
	}

	/*** 体积 */
	public BigDecimal getVolumeTo() {
		return volumeTo;
	}

	/*** 体积 */
	public void setVolumeTo(BigDecimal volumeTo) {
		this.volumeTo = volumeTo;
	}

	/*** 重量 */
	public BigDecimal getWeightFrom() {
		return weightFrom;
	}

	/*** 重量 */
	public void setWeightFrom(BigDecimal weightFrom) {
		this.weightFrom = weightFrom;
	}

	/*** 重量 */
	public BigDecimal getWeightTo() {
		return weightTo;
	}

	/*** 重量 */
	public void setWeightTo(BigDecimal weightTo) {
		this.weightTo = weightTo;
	}

	/*** 商家系统编号 */
	public String getEnterpriseProductNo() {
		return enterpriseProductNo;
	}

	/*** 商家系统编号 */
	public void setEnterpriseProductNo(String enterpriseProductNo) {
		this.enterpriseProductNo = enterpriseProductNo;
	}

	/*** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/*** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
}
