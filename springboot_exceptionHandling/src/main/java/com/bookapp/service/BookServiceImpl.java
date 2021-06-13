package com.bookapp.service;

import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.IdNotFoundException;
import com.bookapp.models.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*Adding all the custom exceptions in this class, however,
there will be other scenarios where the client will throw exceptions like
incorrect httpMethod (POST instead of GET), missing body part for RequestBody, giving wrong pathVariable name,
passing String value instead of numerical value in PathVariable, missing value in RequestParam etc.
So in order to handle all these exceptions, we have created a separate class called GlobalExceptionHandler */

@Service
public class BookServiceImpl implements BookService {
    @Override
    public void addBook(Book book) {
        System.out.println(book);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        /* without exception handling
        return getBooksList()
                .stream()
                .filter((book)->book.getAuthor().equals(author))
                .collect(Collectors.toList());*/

        //handling exception
        List<Book> bookList = getBooksList()
                .stream()
                .filter((book) -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
        if (bookList.isEmpty()) {
            throw new BookNotFoundException("Book with this author not found");
        }
        return bookList;

    }

    @Override
    public List<Book> getBooksByCategory(String category) {
        /* without exception handling
        return getBooksList()
                .stream()
                .filter((book)->book.getCategory().equals(category))
                .collect(Collectors.toList());*/

        //handling exception
        List<Book> bookList = getBooksList()
                .stream()
                .filter((book) -> book.getCategory().equals(category))
                .collect(Collectors.toList());

        if (bookList.isEmpty()) {
            throw new BookNotFoundException("Book with this category not found");
        }
        return bookList;

    }

    @Override
    public Book getBookById(int bookId) {
        /* without exception handling
        return getBooksList().stream()
                .filter((book)->book.getBookId()==bookId)
                .findAny()
                .orElse(new Book()); */

        //Just to get Other exception apart from the handled ones
        if(bookId<=0){
            throw new RuntimeException("other type of exception");
        }

        //handling exception
        //Optional<Book> opt  = getBooksList().stream()
        return getBooksList().stream()
                .filter((book) -> book.getBookId() == bookId)
                .findAny()
                .orElseThrow(() -> new IdNotFoundException("Invalid ID"));
        /*if(opt.isPresent()){
            return opt.get();
        }
        else{
            throw new IdNotFoundException("Invalid ID");
        }*/


    }

    //Hardcoding values instead of taking it from DB
    private List<Book> getBooksList() {
        return Arrays.asList(new Book("Java", "Kathy", "Tech", 10),
                new Book("Spring", "Rod", "Tech", 11),
                new Book("Miracle", "Robin", "Fiction", 12),
                new Book("Ferrari", "Robin", "Fiction", 13),
                new Book("Captain", "Hal", "Comic", 14),
                new Book("Morning Misty", "Hal", "Horror", 15)

        );
    }
}
