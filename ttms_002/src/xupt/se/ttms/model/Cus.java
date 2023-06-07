package xupt.se.ttms.model;

public class Cus {
    private int id=0;
    private String name="";
    private int gender=0;
    private String telnum="";
    private String email="";
    private String uid="";
    private String pwd="";
    private int balance=0;
    private String paypwd="";

    public Cus() {
        id=0;
    }

    public Cus(int id, String name, int gender, String telnum, String email, String uid, String pwd, int balance, String paypwd) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.telnum = telnum;
        this.email = email;
        this.uid = uid;
        this.pwd = pwd;
        this.balance = balance;
        this.paypwd = paypwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPaypwd() {
        return paypwd;
    }

    public void setPaypwd(String paypwd) {
        this.paypwd = paypwd;
    }
}
