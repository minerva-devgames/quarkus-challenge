package org.acme.getting.started;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(REQUIRED)
public class BookService {

    @Transactional(SUPPORTS)
    public List<Book> findAllBooks() {
        return Book.listAll();

    }

    @Transactional(SUPPORTS)
    public Book findBookById(Long id) {
        return Book.findById(id);
    }

        @Transactional(SUPPORTS)
    public Book findBookByName(String name) {
        return Book.findByName(name);
    }

    @Transactional(SUPPORTS)
    public List<Book> findBookByPublicationYear(Integer lowerYear,Integer higherYear) {
        return Book.find("select b from Book b where publicationYear >= ?1 and publicationYear <= ?2 ", lowerYear,higherYear).list();
    }

    public void deleteBook(Long id) {
        Book book = Book.findById(id);
        book.delete();
    }

    public Book persistBook(@Valid Book book) {

        Book.persist(book);
        return book;
    }

    public Book updateBook(@Valid Book book) {
        Book entity = Book.findById(book.id);
        entity.name = book.name;
        entity.publicationYear = book.publicationYear;
        return entity;
    }


}
