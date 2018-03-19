package com.deyi.entity;

import java.io.Serializable;
import java.util.Date;

public class Clerk implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5999485414150269480L;

	private String id;

    private String clerkCode;
    
    private String accountName;
    
    private Integer storeid;
    
    private String storename;

    private String clerkname;

    private String clerkjob;

    private String phone;

    private Date createtime;
    
    private String mac;
    
    private Integer merId;
	
    private String merIds;//扩展字段
    
    private String deletedStatus;//删除状态0(未删除)1(已删除)

    private String status; //1(启用)2(停用)

    
    private String imei; //android 唯一标识
    
    private String uuid; // ios 唯一标识
    
    private String 	posmac;  // pos端的mac地址
    
    private String refundstatus;
    
    
    //二清的用户采用
    private String terminal_id;
    
    private String access_token;
    
    private String merchant_no;

	public String getQrcodeId() {
		return qrcodeId;
	}

	public void setQrcodeId(String qrcodeId) {
		this.qrcodeId = qrcodeId;
	}

	private String qrcodeId;
    
    
    
	/**
	 * @return the merchant_no
	 */
	public String getMerchant_no() {
		return merchant_no;
	}
	/**
	 * @param merchant_no the merchant_no to set
	 */
	public void setMerchant_no(String merchant_no) {
		this.merchant_no = merchant_no;
	}
	/**
	 * @return the terminal_id
	 */
	public String getTerminal_id() {
		return terminal_id;
	}
	/**
	 * @param terminal_id the terminal_id to set
	 */
	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}
	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}
	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	/**
	 * @return the refundstatus
	 */
	public String getRefundstatus() {
		return refundstatus;
	}
	/**
	 * @param refundstatus the refundstatus to set
	 */
	public void setRefundstatus(String refundstatus) {
		this.refundstatus = refundstatus;
	}
	/**
	 * @return the posmac
	 */
	public String getPosmac() {
		return posmac;
	}
	/**
	 * @param posmac the posmac to set
	 */
	public void setPosmac(String posmac) {
		this.posmac = posmac;
	}
	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}
	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * @return the postmac
	 */
	public String getMerIds() {
		return merIds;
	}
	public void setMerIds(String merIds) {
		this.merIds = merIds;
	}
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getId() {
        return id;
    }
    public String getClerkCode() {
		return clerkCode;
	}

	public void setClerkCode(String clerkCode) {
		this.clerkCode = clerkCode;
	}

	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename == null ? null : storename.trim();
    }

    public String getClerkname() {
        return clerkname;
    }

    public void setClerkname(String clerkname) {
        this.clerkname = clerkname == null ? null : clerkname.trim();
    }

    public String getClerkjob() {
        return clerkjob;
    }

    public void setClerkjob(String clerkjob) {
        this.clerkjob = clerkjob == null ? null : clerkjob.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    
	public String getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(String deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
}