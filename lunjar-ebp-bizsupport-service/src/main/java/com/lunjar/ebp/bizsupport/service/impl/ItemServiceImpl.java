package com.lunjar.ebp.bizsupport.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.cache.ItemListCache;
import com.lunjar.ebp.bizsupport.mappers.ItemMapper;
import com.lunjar.ebp.bizsupport.model.Item;
import com.lunjar.ebp.bizsupport.model.ItemList;
import com.lunjar.ebp.bizsupport.query.ItemQuery;
import com.lunjar.ebp.bizsupport.service.ItemService;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 产品类目（行业标准）实现类
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年10月9日下午3:12:00
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {
	private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);
	private static final String ITEM_LIST_CACHE_KEY = "item_list_cache_key_";
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemListCache itemListCache;

	@Transactional
	@Override
	public Long add(Item item) throws ServiceException {
		itemMapper.insert(item);
		return item.getId();
	}

	@Transactional
	@Override
	public void update(Item item) {
		Assert.notNull(item, "object item is null");
		Assert.notNull(item.getId(), "required item id is null");
		itemMapper.update(item);
	}

	@Override
	public List<Item> queryList(ItemQuery query) {
		return itemMapper.queryList(query);
	}

	@Override
	public int queryCount(ItemQuery query) {
		return itemMapper.queryCount(query);
	}

	@Override
	public Item load(Long id) {
		Assert.notNull(id, "object id is null");
		return itemMapper.load(id);
	}

	@Override
	public void delete(Long id) {
		Assert.notNull(id, "object id is null");
//		itemMapper.delete(id);
	}
	
	@Override
	public List<Item> getChildItemByParentId(Long parentId) {
		Assert.notNull(parentId, "object parentId is null");
		List<Item> list = itemListCache.get(ITEM_LIST_CACHE_KEY + parentId);
		if (CollectionUtils.isEmpty(list)) {
			ItemQuery query = new ItemQuery();
			query.setParentId(parentId);
			query.setStatus(1);
			list = itemMapper.queryList(query);
			if (CollectionUtils.isNotEmpty(list)) {
				itemListCache.put(ITEM_LIST_CACHE_KEY + parentId, new ItemList(list));
			}
		}
		return list;
	}

	@Override
	public Map<String, String> getItemNameAndParentName(Long id) {
		
		Map<String,String> name = new HashMap<>();
		
		Item i = this.load(id);
		if(i!=null && i.getLevel()==3){
			//说明最后一级
			name.put("three", i.getName());
			Item second = this.load(i.getParentId());
			if(second!=null && second.getLevel()==2){
				name.put("second", second.getName());
			}
			Item first = this.load(second.getParentId());
			if(first!=null && first.getLevel()==1){
				name.put("first", first.getName());
			}
		}
		
		return name;
	}
}
