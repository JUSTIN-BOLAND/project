package com.secray.utils.random;

/**
 * Created by root on 2017/6/6 0006.
 */
public class RandomUtil {

    /**
     *
     * 函数名    : random
     * 功   能   : 产生随机数
     * 参   数   : @param  begin
     * 参   数   : @param  end
     * 返回值    : long
     * 编写者    : root
     * 编写时间  : 2017-06-07 15:33:08 下午 3:33
     */
    public static long random(int begin,int end ){

        return Math.round(Math.random()*(end-begin)+begin);
    }
    /**
     *
     * 函数名    : getIP
     * 功   能   : 产生随机IP
     * 参   数   : @param
     * 返回值    : java.lang.String
     * 编写者    : root
     * 编写时间  : 2017-06-07 15:33:55 下午 3:33
     */
    public static String getIP(){

        return random(1,254)+"."+random(1,254)+"."+random(1,254)+"."+random(1,254);
    }
    public static void main(String[] args) {

       /* int i = (int)(Math.random()*10);
        int number = new Random().nextInt(10) + 1;*/
        System.out.println(RandomUtil.getIP());
    }
}
