package com.lb.server;

public interface Constant {
   public static final String  CONSUME_CODE="06";
   public static final String  CONSUME_OK="00";
   public static final String  CONSUME_LIMIT="33";
   public static final String  CONSUME_BEYOND="44";
   
   public static final String  QUERY_CODE="22";
   public static final String  QUERY_OK="41";
   public static final String  QUERY_USING="40";
   public static final String  QUERY_FORBIT="33";
   
   public static final String  SHUTDOWN_CODE="07";
   public static final String  SHUTDOWNE_OK="00";
   
   public static final String  TRANS_RECHARGE_CODE="66";
   public static final String  TRANS_QUERY_CODE="22";
   public static final String  TRANS_CARDLOST_CODE="11";               //卡已挂失
   public static final String  TRANS_INSUFFICIENT_BALANCE_CODE="22"; //余额不足,或存款余额为0
   public static final String  TRANS_EXCEED_MAX_CODE="44";             //超出最大充值数次
   public static final String  TRANS_CARD_NOT_ACCOUNT_CODE="55";      //存款记录或此卡未开户


   public static final int TRANSFER_MACHINE = 1;  //圈存机
   public static final int MESSAGE_ARECHAIR = 2 ; //按摩椅
   public static final int WATER_DISPENSER =  3;  //饮水机
   public static final int DRINKING_FOUNTAIN_STAND =4 ; //饮水台
   public static final int WATER_PURIFIER = 5;           //净水器
   public static final int WASHING_MACHINE = 6;          //洗衣机
   public static final int CHARGING_PILE = 7;            //充电桩
   public static final int AIR_CONDITIOINER = 8;         //空调
   
   
   
}
