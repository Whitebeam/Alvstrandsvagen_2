package se.nackademin.library.rest.test.model;

/**
 *
 * @author Whitebeam
 */
public class SingleAuthor {
    private Author author;

    public SingleAuthor(Author author) {
        this.author = author;
    }
    
    /**
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }
}
