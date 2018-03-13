/*
package com.tenmaker.backupwd.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsUtil {
    private static final Logger logger = LoggerFactory.getLogger(SmsUtil.class);

    @Value("${sms.app.id}")
    private String smsAppId;
    @Value("${sms.app.key}")
    private String smsAppKey;

    public void sendSingle(String mobile, String code) throws Exception {
        logger.debug("sendSingle to {}，验证码={}", mobile, code);
        SmsSingleSender sender = new SmsSingleSender(Integer.parseInt(smsAppId), smsAppKey);
        SmsSingleSenderResult result = sender.send(0, "86", mobile, "【十匠】您的验证码是"
                + code + "，请在10分钟内输入", "", "");
        logger.debug("短信发送结果：" + result);
    }
}
*/
