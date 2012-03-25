package im.lich.gdms.core.security;

import java.util.ArrayList;
import java.util.List;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.admin.AdministratorDao;
import im.lich.gdms.core.dao.admin.SysPropertyDao;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.dao.teacher.TeacherDao;
import im.lich.gdms.core.model.admin.SysProperty;
import im.lich.gdms.core.model.generic.User;

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
	private SysPropertyDao sysPropertyDao;

	public User findUserByLoginName(String loginName, String roleType) {
		Assert.notNull(roleType);

		User user = null;

		//Admin中查询
		if (roleType.equals("admin"))
			user = administratorDao.findByLoginName(loginName);

		//Teacher中查询
		if (roleType.equals("teacher"))
			user = teacherDao.findByLoginName(loginName);

		//Student中查询
		if (roleType.equals("student"))
			user = studentDao.findByLoginName(loginName);

		return user;
	}

	public String findRoleByUser(String loginName, String roleType) {
		Assert.notNull(roleType);

		User user = null;
		String role = null;

		//Admin中查询
		if (roleType.equals("admin")) {
			user = administratorDao.findByLoginName(loginName);
			if (user != null)
				role = "ROLE_ADMIN";
		}

		//Teacher中查询
		if (roleType.equals("teacher")) {
			user = teacherDao.findByLoginName(loginName);
			if (user != null)
				role = "ROLE_TEACHER";
		}

		//Student中查询
		if (roleType.equals("student")) {
			user = studentDao.findByLoginName(loginName);
			if (user != null)
				role = "ROLE_STUDENT";
		}

		Assert.notNull(role);
		return role;
	}

	public List<String> findPermissionsByUser(String roleType) {
		Assert.notNull(roleType);

		List<SysProperty> props = null;
		List<String> perms = new ArrayList<String>();

		//Admin中查询
		if (roleType.equals("admin"))
			props = sysPropertyDao.findByPropKeyLike("admin:%");

		//Teacher中查询
		if (roleType.equals("teacher"))
			props = sysPropertyDao.findByPropKeyLike("teacher:%");

		//Student中查询
		if (roleType.equals("student"))
			props = sysPropertyDao.findByPropKeyLike("student:%");

		logger.debug("查询{}类型的权限总数量：{}", roleType, props.size());

		for (SysProperty p : props) {
			Assert.isTrue(p.getType().equals("boolean"), "Shiro权限类型不正确");
			if (p.getPropVal().equals("true"))
				perms.add(p.getPropKey());
		}

		logger.debug("查询{}类型的权限总数量：{}", roleType, perms.size());

		return perms;
	}
}
