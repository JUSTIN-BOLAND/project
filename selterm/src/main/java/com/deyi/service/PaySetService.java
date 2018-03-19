package com.deyi.service;

import com.deyi.entity.AliPublicWithBLOBs;
import com.deyi.entity.UnionPublic;
import com.deyi.entity.WxPublic;

public interface PaySetService {

	WxPublic getWxPublicById(String id);

	void updateWxPublic(WxPublic wxPublic);

	void saveWxPublic(WxPublic wxPublic);

	WxPublic getWxPublicByMerchId(String string);

	AliPublicWithBLOBs getAliPublicByMerchId(String string);

	void updateAliPublic(AliPublicWithBLOBs aliPublicWithBLOBs);

	void saveAliPublic(AliPublicWithBLOBs aliPublicWithBLOBs);

	/**
	 * 获取机构阿里参数
	 * @param orgid
	 * @return
	 */
	AliPublicWithBLOBs queryorgAlipublic(String orgid);

	WxPublic getWxPublicByorgid(String id);

	void updateUnionPublic(UnionPublic unionPublic);

	void saveUnionPublic(UnionPublic unionPublic);

	UnionPublic getUnionPublicByorgid(String id);

	UnionPublic getUnionPublicByMerchId(String string);

}
