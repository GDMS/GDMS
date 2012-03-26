package im.lich.gdms.core.model.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "thesisId", "studentId" }))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Preview extends IdEntity {
	private static final long serialVersionUID = -7315029590096827074L;

	private Long thesisId;//课题ID
	private Long studentId;//学生ID
	private int subjectOrder = 1;//预选顺序

	public Preview(Long thesisId, Long studentId) {
		super();
		this.thesisId = thesisId;
		this.studentId = studentId;
	}

	public Preview(Long thesisId, Long studentId, int subjectOrder) {
		super();
		this.thesisId = thesisId;
		this.studentId = studentId;
		this.subjectOrder = subjectOrder;
	}

	@Column(nullable = false)
	public Long getThesisId() {
		return thesisId;
	}

	public void setThesisId(Long thesisId) {
		this.thesisId = thesisId;
	}

	@Column(nullable = false)
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	@Column(nullable = false)
	public int getSubjectOrder() {
		return subjectOrder;
	}

	public void setSubjectOrder(int subjectOrder) {
		this.subjectOrder = subjectOrder;
	}
}
