package com.deyi.dao;

import com.deyi.entity.InfLog;
import com.deyi.entity.Mercollectday;
import com.deyi.entity.Order;
import com.deyi.entity.OrderAsyn;
import com.deyi.util.Page;
import com.deyi.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDao")
public interface OrderDao {
	void saveInfLog(InfLog infLog);
	void saveOrderAsyn(String orderNo);
	void updateOrderAsyn(String orderNo);
	List<OrderAsyn> getOrderAsyns(Integer handleFlag);
	int getOrderAsynsCnt(String orderNo);
	List<Order> getNotPayOrder(Integer minute);
	void save(Order order);

	void update(Order order);
	
	Order getOrder(String orderNo);
	
	List<OrderVo> getOrdersList(Page<OrderVo> page);
	/**
	 * 查询历史订单表数据
	 * @param page
	 * @return
	 */
	List<OrderVo> getOrdersListHistory(Page<OrderVo> page);
	
	List<OrderVo> getExceleOrderList(OrderVo page); 
	//预约查询
	List<OrderVo> getExceleOrderListCopy(OrderVo page); 
	
	List<OrderParams> getAppOrdersList(Page<OrderParams> page);
	/**
	 * getOrdersList 的统计
	 * @param orderVo
	 * @return
	 */
	PaystatisWorkVo getOrdersListStatis(OrderVo orderVo);
	PaystatisVo getOrdersStatis(OrderParams orderVo);
	
	/**
	 * 结算按天 按人按日期 统计 不管支付方式的汇总
	 * @param orderVo
	 * @return
	 */
	List<OrderSettleByDayVo> settleByDay(Page<OrderSettleByDayVo> page);
	
	Double settleByDayTotal(OrderSettleByDayVo orderSettleByDayVo);
	/**
	 * 查询某个用户在某个门店某天的某种支付方式的统计
	 * @param storeId
	 * @param creator
	 * @param payType
	 * @param payDate
	 * @return
	 */
	OrderSettleByDayVo settleByDay2(String storeId, String payEnv, String creator, String payType, String payDate);
	
	/**
	 * 按时间段统计
	 * @param orderVo
	 * @return
	 */
	PaystatisWorkVo settleByTime(OrderVo orderVo);
	
	/**
	 * 按支付方式统计支付数
	 * @param orderVo
	 * @return
	 */
	Integer settleByPayType(OrderVo orderVo);
	
	/**
	 * 每日进行统计订单数据
	 */
	public List<Mercollectday> queryCollectDay();
	
	/**
	 * 当日的所有统计 根据商户id
	 */
	PaystatisVo toDayCollectDay(@Param("merids") String merId);
	
	
	OrderVo getOrderDetilByorderNo(@Param("orderno") String orderno);
	
	/**
	 * 查询订单历史表数据
	 * @param orderno
	 * @return
	 */
	OrderVo getOrderDetilByorderNoHistory(@Param("orderno") String orderno);
	
	/**
	 *统计一天的数据  
	 * @param daysum   [paytype = null 统计所有的]
	 * @return
	 */
	OrderDaySum toDayCollectData(OrderDaySum daysum);
	
	/**
	 * 统计订单表一天的数据
	 * @param daysum
	 * @return
	 */
	OrderDaySum toDayCollectDataCopy(OrderDaySum daysum);
	
	
	OrderDaySum selectorderbysum(OrderVo order);
	/**
	 * 统计订单历史表
	 * @param order
	 * @return
	 */
	OrderDaySum selectordercopybysum(OrderVo order);

	List<String> queryYesterdayOrder(int i);
	
	/**
	 * 获取三个月前前一天的历史数据
	 * @param nextDay 三个月前前一天的日期
	 * @param tody	  三个月前当天的日期
	 * @return
	 */
	List<Order> queryLastDayOrder(@Param("nextDay") String nextDay, @Param("tody") String tody);
	
	/**
	 * 新增三个月前前一天的历史数据到历史数据表(sta_order_copy)
	 * @param order
	 * @return
	 */
	Integer saveLastDayOrder(Order order);
	
	/**
	 * 删除三个月前钱一天的数据
	 * @param nextDay 三个月前前一天的日期
	 * @param tody    三个月前当天的日期
	 * @return
	 */
	Integer deleteLastDayOrder(@Param("nextDay") String nextDay, @Param("tody") String tody);
	
	void loadOrderFile(String fileName) throws Exception;
}
