import java.util.List;
import java.util.Objects;

public class Author {
    String name;
    private short age;
    private List<Book> books;

    public Author(){
        this.name = null;
        this.age = 0;
        this.books = null;
    }

    public Author(String name, short age, List<Book> books){
        this.name = name;
        this.age = age;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return age == author.age &&
                Objects.equals(name, author.name) &&
                Objects.equals(books, author.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("Author: ");
        sb.append(name).append("(").append(age).append("),\n");
        sb.append("Author's books: ");
        books.stream().forEach((book) -> sb.append(book.getTitle()).append(", "));
        sb.delete(sb.lastIndexOf(", "), sb.lastIndexOf(", ") + 1);
        return sb.toString();
    }

}
