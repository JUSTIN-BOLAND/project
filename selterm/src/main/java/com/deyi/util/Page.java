package com.deyi.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对分页的基本数据进行一个简单的封装
 */
public class Page<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3037566731443030035L;
	private int pageNo = 1;// 页码，默认是第一页
	private int pageSize = 10;// 每页显示的记录数，默认是10
	private int totalRecord;// 总记录数
	private int totalPage;// 总页数
	private List<T> results;// 对应的当前页记录
	private String url;// 请求URL
	private String orderBy;
	private boolean desc = false;
	private Map<String, Object> params = new HashMap<String, Object>();// 其他的参数我们把它分装成一个Map对象

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		// this.totalRecord = totalRecord;
		// 在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
		int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
		this.setTotalPage(totalPage);

		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;

		if (totalRecord == 0)
			this.totalRecord = results.size();
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isDesc() {
		return desc;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageNo=").append(pageNo).append(", pageSize=").append(pageSize).append(", results=")
				.append(results).append(", totalPage=").append(totalPage).append(", totalRecord=").append(totalRecord)
				.append("]");
		return builder.toString();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}