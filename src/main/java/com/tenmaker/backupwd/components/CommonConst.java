package com.tenmaker.backupwd.components;

public class CommonConst {
	// public static String smsUrl =
	// "http://www.smsadmin.cn/smsmarketing/wwwroot/api/get_send_md5?uid=nutshell&mobile=15380919404&msg=hello";
	public static String SITEURL = "http://www.uhaou.cn";//"http://nutogy.com:8089/uhaou"

	//支付方式
	public static int PAYTYPE_ALIPAY = 0; // 支付宝付款
	public static int PAYTYPE_WXPAY = 1;  // 微信付款

	// 七牛
	public static String QN_ACCESS_KEY = "d6xO5kaPpzOdxtBJB5rQVzc7IVTLdIMtD3bOsSnI";
	public static String QN_SECRET_KEY = "z4jHXEROLKHxBWe8-GJRjgQI69ZCkUxEm7BOCkYT";
	public static String QN_IMG_SITE = "http://uhaou.qiniudn.com/";

	public static String KUAIDI_SF = "shunfeng";
	
	public static String OPENID = "OPENID";
	public static String UNIONID = "UNIONID";

	//默认店铺
	public static Long DEFAULT_SHOP_ID = 1L;
	
	public static String SELLER_MAIL = "info@uhaou.cn";
	
	public static String AVATAR_DEFAULT = "http://uhaou.qiniudn.com/avatar_preview_default.png";
	// 邮箱
	public static String mail_name = "kevin@nutin.me";
	public static String mail_pwd = "4481318hong";
	// 短信
	public static String SMS_NAME = "uhaou";
	public static String SMS_PWD = "uhaou8888";
	public static String BI_SIGN = "【有好油】";
	public static String REGISTER = "该注册码用于注册账号";
	public static String MODIFY_EMAIL = "该注册码用于修改邮箱";
	public static String MODIFY_PWD = "该注册码用于修改密码";

	public static String LOGIN_ID = "LOGIN_ID";
	public static String LOGIN_AVATAR = "LOGIN_AVATAR";
	public static String LOGIN_PARAM = "LOGIN_PARAM";
	public static String LOGIN_PWD = "LOGIN_PWD";
	public static String LOGIN_ROLES = "LOGIN_ROLES";
	public static String LOGIN_SHOP = "LOGIN_SHOP";

	public static String LOGIN_FAILURE = "LOGIN_FAILURE";

	/* 订单的状态 */
	public static int ORDER_SHIPED = 0; // 已发货 = 未评价
	public static int ORDER_COMMENTED = 1; // 已评价

	public static int ORDER_PAYING = 2; // 待付款
	public static int ORDER_PAYED = 3; // 已付款 = 等待发货

	public static int ORDER_CANCELED = 4; // 未支付取消订单

	public static int ORDER_RETURNING = 5; // 待退款
	public static int ORDER_RETURNED = 6; // 已退款

	public static int ORDER_DELETE = 7; // 已删除
	public static int ORDER_DELETED = 8; // 已彻底删除

	public static int REFUND_APPLY = 0; // 客户申请退款
	public static int REFUND_SUCCESS = 1; // 同意退款/发起支付渠道申请
	public static int REFUND_REJECT = 2; // 驳回客户
	public static int REFUND_CLOSE = 3; // 关闭退款/收到支付渠道结果

	/* 订单详情状态 */
	public static int ORDER_DETAIL_NORMAL = 0; // 正常
	public static int ORDER_DETAIL_RETURN = 2; // 退货

	public static int ORDER_ERROR_EXPIRE = 1; // 订单过期
	// 不能重复付款

	public static String CART_LIST = "CART_LIST";	//购物车
	//public static String CART = "CART";			//unused(2018-2-24)
	public static String WISH_LIST = "WISH_LIST";	//心愿单

	// 结算购物车
	public static String CART_SETTLE_LIST = "CART_SETTLE_LIST";
	// 角色（tbl_passport_role）
	public static Integer CUSTOMER = 1;	//客户
	public static Integer SELLER = 2;		//管理员
	public static Integer SUPERADMIN = 3;	//超级管理员

	public static Integer SUCCESS = 0;
	public static Integer FAILURE = 1;

	public static Integer NOTEXISTS = 101;
	public static Integer EXISTS = 102;

	public static Integer FORMAT_ERROR = 201;
	public static Integer REPEAT_SUBMIT = 202;
	public static Integer PWD_ERROR = 203;

	public static Integer AUT_FAILED = 301;
	public static Integer OUT_OF_DATE = 302;
	public static Integer NOT_BIND = 303;
	public static Integer BOUND = 304;

	public static Integer NO_AUTH = 401;
	public static Integer NO_OPENID = 402;
	public static Integer NO_LOGIN = 403;

	public static Integer UNKNOWN_ERROR = 505;

	public static Integer TOO_BIG = 55;

	// 0-管理员验证, 1-手机验证, 2-邮箱验证
	public static Integer KEY_ADMIN = 0;
	public static Integer KEY_TEL = 1;
	public static Integer KEY_EMAIL = 2;

	// 商品状态
	public static Integer ITEM_STATE_NOT_RELEASE = 0; // 未发布
	public static Integer ITEM_STATE_RELEASE = 1; // 已经发布
	public static Integer ITEM_STATE_DELETE = 2; // 已删除

	public static String SALE_CART = "SALE_CART";

	public static Integer COMMENT_GOOD = 1;
	public static Integer COMMENT_MEDIUM = 2;
	public static Integer COMMENT_BAD = 3;
	
	//账号状态
	public static int ACCOUNT_NORMAL = 0;	//正常
	public static int ACCOUNT_FROZEN = 1;	//冻结
	public static String ACCOUNT_INIT_PWD = "123456";	//初始密码

	
	//个人优惠券状态
	public static int ACCOUNT_TICKET_NORMAL = 0;	//正常(待使用)
	public static int ACCOUNT_TICKET_USED = 1;		//已使用
	public static int ACCOUNT_TICKET_OUTDATE = 2;	//已过期
	public static int ACCOUNT_TICKET_FROZEN = 4;	//被冻结
	
	//系统优惠券状态
	public static int SYSTEM_TICKET_NORMAL = 0;	//正常(待使用)
	public static int SYSTEM_TICKET_END = 1;		//被领完(领取活动结束)
	public static int SYSTEM_TICKET_OUTDATE = 2;	//已过期
	public static int SYSTEM_TICKET_DELETE = 3;	//删除
	public static int SYSTEM_TICKET_FROZEN = 4;	//被冻结


	// 积分获取途径（数据库里存文字说明，不是id）
	public static String CREDIT_WAY_ADD_REGISTER = "注册账户奖励";
	public static String CREDIT_WAY_1 = "绑定手机号奖励";
	public static String CREDIT_WAY_2 = "关注公众号奖励";
	public static String CREDIT_WAY_ADD_PURCHASE = "购买商品奖励";
	public static String CREDIT_WAY_MINUS_PURCHASE = "购买商品消耗";
	public static String CREDIT_WAY_ADD_REFUND_ORDER = "退款时一并退还积分";
	public static String CREDIT_WAY_MINUS_REFUND_ORDER = "因退款扣去赠送的积分";
	public static String CREDIT_WAY_MINUS_EXCHANGE_STOCK = "兑换股权消耗";
	public static String CREDIT_WAY_ADD_CLOSE_ORDER = "因订单关闭返还消耗的积分";
	// 积分操作：0增加，1减少
	public static Integer CREDIT_OP_TYPE_ADD = 0;
	public static Integer CREDIT_OP_TYPE_MINUS = 1;
	// 积分类型
	public static Integer CREDIT_TYPE_CONSUME = 0;	//消费积分
	public static Integer CREDIT_TYPE_FX = 1;			//分销积分
	/*
	 * // 数据的添加和修改 public static String dataOperation_0 = "操作成功"; public static
	 * String dataOperation_1 = "操作失败";
	 * 
	 * // 登录 public static String userLogin_0 = "登陆成功"; public static String
	 * userLogin_1 = "用户名密码不存在"; public static String userLogin_2 = "验证码不正确";
	 * public static String userLogin_3 = "用户名和密码不能为空";
	 * 
	 * // 注册 public static String regUser_0 = "注册成功"; public static String
	 * regUser_1 = "该用户名已被注册过了"; public static String regUser_2 = "该号码已被注册过了";
	 * public static String regUser_3 = "手机号码格式不正确"; public static String
	 * regUser_4 = "邮箱已存在"; public static String regUser_5 = "邮箱格式不正确"; public
	 * static String regUser_6 = "手机验证码已过期，请重新发送"; public static String
	 * regUser_7 = "验证码不正确"; public static String regUser_99 = "注册失败，请重新注册";
	 * 
	 * // 注册的验证 public static String checkUserName_0 = "可以使用"; public static
	 * String checkUserName_1 = "该用户名已被注册过了"; public static String
	 * checkUserName_2 = "用户名必须超过6位"; public static String checkUserName_99 =
	 * "验证失败";
	 * 
	 * public static String checkPassword_1 = "密码必须大于6位";
	 * 
	 * public static String checkEmail_0 = "可以使用"; public static String
	 * checkEmail_1 = "邮箱已经被注册过了"; public static String checkEmail_2 =
	 * "邮箱格式不正确"; public static String checkEmail_99 = "验证失败";
	 * 
	 * public static String checkPhoneNumber_0 = "可以使用"; public static String
	 * checkPhoneNumber_1 = "该号码已被注册过了"; public static String checkPhoneNumber_2
	 * = "手机号码格式不正确"; public static String checkPhoneNumber_99 = "验证失败";
	 * 
	 * public static String CHECK_NICKNAME_0 = "可以使用"; public static String
	 * CHECK_NICKNAME_1 = "昵称已经被注册过了"; public static String CHECK_NICKNAME_2 =
	 * "昵称格式不正确"; public static String CHECK_NICKNAME_99 = "验证失败";
	 * 
	 * //唯一性验证 public static String UNIQUECHECK_0 = "验证通过"; public static String
	 * UNIQUECHECK_1 = "用户名已存在"; public static String UNIQUECHECK_2 = "手机号码已存在";
	 * public static String UNIQUECHECK_3 = "邮箱已存在"; public static String
	 * UNIQUECHECK_4 = "昵称已存在"; public static String UNIQUECHECK_99 =
	 * "验证出错，请重新提交";
	 * 
	 * // 删除 public static String deleteUser_0 = "删除成功"; public static String
	 * deleteUser_1 = "删除失败";
	 * 
	 * // 预定 public static String booking_0 = "预定成功"; public static String
	 * booking_1 = "预定失败";
	 * 
	 * //Rss的check验证 public static String checkRssEmail_0 = "可以使用"; public
	 * static String checkRssEmail_1 = "该邮件已被注册"; public static String
	 * checkRssEmail_2 = "该链接已过期，请重新申请";
	 * 
	 * public static String ACCOUNT_NOT_EXIST = "账户不存在，请确认是否已注册或登录";
	 * 
	 * public static String KEY_IS_OUTDATE = "链接已过期！请重新获取";
	 * 
	 * public static String TELNOTEXIST = "该号码没有经过注册，无法发送验证码";
	 */

}
