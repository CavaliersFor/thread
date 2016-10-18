package com.lunjar.ebp.admin.web.controller.basedata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ancun.bps.core.datadict.impl.BaseDataExistsException;
import com.ancun.bps.core.datadict.impl.BpsDataDictRemoteService;
import com.lunjar.ebp.admin.biz.authority.AdminAuthority;
import com.lunjar.products.core.datadict.model.PubDataDictionaryEntity;
import com.lunjar.products.core.datadict.model.PubDataDictionaryQuery;
import com.lunjar.products.core.model.DataDictionary;
import com.lunjar.products.core.web.controller.ControllerSupport;
import com.lunjar.products.core.web.model.ext.BaseDataTreeNode;
import com.lunjar.products.core.web.utils.ControllerUtils;

/**
 * 基础数据管理控制类
 * <p>
 * create at 2016年5月7日 下午3:56:20
 * @author <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
 * @version %I%, %G%
 * @see
 */
@Controller
@RequestMapping("basedata")
@AdminAuthority("bd_normalBaseData")
public class BaseDataController extends ControllerSupport {
	
	@Autowired
	private BpsDataDictRemoteService dataDictRemoteService;
	
	static String ROOT_NODE="root";
	
	@Autowired
	private ControllerUtils controllerUtils;

	/** 入口 */
	@RequestMapping("list")
	@AdminAuthority("bd_normalBaseData_list")
	public String index(Model model) {
		controllerUtils.renderDatadict(model, "dataDictStatus");
		return "/basedata/basedata-list";
	}
	
	private DataDictionary pubToDTO(PubDataDictionaryEntity pdde) {
		DataDictionary dto = new DataDictionary();
		String value=pdde.getDictGroup()+"|"+pdde.getDictValue()+"|"+pdde.getParentValue();
		dto.setValue(value);
		dto.setColor(pdde.getTextColor());
		dto.setText(pdde.getDictText());
		dto.setLeaf(pdde.isLeaf());
		dto.setExtend1(pdde.getExtend1());
		dto.setExtend2(pdde.getExtend2());
		dto.setExtend3(pdde.getExtend3());
		return dto;
	}
	
	/**取数据字典子级**/
	private List<PubDataDictionaryEntity> getChildpList(String dictGroup,String parentValue,List<PubDataDictionaryEntity> list){
		List<PubDataDictionaryEntity> rlist =new ArrayList<PubDataDictionaryEntity>();
		for(PubDataDictionaryEntity o:list){
			if(o.getParentValue().equalsIgnoreCase(parentValue)||o.getDictValue().equalsIgnoreCase(parentValue)||parentValue==null){
				if(dictGroup==null||o.getDictGroup().equalsIgnoreCase(dictGroup)||dictGroup.equalsIgnoreCase(ROOT_NODE)||dictGroup.equalsIgnoreCase("")){
					rlist.add(o);
				}
			}
		}
		list.removeAll(rlist);
		return rlist;
	}
	/**取数据字典子级-递归**/
	private List<PubDataDictionaryEntity> getChildPDDEList(String dictGroup,String dictValue,List<PubDataDictionaryEntity> list,List<PubDataDictionaryEntity> rlist){
		List<PubDataDictionaryEntity> olist=getChildpList(dictGroup,dictValue,list);
		if(olist!=null){
			rlist.addAll(olist);
		}
		for(PubDataDictionaryEntity o:olist){
			getChildPDDEList(o.getDictGroup(),o.getDictValue(),list,rlist);
		}
		return rlist;
	}
	/**取数据字典树子级**/
	private List<BaseDataTreeNode> getChildList(String dictGroup,String parentValue,List<PubDataDictionaryEntity> list){
		List<BaseDataTreeNode> rlist=new ArrayList<BaseDataTreeNode>();
		for(PubDataDictionaryEntity o:list){
			if(o.getParentValue().equalsIgnoreCase(parentValue)){
				if(dictGroup==null||o.getDictGroup().equalsIgnoreCase(dictGroup)){
					BaseDataTreeNode bdtn=new BaseDataTreeNode(pubToDTO(o));
					rlist.add(bdtn);
				}
			}
		}
		return rlist;
	}
	/**取数据字典树子级-递归**/
	private List<BaseDataTreeNode> getChildBDTNList(String dictGroup,String parentValue,List<PubDataDictionaryEntity> list){
		List<BaseDataTreeNode> rlist=getChildList(dictGroup,parentValue,list);
		for(BaseDataTreeNode bdtn:rlist){
			String[] sid=bdtn.getId().toString().split("\\|");
			List<BaseDataTreeNode> innerList=getChildBDTNList(sid[0],sid[1],list);
			bdtn.setChildren(innerList);
		}
		return rlist;
	}
	/** 左边三级树 */
	@RequestMapping("getGroupTree")
	@ResponseBody
	@AdminAuthority("bd_normalBaseData_list")
	public List<BaseDataTreeNode> getGroupTree() {
		List<PubDataDictionaryEntity> treeList = dataDictRemoteService.queryList(null);
		return getChildBDTNList(null,ROOT_NODE,treeList);
	}
	
	/** 获取二级下拉树 */
	@RequestMapping("getSelectTree")
	@ResponseBody
	@AdminAuthority("bd_normalBaseData_list")
	public List<BaseDataTreeNode> getSelectTree() {
		//获取所有Root
		List<PubDataDictionaryEntity> treeList = dataDictRemoteService.queryList(null);
		return getChildBDTNList(null,ROOT_NODE,treeList);
	}

	/** 右边grid数据 */
	@RequestMapping("getByGroup")
	@ResponseBody
	@AdminAuthority("bd_normalBaseData_list")
	public List<PubDataDictionaryEntity> getByGroup(PubDataDictionaryQuery query) {
		List<PubDataDictionaryEntity> pList =dataDictRemoteService.queryList(null);
		return getChildPDDEList(query.getDictGroup(),query.getDictValue(),pList,new ArrayList<PubDataDictionaryEntity>());
	}
	
	/** 单个数据 */
	@RequestMapping("getdetail")
	@ResponseBody
	@AdminAuthority({"bd_normalBaseData_add","bd_normalBaseData_edit"})
	public PubDataDictionaryEntity getDetail(@RequestParam("dictValue") String dictValue,
			@RequestParam("dictGroup") String dictGroup) {
		return dataDictRemoteService.load(dictGroup, dictValue);
	}
	
	/**修改更新**/
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	@AdminAuthority({"bd_normalBaseData_edit"})
	public void edit(PubDataDictionaryEntity pubDataDictionary) {
		dataDictRemoteService.update(pubDataDictionary);
	}

	/**
	 * 添加保存
	 * 
	 * @throws BaseDataExistsException
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	@AdminAuthority("bd_normalBaseData_add")
	public void add(PubDataDictionaryEntity pubDataDictionary) throws BaseDataExistsException {
		dataDictRemoteService.insert(pubDataDictionary);
	}

	/** 删除和恢复 */
	@RequestMapping(value = "updateStatus", method = RequestMethod.POST)
	@ResponseBody
	@AdminAuthority("bd_normalBaseData_enableAndDisable")
	public void updateStatus(PubDataDictionaryEntity pubDataDictionary) {
		dataDictRemoteService.updateStatus(pubDataDictionary.getDictValue(), pubDataDictionary.getDictGroup(),pubDataDictionary.getStatus());
	}

}