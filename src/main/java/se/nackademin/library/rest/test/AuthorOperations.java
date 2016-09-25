package se.nackademin.library.rest.test;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import se.nackademin.library.rest.test.model.Author;
import se.nackademin.library.rest.test.model.SingleAuthor;

/**
 *
 * @author Whitebeam
 */
public class AuthorOperations {
    private static final String BASE_URL = BookOperations.BASE_URL;
    
    protected Author createRandomAuthor() {
        BookOperations bookOperations = new BookOperations();
        Author author = new Author();
        author.setName(bookOperations.getRandomString());
        setUniqueAuthorId(author);
        return author;
    }
    
    protected void setUniqueAuthorId(Author author) {
        BookOperations bookOperations = new BookOperations();
        Integer id = bookOperations.getRandomId();
        Response response = getAuthor(id);
        while(response.statusCode() != 404) {
            id = bookOperations.getRandomId();
            response = getAuthor(id);
        }
        author.setId(id);
    }
            
    protected Response getAuthors() {
        return given().accept(ContentType.JSON).get(BASE_URL + "authors/");
    }

    protected Response getAuthorsOfBook(int id) {
        return given().accept(ContentType.JSON).get(BASE_URL + "books/" + id + "/authors");
    }
    
    protected Response deleteAuthor(int i) {
        return given().contentType(ContentType.JSON).log().all().delete(BASE_URL + "authors/" + i).prettyPeek();
    }
    
    protected Response getAuthor(int i) {
        return given().accept(ContentType.JSON).get(BASE_URL + "authors/" + i);
    }

    protected Response putAuthor(Author author) {
        SingleAuthor singleAuthor = new SingleAuthor(author);
        return given().contentType(ContentType.JSON).body(singleAuthor).log().all().put(BASE_URL + "authors").prettyPeek();
    }
    
    protected Response postAuthor(Author author) {
        SingleAuthor singleAuthor = new SingleAuthor(author);
        return given().contentType(ContentType.JSON).body(singleAuthor).log().all().post(BASE_URL + "authors").prettyPeek();
    }
    
    protected Response getAuthorResponseById(Integer id) {
        return given().accept(ContentType.JSON).get(BASE_URL + "authors/" + id);
    }
    
    protected Author getAuthorFromResponse(Response response) {
        return response.jsonPath().getObject("author", Author.class);
    }
    
    protected Author getAuthorFromAuthorList(int i, Response response) {
        if (response.jsonPath().getObject("authors.author[" + i + "]", Author.class) == null) {
            return response.jsonPath().getObject("authors.author", Author.class);
        }
        return response.jsonPath().getObject("authors.author[" + i + "]", Author.class);
    }
}
