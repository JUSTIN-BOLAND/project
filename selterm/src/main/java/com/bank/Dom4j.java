package com.bank;
import java.io.InputStream;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
  
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;  
import org.dom4j.io.SAXReader;  
  
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;  
  
public class Dom4j {  
    public static Object parse(Element root) {  
        List<?> elements = root.elements();  
        if (elements.size() == 0) {  
            // 没有子元素  
            return root.getTextTrim();  
        } else {  
            // 有子元素  
            String prev = null;  
            boolean guess = true; // 默认按照数组处理  
  
            Iterator<?> iterator = elements.iterator();  
            while (iterator.hasNext()) {  
                Element elem = (Element) iterator.next();  
                String name = elem.getName();  
                if (prev == null) {  
                    prev = name;  
                } else {  
                    guess = name.equals(prev);  
                    break;  
                }  
            }  
            iterator = elements.iterator();  
            if (guess) {  
                List<Object> data = new ArrayList<Object>();  
                while (iterator.hasNext()) {  
                    Element elem = (Element) iterator.next();  
                    ((List<Object>) data).add(parse(elem));  
                }  
                return data;  
            } else {  
                Map<String, Object> data = new HashMap<String, Object>();  
                while (iterator.hasNext()) {  
                    Element elem = (Element) iterator.next();  
                    ((Map<String, Object>) data).put(elem.getName(), parse(elem));  
                }  
                return data;  
            }  
        }  
    }  
  
    public static void main(String[] args) throws Throwable {  
//        SAXReader reader = new SAXReader();  
//        InputStream in = Dom4j.class.getResourceAsStream("toolbox.xml");  
    	String  text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"www.cqrcb.com.cn\">  <soapenv:Header/>  <soapenv:Body>    <ns:SQRY000030100011>      <ResponseHeader>        <VerNo>V1.0</VerNo>        <RespSysCd>003010</RespSysCd>        <RespSecCd/>        <TxnCd>SQRY000030100011</TxnCd>        <TxnNme>batch pay query</TxnNme>        <WhlSeqNo>MMD2017110290430000000003</WhlSeqNo>        <ReqDt>20171205</ReqDt>        <ReqTm>2017120052305008000002</ReqTm>        <ReqSeqNo>MMD2017120052305008000002</ReqSeqNo>        <SvrDt/>        <SvrSeqNo>C00020873478</SvrSeqNo>        <SvrTm/>        <ChnlNo>MMD</ChnlNo>        <RcvFileNme/>        <TotNum>4</TotNum>        <CurrRecNum>4</CurrRecNum>        <HostDt/>        <HostSeq/>        <ErrSeq/>        <ErrScpt/>        <FileHMac/>        <HMac/>      </ResponseHeader>      <ResponseBody>        <RecdInfo/>        <SuccNum/>        <TxnStatus>2</TxnStatus>        <Desc>处理成功</Desc>        <PayAcctNo>660011021631100020</PayAcctNo>        <Sumry>20171123:代付金额12.43</Sumry>        <Loop>          <AddInfo>            <TxnSeqNo>000007015729</TxnSeqNo>            <EBkTrdSeqNo>201711230</EBkTrdSeqNo>            <RcvAcctNo>623221550031999000</RcvAcctNo>            <RcvAcctNme>李伟</RcvAcctNme>            <TxnAmt/>            <Sumry>20171123:代付金额12.43</Sumry>            <CtrlStat>00</CtrlStat>            <Rmrk>账号[623221550031999000]不存在!</Rmrk>          </AddInfo>          <AddInfo>            <TxnSeqNo>000007015730</TxnSeqNo>            <EBkTrdSeqNo>201711231</EBkTrdSeqNo>            <RcvAcctNo>623221550031999000</RcvAcctNo>            <RcvAcctNme>李伟</RcvAcctNme>            <TxnAmt/>            <Sumry>20171123:代付金额0.03</Sumry>            <CtrlStat>00</CtrlStat>            <Rmrk>账号[623221550031999000]不存在!</Rmrk>          </AddInfo>          <AddInfo>            <TxnSeqNo>000007015731</TxnSeqNo>            <EBkTrdSeqNo>201711232</EBkTrdSeqNo>            <RcvAcctNo>623221550031999000</RcvAcctNo>            <RcvAcctNme>李伟</RcvAcctNme>            <TxnAmt/>            <Sumry>20171123:代付金额0.08</Sumry>            <CtrlStat>00</CtrlStat>            <Rmrk>账号[623221550031999000]不存在!</Rmrk>          </AddInfo>          <AddInfo>            <TxnSeqNo>000007015732</TxnSeqNo>            <EBkTrdSeqNo>201711233</EBkTrdSeqNo>            <RcvAcctNo>623221550031999000</RcvAcctNo>            <RcvAcctNme>李伟</RcvAcctNme>            <TxnAmt/>            <Sumry>20171123:代付金额0.06</Sumry>            <CtrlStat>01</CtrlStat>            <Rmrk>账号[623221550031999000]不存在!</Rmrk>          </AddInfo>        </Loop>      </ResponseBody>      <Fault>        <FaultCode>00000</FaultCode>        <FaultString>成功完成</FaultString>        <Detail>          <TxnStat>SUCCESS</TxnStat>        </Detail>      </Fault>    </ns:SQRY000030100011>  </soapenv:Body></soapenv:Envelope>";
        Document document = DocumentHelper.parseText(text);  
        Element root = document.getRootElement();  
  
        Object obj = parse(root); // 返回类型未知，已知DOM结构的时候可以强制转换  
        JSONObject json2 = (JSONObject) JSONObject.toJSON(obj);
        JSONArray jsonObject = json2.getJSONArray("Body");
        JSONObject lev3 = (JSONObject) jsonObject.get(0);
        JSONObject jsonObject2 = lev3.getJSONObject("ResponseBody");
        System.out.println("Body:"+jsonObject);
        JSONArray jsonArray = jsonObject2.getJSONArray("Loop");
        for (Object object : jsonArray) {
        	JSONObject info = (JSONObject)object;
			System.out.println(info);
		}
        System.out.println("ResponseBody:"+jsonObject2);
        System.out.println(json2); // 打印JSON  
    }
}