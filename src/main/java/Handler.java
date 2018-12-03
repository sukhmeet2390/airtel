import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class Handler {
    private FileTokenizer fileTokenizer;

    public Handler() {
        this.fileTokenizer = new FileTokenizer();
    }

    public void execute(String[] paths) {
        System.out.println("Received file paths" + Arrays.toString(paths));
        Map<String, Word> map = tokenizeFiles(paths);
        printWords(map, paths.length);
    }

    private Map<String, Word> tokenizeFiles(String[] paths) {
        Map<String, Word> masterMap = null;
        try {
            masterMap = fileTokenizer.tokenize(paths[0]);
            for (int i = 1; i < paths.length; i++) {
                Map<String, Word> stringWordMap = fileTokenizer.tokenize(paths[i]);
                masterMap = Util.merge(stringWordMap, masterMap);
            }
        } catch (IOException e) {
            System.out.println("::ERROR:: File not found");
        }
        return masterMap;
    }

    private void printWords(Map<String, Word> masterMap, int freq) {
        for (Map.Entry<String, Word> entry : masterMap.entrySet()) {
            if (entry.getValue().count == freq) {
                System.out.println(entry.getValue().word);
            }
        }
    }
}
