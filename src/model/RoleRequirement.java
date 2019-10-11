package model;

import java.util.Collection;
import java.util.List;
import enums.Role;
import enums.Skill;

public class RoleRequirement {
	private Role role;
	
	private List<Skill> skills;
	
	public RoleRequirement(Role role, Collection<Skill> skills) {

		this.role = role;
		this.skills = (List<Skill>) skills;
	}

	public Role getRole() {
		return role;
	}

	
	public Collection<Skill> getSkills() {

		return skills;
	}

	/**
	 * Takes another roleRequirement and returns a -1 if the two requirements are
	 * for different roles, 0 if the roles are equal, and an integer representing
	 * the number of matching skills if any are found
	 */
	public int compare(RoleRequirement role) {
		int result = -1;
		List<Skill> roleSkills = (List<Skill>) role.getSkills();
		if (role.getRole() == this.role) {
			result = 0;
			for (Skill s : roleSkills) {
				if (skills.contains(s)) {
					result++;
				}
			}
		}
		return result;
	}

	public void addSkill(Skill skill) {
		skills.add(skill);
	}
}
