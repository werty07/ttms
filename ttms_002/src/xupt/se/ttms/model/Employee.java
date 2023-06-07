package xupt.se.ttms.model;

public class Employee {
    private int id=0;
    private int dict_id=0;
    private String no="";
    private String name="";
    private int gender=0;
    private String telnum="";
    private String email="";
    private String pwd="";

    public Employee() {
        id=0;
    }

    public Employee(int id, int dict_id, String no, String name, int gender, String telnum, String email, String pwd) {
        this.id = id;
        this.dict_id = dict_id;
        this.no = no;
        this.name = name;
        this.gender = gender;
        this.telnum = telnum;
        this.email = email;
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDict_id() {
        return dict_id;
    }

    public void setDict_id(int dict_id) {
        this.dict_id = dict_id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
