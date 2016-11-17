package com.mx.util;

import java.io.Serializable;

public class PayOrder implements Serializable {
	private static final long serialVersionUID = -2412626322132354533L;

	public static enum OrderChannel {
		CMB, CUP, AliPay;
	}

	private String orderNo; // 支付中心订单号
	private String orderInfo; // 支付附加信息
	private String orderTime; // 订单时间
	private String tradeId; // 商户自定义订单号
	private String amount; // 交易金额
	private OrderChannel orderChannel; // 支付渠道

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public OrderChannel getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(OrderChannel orderChannel) {
		this.orderChannel = orderChannel;
	}

	@Override
	public String toString() {
		return "PayOrder [orderNo=" + orderNo + ", orderInfo=" + orderInfo + ", orderTime=" + orderTime + ", tradeId="
				+ tradeId + ", amount=" + amount + ", orderChannel=" + orderChannel + "]";
	}

}
