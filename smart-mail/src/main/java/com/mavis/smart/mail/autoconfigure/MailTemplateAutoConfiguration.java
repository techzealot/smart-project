package com.mavis.smart.mail.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

import com.mavis.smart.mail.template.MailTemplate;

@Configuration
@ConditionalOnBean(value = { MailSender.class, freemarker.template.Configuration.class })
public class MailTemplateAutoConfiguration {
	@Autowired
	private MailSender mailSender;
	@Autowired
	private freemarker.template.Configuration freemarkerConfig;
	@Bean
	public MailTemplate getMailTemplate() {
		return null;
	}
}
