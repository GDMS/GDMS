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
public class SysProperty extends IdEntity {
	private static final long serialVersionUID = -8244930104699243848L;

	private String propKey;//关键字段
	private String propVal = "false";//数值
	private String type = "boolean";//字段类型
	private String description = "";//字段描述

	public SysProperty() {
		super();
	}

	public SysProperty(String propKey) {
		super();
		this.propKey = propKey;
	}

	public SysProperty(String propKey, String propVal, String type, String description) {
		super();
		this.propKey = propKey;
		this.propVal = propVal;
		this.type = type;
		this.description = description;
	}

	@Column(nullable = false, unique = true)
	public String getPropKey() {
		return propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}

	@Column(nullable = false)
	public String getPropVal() {
		return propVal;
	}

	public void setPropVal(String propVal) {
		this.propVal = propVal;
	}

	@Column(nullable = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
