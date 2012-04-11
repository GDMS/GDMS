package im.lich.gdms.core.service.teacher.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.service.teacher.PingyueService;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PingyueServiceImpl extends BaseServiceImpl implements PingyueService {

	@Override
	public List<Teacher> getStudentsPingyueTeachers(Collection<Student> students) {
		// TODO 缺少实现
		return null;
	}
}