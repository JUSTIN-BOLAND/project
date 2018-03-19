package com.deyi.service;

import com.deyi.entity.Order;
import com.deyi.entity.PaystatisWork;
import com.deyi.paysdk.entity.DeyiPayResponse;
import com.deyi.util.Page;
import com.deyi.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface OrderService {
	/**
	 * 保存订单
	 *
	 * @param order
	 */
	int truncateTable(String tableName);
	void saveOrder(Order order);
	void updateOrder(Order order);

	Order save(Order order);

	Order getOrder(String orderNo);

	Order getPayOrder(String orderNo);

	/**
	 * 更新订单 payNo payMoney payTime payStatus orderStatus
	 *
	 * @param order
	 */
	void update(Order order);

	List<OrderVo> getOrdersList(Page<OrderVo> page);
	/**
	 * 查询历史订单表数据
	 * @param page
	 * @return
	 */
	List<OrderVo> getOrdersListHistory(Page<OrderVo> page);

	List<OrderParams> getAppOrdersList(Page<OrderParams> page);

	PaystatisWorkVo getOrdersListStatis(OrderVo orderVo);

	/**
	 * 统计当天总金额，微信总金额 支付宝总金额
	 *
	 * @param orderVo
	 * @return
	 */
	PaystatisVo getOrdersStatis(OrderParams orderVo);

	List<OrderSettleByDayVo> settleByDay(Page<OrderSettleByDayVo> page, String payType);

	Double settleByDayTotal(OrderSettleByDayVo orderSettleByDayVo);

	PaystatisWork settleByWorkTimeShow(Integer storeId, String orderTimeStart, String orderTimeEnd, String payEnv);

	ReturnVo<Object> refund(Order order);

	/**
	 * 支付宝被扫支付生成二维码
	 *
	 * @param order
	 */
	ReturnVo<Map<String, String>> aliPrecreate(Order order);

	ReturnVo<Map<String, String>> wxUnified(Order order);

	ReturnVo<Map<String, String>> unionPrecreate(Order order);

	List<OrderVo> getExceleOrderList(OrderVo page);

	List<OrderVo> getExceleOrderListCopy(OrderVo page);

	DeyiPayResponse payPrepair(Order order, String code);

	String notifyWxJs(HttpServletRequest request) throws Exception;

	String notifyAliJs(HttpServletRequest request, Map<String, String> paramts) throws Exception;

	/**
	 * 当日的所有统计 根据商户id
	 */
	PaystatisVo toDayCollectDay(String merId);

	OrderVo getOrderDetilByorderNo(String id);

	OrderVo getOrderDetilByorderNoHistory(String id);

	OrderDaySum toDayCollectData(OrderDaySum daysum);

	/**
	 * 统计历史订单表一天的数据
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

	String notifyUnionPay(String content) throws Exception;
}
