package com.lunjar.ebp.admin.web.controller.collectPlace;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunjar.ebp.admin.domain.model.EnterpriseAgent;
import com.lunjar.ebp.admin.web.session.EnterpriseAgentSession;
import com.lunjar.ebp.bizsupport.model.CollectPlace;
import com.lunjar.ebp.bizsupport.model.Region;
import com.lunjar.ebp.bizsupport.query.CollectPlaceQuery;
import com.lunjar.ebp.bizsupport.service.CollectPlaceService;
import com.lunjar.ebp.bizsupport.service.RegionService;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.web.controller.ControllerSupport;
import com.lunjar.products.core.webapi.LunjarApiResponse;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

@RequestMapping(value = "/collectPlace")
@Controller
public class CollectPlaceController extends ControllerSupport {
	
	
	@Autowired
	private CollectPlaceService collectPlaceService;
	
	@Autowired
	private RegionService regionService;
	
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
	/**
	 * 查询所有自提点记录
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "list")
	public String list(CollectPlaceQuery query, Model model,HttpServletRequest request) {
		query.setEnterpriseId(getId(request));
		query.setPageSize(10);
		query.setSort("GMT_CREATE desc");
		PageResult<CollectPlace>  listPlace = collectPlaceService.queryListPage(query);
		
		model.addAttribute("ps", listPlace);
		model.addAttribute("title", "自提点列表");
		return "/pages/collectPlacelist";
	}
	
	/**
	 * 新增自提点页面
	 * @return
	 * @throws ServiceException 
	 */
	@RequestMapping(value="addPage")
	public String addPage(Long id,Model model,HttpServletRequest request) throws ServiceException{
		
		List<Region> listProvince = regionService.getAllProvince();
		
		if(id!=null && !id.equals(new Long(-1))){
			CollectPlaceQuery query = new CollectPlaceQuery();
			query.setEnterpriseId(getId(request));
			query.setIdArray(id);
			List<CollectPlace> listPlace = collectPlaceService.queryList(query);
			if(listPlace!=null && listPlace.size()==1){
				model.addAttribute("p", listPlace.get(0));
				
				String province = listPlace.get(0).getProvince();
				String city = listPlace.get(0).getCity();
				for(Region r: listProvince){
					if(r.getRegionName().equals(province)){
						List<Region> listCity = regionService.getByParentCode(r.getCode());
						model.addAttribute("citys", listCity);
						for(Region c:listCity){
							System.out.println(c.getRegionName());
							System.out.println(city);
							if(c.getRegionName().trim().equals(city.trim())){
								List<Region> listRegion = regionService.getByParentCode(c.getCode());
								model.addAttribute("regions", listRegion);
							}
						}
						break;
					}
				}
			}else{
				throw new ServiceException("自提点id有误");
			}
		}
		model.addAttribute("provinces", listProvince);
		return "/pages/addCollectPlace";
	}
	/**
	 * 更新自提点状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update")
	public LunjarApiResponse update(Long id,Integer status,HttpServletRequest request){
		
		if(id==null){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误!");
		}
		
		CollectPlaceQuery query = new CollectPlaceQuery();
		query.setEnterpriseId(getId(request));
		query.setIdArray(id);
		int count = collectPlaceService.queryCount(query);
		
		if(count<=0){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "自提点数据错误!");
		}
		
		CollectPlace c = new CollectPlace();
		c.setId(id);
		c.setClpStatus(status);
		collectPlaceService.update(c);
		
		return LunjarApiResponse.create();
	}
	/**
	 * 删除自提点
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delete")
	public LunjarApiResponse delete(Long id,HttpServletRequest request){
		
		if(id==null){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误!");
		}
		
		CollectPlaceQuery query = new CollectPlaceQuery();
		query.setEnterpriseId(getId(request));
		query.setIdArray(id);
		int count = collectPlaceService.queryCount(query);
		
		if(count<=0){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "自提点数据错误!");
		}
		
		collectPlaceService.delete(id);
		
		return LunjarApiResponse.create();
	}
	
	/**
	 * 保存自提点
	 * @param id
	 * @return
	 * @throws ServiceException 
	 */
	@ResponseBody
	@RequestMapping(value = "save")
	public LunjarApiResponse save(CollectPlace c,HttpServletRequest request) throws ServiceException{
		
		if(c.getClpName()==null || "".equals(c.getClpName().trim())){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "自提点名称为空!");
		}
		
		if(c.getClpTelephone()==null || "".equals(c.getClpTelephone().trim())){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "联系电话为空!");
		}
		
		if(c.getProvince()==null || "".equals(c.getProvince().trim()) || "0".equals(c.getProvince().trim())){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "省为空!");
		}
		if(c.getCity()==null || "".equals(c.getCity().trim()) || "0".equals(c.getCity().trim())){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "市为空!");
		}
/*		if(c.getRegion()==null || "".equals(c.getRegion().trim())){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "区为空!");
		}
*/		
		if(c.getClpAddress()==null || "".equals(c.getClpAddress().trim())){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "详细地址为空!");
		}
		
		if(c.getCollectTime()==null){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "取货时间为空!");
		}
		if(c.getFee()==null){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "自提费用为空!");
		}
		if(c.getMaxDepositDays()==null){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "货物存放时间为空!");
		}
		if(c.getStartTime()==null || c.getEndTime()==null || "".equals(c.getEndTime().trim()) || "".equals(c.getStartTime().trim())){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "自提时间段为空!");
		}
		
		if (c.getProvince() != null && !"".equals(c.getProvince())) {
			Region r = regionService.getByCode(c.getProvince());
			if (r != null) {
				String province = r.getRegionName();
				c.setProvince(province);
			}
		}
		if (c.getCity() != null && !"".equals(c.getCity())) {
			Region r = regionService.getByCode(c.getCity());
			if (r != null) {
				String city = r.getRegionName();
				c.setCity(city);
			}
		}
		if (c.getRegion() != null && !"".equals(c.getRegion())) {
			Region r = regionService.getByCode(c.getRegion());
			if (r != null) {
				String region = r.getRegionName();
				c.setRegion(region);
			}
		}
		
		if(c.getId()!=null){
			
			CollectPlaceQuery query = new CollectPlaceQuery();
			query.setEnterpriseId(getId(request));
			query.setIdArray(c.getId());
			int count = collectPlaceService.queryCount(query);
			
			if(count<=0){
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "自提点数据错误!");
			}
			
			c.setGmtModify(new Date());
			c.setClpStatus(1);
			c.setEnterpriseId(getId(request));
			collectPlaceService.update(c);
			
			
		}else{
			c.setGmtModify(new Date());
			c.setClpStatus(1);
			c.setEnterpriseId(getId(request));
			c.setGmtCreate(new Date());
			collectPlaceService.add(c);
		}
		
		return LunjarApiResponse.create();
	}
}
