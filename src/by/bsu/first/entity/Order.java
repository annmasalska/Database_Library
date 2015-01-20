package by.bsu.first.entity;


public class Order extends Entity {

    private String username;
    private int idbook;
    private int idorder;
    private int status;
    private String name;
    private String author;
    private String information;
    private String date;
    private String genre;
    public Order() {
    }
    public int getIdorder() {
        return idorder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdbook() {
        return idbook;
    }

    public void setIdbook(int idbook) {
        this.idbook = idbook;
    }

    public int getStatus() {
        return status;
    }
    public String getGenreID() {
        return genre;
    }

    public void setGenreID(String genre) {
        this.genre = genre;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

}
