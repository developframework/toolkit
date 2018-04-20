package com.github.developframework.toolkit.mail;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author qiuzhenhao
 */
public class JavaxMailSender extends MailSender {

    private boolean debug = true;

    private Session session;

    public JavaxMailSender(MailSenderOptions options) {
        super(options);
    }

    @Override
    public void configSender(MailSenderOptions options) {
        Objects.requireNonNull(options.getEmailAccount(), "email account is null.");
        Objects.requireNonNull(options.getEmailPassword(), "email password is null.");
        Objects.requireNonNull(options.getSmtpHost(), "smtp host is null.");
        Objects.requireNonNull(options.getSmtpPort(), "smtp port account is null.");

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", options.getProtocol());
        props.setProperty("mail.smtp.host", options.getSmtpHost());
        props.setProperty("mail.smtp.auth", String.valueOf(options.isSmtpAuth()));
        props.setProperty("mail.smtp.port", String.valueOf(options.getSmtpPort()));
        if (options.isSSL()) {
            props.setProperty("mail.smtp.ssl.enable","true");
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", String.valueOf(options.getSmtpPort()));
        }
        session = Session.getInstance(props);
        session.setDebug(debug);
    }

    @Override
    public void sendMail(MailInfo mailInfo) {
        try {
            InternetAddress fromAddress = new InternetAddress(options.getEmailAccount(), mailInfo.getFromName(), mailInfo.getCharset());
            MimeMessage mimeMessage = mailInfo.createMimeMessage(session, fromAddress);
            Transport transport = session.getTransport();
            transport.connect(options.getEmailAccount(), options.getEmailPassword());
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
