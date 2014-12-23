package by.bsu.first.entity;


public class Order extends Entity {

    private int idorder;
    private int idbook;
    private int idcard;

    private int status;

    public Order() {
    }


    public int getIdOrder() {
        return idorder;
    }

    public void setIdOrder(int idissue) {
        this.idorder = idorder;
    }

    public int getIdcard() {
        return idcard;
    }

    public void setIdcard(int idcard) {
        this.idcard = idcard;
    }

    public int getIdbook() {
        return idbook;
    }

    public void setIdbook(int idcard) {
        this.idbook = idbook;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}