package xupt.se.ttms.idao;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Studio;

import java.util.List;

public interface iEmployeeDAO {
    public int insert(Employee employee);

    public int update(Employee employee);

    public int delete(int Id);

    public int log(String name,String pwd);

    public List<Employee> select(String employeeName);
}
