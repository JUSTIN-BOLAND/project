package com.deyi.service;

import java.util.List;

import com.deyi.entity.Disparam;

public interface DisparamService {
	 List<Disparam> getDisparams(String storeId);

	void update(Disparam sysparam);
}
