package com.tenmaker.backupwd.mapper;


import com.tenmaker.backupwd.model.PassportAccount;
import com.tenmaker.backupwd.util.PageUtil;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by HTJ on 14-8-23.
 */
@Mapper
public interface PassportAccountDao {
	@Insert("insert into tbl_passport_account (id, user_type, userName,\n" +
			"      password, telephone, email,\n" +
			"      user_qq, uname_state, tel_state,\n" +
			"      email_state, state, create_time\n" +
			"      )\n" +
			"      values (#{id}, #{userType}, #{username},\n" +
			"      #{password}, #{telephone}, #{email},\n" +
			"      #{userQQ}, #{unameState}, #{telState},\n" +
			"      #{emailState}, #{state}, #{createTime}\n" +
			"      )")
	public void add(PassportAccount account);
	
	public void wx_save(PassportAccount account);

	public void update(PassportAccount account);
	//g根据用户名查询用户
	@Select("select * from tbl_passport_account where userName=#{userName}")
	public PassportAccount getByName(String userName);
	//根据手机号查询用户记录
	@Select("select * from tbl_passport_account where telephone=#{telephone}")
	public PassportAccount getByTelephone(String telephone);
	//根据邮箱查询用户记录
	@Select("select * from tbl_passport_account where email=#{emil}")
	public PassportAccount getByEmail(String email);

	//根据id查询用户的id
	@Select("select * from tbl_passport_account where id=#{accountId}")
	public PassportAccount getById(Long accountId);

	//public PassportLightAccount getLightById(Long accountId);
	//根据用户名或邮箱或手机号查询用户
	@Select("select * from tbl_passport_account where userName=#{param} or email=#{param} or telephone=#{param}")
	public PassportAccount getByParam(String param);

	public boolean delete(Long accountId);

	@Select("SELECT * FROM tbl_passport_account pa LEFT JOIN tbl_sale_shop_account ssa ON pa.id=ssa.account_id WHERE pa.state=0 AND ssa.shop_id=#{shopId} ORDER BY pa.id")
	public List<PassportAccount> getByShopId(Long shopId);

	public PageUtil getPageList(Integer lastPage, Integer limit, String userName);

	public List<PassportAccount> getAllUserList();
	public PageUtil getUnFrozenUserPageList(int i, Integer limit, String keyWord);

	public PassportAccount getStatusAccountById(Long id);

}
