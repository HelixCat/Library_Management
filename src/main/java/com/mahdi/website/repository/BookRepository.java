package com.mahdi.website.repository;

import com.mahdi.website.model.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Query("SELECT book FROM Book book WHERE book.title = :title")
    Optional<Book> findBookByBookTitle(@Param("title") String bookTitle);

    @Query("SELECT book FROM Book book WHERE book.bookId = :bookId")
    Optional<Book> findBookByBookId(@Param("bookId") String bookId);

    @Query("SELECT book FROM Book book WHERE book.publishYear = :publishYear")
    Optional<Book> findBookByPublishYear(@Param("publishYear") String publishYear);

    @Query("SELECT book FROM Book book WHERE book.publishDate = :publishDate")
    Optional<Book> findByBookByPublishDate(@Param("publishDate") String publishDate);

    @Query("SELECT book FROM Book book JOIN Author author WHERE author.firstName = :authorFirstName")
    Optional<Book> findByBookByAuthorFirstName(@Param("authorFirstName") String authorFirstName);

    @Query("SELECT book FROM Book book JOIN Author author WHERE author.lastName = :authorLastName")
    Optional<Book> findByBookByAuthorLastName(@Param("authorLastName") String authorLastName);

    @Query("SELECT book FROM Book book JOIN Translator translator WHERE translator.firstName = :translatorFirstName")
    Optional<Book> findByBookByTranslatorFirstName(@Param("translatorFirstName") String translatorFirstName);

    @Query("SELECT book FROM Book book JOIN Translator translator WHERE translator.lastName = :translatorLastName")
    Optional<Book> findByBookByTranslatorLastName(@Param("translatorLastName") String translatorLastName);
}
