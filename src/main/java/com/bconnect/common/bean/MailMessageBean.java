package com.bconnect.common.bean;

import com.bconnect.common.bean.personal.PersonaBean;
import com.bconnect.common.util.CommonUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jorge Rodriguez
 */
public class MailMessageBean {

    protected PersonaBean sender;
    protected List<String> recipients;
    protected List<String> recipientsCC;
    protected List<String> recipientsBCC;
    protected String contentType;
    protected String subject;
    protected Date creationDate;
    protected String replyTo;
    protected String body;
    protected String ip;
    protected String protocol;
    protected List<AttachmentBean> attachments;
    protected List<AttachmentBean> files;
    public static final String HTML = "text/html";
    public static final String XML = "text/xml";
    public static final String TEXT = "text/plain";
    public static final String ENCODING_ISO_8859_1 = "ISO-8859-1";

    public MailMessageBean() {
        recipients = new ArrayList<String>();
        recipientsCC = new ArrayList<String>();
        recipientsBCC = new ArrayList<String>();
        attachments = new ArrayList<AttachmentBean>();
        files = new ArrayList<AttachmentBean>();
    }

    public PersonaBean getSender() {
        return sender;
    }

    public void setSender(String senderEmail) {
        PersonaBean senderPersona = new PersonaBean();
        senderPersona.setEmail(senderEmail);
        this.sender = senderPersona;
    }

    public void setSender(PersonaBean persona) {
        this.sender = persona;
    }

    public void addRecipient(String direccion) {
        this.recipients.add(direccion);
    }

    public void addRecipient(PersonaBean persona) {
        String email = persona.getEmail();
        if (CommonUtils.hasValue(email)) {
            this.recipients.add(email);
        }
    }

    public void addRecipientCC(String direccion) {
        this.recipientsCC.add(direccion);
    }

    public void addRecipientCC(PersonaBean persona) {
        String email = persona.getEmail();
        if (CommonUtils.hasValue(email)) {
            this.recipientsCC.add(email);
        }
    }

    public void addRecipientBCC(String direccion) {
        this.recipientsBCC.add(direccion);
    }

    public void addRecipientBCC(PersonaBean persona) {
        String email = persona.getEmail();
        if (CommonUtils.hasValue(email)) {
            this.recipientsBCC.add(email);
        }
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public List<String> getRecipientsCC() {
        return recipientsCC;
    }

    public void setRecipientsCC(List<String> recipientsCC) {
        this.recipientsCC = recipientsCC;
    }

    public List<String> getRecipientsBCC() {
        return recipientsBCC;
    }

    public void setRecipientsCCO(List<String> recipientsBCC) {
        this.recipientsBCC = recipientsBCC;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setBodyUrl(String url) {
        this.body = CommonUtils.openUrl(url);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public List<AttachmentBean> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentBean> attachments) {
        this.attachments = attachments;
    }

    public void addAttachment(AttachmentBean fileName) {
        this.attachments.add(fileName);
    }

    public List<AttachmentBean> getFiles() {
        return files;
    }

    public void setFiles(List<AttachmentBean> files) {
        this.files = files;
    }

    public void addFile(AttachmentBean file) {
        this.files.add(file);
    }
}
