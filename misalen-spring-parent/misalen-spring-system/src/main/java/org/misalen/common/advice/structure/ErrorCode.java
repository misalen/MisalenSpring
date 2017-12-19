package org.misalen.common.advice.structure;

/**
 * 状态码+信息
 * 
 * @author guochao
 *
 */
public enum ErrorCode {

	SYS_ERROR_CODE_404(404, "找不到页面"), //
	SYS_ERROR_CODE_500(500, "内部服务器错误"), //
	
	SYS_ERROR_CODE_600(600, "请求filter校验失败"), //
	SYS_ERROR_CODE_601(600, "无操作权限"), //
	
	SYS_ERROR_CODE_700(700, null), //
	SYS_ERROR_CODE_1000(1000, null), //

	ERROR_CODE_SUCCESS(200, "ok");//

	public int value;
	public String message;

	ErrorCode(int value, String message) {
		this.value = value;
		this.message = message;
	}

}
