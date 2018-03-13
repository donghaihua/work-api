package com.tenmaker.backupwd.components;

public class AliPay {
	private String seller_email; // 卖家支付宝账户
	private String out_trade_no; // 订单名称
	private String subject; // 备注
	private String total_fee; // 总价
	private String body; // 订单描述

	public AliPay() {

	}

	public AliPay(String seller_email, String out_trade_no, String subject,
			String total_fee, String body) {
		super();
		this.seller_email = seller_email;
		this.out_trade_no = out_trade_no;
		this.subject = subject;
		this.total_fee = total_fee;
		this.body = body;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
