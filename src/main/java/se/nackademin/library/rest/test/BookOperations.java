package se.nackademin.library.rest.test;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.Random;
import java.util.UUID;
import se.nackademin.library.rest.test.model.Author;
import se.nackademin.library.rest.test.model.Book;
import se.nackademin.library.rest.test.model.SingleAuthor;
import se.nackademin.library.rest.test.model.SingleBook;

/**
 *
 * @author Whitebeam
 */
public class BookOperations {
    protected static final String BASE_URL = "http://localhost:8080/librarytest/rest/";

    public Response getAllBooks() {
        return given().accept(ContentType.JSON).get(BASE_URL + "books/").prettyPeek();
    }
    
    public Response getBooksByAuthor(int number) {
        return given().accept(ContentType.JSON).get(BASE_URL + "books/byauthor/" + number);
    }
    
    protected Response addAuthorToBook(int bookId, Author author) {
        return given().contentType(ContentType.JSON).body(new SingleAuthor(author)).log().all().post(BASE_URL + "books/" + bookId + "/authors").prettyPeek();
    }
        
    public Response deleteBook(int id) {
        return delete(BASE_URL + "books/"+id);
    }
    
    protected Response getBook(int number) {
        return given().accept(ContentType.JSON).get(BASE_URL + "books/" + number);
    }

    protected Response postBook(Book author) {
        return given().contentType(ContentType.JSON).body(new SingleBook(author)).log().all().post(BASE_URL + "books").prettyPeek();
    }

    protected Response putBook(Book author) {
        return given().contentType(ContentType.JSON).body(new SingleBook(author)).log().all().put(BASE_URL + "books").prettyPeek();
    }

    protected Book getBook(Response response) {
        return response.jsonPath().getObject("book", Book.class);
    }
    
    protected Book getBookFromBookList(int i, Response response) {
        if (response.jsonPath().getObject("books.book[" + i + "]", Book.class) == null) {
            return response.jsonPath().getObject("books.book", Book.class);
        }
        return response.jsonPath().getObject("books.book[" + i + "]", Book.class);
    }
    
    protected Book createRandomBook(){
        Book book = new Book();
        setRandomTitle(book);
        setUniqueAuthorId(book);
        setRandomDescription(book);
        setRandomIsbn(book);
        return book;
    }

    protected void setRandomTitle(Book book){
        book.setTitle(getRandomString());
    }

    protected void setUniqueAuthorId(Book book) {
        Integer id = getRandomId();
        Response response = getBook(id);
        while(response.statusCode() != 404) {
            id = getRandomId();
            response = getBook(id);
        }
        book.setId(id);
    }

    protected void setRandomDescription(Book book) {
        book.setDescription(getRandomString());
    }

    protected void setRandomIsbn(Book book) {
        book.setIsbn(getRandomString());
    }

    public String getRandomString() {
        return UUID.randomUUID().toString();
    }

    public Integer getRandomPageNumber() {
        return new Random().nextInt(1100) + 150;
    }
    
    public Integer getRandomId() {
        return new Random().nextInt(1000) + 200;
    }
}
