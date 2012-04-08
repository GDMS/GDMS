package im.lich.gdms.core.security;

public enum RoleType {
	ADMIN("admin", "ROLE_ADMIN"), TEACHER("teacher", "ROLE_TEACHER"), STUDENT("student", "ROLE_STUDENT");

	private String value;
	private String roleNmae;

	RoleType(String value, String roleNmae) {
		this.value = value;
		this.roleNmae = roleNmae;
	}

	public static RoleType getRoleType(String name) {
		for (RoleType rt : RoleType.values()) {
			if (name.equals(rt.getValue()))
				return rt;
		}
		throw new IllegalArgumentException("No RoleType value found");
	}

	public String getRoleName() {
		return roleNmae;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
