package com.lunjar.ebp.admin.web.controller.basedata;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ancun.bps.core.region.BpsRegionService;
import com.ancun.bps.core.region.cache.RegionCacheForData;
import com.ancun.bps.core.region.cache.RegionCacheForName;
import com.ancun.bps.core.region.exception.RegionExistsException;
import com.ancun.bps.core.region.model.PubRegion;
import com.ancun.bps.core.region.query.PubRegionQuery;
import com.lunjar.ebp.admin.biz.authority.AdminAuthority;
import com.lunjar.ebp.admin.domain.enums.EnumRegionStatus;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.utils.RegionUtils;
import com.lunjar.products.core.web.annotation.QueryPage;
import com.lunjar.products.core.web.controller.ControllerSupport;

@Controller
@RequestMapping("region")
@AdminAuthority("basedata")
public class RegionController extends ControllerSupport {
	
	@Autowired
	private BpsRegionService bpsRegionService;
	@Autowired
	private RegionCacheForData regionCacheForData;
	@Autowired
	private RegionCacheForName regionCacheForName;

	@RequestMapping("/list")
	@AdminAuthority("bd_region_list")
	public String index(Model model, @QueryPage PubRegionQuery query, HttpServletRequest request) {
		//将地区状态输出到前段
		//前段 var statusText = App.getDataDictByGroupAndValue('regionStatus',newStatus)
		controllerUtils.renderDatadict(model, "regionStatus");
		if (StringUtils.isNotBlank(query.getParentCode())) {
			query.setParentCodePrefix(RegionUtils.getParentCodePrefix(query.getParentCode()));
		}
		PageResult<PubRegion> page = bpsRegionService.getListPage(query);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "basedata/region-list";
	}

	@RequestMapping("/getdetail")
	@ResponseBody
	@AdminAuthority({"bd_region_add","bd_region_edit"})
	public PubRegion getdetail(String code) {
		System.out.println("接口调用！！！");
		return bpsRegionService.getByCode(code);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	@AdminAuthority("bd_region_edit")
	public void update(PubRegion region, HttpServletRequest request) {
		try{
			String oldParentCode=request.getParameter("oldParentCode");
			bpsRegionService.update(region, oldParentCode);
			regionCacheForName.remove(region.getCode());
			regionCacheForData.remove((Serializable)1);
			regionCacheForData.remove((Serializable)region.getParentCode());
		}catch(Exception e){
			System.out.print(e.getStackTrace());
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@AdminAuthority("bd_region_add")
	public void add(PubRegion region) throws RegionExistsException {
		region.setStatus(EnumRegionStatus.NORMAL.getValue());
		bpsRegionService.add(region);
		regionCacheForData.remove((Serializable)1);
		regionCacheForData.remove((Serializable)region.getParentCode());
	}

	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	@ResponseBody
	@AdminAuthority("bd_region_enableAndDisable")
	public void updateStatus(PubRegion region) {
		bpsRegionService.updateStatus(region.getCode(), region.getParentCode(), region.getStatus());
	}	
}