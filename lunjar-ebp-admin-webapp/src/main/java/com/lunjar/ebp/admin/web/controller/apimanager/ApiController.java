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
//import com.ancun.bps.bizsupport.model.Api;
//import com.ancun.bps.bizsupport.query.ApiQuery;
//import com.ancun.bps.bizsupport.service.ApiService;
//import com.ancun.products.core.model.PageResult;
//import com.ancun.products.core.web.WebCoreConstants;
//import com.ancun.products.core.web.annotation.QueryPage;
//import com.ancun.products.core.web.controller.ControllerSupport;
//import com.ancun.products.core.webapi.AncunApiResponse;
//
//@Controller
//@RequestMapping("api")
//
//public class ApiController extends ControllerSupport {
//
//	@Autowired
//	private ApiService apiService;
//
//	/**
//	 * 列表
//	 * <p>
//	 * 
//	 * @param model
//	 * @param query
//	 * @param response
//	 * @return
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a> create at
//	 *         2016年4月7日 下午3:04:42
//	 */
//	@RequestMapping(value = "index", method = RequestMethod.GET)
//	public String List(Model model, @QueryPage(defaultPageSize = 10) ApiQuery query, HttpServletResponse response){
//		controllerUtils.saveQueryToCookie(query, response);
//
//		return getViewAndPageData(model, query);
//	}
//
//	/**
//	 * 列表返回时从cookie中取出ApiQuery
//	 * <p>
//	 * 
//	 * @param model
//	 * @param request
//	 * @return
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a> create at
//	 *         2016年4月7日 下午3:04:49
//	 */
//	@RequestMapping(value = WebCoreConstants.LIST_BACK_ACTION_NAME, method = RequestMethod.GET)
//	public String listb(Model model, HttpServletRequest request){
//		ApiQuery query = controllerUtils.getQueryFromCookie(ApiQuery.class, request);
//		return getViewAndPageData(model, query);
//	}
//
//	/**
//	 * 查询获取列表数据
//	 * <p>
//	 * 
//	 * @param model
//	 * @param query
//	 * @return
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a> create at
//	 *         2016年4月7日 下午3:05:59
//	 */
//	private String getViewAndPageData(Model model, ApiQuery query){
//		List<Api> api = apiService.queryList(query);
//		int count = apiService.queryCount(query);
//		PageResult<Api> page = PageResult.create(query, api, count);
//		model.addAttribute("page", page);
//		model.addAttribute("query", query);
//		return "api/api-list";
//	}
//
//	/**
//	 * 添加
//	 * <p>
//	 * 
//	 * @param model
//	 * @param apiDto
//	 * @return
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a> create at
//	 *         2016年4月7日 下午3:04:31
//	 */
//	@RequestMapping(value = "/add", method = RequestMethod.GET)
//	public String add(Model model, @ModelAttribute("api") Api api) {
//		model.addAttribute("screen_title", "添加接口");
//		return "api/api-form";
//	}
//
//	/**
//	 * 添加提交
//	 * <p>
//	 * 
//	 * @param apiDto
//	 * @return
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a> create at
//	 *         2016年4月7日 下午3:04:22
//	 */
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	public AncunApiResponse addSubmit(@ModelAttribute("api") @Valid Api api) {
//		apiService.add(api);
//		return AncunApiResponse.create(api.getId());
//	}
//
//	/**
//	 * 详细
//	 * 
//	 */
//	@RequestMapping(value = "/a{id}", method = RequestMethod.GET)
//	public String detail(Model model, @PathVariable("id") Integer id) {
//		Api api = apiService.load(id);
//		if (api != null) {
//
//			model.addAttribute("api", api);
//		}
//		return "api/param-list";
//	}
//
//	/**
//	 * 修改表单
//	 * <p>
//	 * 
//	 * @param model
//	 * @param apiUrl
//	 * @return
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a> create at
//	 *         2016年4月7日 下午3:15:32
//	 */
//	@RequestMapping(value = "/e{id}", method = RequestMethod.GET)
//	public String update(Model model, @PathVariable("id") Integer id) {
//		Api api = apiService.load(id);
//		if (api != null) {
//			model.addAttribute("screen_title", "操作接口:" + api.getApiName());
//			model.addAttribute("api", api);
//		}
//		return "api/api-form";
//	}
//
//	/**
//	 * 修改提交
//	 * 
//	 */
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	@ResponseBody
//	public AncunApiResponse editSubmit(@ModelAttribute() Api api){
//		apiService.update(api);
//		return AncunApiResponse.create(api.getId());
//
//	}
//
//	/**
//	 * 修改状态
//	 * <p>
//	 * 
//	 * @param api
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a> create at
//	 *         2016年4月7日 下午3:17:39
//	 */
//	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
//	@ResponseBody
//	public void updateStatus(Integer id, Integer newStatus){
//
//		apiService.updateStatus(id, newStatus);
//
//	}
//}
