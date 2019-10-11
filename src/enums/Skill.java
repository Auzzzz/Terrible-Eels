package enums;

public enum Skill {
	ORACLE(1),
	JAVA(2),
	CPLUSPLUS(3);
	
	private final int id;
	
	Skill(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
}
