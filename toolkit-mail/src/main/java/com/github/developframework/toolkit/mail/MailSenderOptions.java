package com.github.developframework.toolkit.mail;

import lombok.Data;

/**
 * @author qiuzhenhao
 */
@Data
public class MailSenderOptions {

    private String protocol = "smtp";

    private String smtpHost;

    private Integer smtpPort = 465;

    private String emailAccount;

    private String emailPassword;

    private boolean smtpAuth = true;

    private boolean isSSL = true;

}
