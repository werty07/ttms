package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Finance;

public interface iFinanceDAO
{
    public int insert(Finance fin);

    public int update(Finance fin);

    public List<Finance> select(int emp_id);
    
	public int amount(String name);
	
	public int refund(String name);
	
	public List<Finance> datesale(String saledate);

	//String selectsaleid(int sale_id);
}