package xupt.se.ttms.model;
import java.util.Date;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class Ticket {
	private int TicketID=0; 
	private int SeatID=0 ;
	private int SchedID=0;
	private String TicketPrice="";
	private String TicketStatus="";
	private String TicketLocktime;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	public Ticket(){
		TicketID = 0;
	}
	public Ticket(int TicketID, int SeatID, int SchedID, String TicketPrice,String TicketStatus,String TicketLocktime){
		this.TicketID=TicketID; 
		this.SeatID=SeatID;
		this.SchedID=SchedID;
		this.TicketPrice=TicketPrice;
		this.TicketStatus=TicketStatus;
		this.TicketLocktime=TicketLocktime;	
	}

	public int getTicketID() {
		return TicketID;
	}
	public void setTicketID(int TicketID) {
		this.TicketID = TicketID;
	}
//	public void setID(int ID){
//		this.id=ID;
//	}
//	
//	public int getID(){
//		return id;
//	}
//	
	public void setSeatID(int SeatID){
		this.SeatID=SeatID;
	}
	
	public int getSeatID(){
		return SeatID;
	}
	
	public void setSchedID(int SchedID){
		this.SchedID=SchedID;
	}
	
	public int getSchedID(){
		return SchedID;
	}
	public void setTicketPrice(String TicketPrice){
		this.TicketPrice=TicketPrice;
	}
	
	public String getTicketPrice(){
		return TicketPrice;
	}
	
	public void setTicketStatus(String TicketStatus){
		this.TicketStatus=TicketStatus;
	}
	
	public String getTicketStatus(){
		return TicketStatus;
	}	
	public void setTicketLocktime(String TicketLocktime) {
		this.TicketLocktime=TicketLocktime;
	}
	public String getTicketLocktime() {
		return TicketLocktime;
	}
}
