package xupt.se.ttms.dao;

import xupt.se.ttms.idao.iEmployeeDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDAO implements iEmployeeDAO
{
    @SuppressWarnings("finally")
    @Override
    public int insert(Employee employee)
    {
        int result=0;
        try
        {
            String sql="insert into employee(dict_id, emp_no, emp_name, emp_gender,emp_telnum,emp_email,emp_pwd,emp_status )"
                    + " values(" + employee.getDict_id() + ", '" + employee.getNo() + "', '" + employee.getName() + "', "
                    + employee.getGender() + ",'" + employee.getTelnum() +"','" + employee.getEmail() + "','" + employee.getPwd() + "',2 )";
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            if(rst != null && rst.first())
            {
                employee.setId(rst.getInt(1));
            }
            db.close(rst);
            db.close();
            result=1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    @SuppressWarnings("finally")
    @Override
    public int update(Employee employee)
    {
        int result=0;
        try
        {
            String sql="update employee set " + " dict_id = " + employee.getDict_id() + ", " + "emp_no = '"
                    + employee.getNo() + "', " + " emp_name = '" + employee.getName() + "', "
                    + " emp_gender = " + employee.getGender() + ", " + " emp_telnum = '" + employee.getTelnum() + "',emp_email = '" + employee.getEmail() + "',emp_pwd = '" + employee.getPwd()+"'";
            sql+=" where emp_id = " + employee.getId();
            DBUtil db=new DBUtil();
            db.openConnection();
            result=db.execCommand(sql);
            db.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    @Override
    public int log(String name, String pwd)
    {
        int result=0;
        try
        {
            String sql="select * from employee where emp_name= '" + name + "' and emp_pwd = '" + pwd + "'";
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.execQuery(sql);
            if(rst.next()){
                result = rst.getInt("emp_status");
            }
            db.close(rst);
            db.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    @Override
    public int delete(int Id)
    {
        int result=0;
        try
        {
            String sql="delete from  employee where emp_id = " + Id + " and emp_status != 1";
            DBUtil db=new DBUtil();
            db.openConnection();
            result=db.execCommand(sql);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }


    @SuppressWarnings("finally")
    @Override
    public List<Employee> select(String Name)
    {
        DBUtil db=null;
        List<Employee> employeeList=null;
        employeeList=new LinkedList<Employee>();
        try
        {
            Name.trim();
            String sql="select * from employee where emp_name like '%" + Name + "%'";
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    Employee employee=new Employee();
                    employee.setId(rst.getInt("emp_id"));
                    employee.setDict_id(rst.getInt("dict_id"));
                    employee.setNo(rst.getString("emp_no"));
                    employee.setName(rst.getString("emp_name"));
                    employee.setGender(rst.getInt("emp_gender"));
                    employee.setTelnum(rst.getString("emp_telnum"));
                    employee.setEmail(rst.getString("emp_email"));
                    employee.setPwd(rst.getString("emp_pwd"));
                    employeeList.add(employee);
                }
            }
            db.close(rst);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return employeeList;
        }
    }

}
