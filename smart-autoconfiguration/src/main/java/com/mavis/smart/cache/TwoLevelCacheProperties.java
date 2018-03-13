package com.mavis.smart.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cache.twolevel")
public class TwoLevelCacheProperties {
	private String changeTopic ;
	
	private String clearTopic ;

	public String getChangeTopic() {
		return changeTopic;
	}

	public void setChangeTopic(String changeTopic) {
		this.changeTopic = changeTopic;
	}

	public String getClearTopic() {
		return clearTopic;
	}

	public void setClearTopic(String clearTopic) {
		this.clearTopic = clearTopic;
	}
	
}
