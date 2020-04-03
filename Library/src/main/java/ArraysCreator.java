import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ArraysCreator {
    private List<Book> booksArrayList = new ArrayList<>();
    private List<Author> authorArrayList = new ArrayList<>();

    Logger logger = LogManager.getLogger();

    public ArraysCreator(){
        logger.info("called lists constructor");

        booksArrayList.add(new Book("Три товарища", 384, new ArrayList<Author>()));
        booksArrayList.add(new Book("Ночь в Лиссабоне", 288, new ArrayList<Author>())); //Возраст автора в момент СМЭРТИ: 72, Эрих Мария Ремарк
        booksArrayList.add(new Book("The Hundred-Page Machine Learning Book", 100, new ArrayList<Author>())); //наверн около 40, Андрей Бурков
        booksArrayList.add(new Book("Двенадцать стульев", 414, new ArrayList<Author>()));
        booksArrayList.add(new Book("Одноэтажная Америка", 464, new ArrayList<Author>()));//И. Ильф - 39, Е. Петров - 39 (во приколы)

        authorArrayList.add(new Author("Эрих Мария Ремарк", (short)72, new ArrayList<>(Arrays.asList(booksArrayList.get(0), booksArrayList.get(1)))));
        authorArrayList.add(new Author("А. Бурков", (short)40, new ArrayList<Book>(Arrays.asList(booksArrayList.get(2)))));
        authorArrayList.add(new Author("И. Ильф", (short)39, new ArrayList<Book>(Arrays.asList(booksArrayList.get(3), booksArrayList.get(4)))));
        authorArrayList.add(new Author("Е. Петров", (short)40, new ArrayList<Book>(Arrays.asList(booksArrayList.get(3), booksArrayList.get(4)))));


        authorArrayList.forEach((Author author) -> {
            author.getBooks().forEach(book -> {
                List<Author> authors = book.getAuthors();
                authors.add(author);
                book.setAuthors(authors);
            });
        });

        logger.info("created books and authors lists successfully");
    }

    public List<Book> getBooksArrayList() {
        return booksArrayList;
    }

    public List<Author> getAuthorArrayList() {
        return authorArrayList;
    }
}
