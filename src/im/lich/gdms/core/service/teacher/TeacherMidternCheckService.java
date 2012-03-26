package im.lich.gdms.core.service.teacher;

import im.lich.gdms.core.model.student.Student;

import java.util.List;

public interface TeacherMidternCheckService {
	public List<Student> getStudents(String teacherLoginName);

	public Student getStudent(String studentLoginName);

	public Student saveMidternCheckWarn(Student student);

	public Student delMidternCheckWarn(String studentLoginName);
}
