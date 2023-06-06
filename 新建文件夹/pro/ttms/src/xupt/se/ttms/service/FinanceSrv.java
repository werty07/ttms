package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iFinanceDAO;
import xupt.se.ttms.model.Finance;

public class FinanceSrv {
	private iFinanceDAO finDAO=DAOFactory.creatFinanceDAO();
	
	public int add(Finance fin){
		return finDAO.insert(fin); 		
	}
	
	public int modify(Finance Sale_id){
		return finDAO.update(Sale_id); 		
	}
	
	public List<Finance> Fetch(int emp_id){
		return finDAO.select(emp_id);		
	}
	
	public List<Finance> FetchAll(){
		return finDAO.select(0);		
	}
	public int FetchAmount(String name) {
		return finDAO.amount(name);
	}
	public int FetchRefund(String name) {
		return finDAO.refund(name);
	}
	public List<Finance> datesale(String saledate)
	{
		return finDAO.datesale(saledate);
	}
}