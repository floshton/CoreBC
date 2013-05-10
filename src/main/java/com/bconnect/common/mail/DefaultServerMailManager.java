package com.bconnect.common.mail;

import com.bconnect.common.bean.AttachmentBean;
import com.bconnect.common.bean.MailMessageBean;
import com.bconnect.common.bean.personal.PersonaBean;
import com.bconnect.common.logging.CommonLogger;
import com.bconnect.common.util.CommonConstants;
import com.bconnect.common.util.ResourcesManager;
import com.bconnect.common.util.CommonUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author Jorge Rodriguez
 */
public class DefaultServerMailManager {

    private static MimeMessage message;
    private static Multipart messageContent;
    private static MailMessageBean messageBean;
    private static InternetAddress from;
    private static InternetAddress to;
    private static Logger logger;

    public DefaultServerMailManager() {
    }

    public boolean sendMessage(MailMessageBean messageBean, Session session) {
        boolean success = false;
        DefaultServerMailManager.messageBean = messageBean;
        try {
            message = new MimeMessage(session);
            messageContent = new MimeMultipart();

            setRecipients();
            setRecipientsCC();
            setRecipientsBCC();

            setSubject();
            setBody();

            setAttachments();
            setFiles();

            message.setContent(messageContent);

            Transport.send(message);
            success = true;
        } catch (AddressException ae) {
            logger.error("Ocurrió un error al momento de enviar el mensaje de correo", ae);
        } catch (MessagingException me) {
            logger.error("Ocurrió un error al momento de enviar el mensaje de correo", me);
        } catch (UnsupportedEncodingException me) {
            logger.error("Ocurrió un error al momento de enviar el mensaje de correo", me);
        }

        return success;
    }

    private void setContentType(BodyPart messageBodyPart) throws MessagingException {
        String contentType = messageBean.getContentType();
        if (!CommonUtils.hasValue(contentType)) {
            contentType = MailMessageBean.HTML + ";charset=\"" + MailMessageBean.ENCODING_ISO_8859_1 + "\"";
        }
        messageBodyPart.setContent(messageBean.getBody(), contentType);
    }

    private void setSubject() throws MessagingException {
        if (messageBean.getSubject() != null) {
            message.setSubject(messageBean.getSubject());
        }
    }

    private void setBody() throws MessagingException {
        BodyPart messageBodyPart = new MimeBodyPart();
        if (messageBean.getBody() != null) {
            messageBodyPart.setText(messageBean.getBody());
        }
        setContentType(messageBodyPart);

        messageContent.addBodyPart(messageBodyPart);
    }

    private void setRecipients() throws AddressException, MessagingException, UnsupportedEncodingException {
        List recipients = messageBean.getRecipients();
        addRecipients(recipients, Message.RecipientType.TO);
    }

    private void setRecipientsCC() throws AddressException, MessagingException, UnsupportedEncodingException {
        List recipients = messageBean.getRecipientsCC();
        addRecipients(recipients, Message.RecipientType.CC);
    }

    private void setRecipientsBCC() throws AddressException, MessagingException, UnsupportedEncodingException {
        List recipients = messageBean.getRecipientsBCC();
        addRecipients(recipients, Message.RecipientType.BCC);
    }

    private void addRecipients(List recipients, Message.RecipientType recipientType)
            throws AddressException, MessagingException, UnsupportedEncodingException {
        for (int i = 0; i < recipients.size(); i++) {
            addRecipient(recipients.get(i), recipientType);
        }
    }

    private void addRecipient(Object recipientObject, Message.RecipientType recipientType)
            throws AddressException, MessagingException, UnsupportedEncodingException {
        InternetAddress theRecipient = null;
        if (recipientObject != null) {
            if (recipientObject instanceof PersonaBean) {
                PersonaBean recipient = (PersonaBean) recipientObject;
                if (CommonUtils.hasValue(recipient.getEmail())) {
                    theRecipient = new InternetAddress(recipient.getEmail());
                    if (CommonUtils.hasValue(recipient.getNombreCompleto())) {
                        theRecipient = new InternetAddress(recipient.getEmail(), recipient.getNombreCompleto());
                    }
                }
            } else if (recipientObject instanceof String) {
                theRecipient = new InternetAddress((String) recipientObject);
            }
            message.addRecipient(recipientType, theRecipient);
        }
    }

    private void setAttachments() throws MessagingException {
        List attachments = messageBean.getAttachments();

        if (attachments.size() > 0) {
            for (int i = 0; i < attachments.size(); i++) {
                AttachmentBean attachment = (AttachmentBean) attachments.get(i);
                String filePath = attachment.getRealPath();
                File file = new File(filePath);

                BodyPart attachmentPart = getBodyPart(file);
                messageContent.addBodyPart(attachmentPart);
            }
        }
    }

    private void setFiles() throws MessagingException {
        List<AttachmentBean> files = messageBean.getFiles();

        if (files.size() > 0) {
            for (AttachmentBean attachment : files) {
                BodyPart attachmentPart = getBodyPart(
                        attachment.getInputStream(), attachment.getMimeType(), attachment.getNombre());
                messageContent.addBodyPart(attachmentPart);
            }
        }
    }

    private BodyPart getBodyPart(File file) {
        BodyPart attachmentPart = null;
        try {
            attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file.getPath());
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(file.getName());
        } catch (MessagingException ex) {
            logger.error("Error cuando se intentaba agregar el attachment", ex);
        }
        return attachmentPart;
    }

    private BodyPart getBodyPart(InputStream is, String mimeType, String fileName) {
        BodyPart attachmentPart = null;
        try {
            attachmentPart = new MimeBodyPart();
            DataSource source = new ByteArrayDataSource(is, mimeType);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(fileName);
        } catch (MessagingException ex) {
            logger.error("Error cuando se intentaba agregar el attachment", ex);
        } catch (IOException ex) {
            logger.error("Error cuando se intentaba agregar el attachment", ex);
        }
        return attachmentPart;
    }

    private BodyPart getBodyPart(byte[] data, String mimeType, String fileName) {
        BodyPart attachmentPart = null;
        try {
            attachmentPart = new MimeBodyPart();
            DataSource source = new ByteArrayDataSource(data, mimeType);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(fileName);
        } catch (MessagingException ex) {
            logger.error("Error cuando se intentaba agregar el attachment", ex);
        }
        return attachmentPart;
    }
}
