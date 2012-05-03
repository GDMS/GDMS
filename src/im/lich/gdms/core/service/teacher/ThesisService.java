package im.lich.gdms.core.service.teacher;

import im.lich.gdms.base.service.BaseService;
import im.lich.gdms.core.model.teacher.Thesis;

import java.util.List;

public interface ThesisService extends BaseService {
	/**
	 * 获取所有课题信息
	 * @return List<Thesis>
	 */
	public List<Thesis> getThesises();
}