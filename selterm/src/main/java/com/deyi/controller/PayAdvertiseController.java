package com.deyi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.deyi.entity.Advertise;
import com.deyi.util.Component;

@Controller
@RequestMapping(value = "advertise")
public class PayAdvertiseController extends Component<Advertise> {
	private Logger log = LoggerFactory.getLogger(Advertise.class);

	

	
	
//	/**
//	 * 支付广告列表
//	 * @param mav
//	 * @param request
//	 * @return   
//	 * ModelAndView 
//	 * @author Enzo
//	 * @date 2017年2月20日
//	 */
//	@RequestMapping(value = "paylist")
//	public ModelAndView paylist(ModelAndView mav) {
//		log.info("paylist");
//		mav.setViewName("advert/payadvList");
//		return mav;
//	}

}
