package com.deyi.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deyi.dao.OrderDao;
import com.deyi.entity.Order;
import com.deyi.service.HistoryDataService;
import com.deyi.util.DateUtils;


@Transactional
@Service("historyDataService")
public class HistoryDataServiceImpl implements HistoryDataService {
	private Logger log =  LoggerFactory.getLogger(HistoryDataService.class);
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public void historyData() {
		
		log.info("迁移历史订单数据开始...");
		
		String tody = DateUtils.getStartTimeOfDay(DateUtils.getMotch()); //三个月前当天的日期
		String nextDay = DateUtils.getStartTimeOfDay(DateUtils.getLastDay(DateUtils.getMotch()));//三个月前前一天的日期
		
		//第一步查询出三个月前前一天的历史数据  sta_order
		List<Order> orders = orderDao.queryLastDayOrder(nextDay, tody);
		
		//第二步往sta_order_copy表里面新增查询出的三个月前前一天的数据
		for(Order order:orders){
			orderDao.saveLastDayOrder(order);
		}
		
		//第三步删除查询出的三个月前前一天的历史数据sta_order
		orderDao.deleteLastDayOrder(nextDay, tody);
		
		log.info("迁移历史订单数据结束...");
		
	}

}
