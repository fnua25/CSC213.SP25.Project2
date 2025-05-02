package edu.canisius.csc213.complaints.service;

import edu.canisius.csc213.complaints.model.Complaint;
import edu.canisius.csc213.complaints.storage.ComplaintLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComplaintSimilarityServiceTest {

    private static List<Complaint> complaints;
    private static ComplaintSimilarityService similarityService;

    @BeforeAll
    public static void setup() throws Exception {
        complaints = ComplaintLoader.loadComplaintsWithEmbeddings("/complaints_sample_1_30.csv", "/embeddings_sample_1_30.jsonl");
        similarityService = new ComplaintSimilarityService(complaints);
    }

    @Test
    public void testFindTop3SimilarComplaints() {
        // Grab a known complaint
        Complaint target = complaints.stream()
                .filter(c -> c.getComplaintId() == 11454889L)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Target complaint not found"));

        List<Complaint> top3 = similarityService.findTop3Similar(target);

        // Assertions
        assertNotNull(top3, "Top 3 list should not be null");
        assertEquals(3, top3.size(), "Should return exactly 3 most similar complaints");

        for (Complaint similar : top3) {
            assertNotNull(similar.getEmbedding(), "Similar complaint should have an embedding");
            assertNotEquals(similar.getComplaintId(), target.getComplaintId(), "Should not include the target itself");
        }

        // 👀 Show me the narratives!
        System.out.println("🔍 Target Narrative:");
        System.out.println("----------------------------------------");
        System.out.println(target.getNarrative());
        System.out.println("\n🔗 Top 3 Similar Narratives:");
        System.out.println("----------------------------------------");

        for (int i = 0; i < top3.size(); i++) {
            Complaint c = top3.get(i);
            System.out.println("[" + (i + 1) + "] ID: " + c.getComplaintId());
            System.out.println(c.getNarrative());
            System.out.println("-----");
        }
    }
}
