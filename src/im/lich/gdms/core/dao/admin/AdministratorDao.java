package im.lich.gdms.core.dao.admin;

import im.lich.gdms.base.dao.BaseDao;
import im.lich.gdms.core.model.admin.Admin;

public interface AdministratorDao extends BaseDao<Admin, Long> {
	Admin findByLoginName(String loginName);
}
