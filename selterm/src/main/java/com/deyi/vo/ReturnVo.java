package com.deyi.vo;

import java.io.Serializable;

/**
 * 移动端返回值通用类型
 * @author jimyang
 *
 * @param <T>
 */
public class ReturnVo<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean success = true;
	
	private String message;
	
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReturnVo [success=" + success + ", message=" + message + ", data=" + data + "]";
	}
	
	
}
