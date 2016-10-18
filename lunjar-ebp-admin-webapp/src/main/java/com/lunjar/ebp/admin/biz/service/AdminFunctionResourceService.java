package com.lunjar.ebp.admin.biz.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunjar.ebp.admin.biz.jdbc.AdminUserRoleJdbc;
import com.lunjar.ebp.admin.domain.model.AdminAgent;
import com.lunjar.ebp.admin.domain.model.FunctionResource;
import com.lunjar.products.core.utils.XmlUtils;

/**
 * 功能资源服务类
 * 
 * @author <a href="mailto:shenwei@ancun.com">ShenWei</a> created at 2010-11-28
 */
@Service
public class AdminFunctionResourceService {
	private static Logger log = LoggerFactory.getLogger(AdminFunctionResourceService.class);

	private static String functionResourceXmlFile = "functionResource.xml";

	@Autowired
	private AdminUserRoleJdbc aminUserRoleDao;

	private static Document doc = null;
	/**
	 * functionResource.xml文件中的根元素
	 */
	private static Element root = null;

	@PostConstruct
	public void init() {// 初始化时一次性加载文件functionResource.xml中的资源
		if (log.isDebugEnabled()) {
			log.debug("Init function resource xml file: [" + functionResourceXmlFile + "]");
		}

		doc = XmlUtils.getDocument(functionResourceXmlFile);
		root = doc.getRootElement();
	}

	
	public List<FunctionResource> getAllResource() {
		FunctionResource rootResource = new FunctionResource();
		putDirectlyChildResource(rootResource, root, null);

		return rootResource.getChildren();
	}

	
	public List<FunctionResource> getUserResource(AdminAgent adminUser) {
		Set<String> userFunctionIds = null;
		if (!adminUser.isSystemAdmin()) {
			userFunctionIds = aminUserRoleDao.getUserFunctions(adminUser.getId());
		}
		FunctionResource rootResource = new FunctionResource();
		putDirectlyChildResource(rootResource, root, userFunctionIds);

		return rootResource.getChildren();
	}

	
	public List<FunctionResource> getUserMainResource(AdminAgent adminUser) {
		Set<String> userFunctionIds = null;
		if (!adminUser.isSystemAdmin()) {
			aminUserRoleDao.getUserFunctions(adminUser.getId());
		}
		return getChildByElement(root, userFunctionIds);
	}

	private List<FunctionResource> getChildByElement(Element parentElement, Set<String> userFunctionIds) {
		List<FunctionResource> children = new ArrayList<FunctionResource>();

		Element element = null;
		FunctionResource functionResource = null;
		for (Iterator<?> i = parentElement.elementIterator(); i.hasNext();) {// 循环Element
			element = (Element) i.next();

			if (userFunctionIds != null) {
			}

			functionResource = elementToFunctionResource(element);

			children.add(functionResource);
		}

		return children;
	}

	private FunctionResource elementToFunctionResource(Element element) {
		FunctionResource functionResource;
		functionResource = new FunctionResource();
		for (Iterator<?> j = element.attributeIterator(); j.hasNext();) {
			Attribute attribute = (Attribute) j.next();
			// 写入FunctionResource
			try {
				BeanUtils.setProperty(functionResource, attribute.getName(), attribute.getValue());
			} catch (IllegalAccessException e) {
				if (log.isWarnEnabled()) {
					log.warn("属性[" + attribute.getName() + "]赋值[" + attribute.getValue()
							+ "]出错,请确定是否有对应的set方法，并且方法是public", e);
				}
			} catch (InvocationTargetException e) {
				if (log.isWarnEnabled()) {
					log.warn("属性[" + attribute.getName() + "]赋值[" + attribute.getValue() + "]出错", e);
				}
			}
		}
		return functionResource;
	}

	/**
	 * 根据Element设置它的下属FunctionResource
	 * 
	 * @param parentElement
	 *            根节点
	 * @param isAboutAction
	 *            是否包括action节点
	 * @return
	 */
	private void putDirectlyChildResource(FunctionResource parentResource, Element parentElement,
			Set<String> userFunctionIds) {
		parentResource.setLeaf(true);

		if (parentElement != null && parentElement.hasContent()) {
			List<FunctionResource> children = new ArrayList<FunctionResource>();
			List<FunctionResource> actions = new ArrayList<FunctionResource>();

			Element element = null;
			FunctionResource functionResource = null;

			// 循环Element
			for (Iterator<?> i = parentElement.elementIterator(); i.hasNext();) {
				element = (Element) i.next();

				boolean isPut = true;
				if (userFunctionIds != null) {
					Attribute attr = element.attribute("id");
					isPut = userFunctionIds.contains(attr.getValue());
				}
				if (isPut) {
					functionResource = elementToFunctionResource(element);

					if (StringUtils.equalsIgnoreCase("action", element.getName())) {// 判断是否action
						functionResource.setAction(true);
						actions.add(functionResource);
					} else {
						if (functionResource.getAlias() == null || StringUtils.isEmpty(functionResource.getAlias())) {
							functionResource.setAlias(functionResource.getText());
						}
						children.add(functionResource);
						parentResource.setLeaf(false);
						putDirectlyChildResource(functionResource, element, userFunctionIds);
					}
				}
			}

			parentResource.setActions(actions);
			parentResource.setChildren(children);
		}
	}

}
