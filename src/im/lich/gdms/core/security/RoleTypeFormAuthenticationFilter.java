package im.lich.gdms.core.security;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleTypeFormAuthenticationFilter extends FormAuthenticationFilter implements Filter {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public static final String DEFAULT_ROLE_TYPE_PARAM = "roleType";
	private String roleTypeParam = DEFAULT_ROLE_TYPE_PARAM;

	public String getRoleTypeParam() {
		return roleTypeParam;
	}

	protected String getRoleType(ServletRequest request) {
		return WebUtils.getCleanParam(request, getRoleTypeParam());
	}

	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse responce) {
		String username = getUsername(request);
		String password = getPassword(request);
		String roleType = getRoleType(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);

		logger.trace("自定义RoleTypeFormAuthenticationFilter运行，获取的RoleType: {}", roleType);

		UsernamePasswordToken usernamepasswordtoken = new UsernamePasswordToken(username, password, rememberMe, host);
		return new UsernamePasswordRoleTypeToken(usernamepasswordtoken, roleType);
	}
}
