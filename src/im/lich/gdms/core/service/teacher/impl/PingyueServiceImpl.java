package im.lich.gdms.core.service.teacher.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.dao.teacher.PingyueRecordDao;
import im.lich.gdms.core.dao.teacher.TeacherDao;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.PingyueRecord;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.service.teacher.PingyueService;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class PingyueServiceImpl extends BaseServiceImpl implements PingyueService {

	@Resource
	private TeacherDao teacherDao;

	@Resource
	private StudentDao studentDao;

	@Resource
	private PingyueRecordDao pingyueRecordDao;

	@Override
	public List<Teacher> getStudentsPingyueTeachers(Collection<Student> students) {
		// TODO 缺少实现
		return null;
	}

	@Override
	public List<Student> getStudents(String teacherLoginName) {
		List<Student> _students = Lists.newArrayList();
		Teacher _t = teacherDao.findByLoginName(teacherLoginName);
		List<PingyueRecord> _pys = pingyueRecordDao.findByTeacherId(_t.getId());
		for (PingyueRecord _py : _pys) {
			Student _s = studentDao.findOne(_py.getStudentId());
			_students.add(_s);
		}

		Assert.isTrue(_pys.size() == _students.size());
		return _students;
	}
}
