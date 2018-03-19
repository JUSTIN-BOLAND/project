package com.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlBankDFUtils {

	private static final Logger logger = LoggerFactory.getLogger(GlBankDFUtils.class);

	public static HashMap<String, String> DFService(String openorder, List<PayMechInfo> merchantlist) throws Exception {

		HashMap<String, Object> header = new HashMap();
		Date date = new Date();
		header.put("VerNo", "V1.0"); // Y
		header.put("ReqSysCd", "001002");// Y
		header.put("ReqSecCd", "");// N
		header.put("TxnTyp", "R");// Y
		header.put("TxnMod", "0");// Y

		header.put("TxnCd", "SQRY000030100009");// Y
		header.put("TxnNme", "batch pay import");// N
		header.put("WhlSeqNo", "MMD" + new SimpleDateFormat("yyyyMMdddHHmmsssuuuuuu").format(date));// Y
		header.put("WhlTm", "60");// Y
		header.put("ReqDt", new SimpleDateFormat("yyyyMMdd").format(date));// Y

		header.put("ReqTm", new SimpleDateFormat("yyyyMMdddHHmmsssuuuuuu").format(date));// Y
		header.put("ChnlNo", "MMD");// Y
		header.put("ReqSeqNo", "MMD" + new SimpleDateFormat("yyyyMMdddHHmmsssuuuuuu").format(date));// Y
		header.put("BrchNo", "MMD");//
		header.put("BrchNme", "");//

		header.put("TlrNo", "B000003");// Y
		header.put("AuthTlr", "");// N��Ȩ��Ա
		header.put("SndFileNme", "");// N
		header.put("BgnRec", "");// N
		header.put("MaxRec", "");// N

		header.put("VTlrNo", "");// N
		header.put("ChkCd", "");// N
		header.put("Checker", "");// N
		header.put("AcctBrch", "009906");// N
		header.put("BizTyp", "C209");// Y

		header.put("ChkNo", "");// N
		header.put("BootNo", "");// N
		header.put("WinNm", "");// N
		header.put("WinId", "");// N
		header.put("FsysFlg", "");// N

		header.put("FileHMac", "");// N
		header.put("HMac", "");// N
		header.put("BkVerNo", "01");// Y???
		header.put("BkSubVerNo", "01");// Y???
		header.put("BkNodeId", "100010");// Y

		header.put("BkEntrNo", "P0000010");// Y
		header.put("BkBusiNo", "0001");// Y

		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("LoctnNo", "000120171115");// ??????
		hashMap.put("UsrID", "P201711154049484");// N
		hashMap.put("OperSeqNo", openorder);// Y
		hashMap.put("TotNum", "1");// Y
		hashMap.put("TotAmt", "2.00");// Y
		hashMap.put("PayAcctNo", "660011021631100020");// Y
		hashMap.put("BatNo", new SimpleDateFormat("yyyyMMddHHmmss").format(date) + RandomStringUtils.randomNumeric(4));// Y
		hashMap.put("PayDt", "");// N
		hashMap.put("RegTm", "");// N
		hashMap.put("TxnTm", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		hashMap.put("RmrkPurps", "pay info");
		Map<String, Object> loop = new HashMap<>();
		BigDecimal totalAmt = BigDecimal.ZERO;
		List<Object> arrayList = new ArrayList<>();
		for (PayMechInfo payMechInfo : merchantlist) {
			Map<String, Object> AddInfo3 = new HashMap<>();
			AddInfo3.put("EBkTrdSeqNo", payMechInfo.getSeqno());
			AddInfo3.put("RcvAcctNo", payMechInfo.getAcctNo());
			AddInfo3.put("RcvAcctNme", payMechInfo.getAcctNme());
			AddInfo3.put("PmtAmt", new BigDecimal(payMechInfo.getPmtAmt()).setScale(2, BigDecimal.ROUND_HALF_UP));
			AddInfo3.put("Sumry", payMechInfo.getSumry());

			arrayList.add(AddInfo3);
			totalAmt = totalAmt.add(new BigDecimal(payMechInfo.getPmtAmt()));
		}
		loop.put("AddInfo", arrayList);
		hashMap.put("TotNum", merchantlist.size() + "");// 代付总笔数
		hashMap.put("TotAmt", totalAmt.setScale(2, BigDecimal.ROUND_HALF_UP).toString());// Y
		hashMap.put("Loop", loop);

		return GlBankDFUtils.submit(hashMap, header);

	}

	public static HashMap<String, String> submit(Map<String, Object> map, Map headermap)
			throws IOException, DocumentException {
		String builddata = builddata(map, headermap);
		System.out.println(builddata);
		URL url = new URL("http://200.100.51.108:13121/SQRY000030100009");
		URLConnection openConnection = url.openConnection();
		openConnection.setRequestProperty("Accept-Charset", "UTF-8");
		openConnection.addRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
		openConnection.addRequestProperty("Content-Length", builddata.getBytes().length + "");
		openConnection.addRequestProperty("Content-MD5", MyBase64.encode(MD5Util.getMD5(builddata).getBytes()));
		openConnection.setDoOutput(true);
		openConnection.setDoInput(true);

		PrintWriter out = new PrintWriter(openConnection.getOutputStream());

		logger.info("========================================");
		logger.info("================发送参数=============");
		logger.info(builddata);
		out.print(builddata);
		out.flush();
		logger.info("================发送结束=============");
		BufferedReader in = new BufferedReader(new InputStreamReader(openConnection.getInputStream(), "UTF-8"));
		String line;
		String result = "";
		while ((line = in.readLine()) != null) {
			result += line;
		}
		logger.info("================接受参数开始=============");
		logger.info(result);
		logger.info("================接受参数结束=============");
		return SoapXmlUtils.parse(result);
	}

	private static String builddata(Map map, Map headermap) {
		String top = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cqr=\"www.cqrcb.com.cn\">";
		String topend = "</soapenv:Envelope>";
		String header = "<soapenv:Header />";
		String body = "<soapenv:Body>";
		String cqr = "<cqr:SQRY000030100009>";
		String cqrend = "</cqr:SQRY000030100009>";
		String requestHeader = "<RequestHeader>";
		String requestHeaderend = "</RequestHeader>";
		String requestbody = "<RequestBody>";
		String requestbodyend = "</RequestBody>";
		String bodyend = " </soapenv:Body>";
		String parse = parse(map);
		return top + header + body + cqr + requestHeader + parse(headermap) + requestHeaderend + requestbody + parse
				+ requestbodyend + cqrend + bodyend + topend;

	}

	private static String parse(Map<?, ?> map) {
		StringBuffer stringBuffer = new StringBuffer();
		String strintTemple = "<%s>%s</%s>";
		for (Map.Entry entry : map.entrySet()) {
			if (entry.getValue() instanceof Map) {
				String aa = parse((Map) entry.getValue());
				stringBuffer.append(String.format(strintTemple, entry.getKey(), aa, entry.getKey()));
			} else if (entry.getValue() instanceof List) {
				List<Map> value = (List<Map>) entry.getValue();
				for (Map map2 : value) {
					String aa = parse(map2);
					stringBuffer.append(String.format(strintTemple, entry.getKey(), aa, entry.getKey()));
				}
			} else {
				stringBuffer.append(String.format(strintTemple, entry.getKey(), entry.getValue(), entry.getKey()));
			}
		}
		return stringBuffer.toString();
	}

	public static void main(String[] args) {
		List<PayMechInfo> merchantlist = new ArrayList<>();
		PayMechInfo payMechInfo = new PayMechInfo();
		payMechInfo.setAcctNme("123123");
		payMechInfo.setAcctNo("111222233");
		payMechInfo.setPmtAmt("100");
		payMechInfo.setSeqno("23493459345");
		payMechInfo.setSumry("remark");
		merchantlist.add(payMechInfo);
		merchantlist.add(payMechInfo);
		try {
//			DFService(merchantlist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
