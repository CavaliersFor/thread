package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class CombinationProductQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14710469079201L;

	private Long[] idArray;// 主键
	private String cpName;// 组合商品名称
	private String cpDesc;// 组合商品描述
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private BigDecimal cpPriceFrom;// 组合商品销售价格
	private BigDecimal cpPriceTo;// 组合商品销售价格
	private Integer cpStatus;// 状态1:出售中2：停止
	private Long product1Id;// 商品1id
	private String product1Name;// 商品1名称
	private BigDecimal product1PriceFrom;// 商品1原价格
	private BigDecimal product1PriceTo;// 商品1原价格
	private BigDecimal product1RealPriceFrom;// 商品1实际销售价格
	private BigDecimal product1RealPriceTo;// 商品1实际销售价格
	private String product1PicPath;// 商品1图片路径
	private Integer product1Num;// 商品1数量
	private Long product2Id;// 商品2id
	private String product2Name;// 商品2名称
	private BigDecimal product2PriceFrom;// 商品2价格
	private BigDecimal product2PriceTo;// 商品2价格
	private BigDecimal product2RealPriceFrom;// 商品2实际销售价格2
	private BigDecimal product2RealPriceTo;// 商品2实际销售价格2
	private String product2PicPath;// 商品2图片路径
	private Integer product2Num;// 商品2数量
	private Long product3Id;// 商品3id
	private String product3Name;// 商品3名称
	private BigDecimal product3PriceFrom;// 商品3价格
	private BigDecimal product3PriceTo;// 商品3价格
	private BigDecimal product3RealPriceFrom;// 商品3实际销售价格
	private BigDecimal product3RealPriceTo;// 商品3实际销售价格
	private String product3PicPath;// 商品3图片路径
	private Integer product3Num;// 商品3数量
	private Long product4Id;// 商品4id
	private String product4Name;// 商品4名称
	private BigDecimal product4PriceFrom;// 商品4价格
	private BigDecimal product4PriceTo;// 商品4价格
	private BigDecimal product4RealPriceFrom;// 商品4实际销售价格4
	private BigDecimal product4RealPriceTo;// 商品4实际销售价格4
	private String product4PicPath;// 商品4图片路径
	private Integer product4Num;// 商品4数量
	private Long product5Id;// 商品5id
	private String product5Name;// 商品5名称
	private BigDecimal product5PriceFrom;// 商品5价格
	private BigDecimal product5PriceTo;// 商品5价格
	private BigDecimal product5RealPriceFrom;// 商品5实际销售价格5
	private BigDecimal product5RealPriceTo;// 商品5实际销售价格5
	private String product5PicPath;// 商品5图片路径
	private Integer product5Num;// 商品5数量
	private Long enterpriseId;// 商家id
	private String cpPicPath;// 组合商品主图
	private Integer isFreePost;// 是否包邮 1：包邮 2：不包邮

	/*** 主键 */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键 */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 组合商品名称 */
	public String getCpName() {
		return cpName;
	}

	/*** 组合商品名称 */
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	/*** 组合商品描述 */
	public String getCpDesc() {
		return cpDesc;
	}

	/*** 组合商品描述 */
	public void setCpDesc(String cpDesc) {
		this.cpDesc = cpDesc;
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

	/*** 组合商品销售价格 */
	public BigDecimal getCpPriceFrom() {
		return cpPriceFrom;
	}

	/*** 组合商品销售价格 */
	public void setCpPriceFrom(BigDecimal cpPriceFrom) {
		this.cpPriceFrom = cpPriceFrom;
	}

	/*** 组合商品销售价格 */
	public BigDecimal getCpPriceTo() {
		return cpPriceTo;
	}

	/*** 组合商品销售价格 */
	public void setCpPriceTo(BigDecimal cpPriceTo) {
		this.cpPriceTo = cpPriceTo;
	}

	/*** 状态1:出售中2：停止 */
	public Integer getCpStatus() {
		return cpStatus;
	}

	/*** 状态1:出售中2：停止 */
	public void setCpStatus(Integer cpStatus) {
		this.cpStatus = cpStatus;
	}

	/*** 商品1id */
	public Long getProduct1Id() {
		return product1Id;
	}

	/*** 商品1id */
	public void setProduct1Id(Long product1Id) {
		this.product1Id = product1Id;
	}

	/*** 商品1名称 */
	public String getProduct1Name() {
		return product1Name;
	}

	/*** 商品1名称 */
	public void setProduct1Name(String product1Name) {
		this.product1Name = product1Name;
	}

	/*** 商品1原价格 */
	public BigDecimal getProduct1PriceFrom() {
		return product1PriceFrom;
	}

	/*** 商品1原价格 */
	public void setProduct1PriceFrom(BigDecimal product1PriceFrom) {
		this.product1PriceFrom = product1PriceFrom;
	}

	/*** 商品1原价格 */
	public BigDecimal getProduct1PriceTo() {
		return product1PriceTo;
	}

	/*** 商品1原价格 */
	public void setProduct1PriceTo(BigDecimal product1PriceTo) {
		this.product1PriceTo = product1PriceTo;
	}

	/*** 商品1实际销售价格 */
	public BigDecimal getProduct1RealPriceFrom() {
		return product1RealPriceFrom;
	}

	/*** 商品1实际销售价格 */
	public void setProduct1RealPriceFrom(BigDecimal product1RealPriceFrom) {
		this.product1RealPriceFrom = product1RealPriceFrom;
	}

	/*** 商品1实际销售价格 */
	public BigDecimal getProduct1RealPriceTo() {
		return product1RealPriceTo;
	}

	/*** 商品1实际销售价格 */
	public void setProduct1RealPriceTo(BigDecimal product1RealPriceTo) {
		this.product1RealPriceTo = product1RealPriceTo;
	}

	/*** 商品1图片路径 */
	public String getProduct1PicPath() {
		return product1PicPath;
	}

	/*** 商品1图片路径 */
	public void setProduct1PicPath(String product1PicPath) {
		this.product1PicPath = product1PicPath;
	}

	/*** 商品1数量 */
	public Integer getProduct1Num() {
		return product1Num;
	}

	/*** 商品1数量 */
	public void setProduct1Num(Integer product1Num) {
		this.product1Num = product1Num;
	}

	/*** 商品2id */
	public Long getProduct2Id() {
		return product2Id;
	}

	/*** 商品2id */
	public void setProduct2Id(Long product2Id) {
		this.product2Id = product2Id;
	}

	/*** 商品2名称 */
	public String getProduct2Name() {
		return product2Name;
	}

	/*** 商品2名称 */
	public void setProduct2Name(String product2Name) {
		this.product2Name = product2Name;
	}

	/*** 商品2价格 */
	public BigDecimal getProduct2PriceFrom() {
		return product2PriceFrom;
	}

	/*** 商品2价格 */
	public void setProduct2PriceFrom(BigDecimal product2PriceFrom) {
		this.product2PriceFrom = product2PriceFrom;
	}

	/*** 商品2价格 */
	public BigDecimal getProduct2PriceTo() {
		return product2PriceTo;
	}

	/*** 商品2价格 */
	public void setProduct2PriceTo(BigDecimal product2PriceTo) {
		this.product2PriceTo = product2PriceTo;
	}

	/*** 商品2实际销售价格2 */
	public BigDecimal getProduct2RealPriceFrom() {
		return product2RealPriceFrom;
	}

	/*** 商品2实际销售价格2 */
	public void setProduct2RealPriceFrom(BigDecimal product2RealPriceFrom) {
		this.product2RealPriceFrom = product2RealPriceFrom;
	}

	/*** 商品2实际销售价格2 */
	public BigDecimal getProduct2RealPriceTo() {
		return product2RealPriceTo;
	}

	/*** 商品2实际销售价格2 */
	public void setProduct2RealPriceTo(BigDecimal product2RealPriceTo) {
		this.product2RealPriceTo = product2RealPriceTo;
	}

	/*** 商品2图片路径 */
	public String getProduct2PicPath() {
		return product2PicPath;
	}

	/*** 商品2图片路径 */
	public void setProduct2PicPath(String product2PicPath) {
		this.product2PicPath = product2PicPath;
	}

	/*** 商品2数量 */
	public Integer getProduct2Num() {
		return product2Num;
	}

	/*** 商品2数量 */
	public void setProduct2Num(Integer product2Num) {
		this.product2Num = product2Num;
	}

	/*** 商品3id */
	public Long getProduct3Id() {
		return product3Id;
	}

	/*** 商品3id */
	public void setProduct3Id(Long product3Id) {
		this.product3Id = product3Id;
	}

	/*** 商品3名称 */
	public String getProduct3Name() {
		return product3Name;
	}

	/*** 商品3名称 */
	public void setProduct3Name(String product3Name) {
		this.product3Name = product3Name;
	}

	/*** 商品3价格 */
	public BigDecimal getProduct3PriceFrom() {
		return product3PriceFrom;
	}

	/*** 商品3价格 */
	public void setProduct3PriceFrom(BigDecimal product3PriceFrom) {
		this.product3PriceFrom = product3PriceFrom;
	}

	/*** 商品3价格 */
	public BigDecimal getProduct3PriceTo() {
		return product3PriceTo;
	}

	/*** 商品3价格 */
	public void setProduct3PriceTo(BigDecimal product3PriceTo) {
		this.product3PriceTo = product3PriceTo;
	}

	/*** 商品3实际销售价格 */
	public BigDecimal getProduct3RealPriceFrom() {
		return product3RealPriceFrom;
	}

	/*** 商品3实际销售价格 */
	public void setProduct3RealPriceFrom(BigDecimal product3RealPriceFrom) {
		this.product3RealPriceFrom = product3RealPriceFrom;
	}

	/*** 商品3实际销售价格 */
	public BigDecimal getProduct3RealPriceTo() {
		return product3RealPriceTo;
	}

	/*** 商品3实际销售价格 */
	public void setProduct3RealPriceTo(BigDecimal product3RealPriceTo) {
		this.product3RealPriceTo = product3RealPriceTo;
	}

	/*** 商品3图片路径 */
	public String getProduct3PicPath() {
		return product3PicPath;
	}

	/*** 商品3图片路径 */
	public void setProduct3PicPath(String product3PicPath) {
		this.product3PicPath = product3PicPath;
	}

	/*** 商品3数量 */
	public Integer getProduct3Num() {
		return product3Num;
	}

	/*** 商品3数量 */
	public void setProduct3Num(Integer product3Num) {
		this.product3Num = product3Num;
	}

	/*** 商品4id */
	public Long getProduct4Id() {
		return product4Id;
	}

	/*** 商品4id */
	public void setProduct4Id(Long product4Id) {
		this.product4Id = product4Id;
	}

	/*** 商品4名称 */
	public String getProduct4Name() {
		return product4Name;
	}

	/*** 商品4名称 */
	public void setProduct4Name(String product4Name) {
		this.product4Name = product4Name;
	}

	/*** 商品4价格 */
	public BigDecimal getProduct4PriceFrom() {
		return product4PriceFrom;
	}

	/*** 商品4价格 */
	public void setProduct4PriceFrom(BigDecimal product4PriceFrom) {
		this.product4PriceFrom = product4PriceFrom;
	}

	/*** 商品4价格 */
	public BigDecimal getProduct4PriceTo() {
		return product4PriceTo;
	}

	/*** 商品4价格 */
	public void setProduct4PriceTo(BigDecimal product4PriceTo) {
		this.product4PriceTo = product4PriceTo;
	}

	/*** 商品4实际销售价格4 */
	public BigDecimal getProduct4RealPriceFrom() {
		return product4RealPriceFrom;
	}

	/*** 商品4实际销售价格4 */
	public void setProduct4RealPriceFrom(BigDecimal product4RealPriceFrom) {
		this.product4RealPriceFrom = product4RealPriceFrom;
	}

	/*** 商品4实际销售价格4 */
	public BigDecimal getProduct4RealPriceTo() {
		return product4RealPriceTo;
	}

	/*** 商品4实际销售价格4 */
	public void setProduct4RealPriceTo(BigDecimal product4RealPriceTo) {
		this.product4RealPriceTo = product4RealPriceTo;
	}

	/*** 商品4图片路径 */
	public String getProduct4PicPath() {
		return product4PicPath;
	}

	/*** 商品4图片路径 */
	public void setProduct4PicPath(String product4PicPath) {
		this.product4PicPath = product4PicPath;
	}

	/*** 商品4数量 */
	public Integer getProduct4Num() {
		return product4Num;
	}

	/*** 商品4数量 */
	public void setProduct4Num(Integer product4Num) {
		this.product4Num = product4Num;
	}

	/*** 商品5id */
	public Long getProduct5Id() {
		return product5Id;
	}

	/*** 商品5id */
	public void setProduct5Id(Long product5Id) {
		this.product5Id = product5Id;
	}

	/*** 商品5名称 */
	public String getProduct5Name() {
		return product5Name;
	}

	/*** 商品5名称 */
	public void setProduct5Name(String product5Name) {
		this.product5Name = product5Name;
	}

	/*** 商品5价格 */
	public BigDecimal getProduct5PriceFrom() {
		return product5PriceFrom;
	}

	/*** 商品5价格 */
	public void setProduct5PriceFrom(BigDecimal product5PriceFrom) {
		this.product5PriceFrom = product5PriceFrom;
	}

	/*** 商品5价格 */
	public BigDecimal getProduct5PriceTo() {
		return product5PriceTo;
	}

	/*** 商品5价格 */
	public void setProduct5PriceTo(BigDecimal product5PriceTo) {
		this.product5PriceTo = product5PriceTo;
	}

	/*** 商品5实际销售价格5 */
	public BigDecimal getProduct5RealPriceFrom() {
		return product5RealPriceFrom;
	}

	/*** 商品5实际销售价格5 */
	public void setProduct5RealPriceFrom(BigDecimal product5RealPriceFrom) {
		this.product5RealPriceFrom = product5RealPriceFrom;
	}

	/*** 商品5实际销售价格5 */
	public BigDecimal getProduct5RealPriceTo() {
		return product5RealPriceTo;
	}

	/*** 商品5实际销售价格5 */
	public void setProduct5RealPriceTo(BigDecimal product5RealPriceTo) {
		this.product5RealPriceTo = product5RealPriceTo;
	}

	/*** 商品5图片路径 */
	public String getProduct5PicPath() {
		return product5PicPath;
	}

	/*** 商品5图片路径 */
	public void setProduct5PicPath(String product5PicPath) {
		this.product5PicPath = product5PicPath;
	}

	/*** 商品5数量 */
	public Integer getProduct5Num() {
		return product5Num;
	}

	/*** 商品5数量 */
	public void setProduct5Num(Integer product5Num) {
		this.product5Num = product5Num;
	}

	/*** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/*** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/*** 组合商品主图 */
	public String getCpPicPath() {
		return cpPicPath;
	}

	/*** 组合商品主图 */
	public void setCpPicPath(String cpPicPath) {
		this.cpPicPath = cpPicPath;
	}

	/*** 是否包邮 1：包邮 2：不包邮 */
	public Integer getIsFreePost() {
		return isFreePost;
	}

	/*** 是否包邮 1：包邮 2：不包邮 */
	public void setIsFreePost(Integer isFreePost) {
		this.isFreePost = isFreePost;
	}
}
