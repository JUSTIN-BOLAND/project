package com.deyi.util;

import com.deyi.entity.UserInfo;

public class UserManage {
    private static ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<UserInfo>();  
    
    public static UserInfo getCurrUserInfo() {  
    	return userInfoThreadLocal.get();
    }
    
    public static void setCurrUserInfo(UserInfo userInfo) {  
    	userInfoThreadLocal.set(userInfo);
    }
    
    public static void removeCurrUserInfo(){
    	userInfoThreadLocal.remove();
    }
    
    
}
