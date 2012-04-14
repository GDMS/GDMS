package im.lich.gdms.core.service.teacher.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.dao.teacher.DabianRecordDao;
import im.lich.gdms.core.dao.teacher.TeacherDao;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.DabianRecord;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.service.teacher.DabianService;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class DabianServiceImpl extends BaseServiceImpl implements DabianService {

	@Resource
	private TeacherDao teacherDao;

	@Resource
	private StudentDao studentDao;

	@Resource
	private DabianRecordDao dabianRecordDao;

	@Override
	public List<Teacher> getStudentsDabianTeachers(Collection<Student> students) {
		// TODO 缺少实现
		return null;
	}

	@Override
	public List<Student> getStudents(String teacherLoginName) {
		List<Student> _students = Lists.newArrayList();
		Teacher _t = teacherDao.findByLoginName(teacherLoginName);
		List<DabianRecord> _dbs = dabianRecordDao.findByTeacherId(_t.getId());
		for (DabianRecord _db : _dbs) {
			Student _s = studentDao.findOne(_db.getStudentId());
			_students.add(_s);
		}

		Assert.isTrue(_dbs.size() == _students.size());
		return _students;
	}
}
