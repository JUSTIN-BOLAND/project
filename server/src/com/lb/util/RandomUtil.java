package com.lb.util;

import java.util.Random;

public class RandomUtil {

    /**
     *
     * 函数名    : random
     * 功   能   : 产生指定长度的随机数
     * 参   数   : @param  length
     * 返回值    : java.lang.String
     * 编写者    : root
     * 编写时间  : 2017-06-28 15:07:23 下午 3:07
     */
    public static String random(int length) {

        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    public static String randomNum(int length) {

        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    public static int getRandom(int max){
        return (int)(1+Math.random()*max);
    }

    public static void main(String[] args){
       System.out.println( RandomUtil.randomNum(4));
    }

}