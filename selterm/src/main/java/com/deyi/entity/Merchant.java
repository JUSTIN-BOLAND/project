package com.deyi.entity;

import java.io.Serializable;

public class Merchant implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 7108135297957023766L;
	private Integer id;
	private String code;//商户编码
	private Integer orgId;
	private String orgName;
	private String name;// 商户名称
	private String contactperson;// 联系人
	private String contacttel;//联系电话
	private String areaid;//地区代码
	private String address;//细地址
	private String mertype;//  1.一清 2二清
	private String remark;// 备注
	private String status;//
	private String creator;//创建人id
	private String creatorname;// 创建人姓名
	private String createtime;
	private String meraccount;//登陆账号
	private String identyescard;// 身份证正面url
	private String identnocard; //身份证反面url
	private String bankyescard;//银行卡正面url
	private String banknocard;// 银行卡反面url
	private String storesopra;//店面门头url
	private String storeinterior;//店面内照url
	private String storeinterior1;//店面内照2url
	private String storeinterior2;//店面内照3url
	private String buslicense;//营业执照url
	private String org;// 组织结构url
	private String taxadministr;//税务登记证url
	private String merchantPermit;//许可证url
	private String rate;//结算费率百分比
	private String orgIds;//扩展字段
	private String deletedStatus;//删除状态0(未删除)1(已删除)
	private String email;//邮箱
	private String merchantids;
	private String querystarttime;
	private String queryendtime;
	private String audit;//审核状态  1 审核中，2审核成功，3，审核失败

	private String aliaudit;  //支付宝审核状态

	private String wxaudit; // 微信审核状态
	private String unionaudit; // 微信审核状态

	private String appid;
	private String appsecret;

	private String sb_merchant_type;

	private String sb_status;

	private String merchantLogo;//商户logo

	private String merchant_rate;//商户费率
	private String org_id;//String
	private String aliMerchantId;
	private String merchantAliAccount;
	private String rejectReason;
	private String settleStatus;

	public String getAli_merchant_id() {
		return ali_merchant_id;
	}

	public void setAli_merchant_id(String ali_merchant_id) {
		this.ali_merchant_id = ali_merchant_id;
	}

	public String getSettle_status() {
		return settle_status;
	}

	public void setSettle_status(String settle_status) {
		this.settle_status = settle_status;
	}

	private String ali_merchant_id;
	private String settle_status;


	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}



	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}



	public String getMerchantAliAccount() {
		return merchantAliAccount;
	}

	public void setMerchantAliAccount(String merchantAliAccount) {
		this.merchantAliAccount = merchantAliAccount;
	}



	public String getAliMerchantId() {
		return aliMerchantId;
	}

	public void setAliMerchantId(String aliMerchantId) {
		this.aliMerchantId = aliMerchantId;
	}




	public String getMerchantLogo() {
		return merchantLogo;
	}
	public void setMerchantLogo(String merchantLogo) {
		this.merchantLogo = merchantLogo;
	}
	/**
	 * @return the sb_status
	 */
	public String getSb_status() {
		return sb_status;
	}
	/**
	 * @param sb_status the sb_status to set
	 */
	public void setSb_status(String sb_status) {
		this.sb_status = sb_status;
	}
	/**
	 * @return the sb_merchant_type
	 */
	public String getSb_merchant_type() {
		return sb_merchant_type;
	}
	/**
	 * @param sb_merchant_type the sb_merchant_type to set
	 */
	public void setSb_merchant_type(String sb_merchant_type) {
		this.sb_merchant_type = sb_merchant_type;
	}
	/**
	 * @return the appid
	 */
	public String getAppid() {
		return appid;
	}
	/**
	 * @param appid the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	/**
	 * @return the appsecret
	 */
	public String getAppsecret() {
		return appsecret;
	}
	/**
	 * @param appsecret the appsecret to set
	 */
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	public String getAliaudit() {
		return aliaudit;
	}
	public void setAliaudit(String aliaudit) {
		this.aliaudit = aliaudit;
	}
	public String getWxaudit() {
		return wxaudit;
	}
	public void setWxaudit(String wxaudit) {
		this.wxaudit = wxaudit;
	}
	public String getStoreinterior1() {
		return storeinterior1;
	}
	public void setStoreinterior1(String storeinterior1) {
		this.storeinterior1 = storeinterior1;
	}
	public String getStoreinterior2() {
		return storeinterior2;
	}
	public void setStoreinterior2(String storeinterior2) {
		this.storeinterior2 = storeinterior2;
	}
	public String getMerchantPermit() {
		return merchantPermit;
	}
	public void setMerchantPermit(String merchantPermit) {
		this.merchantPermit = merchantPermit;
	}
	public String getAudit() {
		return audit;
	}
	public void setAudit(String audit) {
		this.audit = audit;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQuerystarttime() {
		return querystarttime;
	}
	public void setQuerystarttime(String querystarttime) {
		this.querystarttime = querystarttime;
	}
	public String getQueryendtime() {
		return queryendtime;
	}
	public void setQueryendtime(String queryendtime) {
		this.queryendtime = queryendtime;
	}
	public String getMerchantids() {
		return merchantids;
	}
	public void setMerchantids(String merchantids) {
		this.merchantids = merchantids;
	}
	public String getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactperson() {
		return contactperson;
	}
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	public String getContacttel() {
		return contacttel;
	}
	public void setContacttel(String contacttel) {
		this.contacttel = contacttel;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMertype() {
		return mertype;
	}
	public void setMertype(String mertype) {
		this.mertype = mertype;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatorname() {
		return creatorname;
	}
	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getMeraccount() {
		return meraccount;
	}
	public void setMeraccount(String meraccount) {
		this.meraccount = meraccount;
	}
	public String getIdentyescard() {
		return identyescard;
	}
	public void setIdentyescard(String identyescard) {
		this.identyescard = identyescard;
	}
	public String getIdentnocard() {
		return identnocard;
	}
	public void setIdentnocard(String identnocard) {
		this.identnocard = identnocard;
	}
	public String getBankyescard() {
		return bankyescard;
	}
	public void setBankyescard(String bankyescard) {
		this.bankyescard = bankyescard;
	}
	public String getBanknocard() {
		return banknocard;
	}
	public void setBanknocard(String banknocard) {
		this.banknocard = banknocard;
	}
	public String getStoresopra() {
		return storesopra;
	}
	public void setStoresopra(String storesopra) {
		this.storesopra = storesopra;
	}
	public String getStoreinterior() {
		return storeinterior;
	}
	public void setStoreinterior(String storeinterior) {
		this.storeinterior = storeinterior;
	}
	public String getBuslicense() {
		return buslicense;
	}
	public void setBuslicense(String buslicense) {
		this.buslicense = buslicense;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getTaxadministr() {
		return taxadministr;
	}
	public void setTaxadministr(String taxadministr) {
		this.taxadministr = taxadministr;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(String deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	public String getUnionaudit() {
		return unionaudit;
	}
	public void setUnionaudit(String unionaudit) {
		this.unionaudit = unionaudit;
	}
	public String getMerchant_rate() {
		return merchant_rate;
	}
	public void setMerchant_rate(String merchant_rate) {
		this.merchant_rate = merchant_rate;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

}
