package com.mavis.smart.mail.template;

import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;

import com.mavis.smart.mail.callback.MailCallback;

import freemarker.template.Template;

public interface MailTemplate {

	MailSender getMailSender();

	void setMailSender();

	Template getTemplate();

	Template setTemplate();

	void sendMailMessage(MailMessage mailMessage) throws Exception;

	default void sendMailMessage(MailMessage mailMessage, MailCallback callback) throws Exception {
		try {
			this.sendMailMessage(mailMessage);
			callback.onSuccess();
		} catch (Exception e) {
			callback.onFailure();
		} finally {
			callback.onCompletion();
		}
	}
}
