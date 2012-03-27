package im.lich.gdms.core.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class News extends IdEntity {
	private static final long serialVersionUID = -4082879836928849616L;

	private String title = "";//消息标题
	private String message = "";//消息内容
	private String receiver = "";//发送对象

	public News() {
		super();
	}

	public News(String title, String message, String receiver) {
		super();
		this.title = title;
		this.message = message;
		this.receiver = receiver;
	}

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(nullable = false)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(nullable = false)
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
}
