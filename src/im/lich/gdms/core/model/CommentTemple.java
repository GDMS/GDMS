package im.lich.gdms.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommentTemple extends IdEntity {
	private static final long serialVersionUID = -180477163122663159L;

	@Column(length = 20, nullable = false)
	private String grade;//评语等第

	@Column(length = 250, nullable = false)
	private String zdpingyu;//指导评语

	@Column(length = 250, nullable = false)
	private String pypingyu;//评阅评语

	@Column(length = 250, nullable = false)
	private String dbpingyu;//答辩评语

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getZdpingyu() {
		return zdpingyu;
	}

	public void setZdpingyu(String zdpingyu) {
		this.zdpingyu = zdpingyu;
	}

	public String getPypingyu() {
		return pypingyu;
	}

	public void setPypingyu(String pypingyu) {
		this.pypingyu = pypingyu;
	}

	public String getDbpingyu() {
		return dbpingyu;
	}

	public void setDbpingyu(String dbpingyu) {
		this.dbpingyu = dbpingyu;
	}

}
