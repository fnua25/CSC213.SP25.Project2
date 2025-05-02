package edu.canisius.csc213.complaints.storage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class EmbeddingLoader {

    /**
     * Loads complaint embeddings from a JSONL (newline-delimited JSON) file.
     * Each line must be a JSON object with:
     * {
     * "complaintId": <long>,
     * "embedding": [<double>, <double>, ...]
     * }
     *
     * @param jsonlStream InputStream to the JSONL file
     * @return A map from complaint ID to its embedding vector
     * @throws IOException if the file cannot be read or parsed
     */
    public static Map<Long, double[]> loadEmbeddings(InputStream jsonlStream) throws IOException {
        Map<Long, double[]> embeddings = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(jsonlStream));

        ObjectMapper objectMapper = new ObjectMapper();
        String line;

        while ((line = reader.readLine()) != null) {
            JsonNode jsonNode = objectMapper.readTree(line);

            // Extract complaintId and embedding
            if (!jsonNode.has("id")) {
                throw new IllegalArgumentException("Missing 'id' field in JSON object: " + jsonNode.toString());
            }
            long id = jsonNode.get("id").asLong();

            JsonNode embeddingNode = jsonNode.get("embedding");

            // Convert embedding array from JsonNode to double[]
            double[] embedding = new double[embeddingNode.size()];
            for (int i = 0; i < embeddingNode.size(); i++) {
                embedding[i] = embeddingNode.get(i).asDouble();
            }

            // Add to the map
            embeddings.put(id, embedding);
        }

        return embeddings;
    }
}
