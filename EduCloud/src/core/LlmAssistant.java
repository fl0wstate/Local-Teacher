package core;

import java.util.List;

/**
 * LlmAssistant - a small stub that simulates guidance generation from a local LLM.
 */
public class LlmAssistant {
    /**
     * Generate simple study guidance based on materials (simulated).
     * @param course Course name
     * @param materials list of materials
     * @return guidance string
     */
    public String generateGuidance(String course, List<String> materials) {
        StringBuilder sb = new StringBuilder();
        sb.append("🤖 LLM Guide for ").append(course).append(":\n");
        if (materials.isEmpty()) {
            sb.append("No materials available to generate guidance.\n");
        } else {
            sb.append("Start by reviewing: ").append(materials.get(0)).append(".\n");
            sb.append("Focus on key concepts and try to explain them in your own words.\n");
            sb.append("Ask targeted questions to deepen understanding.\n");
        }
        return sb.toString();
    }
}
