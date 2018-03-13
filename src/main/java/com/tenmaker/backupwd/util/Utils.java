package com.tenmaker.backupwd.util;

import java.util.regex.Pattern;

public class Utils {
    public static boolean validMobile(String mobile) {
        if (Pattern.matches("^((13)|(14)|(15)|(17)|(18))[0-9]{9}", mobile)) {
            return true;
        }
        return false;
    }

}
