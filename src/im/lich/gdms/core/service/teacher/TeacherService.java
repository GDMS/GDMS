package im.lich.gdms.core.service.teacher;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.model.teacher.Thesis;

import java.util.List;

public interface TeacherService extends BaseService {
	/**
	 * 获取教师列表
	 * @return List<Teacher>
	 */
	public List<Teacher> getTeachers();

	/**
	 * 添加教师
	 * @param teacher 教师
	 * @return Teacher or null
	 */
	public Teacher addTeacher(Teacher teacher);

	/**
	 * 添加或更新教师
	 * @param teacher 教师
	 * @return Teacher or null
	 */
	public Teacher addOrUpdateTeacher(Teacher teacher);

	/**
	 * 删除教师
	 * @param teacherId 教师ID
	 * @return Teacher
	 */
	public Teacher delTeacher(Long teacherId);

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

	/**
	 * 获取教师课题列表
	 * @param teacherLoginName 教师登录名、教工号
	 * @return List<Thesis>
	 */
	public List<Thesis> getTeacherThesises(String teacherLoginName);

	/**
	 * 教师添加新课题
	 * @param thesis
	 * @param teacherLoginName
	 * @return
	 */
	public Thesis saveTeacherThesis(Thesis thesis, String teacherLoginName);
}
