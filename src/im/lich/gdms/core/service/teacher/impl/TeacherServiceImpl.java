package im.lich.gdms.core.service.teacher.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.admin.SysPropertyDao;
import im.lich.gdms.core.dao.student.PreviewDao;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.dao.teacher.DabianRecordDao;
import im.lich.gdms.core.dao.teacher.PingyueRecordDao;
import im.lich.gdms.core.dao.teacher.TeacherDao;
import im.lich.gdms.core.dao.teacher.ThesisDao;
import im.lich.gdms.core.model.admin.SysProperty;
import im.lich.gdms.core.model.student.Preview;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.DabianRecord;
import im.lich.gdms.core.model.teacher.PingyueRecord;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.teacher.TeacherService;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class TeacherServiceImpl extends BaseServiceImpl implements TeacherService {

	@Resource
	private TeacherDao teacherDao;

	@Resource
	private StudentDao studentDao;

	@Resource
	private ThesisDao thesisDao;

	@Resource
	private PreviewDao previewDao;

	@Resource
	private PingyueRecordDao pingyueRecordDao;

	@Resource
	private DabianRecordDao dabianRecordDao;

	@Resource
	private SysPropertyDao sysPropertyDao;

	@Override
	public List<Teacher> getTeachers() {
		List<Teacher> teachers = Lists.newArrayList(teacherDao.findAll());

		logger.debug("获取Teacher数量：{}", teachers.size());
		return teachers;
	}

	@Override
	@Transactional(readOnly = false)
	public Teacher addTeacher(Teacher teacher) {
		return teacherDao.save(teacher);
	}

	@Override
	@Transactional(readOnly = false)
	public Teacher addOrUpdateTeacher(Teacher teacher) {
		Teacher _t = teacherDao.findByLoginName(teacher.getLoginName());
		if (_t != null) {
			teacher.setId(_t.getId());
		}

		return teacherDao.save(teacher);
	}

	@Override
	@Transactional(readOnly = false)
	public Teacher delTeacher(Long teacherId) {
		Teacher teacher = teacherDao.findOne(teacherId);
		//TODO 完成删除学生课题
		teacherDao.delete(teacher);
		return teacher;
	}

	@Override
	public Teacher getTeacherInfo(String loginName) {
		Assert.notNull(loginName);

		Teacher teacher = teacherDao.findByLoginName(loginName);
		Assert.notNull(teacher);

		logger.debug("查询教师信息：{}", teacher);

		return teacher;
	}

	@Override
	@Transactional(readOnly = false)
	public Teacher saveTeacherInfo(Teacher teacher) {
		Assert.notNull(teacher);
		Assert.notNull(teacher.getLoginName());
		//简化名称
		Teacher t = teacher;

		//获取内部学生
		Teacher _t = teacherDao.findByLoginName(t.getLoginName());
		Assert.notNull(_t);

		//判断是否需要修改密码
		String pass = t.getPassword();
		if (StringUtils.isNotEmpty(pass)) {
			_t.setPassword(pass);
			logger.debug("用户：{} 修改密码", _t.getLoginName());
		}

		_t.setName(t.getName());
		_t.setSubnum(t.getSubnum());
		_t.setMail(t.getMail());
		_t.setTel(t.getTel());
		_t.setDept(t.getDept());

		logger.debug("保存教师信息：{}", _t);
		return teacherDao.save(_t);
	}

	@Override
	public List<Student> getTeachingStudents(String teacherLoginName) {
		Assert.notNull(teacherLoginName);

		Teacher teacher = teacherDao.findByLoginName(teacherLoginName);
		List<Thesis> _thesises = Lists.newArrayList(teacher.getThesises());

		List<Thesis> thesises = Lists.newArrayList();
		for (Thesis t : _thesises)
			if (StringUtils.isNotBlank(t.getAssign()))
				thesises.add(t);

		if (thesises.isEmpty()) {
			logger.debug("课题数为零，返回空学生");
			return null;
		}

		List<Student> students = Lists.newArrayList();
		for (Thesis t : thesises) {
			Student s = studentDao.findByThesisId(t.getId());
			if (s != null)
				students.add(s);
		}

		//判断课题数、学生数是否符合
		logger.debug("课题数: {}", thesises.size());
		logger.debug("学生数: {}", students.size());
		Assert.isTrue(thesises.size() == students.size(), "课题数、学生数不一致");

		return students;
	}

	@Override
	public Thesis getTeacherThesis(Long thesisId) {
		Assert.notNull(thesisId);

		Thesis t = thesisDao.findOne(thesisId);
		String majorRestrict = t.getMajorRestrict();
		String[] ms = majorRestrict.split(",");
		List<String> majors = Arrays.asList(ms);
		t.setMajorRes(majors);

		return t;
	}

	@Override
	public List<Thesis> getTeacherThesises(String teacherLoginName) {
		Assert.notNull(teacherLoginName);

		Teacher teacher = teacherDao.findByLoginName(teacherLoginName);
		List<Thesis> _thesises = Lists.newArrayList(teacher.getThesises());

		for (Thesis t : _thesises) {
			String majorRestrict = t.getMajorRestrict();
			String[] ms = majorRestrict.split(",");
			List<String> majors = Arrays.asList(ms);
			t.setMajorRes(majors);
		}

		return _thesises;
	}

	@Override
	public List<Thesis> getTeacherUnassignedThesises(String teacherLoginName) {
		Assert.notNull(teacherLoginName);

		List<Thesis> thesises = getTeacherThesises(teacherLoginName);
		Iterator<Thesis> it = thesises.iterator();
		while (it.hasNext()) {
			Thesis t = it.next();
			if (StringUtils.isNotBlank(t.getAssign()))
				it.remove();
		}

		return thesises;
	}

	@Override
	public boolean isOverMaxAssign(String teacherLoginName) {
		int thesesNum = getTeacherThesises(teacherLoginName).size();
		SysProperty sp = sysPropertyDao.findByPropKey("pct_max_assign");
		int num = Math.round((float) (thesesNum * 0.3));
		try {
			float pct = Integer.parseInt(sp.getPropVal()) / 100f;
			num = Math.round((float) (thesesNum * pct));
		} catch (NullPointerException e) {
			logger.debug("解析最大教师预选课题数量失败");
		} catch (NumberFormatException e) {
			logger.debug("解析最大教师预选课题数量失败");
		}
		int assignedNum = getAssignedStudentThesis(teacherLoginName).size();
		return assignedNum >= num;
	}

	@Override
	@Transactional(readOnly = false)
	public Thesis saveTeacherThesis(Thesis thesis, String teacherLoginName) {
		if (teacherDao.findByLoginName(teacherLoginName) == null)
			return null;
		//处理专业限制字符串
		List<String> majors = thesis.getMajorRes();
		String majorRestrict = StringUtils.join(majors, ',');
		majorRestrict = StringUtils.stripToEmpty(majorRestrict);
		thesis.setMajorRestrict(majorRestrict);

		//设置课题所属教师
		Teacher teacher = teacherDao.findByLoginName(teacherLoginName);
		thesis.setTeacher(teacher);

		return thesisDao.save(thesis);
	}

	@Override
	@Transactional(readOnly = false)
	public Thesis delTeacherThesis(Long thesisId) {
		//删除学生指向的课题
		Student student = studentDao.findByThesisId(thesisId);
		if (student != null) {
			student.setThesisId(0L);

			Long studentId = student.getId();
			PingyueRecord py = pingyueRecordDao.findByStudentId(studentId);
			if (py != null)
				pingyueRecordDao.delete(py);
			DabianRecord db = dabianRecordDao.findByStudentId(studentId);
			if (db != null)
				dabianRecordDao.delete(db);
		}

		Thesis thesis = thesisDao.findOne(thesisId);
		thesisDao.delete(thesis);
		return thesis;
	}

	@Override
	@Transactional(readOnly = false)
	public Thesis modTeacherThesis(Long thesisId, Thesis newThesis) {
		//处理专业限制字符串
		List<String> majors = newThesis.getMajorRes();
		String majorRestrict = StringUtils.join(majors, ',');
		majorRestrict = StringUtils.stripToEmpty(majorRestrict);
		newThesis.setMajorRestrict(majorRestrict);

		Thesis t = newThesis;
		Thesis _t = thesisDao.findOne(thesisId);

		_t.setMajorRestrict(t.getMajorRestrict());
		_t.setMode(t.getMode());
		_t.setName(t.getName());
		_t.setProperty(t.getProperty());
		_t.setType(t.getType());

		return thesisDao.save(_t);
	}

	@Override
	public List<Thesis> getStudentsPreviewTheses(List<Thesis> theses) {
		List<Thesis> thesises = Lists.newArrayList();
		for (Thesis t : theses) {
			Long thesisId = t.getId();
			List<Preview> previews = previewDao.findByThesisId(thesisId);
			for (Preview p : previews) {
				Long tId = p.getThesisId();
				Thesis _t = thesisDao.findOne(tId);
				thesises.add(_t);
			}
		}

		return thesises;
	}

	@Override
	public List<Student> getStudentsPreviewName(List<Thesis> theses) {
		List<Student> students = Lists.newArrayList();
		for (Thesis t : theses) {
			Long thesisId = t.getId();
			List<Preview> previews = previewDao.findByThesisId(thesisId);
			for (Preview p : previews) {
				Long sId = p.getStudentId();
				Student s = studentDao.findOne(sId);
				students.add(s);
			}
		}

		return students;
	}

	@Override
	public List<Integer> getStudentsPreviewOrder(List<Thesis> theses) {
		List<Integer> orders = Lists.newArrayList();
		for (Thesis t : theses) {
			Long thesisId = t.getId();
			List<Preview> previews = previewDao.findByThesisId(thesisId);
			for (Preview p : previews) {
				Integer o = p.getSubjectOrder();
				orders.add(o);
			}
		}

		return orders;
	}

	@Override
	public List<String> getStudentsPreviewAssign(List<Thesis> theses) {
		List<String> assigns = Lists.newArrayList();
		for (Thesis t : theses) {
			Long thesisId = t.getId();
			List<Preview> previews = previewDao.findByThesisId(thesisId);
			for (Preview p : previews) {
				Long sId = p.getStudentId();
				Student s = studentDao.findOne(sId);
				if (s.getThesisId() == 0L)
					assigns.add("0");
				else {
					assigns.add("无法选择");
				}

			}
		}

		return assigns;
	}

	@Override
	public List<Thesis> getAssignedStudentThesis(String loginName) {
		Assert.notNull(loginName);

		Teacher teacher = teacherDao.findByLoginName(loginName);
		List<Thesis> thesises = Lists.newArrayList(teacher.getThesises());

		Iterator<Thesis> it = thesises.iterator();
		while (it.hasNext()) {
			Thesis t = it.next();
			if (StringUtils.isBlank(t.getAssign()))
				it.remove();
		}
		return thesises;
	}

	@Override
	public List<Student> getAssignedStudentInfo(String loginName) {
		Assert.notNull(loginName);

		List<Thesis> thesises = getAssignedStudentThesis(loginName);

		List<Student> students = Lists.newArrayList();
		for (Thesis t : thesises) {
			Long thesisId = t.getId();
			Student s = studentDao.findByThesisId(thesisId);
			students.add(s);
		}

		return students;
	}

	@Override
	public List<Thesis> getUnassignedThesises(String loginName) {
		Assert.notNull(loginName);

		Teacher teacher = teacherDao.findByLoginName(loginName);
		List<Thesis> thesises = Lists.newArrayList(teacher.getThesises());

		Iterator<Thesis> it = thesises.iterator();
		while (it.hasNext()) {
			Thesis t = it.next();
			if (StringUtils.isNotBlank(t.getAssign()))
				it.remove();
		}
		return thesises;
	}
}
