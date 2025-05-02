package edu.canisius.csc213.complaints.service;

import edu.canisius.csc213.complaints.model.Complaint;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComplaintSimilarityService {

    private final List<Complaint> complaints;

    public ComplaintSimilarityService(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public List<Complaint> findTop3Similar(Complaint target) {
        double[] targetVector = vectorize(target);
        return complaints.stream()
                .filter(c -> !c.equals(target))
                .map(c -> new ComplaintWithScore(c, cosineSimilarity(targetVector, vectorize(c))))
                .sorted(Comparator.comparingDouble((ComplaintWithScore cws) -> cws.score).reversed())
                .limit(3)
                .map(cws -> cws.complaint)
                .collect(Collectors.toList());
    }

    private double[] vectorize(Complaint complaint) {
        String narrative = complaint.getNarrative() != null ? complaint.getNarrative() : "";
        String text = narrative.toLowerCase();
        double[] vec = new double[26]; // frequency vector for a-z
        for (char c : text.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                vec[c - 'a'] += 1.0;
            }
        }
        return vec;
    }

    private double cosineSimilarity(double[] a, double[] b) {
        double dot = 0.0, normA = 0.0, normB = 0.0;
        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }
        return (normA == 0 || normB == 0) ? 0.0 : dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    private static class ComplaintWithScore {
        Complaint complaint;
        double score;

        ComplaintWithScore(Complaint c, double s) {
            this.complaint = c;
            this.score = s;
        }
    }
}
