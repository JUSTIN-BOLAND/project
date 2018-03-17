package com.secray.utils;

/**
 * Created by root on 2018/2/1 0001.
 */
public class JspUtils {
    public static void main(String[] args){
        String[] moduleNames = {"充值明细","充值明细列表"};
        String[] querys = {"input,商户订单号,orderNo",
                           "input,经销商名称,authCode",
                           "input,机器名称,subMerchantId",
                           "input,机器编号,memo",
                          "select,支付方式,payType",
                          "select,支付状态,payStatus"};
        String[] pages = {"orderNo,商户订单号","authCode,经销商名称","subMerchantId,机器名称","memo,机器编号","payType,支付方式",
                          "payAmount,付款金额","actualAmount,到账金额","sendCmd,卡号","serviceType,业务类型","beforeAmount,操作前余额",
                          "afterAmount,操作后余额","payTime,时间","payStatus,支付状态","status,业务结果"};
        String[] exportFields = {"商户订单号,经销商名称,机器名称,机器编号,支付方式,付款金额,到账金额,卡号,业务类型,操作前余额,操作后余额,时间,支付状态,业务结果",
                "orderNo,authCode,subMerchantId,memo,payType,payAmount,actualAmount,sendCmd,serviceType,beforeAmount,afterAmount,payTime,payStatus,status",
                "充值明细"};
        ControlJsp controlJsp = new ControlJsp("t_recharge");
        controlJsp.setCondition(moduleNames,querys,pages);
        controlJsp.setExportFields(exportFields);
        controlJsp.buildListJsp();
        controlJsp.buildPageJsp();
        controlJsp.buildActionFile();
        System.out.println(2 % 3+" : "+  Math.ceil(2d/3d) +" -> "+5 % 3+" : "+ Math.ceil(5d/3d) );

        /*String[] fields = {"input,批号,batchNo,n2","input,卡号,cardNo,n5","input,姓名,name,userName",
                "input,手机号,mobile,m","input,账户余额,balance,money","input,充值账户余额,deposit,money",
                "input,身份证号,idCardNo,idCard"};
        moduleNames = new String[]{"一卡通管理","发卡"};
        JspBase jspBase = new JspBase(JspBase.ADD,"t_card");
        jspBase.setAddFields(fields,moduleNames);
        jspBase.buildJsp();

        moduleNames = new String[]{"一卡通管理","修改"};
        jspBase = new JspBase(JspBase.EDIT,"t_card");
        jspBase.setAddFields(fields,moduleNames);
        jspBase.buildJsp();*/
    }
}
