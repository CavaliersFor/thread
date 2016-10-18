package com.lunjar.ebp.admin.biz.service;
//package com.lunjar.demo.biz.service;
//
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.aliyun.oss.ServiceException;
//import com.ancun.bps.bizsupport.dto.ApiDto;
//import com.ancun.bps.bizsupport.exception.BizSupportException;
//import com.ancun.bps.bizsupport.model.Api;
//import com.ancun.bps.bizsupport.query.ApiQuery;
//import com.ancun.bps.bizsupport.service.ApiService;
//import com.ancun.products.core.exception.DataNotFindException;
//import com.ancun.products.core.model.PageResult;
//
//
///**
// * 接口管理
// * <p>
// * create at 2016年4月7日 上午9:52:22
// * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a>
// * @version
// * @see
// */
//@Service
//public class ApiManager {
//	
//	@Autowired
//	private ApiService apiService;
//	
//	/**
//	 * 添加新接口
//	 * <p>
//	 * @param apiDto
//	 * @throws BizSupportException
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a>
//	 * create at 2016年4月7日 下午2:42:32
//	 */
//	public void add(Api api) throws BizSupportException{
//		apiService.add(api);
//	}
//	
//	
//	/**
//	 * 获取列表
//	 * <p>
//	 * @param query
//	 * @return
//	 * @throws BizSupportException
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a>
//	 * create at 2016年4月7日 上午10:03:43
//	 */
//	public PageResult<Api> queryList(ApiQuery query) throws BizSupportException {
//		List<Api> api=apiService.queryList(query);
//		int count=apiService.queryCount(query);
//		return PageResult.create(query, api, count);
//	}
//	
//	/**
//	 * 根据url获取
//	 * <p>
//	 * @param apiUrl
//	 * @return
//	 * @throws BizSupportException
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a>
//	 * create at 2016年4月7日 上午10:09:34
//	 */
//	public Api loadByUrl(String apiUrl) throws BizSupportException{
//		return apiService.loadByUrl(apiUrl);
//	}
//	
//	/**
//	 * 更新数据
//	 * <p>
//	 * @param apiDto
//	 * @throws BizSupportException
//	 * @throws DataNotFindException
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a>
//	 * create at 2016年4月7日 上午10:29:26
//	 */
//	public void update(Api api) throws BizSupportException, DataNotFindException{
//		Api old=apiService.load(api.getId());
//		
//		if(old==null){
//			throw new DataNotFindException();
//		}
//		
//		if(StringUtils.equals(old.getApiUrl(), api.getApiUrl())){
//			throw new ServiceException("修改失败，新旧值相同");
//		}
//		
//		apiService.update(api);
//	}
//	
//	/**
//	 * 更新状态
//	 * <p>
//	 * @param api
//	 * @throws BizSupportException
//	 * @author <a href="mailto:jiangwei@ancun.com">JiangWei</a>
//	 * create at 2016年4月7日 上午10:41:54
//	 */
//	public void updateStatus(String apiUrl,Integer newStatus) throws BizSupportException{
//		Api api=apiService.loadByUrl(apiUrl);
//		
//		apiService.updateStatus(api.getId(), newStatus);
//	}
//	
//	
//}
