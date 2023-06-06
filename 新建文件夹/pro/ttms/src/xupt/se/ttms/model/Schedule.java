package xupt.se.ttms.model;
//import java.util.Date;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class Schedule {
	private int SchedID=0; 
	private int StudioID=0 ;
	private int PlayID=0;
	private String SchedTime="";
	private String SchedTicketPrice="";
	private String SchedStatus="";//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	public Schedule(){
		SchedID = 0;
	}
	public Schedule(int SchedID, int StudioID, int PlayID, String SchedTime,String SchedTicketPrice,String SchedStatus){
		this.SchedID=SchedID; 
		this.StudioID=StudioID;
		this.PlayID=PlayID;
		this.SchedTime=SchedTime;
		this.SchedTicketPrice=SchedTicketPrice;
		this.SchedStatus=SchedStatus;	
	}

	public int getSchedID() {
		return SchedID;
	}
	public void setSchedID(int SchedID) {
		this.SchedID = SchedID;
	}
//	public void setID(int ID){
//		this.id=ID;
//	}
//	
//	public int getID(){
//		return id;
//	}
//	
	public void setStudioID(int StduioID){
		this.StudioID=StduioID;
	}
	
	public int getStudioID(){
		return StudioID;
	}
	
	public void setPlayID(int PlayID){
		this.PlayID=PlayID;
	}
	
	public int getPlayID(){
		return PlayID;
	}
	public void setSchedTime(String SchedTime){
		this.SchedTime=SchedTime;
	}
	
	public String getSchedTime(){
		return SchedTime;
	}
	
	public void setSchedTicketPrice(String SchedTicketPrice){
		this.SchedTicketPrice=SchedTicketPrice;
	}
	
	public String getSchedTicketPrice(){
		return SchedTicketPrice;
	}	
	public void setSchedStatus(String SchedStatus) {
		this.SchedStatus=SchedStatus;
	}
	public String getSchedStatus() {
		return SchedStatus;
	}
}
