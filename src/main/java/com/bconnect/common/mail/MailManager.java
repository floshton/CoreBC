package com.bconnect.common.mail;

import com.bconnect.common.bean.MailMessageBean;

/**
 *
 * @author Jorge Rodriguez
 */
public interface MailManager {

    
    public boolean sendMessage(MailMessageBean message);
    
}
