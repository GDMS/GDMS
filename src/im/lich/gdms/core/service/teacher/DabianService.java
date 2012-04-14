package im.lich.gdms.core.service.teacher;

import java.util.Collection;
import java.util.List;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Teacher;

public interface DabianService extends BaseService {
	/**
	 * 获取答辩小组名称
	 * @param students 学生集合
	 * @return List<Teacher>
	 */
	public List<Teacher> getStudentsDabianTeachers(Collection<Student> students);

	/**
	 * 获取答辩小组填写成绩的学生
	 * @param teacherLoginName 教师登录名、教工号
	 * @return List<Student>
	 */
	public List<Student> getStudents(String teacherLoginName);
}
