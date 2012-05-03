package im.lich.gdms.core.service.student;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;

import java.util.Collection;
import java.util.List;

public interface StudentService extends BaseService {

	/**
	 * 获取学生
	 * @param loginName 登录名、学号
	 * @return Student 学生
	 */
	public Student getStudent(String loginName);

	/**
	 * 根据学生登录名（学号）获取学生信息
	 * @param loginName 登录名、学号
	 * @return Student 学生
	 */
	public Student getStudentInfo(String loginName);

	/**
	 * 获取学生课题
	 * @param student 学生
	 * @return Thesis 课题，或者null
	 */
	public Thesis getStudentThesis(Student student);

	/**
	 * 获取学生课题信息
	 * @param students 学生集合
	 * @return List<Thesis>
	 */
	public List<Thesis> getStudentsThesises(Collection<Student> students);

	/**
	 * 保存学生信息
	 * @param student 学生
	 * @return Student 学生，失败返回null
	 */
	public Student saveStudentInfo(Student student);

	/**
	 * 获取学生中期警告信息
	 * @param loginName 登录名、学号
	 * @return String 中期警告信息
	 */
	public String getMidternCheckWarn(String loginName);

	/**
	 * 获取学生中期检查信息
	 * @param loginName 登录名、学号
	 * @return Student 学生
	 */
	public Student getMidternCheckInfo(String loginName);

	/**
	 * 保存学生中期检查信息
	 * @param student 学生
	 * @return Student 学生，失败返回null
	 */
	public Student saveMidternCheckInfo(Student student);

	/**
	 * 保存学生中期检查信息，老师操作，能够修改警告信息
	 * @param student 学生
	 * @return Student 学生，失败返回null
	 */
	public Student saveMidternCheckInfoByTeacher(Student student);

	/**
	 * 删除学生中期检查信息
	 * @param studentLoginName 学生登录名、学号
	 * @return Student 学生，失败返回null
	 */
	public Student delMidternCheckWarn(String studentLoginName);

	/**
	 * 获取学生指导教师成绩输入状态
	 * @param students 学生集合
	 * @return List<String>
	 */
	public List<String> getStudentsZhidaoScoreStatuses(Collection<Student> students);

	/**
	 * 获取学生评阅教师成绩输入状态
	 * @param students 学生集合
	 * @return List<String>
	 */
	public List<String> getStudentsPingyueScoreStatuses(Collection<Student> students);

	/**
	 * 获取学生答辩小组成绩输入状态
	 * @param students 学生集合
	 * @return List<String>
	 */
	public List<String> getStudentsDabianScoreStatuses(Collection<Student> students);

	/**
	 * 保存学生指导教师输入成绩
	 * @param student 学生指导成绩
	 * @return Student 学生，失败返回null
	 */
	public Student updateScoreInputZhidaoDetail(Student student);

	/**
	 * 保存学生评阅教师输入成绩
	 * @param student 学生评阅成绩
	 * @param teacherLoginName 教师登录名、教工号
	 * @return Student 学生，失败返回null
	 */
	public Student addScoreInputPingyue(Student student, String teacherLoginName);

	/**
	 * 删除学生评阅教师输入成绩
	 * @param studentLoginName 学生登录名、学号
	 * @return Student 学生，失败返回null
	 */
	public Student delScoreInputPingyue(String studentLoginName);

	/**
	 * 保存学生答辩小组输入成绩
	 * @param student 学生答辩成绩
	 * @param teacherLoginName 教师登录名、教工号
	 * @return Student 学生，失败返回null
	 */
	public Student addScoreInputDabian(Student student, String teacherLoginName);

	/**
	* 删除学生答辩小组输入成绩
	* @param studentLoginName 学生登录名、学号
	* @return Student 学生，失败返回null
	*/
	public Student delScoreInputDabian(String studentLoginName);

	/**
	 * 更新学生评语（全部）
	 * @param student 学生
	 * @return Student 学生，失败返回null
	 */
	public Student updatePingyu(Student student);

	/**
	 * 获取学生列表
	 * @return List<Student>
	 */
	public List<Student> getStudents();

	/**
	 * 添加或更新学生信息
	 * @param student
	 * @return
	 */
	public Student addOrUpdateStudent(Student student);

	/**
	 * 删除学生信息
	 * @param StudentId
	 * @return
	 */
	public Student delStudent(Long studentId);
}
