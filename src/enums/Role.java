package enums;

public enum Role {
	LEADER(1),
	DBA(2),
	PROGRAMMER(3),
	UIDESIGNER(4);
	
	private final int id;
	
	Role(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public static Role getRole(int roleId) {
		for (Role role : Role.values()) {
			int id = role.getId();
			if (id == roleId) {
				return role;
			}
		}
		
		return null;
	}
}
