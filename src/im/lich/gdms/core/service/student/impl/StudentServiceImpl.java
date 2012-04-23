package im.lich.gdms.core.service.student.impl;

import im.lich.gdms.base.service.BaseServiceImpl;
import im.lich.gdms.core.dao.student.StudentDao;
import im.lich.gdms.core.dao.teacher.DabianRecordDao;
import im.lich.gdms.core.dao.teacher.PingyueRecordDao;
import im.lich.gdms.core.dao.teacher.TeacherDao;
import im.lich.gdms.core.dao.teacher.ThesisDao;
import im.lich.gdms.core.model.student.Student;
import im.lich.gdms.core.model.teacher.DabianRecord;
import im.lich.gdms.core.model.teacher.PingyueRecord;
import im.lich.gdms.core.model.teacher.Teacher;
import im.lich.gdms.core.model.teacher.Thesis;
import im.lich.gdms.core.service.student.StudentService;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl extends BaseServiceImpl implements StudentService {

	@Resource
	private StudentDao studentDao;

	@Resource
	private ThesisDao thesisDao;

	@Resource
	private TeacherDao teacherDao;

	@Resource
	private PingyueRecordDao pingyueRecordDao;

	@Resource
	private DabianRecordDao dabianRecordDao;

	@Override
	public Student getStudent(String loginName) {
		Assert.notNull(loginName);

		Student student = studentDao.findByLoginName(loginName);
		Assert.notNull(student);

		logger.debug("查询学生信息：{}", student);
		return student;
	}

	@Override
	public Student getStudentInfo(String loginName) {
		return getStudent(loginName);
	}

	@Override
	public Thesis getStudentThesis(Student student) {
		Assert.notNull(student);
		Long thesisId = student.getThesisId();
		return thesisDao.findOne(thesisId);
	}

	@Override
	public List<Thesis> getStudentsThesises(Collection<Student> students) {
		List<Thesis> thesises = Lists.newArrayList();
		for (Student s : students) {
			Long thesisId = s.getThesisId();
			Thesis t = thesisDao.findOne(thesisId);
			Assert.notNull(t);
			Assert.hasText(t.getAssign());
			thesises.add(t);
		}
		return thesises;
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveStudentInfo(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		//判断是否需要修改密码
		String pass = s.getPassword();
		if (StringUtils.isNotEmpty(pass)) {
			_s.setPassword(pass);
			logger.debug("用户：{} 修改密码", _s.getLoginName());
		}

		_s.setName(s.getName());
		_s.setGender(s.getGender());
		_s.setPhone(s.getPhone());
		_s.setEmail(s.getEmail());
		_s.setMajor(s.getMajor());
		_s.setStuClass(s.getStuClass());

		logger.debug("保存学生信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	public String getMidternCheckWarn(String loginName) {
		Assert.notNull(loginName);

		Student student = studentDao.findByLoginName(loginName);
		Assert.notNull(student);

		String warn = student.getWarn();
		logger.debug("学生：{}，中期检查警告信息：{}", loginName, warn);

		return warn;
	}

	@Override
	public Student getMidternCheckInfo(String loginName) {
		Assert.notNull(loginName);

		Student student = studentDao.findByLoginName(loginName);
		logger.debug("查询学生中期检查信息：{}", student);
		return student;
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveMidternCheckInfo(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setProgress(s.getProgress());
		_s.setQuality(s.getQuality());
		_s.setAttitude(s.getAttitude());
		_s.setDuty(s.getDuty());

		logger.debug("保存学生中期检查信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student saveMidternCheckInfoByTeacher(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setProgress(s.getProgress());
		_s.setQuality(s.getQuality());
		_s.setAttitude(s.getAttitude());
		_s.setDuty(s.getDuty());
		_s.setWarn(s.getWarn());

		logger.debug("保存学生中期检查信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student delMidternCheckWarn(String studentLoginName) {
		Assert.notNull(studentLoginName);

		//获取内部学生
		Student _s = studentDao.findByLoginName(studentLoginName);
		Assert.notNull(_s);

		_s.setWarn("");

		logger.debug("保存学生中期检查信息：{}", _s);
		return studentDao.save(_s);
	}

	private static final String ZHIDAO_NOT_INPUT = "未输入";
	private static final String ZHIDAO_INPUT = "已输入";

	@Override
	public List<String> getStudentsZhidaoScoreStatuses(Collection<Student> students) {
		List<String> statuses = Lists.newArrayList();
		for (Student s : students) {
			int zd1 = s.getZd1grade();
			int zd2 = s.getZd2grade();
			int zd3 = s.getZd3grade();
			int zd4 = s.getZd4grade();
			String status = ZHIDAO_NOT_INPUT;
			if (zd1 > 0 && zd2 > 0 && zd3 > 0 && zd4 > 0)
				status = ZHIDAO_INPUT;
			statuses.add(status);
		}
		return statuses;
	}

	@Override
	public List<String> getStudentsPingyueScoreStatuses(Collection<Student> students) {
		List<String> statuses = Lists.newArrayList();
		for (Student s : students) {
			int py1 = s.getPy1grade();
			int py2 = s.getPy2grade();
			String status = "未输入";

			//根据记录查询
			PingyueRecord _py = pingyueRecordDao.findByStudentId(s.getId());
			if (_py != null && (py1 > 0 && py2 > 0)) {
				Teacher _t = teacherDao.findOne(_py.getTeacherId());
				status = "已输入：" + _t.getName();
			}

			statuses.add(status);
		}
		return statuses;
	}

	@Override
	public List<String> getStudentsDabianScoreStatuses(Collection<Student> students) {
		List<String> statuses = Lists.newArrayList();
		for (Student s : students) {
			int db1 = s.getDb1grade();
			int db2 = s.getDb2grade();
			String status = "未输入";

			//根据记录查询
			DabianRecord _db = dabianRecordDao.findByStudentId(s.getId());
			if (_db != null && (db1 > 0 && db2 > 0)) {
				Teacher _t = teacherDao.findOne(_db.getTeacherId());
				status = "已输入：" + _t.getName();
			}

			statuses.add(status);
		}
		return statuses;
	}

	@Transactional(readOnly = false)
	private void updateTotalGrade(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		int zd1 = _s.getZd1grade();
		int zd2 = _s.getZd2grade();
		int zd3 = _s.getZd3grade();
		int zd4 = _s.getZd4grade();
		int py1 = _s.getPy1grade();
		int py2 = _s.getPy2grade();
		int db1 = _s.getDb1grade();
		int db2 = _s.getDb2grade();

		double total = 0;
		total += zd1 * 0.15 + zd2 * 0.05 + zd3 * 0.05 + zd4 * 0.05;
		total += py1 * 0.2 + py2 * 0.1;
		total += db1 * 0.3 + db2 * 0.1;
		logger.debug("updateTotalGrade-total: {}", total);

		int grade = (int) Math.round(total);
		logger.debug("updateTotalGrade-grade: {}", grade);
		_s.setGrade(grade);

		logger.debug("更新成绩总分：{}", _s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student updateScoreInputZhidaoDetail(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setZd1grade(s.getZd1grade());
		_s.setZd2grade(s.getZd2grade());
		_s.setZd3grade(s.getZd3grade());
		_s.setZd4grade(s.getZd4grade());

		updateTotalGrade(_s);

		logger.debug("保存指导教师输入成绩信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student addScoreInputPingyue(Student student, String teacherLoginName) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setPy1grade(s.getPy1grade());
		_s.setPy2grade(s.getPy2grade());

		updateTotalGrade(_s);

		Teacher _t = teacherDao.findByLoginName(teacherLoginName);
		Assert.notNull(_t);

		PingyueRecord py = new PingyueRecord(_s.getId(), _t.getId());
		PingyueRecord _py = pingyueRecordDao.save(py);
		Assert.notNull(_py);

		logger.debug("保存评阅教师输入成绩信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student delScoreInputPingyue(String studentLoginName) {
		Student s = studentDao.findByLoginName(studentLoginName);
		Long pingyueId = pingyueRecordDao.findByStudentId(s.getId()).getId();
		pingyueRecordDao.delete(pingyueId);
		return s;
	}

	@Override
	@Transactional(readOnly = false)
	public Student addScoreInputDabian(Student student, String teacherLoginName) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());
		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setDb1grade(s.getDb1grade());
		_s.setDb2grade(s.getDb2grade());

		updateTotalGrade(_s);

		Teacher _t = teacherDao.findByLoginName(teacherLoginName);
		Assert.notNull(_t);

		DabianRecord db = new DabianRecord(_s.getId(), _t.getId());
		DabianRecord _db = dabianRecordDao.save(db);
		Assert.notNull(_db);

		logger.debug("保存答辩小组输入成绩信息：{}", _s);
		return studentDao.save(_s);
	}

	@Override
	@Transactional(readOnly = false)
	public Student delScoreInputDabian(String studentLoginName) {
		Student s = studentDao.findByLoginName(studentLoginName);
		Long dabianId = dabianRecordDao.findByStudentId(s.getId()).getId();
		dabianRecordDao.delete(dabianId);
		return s;
	}

	@Override
	@Transactional(readOnly = false)
	public Student updatePingyu(Student student) {
		Assert.notNull(student);
		Assert.notNull(student.getLoginName());

		//简化名称
		Student s = student;

		//获取内部学生
		Student _s = studentDao.findByLoginName(s.getLoginName());
		Assert.notNull(_s);

		_s.setZdpingyu(s.getZdpingyu());

		_s.setPypingyu(s.getPypingyu());

		_s.setQuestion1(s.getQuestion1());
		_s.setAnswer1(s.getAnswer1());
		_s.setQuestion2(s.getQuestion2());
		_s.setAnswer2(s.getAnswer2());
		_s.setQuestion3(s.getQuestion3());
		_s.setAnswer3(s.getAnswer3());
		_s.setDbpingyu(s.getDbpingyu());

		logger.debug("保存评语信息：{}", _s);
		return studentDao.save(_s);
	}
}
