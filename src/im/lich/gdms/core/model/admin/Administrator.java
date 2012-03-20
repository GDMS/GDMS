package im.lich.gdms.core.model.admin;

import im.lich.gdms.base.model.IdEntity;
import im.lich.gdms.core.model.generic.User;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Administrator extends IdEntity implements User {
	private static final long serialVersionUID = 4217558191841990227L;

	@Column(length = 20, nullable = false)
	private String loginName;//管理员登录名

	@Column(length = 20, nullable = false)
	private String name;//管理员姓名

	@Column(length = 50, nullable = false)
	private String password;//密码

	@Column(nullable = false)
	private String enable;//是否启用

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
}
