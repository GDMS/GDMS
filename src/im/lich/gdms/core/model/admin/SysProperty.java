package im.lich.gdms.core.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysProperty extends IdEntity {
	private static final long serialVersionUID = -8244930104699243848L;

	@Column(length = 50, nullable = false, unique = true)
	private String propKey;//关键字段

	@Column(length = 50, nullable = false)
	private String propVal;//数值

	@Column(length = 250, nullable = false)
	private String description;//字段描述

	public String getPropKey() {
		return propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}

	public String getPropVal() {
		return propVal;
	}

	public void setPropVal(String propVal) {
		this.propVal = propVal;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
