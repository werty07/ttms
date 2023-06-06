package xupt.se.ttms.model;


public class Play {
	private int PlayId=0;
	private String DictTypeId="";
	private String DictLangId="";
	private String PlayName="" ;
	private String PlayIntroduction="";
	private String PlayImage="";
	private int PlayLength=0;
	private String PlayTicketPrice="";
//	private int PlayStatus=0;
	private String PlayStatus="";
	 //
	public Play(){
		PlayId = 0;
	}
	public Play(int PlayId, String DictTypeId, String DictLangId,String PlayName, String PlayIntroduction,int PlayLength,String PlayTicketPrice,String PlayStatus,String PlayImage){
		this.PlayId = PlayId;
		this.DictTypeId=DictTypeId;
		this.DictLangId=DictLangId;
		this.PlayName=PlayName;
		this.PlayIntroduction = PlayIntroduction;
		this.PlayLength=PlayLength;
		this.PlayTicketPrice=PlayTicketPrice;
		this.PlayStatus=PlayStatus;
		this.PlayImage=PlayImage;
	}

	public int getPlayId() {
		return PlayId;
	}
	public void setPlayId(int PlayId) {
		this.PlayId = PlayId;
	}
	public void setPlayName(String PlayName){
		this.PlayName=PlayName;
	}
	
	public String getPlayName(){
		return PlayName;
	}
	
	public void setDictTypeId(String DictTypeId){
		this.DictTypeId=DictTypeId;
	}
	
	public String getDictTypeId(){
		return DictTypeId;
	}
	public void setDictLangId(String DictLangId){
		this.DictLangId=DictLangId;
	}
	
	public String getDictLangId(){
		return DictLangId;
	}
	
	public void setPlayIntroduction(String intro){
		this.PlayIntroduction=intro;
	}
	
	public String getPlayIntroduction(){
		return PlayIntroduction;
	}	
	public void setPlayLength(int PlayLength) {
		this.PlayLength=PlayLength;
	}
	public int getPlayLength() {
		return PlayLength;
	}
	public void setPlayTicketPrice(String PlayTicketPrice) {
		this.PlayTicketPrice=PlayTicketPrice;
	}
	public String getPlayTicketPrice() {
		return PlayTicketPrice;
	}
	public void setPlayStatus(String PlayStatus) {
		this.PlayStatus=PlayStatus;
	}
	public String getPlayStatus() {
		return PlayStatus;
	}
	public void setPlayImage(String PlayImage) {
		this.PlayImage=PlayImage;
	}
	public String getPlayImage() {
		return PlayImage;
	}
}