package xupt.se.ttms.model;

public class Roles {
    private int id=0;
    private String name="";

    public Roles() {
        id=0;
    }

    public Roles(int id, String name) {
        this.id = id;
        this.name = name;
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
}
