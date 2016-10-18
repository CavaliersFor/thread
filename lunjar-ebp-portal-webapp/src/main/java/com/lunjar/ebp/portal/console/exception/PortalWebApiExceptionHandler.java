package com.lunjar.ebp.portal.console.exception;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.webapi.LunjarApiResponse;


/**
 * 门户接口异常统一处理
 * @author shenwei@ancun.com
 * 2016年5月14日
 * 
 */
@ControllerAdvice
public class PortalWebApiExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(PortalWebApiExceptionHandler.class);

	@ExceptionHandler(value = ServiceException.class)
	public final ResponseEntity<?> handleServiceException(ServiceException ex, WebRequest request) {
		log.warn(ex.getMessage());
	
		LunjarApiResponse res = new LunjarApiResponse();

		res.setCode(ex.getCode());
		res.setMsg(ex.getMessage());

		ResponseEntity<LunjarApiResponse> responseEntity = new ResponseEntity<LunjarApiResponse>(res, HttpStatus.OK);	

		return responseEntity;
	}

	@ExceptionHandler(value = Exception.class)
	public final ResponseEntity<?> handleExceptionExtent(Exception ex, WebRequest request) {
		log.error(ex.getMessage(), ex);

		LunjarApiResponse res = new LunjarApiResponse();

		res.setCode(1000001);
		res.setMsg("系统繁忙，请稍候再试");

		ResponseEntity<LunjarApiResponse> responseEntity = new ResponseEntity<LunjarApiResponse>(res, HttpStatus.OK);

		return responseEntity;
	}

	@ExceptionHandler(value = BpsIllegalArgumentException.class)
	public final ResponseEntity<?> handleExceptionExtent(BpsIllegalArgumentException ex, WebRequest request) {
		log.error(ex.getMessage());

		LunjarApiResponse res = new LunjarApiResponse();

		res.setCode(1000001);
		res.setMsg("接口参数错误【"+ex.getMessage()+"】");

//		ResponseEntity<AncunApiResponse> responseEntity = new ResponseEntity<AncunApiResponse>(res, HttpStatus.NOT_ACCEPTABLE);
		ResponseEntity<LunjarApiResponse> responseEntity = new ResponseEntity<LunjarApiResponse>(res, HttpStatus.OK);

		return responseEntity;
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
		if (!CollectionUtils.isEmpty(mediaTypes)) {
			headers.setAccept(mediaTypes);
		}

		return handleExceptionInternal(ex, null, headers, status, request);
	}
}

