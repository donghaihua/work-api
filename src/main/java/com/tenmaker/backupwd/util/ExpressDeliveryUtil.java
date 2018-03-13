package com.tenmaker.backupwd.util;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class ExpressDeliveryUtil {
	private String endpoint;

	private String operationName;

	/**
	 * 取得WebService调用的结果
	 *
	 * @param args
	 * @return
	 * @throws ServiceException
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	/*
	 * public Object getCallResult(Object[] args) throws MalformedURLException,
	 * RemoteException, ServiceException { // 创建 Service实例 Service service = new
	 * Service();
	 * 
	 * // 通过Service实例创建Call的实例 Call call = (Call) service.createCall();
	 * 
	 * // 将Web Service的服务路径加入到call实例之中. call.setTargetEndpointAddress(new
	 * java.net.URL(endpoint));// 为Call设置服务的位置
	 * 
	 * // 调用Web Service的方法 call.setOperationName(operationName);
	 * 
	 * // 调用Web Service,传入参数 return call.invoke(args); }
	 */
	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
}
