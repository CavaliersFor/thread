package com.lunjar.ebp.bizsupport.test.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.exception.BizSupportException;
import com.lunjar.ebp.bizsupport.model.Item;
import com.lunjar.ebp.bizsupport.service.ItemService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class ItemServiceImpTest extends BizTestSupport {
	protected static final Logger logger = LoggerFactory.getLogger(ItemServiceImpTest.class);

	@Autowired
	private ItemService itemService;

	@Test
	public void add() throws ServiceException {
//		String url = "http://che.taodianzhang.net/TopProxyV2/rest";
//		String appkey = "13584125";
//		String secret = "45eb589e6c9aa54eefh658bf468955kee";
//		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
//		ItemcatsGetRequest req = new ItemcatsGetRequest();
//		req.setFields("cid,parent_cid,name,is_parent");
//		req.setParentCid(0L);
//		ItemcatsGetResponse rsp = client.execute(req);
//		System.out.println(rsp.getBody());
//		List<ItemCat> list = rsp.getItemCats();
//		if (CollectionUtils.isNotEmpty(list)) {
//			Item item = null;
//			Long level1Id = null;
//			Long level2Id = null;
//			for (ItemCat it : list) {
//				item = new Item();
//				item.setGmtCreate(new Date());
//				item.setGmtModify(new Date());
//				item.setStatus(1);
//				item.setName(it.getName());
//				item.setLevel(1);
//				item.setParentId(0l);
//				level1Id = itemService.add(item);
//				if (it.getIsParent()) {
//					req.setParentCid(it.getCid());
//					rsp = client.execute(req);
//					List<ItemCat> list2 = rsp.getItemCats();
//					if (CollectionUtils.isNotEmpty(list2)) {
//						for (ItemCat c : list2) {
//							item = new Item();
//							item.setGmtCreate(new Date());
//							item.setGmtModify(new Date());
//							item.setStatus(1);
//							item.setName(c.getName());
//							item.setLevel(2);
//							item.setParentId(level1Id);
//							level2Id = itemService.add(item);
//							if (c.getIsParent()) {
//								req.setParentCid(c.getCid());
//								rsp = client.execute(req);
//								List<ItemCat> list3 = rsp.getItemCats();
//								if (CollectionUtils.isNotEmpty(list3)) {
//									for (ItemCat ca : list3) {
//										item = new Item();
//										item.setGmtCreate(new Date());
//										item.setGmtModify(new Date());
//										item.setStatus(1);
//										item.setName(ca.getName());
//										item.setLevel(3);
//										item.setParentId(level2Id);
//										itemService.add(item);
//									}
//								}
//							}
//						}
//					}
//				}
//
//				Long id = itemService.add(item);
//				logger.info("产品类目id为：：：：" + id);
//			}
//
//		}
	}
	 @Test
	 public void getList() throws BizSupportException {
		 Long parentId = 0l;
		 List<Item> list = itemService.getChildItemByParentId(parentId);
		 if (CollectionUtils.isNotEmpty(list)) {
			for(Item it: list) {
				System.out.println("name: " +it.getName() + "--id:" + it.getId() + "--level: " + it.getLevel() + "--parentId: " + it.getParentId());
			}
		}
	 }
	
	 @Test
	 public void load() throws BizSupportException {
	 Item item = itemService.load(1L);
	 logger.info(item.toString());
	 }
	
}
