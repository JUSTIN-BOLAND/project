/**
 *
 */
package com.deyi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyi.dao.FacilorMapper;
import com.deyi.entity.Facilor;
import com.deyi.service.FacilorService;

/**
 * pure
 * @author hejx
 * @2016年3月19日
 */
@Service
public class FacilorServiceImpl implements FacilorService{

	@Autowired
	private FacilorMapper facilorMapper;

	@Override
	public Facilor queryDyFacilor() {
		return facilorMapper.selectByPrimaryKey(Long.valueOf(1));
	}

	@Override
	public Facilor getWxFacilor() {
		return facilorMapper.selectByPrimaryKey(Long.valueOf(1));
	}

	@Override
	public Facilor getAliFacilor() {
		return facilorMapper.selectByPrimaryKey(Long.valueOf(2));
	}
	@Override
	public void update(Facilor facilor){
		this.facilorMapper.updateByPrimaryKey(facilor);
	}
	@Override
	public void save(Facilor facilor){
		this.facilorMapper.insert(facilor);
	}




}
