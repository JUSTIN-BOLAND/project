package com.deyi.dao;

import org.springframework.stereotype.Repository;

import com.deyi.entity.DisClerk;

@Repository("disClerkDao")
public interface DisClerkDao {
	DisClerk getDisClerkById(String id);

}
