package com.mavis.smart.mail.callback;

public interface MailCallback {
	void onSuccess();
	void onFailure();
	void onCompletion();
}
