package im.lich.gdms.core.dao.admin;

import im.lich.gdms.base.dao.BaseDao;
import im.lich.gdms.core.model.admin.Administrator;

public interface AdministratorDao extends BaseDao<Administrator, Long> {
	Administrator findByLoginName(String loginName);
}
