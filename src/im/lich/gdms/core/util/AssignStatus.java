package im.lich.gdms.core.util;

public enum AssignStatus {
	NULL(""), AUTO1("自动分配1"), AUTO2("自动分配2"), AUTO3("自动分配3"), TEACHER1("教师选择1"), TEACHER2("教师选择2"), TEACHER3("教师选择3"), ADMIN(
			"管理员分配");

	private String value;

	AssignStatus(String value) {
		this.value = value;
	}

	public static AssignStatus getAssignStatus(String value) {
		for (AssignStatus as : AssignStatus.values()) {
			if (value.equals(as.getValue()))
				return as;
		}
		throw new IllegalArgumentException("No AssignStatus value found");
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
