package com.lunjar.ebp.admin.web.controller.apimanager;
//package com.lunjar.demo.web.controller.apimanager;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ancun.bps.bizsupport.exception.BizSupportException;
//import com.ancun.bps.bizsupport.model.BizSystem;
//import com.ancun.bps.bizsupport.query.BizSystemQuery;
//import com.ancun.bps.bizsupport.service.BizSystemService;
//import com.ancun.products.core.model.PageResult;
//import com.ancun.products.core.web.annotation.QueryPage;
//import com.ancun.products.core.web.exception.WebException;
//import com.ancun.products.core.webapi.AncunApiResponse;
//import com.lunjar.demo.domain.enums.EnumBizSystemStatus;
//
//@Controller
//@RequestMapping(value = "bizSystem")
//public class BizSystemController {
//    private static final Logger logger = LoggerFactory.getLogger(BizSystemController.class);
//
//    @Autowired
//    private BizSystemService bizSystemService;
//
//    @RequestMapping(value = "/list")
//    public String list(Model model, @QueryPage(defaultPageSize = 10) BizSystemQuery bizSystemQuery) throws WebException{
//    	return getViewAndPageData(model, bizSystemQuery);
//    }
//
//    /** 查询获取列表数据并渲染 
//     * @throws BizSupportException */
//	private String getViewAndPageData(Model model, BizSystemQuery query){
//		List<BizSystem> apiKeyList = bizSystemService.queryList(query);
//		int count = bizSystemService.queryCount(query);
//		PageResult<BizSystem> page = PageResult.create(query, apiKeyList, count);
//		model.addAttribute("page", page);
//		model.addAttribute("query", query);
//		return "bizSystem/accesskeys-list";
//	}
//	
//	/**
//	 * 详情
//	* @param id
//	* @return
//	* @throws WebException
//	* <p>
//	* author: <a href="mailto:caozhenfei@ancun.com">CaoZhenfei</a><br>
//	* create at: 2016年6月21日 上午9:38:33
//	 */
//	@RequestMapping(value = "/detail/{id}")
//    public String detail(Model model,@PathVariable("id") Integer id) throws WebException{
//		BizSystem bizSystem = bizSystemService.load(id);
//		model.addAttribute("bizSystem",bizSystem);
//        return "bizSystem/bizSystem-detail";
//    }
//
//	/**
//	 * 改变状态
//	 * @param id
//	 * @param status
//	 * @return
//	 * @throws WebException
//	 */
//	@RequestMapping(value="/updateBizSystemstatus", method = RequestMethod.POST)
//	@ResponseBody
//	public AncunApiResponse updateBizSystemStatus(@RequestParam("id")Integer id, @RequestParam("status")Integer status) throws WebException{
//		BizSystem bizSystem = new BizSystem();
//		bizSystem.setId(id);
//		bizSystem.setStatus(status);
//		bizSystemService.update(bizSystem);
//	    return AncunApiResponse.create();
//	}
//	
//	/**
//	 * 重置SecretKey
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value = "/resetSecretKey", method = RequestMethod.POST)
//    @ResponseBody
//    public AncunApiResponse resetSecretKey(Integer id){
//        Assert.notNull(id);
//        BizSystem bizSystem=new BizSystem();
//        bizSystem.setId(id);
//        bizSystem.setSecretKey(bizSystemService.gennerateSecretKey());
//        bizSystemService.update(bizSystem);
//        return AncunApiResponse.create();
//    }
//	
//	/**
//	 * 生成createAccessKeyAndSecretKey
//	* @return
//	* <p>
//	* author: <a href="mailto:xuanyi@ancun.com">LiXuan</a><br>
//	* create at: 2016年5月14日 下午15:54:56
//	 */
//	@RequestMapping(value = "/create-access-secret-key", method = RequestMethod.GET)
//	@ResponseBody
//	public AncunApiResponse AccessKeyAndSecretKey(){
//		return AncunApiResponse.create(bizSystemService.gennerateAccessKey() + "," + bizSystemService.gennerateSecretKey());
//	}
//	
//	@RequestMapping(value="/add")
//	@ResponseBody
//	public AncunApiResponse createbizSystem(BizSystem bizSystem){
//		bizSystem.setStatus(EnumBizSystemStatus.NORMAL.getValue());
//		return AncunApiResponse.create(bizSystemService.insert(bizSystem));
//	}
//	
//	@RequestMapping(value="/update")
//	@ResponseBody
//	public AncunApiResponse updatebizSystem(BizSystem bizSystem){
//		bizSystemService.update(bizSystem);
//		return AncunApiResponse.create();
//	}
//	
//}
//
