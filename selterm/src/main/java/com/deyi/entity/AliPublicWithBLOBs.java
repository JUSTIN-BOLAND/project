package com.deyi.entity;

import java.io.Serializable;

public class AliPublicWithBLOBs extends AliPublic  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2851865485559209874L;

	private String pkey;

    private String publicKey;

    private String aliPublicKey;
    
    private String audit;
    
    
    public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey == null ? null : pkey.trim();
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey == null ? null : publicKey.trim();
    }

    public String getAliPublicKey() {
        return aliPublicKey;
    }

    public void setAliPublicKey(String aliPublicKey) {
        this.aliPublicKey = aliPublicKey == null ? null : aliPublicKey.trim();
    }
}