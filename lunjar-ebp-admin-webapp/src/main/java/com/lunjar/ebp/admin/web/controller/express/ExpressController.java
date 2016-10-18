package com.lunjar.ebp.admin.web.controller.express;

import java.math.BigDecimal;
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
import com.lunjar.ebp.bizsupport.model.Express;
import com.lunjar.ebp.bizsupport.model.Region;
import com.lunjar.ebp.bizsupport.query.ExpressQuery;
import com.lunjar.ebp.bizsupport.service.ExpressService;
import com.lunjar.ebp.bizsupport.service.RegionService;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.web.controller.ControllerSupport;
import com.lunjar.products.core.webapi.LunjarApiResponse;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

@RequestMapping(value = "express")
@Controller
public class ExpressController extends ControllerSupport {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ExpressController.class);

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
	private ExpressService expressService;

	@Autowired
	private RegionService regionService;

	@RequestMapping(value = "list")
	public String list(ExpressQuery query, Model model,HttpServletRequest request) {
		query.setEnterpriseId(getId(request));
		query.setSort("GMT_CREATE desc");
		PageResult<Express> list = expressService.querListPage(query);
		model.addAttribute("es", list);
		model.addAttribute("title", "运费模板列表");
		return "/pages/expresslist";
	}

	@RequestMapping(value = "add")
	public String add(Long id, Model model,HttpServletRequest request) {
		if (id != null) {
			ExpressQuery query = new ExpressQuery();
			query.setEnterpriseId(getId(request));
			PageResult<Express> list = expressService.querListPage(query);

			if (list != null && list.getData() != null && list.getData().size() > 0) {
				Express e = list.getData().get(0);
				model.addAttribute("e", e);
			}
		}
		model.addAttribute("title", "添加运费模板");
		return "/pages/expressadd";
	}

	@RequestMapping(value = "addWin")
	public String addWin(Model model, String address) {
		List<Region> listRegion = regionService.getAllProvince();
		model.addAttribute("regions", listRegion);
		model.addAttribute("address", address);
		return "/pages/expressWin";
	}

	/**
	 * 通过省的id获取市
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "getCity")
	public LunjarApiResponse getCity(String code) throws ServiceException {
		List<Region> listRegion = regionService.getByParentCode(code);
		return LunjarApiResponse.create(listRegion);
	}

	/**
	 * 保存方法
	 * 
	 * @param valuation
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save")
	public LunjarApiResponse save(Long id, Integer valuation, String[] addressss, Integer[] firstNums,
			BigDecimal[] firstPrices, Integer[] addNums, BigDecimal[] addPrices,HttpServletRequest request) {
		if (id == null) {
			ExpressQuery query = new ExpressQuery();
			query.setEnterpriseId(getId(request));
			int count = expressService.queryCount(query);
			if (count > 0) {
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "运费模版只能添加一个!");
			}
		}

		if (valuation == null) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "物流公司为空!");
		}

		Express e = new Express();
		e.setEnterpriseId(getId(request));
		e.setValuation(valuation);

		e.setGmtModify(new Date());
		e.setEcStatus(1);

		StringBuffer sb = new StringBuffer();

		if (addressss != null && addressss.length > 0) {
			for (int i = 0; i < addressss.length; i++) {
				if (addressss.length == (i + 1)) {
					sb.append(addressss[i] + ":" + firstPrices[i] + "," + addPrices[i]);
				} else {
					sb.append(addressss[i] + ":" + firstPrices[i] + "," + addPrices[i] + ";");
				}
			}
		}

		String ecPrice = sb.toString();
		logger.debug("ecPrice={}", ecPrice);
		if (ecPrice == null || "".equals(ecPrice.trim())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "快递费用为空!");
		}

		e.setEcPrice(ecPrice);
		try {
			if (id == null) {
				e.setGmtCreate(new Date());
				expressService.add(e);
			} else {
				e.setId(id);
				expressService.update(e);
			}
		} catch (Exception ex) {
			logger.error("更新失败", ex);
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "操作失败!");
		}

		return LunjarApiResponse.create();
	}

	/**
	 * 删除运费模版，物理删除
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delete")
	public LunjarApiResponse delete(Long id,HttpServletRequest request) {

		if (id == null) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误!");
		}

		ExpressQuery query = new ExpressQuery();
		query.setEnterpriseId(getId(request));
		query.setIdArray(id);
		int count = expressService.queryCount(query);
		if (count <= 0) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "运费模版数据错误!");
		}

		expressService.delete(id);

		return LunjarApiResponse.create();
	}
}
