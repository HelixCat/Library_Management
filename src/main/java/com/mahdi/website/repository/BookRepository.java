package com.mahdi.website.repository;

import com.mahdi.website.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    Optional<Book> findBooksByTitle(@Param("title") String bookTitle);

    Optional<Book> findBooksByBookId(@Param("bookId") String bookId);

    Optional<Book> findBooksByPublishYear(@Param("publishYear") String publishYear);

    Optional<Book> findBooksByPublishDate(@Param("publishDate") String publishDate);

    @Query("SELECT book FROM Book book JOIN Author author WHERE author.firstName = :authorFirstName")
    Optional<Book> findBookByAuthorFirstName(@Param("authorFirstName") String authorFirstName);

    @Query("SELECT book FROM Book book JOIN Author author WHERE author.lastName = :authorLastName")
    Optional<Book> findBookByAuthorLastName(@Param("authorLastName") String authorLastName);

    @Query("SELECT book FROM Book book JOIN Translator translator WHERE translator.firstName = :translatorFirstName")
    Optional<Book> findBookByTranslatorFirstName(@Param("translatorFirstName") String translatorFirstName);

    @Query("SELECT book FROM Book book JOIN Translator translator WHERE translator.lastName = :translatorLastName")
    Optional<Book> findBookByTranslatorLastName(@Param("translatorLastName") String translatorLastName);
}
