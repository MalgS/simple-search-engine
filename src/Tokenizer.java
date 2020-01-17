import java.util.ArrayList;
import java.util.HashMap;

public class Tokenizer {
    public HashMap<String, ArrayList<Integer>> generateHashMap(ArrayList<Document> documents) {
        /**
         * Creates a HashMap from the documents, where keys are words from documents and
         * values indexes of documents in which the word appears.
         */
        HashMap<String, ArrayList<Integer>> tokensHM = new HashMap<String, ArrayList<Integer>>();
        for (Document doc : documents) {
            String[] words = doc.generateWordList(doc.getDocument());
            for (String word : words) {
                if (tokensHM.containsKey(word)) {
                    ArrayList<Integer> indexes = tokensHM.get(word);
                    Integer index = doc.getIndex();
                    if (!indexes.contains(index)) {
                        indexes.add(index);
                    }
                }
                else {
                    ArrayList<Integer> indexes = new ArrayList<Integer>();
                    indexes.add(doc.getIndex());
                    tokensHM.put(word, indexes);
                }
            }
        }
        return tokensHM;
    }
}
