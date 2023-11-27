package com.ministryoftesting.unit.db;

import com.ministryoftesting.db.ProjectDB;
import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.project.Project;
import com.ministryoftesting.models.project.ProjectDetails;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectDBTest {

    private static ProjectDB projectDB;

    @BeforeAll
    public static void initialiseDB() {
        projectDB = new ProjectDB();
    }

    @Test
    public void returnPositiveResultCreatingProject() throws SQLException {
        Project project = new Project( "Project 1", "This is a brief description of Project 1");

        int result = projectDB.createProject(project);

        assertTrue(result > 0);
    }

    @Test
    public void returnPositiveResultDeletingProject() throws SQLException {
        boolean result = projectDB.deleteProject(1);

        assertTrue(result);
    }

    @Test
    public void returnNegativeResultDeletingProject() throws SQLException {
        boolean result = projectDB.deleteProject(999);

        assertFalse(result);
    }

    @Test
    public void returnPositiveResultDeletingEntry() throws SQLException {
        boolean result = projectDB.deleteEntry(1, 1);

        assertTrue(result);
    }

    @Test
    public void returnNegativeResultDeletingEntry() throws SQLException {
        boolean result = projectDB.deleteEntry(1, 999);

        assertFalse(result);
    }

    @Test
    public void returnPositiveResultWhenCreatingEntry() throws SQLException {
        int result = projectDB.storeEntry(1, new Entry(LocalDate.now(), 8, "Ate cake"));

        assertTrue(result > 0);
    }

    @Test
    public void returnProjectsWhenQueryingProjectDB() throws SQLException {
        projectDB.createProject(new Project( "Project 1", "This is a brief description of Project 1"));
        projectDB.createProject(new Project( "Project 2", "This is a brief description of Project 2"));
        projectDB.createProject(new Project( "Project 3", "This is a brief description of Project 3"));
        List<Project> projects = projectDB.getProjects();

        Approvals.verify(projects.toString());
    }

    @Test
    public void returnProjectDetailsWhenQueryingProjectDB() throws SQLException {
        int projectId = projectDB.createProject(new Project( "Group project", "This project has lots of entries"));
        projectDB.storeEntry(projectId, new Entry(LocalDate.of(2023,1,1), 8, "Ate cake"));
        projectDB.storeEntry(projectId, new Entry(LocalDate.of(2023,1,2), 7, "Ate pie"));
        projectDB.storeEntry(projectId, new Entry(LocalDate.of(2023, 1, 3), 6, "Ate pizza"));

        ProjectDetails project = projectDB.getProject(projectId);

        assertEquals("Group project", project.getName());
        assertEquals(3, project.getEntries().size());
    }
}
