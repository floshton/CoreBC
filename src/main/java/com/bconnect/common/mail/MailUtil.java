package com.bconnect.common.mail;

import com.bconnect.common.util.CommonUtils;

/**
 *
 * @author Jorge Rodriguez
 */
public class MailUtil {

    public static String getSite(String site){
        return CommonUtils.openUrl(site);
    }
}
