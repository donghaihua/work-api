package com.tenmaker.backupwd.controller;

import com.tenmaker.backupwd.components.CommonConst;
import com.tenmaker.backupwd.components.CommonResult;
import com.tenmaker.backupwd.components.CommonUtils;
import com.tenmaker.backupwd.model.PassportAccount;
import com.tenmaker.backupwd.service.PassportAccountService;
import com.tenmaker.backupwd.util.MD5Util;
import com.tenmaker.backupwd.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Hayward
 * @date 2018/3/13 0013  12:57
 */
@Controller

public class LoginController {
    @Autowired
    private PassportAccountService accountService;

    @RequestMapping(value = "/passport/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("***************************");

        CommonResult rs = new CommonResult();
        HttpSession session = CommonUtils.getSession(request);
        String loginParam = request.getParameter("loginParam");
        String password = request.getParameter("password");

        rs = (CommonResult) accountService.acountLogin(loginParam, password, 0);

        if (rs.success == 0) {
            PassportAccount account = (PassportAccount) accountService.acountLogin(loginParam, password, 1);

            session.setAttribute(CommonConst.LOGIN_ID, account.getId());
            session.setAttribute(CommonConst.LOGIN_PARAM, account.getUserName());
            session.setAttribute(CommonConst.LOGIN_PWD, MD5Util.StrToMD5_32(password));


            return rs;
        } else if (rs.error.equals(CommonConst.NOTEXISTS) || rs.error.equals(CommonConst.PWD_ERROR)) {
            // 针对用户名进行session记录
            Object loginFailure_obj = session.getAttribute(
                    CommonConst.LOGIN_FAILURE);
            Integer failureCount = 0;
            try {
                if (loginFailure_obj != null) {
                    failureCount = NumberUtil.parseIntegerFromStr(loginFailure_obj.toString());
                    failureCount++;
                } else {
                    failureCount = 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
                failureCount = 1;
            }
            session.setAttribute(CommonConst.LOGIN_FAILURE,
                    failureCount);
            rs.loginFailure = failureCount;
        } else { // 显示验证码
            rs.loginFailure = 3;
            session.setAttribute(CommonConst.LOGIN_FAILURE, 3);// 随意给一个>=3的数字
		}

        return rs;
    }

}
