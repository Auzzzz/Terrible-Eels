package enums;

public enum Skill {
	ORACLE(0),
	JAVA(1),
	CPLUSPLUS(2);
	
	private final int id;
	
	Skill(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
}
