package cn.saobei.entity.request.pay;

import java.io.Serializable;

public class MerNotifyVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1073077769104582854L;
	
	private String return_code;
	private String return_msg;
	private String trace_no;
	/**
	 * @return the return_code
	 */
	public String getReturn_code() {
		return return_code;
	}
	/**
	 * @param return_code the return_code to set
	 */
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	/**
	 * @return the return_msg
	 */
	public String getReturn_msg() {
		return return_msg;
	}
	/**
	 * @param return_msg the return_msg to set
	 */
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	/**
	 * @return the trace_no
	 */
	public String getTrace_no() {
		return trace_no;
	}
	/**
	 * @param trace_no the trace_no to set
	 */
	public void setTrace_no(String trace_no) {
		this.trace_no = trace_no;
	}
	
	

}
