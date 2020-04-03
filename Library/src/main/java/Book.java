import java.util.List;
import java.util.Objects;

public class Book {
    private String title;
    private int numberOfPages;
    private List<Author> authors;

    public Book(){
        this.title = null;
        this.numberOfPages = 0;
        this.authors = null;
    }

    public Book(String title, int numberOfPages, List<Author> authors){
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return numberOfPages == book.numberOfPages &&
                Objects.equals(title, book.title) &&
                Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, numberOfPages);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("Book '");
        sb.append(title).append("' (").append(numberOfPages).append(" pages)");
        sb.append("\nWritten by: ");
        authors.stream().forEach((author) -> sb.append(author.getName()).append("(age ").append(author.getAge()).append("), "));
        sb.delete(sb.lastIndexOf(", "), sb.lastIndexOf(", ") + 1);
        return sb.toString();
    }
}
