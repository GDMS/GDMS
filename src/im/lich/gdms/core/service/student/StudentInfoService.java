package im.lich.gdms.core.service.student;

import java.util.List;

import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.student.StudentMajor;

public interface StudentInfoService {
	public Student getStudentInfo(String loginName);

	public Student saveStudentInfo(Student student);

	public List<StudentMajor> getStudentMajors();
}
