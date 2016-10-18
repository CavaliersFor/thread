package com.lunjar.ebp.admin.biz.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ancun.bps.core.datadict.impl.BaseDataExistsException;
import com.ancun.bps.core.datadict.impl.BpsDataDictRemoteService;
import com.ancun.bps.core.datadict.impl.EnumDataDictStatus;
import com.lunjar.products.core.datadict.DataDictionaryService;
import com.lunjar.products.core.datadict.cache.DataDictionaryCacheForData;
import com.lunjar.products.core.datadict.cache.DataDictionaryCacheForName;
import com.lunjar.products.core.datadict.dto.DataDictionaryList;
import com.lunjar.products.core.datadict.model.PubDataDictionaryEntity;
import com.lunjar.products.core.datadict.model.PubDataDictionaryQuery;
import com.lunjar.products.core.model.DataDictionary;

@Service
public class BpsDataDictManager {

	// private static final EnumSysLogOpertateObject OPERATE_OBJECT =
	// EnumSysLogOpertateObject.DATA_DICTIONARY;

	private static Logger log = LoggerFactory.getLogger(BpsDataDictManager.class);

	@Autowired
	private BpsDataDictRemoteService dataDictRemoteService;
	@Autowired
	private DataDictionaryCacheForData dataDictionaryCacheForData;
	@Autowired
	private DataDictionaryCacheForName dataDictionaryCacheForName;
	// @Autowired
	// private SysLogService sysLogService;

	/**
	 * 获取分组
	 * <p>
	 * author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
	 * version: 2013-2-21 下午1:51:36 <br>
	 * 
	 * @return
	 */
	public List<PubDataDictionaryEntity> getGroup() {
		return dataDictRemoteService.getGroup();
	}

	/**
	 * 列表
	 * <p>
	 * author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
	 * version: 2012-12-5 下午5:15:39 <br>
	 * 
	 * @param queryParameter
	 * @return
	 */
	public List<PubDataDictionaryEntity> queryForList(PubDataDictionaryQuery query) {
		return dataDictRemoteService.queryList(query);
	}

	/**
	 * 根据主键获取
	 * <p>
	 * author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
	 * version: 2012-12-5 下午5:16:11 <br>
	 * 
	 * @param dictValue
	 * @param dictGroup
	 * @return
	 */
	public PubDataDictionaryEntity getByPk(String dictGroup, String dictValue) {
//		PubDataDictionaryQuery query = new PubDataDictionaryQuery();
//		query.setDictValue(dictValue);
//		query.setDictGroup(dictGroup);
//		List<PubDataDictionaryEntity> list = dataDictRemoteService.queryList(query);
//		if(list.size()>0){
//			return list.get(0);
//		}else{
//			List<PubDataDictionaryEntity> listGroup = dataDictRemoteService.getGroup();
//			for(PubDataDictionaryEntity  p :listGroup){
//				if(p.getDictValue().equalsIgnoreCase(dictGroup)){
//					return p;
//				}
//			}
//		}
//		return null;
		return dataDictRemoteService.load(dictGroup, dictValue);
	}

	/**
	 * 更新
	 * <p>
	 * author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
	 * version: 2012-12-5 下午5:16:23 <br>
	 * 
	 * @param dataDictionary
	 */
	@Transactional
	public void update(PubDataDictionaryEntity dataDictionary) {
		//父节点需要传,先取出当前的数据
		String group=getStr(dataDictionary.getDictGroup());
		String value=getStr(dataDictionary.getDictValue());
		PubDataDictionaryEntity old = this.getByPk(group,value);
		old.setDictText(dataDictionary.getDictText());//修改text
		old.setSortNum(dataDictionary.getSortNum());//修改排序
		dataDictRemoteService.update(old);
		// 刷新缓存
		refreshCache(dataDictionary, old.getParentValue(), true);
	}

	private String getStr(String s){
		if(s.contains(",")){
			s = s.substring(0, s.indexOf(","));
		}
		return s.trim();
	}
	/**
	 * 添加
	 * <p>
	 * author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
	 * version: 2012-12-5 下午5:17:03 <br>
	 * 
	 * @param dataDictionary
	 * @throws BaseDataExistsException
	 */
	@Transactional
	public void add(PubDataDictionaryEntity dataDictionary) throws BaseDataExistsException {
		String group = dataDictionary.getDictGroup();
		if(dataDictionary.getParentValue().equalsIgnoreCase("root")){
			group=dataDictionary.getDictValue();
		}
		PubDataDictionaryEntity o =dataDictRemoteService.load(group, dataDictionary.getDictValue());
		if (o != null) {// 已存在
			throw new BaseDataExistsException(o.toDTO());
		}
		dataDictionary.setDictGroup(group);
		dataDictionary.setStatus(EnumDataDictStatus.NORMAL.getValue());
		// 添加时设置最大排序号
//		int maxSort = dataDictRemoteService.getMaxSortNum(group, dataDictionary.getParentValue());
//		dataDictionary.setSortNum(maxSort + 1);
		dataDictRemoteService.insert(dataDictionary);
		// 刷新缓存
		refreshCache(dataDictionary, null, true);
		// sysLogService.writeLog(OPERATE_OBJECT, dataDictionary.getDictGroup()
		// + ":" + dataDictionary.getDictValue(),
		// dataDictionary.getDictText(), "添加");
	}

	/**
	 * 修改状态
	 * <p>
	 * author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
	 * version: 2012-12-4 下午4:47:26 <br>
	 * 
	 * @param pubDataDictionary
	 */
	@Transactional
	public void updateStatus(PubDataDictionaryEntity pubDataDictionary) {
		// // 先取出当前的数据
		// PubDataDictionaryEntity old =
		// dataDictRemoteService.load(pubDataDictionary.getDictGroup(),
		// pubDataDictionary.getDictValue());

		dataDictRemoteService.updateStatus(pubDataDictionary.getDictValue(), pubDataDictionary.getDictGroup(),
				pubDataDictionary.getStatus());

		// 刷新缓存
		refreshCache(pubDataDictionary, null, false);

		// String operateText = null;
		// if (EnumDataDictStatus.AUDIT_NORMAL.getValue() ==
		// pubDataDictionary.getStatus()) {
		// operateText = "启用";
		// } else {
		// operateText = "停用";
		// }

		// sysLogService.writeLog(OPERATE_OBJECT,
		// pubDataDictionary.getDictGroup() + ":" +
		// pubDataDictionary.getDictValue(),
		// pubDataDictionary.getDictText(), operateText);
	}

	/**
	 * 根据group获取数据项
	 * <p>
	 * author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
	 * version: 2012-12-5 下午5:18:57 <br>
	 * 
	 * @param group
	 * @return
	 */
	public List<PubDataDictionaryEntity> getByGroup(String group) {
		return dataDictRemoteService.getByGroup(group, true);
	}

	/**
	 * 根据group和parent获取数据项
	 * <p>
	 * author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
	 * version: 2012-12-5 下午5:19:04 <br>
	 * 
	 * @param group
	 * @param parentValue
	 * @return
	 */
	public List<PubDataDictionaryEntity> getByGroupAndParentValue(String group, String parentValue) {
		return dataDictRemoteService.getByGroupAndParentValue(group, parentValue, true);
	}

	/**
	 * 获取拖住之后的排序号
	 * <p>
	 * author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
	 * version: 2012-12-5 下午5:19:39 <br>
	 * 
	 * @param childrenFoDdToUpdate
	 *            拖动之后上级数据项的子项
	 * @param index
	 *            拖动之后的索引
	 * @return
	 */
	public int getNewSortNumOnMove(List<PubDataDictionaryEntity> childrenFoDdToUpdate, int index) {
		int newSortNum;
		if (childrenFoDdToUpdate.size() > index) {
			newSortNum = childrenFoDdToUpdate.get(index).getSortNum();
		} else {
			newSortNum = childrenFoDdToUpdate.get(childrenFoDdToUpdate.size() - 1).getSortNum() + 1;
		}
		return newSortNum;
	}

	/**
	 * 拖动
	 * <p>
	 * author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
	 * version: 2012-12-1 上午12:05:05 <br>
	 * 
	 * @param ddMove
	 *            要拖动的数据项
	 * @param dictGroup
	 *            要拖动的数据的group
	 * @param newParentValue
	 *            拖动之后的数据项value
	 * @param newSortNum
	 *            拖动之后的排序号
	 * @param maxSortNumInChildrenOfNewParent
	 *            拖动之后的上级数据项的子项中最大排序号
	 */
	@Transactional
	public void move(PubDataDictionaryEntity ddMove, String dictGroup, String newParentValue, int newSortNum,
			int maxSortNumInChildrenOfNewParent) {
		String ddDictValue = ddMove.getDictValue();
		String oldParentValue = ddMove.getParentValue();// 原上级数据项value
		// String ddDictText = ddMove.getDictText();

		PubDataDictionaryEntity ddUpdate = new PubDataDictionaryEntity(ddDictValue, dictGroup);// 初始化拖动之后的数据项

		if (StringUtils.equals(oldParentValue, newParentValue)) {// 不改变上级目录
			// 判断是否存在排序号为sortNum的项
			boolean isExists = dataDictRemoteService.isExistsBySortNum(newSortNum, dictGroup, newParentValue);
			if (isExists) {// 若存在，则需要调整其他部门编号
				int oldSortNum = ddMove.getSortNum();// 原排序号
				if (newSortNum < oldSortNum) {// 原排序号大约新排序号，表示 向上拖动
					// 把大于或等于newSortNum且小于oldSortNum部门的排序号加1
					dataDictRemoteService.increaseSortNum(newSortNum, oldSortNum, dictGroup, newParentValue);
				} else {// 原排序号小于新排序号, 表示向下拖动
					// 把小于或等于newSortNum且大于oldSortNum部门的排序号减1
					dataDictRemoteService.decreaseSortNum(newSortNum, oldSortNum, dictGroup, newParentValue);
				}
			}
		} else {// 改变了上级目录
			ddUpdate.setParentValue(newParentValue);

			if (newSortNum <= maxSortNumInChildrenOfNewParent) {
				// 若新排序号不大于拖动之后的上级目录的子目录中最大排序号，表示不是拖动到末尾，
				// 需要将拖动之后的上级目录的子目录中排序号大于newSortNum的部门的排序号+1
				dataDictRemoteService.increaseSortNum(newSortNum, dictGroup, newParentValue);
			}
		}

		ddUpdate.setSortNum(newSortNum);
		dataDictRemoteService.update(ddUpdate);

		// sysLogService.writeLog(OPERATE_OBJECT, ddMove.getDictGroup() + ":" +
		// ddMove.getDictValue(),
		// ddMove.getDictText(), String.format("排序[%s-->%s]",
		// ddMove.getSortNum(), newSortNum));

		// 刷新缓存
		if (StringUtils.isBlank(ddUpdate.getParentValue())) {
			ddUpdate.setParentValue(oldParentValue);
		}
		refreshCache(ddUpdate, oldParentValue, false);
	}

	/**
	 * 刷新缓存 author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
	 * version: 2012-12-4 下午1:41:17 <br>
	 * 
	 * @param dataDictionary
	 *            新加的或更新之后的数据项
	 * @param oldParentValue
	 *            原上级
	 * @param isRefreshNameCache
	 *            是否更新名称缓存，当添加和修改的时候需要
	 */
	private void refreshCache(PubDataDictionaryEntity dataDictionary, String oldParentValue,
			boolean isRefreshNameCache) {
		String group = dataDictionary.getDictGroup();
		String dictValue = dataDictionary.getDictValue();
		String newParentValue = dataDictionary.getParentValue();

		// 刷新名称缓存
		if (isRefreshNameCache) {
			String keyNameCache = group + DataDictionaryService.DATA_GROUP_AND_VALUE_SPLIT + dictValue;
			dataDictionaryCacheForName.put(keyNameCache, dataDictionary.toDTO());
		}

		// 刷新分组和上级值的缓存
		List<PubDataDictionaryEntity> listPo = dataDictRemoteService.getByGroupAndParentValue(group, newParentValue,
				false);
		if (!listPo.isEmpty()) {
			List<DataDictionary> listDto = toDtoList(listPo);
			if (log.isDebugEnabled()) {
				log.debug("put data dictionary  group is [" + group + "], parentValue is [" + newParentValue
						+ "], size: [" + listPo.size() + "]");
			}
			// 缓存的key为分组+分隔符+上级值
			String key = group + DataDictionaryService.DATA_GROUP_AND_VALUE_SPLIT + newParentValue;
			dataDictionaryCacheForData.put(key, new DataDictionaryList(listDto));
		}

		// 刷新分组缓存
		List<PubDataDictionaryEntity> listPo2 = dataDictRemoteService.getByGroup(group, false);
		if (!listPo2.isEmpty()) {
			List<DataDictionary> listDto2 = toDtoList(listPo2);
			if (log.isDebugEnabled()) {
				log.debug("put data dictionary  group is [" + group + "], size: [" + listDto2.size() + "]");
			}
			// 缓存的key为分组
			dataDictionaryCacheForData.put(group, new DataDictionaryList(listDto2));
		}
	}

	/**
	 * <p>
	 * author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
	 * version: 2012-12-4 下午2:18:56 <br>
	 * 
	 * @param listPo
	 * @return
	 */
	private List<DataDictionary> toDtoList(List<PubDataDictionaryEntity> listPo) {
		List<DataDictionary> listDto = new ArrayList<DataDictionary>();
		for (PubDataDictionaryEntity o : listPo) {
			listDto.add(o.toDTO());
		}
		return listDto;
	}
}
