package im.lich.gdms.core.service.admin;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.admin.SysPermission;

import java.util.List;

public interface SysPermissionService extends BaseService {

	/**
	 * 获取所有系统属性
	 * @return List<SysPermission>
	 */
	public List<SysPermission> getAllSysPermissions();

	/**
	 * 保存系统属性
	 * @param sysPermission
	 * @return SysPermission or null
	 */
	public SysPermission saveSysPermission(SysPermission sysPermission);
}
