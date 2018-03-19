package com.deyi.dao;

import com.deyi.entity.Store;
import com.deyi.util.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("storeDao")
public interface StoreDao {
	Store getStoreById(Integer id);

	List<Store> getStores(Store store);

	void addStore(Store store);

	void upadeStore(Store store);

	int deleteStore(String id);

	List<String> getStoreIdsByMerId(Integer id);

	List<Store> getStoresByPage(Page<Store> page);

	List<Store> selectByMerchant(String merId);//根据商户id查门店

	List<Store> selectByMerchantIds(@Param("merids") String merIds);
	Store getStoreByCode(String storeCode);

	void loadStoreFile(String fileName) throws Exception;

	Store bankQueryStoreByCode(String storeCode);
	List<Store> getStoreByName(String storeName);
	void upadeStoreByCode(Store store);
	int upadeStoreByMerId(Store store);
}
