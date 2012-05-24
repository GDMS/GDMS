package im.lich.gdms.core.service.admin.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.admin.AdministratorDao;
import im.lich.gdms.core.model.admin.Admin;
import im.lich.gdms.core.service.admin.AdminService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {

	@Resource
	private AdministratorDao administratorDao;

	@Override
	public List<Admin> getAdministrators() {
		List<Admin> admins = administratorDao.findAll();

		logger.debug("获取Administrator数量：{}", admins.size());
		return admins;
	}

	@Override
	@Transactional(readOnly = false)
	public Admin addAdministrator(Admin administrator) {
		return administratorDao.save(administrator);
	}

	@Override
	@Transactional(readOnly = false)
	public Admin addOrUpdateAdministrator(Admin administrator) {
		Admin _a = administratorDao.findByLoginName(administrator.getLoginName());
		if (_a != null) {
			administrator.setId(_a.getId());
		}

		return administratorDao.save(administrator);
	}

	@Override
	@Transactional(readOnly = false)
	public Admin delAdministrator(Long administratorId) {
		Admin admin = administratorDao.findOne(administratorId);
		administratorDao.delete(admin);
		return admin;
	}

}
