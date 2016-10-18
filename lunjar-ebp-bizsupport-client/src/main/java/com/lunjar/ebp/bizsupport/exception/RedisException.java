package com.lunjar.ebp.bizsupport.exception;

public class RedisException extends Exception {

	private static final long serialVersionUID = -3395942493833704494L;
	
	public RedisException() {
		super();
	}

	public RedisException(String message, Throwable cause) {
		super(message, cause);
	}

	public RedisException(String message) {
		super(message);
	}

	public RedisException(Throwable cause) {
		super(cause);
	}

}
