package im.lich.gdms.core.dao.teacher;

import im.lich.gdms.base.dao.BaseDao;
import im.lich.gdms.core.model.teacher.ReplyRelRecord;

import java.util.List;

public interface ReplyRelRecordDao extends BaseDao<ReplyRelRecord, Long> {

	public List<ReplyRelRecord> findByTeacherId(Long teacherId);

}
