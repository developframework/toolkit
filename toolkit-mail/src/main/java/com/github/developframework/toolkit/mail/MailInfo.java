package com.github.developframework.toolkit.mail;


import com.github.developframework.toolkit.base.NameValuePair;
import lombok.Getter;
import lombok.Setter;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author qiuzhenhao
 */
@Getter
public class MailInfo {

    private Set<NameValuePair<String, String>> toSet = new HashSet<>();

    @Setter
    private String fromName;

    @Setter
    private String subject;

    @Setter
    private String content;

    @Setter
    private String contentType = "text/html;charset=UTF-8";

    @Setter
    private String charset = "UTF-8";

    protected MimeMessage createMimeMessage(Session session, InternetAddress fromAddress) throws MessagingException {

        MimeMessage message = new MimeMessage(session);
        message.setFrom(fromAddress);
        InternetAddress[] addresses = toSet.stream().map(pair -> {
            try {
                return new InternetAddress(pair.getName(), pair.getValue(), charset);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }).toArray(InternetAddress[]::new);
        message.setRecipients(MimeMessage.RecipientType.TO, addresses);
        message.setSubject(subject, charset);
        message.setContent(content, contentType);
        message.saveChanges();
        return message;
    }

    public void addTo(String to, String toName) {
        toSet.add(new NameValuePair<>(to, toName));
    }
}
