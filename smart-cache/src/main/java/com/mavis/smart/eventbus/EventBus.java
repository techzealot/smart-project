package com.mavis.smart.eventbus;

public interface EventBus {
	void publish(String topic);

	void publish(String topic, String key);

	void subscribe(String topic);

	void subscribe(String topic, String key);

}
