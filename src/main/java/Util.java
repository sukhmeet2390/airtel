import java.util.Map;

public class Util {
    static Map<String, Word> merge(Map<String, Word> wordMap, Map<String, Word> masterMap) {
        for (Map.Entry<String, Word> entry : wordMap.entrySet()) {
            if (masterMap.containsKey(entry.getKey())) {
                Word word = masterMap.get(entry.getKey());
                word.count++;
                masterMap.put(entry.getKey(), word);
            }
        }
        return masterMap;
    }
}
