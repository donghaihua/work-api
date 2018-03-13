/*
package com.tenmaker.backupwd.controller.client;

import com.tenmaker.common.controller.BaseController;
import com.tenmaker.common.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @Value("${wilddog.backup.dir}")
    private String wilddogBackupDir;

    @RequestMapping("/login")
    public AjaxResult test() {
        logger.debug("-----in test-----");
        try {
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.failed(e.getMessage());
        }
    }

}
*/
