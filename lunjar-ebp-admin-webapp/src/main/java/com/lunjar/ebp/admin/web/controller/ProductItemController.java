package com.lunjar.ebp.admin.web.controller;
//package com.lunjar.demo.web.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ancun.bps.bizsupport.model.ProductItem;
//import com.ancun.bps.bizsupport.service.ProductItemService;
//import com.ancun.products.core.web.exception.WebException;
//import com.ancun.products.core.webapi.AncunApiResponse;
//
//@Controller
//@RequestMapping(value = "product-item")
//public class ProductItemController {
//    private static final Logger logger = LoggerFactory.getLogger(ProductItemController.class);
//    @Autowired
//    private ProductItemService productItemService;
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    @ResponseBody
//    public AncunApiResponse updateItem(@RequestParam("id")Integer id, @RequestParam("status")Integer status, @RequestParam("type")Integer type) throws WebException {
//        if (type == 1) {//修改保全点状态
//			productItemService.updateStatus(id, status);
//		}else if (type == 2) {//开通/关闭个人章
//			ProductItem productItem = new ProductItem();
//			productItem.setId(id);
//			productItem.setIsOpenPersonSeal(status);
//			productItemService.update(productItem);
//		}
//        return AncunApiResponse.create(id);
//    }
//}
