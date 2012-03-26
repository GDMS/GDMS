package im.lich.gdms.core.service.teacher;

import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.model.teacher.Thesis;

import java.util.Collection;
import java.util.List;

public interface TeacherScoreInputService {
	public List<Student> getStudents(String teacherLoginName);

	public List<Thesis> getStudentsThesises(Collection<Student> students);

	public List<Teacher> getStudentsRemarkTeachers(Collection<Student> students);

	public List<Teacher> getStudentsReplyTeachers(Collection<Student> students);
}
