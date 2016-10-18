package com.lunjar.ebp.admin.web.controller.discount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.oss.ServiceException;
import com.lunjar.ebp.admin.domain.model.EnterpriseAgent;
import com.lunjar.ebp.admin.web.session.EnterpriseAgentSession;
import com.lunjar.ebp.bizsupport.model.Discount;
import com.lunjar.ebp.bizsupport.query.DiscountQuery;
import com.lunjar.ebp.bizsupport.service.DiscountService;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.web.controller.ControllerSupport;
import com.lunjar.products.core.webapi.LunjarApiResponse;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

@RequestMapping(value = "discount")
@Controller
public class DiscountController extends ControllerSupport {
	
	
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
	private DiscountService discountService;

	@RequestMapping(value = "list")
	public String list(DiscountQuery query, Model model
			,HttpServletRequest request) {
		query.setSort("GMT_CREATE desc");
		query.setEnterpriseId(getId(request));
		query.setPageSize(10);
		PageResult<Discount> list = discountService.queryListByPage(query);
		model.addAttribute("ds", list);
		model.addAttribute("title", "优惠列表");
		return "/pages/discount/list";
	}

	@RequestMapping(value = "add")
	public String add(Long id, Model model,HttpServletRequest request) {
		if (id != null) {
			DiscountQuery query = new DiscountQuery();
			query.setEnterpriseId(getId(request));
			query.setIdArray(id);
			List<Discount> list = discountService.queryList(query);
			if (list == null || list.size() != 1) {
				throw new ServiceException("数据错误");
			}
			model.addAttribute("d", list.get(0));
		}
		model.addAttribute("title", "优惠设置");
		return "/pages/discount/add";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delete")
	public LunjarApiResponse delete(Long id,HttpServletRequest request) {

		if (id == null) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误!");
		}
		DiscountQuery query = new DiscountQuery();
		query.setEnterpriseId(getId(request));
		query.setIdArray(id);
		int count = discountService.queryCount(query);
		if (count <= 0) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "优惠数据错误!");
		}

		discountService.delete(id);

		return LunjarApiResponse.create();
	}

	/**
	 * 保存方法
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save")
	public LunjarApiResponse save(Discount discount, String staticType, BigDecimal[] conditions, String[] isFree,
			BigDecimal[] discountFee, String[] isPost, String[] freePostArea,HttpServletRequest request) {

		if (discount.getName() == null || "".equals(discount.getName().trim())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "活动名称为空!");
		}
		
		if(conditions==null || conditions.length<=0){
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "优惠条件为空!");
		}
		

		List<Discount> list = new ArrayList<>();

		for (int i = 0; i < conditions.length; i++) {
			Discount d = new Discount();

			if (conditions[i] == null) {
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "优惠条件为空!");
			}
			d.setConditions(conditions[i]);
			if (isFree!= null && i<isFree.length && "1".equals(isFree[i])) {
				// 表示需要减免金额
				if (discountFee == null || !(i<discountFee.length) || discountFee[i] == null ) {
					return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "减免金额为空!");
				}
				d.setDiscountFee(discountFee[i]);
			} else {
				d.setDiscountFee(new BigDecimal("0"));
			}

			if (isPost[i] != null && i<isPost.length && "1".equals(isPost[i])) {
				// 表示包邮
				if (freePostArea== null || !(i<freePostArea.length) || "".equals(freePostArea[i]) ) {
					return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "包邮地区为空!");
				} else {
					d.setIsPost(1);
					d.setFreePostArea(freePostArea[i]);
				}
			} else {
				d.setIsPost(2);
			}

			if (isFree[i] != null && "2".equals(isFree[i]) && isPost[i] != null && "2".equals(isPost[i])) {
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请设置优惠信息!");
			}

			d.setEnterpriseId(getId(request));
			d.setName(discount.getName());
			d.setStatus(1);
			list.add(d);
		}

		if (discount.getId() != null) {
			
			DiscountQuery query = new DiscountQuery();
			query.setEnterpriseId(getId(request));
			query.setIdArray(discount.getId());
			int count = discountService.queryCount(query);
			if (count != 1) {
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "优惠信息数据错误!");
			}
			
			if(list!=null && list.size()>0){
				Discount d = list.get(0);
				d.setId(discount.getId());
				discountService.update(d);
			}
		} else {
			if(list!=null && list.size()>0){
				discountService.addDiscount(list);
			}
		}

		return LunjarApiResponse.create();
	}

	/**
	 * 更新方法
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update")
	public LunjarApiResponse update(Long id, String name, Integer status,HttpServletRequest request) {
		if (id == null) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误!");
		}
		DiscountQuery query = new DiscountQuery();
		query.setEnterpriseId(getId(request));
		query.setIdArray(id);
		int count = discountService.queryCount(query);
		if (count != 1) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "优惠信息数据错误!");
		}

		if ((name == null || "".equals(name.trim())) && status == null) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误!");
		}
		// 更新名称
		if (name != null && !"".equals(name.trim())) {
			Discount d = new Discount();
			d.setId(id);
			d.setName(name);
			discountService.update(d);
		}

		// 更新名称
		if (status != null) {
			Discount d = new Discount();
			d.setId(id);
			d.setStatus(status);
			discountService.update(d);
		}

		return LunjarApiResponse.create();
	}
}
