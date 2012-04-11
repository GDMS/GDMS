package im.lich.gdms.core.service.teacher;

import java.util.List;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.teacher.TeacherDept;

public interface TeacherDeptService extends BaseService {
	/**
	 * 获取教师部门列表
	 * @return List<TeacherDept>
	 */
	public List<TeacherDept> getTeacherDepts();
}
