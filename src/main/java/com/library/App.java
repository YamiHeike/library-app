package com.library;

import com.library.repository.Book;
import com.library.repository.BookDao;
import com.library.repository.Dao;

import java.util.List;
import java.util.Optional;

public class App {
    public static void main( String[] args ) {
        Dao<Book> bookDao = new BookDao();
        //Retrieving all books

        List<Book> books = bookDao.findAll();
        books.forEach(System.out::println);

        //Retrieving a book by id

        Optional<Book> bookPlaceholder = bookDao.findById(1);
        //Accessing the book
        //Optionals
        bookPlaceholder.ifPresent(System.out::println);
        if(bookPlaceholder.isPresent()) {
            Book book = bookPlaceholder.get();
            System.out.println(book);
        }

        //Adding a new book to the repository

        //Book newBook = new Book();
        //newBook.setTitle("Cultish. The Language of Fanaticism");
        //newBook.setAuthor("Amanda Montell");
        //newBook = bookDao.create(newBook);
        //System.out.println(newBook);
        books.get(0).setTitle("Effective Java: Second Edition");
        bookDao.update(books.get(0));
        books.forEach(System.out::println);
    }
}
