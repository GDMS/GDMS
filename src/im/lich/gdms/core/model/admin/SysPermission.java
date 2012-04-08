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
public class SysPermission extends IdEntity {
	private static final long serialVersionUID = -8244930104699243848L;

	private String propKey;//字段
	private Boolean propVal = false;//值
	private String description = "";//字段描述

	public SysPermission() {
		super();
	}

	public SysPermission(String propKey) {
		super();
		this.propKey = propKey;
	}

	public SysPermission(String propKey, Boolean propVal, String description) {
		super();
		this.propKey = propKey;
		this.propVal = propVal;
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
	public Boolean getPropVal() {
		return propVal;
	}

	public void setPropVal(Boolean propVal) {
		this.propVal = propVal;
	}

	@Column(nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
