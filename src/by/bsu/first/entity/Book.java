package by.bsu.first.entity;

public class Book extends Entity {

    private String name;
    private String author;
    private String genre;
    private int amount;
    private String information;

    public Book() {
    }

    public Book(int id, String name, String author, String genreID, int amount, String information) {

        super(id);
        this.name = name;
        this.author = author;
        this.genre = genreID;
        this.amount = amount;
        this.information = information;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenreID() {
        return genre;
    }

    public void setGenreID(String genre) {
        this.genre = genre;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }


}
