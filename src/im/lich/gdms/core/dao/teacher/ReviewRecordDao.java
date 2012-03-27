package im.lich.gdms.core.dao.teacher;

import im.lich.gdms.base.dao.BaseDao;
import im.lich.gdms.core.model.teacher.ReviewRecord;

import java.util.List;

public interface ReviewRecordDao extends BaseDao<ReviewRecord, Long> {

	public List<ReviewRecord> findByTeacherId(Long teacherId);

	public ReviewRecord findByStudentId(Long studnetId);

}
