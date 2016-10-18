package com.lunjar.ebp.admin.web.controller.template;
//package com.lunjar.demo.web.controller.template;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ancun.bps.bizsupport.dto.TemplateSourceDto;
//import com.ancun.bps.bizsupport.enums.EnumTemplateSourceStatus;
//import com.ancun.bps.bizsupport.model.TemplateSource;
//import com.ancun.bps.bizsupport.query.TemplateSourceQuery;
//import com.ancun.bps.bizsupport.service.TemplateSourceService;
//import com.ancun.bps.core.datadict.BpsDataDictService;
//import com.ancun.bps.core.datadict.impl.BpsDataDictRemoteService;
//import com.ancun.bps.core.utils.BpsHttpUtils;
//import com.ancun.products.core.datadict.model.PubDataDictionaryEntity;
//import com.ancun.products.core.exception.ServiceException;
//import com.ancun.products.core.model.DataDictionary;
//import com.ancun.products.core.model.PageResult;
//import com.ancun.products.core.web.annotation.QueryPage;
//import com.ancun.products.core.webapi.AncunApiResponse;
//import com.lunjar.demo.biz.authority.AdminAuthority;
//
///**
// * 模版资源库管理
// * 
// * <p>
// * create at 2016年4月25日 下午2:51:31
// * 
// * @author <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
// * @version %I%, %G%
// * @see
// */
//@Controller
//@RequestMapping(value = "templatesource")
//@AdminAuthority("template")
//public class TemplateSourceController {
//
//	private static final Logger logger = LoggerFactory.getLogger(TemplateSourceController.class);
//
//	@Autowired
//	private TemplateSourceService templateSourceService;
//	@Autowired
//	private BpsDataDictService bpsDataDictService;
//	@Autowired
//	private BpsDataDictRemoteService bpsDataDictRemoteService;
//
//	static String DICT_GROUP = "productType";
//	static String PARENT_VALUE = "root";
//
//	/**
//	 * 模版列表首页
//	 */
//	@SuppressWarnings("static-access")
//	@RequestMapping(value = "list")
//	@AdminAuthority("templatesource_list")
//	public String list(Model model, @QueryPage TemplateSourceQuery templateSourceQuery, HttpServletRequest request) {
//		templateSourceQuery.setSort("version desc");
//		int count = templateSourceService.queryCount(templateSourceQuery);
//		if (count > 0) {
//			List<TemplateSource> list = templateSourceService.list(templateSourceQuery);
//			List<TemplateSourceDto> resultList = new ArrayList<>();
//			if (list != null && list.iterator().hasNext()) {
//
//				for (TemplateSource ts : list) {
//					TemplateSourceDto dto = new TemplateSourceDto(ts);
//					PubDataDictionaryEntity pdde = bpsDataDictRemoteService.load(DICT_GROUP, ts.getProductType());
//					if (pdde != null && pdde.getParentValue() != null) {
//						dto.setItemName(pdde.getDictText());
//						PubDataDictionaryEntity o = bpsDataDictRemoteService.load(DICT_GROUP, pdde.getParentValue());
//						if (o != null) {
//							dto.setProductTypeName(o.getDictText());
//						}
//					}
//					resultList.add(dto);
//				}
//			}
//			PageResult<TemplateSourceDto> page = null;
//			page = page.create(templateSourceQuery, resultList, count);
//			model.addAttribute("page", page);
//		}
//		model.addAttribute("query", templateSourceQuery);
//		return "templatesource/templatesource-list";
//	}
//
//	/**
//	 * 启用资源库模版
//	 * @throws ServiceException 
//	 * 
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "enable", method = RequestMethod.POST)
//	@ResponseBody
//	@AdminAuthority("templatesource_enableAndDisable")
//	public AncunApiResponse edit(String productType, Integer id) throws ServiceException {
//		if (productType != null && id != null) {
//			TemplateSource ts = templateSourceService.load(id);
//			if(null != ts){
//				if (ts.getStatus().intValue() == EnumTemplateSourceStatus.DISABLE.getValue().intValue()) {
//					templateSourceService.enableTemplate(id, productType);
//				} else {
//					ts.setStatus(EnumTemplateSourceStatus.DISABLE.getValue());
//					templateSourceService.updateTemplate(ts);
//				}
//			}
//			return AncunApiResponse.create();
//		} else {
//			return AncunApiResponse.create("参数不完整");
//		}
//	}
//
//	@RequestMapping(value = "upload", method = RequestMethod.POST)
//	@AdminAuthority("templatesource_upload")
//	public AncunApiResponse upload(@RequestParam("productType") String productType, @RequestParam("path") String path) {
//		InputStream is=null;
//		try {
//			TemplateSourceDto templateSourceDto = new TemplateSourceDto();
//			templateSourceDto.setProductType(productType);
////			templateSourceDto.setPath(path);
//			is = BpsHttpUtils.getInputStream2(path);
//			int id = templateSourceService.add(templateSourceDto,is);
//			return AncunApiResponse.create(id);
//		} catch (ServiceException e) {
//			return AncunApiResponse.create(e.getCode(), e.getMessage());
//		} finally {
//			try {
//				is.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	@RequestMapping(value = "getDcitGroupByParentValue")
//	@ResponseBody
//	@AdminAuthority("templatesource_upload")
//	public List<DataDictionary> getDictGroupByParentValue(String dictGroup, String parentValue) {
//		List<DataDictionary> list = bpsDataDictService.getByGroupAndParentValue(dictGroup, parentValue);
//		return list;
//	}
//
//	@RequestMapping(value = "getVersion")
//	@ResponseBody
//	@AdminAuthority("templatesource_upload")
//	public String getVersion(String productType) throws ServiceException{
//		int version = templateSourceService.getNextVersion(productType);
//		String versionStr = "V" + String.valueOf(version) + ".0";
//		return versionStr;
//	}
//}
