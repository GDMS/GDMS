package im.lich.gdms.core.service.teacher.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.dao.teacher.TeacherDao;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.teacher.TeacherMidternCheckService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class TeacherMidternCheckServiceImpl extends BaseServiceImpl implements TeacherMidternCheckService {

	@Resource
	private TeacherDao teacherDao;

	@Resource
	private StudentDao studentDao;

	@Override
	public List<Student> getStudents(String teacherLoginName) {
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

		List<Student> students = new ArrayList<Student>();
		for (Thesis t : thesises) {
			Student s = studentDao.findByThesisId(t.getId());
			if (s != null)
				students.add(s);
		}

		//判断课题数、学生数是否符合
		Assert.isTrue(thesises.size() == students.size(), "课题数、学生数不一致");

		return students;
	}

	@Override
	public Student getStudent(String studentLoginName) {
		Student student = studentDao.findByLoginName(studentLoginName);
		logger.debug("获取学生信息:{}", student);
		return student;
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveMidternCheckWarn(Student student) {
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

		_s.setWarn("");

		logger.debug("保存学生中期检查信息：{}", _s);
		return studentDao.save(_s);
	}
}
