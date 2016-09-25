package se.nackademin.library.rest.test;

import com.jayway.restassured.response.Response;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import se.nackademin.library.rest.test.model.Author;
import se.nackademin.library.rest.test.model.Book;

/**
 * @author Whitebeam
 */
public class TestITSuite {
    @Test
    public void testPostNewAuthor() {
        AuthorOperations ao = new AuthorOperations();
        Author author = ao.createRandomAuthor();
        Response postResponse = ao.postAuthor(author);
        assertEquals("post response should have status code 201", 201, postResponse.statusCode());

        Response getResponse = ao.getAuthorResponseById(author.getId());
        assertEquals("getting a newly posted author should render status code 200", 200, getResponse.statusCode());

        Author receivedAuthor = ao.getAuthorFromResponse(getResponse);
        assertEquals("newly posted author has wrong name", author.getName(), receivedAuthor.getName());
        assertEquals("newly posted author has wrong id", author.getId(), receivedAuthor.getId());
    }
/* 
    @Test
    public void testPostNewAuthorWithBusyID() {
    }
*/
    @Test
    public void testUpdateAuthor() {
        AuthorOperations ao = new AuthorOperations();
        BookOperations bookOperations = new BookOperations();

        Author author = ao.createRandomAuthor();
        ao.postAuthor(author);

        author.setName(bookOperations.getRandomString());
        Response putResponse = ao.putAuthor(author);
        assertEquals("put response should have status code 200", 200, putResponse.statusCode());

        Response getResponse = ao.getAuthorResponseById(author.getId());
        assertEquals("getting a newly posted author should render status code 200", 200, getResponse.statusCode());
        
        Author receivedAuthor = ao.getAuthorFromResponse(getResponse);
        assertEquals("newly updated author has wrong name", author.getName(), receivedAuthor.getName());
        assertEquals("newly apdated author has wrong id", author.getId(), receivedAuthor.getId());
    }
/*
    @Test
    public void testUpdateNonexistingAuthor() {
    }
*/
    @Test
    public void testGetAuthors() {
        AuthorOperations ao = new AuthorOperations();
      
        Response getResponse = ao.getAuthors();
        assertEquals("Get authors should render status code 200", 200, getResponse.statusCode());
        
        boolean foundAuthor = findAuthorAmongAuthors(getResponse, "Neil Gaiman");
        assertTrue("Neil Gaiman wasn't found in the database.", foundAuthor);
    }

    @Test
    public void testGetAuthor() {
        AuthorOperations ao = new AuthorOperations();
        Response response = ao.getAuthor(1);
        assertEquals("get response should have status code 200", 200, response.statusCode());
        
        Author author = ao.getAuthorFromResponse(response);
        assertEquals("Author[1] should be Margaret Atwood.", "Margaret Atwood", author.getName());
    }
    /*
    @Test
    public void testGetNonexistingAuthor() {
    }
*/
    @Test
    public void testDeleteAuthor() {
        AuthorOperations ao = new AuthorOperations();
        Author author = ao.createRandomAuthor();
        Response postResponse = ao.postAuthor(author);
        assertEquals("post response should have status code 201", 201, postResponse.statusCode());

        Response deleteResponse = ao.deleteAuthor(author.getId());
        assertEquals("delete method should return 204", 204, deleteResponse.getStatusCode());

        Response getResponse = ao.getAuthor(author.getId());
        assertEquals("Getting a removed author should render status code 404", 404, getResponse.statusCode());
    }
/*
    @Test
    public void testDeleteNonexistentAuthor() {
    }
*/
    @Test
    public void testPostBook() {
        BookOperations bo = new BookOperations();
        Book book = bo.createRandomBook();
        Response postResponse = bo.postBook(book);
        assertEquals("post response should have status code 201", 201, postResponse.statusCode());

        Response getResponse = bo.getBook(book.getId());
        assertEquals("getting a newly posted author should render status code 200", 200, getResponse.statusCode());

        Book receivedBook = bo.getBook(getResponse);
        assertEquals("newly posted book has wrong name", book.getTitle(), receivedBook.getTitle());
        assertEquals("newly posted book has wrong id", book.getId(), receivedBook.getId());
        assertEquals("newly posted book has wrong description", book.getDescription(), receivedBook.getDescription());
        assertEquals("newly posted book has wrong ISBN-number", book.getIsbn(), receivedBook.getIsbn());
       }
/*
    @Test
    public void testPostBookWithoutID() {
    }
    
    @Test
    public void testPostBookWithoutRecognisedAuthor() {
    }
*/
    @Test
    public void testPutBook() {
        BookOperations bo = new BookOperations();
        Book book = bo.createRandomBook();
        Response postResponse = bo.postBook(book);
        assertEquals("post response should have status code 201", 201, postResponse.statusCode());
        
        bo.setRandomTitle(book);
        Response putResponse = bo.putBook(book);
        assertEquals("put response should have status code 200", 200, putResponse.statusCode());
   
        Response getResponse = bo.getBook(book.getId());
        Book receivedBook = bo.getBook(getResponse);
        assertEquals("getting a newly posted book should render status code 200", 200, getResponse.statusCode());
        assertEquals("newly updated book has wrong name", book.getTitle(), receivedBook.getTitle());
        assertEquals("newly apdated book has wrong id", book.getId(), receivedBook.getId());
    }
/*
    @Test
    public void testPutBookWithoutID() {
    }
    
    @Test
    public void testPutBookWithoutRecognisedAuthor() {
    }
    
    @Test
    public void testPutNonexistentBook() {
    }
*/
    
    @Test
    public void testGetBooks() {
        BookOperations bo = new BookOperations();
        Response getResponse = bo.getAllBooks();
        assertEquals("Get books should render status code 200", 200, getResponse.statusCode());
        
        boolean foundBook = findBookAmongBooks(getResponse, "2001: A Space Odyssey");
        assertTrue("Neil Gaiman wasn't found in the database.", foundBook);
    }
    
    @Test
    public void testGetBook() {
        BookOperations bo = new BookOperations();
        Response response = bo.getBook(4);
        Book book = bo.getBook(response);
        assertEquals("get response should have status code 200", 200, response.statusCode());
        assertEquals("ISBN code for book with id=4 should be 0-00-713846-6", "0-00-713846-6", book.getIsbn());
    }

    @Test
    public void testGetNonexistentBook() {
        Response response =  new BookOperations().getBook(235987);
        assertEquals("status code should be 404", 404, response.statusCode());
    }
    
    @Test
    public void testDeleteBook() {
        BookOperations bo = new BookOperations();        
        Book book = bo.createRandomBook();
        Response postResponse = bo.postBook(book);
        assertEquals("post book response should have status code 201", 201, postResponse.statusCode());

        Response deleteResponse = bo.deleteBook(book.getId());
        assertEquals("book delete method should return 204", 204, deleteResponse.getStatusCode());

        Response getResponse = bo.getBook(book.getId());
        assertEquals("Getting a removed book should render status code 404", 404, getResponse.statusCode());
    }
/*
    @Test
    public void testDeleteNonexistantBook() {
    }
*/
    @Test
    public void testGetBookByAuthor() {
        BookOperations bo = new BookOperations();
        Response response = bo.getBooksByAuthor(1);
        assertEquals("get response should have status code 200", 200, response.statusCode());

        Book book = bo.getBookFromBookList(1, response);
        assertEquals("ISBN code for book with author id=1 should be 0-7710-0868-6", "0-7710-0868-6", book.getIsbn());
    }

    @Test
    public void testGetAuthorsToBook() {
        AuthorOperations ao = new AuthorOperations();
        int bookId = 1;
        Response response = ao.getAuthorsOfBook(bookId);
        assertEquals("get response should have status code 200", 200, response.statusCode());        
        Author author = ao.getAuthorFromAuthorList(1, response);       
        assertEquals("ID for book with id=1 should be 1 (Margaret Atwood)", "1", author.getId().toString());
    }
/*    
    @Test
    public void testGetAuthorsOfBookThatDoesn'tExists() {
    }

    @Test
    public void testAddAuthorToBook() {
        //In practice covered by testUpdateBookAuthorList()
    }
    
    @Test
    public void testAddAuthorWithoutIdToBook() {
    }    
    
    @Test
    public void testAddAlreadyAddedAuthorToBook() {
    } 
    
    @Test
    public void testAddAuthorToNonexistentBook() {
    } 
*/
    @Test
    public void testUpdateBookAuthorList() {
        BookOperations bo = new BookOperations();
        AuthorOperations ao = new AuthorOperations();

        Book book = bo.createRandomBook();
        Response postResponse = bo.postBook(book);
        assertEquals("post response should have status code 201", 201, postResponse.statusCode());
              
        Author author = ao.createRandomAuthor();
        ao.postAuthor(author);

        Response postAuthorIntoBookResponse = bo.addAuthorToBook(book.getId(), author);
        assertEquals("post response should have status code 200", 200, postAuthorIntoBookResponse.statusCode());

        Response getResponse = bo.getBook(book.getId());
        assertEquals("getting a newly posted book should render status code 200", 200, getResponse.statusCode());

        Book receivedBook = bo.getBook(getResponse);
        assertEquals("newly updated book has wrong title", book.getTitle(), receivedBook.getTitle());
        assertEquals("newly updated book has wrong id", book.getId(), receivedBook.getId());
    }
/*
    @Test
    public void testUpdateBookAuthorListWithoutID() {
    }

    @Test
    public void testUpdateBookAuthorListWithAlreadyExistingAuthors() {
    }

    @Test
    public void testUpdateNonexistentBookAuthorList() {
    }
*/   
    private boolean findAuthorAmongAuthors(Response authorsResponse, String soughtAuthorName) {
        AuthorOperations ao = new AuthorOperations();
        String currentName = "";
        Author currentAuthor;
        int i = 0;
        while (!soughtAuthorName.equals(currentName)) {
            currentAuthor = ao.getAuthorFromAuthorList(i, authorsResponse);
            assertNotNull("Didn't find expected author " + soughtAuthorName, currentAuthor);
            currentName = currentAuthor.getName();
            i++;
            if (currentName.equals(soughtAuthorName)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean findBookAmongBooks(Response response, String soughtTitle) {
        BookOperations ao = new BookOperations();
        String currentName = "";
        Book currentBook;
        int i = 0;
        while (!soughtTitle.equals(currentName)) {
            currentBook = ao.getBookFromBookList(i, response);
            assertNotNull("Didn't find expected author " + soughtTitle, currentBook);
            currentName = currentBook.getTitle();
            i++;
            if (currentName.equals(soughtTitle)) {
                return true;
            }
        }
        return false;
    }
}
