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
	 * 获取教师课题
	 * @param thesisId 课题ID
	 * @return Thesis
	 */
	public Thesis getTeacherThesis(Long thesisId);

	/**
	 * 获取教师课题列表
	 * @param teacherLoginName 教师登录名、教工号
	 * @return List<Thesis>
	 */
	public List<Thesis> getTeacherThesises(String teacherLoginName);

	/**
	 * 获取教师未分配课题列表
	 * @param teacherLoginName 教师登录名、教工号
	 * @return List<Thesis>
	 */
	public List<Thesis> getTeacherUnassignedThesises(String teacherLoginName);

	/**
	 * 教师添加新课题
	 * @param thesis
	 * @param teacherLoginName
	 * @return
	 */
	public Thesis saveTeacherThesis(Thesis thesis, String teacherLoginName);

	/**
	 * 删除教师课题
	 * @param ThesisId
	 * @return
	 */
	public Thesis delTeacherThesis(Long thesisId);

	/**
	 * 更新教师课题
	 * @param thesisId
	 * @param newThesis
	 * @return
	 */
	public Thesis modTeacherThesis(Long thesisId, Thesis newThesis);

	/**
	 * 获取学生预选的课题
	 * @param theses
	 * @return
	 */
	public List<Thesis> getStudentsPreviewTheses(List<Thesis> theses);

	/**
	 * 获取学生预选的学生姓名
	 * @param theses
	 * @return
	 */
	public List<Student> getStudentsPreviewName(List<Thesis> theses);

	/**
	 * 获取学生预选的志愿顺序
	 * @param theses
	 * @return
	 */
	public List<Integer> getStudentsPreviewOrder(List<Thesis> theses);

	/**
	 * 获取学生预选时当前分配状态
	 * @param theses
	 * @return
	 */
	public List<String> getStudentsPreviewAssign(List<Thesis> theses);

	/**
	 * 获取已分配学生课题
	 * @param loginName
	 * @return
	 */
	public List<Thesis> getAssignedStudentThesis(String loginName);

	/**
	 * 获取已分配学生信息
	 * @param loginName
	 * @return
	 */
	public List<Student> getAssignedStudentInfo(String loginName);

	/**
	 * 获取未分配课题
	 * @param loginName
	 * @return
	 */
	public List<Thesis> getUnassignedThesises(String loginName);
}
