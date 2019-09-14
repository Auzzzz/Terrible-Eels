package model;

import java.util.List;

import enums.Role;
import enums.Skill;

public class RoleRequirement {
	private Role role;
	private List<Skill> skills;
	
	public RoleRequirement(Role role, List<Skill> skills) {
		this.role = role;
		this.skills = skills;
	}
	
	public Role getRole() {
		return role;
	}
	
	public List<Skill> getSkills() {
		return skills;
	}
}
