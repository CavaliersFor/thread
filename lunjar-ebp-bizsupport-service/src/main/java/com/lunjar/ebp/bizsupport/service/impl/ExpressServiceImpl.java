package com.lunjar.ebp.bizsupport.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunjar.ebp.bizsupport.dto.ExpressInfo;
import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.mappers.ExpressMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductSkuMapper;
import com.lunjar.ebp.bizsupport.model.Express;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.query.ExpressQuery;
import com.lunjar.ebp.bizsupport.service.ExpressService;
import com.lunjar.ebp.bizsupport.utils.GenerateUtil;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;

@Service(value = "expressService")
public class ExpressServiceImpl implements ExpressService {

	private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ExpressServiceImpl.class);

	@Autowired
	private ExpressMapper expressMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductSkuMapper productSkuMapper;

	@Override
	public Express load(Long id) {
		return expressMapper.load(id);
	}

	@Override
	public Long add(Express t) {
		expressMapper.insert(t);
		return t.getId();
	}

	@Override
	public int update(Express t) {
		return expressMapper.update(t);
	}

	@Override
	public void delete(Long id) {
		expressMapper.delete(id);
	}

	@Override
	public List<Express> queryList(ExpressQuery q) {
		return expressMapper.queryList(q);
	}

	@Override
	public int queryCount(ExpressQuery q) {
		return expressMapper.queryCount(q);
	}

	@Override
	public int deleteByIdAndEnterpriseId(Express express) {
		return expressMapper.deleteByIdAndEnterpriseId(express);
	}

	@Override
	public BigDecimal getPostFee(Long enterpriseId, String city, Integer num, Long pId, Integer type) throws ServiceException {

		if (enterpriseId == null) {
			logger.warn(BizSupportCode._2001002.getMsg());
			throw new ServiceException(BizSupportCode._2001002.getCode(), BizSupportCode._2001002.getMsg());
		}
		if (num < 1) {
			logger.warn(BizSupportCode._3000018.getMsg());
			throw new ServiceException(BizSupportCode._3000018.getCode(), BizSupportCode._3000018.getMsg());
		}
		Express express = expressMapper.getByEntId(enterpriseId);
		if (express == null) {
			logger.warn(BizSupportCode._2001017.getMsg());
			throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
		}
		BigDecimal postFee = new BigDecimal("0");
		String price = express.getEcPrice();
		if (StringUtils.isNoneBlank(price)) {
			Double v = null;
			if (express.getValuation() != 1) {
				if (type == 1) {//产品id
					Product product = productMapper.load(pId);
					if (product != null) {
						if (express.getValuation() == 2) {
							v = product.getWeight().doubleValue();
						}else {
							v = product.getVolume().doubleValue();
						}
					}
				}else if (type == 2) {
					ProductSku productSku = productSkuMapper.load(pId);
					if (productSku != null) {
						if (express.getValuation() == 2) {
							v = productSku.getWeight().doubleValue();
						}else {
							v = productSku.getVolume().doubleValue();
						}
					}
				}
			}
			postFee = GenerateUtil.getPostFee(price, city, num, v);
		}
		return postFee;
	}

	@Override
	public PageResult<Express> querListPage(ExpressQuery query) {
		
		List<Express> data = this.queryList(query);
		List<ExpressInfo> infos = new ArrayList<>();
		if(data!=null && data.size()>0){
			for(Express e:data){
				if(e.getEcPrice()!=null && !"".equals(e.getEcPrice())){
					//默认:5,2;中山市,东莞市,顺德市:5,3;长沙市,厦门市:10,0;广州市,深圳市:5,1;北京市:13,1
					//中山市,东莞市,顺德市:5,3;   这三个城市第一个计费单位收费位5，后面每价格计费单位会加3块钱
					String ecPrice = e.getEcPrice();
					if(ecPrice.contains(";")){
						String [] arrayEcPrice = ecPrice.split(";");
						for(String str:arrayEcPrice){
							
							String[] ecpriceOne = str.split(":");
							String address = ecpriceOne[0];
							Integer firstNum = 1;//首件
							Integer addNum = 1;//续件
							
							BigDecimal firstPrice=new BigDecimal(ecpriceOne[1].split(",")[0]);//首件运费
							BigDecimal addPrice = new BigDecimal(ecpriceOne[1].split(",")[1]);//续件费用
							ExpressInfo info = new ExpressInfo();
							info.setAddNum(addNum);
							info.setAddresss(address);
							info.setFirstNum(firstNum);
							info.setFirstPrice(firstPrice);
							info.setAddPrice(addPrice);
							infos.add(info);
						}
					}else if(ecPrice.contains(":")){
						//默认:5,2
						String[] ecpriceOne = ecPrice.split(":");
						String address = ecpriceOne[0];
						Integer firstNum = 1;//首件
						Integer addNum = 1;//续件
						
						BigDecimal firstPrice=new BigDecimal(ecpriceOne[1].split(",")[0]);//首件运费
						BigDecimal addPrice = new BigDecimal(ecpriceOne[1].split(",")[1]);//续件费用
						ExpressInfo info = new ExpressInfo();
						info.setAddNum(addNum);
						info.setAddresss(address);
						info.setFirstNum(firstNum);
						info.setFirstPrice(firstPrice);
						info.setAddPrice(addPrice);
						infos.add(info);
					}
				}
				if(infos.size()>0){
					e.setListInfo(infos);
				}
			}
		}
		int count = this.queryCount(query);
		return PageResult.create(query, data, count);
	}

}
