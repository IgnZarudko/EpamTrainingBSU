import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Test()
public class StreamTest {
    Logger logger = LogManager.getLogger();

    ArraysCreator lib = new ArraysCreator();

    List<Book> books = lib.getBooksArrayList();

    Stream<Book> bookStream = books.stream();

    @Test
    public void printTest(){
        Stream<Book> bookStream = books.stream();

        System.out.print("\nAbout books:\n");
        bookStream.forEach(System.out::println);
    }

    @Test
    public void anyMatchTest(){
        Stream<Book> bookStream = books.stream();

        long start = System.nanoTime();
        boolean match = bookStream.peek(book -> {
            logger.info("Checking book with " + book.getNumberOfPages() + " pages");
        }).anyMatch(book -> book.getNumberOfPages() > 200);
        long end = System.nanoTime();

        logger.info("Time passed(ms): " + (end - start));

        System.out.print("If there is book >200 pages: ");
        System.out.print(match);
    }

    @Test
    public void MaxTest(){
        Stream<Book> bookStream = books.stream();

        long start = System.nanoTime();
        Optional<Book> maxPagesBookOptional = bookStream.max(Comparator.comparingInt(Book::getNumberOfPages));
        Book maxPagesBook = maxPagesBookOptional.orElse(new Book());
        long end = System.nanoTime();

        logger.info("Time passed(ms): " + (end - start));
        logger.info("Used Optional.orElse()");

        System.out.print("Book with maximum pages: ");
        System.out.print(maxPagesBook);
    }

    @Test
    public void MaxTestParallel(){
        Stream<Book> bookStream = books.stream();

        long start = System.nanoTime();
        Optional<Book> maxPagesBookOptional = bookStream.parallel().max(Comparator.comparingInt(Book::getNumberOfPages));
        Book maxPagesBook = maxPagesBookOptional.isPresent() ? maxPagesBookOptional.get() : new Book(); //
        long end = System.nanoTime();

        logger.info("Time passed(ms): " + (end - start) + "(with parallel())");
        logger.info("Used Optional.isPresent()");

        System.out.print("Book with maximum pages: ");
        System.out.print(maxPagesBook);
    }

    @Test
    public void MinTest(){
        Stream<Book> bookStream = books.stream();

        long start = System.nanoTime();
        Optional<Book> minPagesBookOptional = bookStream.min(Comparator.comparingInt(Book::getNumberOfPages));
        Book minPagesBook = minPagesBookOptional.orElse(new Book());
        long end = System.nanoTime();

        logger.info("Time passed(ms): " + (end - start));

        System.out.print("Book with minimal pages: ");
        System.out.print(minPagesBook);
    }

    @Test
    public void MinTestParallel(){
        Stream<Book> bookStream = books.stream();

        long start = System.nanoTime();
        Optional<Book> minPagesBookOptional = bookStream.parallel().min(Comparator.comparingInt(Book::getNumberOfPages));
        Book minPagesBook = minPagesBookOptional.isPresent() ? minPagesBookOptional.get() : new Book();;
        long end = System.nanoTime();

        logger.info("Time passed(ms): " + (end - start));

        System.out.print("Book with minimal pages: ");
        System.out.print(minPagesBook);
    }

    @Test
    public void filterTest(){
        Stream<Book> bookStream = books.stream();

        long start = System.nanoTime();
        Stream<Book> booksFilteredStream = bookStream.parallel().filter(book -> book.getAuthors().size() == 1); //вроде быстрее
        long end = System.nanoTime();

        logger.info("Time passed(ms): " + (end - start));

        System.out.print("Filtered by one author: ");
        booksFilteredStream.forEach(System.out::println);
    }

    @Test
    public void sortByPagesTest(){
        Stream<Book> bookStream = books.stream();

        long start = System.nanoTime();
        Stream<Book> sortedBooks = bookStream.sorted(Comparator.comparingInt(Book::getNumberOfPages));
        long end = System.nanoTime();

        logger.info("Time passed(ms): " + (end - start));

        System.out.print("Sorted by number of pages:\n");
        sortedBooks.forEach(System.out::println);
    }

    @Test
    public void sortByTitleTest(){
        Stream<Book> bookStream = books.stream();

        long start = System.nanoTime();
        Stream<Book> sortedBooks = bookStream.sorted((Comparator.comparing(Book::getTitle)));
        long end = System.nanoTime();

        logger.info("Time passed: " + (end - start));

        System.out.print("Sorted by title:\n");
        sortedBooks.forEach(System.out::println);
    }

    @Test
    public void getAuthorsTest(){
        Stream<Book> bookStream = books.stream();

        System.out.print("All authors from books: \n");
        List<Author> authorsList = new ArrayList<>();

        long start = System.nanoTime();
        bookStream.forEach(book -> {
            authorsList.addAll(book.getAuthors());
        });
        long end = System.nanoTime();

        logger.info("Time passed(ms): " + (end - start));

        System.out.print("All Authors: ");
        authorsList.stream().forEach(System.out::println);


        start = System.nanoTime();
        Stream<Author> distinct = authorsList.stream().distinct();
        end = System.nanoTime();

        logger.info("Time passed(ms): " + (end - start));

        System.out.print("All Authors (distinct): ");
        distinct.forEach(System.out::println);
    }
}
