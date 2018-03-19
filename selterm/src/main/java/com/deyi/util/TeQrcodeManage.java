package com.deyi.util;

import com.deyi.entity.TeQrcode;

public class TeQrcodeManage {
	 private static ThreadLocal<TeQrcode> teQrcodeThreadLocal = new ThreadLocal<TeQrcode>();  
	    
	    public static TeQrcode getCurrUserInfo() {  
	    	return teQrcodeThreadLocal.get();
	    }
	    
	    public static void teQrcode(TeQrcode teQrcode) {  
	    	teQrcodeThreadLocal.set(teQrcode);
	    }
}
