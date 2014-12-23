package by.bsu.first.entity;

public class BorrowInfo extends Entity {

    private int idissue;
    private String name;
    private String author;
    private String information;
    private int idcard;
    private String date;
    private int status;

    public BorrowInfo() {
    }


    public int getIdissue() {
        return idissue;
    }

    public void setIdissue(int idissue) {
        this.idissue = idissue;
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



    public int getIdcard() {
        return idcard;
    }

    public void setIdcard(int idcard) {
        this.idcard = idcard;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}