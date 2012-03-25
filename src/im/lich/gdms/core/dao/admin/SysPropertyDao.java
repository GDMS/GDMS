package im.lich.gdms.core.dao.admin;

import im.lich.gdms.base.dao.BaseDao;
import im.lich.gdms.core.model.admin.SysProperty;

import java.util.List;

public interface SysPropertyDao extends BaseDao<SysProperty, Long> {
	public SysProperty findByPropKey(String propKey);

	public List<SysProperty> findByPropKeyLike(String propKey);
}
