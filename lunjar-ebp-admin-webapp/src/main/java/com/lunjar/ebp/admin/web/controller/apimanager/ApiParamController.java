package com.lunjar.ebp.admin.web.controller.apimanager;
//package com.lunjar.demo.web.controller.apimanager;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ancun.bps.bizsupport.exception.BizSupportException;
//import com.ancun.bps.bizsupport.model.ApiParam;
//import com.ancun.bps.bizsupport.query.ApiParamQuery;
//import com.ancun.bps.bizsupport.service.ApiParamService;
//import com.ancun.products.core.model.PageResult;
//import com.ancun.products.core.web.WebCoreConstants;
//import com.ancun.products.core.web.annotation.QueryPage;
//import com.ancun.products.core.web.controller.ControllerSupport;
//import com.ancun.products.core.webapi.AncunApiResponse;
//import com.lunjar.demo.biz.authority.AdminAuthority;
//
//@Controller
//@RequestMapping("apiParam")
//@AdminAuthority("bd_api_param")
//public class ApiParamController extends ControllerSupport{
//	@Autowired
//	private ApiParamService apService;
//	
//	/**
//	 * 列表
//	 * <p>
//	 * @param model
//	 * @param query
//	 * @param response
//	 * @return
//	 * @throws BizSupportException
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a>
//	 * create at 2016年4月25日 下午2:16:42
//	 */
//	@RequestMapping(value="/a{id}",method= RequestMethod.GET)
//	public String List(Model model,@QueryPage(defaultPageSize=10) ApiParamQuery query,HttpServletResponse response) throws BizSupportException{
//		controllerUtils.saveQueryToCookie(query, response);
//		
//		return getViewAndPageData(model, query);
//	} 
//	
//	/**
//	 * 列表返回时从cookie中取出ApiParamQuery
//	 * <p>
//	 * @param model
//	 * @param request
//	 * @return
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a>
//	 * create at 2016年4月25日 下午2:16:58
//	 */
//	@RequestMapping(value=WebCoreConstants.LIST_BACK_ACTION_NAME,method=RequestMethod.GET)
//	public String listb(Model model,HttpServletRequest request){
//		ApiParamQuery query=controllerUtils.getQueryFromCookie(ApiParamQuery.class, request);
//		return getViewAndPageData(model, query);
//	}
//	
//	/**
//	 * 查询获取列表数据
//	 * <p>
//	 * @param model
//	 * @param query
//	 * @return
//	 * @throws BizSupportException
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a>
//	 * create at 2016年4月25日 下午2:17:43
//	 */
//	private String getViewAndPageData(Model model,ApiParamQuery query){
//		List<ApiParam> apiParam=apService.queryList(query);
//		int count=apService.queryCount(query);
//		PageResult<ApiParam> page=PageResult.create(query, apiParam, count);
//		model.addAttribute("page",page);
//		model.addAttribute("query",query);
//		return "basedata/param-list";
//	}
//	
//	/**
//	 * 添加
//	 * <p>
//	 * @param model
//	 * @param apiParam
//	 * @return
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a>
//	 * create at 2016年4月25日 下午2:18:02
//	 */
//	@RequestMapping(value="/add",method=RequestMethod.GET)
//	public String add(Model model,@ModelAttribute("apiParam") ApiParam apiParam){
//		model.addAttribute("screen_title","添加接口参数");
//		return "basedata/api-form";
//	}
//	
//	/**
//	 * 添加提交
//	 * <p>
//	 * @param apiParam
//	 * @return
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a>
//	 * create at 2016年4月25日 下午2:18:09
//	 */
//	@RequestMapping(value="/add",method=RequestMethod.POST)
//	public AncunApiResponse addSubmit(@ModelAttribute("apiParam") @Valid ApiParam apiParam){
//		apService.add(apiParam);
//		return AncunApiResponse.create(apiParam.getId());
//	}
//	
//	/**
//	 * 详细
//	 * <p>
//	 * @param model
//	 * @param id
//	 * @return
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a>
//	 * create at 2016年4月25日 下午2:19:50
//	 */
//	@RequestMapping(value = "/d{id}", method = RequestMethod.GET)
//	public String detail(Model model, @PathVariable("id") Integer id) {
//		ApiParam apiParam = apService.load(id);
//		if (apiParam != null) {
//			model.addAttribute("apiParam", apiParam);
//		}
//		return "basedata/param-form";
//	}
//	
//	/**
//	 * 修改表单
//	 * <p>
//	 * @param model
//	 * @param id
//	 * @return
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a>
//	 * create at 2016年4月25日 下午2:20:02
//	 */
//	@RequestMapping(value = "/e{id}", method = RequestMethod.GET)
//	public String update(Model model,@PathVariable("id") Integer id){
//		ApiParam apiParam=apService.load(id);
//		if(apiParam!=null){
//			model.addAttribute("screen_title","操作接口:"+apiParam.getParamName());
//			model.addAttribute("apiParam",apiParam);
//		}
//		return "basedata/api-form";
//	}
//	
//	/**
//	 * 修改提交
//	 * <p>
//	 * @param apiParam
//	 * @return
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a>
//	 * create at 2016年4月25日 下午2:20:18
//	 */
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	@ResponseBody	
//	public AncunApiResponse editSubmit(@ModelAttribute() ApiParam apiParam) {
//		apService.update(apiParam);
//		return AncunApiResponse.create(apiParam.getId());
//
//	}
//}
