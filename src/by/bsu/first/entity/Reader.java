package by.bsu.first.entity;

public class Reader extends Entity {

    private int idcard;
    private String lastname;
    private String name;
    private String secondname;
    private String address;
    private String phone;


    public Reader() {
    }


    public int getIdcard() {
        return idcard;
    }

    public void setIdcard(int idcard) {
        this.idcard = idcard;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}