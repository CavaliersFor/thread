package com.ancun.bps.portal.partner.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import com.ancun.bps.portal.partner.support.ApiTestCase;

public class ProudctControllerTest extends ApiTestCase{
	private static final Logger logger = LoggerFactory.getLogger(ProudctControllerTest.class);
	private static final String PARTNER_KEY = "a335f64fdba2bd964f54bd2d019335d6";
	
    
	@Test
	public void getInfo() throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(SERVICE_URL + "product/getInfo");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("id", "1"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, "UTF-8");
		httpPost.setEntity(entity);
		setHeaders(httpPost, PARTNER_KEY, MediaType.APPLICATION_FORM_URLENCODED_VALUE, 0, "");
		callApi(httpPost);
	}
	
}
