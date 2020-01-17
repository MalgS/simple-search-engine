public class Document {
    private Integer index;
    private String document;

    public Document(Integer index, String document) {
        this.index = index;
        this.document = document;
    }

    public Integer getIndex() {
        return index;
    }

    public String getDocument() {
        return document;
    }

    public String[] generateWordList(String str) {
        return str.split(" ");
    }

}
