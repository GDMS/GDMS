package im.lich.gdms.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Paper extends IdEntity {
	private static final long serialVersionUID = 922932418102511950L;

	@Column(length = 50, nullable = false)
	private String description;//论文名称、描述

	@Column(length = 250, nullable = false, unique = true)
	private String filename;//实际论文名称

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
