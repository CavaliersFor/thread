package com.ancun.bps.portal.partner.support;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

import com.lunjar.products.core.utils.JsonUtils;
import com.lunjar.products.core.webapi.LunjarApiResponse;

/**
 * <p>
 * create at 2016年3月28日 下午8:03:41
 * @author <a href="mailto:caozhenfei@ancun.com">CaoZhenfei</a>
 * @version %I%, %G%
 * @see
 */
@ContextConfiguration({ "classpath*:spring/core-bean.xml" })
public class ApiTestCase {
	private static final Logger logger = LoggerFactory
			.getLogger(ApiTestCase.class);

	protected static final String SERVICE_URL = "http://127.0.0.1:8008/";

	protected static final String CHARSET = "UTF-8";

	private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 100;
	private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = (60 * 1000);

	protected CloseableHttpClient httpClient;

	public ApiTestCase() {
		SocketConfig socketConfig = SocketConfig.custom()//
				.setSoTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS)//
				.setTcpNoDelay(true)//
				.build();

		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setDefaultSocketConfig(socketConfig);
		connManager.setSocketConfig(new HttpHost("127.0.0.1", 8008),
				socketConfig);
		connManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);// 最大连接数
		connManager.setDefaultMaxPerRoute(DEFAULT_MAX_TOTAL_CONNECTIONS);// 每个路由基础的连接,和最大连接数一样

		RequestConfig defaultRequestConfig = RequestConfig.custom()//
				.setSocketTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS)//
				.setConnectTimeout(50)//
				.setConnectionRequestTimeout(50)//
				.build();

		httpClient = HttpClients.custom()//
				.setDefaultRequestConfig(defaultRequestConfig)//
				.setConnectionManager(connManager)//
				.build();
	}

	protected void callApi(HttpPost httpPost) {
		try {
			String result = httpClient.execute(httpPost,
					new ResponseHandler<String>() {
						@Override
						public String handleResponse(HttpResponse response)
								throws ClientProtocolException, IOException {
							int statusCode = response.getStatusLine()
									.getStatusCode();
							HttpEntity entity = response.getEntity();
							String responseBody = null;
							
							if (entity != null) {
								responseBody = EntityUtils.toString(entity,
										CHARSET);
							}
							return responseBody;
						}
					});

			LunjarApiResponse aospResponse = JsonUtils.jsonToBean(result,
					LunjarApiResponse.class);

			logger.info(aospResponse.toString());

		} catch (ClientProtocolException ex) {
			logger.error("Client protocol is error", ex);
		} catch (IOException ex) {
			logger.error("Can not connect to http service ", ex);
		} finally {
			httpPost.abort();
		}
	}

	protected void setHeaders(HttpPost httpPost, String partnerKey,
			String contentType, int length, String sign) {
		httpPost.setHeader("partnerKey", partnerKey);// 接入者Key
/*		httpPost.setHeader("reqtime",
				DateUtil.dateToString(new Date(), "yyyyMMddHHmmss"));// 请求时间
*/		httpPost.setHeader("reqlength", length + "");// 请求长度
		httpPost.setHeader("sign", sign);
		httpPost.setHeader("Content-Type", contentType);
	}
}
