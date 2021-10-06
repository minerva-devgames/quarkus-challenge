package org.acme.getting.started;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import org.jboss.logging.Logger;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;




@Path("/books")
public class BookResource {

    private static final Logger LOGGER = Logger.getLogger(BookResource.class);

    @Inject
    BookService service;


    
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/all")
    public List<Book>  getAllBook() {
        List<Book> books = service.findAllBooks();
        return books;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/byName/{name}")
    public Book getBookByName(@PathParam("name") String name) {
        Book book = service.findBookByName(name);
        if (book != null) {
            LOGGER.debug("Found book " + book);
            return book;
        } else {
            LOGGER.debug("No book found with name " + name);
            return book;
        }
    }
  
   @GET
   @Produces(MediaType.APPLICATION_JSON)
    @Path("byPublicationYearBetween/{lowerYear}/{higherYear}")
    public List<Book>  getBookByPublicationYearBetween(@PathParam("lowerYear") Integer ly,@PathParam("higherYear") Integer hy) {
        List<Book> books = service.findBookByPublicationYear(ly,hy);
        return books;
    }



    //Optional endpoints
    @POST
    @Transactional
    public String create(Book book) {
        service.persistBook(book);
        return "Book Created";
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Book update(@PathParam("id") Long id, Book book) {
       book = service.updateBook(book);
       return book;
    }


    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        service.deleteBook(id);
    }

    
    
    @GET
     @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHelloBook() {
     
        return "Hello RESTEasy";
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Book get(@PathParam("id") Long id) {
        return Book.findById(id);
    }


}