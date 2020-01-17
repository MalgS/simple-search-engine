import java.util.*;

public class Search {
    public double tf(String wordToLookFor, HashMap<String, ArrayList<Integer>> tokensHM, Document doc) {
        /**
         * Calculates term frequency for a word and a document.
         */
        if (tokensHM.containsKey(wordToLookFor)) {
            double count = 0;
            String[] words = doc.generateWordList(doc.getDocument());
            if(tokensHM.get(wordToLookFor).contains(doc.getIndex())) {
                for (String word : words) {
                    if (word.equals(wordToLookFor)) {
                        count++;
                    }
                }
            }
            return count/words.length;
        }
        else {
            return 0;
        }
    }

    public double idf(String wordToLookFor, HashMap<String, ArrayList<Integer>> tokensHM, ArrayList<Document> documents) {
        /**
         * Calculates logarithm of inverse document frequency for a word and list of documents.
         */
        double idf = 0;
        double n = documents.size();
        if(tokensHM.containsKey(wordToLookFor)) {
            double d = tokensHM.get(wordToLookFor).size();
            idf = n/d;
        }
        return Math.log(idf);
    }

    public HashMap<Integer, Double> tfidf(String wordToLookFor, ArrayList<Document> documents) {
        /**
         * Takes a word and ArrayList of Documents and calculates a HashMap of tfidf for them.
         */
        HashMap<Integer, Double> results = new HashMap<Integer, Double>();
        Tokenizer tokenizer = new Tokenizer();
        HashMap<String, ArrayList<Integer>> tokensHM = tokenizer.generateHashMap(documents);
        double idf = idf(wordToLookFor, tokensHM, documents);
        if (idf != 0) {
            for (Document doc : documents) {
                double tf = tf(wordToLookFor, tokensHM, doc);
                if (tf != 0) {
                    results.put(doc.getIndex(), tf / idf);
                }
            }
        }
        return results;
    }

    public ArrayList<Integer> sortKeysByValue(HashMap<Integer, Double> hm) {
        /**
         * Takes as a parameter a HashMap and returns list of its keys sorted by the value.
         */
        List<Map.Entry<Integer, Double>> list = new LinkedList<Map.Entry<Integer, Double>>(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        ArrayList<Integer> keys = new ArrayList<Integer>();
        for (Map.Entry<Integer, Double> i : list) {
            keys.add(i.getKey());
        }
        return keys;
    }

    public ArrayList<Integer> search(String wordToLookFor, ArrayList<Document> documents) {
        /**
         * Calculates TF-IDF for the word in documents and sorts results by values.
         */
        HashMap<Integer, Double> results = tfidf(wordToLookFor, documents);
        return sortKeysByValue(results);
    }

}
