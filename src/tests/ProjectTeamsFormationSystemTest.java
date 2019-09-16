package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import SQL.SQLConnectionImpl;
import interfaces.*;
import model.teamFormation.ProjectTeamsFormationSystemImpl;

public class ProjectTeamsFormationSystemTest {
	private SQLConnection db;
	private ProjectTeamsFormationSystem ptfs;

	@Before
	public void setUp() {
		db = new SQLConnectionImpl();
		ptfs = new ProjectTeamsFormationSystemImpl(db);
	}

	@Test
	public void test() {
		List<Project> popularProjects = ptfs.getPopularProjects();
		
		for (Project project : popularProjects) {
			System.out.println(project.getId());
			System.out.println(project.getProjectDesc());
		}
	}
}
