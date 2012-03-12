package im.lich.gdms.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class News extends IdEntity {
	private static final long serialVersionUID = -4082879836928849616L;

	@Column(length = 50, nullable = false)
	private String title;//消息标题

	@Column(length = 250, nullable = false)
	private String message;//消息内容

	@Column(length = 20, nullable = false)
	private String receiver;//发送对象

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

}
