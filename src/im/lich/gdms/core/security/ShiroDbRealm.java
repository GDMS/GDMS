package im.lich.gdms.core.security;

import im.lich.gdms.core.model.generic.User;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自实现用户与权限查询.
 * 演示关系，密码用明文存储，因此使用默认 的SimpleCredentialsMatcher.
 */
public class ShiroDbRealm extends AuthorizingRealm {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private AccountManager accountManager;

	/**
	 * 认证回调函数, 登录时调用.
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordRoleTypeToken token = (UsernamePasswordRoleTypeToken) authcToken;
		String roleType = token.getRoleType();
		User user = accountManager.findUserByLoginName(token.getUsername(), roleType);
		if (user != null) {
			ShiroUser u = new ShiroUser(user.getLoginName(), user.getName(), roleType);
			logger.debug("登录用户：{}，RoleType：{}", user.getLoginName(), roleType);
			return new SimpleAuthenticationInfo(u, user.getPassword(), getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals.fromRealm(getName()).isEmpty()) {
			logger.warn("用户认证后无法获取该用户");
			return null;
		}
		ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName()).iterator().next();
		String roleType = shiroUser.getRoleType();
		User user = accountManager.findUserByLoginName(shiroUser.getLoginName(), roleType);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			//添加角色
			String role = accountManager.findRoleByUser(user.getLoginName(), roleType);
			info.addRole(role);
			logger.debug("登录用户：{}，拥有角色：{}", user.getLoginName(), role);
			//添加权限
			List<String> permissions = accountManager.findPermissionsByUser(roleType);
			logger.debug("登录用户：{}，拥有权限：{}", user.getLoginName(), StringUtils.join(permissions, ','));
			return info;
		}
		return null;
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {

		private static final long serialVersionUID = -1748602382963711884L;
		private final String loginName;
		private final String name;
		private final String roleType;

		public ShiroUser(String loginName, String name, String roleType) {
			this.loginName = loginName;
			this.name = name;
			this.roleType = roleType;
		}

		public String getLoginName() {
			return loginName;
		}

		public String getName() {
			return name;
		}

		public String getRoleType() {
			return roleType;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}

	}

}
