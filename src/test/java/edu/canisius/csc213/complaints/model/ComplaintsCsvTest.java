package edu.canisius.csc213.complaints.model;

import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComplaintsCsvTest {

    @Test
    public void testLoadComplaintsFromCsv() {
        // Load the CSV file from test resources
        InputStreamReader reader = new InputStreamReader(
                getClass().getResourceAsStream("/complaints_sample_1_30.csv"),
                StandardCharsets.UTF_8);

        // Parse the CSV into a list of Complaint objects
        List<Complaint> complaints = new CsvToBeanBuilder<Complaint>(reader)
                .withType(Complaint.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build()
                .parse();

        // Make sure we loaded 30 entries
        assertNotNull(complaints, "List should not be null");
        assertEquals(30, complaints.size(), "Should have loaded 30 complaints");

        // Check a couple sample fields on the first complaint
        Complaint c = complaints.get(0);
        assertTrue(c.getComplaintId() > 0, "Complaint ID should be present");
        assertNotNull(c.getProduct(), "Product should not be null");
        assertNotNull(c.getNarrative(), "Narrative should not be null");
    }
}
