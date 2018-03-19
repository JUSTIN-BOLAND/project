package com.deyi.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Org implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -312290814982242204L;

	private String id;

    private String name;
    
    private String code;

    private String contactPerson;

    private String contactTel;

    private String address;

    private String status;

    private Integer areaId;

    private String creator;

    private String creatorName;

    private Date createTime;

    private String parentId;

    private String level;

    private String orgAccount;

    private BigDecimal rate;
    
    private String node;
    
    private String ids;//扩展字段
    
    private String remark;
    
    private String deletedStatus;//删除状态0(未删除)1(已删除)
    
    
    private String legalIdentityCardPositive;//法人身份证正面
    private String legalIdentityCardOpposite;//法人身份证反面
    private String businessLicense;//营业执照
    private String accountPermit;//开户许可证
    private String email;//邮箱
    
    private String licenseNumber;//执照编号
    private String bankNumber;//对公账号（银行卡号）
    private String notifyUrl;//被扫通知地址
    
    
    
	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getLegalIdentityCardPositive() {
		return legalIdentityCardPositive;
	}

	public void setLegalIdentityCardPositive(String legalIdentityCardPositive) {
		this.legalIdentityCardPositive = legalIdentityCardPositive;
	}

	public String getLegalIdentityCardOpposite() {
		return legalIdentityCardOpposite;
	}

	public void setLegalIdentityCardOpposite(String legalIdentityCardOpposite) {
		this.legalIdentityCardOpposite = legalIdentityCardOpposite;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getAccountPermit() {
		return accountPermit;
	}

	public void setAccountPermit(String accountPermit) {
		this.accountPermit = accountPermit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getcontactPerson() {
        return contactPerson;
    }

    public void setcontactPerson(String contactPerson) {
        this.contactPerson = contactPerson == null ? null : contactPerson.trim();
    }

    public String getcontactTel() {
        return contactTel;
    }

    public void setcontactTel(String contactTel) {
        this.contactTel = contactTel == null ? null : contactTel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getareaId() {
        return areaId;
    }

    public void setareaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getcreateTime() {
        return createTime;
    }

    public void setcreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getparentId() {
        return parentId;
    }

    public void setparentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getOrgAccount() {
		return orgAccount;
	}

	public void setOrgAccount(String orgAccount) {
		this.orgAccount = orgAccount;
	}

	public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

	public String getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(String deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
    
    
}