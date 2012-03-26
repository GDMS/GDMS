package im.lich.gdms.core.service.teacher.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.dao.teacher.RemarkRecordDao;
import im.lich.gdms.core.dao.teacher.ReplyRecordDao;
import im.lich.gdms.core.dao.teacher.TeacherDao;
import im.lich.gdms.core.dao.teacher.ThesisDao;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.RemarkRecord;
import im.lich.gdms.core.model.teacher.ReplyRecord;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.teacher.TeacherScoreInputService;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

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
	private RemarkRecordDao remarkRecordDao;

	@Resource
	private ReplyRecordDao replyRecordDao;

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

		List<Student> students = Lists.newArrayList();
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
	public List<Thesis> getStudentsThesises(Collection<Student> students) {
		List<Student> _students = Lists.newArrayList();
		for (Student s : students) {
			if (s.getThesisId() != null)
				_students.add(s);
			else
				logger.error("出现没有课题的学生:{}", s);
		}

		List<Thesis> thesises = Lists.newArrayList();
		for (Student s : _students) {
			Long id = s.getThesisId();
			Thesis thesis = thesisDao.findOne(id);
			if (thesis != null) {
				Assert.isTrue(StringUtils.isNotBlank(thesis.getAssign()), "thesis assign should not be blank");
				thesises.add(thesis);
			}
		}
		//判断课题数、学生数是否符合
		Assert.isTrue(_students.size() == thesises.size(), "学生数、课题数不一致");

		return thesises;
	}

	@Override
	public List<Teacher> getStudentsRemarkTeachers(Collection<Student> students) {
		List<Student> _students = Lists.newArrayList();
		for (Student s : students) {
			if (s.getThesisId() != null)
				_students.add(s);
			else
				logger.error("出现没有课题的学生:{}", s);
		}

		List<Teacher> teachers = Lists.newArrayList();
		for (Student s : _students) {
			RemarkRecord rr = remarkRecordDao.findByStudentId(s.getId());
			if (rr == null) {//有可能评阅老师没有输入成绩
				teachers.add(null);
				continue;
			}
			Long tId = rr.getTeacherId();
			Teacher t = teacherDao.findOne(tId);
			Assert.notNull(t);
			teachers.add(t);
		}

		return teachers;
	}

	@Override
	public List<Teacher> getStudentsReplyTeachers(Collection<Student> students) {
		List<Student> _students = Lists.newArrayList();
		for (Student s : students) {
			if (s.getThesisId() != null)
				_students.add(s);
			else
				logger.error("出现没有课题的学生:{}", s);
		}

		List<Teacher> teachers = Lists.newArrayList();
		for (Student s : _students) {
			ReplyRecord rr = replyRecordDao.findByStudentId(s.getId());
			if (rr == null) {//有可能答辩老师没有输入成绩
				teachers.add(null);
				continue;
			}
			Long tId = rr.getTeacherId();
			Teacher t = teacherDao.findOne(tId);
			Assert.notNull(t);
			teachers.add(t);
		}

		return teachers;
	}
}
