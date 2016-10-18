package com.lunjar.ebp.admin.web.controller;
//package com.lunjar.demo.web.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ancun.bps.bizsupport.dto.PartnerAccountDto;
//import com.ancun.bps.bizsupport.enums.EnumPartnerType;
//import com.ancun.bps.bizsupport.query.PartnerQuery;
//import com.ancun.bps.bizsupport.service.AuditService;
//import com.ancun.bps.bizsupport.service.PartnerService;
//import com.ancun.products.core.exception.ServiceException;
//import com.ancun.products.core.model.PageResult;
//import com.ancun.products.core.web.annotation.QueryPage;
//import com.ancun.products.core.webapi.AncunApiResponse;
//
///**
// * 实名验证
// * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/6/12
// * @version %I%,%G%
// * @see
// */
//@Controller
//@RequestMapping(value = "realnamecertification")
//public class RealnameCertificationController extends AdminControllerSupport{
//    private static final Logger logger = LoggerFactory.getLogger(RealnameCertificationController.class);
//
//    private static final String URI_PREFIX="realnamecertification/realname-certification-";
//
//    @Autowired
//    private PartnerService partnerService;
//    @Autowired
//    private AuditService auditService;
//
//    @RequestMapping(value = "/list")
//    public String list(Model model,@QueryPage(defaultPageSize = 10)  PartnerQuery partnerQuery) throws ServiceException {
//        Assert.notNull(partnerQuery);
//        Integer type = partnerQuery.getType();
//        Assert.notNull(type);
//
//        String account = partnerQuery.getAccount();
//        Integer status = partnerQuery.getStatus();
//        String partnerName = partnerQuery.getPartnerName();
//        logger.info("account【{}】,type【{}】,partnerName【{}】,status【{}】", account,type, partnerName,status);
//
//        PageResult<PartnerAccountDto> page = partnerService.queryRealnameCertificationList(partnerQuery);
//        model.addAttribute("page", page);
//        model.addAttribute("query", partnerQuery);
//
//        if (EnumPartnerType.PERSONAL.getCode().equals(type)){
//            return URI_PREFIX+"personal-list";
//        }else {
//            return URI_PREFIX+"enterprise-list";
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping("audit")
//    public AncunApiResponse audit(Integer partnerId,Integer status,String auditReason) throws ServiceException {
//        Assert.notNull(partnerId);
//        Assert.notNull(status);
//        logger.info("partnerId【{}】,status【{}】,auditReason【{}】",partnerId,status,auditReason);
//
//        auditService.auditPartner(partnerId,status,auditReason);
//
//        return AncunApiResponse.create();
//    }
//
//}
