package com.mavis.smart.mail.template;

import org.springframework.mail.MailSender;

import freemarker.template.Template;

public interface MailTemplate {

	MailSender getMailSender();

	void setMailSender();

	Template getTemplate();

	Template setTemplate();
}
