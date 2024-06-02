package org.ej31.exam.books;

import java.util.List;

public interface BookRepository {
    void addBook(Book book);

    Book getBookById(String id);

    List<Book> getAllBooks();

    void deleteBook(String id);
}
