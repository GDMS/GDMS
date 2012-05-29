package im.lich.gdms.core.service.admin.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.admin.SysPermissionDao;
import im.lich.gdms.core.model.admin.SysPermission;
import im.lich.gdms.core.service.admin.SysPermissionService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class SysPermissionServiceImpl extends BaseServiceImpl implements SysPermissionService {

	@Resource
	private SysPermissionDao sysPermissionDao;

	@Override
	public List<SysPermission> getAllSysPermissions() {
		List<SysPermission> sysPermissions = Lists.newArrayList(sysPermissionDao.findAll());
		logger.debug("系统权限数量：{}", sysPermissions.size());

		return sysPermissions;
	}

	@Override
	@Transactional(readOnly = false)
	public SysPermission saveSysPermission(SysPermission sysPermission) {
		Assert.notNull(sysPermission);
		SysPermission s = sysPermission;

		Long id = s.getId();
		SysPermission _s = sysPermissionDao.findOne(id);
		_s.setPropVal(s.getPropVal());
		logger.debug("修改系统权限：{}", _s);

		return sysPermissionDao.save(_s);
	}
}
