package com.deyi.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.deyi.util.Page;

public class Payanother {
    private Long id;

    private Long merchantid;

    private String merchantname;

    private Long orgid;

    private String orgname;

    private Date createtime;

    private String status;

    private String reason;

    private Date operdate;

    private BigDecimal paymoney;
    
    private String  bathno;
    
    private String accountno;
    
    private String username;
    
    private String banklinkno;
    
    private String bankname;
    

    private String whlseqno;
    
    private String timeStart;
    
    private String timeEnd;
    
    private String seqno;
    
    
    
    
    
    
    
    public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getWhlseqno() {
		return whlseqno;
	}

	public void setWhlseqno(String whlseqno) {
		this.whlseqno = whlseqno;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBanklinkno() {
		return banklinkno;
	}

	public void setBanklinkno(String banklinkno) {
		this.banklinkno = banklinkno;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getBathno() {
		return bathno;
	}

	public void setBathno(String bathno) {
		this.bathno = bathno;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Long merchantid) {
        this.merchantid = merchantid;
    }

    public String getMerchantname() {
        return merchantname;
    }

    public void setMerchantname(String merchantname) {
        this.merchantname = merchantname == null ? null : merchantname.trim();
    }

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getOperdate() {
        return operdate;
    }

    public void setOperdate(Date operdate) {
        this.operdate = operdate;
    }

    public BigDecimal getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(BigDecimal paymoney) {
        this.paymoney = paymoney;
    }

}