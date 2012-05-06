package im.lich.gdms.core.service.teacher.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.dao.teacher.TeacherDao;
import im.lich.gdms.core.dao.teacher.ThesisDao;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.teacher.TeacherService;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class TeacherServiceImpl extends BaseServiceImpl implements TeacherService {

	@Resource
	private TeacherDao teacherDao;

	@Resource
	private StudentDao studentDao;

	@Resource
	private ThesisDao thesisDao;

	@Override
	public List<Teacher> getTeachers() {
		List<Teacher> teachers = Lists.newArrayList(teacherDao.findAll());

		logger.debug("获取Teacher数量：{}", teachers.size());
		return teachers;
	}

	@Override
	@Transactional(readOnly = false)
	public Teacher addTeacher(Teacher teacher) {
		return teacherDao.save(teacher);
	}

	@Override
	@Transactional(readOnly = false)
	public Teacher addOrUpdateTeacher(Teacher teacher) {
		Teacher _t = teacherDao.findByLoginName(teacher.getLoginName());
		if (_t != null) {
			teacher.setId(_t.getId());
		}

		return teacherDao.save(teacher);
	}

	@Override
	@Transactional(readOnly = false)
	public Teacher delTeacher(Long teacherId) {
		Teacher teacher = teacherDao.findOne(teacherId);
		teacherDao.delete(teacher);
		return teacher;
	}

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
	public List<Student> getTeachingStudents(String teacherLoginName) {
		Assert.notNull(teacherLoginName);

		Teacher teacher = teacherDao.findByLoginName(teacherLoginName);
		List<Thesis> _thesises = Lists.newArrayList(teacher.getThesises());

		List<Thesis> thesises = Lists.newArrayList();
		for (Thesis t : _thesises)
			if (StringUtils.isNotBlank(t.getAssign()))
				thesises.add(t);

		if (thesises.isEmpty()) {
			logger.debug("课题数为零，返回空学生");
			return null;
		}

		List<Student> students = Lists.newArrayList();
		for (Thesis t : thesises) {
			Student s = studentDao.findByThesisId(t.getId());
			if (s != null)
				students.add(s);
		}

		//判断课题数、学生数是否符合
		logger.debug("课题数: {}", thesises.size());
		logger.debug("学生数: {}", students.size());
		Assert.isTrue(thesises.size() == students.size(), "课题数、学生数不一致");

		return students;
	}

	@Override
	public List<Thesis> getTeacherThesises(String teacherLoginName) {
		Assert.notNull(teacherLoginName);

		Teacher teacher = teacherDao.findByLoginName(teacherLoginName);
		List<Thesis> _thesises = Lists.newArrayList(teacher.getThesises());

		for (Thesis t : _thesises) {
			String majorRestrict = t.getMajorRestrict();
			String[] ms = majorRestrict.split(",");
			List<String> majors = Arrays.asList(ms);
			t.setMajorRes(majors);
		}

		return _thesises;
	}

	@Override
	@Transactional(readOnly = false)
	public Thesis saveTeacherThesis(Thesis thesis, String teacherLoginName) {
		//处理专业限制字符串
		List<String> majors = thesis.getMajorRes();
		String majorRestrict = StringUtils.join(majors, ',');
		thesis.setMajorRestrict(majorRestrict);

		//设置课题所属教师
		Teacher teacher = teacherDao.findByLoginName(teacherLoginName);
		thesis.setTeacher(teacher);

		return thesisDao.save(thesis);
	}
}
