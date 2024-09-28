package com.library.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookDao extends AbstractDao implements Dao<Book>{

    @Override
    public Optional<Book> findById(long id) {
        Optional<Book> book = Optional.empty();
        String sql = "SELECT id, title, author FROM book WHERE id = ?";
        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ) {
            statement.setLong(1, id);
            try(ResultSet rst = statement.executeQuery()) {
                Book foundBook = new Book();
                if (rst.next()) {
                    foundBook.setId(id);
                    foundBook.setTitle(rst.getString("title"));
                    foundBook.setAuthor(rst.getString("author"));
                }
                book = Optional.of(foundBook);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = Collections.emptyList();
        String sql = "SELECT * FROM book";
        try(
                Connection con = getConnection();
                Statement stmt = con.createStatement();
                ResultSet rst = stmt.executeQuery(sql);
                ) {
            books = new ArrayList<Book>();

            while(rst.next()) {
                Book book = new Book();
                book.setId(rst.getLong("id"));
                book.setTitle(rst.getString("title"));
                book.setAuthor(rst.getString("author"));
                books.add(book);
            }
            return books;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book create(Book book) {
        String sql = "INSERT INTO book (title, author) VALUES (?, ?)";
        try(
                Connection con = getConnection();
                PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.executeUpdate();
            try(ResultSet rst = statement.getGeneratedKeys()) {
                if(rst.next()) {
                    book.setId(rst.getLong(1));
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Book update(Book book) {
        String sql = "UPDATE book SET title = ?, author = ? WHERE id = ?";
        try(
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(sql);
                ) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setLong(3, book.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void delete(Book book) {

    }
}
