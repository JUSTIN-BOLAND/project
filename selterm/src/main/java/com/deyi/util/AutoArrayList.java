package com.deyi.util;

import java.util.ArrayList;

/**
 * 
 * <p>Title: 自动增长ArrayList</p>
 * <p>Description: 页面多行数据提交到Controller时，用于封装多行数据</p>
 * <p>Copyright:Copyright(c)2014</p>
 * <p>Company:MOPON</p>
 * @date 2014-3-18
 * @author tongbiao
 * @version 1.0
 */

@SuppressWarnings("rawtypes")
public class AutoArrayList extends ArrayList {

	private static final long serialVersionUID = 5332296994651370661L;
	private Class classItem;

	public AutoArrayList(Class classItem) {
		this.classItem = classItem;
	}

	@SuppressWarnings("unchecked")
	public Object get(int index) {
		try {
			while (index >= size()) {
				add(classItem.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.get(index);
	}

}
