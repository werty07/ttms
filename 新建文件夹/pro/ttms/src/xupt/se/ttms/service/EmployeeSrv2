package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iEmployeeDAO;
import xupt.se.ttms.model.Employee;
public class EmployeeSrv {
	private iEmployeeDAO employeeDAO=DAOFactory.creatEmployeeDAO();
	public int add(Employee emp){
		return employeeDAO.insert(emp); 		
	}
	
	public int modify(Employee emp){
		return employeeDAO.update(emp); 		
	}
	public int delete(int EmpId){
		return employeeDAO.delete(EmpId); 		
	}
	
	public List<Employee> Fetch(String condt){
		return employeeDAO.select(condt);		
	}
	
	public List<Employee> FetchAll(){
		return employeeDAO.select("");	//????????????????????????????????????	
	}
	public List<Employee> login(int EmpId){
		return employeeDAO.login(EmpId);
	}
}
