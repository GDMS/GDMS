package im.lich.gdms.core.model.teacher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReplyRecord extends IdEntity {
	private static final long serialVersionUID = -6626250567951247734L;

	private Long studentId;//答辩学生id
	private Long teacherId;//答辩老师id

	public ReplyRecord(Long studentId, Long teacherId) {
		super();
		this.studentId = studentId;
		this.teacherId = teacherId;
	}

	@Column(nullable = false, unique = true)
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	@Column(nullable = false)
	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
}
