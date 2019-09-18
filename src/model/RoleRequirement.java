package model;

import java.util.Set;

import enums.Role;
import enums.Skill;

public class RoleRequirement {
	private Role role;
	private Set<Skill> skills;
	
	public RoleRequirement(Role role, Set<Skill> skills) {
		this.role = role;
		this.skills = skills;
	}
	
	public Role getRole() {
		return role;
	}
	
	public Set<Skill> getSkills() {
		return skills;
	}
}
