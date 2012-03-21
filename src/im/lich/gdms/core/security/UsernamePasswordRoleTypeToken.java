package im.lich.gdms.core.security;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernamePasswordRoleTypeToken extends UsernamePasswordToken {

	private static final long serialVersionUID = -5588207773442681409L;

	private String roleType;

	public UsernamePasswordRoleTypeToken() {
		super();
	}

	public UsernamePasswordRoleTypeToken(UsernamePasswordToken token, String roleType) {
		super(token.getUsername(), token.getPassword(), token.isRememberMe(), token.getHost());
		this.roleType = roleType;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

}
