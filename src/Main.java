import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Document doc1 = new Document(0, "the brown fox jumped over the brown dog");
        Document doc2 = new Document(1, "the lazy brown dog sat in the corner");
        Document doc3 = new Document(2, "the red fox bit the lazy dog");

        ArrayList<Document> docs= new ArrayList<Document>();
        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);

        Search search = new Search();

        System.out.println(search.search("lazy", docs));
    }
}