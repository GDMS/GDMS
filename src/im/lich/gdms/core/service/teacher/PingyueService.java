package im.lich.gdms.core.service.teacher;

import java.util.Collection;
import java.util.List;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Teacher;

public interface PingyueService extends BaseService {
	/**
	 * 获取评阅小组名称
	 * @param students 学生集合
	 * @return List<Teacher>
	 */
	public List<Teacher> getStudentsPingyueTeachers(Collection<Student> students);
}
