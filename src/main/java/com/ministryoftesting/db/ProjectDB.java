package com.ministryoftesting.db;

import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.models.project.Project;
import com.ministryoftesting.models.project.ProjectDetails;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectDB extends BaseDB {

    private final String CREATE_PROJECT = "INSERT INTO PROJECTS (name, description) VALUES (?, ?)";
    private final String DELETE_PROJECT = "DELETE FROM PROJECTS WHERE projectid = ?";
    private final String DELETE_ENTRY = "DELETE FROM ENTRIES WHERE projectid = ? AND entryid = ?";
    private final String INSERT_ENTRY = "INSERT INTO ENTRIES (projectid, date, hours, description) VALUES (?, ?, ?, ?)";
    private final String SELECT_PROJECTS = "SELECT * FROM PROJECTS";
    private final String SELECT_PROJECT_BY_ID = "SELECT * FROM PROJECTS WHERE projectid = ?";
    private final String SELECT_ENTRY_BY_PROJECT_ID = "SELECT * FROM ENTRIES WHERE projectid = ?";
    public int createProject(Project projectToCreate) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(CREATE_PROJECT);
        ps.setString(1, projectToCreate.getName());
        ps.setString(2, projectToCreate.getDescription());

        if(ps.executeUpdate() > 0){
            ResultSet lastInsertId = connection.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
            lastInsertId.next();

            return lastInsertId.getInt("LAST_INSERT_ID()");
        } else {
            return 0;
        }
    }

    public boolean deleteProject(int projectId) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE_PROJECT);
        ps.setInt(1, projectId);

        int result = ps.executeUpdate();

        return result == 1;
    }

    public ProjectDetails getProject(int projectId) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT_PROJECT_BY_ID);
        ps.setInt(1, projectId);

        ResultSet projectResults = ps.executeQuery();

        if(projectResults.next()){
            PreparedStatement ps2 = connection.prepareStatement(SELECT_ENTRY_BY_PROJECT_ID);
            ps2.setInt(1, projectId);

            ResultSet results = ps2.executeQuery();

            List<Entry> entries = new ArrayList<>();
            int totalHours = 0;

            while (results.next()) {
                entries.add(new Entry(results));
                totalHours += results.getInt("hours");
            }

            return new ProjectDetails(projectResults.getString("name"), projectResults.getString("description"), totalHours, entries);
        } else {
            return null;
        }

    }

    public List<Project> getProjects() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(SELECT_PROJECTS);

        ResultSet results = ps.executeQuery();
        List<Project> projects = new ArrayList<>();
        while(results.next()){
            projects.add(new Project(results));
        }

        return projects;
    }

    public int storeEntry(int projectId, Entry entry) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(INSERT_ENTRY);
        ps.setInt(1, projectId);
        ps.setDate(2, java.sql.Date.valueOf(entry.getDate()));
        ps.setInt(3, entry.getHours());
        ps.setString(4, entry.getDescription());

        if(ps.executeUpdate() > 0){
            ResultSet lastInsertId = connection.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
            lastInsertId.next();

            return lastInsertId.getInt("LAST_INSERT_ID()");
        } else {
            return 0;
        }
    }

    public Boolean deleteEntry(int projectId, int entryId) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE_ENTRY);
        ps.setInt(1, projectId);
        ps.setInt(2, entryId);

        int result = ps.executeUpdate();

        return result == 1;
    }
}
