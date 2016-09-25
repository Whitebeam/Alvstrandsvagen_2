package se.nackademin.library.rest.test.model;

/**
 *
 * @author Whitebeam
 */
public class Book {
    private Integer id;
    private String description;
    private Integer nbOfPage;
    private String title;
    private String isbn;
    private Object author;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the nbOfPage
     */
    public Integer getNbOfPage() {
        return nbOfPage;
    }

    /**
     * @param nbOfPage the nbOfPage to set
     */
    public void setNbOfPage(int nbOfPage) {
        this.nbOfPage = nbOfPage;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the author
     */
    public Object getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Object author) {
        this.author = author;
    }
}
