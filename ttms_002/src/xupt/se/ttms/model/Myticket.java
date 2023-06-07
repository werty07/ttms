package xupt.se.ttms.model;

public class Myticket {
    private int id=0;
    private int sched_id=0;
    private int row=0;
    private int col=0;
    private int price=0;
    private int emp=0;
    private int cus=0;

    public Myticket() {
        id=0;
    }

    public Myticket(int id, int sched_id, int row, int col, int price, int emp, int cus) {
        this.id = id;
        this.sched_id = sched_id;
        this.row = row;
        this.col = col;
        this.price = price;
        this.emp = emp;
        this.cus = cus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSched_id() {
        return sched_id;
    }

    public void setSched_id(int sched_id) {
        this.sched_id = sched_id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getEmp() {
        return emp;
    }

    public void setEmp(int emp) {
        this.emp = emp;
    }

    public int getCus() {
        return cus;
    }

    public void setCus(int cus) {
        this.cus = cus;
    }
}
