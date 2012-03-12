package im.lich.gdms.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Preview extends IdEntity {
	private static final long serialVersionUID = -7315029590096827074L;

	@Column(length = 20, nullable = false, unique = true)
	private Long thesis_id;

	@Column(length = 20, nullable = false, unique = true)
	private Long student_id;

	@Column(nullable = false)
	private int subjectOrder;

	public Long getThesis_id() {
		return thesis_id;
	}

	public void setThesis_id(Long thesis_id) {
		this.thesis_id = thesis_id;
	}

	public Long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}

	public int getSubjectOrder() {
		return subjectOrder;
	}

	public void setSubjectOrder(int subjectOrder) {
		this.subjectOrder = subjectOrder;
	}

}
