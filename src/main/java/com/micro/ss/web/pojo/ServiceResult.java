package com.micro.ss.web.pojo;

/**
 * @author micro
 * @date 2017年7月5日
 * @description :
 */
public class ServiceResult<T> {
	private boolean isSuccess;
	private String msg;
	private T data;

	public boolean isSuccess() {
		return isSuccess;
	}
	
	public static <T> ServiceResult<T> getSuccess(T data) {
		ServiceResult<T> result = new ServiceResult<T>();
		result.setSuccess(true);
		result.setData(data);
		return result;
	}
	
	public static <T> ServiceResult<T> getErrorResult(String msg) {
		ServiceResult<T> result = new ServiceResult<T>();
		result.setSuccess(false);
		result.setMsg(msg);
		return result;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
