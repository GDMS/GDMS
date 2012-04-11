package im.lich.gdms.core.service.student;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.Thesis;

import java.util.Collection;
import java.util.List;

public interface StudentThesisService extends BaseService {
	/**
	 * 获取学生课题信息
	 * @param students 学生集合
	 * @return List<Thesis>
	 */
	public List<Thesis> getStudentsThesises(Collection<Student> students);
}
