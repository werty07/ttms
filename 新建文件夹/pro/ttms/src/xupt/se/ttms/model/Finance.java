package xupt.se.ttms.model;

public class Finance {
	private int sale_id=0;
	private int emp_id=0; 
	private String sale_time;
	private String sale_payment;
	private String sale_change;
	private int sale_type=0;
	private int sale_status=0;
	private String play_name;
	 //���
	
	public Finance(){
		sale_id=0;
		emp_id=0; 
		sale_payment="";
		sale_change="";
		sale_type=0;
		sale_status=0;
		play_name="";
	}
	public Finance(int sale_id) {
		this.sale_id=sale_id;
	}
	public Finance(int sale_id,int emp_id,String sale_time,String sale_payment,String sale_change,int sale_type,int sale_status,String play_name) {
		this.sale_id=sale_id;
		this.emp_id=emp_id;
		this.sale_time=String.valueOf(sale_time);
		this.sale_payment=sale_payment;
		this.sale_change=sale_change;
		this.sale_type=sale_type;
		this.sale_status=sale_status;
		this.play_name=play_name;
	}
	public void setsale_id(int ID){
		this.sale_id=ID;
	}
	
	public int getsale_id(){
		return sale_id;
	}
	
	public void setemp_id(int emp_id){
		this.emp_id=emp_id;
	}
	
	public int getemp_id(){
		return emp_id;
	}
	
	public void setsale_time(String sale_time){
		this.sale_time=sale_time;
	}
	
	public String getsale_time(){
		return sale_time;
	}
	public void setsale_payment(String sale_payment){
		this.sale_payment=sale_payment;
	}
	
	public String getsale_payment(){
		return sale_payment;
	}
	
	public void setsale_type(int sale_type){
		this.sale_type=sale_type;
	}
	
	public int getsale_type(){
		return sale_type;
	}	
	public void setsale_status(int sale_status) {
		this.sale_status=sale_status;
	}
	public int getsale_status() {
		return sale_status;
	}
	public String getsale_change() {
		return sale_change;
	}
	public void setsale_change(String sale_change) {
		this.sale_change=sale_change;
	}
	public void setplay_name(String play_name) {
		this.play_name=play_name;
	}
	public  String getplay_name() {
		return play_name;
	}

}