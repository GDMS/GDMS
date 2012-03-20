package im.lich.gdms.core.dao.student;

import im.lich.gdms.base.dao.BaseDao;
import im.lich.gdms.core.model.student.Student;

public interface StudentDao extends BaseDao<Student, Long> {
	Student findByLoginName(String loginName);
}
