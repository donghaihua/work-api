package com.tenmaker.backupwd.service.impl;

import com.tenmaker.backupwd.components.CommonConst;
import com.tenmaker.backupwd.components.CommonResult;
import com.tenmaker.backupwd.components.CommonUtils;
import com.tenmaker.backupwd.mapper.*;
import com.tenmaker.backupwd.model.*;
import com.tenmaker.backupwd.service.*;
import com.tenmaker.backupwd.util.*;
import com.tenmaker.backupwd.util.mail.MailUtil;
import org.apache.http.client.ClientProtocolException;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by HTJ on 14-8-23.
 */
@Service("accountService")
@Transactional
public class PassportAccountServiceImpl implements PassportAccountService {

	@Autowired
	private PassportAccountDao accountDao;

	private static Logger log = LoggerFactory.getLogger(PassportAccountServiceImpl.class);

	//根据id查询用户的记录

	public PassportAccount getAccountById(Long acountId) {
		PassportAccount account = accountDao.getById(acountId);
		return account;
	}
	//根据邮箱查询用户记录

	public PassportAccount getAccountByEmail(String email) {
		return accountDao.getByEmail(email);
	}
	//根据手机号查询用户记录
	public PassportAccount getAccountByTelephone(String telephone) {
		return accountDao.getByTelephone(telephone);
	}

	/**
	 * @param captcha  用户输入的验证码
	 * @param captchaId  系统里的验证码ID
	 * 
	 */
	/*@Override
	public CommonResult regAccount(PassportAccount account, String captcha, Long captchaId, String spreadCode) {
		PassportCaptcha captchaObj = captchaDao.getById(captchaId);
		CommonResult result = captchaService.checkCaptchaVerify(captchaId,
				captcha);
		if (0 != result.success) {
			return result;
		}

		if (account == null) {
			result.success = 1;
			result.error = CommonConst.UNKNOWN_ERROR;
		} else if (ValidateUtil.isMobile(account.getTelephone()) == false) {
			result.success = 1;
			result.error = CommonConst.FORMAT_ERROR;
		} else if (accountDao.getByTelephone(account.getTelephone()) != null) {
			result.success = 1;
			result.error = CommonConst.EXISTS;
		} else {
			try {
				account.setTelState(1); // 手机号码已经被修改过了
				account.setUserName(account.getTelephone());
				account.setUnameState(0);
				account.setEmailState(0);
				account.setState(0);
				accountDao.add(account);

				// 插入用户角色,默认是普通用户
				roleAccountService.add(account.getId(), new Integer(1));
				// 用户的详情信息
				PassportAccountInfo accountInfo = new PassportAccountInfo();
				accountInfo.setAccount(account);
				accountInfo.setAvatar(CommonConst.AVATAR_DEFAULT);
				accountInfoDao.add(accountInfo);

				PassportLevel level = new PassportLevel();
				level.setAccount(account);
				level.setLevel(1);
				level.setCredits(0);

				levelDao.add(level);

				Long accountId = account.getId();
				// 注册默认用手机注册
				PassportSecKeyVerify skv = secKeyVerifyService.addData(accountId, 1);
				// 更新
				captchaObj.setState(1);
				//TODO
				captchaDao.update(captchaObj);

				// TODO 生成用户的推广信息
				spreadService.add(account, spreadCode);


				Date currDate = new Date();
				// 通过PC注册，系统赠送10积分
				PassportCredit credit = new PassportCredit();
				credit.setAccount(account);
				credit.setTotalValue(10);
				credit.setUsedValue(0);
				credit.setCurrValue(10);
				credit.setFxUsedValue(0);
				credit.setFxCurrValue(0);
				credit.setGetTime(currDate);
				credit.setState(0);
				//TODO
				creditDao.add(credit);

				// 积分记录
				PassportCreditRecord creditRecord = new PassportCreditRecord();
				creditRecord.setOperateTime(currDate);
				creditRecord.setOperateDesc(CommonConst.CREDIT_WAY_ADD_REGISTER);
				creditRecord.setCreditValue(10);
				creditRecord.setCreditType(CommonConst.CREDIT_TYPE_CONSUME);
				creditRecord.setCredit(credit);
				creditRecord.setAccount(account);
				creditRecord.setOperateType(CommonConst.CREDIT_OP_TYPE_ADD);
				//TODO
				creditRecordDao.add(creditRecord);

				
				result.id = accountId;
				result.success = 0;
				result.securityKey = skv.getSecurityKey();
				result.error = CommonConst.SUCCESS;
			} catch (Exception e) {
				log.error("PassportAccountServiceImpl.regAccount()=======", e);
				result.success = 1;
				result.error = CommonConst.UNKNOWN_ERROR;
			}
		}

		return result;
	}

	*//**
	 * 更新用户
	 *//*
	@Override
	public CommonResult updateAccount(Long accountId, String userName,
			String email, String qq) {
		CommonResult rs = new CommonResult();
		if (ValidateUtil.isEmail(email) == false) {
			rs.success = 1;
			rs.error = CommonConst.FORMAT_ERROR;
		} else if (accountDao.getByEmail(email) != null) {// 邮箱唯一性验证
			rs.success = 1;
			rs.error = CommonConst.FAILURE;
		} else if (StringUtil.isEmpty(userName) || userName.length() < 6) {
			rs.success = 1;
			rs.error = CommonConst.FORMAT_ERROR;
		} else if (accountDao.getByName(userName) != null) {
			rs.success = 1;
			rs.error = CommonConst.FORMAT_ERROR;
		} else {
			PassportAccount account = accountDao.getById(accountId);
			account.setEmail(email);
			account.setEmailState(0);
			account.setUserName(userName);
			account.setUserQQ(qq);
			account.setUnameState(1);
			try {
				//TODO
				accountDao.update(account);
				//TODO
				emailVerifyService.addEmail(email, 0);
				//MailUtil.sendVerifyEmail(emailResult.id,emailResult.securityKey, account.getEmail());
				rs.success = 0;
				rs.error = CommonConst.SUCCESS;
			} catch (Exception e) {
				log.error("PassportAccountServiceImpl.updateAccount()=======", e);
				rs.success = 1;
				rs.error = CommonConst.FAILURE;
			}

		}
		return rs;
	}*/

	/**
	 * 0返回Result类型， 1返回User类型
	 */
	@Override
	public Object acountLogin(String loginParam, String password, int rsType) {
		CommonResult result = new CommonResult();
		/* 用户名密码不能为空 */
		if (StringUtil.isEmpty(loginParam) || StringUtil.isEmpty(password)) {
			result.success = 1;
			result.error = CommonConst.FORMAT_ERROR;
			return result;
		}
		/* END */

		PassportAccount account = new PassportAccount();
		account = accountDao.getByParam(loginParam);
		if (account == null) {
			result.success = 1;
			result.error = CommonConst.NOTEXISTS;
			return result;
		} else  if(account.getState() == 1){
			result.success = 1;
			result.error = CommonConst.ACCOUNT_FROZEN;
			return result;
		}else{
			if (account.getPassword().equals(MD5Util.StrToMD5_32(password))) {// 登录成功！
				result.success = 0;
				result.id = account.getId();
				result.error = CommonConst.SUCCESS;
			} else {
				result.success = 1;
				result.error = CommonConst.PWD_ERROR;
	/*			PassportSecurity security = securityDao.getByAccountId(account
						.getId());
				if (security == null) {
					security = new PassportSecurity(account, 1);

					securityDao.insertSelective(security);
					result.loginFailure = 1;
				} else {
					Integer failureCount = 1 + security.getFailureCount();
					security.setFailureCount(failureCount);

					securityDao.updateByPrimaryKeySelective(security);
					if (failureCount >= 3) {
						result.loginFailure = failureCount;
						// result.success = 1;
						// result.error = CommonConst.AUT_FAILED;
					}
				}
	*/		}
		}
		if (rsType == 0) {
			return result;
		} else if (rsType == 1) {
			if (result.success == 0) {
				return account;
			} else {
				account.setId(-1L);
				return account;
			}
		}
		return null;
	}
	@Override
	public CommonResult checkUserName(String userName) {
		CommonResult result = new CommonResult();
		if (accountDao.getByName(userName) == null) {
			result.success = 0;
			result.error = CommonConst.SUCCESS;
		} else {
			result.success = 1;
			result.error = CommonConst.FAILURE;
		}
		return result;
	}
	@Override
	public CommonResult checkPhoneNumber(String phoneNumber) {
		CommonResult result = new CommonResult();
		if (accountDao.getByTelephone(phoneNumber) == null) {
			result.success = 0;
			result.error = CommonConst.SUCCESS;
		} else {
			result.success = 1;
			result.error = CommonConst.FAILURE;
		}
		return result;
	}
	@Override
	public CommonResult checkEmail(String email) {
		CommonResult result = new CommonResult();
		if (accountDao.getByEmail(email) == null) {
			result.success = 0;
			result.error = CommonConst.SUCCESS;
		} else {
			result.success = 1;
			result.error = CommonConst.FAILURE;
		}
		return result;
	}

	public CommonResult uniqueCheck(String userName, String telephone,
			String email) {
		CommonResult rs = new CommonResult();
		if (StringUtil.isNotEmpty(userName)) {
			rs = this.checkUserName(userName);
			if (rs.success > 0) {
				rs.error = CommonConst.EXISTS;
			}
		} else if (StringUtil.isNotEmpty(telephone)) {
			if (ValidateUtil.isMobile(telephone) == false) {
				rs.success = 1;
				rs.error = CommonConst.FORMAT_ERROR;
			} else {
				rs = this.checkPhoneNumber(telephone);
				if (rs.success > 0) {
					rs.error = CommonConst.EXISTS;
				}
			}

		} else if (StringUtil.isNotEmpty(email)) {
			if (ValidateUtil.isEmail(email) == false) {
				rs.success = 1;
				rs.error = CommonConst.FORMAT_ERROR;
			} else {
				rs = this.checkEmail(email);
				if (rs.success > 0) {
					rs.error = CommonConst.EXISTS;
				}
			}
		} else {
			rs.success = 1;
			rs.error = CommonConst.FORMAT_ERROR;
		}
		return rs;
	}

	public void SendSMS(String phoneNumber) {
		try {
			SmsSenter.sendSms(phoneNumber, phoneNumber);
		} catch (ClientProtocolException e) {
			log.error("PassportAccountServiceImpl.SendSMS=====", e);
		} catch (IOException e) {
			log.error("PassportAccountServiceImpl.SendSMS=====", e);
		}
	}
	public CommonResult deleteAcount(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * NS_B2C_M_PST_ACC_05 修改密码 手机 邮箱
	 */
	/*@Override
	public CommonResult modifyParam(Map<String, String> paramMap,
			String securityKey, int type, Long accountId) {
		CommonResult result = new CommonResult();
		result.success = 0;
		PassportAccount account = null;
		PassportSecKeyVerify skv = null;

		if (type != 3) {// type=3 直接修改
			result = this.skipCaptchaImg(accountId, paramMap);
			if (result.success == 1) {// 图片验证码的比较
				String captchaImg = paramMap.get("captchaImg");
				String code = paramMap.get("code");
				if (!code.equals(captchaImg)) {
					result.success = 1;
					result.error = CommonConst.AUT_FAILED;
					return result;
				}
			} else if (result.success == 2) {// 短信验证码的比较
				String captchaId_str = paramMap.get("captchaId");
				String captcha = paramMap.get("captcha");
				try {
					Long captchaId = Long.valueOf(captchaId_str);
					result = captchaService.checkCaptchaVerify(captchaId,
							captcha);
					if (0 != result.success) {

						return result;
					}
				} catch (NumberFormatException e) {
					log.error("PassportAccountServiceImpl.modifyParam=====", e);
					result.success = 1;
					result.error = CommonConst.AUT_FAILED;
					return result;
				}
			}
			// TODO 验证key的合法性之后，取得key中对应的accountId
			result = secKeyVerifyService.checkSecurityKey(securityKey, type,
					accountId, 0);// 无视最后一个时间参数，正常判断
			if (result.success == 0) {
				//TODO
				skv = secKeyVerifyService.getByKeyAndType(securityKey, type, 0);
				account = skv.getAccount();
				if (account == null) {
					result.success = 1;
					result.error = CommonConst.NOTEXISTS;
					return result;
				}
			} else {
				result.success = 1;
				result.error = CommonConst.OUT_OF_DATE;
				return result;
			}
		} else {
			account = accountDao.getById(accountId);
			if (account == null) {
				result.success = 1;
				result.error = CommonConst.NOTEXISTS;
				return result;
			}
		}

		*//*
		 * 走到这一步，可以知道 result.success==0 && account!=null, 可以直接对account进行修改
		 *//*
		String userName = paramMap.get("userName");
		String password = paramMap.get("password");
		String telephone = paramMap.get("telephone");
		String email = paramMap.get("email");

		boolean updateEmail = false;

		if (StringUtil.isNotEmpty(userName)) {
			if (userName.length() < 3) { // 用户名
				result.success = 1;
				result.error = CommonConst.FORMAT_ERROR;
			} else if (account.getUnameState() > 0) {
				result.success = 1;// 已经修改了一次了，不可再次修改
				result.error = CommonConst.FAILURE;
			} else {
				CommonResult unameCheck = uniqueCheck(userName, null, null);// 唯一性判断
				if (unameCheck.error != CommonConst.EXISTS) {
					account.setUserName(userName);
					account.setUnameState(1);
				}
			}
		}
		// 修改密码
		if (StringUtil.isNotEmpty(password)) {
			if (password.length() < 6) {
				result.success = 1;
				result.error = CommonConst.FORMAT_ERROR;
			} else {
				account.setPassword(MD5Util.StrToMD5_32(password));
			}
		}
		// 手机
		if (StringUtil.isNotEmpty(telephone) && skv.getGenerateType() == 1) {
			if (ValidateUtil.isMobile(telephone) == false) {
				result.success = 1;
				result.message = "手机号码非法" + telephone;
				result.error = CommonConst.FORMAT_ERROR;
			} else if (accountDao.getByTelephone(telephone) != null) { // 唯一性判断
				result.success = 1;
				result.message = "已注册的手机号码：" + telephone;
				result.error = CommonConst.EXISTS;// 修改的手机号码已被注册
			} else {
				if (skv.getGenerateType().equals(CommonConst.KEY_TEL)) { // 必须是手机生成的key，才可以修改手机
					account.setTelephone(telephone);
					account.setTelState(1);
					if (account.getUnameState() == 0)
						account.setUserName(telephone);
				}
			}
		}
		// 修改邮箱
		if (StringUtil.isNotEmpty(email)) {
			if (ValidateUtil.isEmail(email) == false) {
				result.success = 1;
				result.error = CommonConst.FORMAT_ERROR;
			} else if (accountDao.getByEmail(email) != null) { // 唯一性判断
				result.success = 1;
				result.error = CommonConst.EXISTS;
			} else {
				updateEmail = true;
				account.setEmail(email);
				// 修改邮箱后，需要把邮箱的state设为0，然后发送验证邮件
				account.setEmailState(0);
			}
		}

		if (result.success > 0) {
			return result;
		}
		//TODO
		accountDao.update(account);
		if (type != 3) {
			//TODO 因为要判断type是正数还是负数
			PassportSecKeyVerify secKeyVerify = secKeyVerifyDao
					.getBySecKeyAndType(securityKey, type, 0);
			// 将key过期
			secKeyVerify.setState(1);
			Calendar calendar = Calendar.getInstance();
			secKeyVerify.setActualExpirationTime(calendar.getTime());
			//TODO
			secKeyVerifyDao.update(secKeyVerify);
		}

		// 如果是修改邮箱，则需要发送一个验证邮件
		if (updateEmail) {
			//TODO
			emailVerifyService.addEmail(email, 0);
			//MailUtil.sendVerifyEmail(emailResult.id, emailResult.securityKey, account.getEmail());
		}
		return result;
	}
	@Override
	public CommonResult verifyEmail(String url, String verify_key) {
		CommonResult rs = new CommonResult();
		rs.success = 0;
		String id = Base64Util.decodeStr(url);
		id = id.substring(id.lastIndexOf("/") + 1, id.length());
		Long emailVerifyId = null;
		try {
			emailVerifyId = Long.valueOf(id);
		} catch (Exception e) {
			rs.success = 1;
			rs.error = CommonConst.FAILURE;
			return rs;
		}
		PassportEmailVerify emailVerify = emailVerifyDao.getById(emailVerifyId);
		rs.id = emailVerifyId;
		rs.securityKey = verify_key;
		if (emailVerify == null
				|| accountDao.getByEmail(emailVerify.getEmail()) == null) {// 操作失败(id对应的数据已被删除)
			rs.success = 1;
			rs.error = CommonConst.NOTEXISTS;
			return rs;
		} else if (emailVerify.getState() == 1) {// 已通过验证了
			rs.success = 0;
			rs.error = CommonConst.SUCCESS;
			return rs;
		} else if (!emailVerify.getVerifyKey().equals(verify_key)) {// key错误
			rs.success = 1;
			rs.error = CommonConst.AUT_FAILED;
		}
		if (rs.success == 1) {
			return rs;
		}

		// 判断时间是否过期
		Calendar nowTime = Calendar.getInstance();
		Date expiryTime = emailVerify.getExpiryTime();
		if (nowTime.getTime().getTime() > expiryTime.getTime()) {
			//TODO 过期就删除，方便下次重新验证
			emailVerifyDao.delete(emailVerify);
			rs.success = 1;
			rs.error = CommonConst.OUT_OF_DATE;
		} else {
			emailVerify.setState(1);
			try {
				//TODO
				emailVerifyDao.update(emailVerify);
				PassportAccount account = accountDao.getByEmail(emailVerify
						.getEmail());
				account.setEmailState(1);
				accountDao.update(account);
				rs.success = 0;
				rs.error = CommonConst.SUCCESS;

			} catch (Exception e) {
				log.error("PassportAccountServiceImpl.verifyEmail=====", e);
				rs.success = 1;
				rs.error = CommonConst.FAILURE;
			}
		}
		return rs;
	}
	@Override
	public void updateAccount(PassportAccount account) {
		//TODO
		accountDao.update(account);
	}

	*//**
	 * type 0-注册验证邮件，1-找回密码， 2-直接发送邮件，需要提供subject和content
	 *//*
	@Override
	public CommonResult postEmail(int type, String email, String subject,
			String content) {
		CommonResult result = new CommonResult();
		try {
			if (type == 0 || type == 1) {
				//TODO
				result = emailVerifyService.addEmail(email, type);
			} else if (type == 2) {
				MailUtil.sendMail(email, subject, content);
			}
		} catch (Exception e) {
			log.error("PassportAccountServiceImpl.postEmail=====", e);
			result.success = 1;
			result.error = CommonConst.FAILURE;
		}
		return result;
	}

	*//**
	 * @param paramMap
	 *            userName, telephone, email, password
	 * @retrun 0-可以跳过 1-不可以跳过
	 *//*
	@Override
	public CommonResult skipCaptchaImg(Long accountId,
			Map<String, String> paramMap) {
		CommonResult result = new CommonResult();
		result.success = 0;
		if (paramMap == null || accountId == null || accountId < 0) {
			result.success = 1;
			result.error = CommonConst.FAILURE;
			return result;
		}
		PassportAccount account = accountDao.getById(accountId);
		String userName = paramMap.get("userName");
		String password = paramMap.get("password");
		String telephone = paramMap.get("telephone");
		String email = paramMap.get("email");

		if (StringUtil.isNotEmpty(userName) && account.getUnameState() == 1) {
			result.success = 1;
		}
		if (StringUtil.isNotEmpty(password)) {
			result.success = 1;
		}
		// 修改手机号码，需要先验证手机，所以需要比对短信验证码.account==null?因为有的账户原先没有设置手机号码，所以为null
		if (StringUtil.isNotEmpty(telephone)) {
			result.success = 2;
		}
		if (StringUtil.isNotEmpty(email) && account.getEmailState() == 1) {
			result.success = 1;
		}

		return result;
	}
	@Override
	public PassportAccount checkLogin(Long id, String password) {
		if (id == null) {
			return null;
		}
		PassportAccount account = accountDao.getById(id);
		if (account == null
				|| !account.getPassword().equals(password)) {
			return null;
		} else {
			return account;
		}
	}
@Override
	public Map<String, Object> getByShopId(Long shopId) {
		List<PassportAccount> accountList = accountDao.getByShopId(shopId);
		return CommonUtils.getSuccess(accountList);
	}
	@Override
	public Map<String, Object> addOrUpdateShopId(PassportAccount account, Long shopId) {
		SaleShop saleShop = saleShopDao.getById(shopId);
		if(saleShop == null) {
			log.error("PassportAccountServiceImpl.addOrUpdateShopId(), 店铺不存在！！");
			return CommonUtils.getError();
		}
		Long notExist_id = new Long(0);
		Long accountId = account.getId();
		PassportAccount db_account = null;
		boolean isAdd = true;
		if(notExist_id.equals(accountId)) {
			db_account = new PassportAccount();
		} else {
			isAdd = false;
			db_account = accountDao.getById(accountId);
			if(db_account == null) {
				return CommonUtils.getError(CommonConst.NOTEXISTS);
			}
		}
		String userName = account.getUserName();
		String email = account.getEmail();
		String telephone = account.getTelephone();
		String qq = account.getUserQQ();
		String servierPwd = account.getServierPwd();
		
		PassportAccount emailAccount = accountDao.getByEmail(email);
		PassportAccount nameAccount = accountDao.getByName(userName);
		
		if (ValidateUtil.isEmail(email) == false ) {
			return CommonUtils.getError(8);
		} else if(StringUtil.isEmpty(userName)){
			return CommonUtils.getError(5);
		} else if(ValidateUtil.isMobile(telephone) == false){
			return CommonUtils.getError(6);
		} else if(StringUtil.isEmpty(servierPwd)){
			return CommonUtils.getError(9);
		} else if(StringUtil.isEmpty(qq)){
			return CommonUtils.getError(7);
		} else if ((emailAccount != null && !emailAccount.getId().equals(accountId))
				|| (nameAccount != null && !nameAccount.getId().equals(accountId))) {// 邮箱唯一性验证
			return CommonUtils.getError(CommonConst.FAILURE);
		} else {
			db_account.setEmail(email);
			db_account.setUserName(userName);
			db_account.setUserQQ(qq);
			db_account.setTelephone(telephone);
			if(StringUtil.isNotEmpty(servierPwd) && !"undefined".equals(servierPwd)) {
				db_account.setPassword(servierPwd);
			}
			try {
				if(isAdd) {
					db_account.setUnameState(0);
					db_account.setTelState(0);
					db_account.setEmailState(0);
					db_account.setState(0);
					//TODO
					accountDao.add(db_account);
					
					
					// 插入店铺对应
					SaleShopAccount ssa = new SaleShopAccount();
					ssa.setAccount(db_account);
					ssa.setShop(saleShop);
					//TODO
					saleShopAccountDao.add(ssa);
					//TODO 插入用户角色
					roleAccountService.add(db_account.getId(), CommonConst.SELLER);
					//用户信息
					PassportAccountInfo accountInfo = new PassportAccountInfo();
					accountInfo.setAccount(db_account);
					//TODO
					accountInfoDao.add(accountInfo);
					//用户等级
					PassportLevel level = new PassportLevel();
					level.setAccount(account);
					level.setLevel(1);
					level.setCredits(0);
					//TODO
					levelDao.add(level);
					
				} else {
					if(StringUtil.isNotEmpty(servierPwd) && !"undefined".equals(servierPwd)) {
						String updatePwd = MD5Util.StrToMD5_32(servierPwd);
						db_account.setPassword(updatePwd);
					}
					//TODO
					accountDao.update(db_account);
				}
				return CommonUtils.getSuccess();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("PassportAccountServiceImpl.addOrUpdateShopId()=======", e);
				return CommonUtils.getError();
			}

		}
	}
	@Override
	public Map<String, Object> deleteByShopId(Long accountId, Long shopId) {
		PassportAccount db_account = accountDao.getById(accountId);
		SaleShopAccount saleShopAccount = saleShopAccountDao.getByAccountId(accountId);
		if(db_account != null && saleShopAccount != null 
				&& (db_account.getId().equals(saleShopAccount.getAccount().getId()))) {
			db_account.setState(1);
			try {
				//TODO
				accountDao.update(db_account);
				return CommonUtils.getSuccess();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("PassportAccountServiceImpl.deleteByShopId()=======", e);
				return CommonUtils.getError();
			}
		}
		return CommonUtils.getError();
	}
	@Override
	public PassportLightAccount getLightAccountById(Long accountId) {
		if(CommonUtils.validateLong(accountId)) {
			//TODO
			return accountDao.getLightById(accountId);
		}
		return null;
	}
	@Override
	public Map<String, Object> getUserList(Integer lastPage, Integer limit, String userName) {
		//TODO
		PageUtil page = accountDao.getPageList((lastPage-1) * limit, limit, userName);
		return CommonUtils.getSuccess(page);
	}
	@Override
	public Map<String, Object> pwdReset(Long passportId) {
		PassportAccount pa = accountDao.getById(passportId);
		pa.setPassword(MD5Util.StrToMD5_32(CommonConst.ACCOUNT_INIT_PWD));
		//TODO
		accountDao.update(pa);
		return CommonUtils.getSuccess();
	}
	@Override
	public Map<String, Object> unForzen(Long passportId) {
		PassportAccount pa = accountDao.getById(passportId);
		pa.setState(CommonConst.ACCOUNT_NORMAL);
		//TODO
		accountDao.update(pa);
		return CommonUtils.getSuccess();
	}
	@Override
	public Map<String, Object> forzen(Long passportId) {
		PassportAccount pa = accountDao.getById(passportId);
		pa.setState(CommonConst.ACCOUNT_FROZEN);
		//TODO
		accountDao.update(pa);
		return CommonUtils.getSuccess();
	}
	@Override
	public void save(PassportAccount account) {
		//TODO
		accountDao.add(account);
	}
	@Override
	public Map<String, Object> getUnFrozenUserlist(Integer lastPage, Integer limit,String keyWord) {
		//TODO
		PageUtil page = accountDao.getUnFrozenUserPageList((lastPage-1) * limit, limit, keyWord);
		return CommonUtils.getSuccess(page);
	}
	@Override
	public void wx_save(Long shopId, PassportAccount account) {
		//TODO
		accountDao.wx_save(account);

		// 新用户注册送积分，积分数从配置表读取；2018-3-1
		SaleShopPara saleShopPara = saleShopParaDao.getByParameterName("wx-register-credit-amount", shopId);
		Integer creditAmount = 0;
		if (saleShopPara != null) {
			creditAmount = NumberUtil.parseIntegerFromStr(saleShopPara.getParameterValue());
			if (creditAmount == null) {
				creditAmount = 0;
			}
		}

		Date currDate = new Date();
		PassportCredit credit = new PassportCredit();
		credit.setAccount(account);
		credit.setUsedValue(0);
		credit.setCurrValue(creditAmount);
		credit.setFxUsedValue(0);
		credit.setFxCurrValue(0);
		credit.setTotalValue(credit.getCurrValue() + credit.getFxCurrValue());
		credit.setGetTime(currDate);
		credit.setState(0);
		//TODO
		creditDao.add(credit);

		// 积分记录
		PassportCreditRecord creditRecord = new PassportCreditRecord();
		creditRecord.setOperateTime(currDate);
		creditRecord.setOperateDesc(CommonConst.CREDIT_WAY_ADD_REGISTER);
		creditRecord.setCreditValue(creditAmount);
		creditRecord.setCreditType(CommonConst.CREDIT_TYPE_CONSUME);
		creditRecord.setCredit(credit);
		creditRecord.setAccount(account);
		creditRecord.setOperateType(CommonConst.CREDIT_OP_TYPE_ADD);
		//TODO
		creditRecordDao.add(creditRecord);
	}*/
}
