package im.lich.gdms.core.util;

import java.util.List;

import com.google.common.collect.Lists;

import im.lich.gdms.base.BaseObject;

public class Message extends BaseObject {
	private String message;

	private List<String> messages = Lists.newArrayList();

	private Object object;

	private List<Object> objects = Lists.newArrayList();

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}

	@Override
	public String toString() {
		return message;
	}
}
