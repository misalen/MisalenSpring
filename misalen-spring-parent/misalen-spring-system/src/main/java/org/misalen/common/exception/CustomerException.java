package org.misalen.common.exception;

import org.misalen.common.advice.structure.ErrorCode;
import org.misalen.common.advice.structure.RestResult;

public class CustomerException extends RuntimeException {

	private RestResult<?> restResult;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerException(ErrorCode code) {
		this.restResult = RestResult.error(code);
	}

	public CustomerException(ErrorCode code, String message) {
		this.restResult = RestResult.error(code, message);
	}

	public RestResult<?> getRestResult() {
		return restResult;
	}
}
