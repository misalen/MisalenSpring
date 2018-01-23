package org.misalen.common.advice.structure;

/**
 * server自定义响应结构
 */
public class RestResult<T> {
	private boolean success;
	private int errorCode;

	private String message;

	private T data;

	public boolean isSuccess() {
		return success;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	private RestResult(int errorCode, String message, T data) {
		this.errorCode = errorCode;
		if (errorCode == 200) {
			success = true;
		} else {
			success = false;
		}
		this.message = message;
		this.data = data;
	}

	/**
	 * 错误
	 * 
	 * @param code
	 * @return
	 */
	public static <T> RestResult<T> error(ErrorCode code) {
		return new RestResult<T>(code.value, code.message, null);
	}

	/**
	 * 错误
	 * 
	 * @param code
	 * @param data
	 * @return
	 */
	public static <T> RestResult<T> error(String msg) {
		return new RestResult<T>(ErrorCode.SYS_ERROR_CODE_1000.value, msg, null);
	}
	/**
	 * 错误
	 * 
	 * @param code
	 * @param data
	 * @return
	 */
	public static <T> RestResult<T> error(ErrorCode code,String msg) {
		return new RestResult<T>(code.value, msg, null);
	}

	/**
	 * 成功
	 * 
	 * @param data
	 * @return
	 */
	public static <T> RestResult<T> ok(T data) {
		return new RestResult<T>(ErrorCode.ERROR_CODE_SUCCESS.value, null, data);
	}

	/**
	 * 成功
	 * 
	 * @return
	 */
	public static <T> RestResult<T> ok() {
		return new RestResult<T>(ErrorCode.ERROR_CODE_SUCCESS.value, null, null);
	}

}
