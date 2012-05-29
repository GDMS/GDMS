package im.lich.gdms.core.service.admin;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.admin.SysProperty;

import java.util.List;

public interface SysPropertyService extends BaseService {

	/**
	 * 获取所有系统属性
	 * @return List<SysProperty>
	 */
	public List<SysProperty> getAllSysProperties();

	/**
	 * 保存系统属性
	 * @param sysProperty
	 * @return SysProperty or null
	 */
	public SysProperty saveSysProperty(SysProperty sysProperty);
}
