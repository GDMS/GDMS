package im.lich.gdms.core.service.teacher;

import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.model.teacher.TeacherDept;

import java.util.List;

public interface TeacherInfoService {
	public Teacher getTeacherInfo(String loginName);

	public Teacher saveTeacherInfo(Teacher teacher);

	public List<TeacherDept> getTeacherDepts();
}
