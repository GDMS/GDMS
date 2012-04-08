package im.lich.gdms.core.security;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsernamePasswordRoleTypeToken extends UsernamePasswordToken {

	private static final long serialVersionUID = -5588207773442681409L;

	private RoleType roleType;

	public UsernamePasswordRoleTypeToken() {
		super();
	}

	public UsernamePasswordRoleTypeToken(UsernamePasswordToken token, RoleType roleType) {
		super(token.getUsername(), token.getPassword(), token.isRememberMe(), token.getHost());
		this.roleType = roleType;
	}

	public UsernamePasswordRoleTypeToken(UsernamePasswordToken token, String roleType) {
		super(token.getUsername(), token.getPassword(), token.isRememberMe(), token.getHost());
		Logger logger = LoggerFactory.getLogger(getClass());
		logger.debug("roType: {}", roleType);
		this.roleType = RoleType.getRoleType(roleType);
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
}
