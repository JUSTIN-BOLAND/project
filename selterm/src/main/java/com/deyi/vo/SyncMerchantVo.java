package com.deyi.vo;

import com.deyi.entity.Merchant;
import com.deyi.entity.OthMerchant;

public class SyncMerchantVo {
	
	private Merchant merchant;
	
	private OthMerchant othMerchant;

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public OthMerchant getOthMerchant() {
		return othMerchant;
	}

	public void setOthMerchant(OthMerchant othMerchant) {
		this.othMerchant = othMerchant;
	}

}
