package im.lich.gdms.core.dao.teacher;

import im.lich.gdms.base.dao.BaseDao;
import im.lich.gdms.core.model.teacher.ReplyRecord;

import java.util.List;

public interface ReplyRecordDao extends BaseDao<ReplyRecord, Long> {

	public List<ReplyRecord> findByTeacherId(Long teacherId);

	public ReplyRecord findByStudentId(Long studnetId);
}
