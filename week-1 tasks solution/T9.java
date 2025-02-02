import java.io.*;
import java.util.*;

public class T9 {

    public static void analyzeLogFile(String inputFile, String outputFile, List<String> keywords) {
        Map<String, Integer> keywordCount = new HashMap<>();

        for (String keyword : keywords) {
            keywordCount.put(keyword, 0);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                for (String keyword : keywords) {
                    if (line.contains(keyword)) {
                        keywordCount.put(keyword, keywordCount.get(keyword) + 1);
                    }
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write("Log Analysis Report\n");
                writer.write("====================\n");
                for (Map.Entry<String, Integer> entry : keywordCount.entrySet()) {
                    writer.write(entry.getKey() + ": " + entry.getValue() + " occurrences\n");
                }
            }
            System.out.println("Log file analysis completed. Results saved in " + outputFile);

        } catch (IOException e) {
            System.err.println("Error processing the log file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        List<String> keywords = Arrays.asList("ERROR", "WARNING", "INFO", "CRITICAL");

        String inputFile = "D:\\gp\\input_log.txt";
        String outputFile = "analysis_report.txt";

        analyzeLogFile(inputFile, outputFile, keywords);
    }
}
