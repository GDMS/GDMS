package im.lich.gdms.core.service.teacher;

import java.util.List;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Teacher;

public interface TeacherService extends BaseService {
	/**
	 * 获取教师个人信息
	 * @param loginName 登录名、教工号
	 * @return Teacher
	 */
	public Teacher getTeacherInfo(String loginName);

	/**
	 * 保存教师个人信息
	 * @param teacher 教师
	 * @return Teacher 教师，失败返回null
	 */
	public Teacher saveTeacherInfo(Teacher teacher);

	/**
	 * 获取教师指导学生列表
	 * @param teacherLoginName 教师登录名、教工号
	 * @return List<Student>
	 */
	public List<Student> getTeachingStudents(String teacherLoginName);
}
