package im.lich.gdms.core.service.student.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.service.student.StudentMidternCheckService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true)
public class StudentMidternCheckServiceImpl extends BaseServiceImpl implements StudentMidternCheckService {

	@Resource
	private StudentDao studentDao;

	@Override
	public String getMidternCheckWarn(String loginName) {
		Assert.notNull(loginName);

		String warn = studentDao.findByLoginName(loginName).getWarn();
		logger.debug("学生：{}，中期检查警告信息：{}", loginName, warn);

		return warn;
	}

	@Override
	public Student getMidternCheckInfo(String loginName) {
		Assert.notNull(loginName);

		Student student = studentDao.findByLoginName(loginName);
		logger.debug("查询学生中期检查信息：{}", student);
		return student;
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveMidternCheckInfo(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());

		_s.setProgress(s.getProgress());
		_s.setQuality(s.getQuality());
		_s.setAttitude(s.getAttitude());
		_s.setDuty(s.getDuty());

		logger.debug("保存学生中期检查信息：{}", _s);
		return studentDao.save(_s);
	}
}
