package im.lich.gdms.core.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import im.lich.gdms.base.model.IdEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Teacher extends IdEntity {
	private static final long serialVersionUID = 5919308243195296451L;

	@Column(length = 20, nullable = false, unique = true)
	private String no;//教工号

	@Column(length = 20, nullable = false)
	private String name;//姓名

	@Column(length = 50, nullable = false)
	private String password;//密码

	@Column(length = 50, nullable = false)
	private String dept;//部门

	@Column(nullable = false)
	private Integer subnum;//可指导学生数、课题数

	@Column(length = 50, nullable = false)
	private String tel;//电话

	@Column(length = 250, nullable = false)
	private String mail;//邮箱

	private Set<Thesis> thesises = new HashSet<Thesis>();

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Integer getSubnum() {
		return subnum;
	}

	public void setSubnum(Integer subnum) {
		this.subnum = subnum;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
	public Set<Thesis> getThesises() {
		return thesises;
	}

	public void setThesises(Set<Thesis> thesises) {
		this.thesises = thesises;
	}

}
