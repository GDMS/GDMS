package im.lich.gdms.core.service.teacher;

import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.model.teacher.Thesis;

import java.util.Collection;
import java.util.List;

public interface TeacherScoreInputService {
	/**
	 * 获取知道学生
	 * @param studentLoginName
	 * @return
	 */
	public Student getStudent(String studentLoginName);

	/**
	 * 获取指导学生
	 * @param teacherLoginName
	 * @return
	 */
	public List<Student> getStudents(String teacherLoginName);

	/**
	 * 获取学生课题
	 * @param students
	 * @return
	 */
	public List<Thesis> getStudentsThesises(Collection<Student> students);

	/**
	 * 获取学生指导教师成绩输入状态
	 * @param students
	 * @return
	 */
	public List<String> getStudentsZhidaoScoreStatuses(Collection<Student> students);

	/**
	 * 获取学生评阅教师成绩输入状态
	 * @param students
	 * @return
	 */
	public List<String> getStudentsPingyueScoreStatuses(Collection<Student> students);

	/**
	 * 获取学生答辩小组成绩输入状态
	 * @param students
	 * @return
	 */
	public List<String> getStudentsDabianScoreStatuses(Collection<Student> students);

	/**
	 * 获取学生评阅教师
	 * @param students
	 * @return
	 */
	public List<Teacher> getStudentsPingyueTeachers(Collection<Student> students);

	/**
	 * 获取学生答辩小组教师
	 * @param students
	 * @return
	 */
	public List<Teacher> getStudentsDabianTeachers(Collection<Student> students);

	/**
	 * 保存指导教师输入成绩
	 * @param student
	 * @return
	 */
	public Student saveScoreInputZhidaoDetail(Student student);

	/**
	 * 保存评阅教师输入成绩
	 * @param student
	 * @return
	 */
	public Student saveScoreInputPingyueDetail(Student student);

	/**
	 * 保存答辩小组输入成绩
	 * @param student
	 * @return
	 */
	public Student saveScoreInputDabianDetail(Student student);
}
