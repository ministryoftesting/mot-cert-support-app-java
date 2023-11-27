package com.ministryoftesting.unit.examples.module5;

import com.ministryoftesting.db.ProjectDB;
import com.ministryoftesting.models.project.Entry;
import com.ministryoftesting.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MockedUnitCheckExample {

    @Mock
    private ProjectDB projectDB;

    @Autowired
    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    public void initialiseMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void returnPositiveWhenEntryCreated() throws SQLException {
        Entry entry = new Entry(LocalDate.of(2023,1,1), 8, "Ate cake");

        projectService.createEntry(1, entry);

        verify(projectDB, times(1)).createEntry(1, entry);
    }

}
