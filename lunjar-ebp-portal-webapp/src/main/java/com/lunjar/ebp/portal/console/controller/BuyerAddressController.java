package com.lunjar.ebp.portal.console.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunjar.ebp.bizsupport.model.BuyerAddress;
import com.lunjar.ebp.bizsupport.model.Region;
import com.lunjar.ebp.bizsupport.query.BuyerAddressQuery;
import com.lunjar.ebp.bizsupport.service.BuyerAddressService;
import com.lunjar.ebp.bizsupport.service.RegionService;
import com.lunjar.ebp.portal.console.model.PortalAgent;
import com.lunjar.ebp.portal.console.session.PortalAgentSession;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.webapi.LunjarApiResponse;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

/**
 * 地址控制器
 * 
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value = "buyAddress")
public class BuyerAddressController extends BaseController {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BuyerAddressController.class);

	@Autowired
    private PortalAgentSession portalAgentSession;
	/**
	 * 获取买方id
	 * 
	 * @return
	 */
	// TODO 获取买方id
//	public Long getBuyerId() {
//		return new Long(12313);
//	}

	/**
	 * 获取商铺id
	 * 
	 * @return
	 */
	// TODO 获取商铺id
//	public Long getShopId() {
//		return new Long(1231);
//	}

	@Autowired

	private BuyerAddressService buyerAddressService;
	@Autowired
	private RegionService regionService;

	@RequestMapping(value = "addPage/{shopId}")
	public String addAddressPage(String id, Model model,@PathVariable("shopId") String shopId,HttpServletRequest request) {
		PortalAgent agent = portalAgentSession.get(request);
		logger.debug("------visit BuyAddressController.addAddressPage start----------");
		if (id == null || "".equals(id)) {
			logger.debug("------id is null 添加地址----------");
			// 查询省
			model.addAttribute("title", "添加地址");
		}else{
			BuyerAddress buyerAddress = buyerAddressService.load(new Long(id));
			
			if(buyerAddress!=null && buyerAddress.getBuyerId().equals(agent.getBuyerId())){
				model.addAttribute("buyerAddress", buyerAddress);
			}
			model.addAttribute("title", "修改地址");
		}
		List<Region> listRegion = regionService.getAllProvince();
		model.addAttribute("regions", listRegion);
		model.addAttribute("shopId", shopId);
		logger.debug("------visit BuyAddressController.addAddressPage end----------");
		return "pages/editaddress";
	}
	/**
	 * 通过省的id获取市
	 * @return
	 * @throws ServiceException 
	 */
	@ResponseBody
	@RequestMapping(value="getCity/{shopId}")
	public LunjarApiResponse getCity(String code) throws ServiceException{
		List<Region> listRegion = regionService.getByParentCode(code);
		return LunjarApiResponse.create(listRegion);
	}
	
	/**
	 * 通过市的id获取地区
	 * @return
	 * @throws ServiceException 
	 */
	@ResponseBody
	@RequestMapping(value="getRegion/{shopId}")
	public LunjarApiResponse getRegion(String code) throws ServiceException{
		List<Region> listRegion = regionService.getByParentCode(code);
		return LunjarApiResponse.create(listRegion);
	}
	
	/**
	 * 保存地址
	 * 
	 * @param buyerAddress
	 * @return
	 * @throws ServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "add/{shopId}")
	public LunjarApiResponse add(BuyerAddress buyerAddress, HttpServletRequest request) throws ServiceException {
		logger.debug("------visit BuyAddressController.add start----------");
		PortalAgent agent = portalAgentSession.get(request);
		if (buyerAddress == null) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "地址信息为空");
		}
		if (buyerAddress.getProvince() == null || "".equals(buyerAddress.getProvince().trim())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "省份不能为空");
		}
		if (buyerAddress.getCity() == null || "".equals(buyerAddress.getCity().trim())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "市不能为空");
		}
		/*if (buyerAddress.getRegion() == null || "".equals(buyerAddress.getRegion().trim())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "区县不能为空");
		}*/
		if (buyerAddress.getBuyerName() == null || "".equals(buyerAddress.getBuyerName().trim())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "收货人姓名不能为空");
		}
		if (buyerAddress.getBuyerPhone() == null || "".equals(buyerAddress.getBuyerPhone().trim())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "收货人手机号不能为空");
		}
		if (buyerAddress.getBuyerAddress() == null || "".equals(buyerAddress.getBuyerAddress().trim())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "收货人详细地址");
		}
		//如果不是默认
		if (buyerAddress.getIsDefault()== null) {
			buyerAddress.setIsDefault(2);
		}
//		buyerAddress.setBuyerId(getBuyerId());
		buyerAddress.setBuyerId(agent.getBuyerId());
		// 设置状态
		buyerAddress.setStatus(1);
		buyerAddress.setGmtCreate(new Date());
		if(buyerAddress.getId()!=null){
			int num = buyerAddressService.update(buyerAddress);
		}else{
			Long id = buyerAddressService.add(buyerAddress);
		}
		logger.debug("------visit BuyAddressController.add end----------");
		return LunjarApiResponse.create();
	}
	/**
	 * 地址列表页面
	 * @return
	 */
	@RequestMapping(value="list/{shopId}")
	public String buyerAddressList(Model model, HttpServletRequest request,@PathVariable("shopId") String shopId){
		PortalAgent agent = portalAgentSession.get(request);
		BuyerAddressQuery buyerAddressQuery = new BuyerAddressQuery();
		buyerAddressQuery.setPageSize(10);
		buyerAddressQuery.setBuyerId(agent.getBuyerId());
		//状态1：正常2：停止
		buyerAddressQuery.setStatus(1);
		buyerAddressQuery.setSort("GMT_CREATE desc");
		List<BuyerAddress>  list = buyerAddressService.queryList(buyerAddressQuery);
		model.addAttribute("addresss", list);
		
		model.addAttribute("title", "我的地址");
		
		model.addAttribute("shopId", shopId);
		return "pages/address";
	}
	/**
	 * 地址列表通过json返回
	 */
	@RequestMapping(value="listJson/{shopId}")
	@ResponseBody
	public LunjarApiResponse buyerAddressListJson(HttpServletRequest request){
		PortalAgent agent = portalAgentSession.get(request);
		BuyerAddressQuery buyerAddressQuery = new BuyerAddressQuery();
		buyerAddressQuery.setPageSize(10);
		buyerAddressQuery.setBuyerId(agent.getBuyerId());
		//状态1：正常2：停止
		buyerAddressQuery.setStatus(1);
		buyerAddressQuery.setSort("GMT_CREATE desc");
		List<BuyerAddress>  list = buyerAddressService.queryList(buyerAddressQuery);
		return LunjarApiResponse.create(list);
	}
	/**
	 * 删除地址
	 */
	@RequestMapping(value="delete/{shopId}")
	@ResponseBody
	public LunjarApiResponse deleteBuyerAddress(BuyerAddress buyerAddress, HttpServletRequest request){
		logger.debug("-----------BuyerAddressController deleteBuyerAddress start------------");
		PortalAgent agent = portalAgentSession.get(request);
		if(buyerAddress.getId()==null){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(),"地址编号为空");
		}
		BuyerAddressQuery buyerAddressQuery = new BuyerAddressQuery();
		buyerAddressQuery.setBuyerId(agent.getBuyerId());
		buyerAddressQuery.setIdArray(buyerAddress.getId());
		List<BuyerAddress>  list = buyerAddressService.queryList(buyerAddressQuery);
		if(list==null || list.size()==0){
			//没有查询到该地址
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(),"没有查询到地址");
		}
		BuyerAddress b = new BuyerAddress();
		b.setId(buyerAddress.getId());
		b.setStatus(2);
		int num = buyerAddressService.update(b);
		
		logger.debug("-----------BuyerAddressController deleteBuyerAddress end update num{}------------",num);
		
		return LunjarApiResponse.create();
	}
}
