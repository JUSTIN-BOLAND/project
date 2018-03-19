package com.deyi.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
public class CommonUtil {

    public static boolean isInt(String src){
        String dest = (src == null?"": src);
        if( dest.length() == 0) return false;
        try
        {
            Integer.parseInt(src);
            return true;
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
    }
    public static boolean isIntNum(String src){
        String exp="^[0-9]+\\.{0,1}[0-9]{0,3}$";

        Pattern pat = Pattern.compile(exp);


        Matcher mat = pat.matcher(src);
        return mat.matches();
    }
    public static String convertToNull(String src){
        return convertToNull(src,"");
    }
    public static String convertToNull(String src,String defaultValue){
        if(src== null  || src.trim().length() ==0 ) return defaultValue;
        return src;

    }

    public static void main(String[] args){
        System.out.println(CommonUtil.isIntNum("0.0001"));
    }
}
