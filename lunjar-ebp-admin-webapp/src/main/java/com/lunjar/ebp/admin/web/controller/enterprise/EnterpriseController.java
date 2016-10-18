package com.lunjar.ebp.admin.web.controller.enterprise;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lunjar.ebp.admin.biz.utils.ValidateContentType;
import com.lunjar.ebp.admin.domain.model.EnterpriseAgent;
import com.lunjar.ebp.admin.web.session.EnterpriseAgentSession;
import com.lunjar.ebp.bizsupport.model.Enterprise;
import com.lunjar.ebp.bizsupport.model.Region;
import com.lunjar.ebp.bizsupport.query.EnterpriseQuery;
import com.lunjar.ebp.bizsupport.service.EnterpriseService;
import com.lunjar.ebp.bizsupport.service.FileUploadService;
import com.lunjar.ebp.bizsupport.service.RegionService;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.utils.DigestsUtils;
import com.lunjar.products.core.web.controller.ControllerSupport;
import com.lunjar.products.core.webapi.LunjarApiResponse;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

@RequestMapping(value = "enterprise")
@Controller
public class EnterpriseController extends ControllerSupport {

	@Autowired
	private FileUploadService fileUploadService;

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
	private EnterpriseService enterpriseService;

	@Autowired
	private RegionService regionService;

	/**
	 * 查询商家信息
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "info")
	public String info(Model model,HttpServletRequest request) throws ServiceException {

		List<Region> listProvince = regionService.getAllProvince();

		Enterprise e = enterpriseService.getById(getId(request));
		if (e != null) {
			model.addAttribute("e", e);

			String province = e.getProvince();
			String city = e.getCity();
			if (province != null && !"".equals(province) && city != null && !"".equals(city)) {
				for (Region r : listProvince) {
					if (r.getRegionName().equals(province)) {
						List<Region> listCity = regionService.getByParentCode(r.getCode());
						model.addAttribute("citys", listCity);
						for (Region c : listCity) {
							if (c.getRegionName().trim().equals(city.trim())) {
								List<Region> listRegion = regionService.getByParentCode(c.getCode());
								model.addAttribute("regions", listRegion);
							}
						}
						break;
					}
				}
			}
			if(e.getTelephone()!=null && e.getTelephone().contains("-")){
				model.addAttribute("areaCode", e.getTelephone().split("-")[0]);
				model.addAttribute("telePhoneNum", e.getTelephone().split("-")[1]);
			}
		}
		model.addAttribute("title", "商家信息");
		model.addAttribute("provinces", listProvince);
		return "/pages/enterpriseinfo";
	}

	/**
	 * 图片上传
	 * 
	 * @param files
	 * @param type
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "fileUpload")
	@ResponseBody
	public LunjarApiResponse fileUpload(@RequestParam("file") MultipartFile[] files, HttpServletResponse response,HttpServletRequest request)
			throws IOException, ServiceException {

		if (files == null || files.length <= 0) {
			// 没有上传文件
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请选择文件!");
		}
		// 文件类型
		String contentType = files[0].getContentType();
		// 文件大小
		long fileSize = files[0].getSize();

		if (!ValidateContentType.validateImage(contentType)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请上传图片!");
		}

		Map<String, String> map = fileUploadService.uploadFileReturnMap(getId(request), files[0].getInputStream());
		return LunjarApiResponse.create(map);
	}

	/**
	 * 保存商家信息
	 * 
	 * @throws ServiceException
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public LunjarApiResponse save(Enterprise e,HttpServletRequest request,String areaCode,String telePhoneNum) throws ServiceException {

		if (e.getEnterpiseName() == null || "".equals(e.getEnterpiseName())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "企业名称为空!");
		}
		if (e.getContactPhone() == null || "".equals(e.getContactPhone())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "联系人电话为空!");
		}

		if (e.getProvince() != null && !"".equals(e.getProvince())) {
			Region r = regionService.getByCode(e.getProvince());
			if (r != null) {
				String province = r.getRegionName();
				e.setProvince(province);
			}
		}
		if (e.getCity() != null && !"".equals(e.getCity())) {
			Region r = regionService.getByCode(e.getCity());
			if (r != null) {
				String city = r.getRegionName();
				e.setCity(city);
			}
		}
		if (e.getRegion() != null && !"".equals(e.getRegion())) {
			Region r = regionService.getByCode(e.getRegion());
			if (r != null) {
				String region = r.getRegionName();
				e.setRegion(region);
			}
		}
		
		if(areaCode!=null && !"".equals(areaCode.trim()) && telePhoneNum!=null && !"".equals(telePhoneNum.trim())){
			e.setTelephone(areaCode.trim()+"-"+telePhoneNum.trim());
		}
		
		e.setId(getId(request));
		e.setGmtModify(new Date());
		enterpriseService.update(e);
		return LunjarApiResponse.create();
	}

	/**
	 * 修改密码页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(Model model) {
		model.addAttribute("title", "修改密码");
		return "/pages/updatePass";
	}

	/**
	 * 修改密码
	 */
	@RequestMapping(value = "updatePass")
	@ResponseBody
	public LunjarApiResponse updatePass(String oldPass, String newPassOne, String newPassTwo, String random,
			String passSign,HttpServletRequest request) {

		if (oldPass == null || "".equals(oldPass)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "原始密码为空!");
		}
		if (newPassOne == null || "".equals(newPassOne)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "新密码为空!");
		}
		if (newPassTwo == null || "".equals(newPassTwo)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "确认密码为空!");
		}

		if (random == null || "".equals(random)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据有误!");
		}
		if (passSign == null || "".equals(passSign)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据有误!");
		}

		EnterpriseQuery query = new EnterpriseQuery();
		query.setIdArray(getId(request));
		List<Enterprise> list = enterpriseService.queryList(query);
		if (list != null && list.size() == 1) {
			Enterprise e = list.get(0);
			if (!e.getPassword().equals(oldPass)) {
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "原始密码不正确!");
			}
		}
		if (!newPassOne.equals(newPassTwo)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "新密码和确认密码不相同!");
		}
		String newPassSign = DigestsUtils.md5Hex(DigestsUtils.md5Hex((newPassTwo + random).getBytes()).getBytes());

		if (!passSign.equals(newPassSign)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "非法请求!");
		}

		Enterprise e = new Enterprise();
		e.setId(getId(request));
		e.setPassword(newPassOne);
		e.setGmtModify(new Date());
		enterpriseService.update(e);
		return LunjarApiResponse.create();
	}
	/**
	 * 用户退出
	 * @return
	 */
	@RequestMapping(value = "userLogout")
	public String userLogout(HttpServletRequest request,HttpServletResponse response){
		//清楚缓存中的数据
		enterpriseAgentSession.remove(request, response);
		//删除cookie
		/*Cookie cookie = new Cookie("adminPass", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		cookie = new Cookie("adminLogin", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);*/
		return "redirect:/";
	}
	
}
