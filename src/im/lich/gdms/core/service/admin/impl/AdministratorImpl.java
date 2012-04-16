package im.lich.gdms.core.service.admin.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.admin.AdministratorDao;
import im.lich.gdms.core.model.admin.Administrator;
import im.lich.gdms.core.service.admin.AdminService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdministratorImpl extends BaseServiceImpl implements AdminService {

	@Resource
	private AdministratorDao administratorDao;

	@Override
	public List<Administrator> getAdministrators() {
		List<Administrator> admins = administratorDao.findAll();

		logger.debug("获取Administrator数量：{}", admins.size());
		return admins;
	}

	@Override
	@Transactional(readOnly = false)
	public Administrator addAdministrator(Administrator administrator) {
		Administrator _a = administratorDao.findByLoginName(administrator.getLoginName());
		if (_a != null) {
			administrator.setId(_a.getId());
		}

		return administratorDao.save(administrator);
	}

	@Override
	@Transactional(readOnly = false)
	public Administrator delAdministrator(Long administratorId) {
		Administrator admin = administratorDao.findOne(administratorId);
		administratorDao.delete(admin);
		return admin;
	}

}
