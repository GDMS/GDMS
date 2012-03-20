package im.lich.gdms.core.dao.teacher;

import im.lich.gdms.base.dao.BaseDao;
import im.lich.gdms.core.model.teacher.Teacher;

public interface TeacherDao extends BaseDao<Teacher, Long> {
	Teacher findByLoginName(String loginName);
}
