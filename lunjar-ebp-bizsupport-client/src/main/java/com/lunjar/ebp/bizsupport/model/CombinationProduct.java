package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类
 */
public class CombinationProduct implements Serializable {
	private static final long serialVersionUID = 14710469079202L;

	private Long id;// 主键
	private String cpName;// 组合商品名称
	private String cpDesc;// 组合商品描述
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private BigDecimal cpPrice;// 组合商品销售价格
	private Integer cpStatus;// 状态1:出售中2：停止
	private Long product1Id;// 商品1id
	private String product1Name;// 商品1名称
	private BigDecimal product1Price;// 商品1原价格
	private BigDecimal product1RealPrice;// 商品1实际销售价格
	private String product1PicPath;// 商品1图片路径
	private String product1AbsPicPath;//商品1图片绝对路径
	private Integer product1Num;// 商品1数量
	private Long product2Id;// 商品2id
	private String product2Name;// 商品2名称
	private BigDecimal product2Price;// 商品2价格
	private String product2AbsPicPath;//商品2图片绝对路径
	private BigDecimal product2RealPrice;// 商品2实际销售价格2
	private String product2PicPath;// 商品2图片路径
	private Integer product2Num;// 商品2数量
	private Long product3Id;// 商品3id
	private String product3Name;// 商品3名称
	private BigDecimal product3Price;// 商品3价格
	private BigDecimal product3RealPrice;// 商品3实际销售价格
	private String product3PicPath;// 商品3图片路径
	private String product3AbsPicPath;//商品3图片绝对路径
	private Integer product3Num;// 商品3数量
	private Long product4Id;// 商品4id
	private String product4Name;// 商品4名称
	private BigDecimal product4Price;// 商品4价格
	private BigDecimal product4RealPrice;// 商品4实际销售价格4
	private String product4PicPath;// 商品4图片路径
	private String product4AbsPicPath;//商品4图片绝对路径
	private Integer product4Num;// 商品4数量
	private Long product5Id;// 商品5id
	private String product5Name;// 商品5名称
	private BigDecimal product5Price;// 商品5价格
	private BigDecimal product5RealPrice;// 商品5实际销售价格5
	private String product5PicPath;// 商品5图片路径
	private String product5AbsPicPath;//商品5图片绝对路径
	private Integer product5Num;// 商品5数量
	private Long enterpriseId;// 商家id
	private String cpPicPath;// 组合商品主图
	private String cpAbsPicPath;// 组合商品主图绝对路径
	private Integer isFreePost;// 是否包邮 1：包邮 2：不包邮

	public CombinationProduct() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键
	 */
	public CombinationProduct(Long id) {
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

	/** 组合商品名称 */
	public String getCpName() {
		return cpName;
	}

	/** 组合商品名称 */
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	/** 组合商品描述 */
	public String getCpDesc() {
		return cpDesc;
	}

	/** 组合商品描述 */
	public void setCpDesc(String cpDesc) {
		this.cpDesc = cpDesc;
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

	/** 组合商品销售价格 */
	public BigDecimal getCpPrice() {
		return cpPrice;
	}

	/** 组合商品销售价格 */
	public void setCpPrice(BigDecimal cpPrice) {
		this.cpPrice = cpPrice;
	}

	/** 状态1:出售中2：停止 */
	public Integer getCpStatus() {
		return cpStatus;
	}

	/** 状态1:出售中2：停止 */
	public void setCpStatus(Integer cpStatus) {
		this.cpStatus = cpStatus;
	}

	/** 商品1id */
	public Long getProduct1Id() {
		return product1Id;
	}

	/** 商品1id */
	public void setProduct1Id(Long product1Id) {
		this.product1Id = product1Id;
	}

	/** 商品1名称 */
	public String getProduct1Name() {
		return product1Name;
	}

	/** 商品1名称 */
	public void setProduct1Name(String product1Name) {
		this.product1Name = product1Name;
	}

	/** 商品1原价格 */
	public BigDecimal getProduct1Price() {
		return product1Price;
	}

	/** 商品1原价格 */
	public void setProduct1Price(BigDecimal product1Price) {
		this.product1Price = product1Price;
	}

	/** 商品1实际销售价格 */
	public BigDecimal getProduct1RealPrice() {
		return product1RealPrice;
	}

	/** 商品1实际销售价格 */
	public void setProduct1RealPrice(BigDecimal product1RealPrice) {
		this.product1RealPrice = product1RealPrice;
	}

	/** 商品1图片路径 */
	public String getProduct1PicPath() {
		return product1PicPath;
	}

	/** 商品1图片路径 */
	public void setProduct1PicPath(String product1PicPath) {
		this.product1PicPath = product1PicPath;
	}

	/** 商品1数量 */
	public Integer getProduct1Num() {
		return product1Num;
	}

	/** 商品1数量 */
	public void setProduct1Num(Integer product1Num) {
		this.product1Num = product1Num;
	}

	/** 商品2id */
	public Long getProduct2Id() {
		return product2Id;
	}

	/** 商品2id */
	public void setProduct2Id(Long product2Id) {
		this.product2Id = product2Id;
	}

	/** 商品2名称 */
	public String getProduct2Name() {
		return product2Name;
	}

	/** 商品2名称 */
	public void setProduct2Name(String product2Name) {
		this.product2Name = product2Name;
	}

	/** 商品2价格 */
	public BigDecimal getProduct2Price() {
		return product2Price;
	}

	/** 商品2价格 */
	public void setProduct2Price(BigDecimal product2Price) {
		this.product2Price = product2Price;
	}

	/** 商品2实际销售价格2 */
	public BigDecimal getProduct2RealPrice() {
		return product2RealPrice;
	}

	/** 商品2实际销售价格2 */
	public void setProduct2RealPrice(BigDecimal product2RealPrice) {
		this.product2RealPrice = product2RealPrice;
	}

	/** 商品2图片路径 */
	public String getProduct2PicPath() {
		return product2PicPath;
	}

	/** 商品2图片路径 */
	public void setProduct2PicPath(String product2PicPath) {
		this.product2PicPath = product2PicPath;
	}

	/** 商品2数量 */
	public Integer getProduct2Num() {
		return product2Num;
	}

	/** 商品2数量 */
	public void setProduct2Num(Integer product2Num) {
		this.product2Num = product2Num;
	}

	/** 商品3id */
	public Long getProduct3Id() {
		return product3Id;
	}

	/** 商品3id */
	public void setProduct3Id(Long product3Id) {
		this.product3Id = product3Id;
	}

	/** 商品3名称 */
	public String getProduct3Name() {
		return product3Name;
	}

	/** 商品3名称 */
	public void setProduct3Name(String product3Name) {
		this.product3Name = product3Name;
	}

	/** 商品3价格 */
	public BigDecimal getProduct3Price() {
		return product3Price;
	}

	/** 商品3价格 */
	public void setProduct3Price(BigDecimal product3Price) {
		this.product3Price = product3Price;
	}

	/** 商品3实际销售价格 */
	public BigDecimal getProduct3RealPrice() {
		return product3RealPrice;
	}

	/** 商品3实际销售价格 */
	public void setProduct3RealPrice(BigDecimal product3RealPrice) {
		this.product3RealPrice = product3RealPrice;
	}

	/** 商品3图片路径 */
	public String getProduct3PicPath() {
		return product3PicPath;
	}

	/** 商品3图片路径 */
	public void setProduct3PicPath(String product3PicPath) {
		this.product3PicPath = product3PicPath;
	}

	/** 商品3数量 */
	public Integer getProduct3Num() {
		return product3Num;
	}

	/** 商品3数量 */
	public void setProduct3Num(Integer product3Num) {
		this.product3Num = product3Num;
	}

	/** 商品4id */
	public Long getProduct4Id() {
		return product4Id;
	}

	/** 商品4id */
	public void setProduct4Id(Long product4Id) {
		this.product4Id = product4Id;
	}

	/** 商品4名称 */
	public String getProduct4Name() {
		return product4Name;
	}

	/** 商品4名称 */
	public void setProduct4Name(String product4Name) {
		this.product4Name = product4Name;
	}

	/** 商品4价格 */
	public BigDecimal getProduct4Price() {
		return product4Price;
	}

	/** 商品4价格 */
	public void setProduct4Price(BigDecimal product4Price) {
		this.product4Price = product4Price;
	}

	/** 商品4实际销售价格4 */
	public BigDecimal getProduct4RealPrice() {
		return product4RealPrice;
	}

	/** 商品4实际销售价格4 */
	public void setProduct4RealPrice(BigDecimal product4RealPrice) {
		this.product4RealPrice = product4RealPrice;
	}

	/** 商品4图片路径 */
	public String getProduct4PicPath() {
		return product4PicPath;
	}

	/** 商品4图片路径 */
	public void setProduct4PicPath(String product4PicPath) {
		this.product4PicPath = product4PicPath;
	}

	/** 商品4数量 */
	public Integer getProduct4Num() {
		return product4Num;
	}

	/** 商品4数量 */
	public void setProduct4Num(Integer product4Num) {
		this.product4Num = product4Num;
	}

	/** 商品5id */
	public Long getProduct5Id() {
		return product5Id;
	}

	/** 商品5id */
	public void setProduct5Id(Long product5Id) {
		this.product5Id = product5Id;
	}

	/** 商品5名称 */
	public String getProduct5Name() {
		return product5Name;
	}

	/** 商品5名称 */
	public void setProduct5Name(String product5Name) {
		this.product5Name = product5Name;
	}

	/** 商品5价格 */
	public BigDecimal getProduct5Price() {
		return product5Price;
	}

	/** 商品5价格 */
	public void setProduct5Price(BigDecimal product5Price) {
		this.product5Price = product5Price;
	}

	/** 商品5实际销售价格5 */
	public BigDecimal getProduct5RealPrice() {
		return product5RealPrice;
	}

	/** 商品5实际销售价格5 */
	public void setProduct5RealPrice(BigDecimal product5RealPrice) {
		this.product5RealPrice = product5RealPrice;
	}

	/** 商品5图片路径 */
	public String getProduct5PicPath() {
		return product5PicPath;
	}

	/** 商品5图片路径 */
	public void setProduct5PicPath(String product5PicPath) {
		this.product5PicPath = product5PicPath;
	}

	/** 商品5数量 */
	public Integer getProduct5Num() {
		return product5Num;
	}

	/** 商品5数量 */
	public void setProduct5Num(Integer product5Num) {
		this.product5Num = product5Num;
	}

	/** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/** 组合商品主图 */
	public String getCpPicPath() {
		return cpPicPath;
	}

	/** 组合商品主图 */
	public void setCpPicPath(String cpPicPath) {
		this.cpPicPath = cpPicPath;
	}

	/** 是否包邮 1：包邮 2：不包邮 */
	public Integer getIsFreePost() {
		return isFreePost;
	}

	/** 是否包邮 1：包邮 2：不包邮 */
	public void setIsFreePost(Integer isFreePost) {
		this.isFreePost = isFreePost;
	}

	@Override
	public String toString() {
		return "CombinationProduct [ id=" + id + ", cpName=" + cpName + ", cpDesc=" + cpDesc + ", gmtCreate="
				+ gmtCreate + ", gmtModify=" + gmtModify + ", cpPrice=" + cpPrice + ", cpStatus=" + cpStatus
				+ ", product1Id=" + product1Id + ", product1Name=" + product1Name + ", product1Price=" + product1Price
				+ ", product1RealPrice=" + product1RealPrice + ", product1PicPath=" + product1PicPath + ", product1Num="
				+ product1Num + ", product2Id=" + product2Id + ", product2Name=" + product2Name + ", product2Price="
				+ product2Price + ", product2RealPrice=" + product2RealPrice + ", product2PicPath=" + product2PicPath
				+ ", product2Num=" + product2Num + ", product3Id=" + product3Id + ", product3Name=" + product3Name
				+ ", product3Price=" + product3Price + ", product3RealPrice=" + product3RealPrice + ", product3PicPath="
				+ product3PicPath + ", product3Num=" + product3Num + ", product4Id=" + product4Id + ", product4Name="
				+ product4Name + ", product4Price=" + product4Price + ", product4RealPrice=" + product4RealPrice
				+ ", product4PicPath=" + product4PicPath + ", product4Num=" + product4Num + ", product5Id=" + product5Id
				+ ", product5Name=" + product5Name + ", product5Price=" + product5Price + ", product5RealPrice="
				+ product5RealPrice + ", product5PicPath=" + product5PicPath + ", product5Num=" + product5Num
				+ ", enterpriseId=" + enterpriseId + ", cpPicPath=" + cpPicPath + ", isFreePost=" + isFreePost + "]";
	}

	public String getProduct1AbsPicPath() {
		return product1AbsPicPath;
	}

	public void setProduct1AbsPicPath(String product1AbsPicPath) {
		this.product1AbsPicPath = product1AbsPicPath;
	}

	public String getProduct2AbsPicPath() {
		return product2AbsPicPath;
	}

	public void setProduct2AbsPicPath(String product2AbsPicPath) {
		this.product2AbsPicPath = product2AbsPicPath;
	}

	public String getProduct3AbsPicPath() {
		return product3AbsPicPath;
	}

	public void setProduct3AbsPicPath(String product3AbsPicPath) {
		this.product3AbsPicPath = product3AbsPicPath;
	}

	public String getProduct4AbsPicPath() {
		return product4AbsPicPath;
	}

	public void setProduct4AbsPicPath(String product4AbsPicPath) {
		this.product4AbsPicPath = product4AbsPicPath;
	}

	public String getProduct5AbsPicPath() {
		return product5AbsPicPath;
	}

	public void setProduct5AbsPicPath(String product5AbsPicPath) {
		this.product5AbsPicPath = product5AbsPicPath;
	}

	public String getCpAbsPicPath() {
		return cpAbsPicPath;
	}

	public void setCpAbsPicPath(String cpAbsPicPath) {
		this.cpAbsPicPath = cpAbsPicPath;
	}
}
