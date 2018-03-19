package com.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlBankDFquery {

	private static final Logger logger = LoggerFactory.getLogger(GlBankDFquery.class);

	public static String DFQueryService(String batchno) throws Exception {

		HashMap<String, Object> header = new HashMap();
		Date date = new Date();
		header.put("VerNo", "V1.0"); // Y
		header.put("ReqSysCd", "001002");// Y
		header.put("ReqSecCd", "");// N
		header.put("TxnTyp", "R");// Y
		header.put("TxnMod", "0");// Y

		header.put("TxnCd", "SQRY000030100011");// Y
		header.put("TxnNme", "batch pay query");// N
		header.put("WhlSeqNo", "MMD2017110290430000000003");// Y
		header.put("WhlTm", "60");// Y
		header.put("ReqDt", new SimpleDateFormat("yyyyMMdd").format(date));// Y

		header.put("ReqTm", new SimpleDateFormat("yyyyMMdddHHmmsssuuuuuu").format(date));// Y
		header.put("ChnlNo", "MMD");// Y
		header.put("ReqSeqNo", "MMD" + new SimpleDateFormat("yyyyMMdddHHmmsssuuuuuu").format(date));// Y
		header.put("BrchNo", "MMD");//
		header.put("BrchNme", "");//

		header.put("TlrNo", "B000003");// Y
		header.put("AuthTlr", "");//
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
		hashMap.put("LoctnNo", "000120171115");// 商户号
		hashMap.put("UsrID", "P201711154049484");// N
		// new SimpleDateFormat("yyyyMMddHHmmss").format(date) + new
		// Random().nextInt(1000)
		hashMap.put("OperSeqNo", batchno);// Y

		return GlBankDFquery.submit(hashMap, header);

	}

	// private static final Logger logger =
	// LoggerFactory.getLogger(GlBankDFquery.class);

	public static String submit(Map<String, Object> map, Map headermap) throws IOException {
		String builddata = builddata(map, headermap);
		System.out.println(builddata);
		URL url = new URL("http://200.100.51.108:13121/SQRY000030100011");
		URLConnection openConnection = url.openConnection();
		openConnection.setRequestProperty("Accept-Charset", "UTF-8");
		openConnection.addRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
		openConnection.addRequestProperty("Content-Length", builddata.getBytes().length + "");
		openConnection.addRequestProperty("Content-MD5", MyBase64.encode(MD5Util.getMD5(builddata).getBytes()));
		openConnection.setDoOutput(true);
		openConnection.setDoInput(true);

		PrintWriter out = new PrintWriter(openConnection.getOutputStream());

		logger.info("========================================");
		logger.info("================发送参数开始=============");
		logger.info(builddata);
		logger.info("================发送参数结束=============");
		out.print(builddata);
		out.flush();

		BufferedReader in = new BufferedReader(new InputStreamReader(openConnection.getInputStream(), "UTF-8"));
		String line;
		String result = "";
		while ((line = in.readLine()) != null) {
			result += line;
		}
		logger.info("========================================");
		logger.info("========================================");
		logger.info("================接受参数开始=============");
		logger.info(result);
		logger.info("================接受参数结束=============");
		return result;
	}

	private static String builddata(Map map, Map headermap) {
		String top = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cqr=\"www.cqrcb.com.cn\">";
		String topend = "</soapenv:Envelope>";
		String header = "<soapenv:Header />";
		String body = "<soapenv:Body>";
		String cqr = "<cqr:SQRY000030100011>";
		String cqrend = "</cqr:SQRY000030100011>";
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
		try {
			// DFQueryService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
