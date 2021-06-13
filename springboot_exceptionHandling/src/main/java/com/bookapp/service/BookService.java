package com.bookapp.service;

import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.IdNotFoundException;
import com.bookapp.models.Book;

import java.util.List;

public interface BookService {

    void addBook(Book book);
    List<Book> getBooksByAuthor(String author) throws BookNotFoundException;
    List<Book> getBooksByCategory(String category) throws BookNotFoundException;
    Book getBookById(int bookId) throws IdNotFoundException;
}
