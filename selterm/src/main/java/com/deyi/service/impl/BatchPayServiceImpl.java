package com.deyi.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bank.Dom4j;
import com.bank.GlBankDFUtils;
import com.bank.GlBankDFquery;
import com.bank.IdGenerator;
import com.bank.PayMechInfo;
import com.deyi.dao.MercollectdayDao;
import com.deyi.dao.OthMerchantMapper;
import com.deyi.dao.PayanotherMapper;
import com.deyi.entity.Mercollectday;
import com.deyi.entity.OthMerchant;
import com.deyi.entity.Payanother;
import com.deyi.service.IBatchPayService;
import com.deyi.vo.MercollectdayVo;
import com.itextpdf.text.log.LoggerFactory;

@Transactional
@Service("batchPayService")
public class BatchPayServiceImpl implements IBatchPayService {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BatchPayServiceImpl.class);

	@Autowired
	private OthMerchantMapper othMerchantMapper;

	@Autowired
	private MercollectdayDao mercollectdayDao;
	@Autowired
	private PayanotherMapper payanotherMapper;

	@Override
	public void BathPay() {

		Calendar instance = Calendar.getInstance();
		MercollectdayVo vo = new MercollectdayVo();
		instance.add(Calendar.DATE, -1);
		vo.setQuerystarttime(new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(instance.getTime()));
		vo.setQueryendtime(new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(instance.getTime()));

		// 查询每天统计的金额
		List<Mercollectday> mercollertCondtion = mercollectdayDao.queryYesterData(vo.getQuerystarttime(),
				vo.getQueryendtime());
		List<PayMechInfo> merchantlist = new ArrayList<>();
		int index = 0;
		String format = new SimpleDateFormat("yyyMMddHHmm").format(new Date());
		String randomNumeric = RandomStringUtils.randomNumeric(4);
		for (Mercollectday mercollectday : mercollertCondtion) {

			OthMerchant selectByPrimaryKey = othMerchantMapper.selectByPrimaryKey(mercollectday.getMer_id());
			Payanother record = new Payanother();
			record.setCreatetime(new Date());
			record.setMerchantid(mercollectday.getMer_id().longValue());
			record.setMerchantname(mercollectday.getMer_name());
			record.setAccountno(selectByPrimaryKey.getAccountNo());
			record.setPaymoney(new BigDecimal(mercollectday.getSummoney()));
			record.setBankname(selectByPrimaryKey.getBankName());
			record.setUsername(selectByPrimaryKey.getAccountName());
			record.setBanklinkno(selectByPrimaryKey.getBankNo());
			record.setBathno(format + randomNumeric);
			record.setOrgid(mercollectday.getOrg_id());
			record.setOperdate(new Date());
			// record.setSeqno(IdGenerator.INSTANCE.nextId());

			BigDecimal temmoney = record.getPaymoney();
			// 金额大于5w的分批 每次出款45000
			if (temmoney.compareTo(new BigDecimal("45000")) > 0) {
				BigDecimal foufive = new BigDecimal("45000");
				int a = temmoney.intValue() / foufive.intValue();
				for (int i = 0; i < a; i++) {
					PayMechInfo payMechInfo = new PayMechInfo();
					payMechInfo.setAcctNme(selectByPrimaryKey.getAccountName());
					payMechInfo.setAcctNo(selectByPrimaryKey.getAccountNo());
					payMechInfo.setPmtAmt(foufive + "");
					payMechInfo.setSeqno(IdGenerator.INSTANCE.nextId());
					payMechInfo.setSumry(format + ":代付金额" + foufive);
					merchantlist.add(payMechInfo);
					// 插入代付的信息到表中
					record.setSeqno(payMechInfo.getSeqno());
					payanotherMapper.insertSelective(record);
				}
				PayMechInfo payMechInfo = new PayMechInfo();
				payMechInfo.setAcctNme(selectByPrimaryKey.getAccountName());
				payMechInfo.setAcctNo(selectByPrimaryKey.getAccountNo());
				payMechInfo.setPmtAmt(temmoney.subtract(new BigDecimal(a).multiply(foufive)) + "");
				payMechInfo.setSeqno(IdGenerator.INSTANCE.nextId());
				payMechInfo.setSumry(format + ":代付金额" + payMechInfo.getPmtAmt());
				merchantlist.add(payMechInfo);
				record.setSeqno(payMechInfo.getSeqno());
			} else {
				PayMechInfo payMechInfo = new PayMechInfo();
				payMechInfo.setAcctNme(selectByPrimaryKey.getAccountName());
				payMechInfo.setAcctNo(selectByPrimaryKey.getAccountNo());
				payMechInfo.setPmtAmt(mercollectday.getSummoney() + "");
				payMechInfo.setSeqno(IdGenerator.INSTANCE.nextId());
				payMechInfo.setSumry(format + ":代付金额" + mercollectday.getSummoney());
				merchantlist.add(payMechInfo);
				record.setSeqno(payMechInfo.getSeqno());
			}

			index++;
			// 插入代付的信息到表中
			payanotherMapper.insertSelective(record);
		}

		try {
			if (!merchantlist.isEmpty()) {
				HashMap<String, String> dfService = GlBankDFUtils.DFService(format + randomNumeric, merchantlist);

				String code = dfService.get("FaultCode");
				String message = dfService.get("FaultString");
				String WhlSeqNo = dfService.get("WhlSeqNo"); // 银行的全局流水号，需要查询提供这个
				if ("00000".equals(code)) {
					payanotherMapper.updateReason(format + randomNumeric, 3, null, WhlSeqNo);
				} else {
					payanotherMapper.updateReason(format + randomNumeric, 3, null, WhlSeqNo);
				}
			} else {
				logger.info(format + "没有出款信息");
			}

		} catch (Exception e) {
			payanotherMapper.updateReason(format + randomNumeric, 1, e.getMessage(), null);
		}
	}


	public void Bathquery() {
		List<Payanother> querytoday = payanotherMapper.querytoday();
		for (Payanother payanother : querytoday) {
			try {
				String dfQueryService = GlBankDFquery.DFQueryService(payanother.getBathno());

				logger.info("Analysis the xml to json :\r \n" + dfQueryService);
				Document document = DocumentHelper.parseText(dfQueryService);
				Element root = document.getRootElement();

				Object obj = Dom4j.parse(root); // 返回类型未知，已知DOM结构的时候可以强制转换

				logger.info("the parse json is : \n" + obj.toString());
				JSONObject json2 = (JSONObject) JSONObject.toJSON(obj);
				JSONArray jsonObject = json2.getJSONArray("Body");
				JSONObject lev3 = (JSONObject) jsonObject.get(0);
				JSONObject jsonObject2 = lev3.getJSONObject("ResponseBody");
				System.out.println("Body:" + jsonObject);
				JSONArray jsonArray = jsonObject2.getJSONArray("Loop");
				for (Object object : jsonArray) {
					JSONObject info = (JSONObject) object;
					if("00".equals(info.getString("CtrlStat"))){
						payanotherMapper.updateReasonBySeq(info.getString("EBkTrdSeqNo"), 2, info.getString("Rmrk"));
					}else{
						payanotherMapper.updateReasonBySeq(info.getString("EBkTrdSeqNo"), 1, info.getString("Rmrk"));
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
