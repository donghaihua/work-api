package com.tenmaker.backupwd.service;


import com.tenmaker.backupwd.components.CommonResult;

/**
 * Created by HTJ on 14-8-23.
 */
public interface PassportAccountService {
/*

	public PassportAccount getAccountById(Long accountId);

	public PassportAccount getAccountByEmail(String email);

	public PassportAccount getAccountByTelephone(String telephone);

	public CommonResult verifyEmail(String url, String verify_key);

	public CommonResult regAccount(PassportAccount account, String captcha, Long captchaId, String spreadCode);

	public void updateAccount(PassportAccount account);

	public CommonResult updateAccount(Long accountId, String userName,
                                      String email, String qq);
*/

//	public PassportAccount checkLogin(Long id, String password);

	public Object acountLogin(String param, String password, int rsType);

//	public CommonResult deleteAcount(Long accountId);

	public CommonResult checkUserName(String userName);

	public CommonResult checkPhoneNumber(String telephone);

	public CommonResult checkEmail(String email);
/*

	public CommonResult uniqueCheck(String userName, String telephone,
                                    String email);

	public void SendSMS(String phoneNumber);

	public CommonResult modifyParam(Map<String, String> paramMap,
                                    String securityKey, int type, Long accountId);

	public CommonResult postEmail(int type, String email, String subject,
                                  String content);

	*/
/**
	 * 跳过验证码判断
	 * @param
	 * @param
	 * @return
	 *//*

	public CommonResult skipCaptchaImg(Long accountId,
                                       Map<String, String> paramMap);
*/

	//public Map<String, Object> getByShopId(Long shopId);
/*

	public Map<String, Object> addOrUpdateShopId(PassportAccount account, Long shopId);
	
	public Map<String, Object> deleteByShopId(Long userId, Long shopId);
	
	public PassportLightAccount getLightAccountById(Long accountId);

	public Map<String, Object> getUserList(Integer lastPage, Integer limit, String userName);

	public Map<String, Object> pwdReset(Long passportId);

	public Map<String, Object> unForzen(Long passportId);

	public Map<String, Object> forzen(Long passportId);
*/

	//public void save(PassportAccount account);
/*

	public Map<String, Object> getUnFrozenUserlist(Integer lastPage, Integer limit, String keyWords);

	public void wx_save(Long shopId, PassportAccount account);
*/

}
