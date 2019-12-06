package additional;

public class Book {
    private String title;
    private int numberOfPages;
    private String author;

    public Book() {
        title = "-NONE-";
        numberOfPages = -1;
        author = "-none-";
    }

    public Book(String title, int numberOfPages, String author) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.author = author;
    }

    @Override
    public String toString() {
        return "\"" + title + "\", " + numberOfPages + " p., " + author + ".";
    }

}
