package com.lunjar.ebp.admin.web.controller.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunjar.ebp.bizsupport.model.Region;
import com.lunjar.ebp.bizsupport.service.RegionService;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.web.controller.ControllerSupport;
import com.lunjar.products.core.webapi.LunjarApiResponse;

@RequestMapping(value="address")
@Controller
public class AddressController extends ControllerSupport{
	
	@Autowired
	private RegionService regionService;
	
	/**
	 * 通过省的id获取市
	 * @return
	 * @throws ServiceException 
	 */
	@ResponseBody
	@RequestMapping(value="getCity")
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
	@RequestMapping(value="getRegion")
	public LunjarApiResponse getRegion(String code) throws ServiceException{
		List<Region> listRegion = regionService.getByParentCode(code);
		return LunjarApiResponse.create(listRegion);
	}
	
}
