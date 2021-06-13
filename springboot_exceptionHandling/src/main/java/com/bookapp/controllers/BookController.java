package com.bookapp.controllers;

import com.bookapp.models.Book;
import com.bookapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book-restapi")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/greet")
    public ResponseEntity<String> sayHello(){
        String msg = "Welcome to Book App";
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc","Online Book Application");
        return new ResponseEntity<String>  (msg,headers, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Void> addBook(@RequestBody Book book){
        bookService.addBook(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", "Adding one book");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int bookId){
        Book book = bookService.getBookById(bookId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", "Getting book by id");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(book);
    }

    @GetMapping("/books-by-author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author){
        List<Book> bookList = bookService.getBooksByAuthor(author);
        return ResponseEntity.ok(bookList);

    }


    @GetMapping("/books-by-category")
    public ResponseEntity<List<Book>> getBookByCategory(@RequestParam("category") String category){
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", "Geting list of books by category");
        headers.add("type", "book Object");
        List<Book> bookList = bookService.getBooksByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bookList);

    }
}
