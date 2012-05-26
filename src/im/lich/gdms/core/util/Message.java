package im.lich.gdms.core.util;

import im.lich.gdms.base.BaseObject;

public class Message extends BaseObject {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
}
