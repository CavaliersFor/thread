package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lunjar.ebp.bizsupport.model.CombinationProduct;
import com.lunjar.ebp.bizsupport.model.ProductSku;
/**
 * 商户组合Dto
 * @author Administrator
 *
 */
public class CombinationProductDto implements Serializable{
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
	private Integer product1Num;// 商品1数量
	private Long product2Id;// 商品2id
	private String product2Name;// 商品2名称
	private BigDecimal product2Price;// 商品2价格
	private BigDecimal product2RealPrice;// 商品2实际销售价格2
	private String product2PicPath;// 商品2图片路径
	private Integer product2Num;// 商品2数量
	private Long product3Id;// 商品3id
	private String product3Name;// 商品3名称
	private BigDecimal product3Price;// 商品3价格
	private BigDecimal product3RealPrice;// 商品3实际销售价格
	private String product3PicPath;// 商品3图片路径
	private Integer product3Num;// 商品3数量
	private Long product4Id;// 商品4id
	private String product4Name;// 商品4名称
	private BigDecimal product4Price;// 商品4价格
	private BigDecimal product4RealPrice;// 商品4实际销售价格4
	private String product4PicPath;// 商品4图片路径
	private Integer product4Num;// 商品4数量
	private Long product5Id;// 商品5id
	private String product5Name;// 商品5名称
	private BigDecimal product5Price;// 商品5价格
	private BigDecimal product5RealPrice;// 商品5实际销售价格5
	private String product5PicPath;// 商品5图片路径
	private Integer product5Num;// 商品5数量
	private Long enterpriseId;// 商家id
	private String cpPicPath;// 组合商品主图
	
	private List<ProductSku> product1Skus; //商品1的sku
	private List<ProductSku> product2Skus; //商品2的sku
	private List<ProductSku> product3Skus; //商品3的sku
	private List<ProductSku> product4Skus; //商品4的sku
	private List<ProductSku> product5Skus; //商品5的sku
	
	private CombinationProduct combinationProduct; //组合商品
	
	public CombinationProductDto(CombinationProduct c){
		this.combinationProduct = c;
		id = combinationProduct.getId();
		cpName = combinationProduct.getCpName();
		cpDesc = combinationProduct.getCpDesc();
		gmtCreate = combinationProduct.getGmtCreate();// 创建时间
		gmtModify = combinationProduct.getGmtModify();
		cpPrice = combinationProduct.getCpPrice();
		cpStatus = combinationProduct.getCpStatus();
		product1Id = combinationProduct.getProduct1Id();
		product1Name = combinationProduct.getProduct1Name();
		product1Price = combinationProduct.getProduct1Price();
		product1RealPrice = combinationProduct.getProduct1RealPrice();
		product1PicPath = combinationProduct.getProduct1PicPath();
		product2Id = combinationProduct.getProduct2Id();
		product2Name = combinationProduct.getProduct2Name();
		product2Price = combinationProduct.getProduct2Price();
		product2RealPrice = combinationProduct.getProduct2RealPrice();
		product2PicPath = combinationProduct.getProduct2PicPath();
		product3Id = combinationProduct.getProduct3Id();
		 product3Name = combinationProduct.getProduct3Name();
		 product3Price = combinationProduct.getProduct3Price();
		 product3RealPrice = combinationProduct.getProduct3RealPrice();
		product3PicPath = combinationProduct.getProduct3PicPath();
		product4Id = combinationProduct.getProduct4Id();
		product4Name = combinationProduct.getProduct4Name();
		 product4Price = combinationProduct.getProduct4Price();
		product4RealPrice = combinationProduct.getProduct4RealPrice();
		product4PicPath = combinationProduct.getProduct4PicPath();
		product5Id = combinationProduct.getProduct5Id();
		product5Name = combinationProduct.getProduct5Name();
		 product5Price = combinationProduct.getProduct5Price();
		 product5RealPrice = combinationProduct.getProduct5RealPrice();
		product5PicPath = combinationProduct.getProduct5PicPath();
		enterpriseId = combinationProduct.getEnterpriseId();
		cpPicPath = combinationProduct.getCpPicPath();
		product1Num = combinationProduct.getProduct1Num();
		product2Num = combinationProduct.getProduct2Num();
		product3Num = combinationProduct.getProduct3Num();
		product4Num = combinationProduct.getProduct4Num();
		product5Num = combinationProduct.getProduct5Num();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	public String getCpDesc() {
		return cpDesc;
	}
	public void setCpDesc(String cpDesc) {
		this.cpDesc = cpDesc;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModify() {
		return gmtModify;
	}
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}
	public BigDecimal getCpPrice() {
		return cpPrice;
	}
	public void setCpPrice(BigDecimal cpPrice) {
		this.cpPrice = cpPrice;
	}
	public Integer getCpStatus() {
		return cpStatus;
	}
	public void setCpStatus(Integer cpStatus) {
		this.cpStatus = cpStatus;
	}
	public Long getProduct1Id() {
		return product1Id;
	}
	public void setProduct1Id(Long product1Id) {
		this.product1Id = product1Id;
	}
	public String getProduct1Name() {
		return product1Name;
	}
	public void setProduct1Name(String product1Name) {
		this.product1Name = product1Name;
	}
	public BigDecimal getProduct1Price() {
		return product1Price;
	}
	public void setProduct1Price(BigDecimal product1Price) {
		this.product1Price = product1Price;
	}
	public BigDecimal getProduct1RealPrice() {
		return product1RealPrice;
	}
	public void setProduct1RealPrice(BigDecimal product1RealPrice) {
		this.product1RealPrice = product1RealPrice;
	}
	public String getProduct1PicPath() {
		return product1PicPath;
	}
	public void setProduct1PicPath(String product1PicPath) {
		this.product1PicPath = product1PicPath;
	}
	public Long getProduct2Id() {
		return product2Id;
	}
	public void setProduct2Id(Long product2Id) {
		this.product2Id = product2Id;
	}
	public String getProduct2Name() {
		return product2Name;
	}
	public void setProduct2Name(String product2Name) {
		this.product2Name = product2Name;
	}
	public BigDecimal getProduct2Price() {
		return product2Price;
	}
	public void setProduct2Price(BigDecimal product2Price) {
		this.product2Price = product2Price;
	}
	public BigDecimal getProduct2RealPrice() {
		return product2RealPrice;
	}
	public void setProduct2RealPrice(BigDecimal product2RealPrice) {
		this.product2RealPrice = product2RealPrice;
	}
	public String getProduct2PicPath() {
		return product2PicPath;
	}
	public void setProduct2PicPath(String product2PicPath) {
		this.product2PicPath = product2PicPath;
	}
	public Long getProduct3Id() {
		return product3Id;
	}
	public void setProduct3Id(Long product3Id) {
		this.product3Id = product3Id;
	}
	public String getProduct3Name() {
		return product3Name;
	}
	public void setProduct3Name(String product3Name) {
		this.product3Name = product3Name;
	}
	public BigDecimal getProduct3Price() {
		return product3Price;
	}
	public void setProduct3Price(BigDecimal product3Price) {
		this.product3Price = product3Price;
	}
	public BigDecimal getProduct3RealPrice() {
		return product3RealPrice;
	}
	public void setProduct3RealPrice(BigDecimal product3RealPrice) {
		this.product3RealPrice = product3RealPrice;
	}
	public String getProduct3PicPath() {
		return product3PicPath;
	}
	public void setProduct3PicPath(String product3PicPath) {
		this.product3PicPath = product3PicPath;
	}
	public Long getProduct4Id() {
		return product4Id;
	}
	public void setProduct4Id(Long product4Id) {
		this.product4Id = product4Id;
	}
	public String getProduct4Name() {
		return product4Name;
	}
	public void setProduct4Name(String product4Name) {
		this.product4Name = product4Name;
	}
	public BigDecimal getProduct4Price() {
		return product4Price;
	}
	public void setProduct4Price(BigDecimal product4Price) {
		this.product4Price = product4Price;
	}
	public BigDecimal getProduct4RealPrice() {
		return product4RealPrice;
	}
	public void setProduct4RealPrice(BigDecimal product4RealPrice) {
		this.product4RealPrice = product4RealPrice;
	}
	public String getProduct4PicPath() {
		return product4PicPath;
	}
	public void setProduct4PicPath(String product4PicPath) {
		this.product4PicPath = product4PicPath;
	}
	public Long getProduct5Id() {
		return product5Id;
	}
	public void setProduct5Id(Long product5Id) {
		this.product5Id = product5Id;
	}
	public String getProduct5Name() {
		return product5Name;
	}
	public void setProduct5Name(String product5Name) {
		this.product5Name = product5Name;
	}
	public BigDecimal getProduct5Price() {
		return product5Price;
	}
	public void setProduct5Price(BigDecimal product5Price) {
		this.product5Price = product5Price;
	}
	public BigDecimal getProduct5RealPrice() {
		return product5RealPrice;
	}
	public void setProduct5RealPrice(BigDecimal product5RealPrice) {
		this.product5RealPrice = product5RealPrice;
	}
	public String getProduct5PicPath() {
		return product5PicPath;
	}
	public void setProduct5PicPath(String product5PicPath) {
		this.product5PicPath = product5PicPath;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getCpPicPath() {
		return cpPicPath;
	}
	public void setCpPicPath(String cpPicPath) {
		this.cpPicPath = cpPicPath;
	}

	public List<ProductSku> getProduct1Skus() {
		return product1Skus;
	}

	public void setProduct1Skus(List<ProductSku> product1Skus) {
		this.product1Skus = product1Skus;
	}

	public List<ProductSku> getProduct2Skus() {
		return product2Skus;
	}

	public void setProduct2Skus(List<ProductSku> product2Skus) {
		this.product2Skus = product2Skus;
	}

	public List<ProductSku> getProduct3Skus() {
		return product3Skus;
	}

	public void setProduct3Skus(List<ProductSku> product3Skus) {
		this.product3Skus = product3Skus;
	}

	public List<ProductSku> getProduct4Skus() {
		return product4Skus;
	}

	public void setProduct4Skus(List<ProductSku> product4Skus) {
		this.product4Skus = product4Skus;
	}

	public List<ProductSku> getProduct5Skus() {
		return product5Skus;
	}

	public void setProduct5Skus(List<ProductSku> product5Skus) {
		this.product5Skus = product5Skus;
	}

	public Integer getProduct1Num() {
		return product1Num;
	}

	public void setProduct1Num(Integer product1Num) {
		this.product1Num = product1Num;
	}

	public Integer getProduct2Num() {
		return product2Num;
	}

	public void setProduct2Num(Integer product2Num) {
		this.product2Num = product2Num;
	}

	public Integer getProduct3Num() {
		return product3Num;
	}

	public void setProduct3Num(Integer product3Num) {
		this.product3Num = product3Num;
	}

	public Integer getProduct4Num() {
		return product4Num;
	}

	public void setProduct4Num(Integer product4Num) {
		this.product4Num = product4Num;
	}

	public Integer getProduct5Num() {
		return product5Num;
	}

	public void setProduct5Num(Integer product5Num) {
		this.product5Num = product5Num;
	}
}
