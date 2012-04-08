package im.lich.gdms.core.dao.admin;

import im.lich.gdms.base.dao.BaseDao;
import im.lich.gdms.core.model.admin.SysPermission;

import java.util.List;

public interface SysPermissionDao extends BaseDao<SysPermission, Long> {
	public SysPermission findByPropKey(String propKey);

	public List<SysPermission> findByPropKeyLike(String propKey);
}
