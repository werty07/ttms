package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Ticket;

public class TicketSrv {
	private iTicketDAO ticketDAO=DAOFactory.creatTicketDAO();
	public int add(Ticket stu){
		return ticketDAO.insert(stu); 		
	}
	
	public int modify(Ticket stu){
		return ticketDAO.update(stu); 		
	}
	public int delete(int ID){
		return ticketDAO.delete(ID); 		
	}
	
	public List<Ticket> Fetch(int condt){
		return ticketDAO.select(condt);		
	}
	
	public List<Ticket> FetchAll(){
		return ticketDAO.select(0);	//????????????????????????????????????	
	}
	public int FetchAmount(String name) {
		return ticketDAO.amount(name);
	}
}
