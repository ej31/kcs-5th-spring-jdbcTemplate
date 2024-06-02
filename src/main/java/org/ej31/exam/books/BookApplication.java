package org.ej31.exam.books;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BookApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BookManagementConfig.class);
        BookService bookService = context.getBean(BookService.class);

        Book book1 = new Book("1", "Spring in Action", "크레이크 월스");
        Book book2 = new Book("2", "Effective Java", "조슈아");

        bookService.addBook(book1);
        bookService.addBook(book2);

        System.out.println("모든 책 리스트: ");
        for (Book book : bookService.getAllBooks()) {
            System.out.println(book);
        }

        System.out.println("책 정보 가져오기. ID 1:");
        System.out.println(bookService.getBookById("1"));

        bookService.deleteBook("1");

        System.out.println("삭제 후 모든 책:");
        for (Book book : bookService.getAllBooks()) {
            System.out.println(book);
        }
    }
}
