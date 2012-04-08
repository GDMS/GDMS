package im.lich.gdms.core.service.teacher.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.dao.teacher.TeacherDao;
import im.lich.gdms.core.dao.teacher.ThesisDao;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.teacher.TeacherMidternCheckService;
import im.lich.gdms.core.service.teacher.TeacherScoreInputService;

@Service
@Transactional(readOnly = true)
public class TeacherScoreInputServiceImpl extends BaseServiceImpl implements TeacherScoreInputService {

	@Resource
	private TeacherDao teacherDao;

	@Resource
	private StudentDao studentDao;

	@Resource
	private ThesisDao thesisDao;

	@Resource
	private TeacherMidternCheckService teacherMidternCheckService;

	@Override
	public Student getStudent(String studentLoginName) {
		return teacherMidternCheckService.getStudent(studentLoginName);
	}

	@Override
	public List<Student> getStudents(String teacherLoginName) {
		return teacherMidternCheckService.getStudents(teacherLoginName);
	}

	@Override
	public List<Thesis> getStudentsThesises(Collection<Student> students) {
		List<Thesis> thesises = Lists.newArrayList();
		for (Student s : students) {
			Long thesisId = s.getThesisId();
			Thesis t = thesisDao.findOne(thesisId);
			Assert.notNull(t);
			Assert.hasText(t.getAssign());
			thesises.add(t);
		}
		return thesises;
	}

	private static final String ZHIDAO_NOT_INPUT = "未输入";
	private static final String ZHIDAO_INPUT = "已输入";

	//
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
	public List<Teacher> getStudentsPingyueTeachers(Collection<Student> students) {
		// TODO to be continued
		return null;
	}

	@Override
	public List<Teacher> getStudentsDabianTeachers(Collection<Student> students) {
		// TODO to be continued
		return null;
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
