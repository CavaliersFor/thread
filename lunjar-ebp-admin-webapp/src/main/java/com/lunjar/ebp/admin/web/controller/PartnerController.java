package com.lunjar.ebp.admin.web.controller;
//package com.ancun.bps.admin.web.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.ancun.bps.admin.domain.enums.EnumAuditStatus;
//import com.ancun.bps.admin.domain.enums.EnumAuditType;
//import com.ancun.bps.admin.domain.enums.EnumAuditTypeStatus;
//import com.ancun.bps.admin.domain.enums.EnumPartnerType;
//import com.ancun.bps.admin.domain.model.AdminAgent;
//import com.ancun.bps.admin.web.session.AdminAgentSession;
//import com.ancun.bps.bizsupport.enums.EnumAuditTargetTable;
//import com.ancun.bps.bizsupport.exception.BizSupportException;
//import com.ancun.bps.bizsupport.model.Audit;
//import com.ancun.bps.bizsupport.model.Partner;
//import com.ancun.bps.bizsupport.model.Product;
//import com.ancun.bps.bizsupport.query.PartnerQuery;
//import com.ancun.bps.bizsupport.query.ProductQuery;
//import com.ancun.bps.bizsupport.service.AuditService;
//import com.ancun.bps.bizsupport.service.PartnerService;
//import com.ancun.bps.bizsupport.service.ProductService;
//import com.ancun.bps.usercenter.model.UcEnterprise;
//import com.ancun.bps.usercenter.model.UcPersonal;
//import com.ancun.bps.usercenter.model.UcUser;
//import com.ancun.bps.usercenter.service.UcEnterpriseService;
//import com.ancun.bps.usercenter.service.UcPersonalService;
//import com.ancun.bps.usercenter.service.UcUserService;
//import com.ancun.products.core.exception.ServiceException;
//import com.ancun.products.core.model.PageResult;
//import com.ancun.products.core.web.annotation.QueryPage;
//import com.ancun.products.core.web.exception.WebException;
//
///**
// * <p/>
// * create at 2016年3月28日 下午8:27:06
// *
// * @author <a href="mailto:caozhenfei@ancun.com">CaoZhenfei</a>
// * @version %I%, %G%
// * @see
// */
//@Controller
//@RequestMapping(value = "partner")
//public class PartnerController extends AdminControllerSupport{
//    private static final Logger logger = LoggerFactory.getLogger(PartnerController.class);
//
//    @Autowired
//    private AdminAgentSession adminAgentSession;
//    @Autowired
//    private PartnerService partnerService;
////    @Autowired
////    private UcEnterpriseService ucEnterpriseService;
//    @Autowired
//    private UcPersonalService ucPersonalService;
//    @Autowired
//    private UcUserService ucUserService;
//    @Autowired
//    private AuditService auditService;
//    @Autowired
//    private ProductService productService;
//
//     /**
//      * 跳转到接入者审核页面
//      * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/13 9:57
//      * @version %I%,%G%
//      * @see
//      */
//    @RequestMapping(value = "/list")
//    public String list(Model model,@QueryPage(defaultPageSize = 10)  PartnerQuery partnerQuery) throws WebException, BizSupportException {
//        PageResult<Partner> page = partnerService.getPageList(partnerQuery);
//        model.addAttribute("page", page);
//        model.addAttribute("query", partnerQuery);
//
//        return "partner/list";
//    }
//
//     /**
//      * 跳转到详情页面
//      * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/13 9:57
//      * @version %I%,%G%
//      * @see
//      */
//    @RequestMapping(value = "detail")
//    public String detail(Model model,Long uid, Integer partnerId) throws ServiceException {
//        logger.info("uid【{}】,partnerId【{}】",uid,partnerId);
//        
//        if(uid!=null){
//        	UcUser ucUser=ucUserService.queryUcUserById(uid);
//            if(ucUser==null){
//            	Partner partner = partnerService.load(partnerId); 
//            	Integer type=partner.getType();
//            	model.addAttribute("partner", partner);
//            	if(type==1){
//            		 model.addAttribute("type", EnumAuditType.PERSONAL.getValue());
//            	}
//            	if(type==2){
//            		model.addAttribute("type", EnumAuditType.ENERPRISEAUDIT.getValue());
//            	}
//            	return "partner/detail";
//            }
//            if (EnumPartnerType.ENTERPRISE.getValue()==ucUser.getUserType()) {
//                UcEnterprise ucEnterprise = ucEnterpriseService.getUcEnterpriseByUserId(uid);
//                if(ucEnterprise==null){
//                    logger.info("企业用户信息错误:找不到企业信息,uid【{}】",uid);
//                    
//                }
//                String photoUrl1 = ucEnterprise.getPhotoUrl1();
//                String photoUrl2 = ucEnterprise.getPhotoUrl2();
//
//                convertEnterprisePhotoUrl1(photoUrl1,"photoUrl1",model);
//                convertEnterprisePhotoUrl2(photoUrl2,"photoUrl2",model);
//                convertOriginPictureUrl(photoUrl1,"originPhotoUrl1",model);
//                convertOriginPictureUrl(photoUrl2,"originPhotoUrl2",model);
//
//                model.addAttribute("enterprise", ucEnterprise);
//                model.addAttribute("type", EnumAuditType.ENERPRISEAUDIT.getValue());
//            } else if(ucUser.getUserType() == EnumPartnerType.PERSONAL.getValue()) {
//                UcPersonal ucPersonal = ucPersonalService.getUcPersonalByUserId(uid);
//                if(ucPersonal==null){
//                    logger.info("个人用户信息错误:找不到个人信息uid【{}】",uid);
//                    
//                }
//
//                String headsPhotoUrl = ucPersonal.getHeadsPhotoUrl();
//                String tailsPhotoUrl = ucPersonal.getTailsPhotoUrl();
//
//                convertPersonalHeadsPhotoUrl(headsPhotoUrl,"photoUrl1",model);
//                convertPersonalTailsPhotoUrl(tailsPhotoUrl,"photoUrl2",model);
//                convertOriginPictureUrl(headsPhotoUrl,"originPhotoUrl1",model);
//                convertOriginPictureUrl(tailsPhotoUrl,"originPhotoUrl2",model);
//
//                model.addAttribute("personal", ucPersonal);
//                model.addAttribute("type", EnumAuditType.PERSONAL.getValue());
//            }else{
//                
//            }
//
//            // 获取实名审核信息
//            Audit audit = auditService.load(partnerId, EnumAuditTypeStatus.PARTNER.getText());
//            if(audit==null){
//                audit=new Audit();
//                audit.setAuditStatus(EnumAuditStatus.NOT_AUDIT.getValue());
//            }
//
//            // 获取接入者信息
//            Partner partner = partnerService.load(partnerId);
//
//            model.addAttribute("audit", audit);
//            model.addAttribute("uid", uid);
//            model.addAttribute("partner", partner);
//            model.addAttribute("user", ucUser);
//            return "partner/detail";
//        }
//        else{
//        	Partner partner = partnerService.load(partnerId);
//        	Integer type=partner.getType();
//        	model.addAttribute("partner", partner);
//        	if(type==1){
//        		 model.addAttribute("type", EnumAuditType.PERSONAL.getValue());
//        	}
//        	if(type==2){
//        		model.addAttribute("type", EnumAuditType.ENERPRISEAUDIT.getValue());
//        	}
//        	return "partner/detail";
//        }
//
//        
//
//    }
//
//     /**
//      * 跳转到审核页面
//      * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/13 9:56
//      * @version %I%,%G%
//      * @see
//      */
//    @RequestMapping(value = "/audit", method = RequestMethod.POST)
//    public String aduit(Audit audit, HttpServletRequest request) throws WebException {
//        Assert.notNull(audit);
//        Assert.notNull(audit.getTargetTable());
//        Assert.notNull(audit.getTargetTableId());
//        Assert.notNull(audit.getType());
//
//        audit.setTargetTable(EnumAuditTargetTable.PARTNER.getText());
//
//		// 获取管理员名称
//		AdminAgent adminAgent = adminAgentSession.get(request);
//		audit.setAuditName(adminAgent.getUserName());
//
//		auditService.audit(audit);
//		return "redirect:/partner/list";
//    }
//
//    @RequestMapping(value = "detail-product-list")
//    public String list(@QueryPage(defaultPageSize = 10) ProductQuery productQuery,Model model) throws WebException, BizSupportException{
//        Assert.notNull(productQuery);
//        Assert.notNull(productQuery.getPartnerId());
//
//        PageResult<Product> page = productService.queryPartnerProductPage(productQuery);
//        model.addAttribute("page", page);
//        return "/partner/partner-detail-product-list";
//    }
//
//}
