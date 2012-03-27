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
public class Paper extends IdEntity {
	private static final long serialVersionUID = 922932418102511950L;

	private String description = "";//论文名称、描述
	private String filename = "";//实际论文名称

	public Paper() {
		super();
	}

	public Paper(String description, String filename) {
		super();
		this.description = description;
		this.filename = filename;
	}

	@Column(nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(nullable = false, unique = true)
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
