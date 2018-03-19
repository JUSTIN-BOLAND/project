/**
 * 
 */
package com.deyi.exception;

/**
 * pure
 * @author hejx
 * @2016年3月19日
 */
public class PayException  extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8202773662104507220L;

	/**
	 * 构造方法。
	 * 
	 * @param message
	 *            异常信息
	 */
	public PayException(String message) {
		super(message);
	}

	/**
	 * 构造方法。
	 * 
	 * @param cause
	 *            异常原因
	 */
	public PayException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造方法。
	 * 
	 * @param message
	 *            异常信息
	 * @param cause
	 *            异常原因
	 */
	public PayException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public static void raise(String msg) {
		throw new PayException(msg);
	}

}
