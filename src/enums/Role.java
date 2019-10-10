package enums;

public enum Role {
	LEADER(0),
	DBA(1),
	PROGRAMMER(2),
	UIDESIGNER(3);
	
	private final int id;
	
	Role(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
}
