package com.ancun.bps.admin.test.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ancun.bps.core.region.BpsRegionService;
import com.ancun.bps.core.region.cache.RegionCacheForData;
import com.ancun.bps.core.region.cache.RegionCacheForName;
import com.ancun.bps.core.region.cache.RegionCacheForTree;
import com.ancun.bps.core.region.exception.RegionExistsException;
import com.ancun.bps.core.region.impl.BpsRegionRemoteService;
import com.ancun.bps.core.region.model.PubRegion;
import com.ancun.bps.core.region.query.PubRegionQuery;
import com.lunjar.products.core.model.PageResult;

public class BpsRegionServiceTest extends TestSupport {
	
	@Autowired
	private BpsRegionService bpsRegionService;
	@Autowired
	private BpsRegionRemoteService bpsRegionRemoteService;
	@Autowired
	private RegionCacheForData regionCacheForData;
	@Autowired
	private RegionCacheForName regionCacheForName;
	@Autowired
	private RegionCacheForTree regionCacheForTree;
	
//	@Test
//	public void getByParentCodeJson(){
//		String parentCode="110000";
//		List<String> list =bpsRegionService.getByParentCodeJson(parentCode);
//		System.out.println(list);
//	}
//	@Test
//	public void getAllProvinceJson(){
//		List<String> list = bpsRegionService.getAllProvinceJson();
//		System.out.println(list);
//	}
	@Test
	public void getListPage(){
		PubRegionQuery query = new PubRegionQuery();
		query.setParentCode("110000");
		PageResult<PubRegion> list=bpsRegionRemoteService.getListPage(query);
		System.out.println(list);
//		regionCacheForTree.remove(key);
	}
//	@Test
//	public void updateStatus(){
//		String code="110000";
//		int newStatus=0;		
//		bpsRegionService.updateStatus(code,newStatus);
//	}
//	@Test
//	public void getByParent(){
//		String parentCode ="110000";
//		List<PubRegion> list= bpsRegionService.getByParent(parentCode);
//		System.out.println(list);
//	}
//	@Test
//	public void getChildrenCountGroupByCode(){
//		List<PubRegion> regionList=new ArrayList<PubRegion>();
//		Map<String, Integer> map=bpsRegionService.getChildrenCountGroupByCode(regionList);
//		System.out.println(map);
//	}
//	@Test
//	public void load(){
//		String regionCode="110000";
//		PubRegion pubRegion=bpsRegionService.load(regionCode);
//		System.out.println(pubRegion);
//	}
//	@Test
//	public void getChildrenByParentAndLevel(){
//		String rootCode="110000";
//		Integer level=4;
//		List<PubRegion> list= bpsRegionService.getChildrenByParentAndLevel(rootCode,level);
//		System.out.println(list);
//	}
	@Test
	public void add(){
		PubRegion region=new PubRegion();
		region.setParentCode("999900");
		region.setCode("999911");
		try {
			bpsRegionService.add(region);
		} catch (RegionExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void update(){
		PubRegion region=new PubRegion();
		region.setParentCode("999900");
		region.setCode("999911");
		String oldParentCode="999900";
		bpsRegionService.update(region, oldParentCode);
		
	}
	@Test
	public void getByCode(){
		String code="110000";
		PubRegion pubRegion=bpsRegionService.getByCode(code);
		System.out.println(pubRegion);
	}
	@Test
	public void getByParentCode(){
		String parentCode = "110000";
		List<PubRegion> list = bpsRegionService.getByParentCode(parentCode);
		System.out.println(list);
	}
}
