package com.deyi.entity;

import java.io.Serializable;

public class IndustryType implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8387202646483605618L;

	private String oneid;

    private String onename;

    private String twoid;

    private String twoname;

    private String threeid;

    private String threename;

    private String typecode;

    private String rate;

    private String cycle;

    public String getOneid() {
        return oneid;
    }

    public void setOneid(String oneid) {
        this.oneid = oneid == null ? null : oneid.trim();
    }

    public String getOnename() {
        return onename;
    }

    public void setOnename(String onename) {
        this.onename = onename == null ? null : onename.trim();
    }

    public String getTwoid() {
        return twoid;
    }

    public void setTwoid(String twoid) {
        this.twoid = twoid == null ? null : twoid.trim();
    }

    public String getTwoname() {
        return twoname;
    }

    public void setTwoname(String twoname) {
        this.twoname = twoname == null ? null : twoname.trim();
    }

    public String getThreeid() {
        return threeid;
    }

    public void setThreeid(String threeid) {
        this.threeid = threeid == null ? null : threeid.trim();
    }

    public String getThreename() {
        return threename;
    }

    public void setThreename(String threename) {
        this.threename = threename == null ? null : threename.trim();
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode == null ? null : typecode.trim();
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle == null ? null : cycle.trim();
    }
}