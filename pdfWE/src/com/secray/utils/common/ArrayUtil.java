package com.secray.utils.common;

/**
 * 类  名: ArrayUtil
 * 描  述: TODO(这里用一句话描述这个类的作用)
 * 创建者: root
 * 公   司: 郑州信达天瑞信息有限公司
 * 创建时间: 2017年2月21日 上午11:12:38
 *
 */
public class ArrayUtil {

    public static   String[] concat(String[] a, String[] b) {
        String[] c= new String[a.length+b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static   Object[] concat(Object[] a, Object[] b) {
        Object[] c= new Object[a.length+b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }
    public static   String[] replace(String[] src,String oldChar, String newChar){
        if(src == null) return null;

        String[] result = new String[src.length];

        for(int i =0 ; i < src.length; i++){
            result[i] = src[i].replace(oldChar, newChar);
        }
        return result;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
