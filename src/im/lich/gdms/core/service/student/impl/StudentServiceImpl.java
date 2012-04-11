package im.lich.gdms.core.service.student.impl;

import java.util.Collection;
import java.util.List;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.service.student.StudentService;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl extends BaseServiceImpl implements StudentService {

	@Resource
	private StudentDao studentDao;

	@Override
	public Student getStudent(String loginName) {
		Assert.notNull(loginName);

		Student student = studentDao.findByLoginName(loginName);
		Assert.notNull(student);

		logger.debug("查询学生信息：{}", student);
		return student;
	}

	@Override
	public Student getStudentInfo(String loginName) {
		return getStudent(loginName);
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
		Assert.notNull(_s);

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
	public String getMidternCheckWarn(String loginName) {
		Assert.notNull(loginName);

		Student student = studentDao.findByLoginName(loginName);
		Assert.notNull(student);

		String warn = student.getWarn();
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
		Assert.notNull(_s);

		_s.setProgress(s.getProgress());
		_s.setQuality(s.getQuality());
		_s.setAttitude(s.getAttitude());
		_s.setDuty(s.getDuty());

		logger.debug("保存学生中期检查信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveMidternCheckInfoByTeacher(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setProgress(s.getProgress());
		_s.setQuality(s.getQuality());
		_s.setAttitude(s.getAttitude());
		_s.setDuty(s.getDuty());
		_s.setWarn(s.getWarn());

		logger.debug("保存学生中期检查信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student delMidternCheckWarn(String studentLoginName) {
		Assert.notNull(studentLoginName);

		//获取内部学生
		Student _s = studentDao.findByLoginName(studentLoginName);
		Assert.notNull(_s);

		_s.setWarn("");

		logger.debug("保存学生中期检查信息：{}", _s);
		return studentDao.save(_s);
	}

	private static final String ZHIDAO_NOT_INPUT = "未输入";
	private static final String ZHIDAO_INPUT = "已输入";

	@Override
	public List<String> getStudentsZhidaoScoreStatuses(Collection<Student> students) {
		List<String> statuses = Lists.newArrayList();
		for (Student s : students) {
			int zd1 = s.getZd1grade();
			int zd2 = s.getZd2grade();
			int zd3 = s.getZd3grade();
			int zd4 = s.getZd4grade();
			String status = ZHIDAO_NOT_INPUT;
			if (zd1 > 0 && zd2 > 0 && zd3 > 0 && zd4 > 0)
				status = ZHIDAO_INPUT;
			statuses.add(status);
		}
		return statuses;
	}

	private static final String PINGYUE_NOT_INPUT = "未输入";
	private static final String PINGYUE_INPUT = "已输入";

	@Override
	public List<String> getStudentsPingyueScoreStatuses(Collection<Student> students) {
		List<String> statuses = Lists.newArrayList();
		for (Student s : students) {
			int py1 = s.getPy1grade();
			int py2 = s.getPy2grade();
			String status = PINGYUE_NOT_INPUT;
			if (py1 > 0 && py2 > 0)
				status = PINGYUE_INPUT;
			statuses.add(status);
		}
		return statuses;
	}

	private static final String DABIAN_NOT_INPUT = "未输入";
	private static final String DABIAN_INPUT = "已输入";

	@Override
	public List<String> getStudentsDabianScoreStatuses(Collection<Student> students) {
		List<String> statuses = Lists.newArrayList();
		for (Student s : students) {
			int db1 = s.getDb1grade();
			int db2 = s.getDb2grade();
			String status = DABIAN_NOT_INPUT;
			if (db1 > 0 && db2 > 0)
				status = DABIAN_INPUT;
			statuses.add(status);
		}
		return statuses;
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveScoreInputZhidaoDetail(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setZd1grade(s.getZd1grade());
		_s.setZd2grade(s.getZd2grade());
		_s.setZd3grade(s.getZd3grade());
		_s.setZd4grade(s.getZd4grade());

		logger.debug("保存指导教师输入成绩信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveScoreInputPingyueDetail(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setPy1grade(s.getPy1grade());
		_s.setPy2grade(s.getPy2grade());

		logger.debug("保存评阅教师输入成绩信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveScoreInputDabianDetail(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setDb1grade(s.getDb1grade());
		_s.setDb2grade(s.getDb2grade());

		logger.debug("保存答辩小组输入成绩信息：{}", _s);
		return studentDao.save(_s);
	}
}