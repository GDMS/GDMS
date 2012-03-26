package im.lich.gdms.core.service.teacher.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.teacher.TeacherDao;
import im.lich.gdms.core.dao.teacher.TeacherDeptDao;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.model.teacher.TeacherDept;
import im.lich.gdms.core.service.teacher.TeacherInfoService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true)
public class TeacherInfoServiceImpl extends BaseServiceImpl implements TeacherInfoService {

	@Resource
	private TeacherDao teacherDao;

	@Resource
	private TeacherDeptDao teacherDeptDao;

	@Override
	public Teacher getTeacherInfo(String loginName) {
		Assert.notNull(loginName);

		Teacher teacher = teacherDao.findByLoginName(loginName);
		Assert.notNull(teacher);

		logger.debug("查询教师信息：{}", teacher);

		return teacher;
	}

	@Override
	@Transactional(readOnly = false)
	public Teacher saveTeacherInfo(Teacher teacher) {
		Assert.notNull(teacher);
		Assert.notNull(teacher.getLoginName());
		//简化名称
		Teacher t = teacher;

		//获取内部学生
		Teacher _t = teacherDao.findByLoginName(t.getLoginName());
		Assert.notNull(_t);

		//判断是否需要修改密码
		String pass = t.getPassword();
		if (StringUtils.isNotEmpty(pass)) {
			_t.setPassword(pass);
			logger.debug("用户：{} 修改密码", _t.getLoginName());
		}

		_t.setName(t.getName());
		_t.setSubnum(t.getSubnum());
		_t.setMail(t.getMail());
		_t.setTel(t.getTel());
		_t.setDept(t.getDept());

		logger.debug("保存教师信息：{}", _t);
		return teacherDao.save(_t);
	}

	@Override
	public List<TeacherDept> getTeacherDepts() {
		return teacherDeptDao.findAll();
	}

}
