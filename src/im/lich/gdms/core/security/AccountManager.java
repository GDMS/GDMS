package im.lich.gdms.core.security;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.admin.AdministratorDao;
import im.lich.gdms.core.dao.admin.SysPermissionDao;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.dao.teacher.TeacherDao;
import im.lich.gdms.core.model.admin.SysPermission;
import im.lich.gdms.core.model.generic.User;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 安全相关实体的管理类,包括用户和权限组.
 * 
 * @author calvin
 */
//Spring Bean的标识.
@Service
//默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class AccountManager extends BaseServiceImpl {

	@Resource
	private AdministratorDao administratorDao;

	@Resource
	private TeacherDao teacherDao;

	@Resource
	private StudentDao studentDao;

	@Resource
	private SysPermissionDao sysPermissionDao;

	public User findUserByLoginName(String loginName, RoleType roleType) {
		Assert.notNull(roleType);

		User user = null;

		switch (roleType) {
		case ADMIN:
			user = administratorDao.findByLoginName(loginName);
			break;
		case TEACHER:
			user = teacherDao.findByLoginName(loginName);
			break;
		case STUDENT:
			user = studentDao.findByLoginName(loginName);
			break;
		}

		return user;
	}

	public String findRoleByUser(String loginName, RoleType roleType) {
		Assert.notNull(roleType);

		switch (roleType) {
		case ADMIN:
			if (administratorDao.findByLoginName(loginName) != null)
				return roleType.getRoleName();
		case TEACHER:
			if (teacherDao.findByLoginName(loginName) != null)
				return roleType.getRoleName();
		case STUDENT:
			if (studentDao.findByLoginName(loginName) != null)
				return roleType.getRoleName();
		}

		return null;
	}

	public List<String> findPermissionsByUser(RoleType roleType) {
		Assert.notNull(roleType);

		List<SysPermission> _perms = null;
		List<String> perms = new ArrayList<String>();

		switch (roleType) {
		case ADMIN:
		case TEACHER:
		case STUDENT:
			_perms = sysPermissionDao.findByPropKeyLike(roleType.getValue() + ":%");
		}

		for (SysPermission p : _perms) {
			if (p.getPropVal() == true)
				perms.add(p.getPropKey());
		}

		logger.debug("{}的允许权限数量：{}", roleType, perms.size());

		return perms;
	}
}
