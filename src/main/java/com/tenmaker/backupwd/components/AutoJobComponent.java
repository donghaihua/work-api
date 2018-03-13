/*
package com.tenmaker.backupwd.components;

import com.tenmaker.backupwd.model.PassportStock;
import com.tenmaker.backupwd.model.PassportStockRecord;
import com.tenmaker.backupwd.model.SaleRefund;
import com.tenmaker.backupwd.model.SystemStock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

*/
/**
 * 定时器任务
 * @author river
 *
 *//*

public class AutoJobComponent {

	private static final Logger LOG = LoggerFactory.getLogger(AutoJobComponent.class);

	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {"/spring-mvc.xml", "/application-context.xml"});    //2018-3-2：这里要注意顺序！

	*/
/*private static SystemTicketService systemTicketService = (SystemTicketService) applicationContext.getBean("systemTicketService");

	private static PassportStockRecordService passportStockRecordService = (PassportStockRecordService) applicationContext.getBean("passportStockRecordService");;

	private static PassportStockService passportStockService = (PassportStockService) applicationContext.getBean("passportStockService");

	private static SystemStockService systemStockService = (SystemStockService) applicationContext.getBean("systemStockService");

	private static SaleRefundService saleRefundService = (SaleRefundService) applicationContext.getBean("saleRefundService");
*//*

	public void updateSystemTicketOutDate(){
		//SystemTicketService systemTicketService = (SystemTicketService) applicationContext.getBean("systemTicketService");

		Long start = System.currentTimeMillis();

		systemTicketService.updateSystemTicketOutDate();
		//System.out.println(new Date());
		Long end = System.currentTimeMillis();
		LOG.debug("定时器任务耗时为：{} 毫秒", (end-start));
		//System.out.println(end-start);
	}

	public void updateEps(){
		//PassportStockRecordService passportStockRecordService = (PassportStockRecordService) applicationContext.getBean("passportStockRecordService");
		//PassportStockService passportStockService = (PassportStockService) applicationContext.getBean("passportStockService");
		//SystemStockService systemStockService = (SystemStockService) applicationContext.getBean("systemStockService");

		Calendar ca=Calendar.getInstance();
		Integer year = Integer.parseInt(""+ca.get(Calendar.YEAR));
		Integer preYear = year -1;
		double systemEps = systemStockService.getSystemStockYear(preYear.toString());
		//当年内日常记录的最后一条，所有数据
		List<PassportStockRecord> list = passportStockRecordService.getList();
		for (PassportStockRecord passportStockRecord : list) {
			PassportStock ps = new PassportStock();
			ps.setAccount(passportStockRecord.getAccount());
			ps.setState(0);
			ps.setStockNumber(passportStockRecord.getStockNumber());
			ps.setCurrYear(passportStockRecord.getRecordYear());
			passportStockService.add(ps);
	    	PassportStockRecord psrecord = new PassportStockRecord();
	    	psrecord.setAccount(passportStockRecord.getAccount());
	    	psrecord.setOperateTime(new Date());
	    	psrecord.setOperateType(1);
	    	psrecord.setRecordYear(preYear.toString());
	    	psrecord.setOperateDesc("股权兑换");
	    	psrecord.setEps(systemEps * passportStockRecord.getStockNumber());
	    	passportStockRecordService.add(psrecord);
	    	System.out.println(psrecord.getId());
	    	SystemStock ss = new SystemStock();
	    	ss.setCurrYear(year.toString());
	    	ss.setState(0);
	    	systemStockService.add(ss);
		}
	}

	public void queryRefund() {
		List<SaleRefund> list = saleRefundService.getByThree(CommonConst.PAYTYPE_WXPAY, WxTradeTypeEnum.小程序支付.type, CommonConst.REFUND_SUCCESS);
		LOG.info("共查询到 {} 条已向微信渠道发出的小程序退款请求", list.size());
		for (SaleRefund saleRefund : list) {
			saleRefundService.processRefundResult(saleRefund);
		}
	}
}
*/
