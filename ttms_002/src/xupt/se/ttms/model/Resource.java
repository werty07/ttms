package xupt.se.ttms.model;

public class Resource {
    private int id=0;
    private String parent="";
    private String name="";
    private String URL="";

    public Resource() {
        id=0;
    }

    public Resource(int id, String parent, String name, String URL) {
        this.id = id;
        this.parent = parent;
        this.name = name;
        this.URL = URL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
