package com.deyi.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.deyi.entity.Order;

@Controller
public class IndexController {

	private Logger log = Logger.getLogger(IndexController.class);
	
	/*@Autowired
	private OrderService orderService;
	
	@Autowired
	private MerCollectService merCollectService;
	
	@Autowired
	private OrgCollectDayService orgCollectDayService;*/
	
	@RequestMapping(value={"/","index.html"})
	public ModelAndView index(ModelAndView mav){
		log.info("index ....");
		return new ModelAndView("redirect:/system/index.html");
	}
	
	
	@ResponseBody
	@RequestMapping(value="day")
	public void test(int day){
		/*List<String> orders = orderService.queryYesterdayOrder(day);
		for (String string : orders) {
			Order payOrder = orderService.getPayOrder(string);
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("商户支付汇总统计开始...");
		merCollectService.addCountData(day);
		log.info("商户支付汇总统计结束...");
		
		log.info("门店支付汇总统计开始...");
		merCollectService.addStoreCountDataByDay(day);
		log.info("门店支付汇总统计结束...");
//		
		log.info("运营商或者代理商支付汇总统计开始...");
		orgCollectDayService.addOrgCountDataByDay(day);
		log.info("运营商或者代理商支付汇总统计结束...");*/
	}
}
