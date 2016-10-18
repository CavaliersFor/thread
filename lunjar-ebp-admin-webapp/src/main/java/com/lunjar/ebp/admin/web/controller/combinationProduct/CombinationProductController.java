package com.lunjar.ebp.admin.web.controller.combinationProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunjar.ebp.admin.domain.dto.CombinationProductDto;
import com.lunjar.ebp.admin.domain.model.EnterpriseAgent;
import com.lunjar.ebp.admin.web.session.EnterpriseAgentSession;
import com.lunjar.ebp.bizsupport.dto.ProductDto;
import com.lunjar.ebp.bizsupport.model.CombinationProduct;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.query.CombinationProductQuery;
import com.lunjar.ebp.bizsupport.query.ProductQuery;
import com.lunjar.ebp.bizsupport.service.CombinationProductService;
import com.lunjar.ebp.bizsupport.service.ProductService;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.web.controller.ControllerSupport;
import com.lunjar.products.core.webapi.LunjarApiResponse;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

@RequestMapping(value = "combinationProduct")
@Controller
public class CombinationProductController extends ControllerSupport {
	
	
	@Resource(name = "enterpriseAgentSession")
	private EnterpriseAgentSession enterpriseAgentSession;
	
	/**
	 * 获取商家id
	 * 
	 * @return
	 */
	public Long getId(HttpServletRequest request) {
		// 登陆信息
		EnterpriseAgent agent = enterpriseAgentSession.get(request);
		return agent.getId();
	}

	@Autowired
	private CombinationProductService combinationProductService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "add")
	public String add(Long id, Model model,HttpServletRequest request) throws ServiceException {
		if (id != null) {
			CombinationProductQuery query = new CombinationProductQuery();
			query.setEnterpriseId(getId(request));
			query.setIdArray(id);

			List<CombinationProduct> list = combinationProductService.queryList(query);
			if (list != null && list.size() == 1) {
				CombinationProduct m = list.get(0);

				List<CombinationProductDto> listDto = new ArrayList<>();

				BigDecimal total = new BigDecimal("0");

				if (m != null && m.getProduct1Id() != null) {
					CombinationProductDto dto = new CombinationProductDto();
					dto.setProductId(m.getProduct1Id());
					dto.setProductName(m.getProduct1Name());
					dto.setProductNum(m.getProduct1Num());
					dto.setProductPicPath(m.getProduct1PicPath());
					dto.setProductPrice(m.getProduct1Price());
					dto.setProductRealPrice(m.getProduct1RealPrice());
					dto.setProductAbsPicPath(m.getProduct1AbsPicPath());
					
					Product p = productService.load(m.getProduct1Id());
					if(p!=null){
						dto.setStatus(p.getStatus());
					}else{
						dto.setStatus(3);
					}
					
					listDto.add(dto);
					total = total.add(m.getProduct1RealPrice());
				}
				if (m != null && m.getProduct2Id() != null) {
					CombinationProductDto dto = new CombinationProductDto();
					dto.setProductId(m.getProduct2Id());
					dto.setProductName(m.getProduct2Name());
					dto.setProductNum(m.getProduct2Num());
					dto.setProductPicPath(m.getProduct2PicPath());
					dto.setProductPrice(m.getProduct2Price());
					dto.setProductRealPrice(m.getProduct2RealPrice());
					dto.setProductAbsPicPath(m.getProduct2AbsPicPath());
					
					Product p = productService.load(m.getProduct2Id());
					if(p!=null){
						dto.setStatus(p.getStatus());
					}else{
						dto.setStatus(3);
					}
					
					listDto.add(dto);
					total = total.add(m.getProduct2RealPrice());
				}

				if (m != null && m.getProduct3Id() != null) {
					CombinationProductDto dto = new CombinationProductDto();
					dto.setProductId(m.getProduct3Id());
					dto.setProductName(m.getProduct3Name());
					dto.setProductNum(m.getProduct3Num());
					dto.setProductPicPath(m.getProduct3PicPath());
					dto.setProductPrice(m.getProduct3Price());
					dto.setProductRealPrice(m.getProduct3RealPrice());
					dto.setProductAbsPicPath(m.getProduct3AbsPicPath());
					
					Product p = productService.load(m.getProduct3Id());
					if(p!=null){
						dto.setStatus(p.getStatus());
					}else{
						dto.setStatus(3);
					}
					
					listDto.add(dto);
					total = total.add(m.getProduct3RealPrice());
				}

				if (m != null && m.getProduct4Id() != null) {
					CombinationProductDto dto = new CombinationProductDto();
					dto.setProductId(m.getProduct4Id());
					dto.setProductName(m.getProduct4Name());
					dto.setProductNum(m.getProduct4Num());
					dto.setProductPicPath(m.getProduct4PicPath());
					dto.setProductPrice(m.getProduct4Price());
					dto.setProductRealPrice(m.getProduct4RealPrice());
					dto.setProductAbsPicPath(m.getProduct4AbsPicPath());
					
					Product p = productService.load(m.getProduct4Id());
					if(p!=null){
						dto.setStatus(p.getStatus());
					}else{
						dto.setStatus(3);
					}
					
					listDto.add(dto);
					total = total.add(m.getProduct4RealPrice());
				}

				if (m != null && m.getProduct5Id() != null) {
					CombinationProductDto dto = new CombinationProductDto();
					dto.setProductId(m.getProduct5Id());
					dto.setProductName(m.getProduct5Name());
					dto.setProductNum(m.getProduct5Num());
					dto.setProductPicPath(m.getProduct5PicPath());
					dto.setProductPrice(m.getProduct5Price());
					dto.setProductRealPrice(m.getProduct5RealPrice());
					dto.setProductAbsPicPath(m.getProduct5AbsPicPath());
					
					Product p = productService.load(m.getProduct5Id());
					if(p!=null){
						dto.setStatus(p.getStatus());
					}else{
						dto.setStatus(3);
					}
					
					listDto.add(dto);
					total = total.add(m.getProduct5RealPrice());
				}
				StringBuilder sb = new StringBuilder();
				if(listDto!=null && listDto.size()>0){
					for(int i=0;i<listDto.size();i++){
						if((i+1)==listDto.size()){
							sb.append(listDto.get(i).getProductId());
						}else{
							sb.append(listDto.get(i).getProductId()+",");
						}
					}
				}
				model.addAttribute("combinPids", sb.toString());
				
				model.addAttribute("total", total);
				model.addAttribute("ds", listDto);
				model.addAttribute("m", list.get(0));
			} else {
				throw new ServiceException("组合商品id错误!");
			}
		}
		model.addAttribute("title", "组合商品添加");
		return "/pages/addCombinationProduct";
	}

	@RequestMapping(value = "list")
	public String list(CombinationProductQuery query, Model model,HttpServletRequest request) {
		query.setPageSize(10);
		query.setEnterpriseId(getId(request));
		query.setSort("GMT_CREATE desc");
		PageResult<CombinationProduct> list = combinationProductService.queryListForPage(query);
		model.addAttribute("list", list);
		model.addAttribute("title", "组合商品列表");
		return "/pages/combinationProductlist";
	}

	/**
	 * 保存组合商品
	 * 
	 * @param c
	 * @return
	 * @throws ServiceException 
	 */
	@ResponseBody
	@RequestMapping(value = "save")
	public LunjarApiResponse save(CombinationProduct c, Long[] productId,HttpServletRequest request) throws ServiceException {
		
		if(c.getCpName()==null || "".equals(c.getCpName().trim())){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "商品组合名称为空!");
		}
		if(c.getCpDesc()==null || "".equals(c.getCpDesc().trim())){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "商品组合描述为空!");
		}
		if(c.getCpPrice()==null){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "组合商品销售价格为空!");
		}
		if(c.getIsFreePost()==null){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "物流设置为空!");
		}
		
		if(productId!=null && productId.length>0){
			for(int i=0;i<productId.length;i++){
				ProductQuery query = new ProductQuery();
				query.setIdArray(productId[i]);
				query.setEnterpriseId(getId(request));
				List<Product> ps = productService.queryList(query);
				
				if(ps==null || ps.size()!=1){
					return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "组合商品包含的商品有误!");
				}
				
				if(ps.get(0).getStatus()!=1){
					return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "组合商品中包含未上架的商品!");
				}
				String productName = ps.get(0).getName();
				BigDecimal productPrice = ps.get(0).getPrice();
				BigDecimal productRealPrice = ps.get(0).getSalePrice();
				String productPicPath = ps.get(0).getRelativePath();
				Integer productNum = 1;//数量只有一个
				if((i+1)==1){
					//商品1
					c.setProduct1Id(productId[i]);
					c.setProduct1Name(productName);
					c.setProduct1Price(productPrice);
					c.setProduct1RealPrice(productRealPrice);
					c.setProduct1PicPath(productPicPath);
					c.setProduct1Num(productNum);
				}
				if((i+1)==2){
					//商品1
					c.setProduct2Id(productId[i]);
					c.setProduct2Name(productName);
					c.setProduct2Price(productPrice);
					c.setProduct2RealPrice(productRealPrice);
					c.setProduct2PicPath(productPicPath);
					c.setProduct2Num(productNum);
				}
				if((i+1)==3){
					//商品1
					c.setProduct3Id(productId[i]);
					c.setProduct3Name(productName);
					c.setProduct3Price(productPrice);
					c.setProduct3RealPrice(productRealPrice);
					c.setProduct3PicPath(productPicPath);
					c.setProduct3Num(productNum);
				}
				if((i+1)==4){
					//商品1
					c.setProduct4Id(productId[i]);
					c.setProduct4Name(productName);
					c.setProduct4Price(productPrice);
					c.setProduct4RealPrice(productRealPrice);
					c.setProduct4PicPath(productPicPath);
					c.setProduct4Num(productNum);
				}
				if((i+1)==5){
					//商品1
					c.setProduct5Id(productId[i]);
					c.setProduct5Name(productName);
					c.setProduct5Price(productPrice);
					c.setProduct5RealPrice(productRealPrice);
					c.setProduct5PicPath(productPicPath);
					c.setProduct5Num(productNum);
				}
			}
		}else{
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据有误!");
		}
		
		if(c.getProduct1Id()==null){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "组合商品至少添加两个商品!");
		}
		
		if(c.getProduct2Id()==null){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "组合商品至少添加两个商品!");
		}
		
		c.setCpStatus(1);
		c.setGmtModify(new Date());
		c.setCpPicPath(c.getProduct1PicPath());
		if(c.getId()!=null){
			CombinationProductQuery query = new CombinationProductQuery();
			query.setEnterpriseId(getId(request));
			query.setIdArray(c.getId());
			int count  = combinationProductService.queryCount(query);
			
			if(count!=1){
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "数据有问题!");
			}
			combinationProductService.updateNoDecide(c);
		}else{
			
			c.setGmtCreate(new Date());
			c.setEnterpriseId(getId(request));
			combinationProductService.add(c);
			
		}
		
		return LunjarApiResponse.create();
	}
	
	/**
	 * 查询商品列表
	 * @return
	 */
	@RequestMapping(value = "listpro")
	public String listpro(Model model,ProductQuery query,HttpServletRequest request){
		
		PageResult<ProductDto> list = productList(query,request);
		model.addAttribute("ps", list);
		return "/pages/comProds";
	}
	
	private PageResult<ProductDto> productList(ProductQuery query,HttpServletRequest request){
		query.setSort("GMT_CREATE desc");
		query.setEnterpriseId(getId(request));
		query.setPageSize(5);
		query.setStatus(1);
		PageResult<ProductDto> list = productService.queryListAndSku(query);
		return list;
	}
	
	/**
	 * 查询商品列表Json返回
	 * @return
	 */
	@RequestMapping(value = "listproJson")
	@ResponseBody
	public LunjarApiResponse listproJson(ProductQuery query,String productName,HttpServletRequest request){
		if (productName != null && !"".equals(productName.trim())) {
			Map<String, Object> properties = new HashMap<>();
			properties.put("productName", productName);
			query.setProperties(properties);
		}
		PageResult<ProductDto> list = productList(query,request);;
		return LunjarApiResponse.create(list);
	}
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public LunjarApiResponse delete(Long id,HttpServletRequest request){
		
		if(id==null){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据有问题!");
		}
		
		CombinationProductQuery query = new CombinationProductQuery();
		query.setEnterpriseId(getId(request));
		query.setIdArray(id);
		int count  = combinationProductService.queryCount(query);
		
		if(count!=1){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "数据有问题!");
		}
		
		combinationProductService.delete(id);
		
		return LunjarApiResponse.create();
	}
	
	/**
	 * 显示失效原因页面
	 * @return
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "showCause")
	public String showCause(Long id,Model model,HttpServletRequest request) throws ServiceException{
		
		if(id==null){
			throw new ServiceException("请求数据错误");
		}
		
		CombinationProductQuery query = new CombinationProductQuery();
		query.setEnterpriseId(getId(request));
		query.setIdArray(id);
		List<CombinationProduct>  list = combinationProductService.queryList(query);
		
		if(list==null || list.size()!=1){
			throw new ServiceException("组合商品数据错误");
		}
		
		List<CombinationProductDto> listDto = new ArrayList<>();
		
		CombinationProduct c = list.get(0);
		
		if (c != null && c.getProduct1Id() != null) {
			CombinationProductDto dto = new CombinationProductDto();
			Product p = productService.load(c.getProduct1Id());
			dto.setProductName(c.getProduct1Name());
			if(p!=null){
				dto.setStatus(p.getStatus());
			}else{
				dto.setStatus(3);
			}
			listDto.add(dto);
		}
		if (c != null && c.getProduct2Id() != null) {
			Product p = productService.load(c.getProduct2Id());
			CombinationProductDto dto = new CombinationProductDto();
			dto.setProductName(c.getProduct2Name());
			if(p!=null){
				dto.setStatus(p.getStatus());
			}else{
				dto.setStatus(3);
			}
			listDto.add(dto);
		}

		if (c != null && c.getProduct3Id() != null) {
			CombinationProductDto dto = new CombinationProductDto();
			dto.setProductName(c.getProduct3Name());
			
			Product p = productService.load(c.getProduct3Id());
			if(p!=null){
				dto.setStatus(p.getStatus());
			}else{
				dto.setStatus(3);
			}
			listDto.add(dto);
		}

		if (c != null && c.getProduct4Id() != null) {
			CombinationProductDto dto = new CombinationProductDto();
			dto.setProductName(c.getProduct4Name());
			
			Product p = productService.load(c.getProduct4Id());
			if(p!=null){
				dto.setStatus(p.getStatus());
			}else{
				dto.setStatus(3);
			}
			listDto.add(dto);
		}

		if (c != null && c.getProduct5Id() != null) {
			CombinationProductDto dto = new CombinationProductDto();
			dto.setProductName(c.getProduct5Name());
			
			Product p = productService.load(c.getProduct5Id());
			if(p!=null){
				dto.setStatus(p.getStatus());
			}else{
				dto.setStatus(3);
			}
			listDto.add(dto);
		}
		
		model.addAttribute("ds", listDto);
		
		model.addAttribute("c", list);
		
		return "/pages/showCombinCause";
	}
	
}
