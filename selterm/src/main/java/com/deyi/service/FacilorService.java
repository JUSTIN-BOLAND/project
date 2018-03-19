/**
 *
 */
package com.deyi.service;

import com.deyi.entity.Facilor;

/**
 * pure  dey服务商配置项 只有一条数据
 * @author hejx
 * @2016年3月19日
 */
public interface FacilorService {

	public Facilor queryDyFacilor();
	Facilor getWxFacilor();
	Facilor getAliFacilor();

	void update(Facilor facilor);
	void save(Facilor facilor);

}
