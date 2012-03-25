package im.lich.gdms.core.service.student.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.dao.student.StudentMajorDao;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.student.StudentMajor;
import im.lich.gdms.core.service.student.StudentInfoService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true)
public class StudentInfoServiceImpl extends BaseServiceImpl implements StudentInfoService {

	@Resource
	private StudentDao studentDao;

	@Resource
	private StudentMajorDao studentMajorDao;

	@Override
	public Student getStudentInfo(String loginName) {
		Assert.notNull(loginName);

		Student student = studentDao.findByLoginName(loginName);
		logger.debug("查询学生信息：{}", student);
		return student;
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveStudentInfo(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());

		//判断是否需要修改密码
		String pass = s.getPassword();
		if (StringUtils.isNotEmpty(pass)) {
			_s.setPassword(pass);
			logger.debug("用户：{} 修改密码", _s.getLoginName());
		}

		_s.setName(s.getName());
		_s.setGender(s.getGender());
		_s.setPhone(s.getPhone());
		_s.setEmail(s.getEmail());
		_s.setMajor(s.getMajor());

		logger.debug("保存学生信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	public List<StudentMajor> getStudentMajors() {
		return studentMajorDao.findAll();
	}

}
