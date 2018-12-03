import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FileTokenizer {
    public Map<String, Word> tokenize(String filePath) throws IOException {
        Map<String, Word> countMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath)));
        String line;
        while ((line = reader.readLine()) != null) {

            String[] words = line.split("\\s+");
            for (String word : words) {
                word = sanitize(word);
                if ("".equals(word)) continue;

                Word wordObj = countMap.get(word);
                if (wordObj == null) {
                    wordObj = new Word(word, 1);
                    countMap.put(word, wordObj);
                }
            }
        }
        reader.close();
        return countMap;
    }

    private String sanitize(String word) {
        return word
                .replaceAll("[^a-zA-Z0-9]", "")
                .toLowerCase();
    }

}
