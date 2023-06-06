package xupt.se.ttms.model;


public class Seat {
	private int SeatId=0      ; 
	private int StudioId=0 ;
	private int SeatRow=0;
	private int SeatColumn=0;
	private String SeatStatus=""; //���
	
	public Seat(){
		SeatId = 0;
	}
	public Seat(int SeatId, int StudioId, int SeatRow, int SeatColumn, String SeatStatus){
		this.SeatId= SeatId;
		this.StudioId=StudioId;
		this.SeatRow = SeatRow;
		this.SeatColumn = SeatColumn;
		this.SeatStatus= SeatStatus;		
	}

	public int getSeatId() {
		return SeatId;
	}
	public void setSeatId(int SeatId) {
		this.SeatId = SeatId;
	}
	public void setStudioId(int StudioId){
		this.StudioId=StudioId;
	}
	
	public int getStudioId(){
		return StudioId;
	}
	
	public void setSeatRow(int SeatRow){
		this.SeatRow=SeatRow;
	}
	
	public int getSeatRow(){
		return SeatRow;
	}
	
	public void setSeatColumn(int SeatColumn){
		this.SeatColumn=SeatColumn;
	}
	
	public int getSeatColumn(){
		return SeatColumn;
	}
	public void setSeatStatus(String SeatStatus){
		this.SeatStatus=SeatStatus;
	}
	
	public String getSeatStatus(){
		return SeatStatus;
	}
}
	
