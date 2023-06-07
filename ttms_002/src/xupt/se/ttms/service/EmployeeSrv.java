package xupt.se.ttms.service;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iEmployeeDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Studio;

import java.util.List;

public class EmployeeSrv {
    private iEmployeeDAO employeeDAO= DAOFactory.creatEmployeeDAO();

    public int add(Employee employee){
        return employeeDAO.insert(employee);
    }

    public int modify(Employee employee){
        return employeeDAO.update(employee);
    }

    public int delete(int Id){
        return employeeDAO.delete(Id);
    }

    public int log(String name , String pwd){
        return employeeDAO.log(name,pwd);
    }

    public List<Employee> Fetch(String condt){
        return employeeDAO.select(condt);
    }

    public List<Employee> FetchAll(){
        return employeeDAO.select("");
    }
}
