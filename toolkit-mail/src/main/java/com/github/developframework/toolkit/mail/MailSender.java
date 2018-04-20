package com.github.developframework.toolkit.mail;

/**
 * @author qiuzhenhao
 */
public abstract class MailSender {

    protected MailSenderOptions options;

    public MailSender(MailSenderOptions options) {
        this.options = options;
        configSender(options);
    }

    public abstract void configSender(MailSenderOptions options);

    public abstract void sendMail(MailInfo mailInfo);
}
