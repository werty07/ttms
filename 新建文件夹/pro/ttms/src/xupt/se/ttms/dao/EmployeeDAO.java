package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iEmployeeDAO;
import xupt.se.ttms.model.Employee;
import xupt.se.util.DBUtil;

public class EmployeeDAO implements iEmployeeDAO
{
    @SuppressWarnings("finally")
    @Override
    public int insert(Employee emp)
    {
    	int result=0;
    	String emp_gender="";
    	String emp_power="";
        try
        {
        	if(emp.getEmpGender().equals("男"))
        	{
        		emp_gender="0";
        	}
        	else
        	{
        		emp_gender="1";
        	}
        	if(emp.getEmpPower().equals("经理"))
            {
           	 emp_power="1";
            }
            else if(emp.getEmpPower().equals("售票员")){
           	 emp_power="2";
            }
            else if(emp.getEmpPower().equals("会计")){
           	 emp_power="3";
            }
            else if(emp.getEmpPower().equals("禁用")) {
           	 emp_power="0";
            }
        	
            String sql="insert into employee(emp_name,emp_gender,emp_telnum,emp_email,emp_pwd,emp_power)"
                    + "values ('"+emp.getEmpName()+"', "+emp_gender+", '"+emp.getEmpTelnum()+"', '"+
            		emp.getEmpEmail()+"', '"+emp.getEmpPwd()+"', "+emp_power+")";
            System.out.println(sql);/////////
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            if(rst != null && rst.first())
            {
                emp.setEmpId(rst.getInt(1));
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
    public int update(Employee emp)
    {
        int result=0;
        String emp_gender="";
    	String emp_power="";
        try
        {
        	if(emp.getEmpGender().equals("男"))
        	{
        		emp_gender="0";
        	}
        	else
        	{
        		emp_gender="1";
        	}
        	/*if(emp.getEmpGender().equals("经理"))
            {
           	 emp_power="1";
            }
            else if(emp.getEmpGender().equals("售票员")){
           	 emp_power="2";
            }
            else if(emp.getEmpGender().equals("会计")){
           	 emp_power="3";
            }
            else {
           	 emp_power="0";
            }*/
        	switch(emp.getEmpPower()) {
        	    case "经理":emp_power="1"; break;
        	    case "售票员":emp_power="2"; break;
        	    case "会计":emp_power="3"; break;
        	    default:emp_power="0"; break;
        	
        	}
        	 String sql="update employee set "  
                     + " emp_name = '"+ emp.getEmpName() + "', " 
                     + " emp_gender = '" + emp_gender + "',"
                     + " emp_telnum = '" + emp.getEmpTelnum() +"',"
                     + " emp_email ='" + emp.getEmpEmail()+"' ,"
                     +"emp_pwd = '"+emp.getEmpPwd()+"',"
                     +"emp_power = '"+emp_power+"'";
            sql+=" where emp_id = " + emp.getEmpId();
            DBUtil db=new DBUtil();
            db.openConnection();
            //System.out.println("sql:"+sql);
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
    public int delete(int EmpId)
    {
        int result=0;
        try
        {
            String sql="delete from employee where emp_id = " + EmpId;
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
    public String selectstudioid(int condt)
    {
        DBUtil db=null;
        String result="";
        try
        {
            String sql="select emp_name from studio  where studio_id= " + condt;
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
                    result=rst.getString("studio_name");
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
            return result;
        }
    }

    @SuppressWarnings("finally")
    @Override
    public List<Employee> select(String EmpName)
    {
    	 DBUtil db=null;
         List<Employee> empList=null;
         empList=new LinkedList<Employee>();
         try
         {
             EmpName.trim();
             String emp_gender="";
             String emp_power="0";
             String sql="select * from employee where emp_name like '%" + EmpName + "%'";
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
                     Employee emp=new Employee();
                     emp.setEmpId(rst.getInt("emp_id"));
                     emp.setEmpName(rst.getString("emp_name"));
                     if(rst.getString("emp_gender").equals("1"))
                     {
                    	 emp_gender="女";
                     }
                     else {
                    	 emp_gender="男";
                     }
                     emp.setEmpGender(emp_gender);
                     emp.setEmpTelnum(rst.getString("emp_telnum"));
                     emp.setEmpEmail(rst.getString("emp_email"));
                     emp.setEmpPwd(rst.getString("emp_pwd"));
                     if(rst.getString("emp_power").equals("1"))
                     {
                    	 emp_power="经理";
                     }
                     else if(rst.getString("emp_power").equals("2")){
                    	 emp_power="售票员";
                     }
                     else if(rst.getString("emp_power").equals("3")){
                    	 emp_power="会计";
                     }
                     else {
                    	 emp_power="禁用";
                     }
                     emp.setEmpPower(emp_power);
                     empList.add(emp);
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
             return empList;
         }
    }
    public List<Employee> login(int EmpId)
    {
    	 DBUtil db=null;
         List<Employee> empList=null;
         empList=new LinkedList<Employee>();
         try
         {
             String emp_gender="";
             String emp_power="0";
             String sql="select * from employee where emp_id = "+EmpId;
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
                     Employee emp=new Employee();
                     emp.setEmpId(rst.getInt("emp_id"));
                     emp.setEmpName(rst.getString("emp_name"));
                     if(rst.getString("emp_gender").equals("1"))
                     {
                    	 emp_gender="女";
                     }
                     else {
                    	 emp_gender="男";
                     }
                     emp.setEmpGender(emp_gender);
                     emp.setEmpTelnum(rst.getString("emp_telnum"));
                     emp.setEmpEmail(rst.getString("emp_email"));
                     emp.setEmpPwd(rst.getString("emp_pwd"));
                     if(rst.getString("emp_power").equals("1"))
                     {
                    	 emp_power="经理";
                     }
                     else if(rst.getString("emp_power").equals("2")){
                    	 emp_power="售票员";
                     }
                     else if(rst.getString("emp_power").equals("3")){
                    	 emp_power="会计";
                     }
                     else {
                    	 emp_power="禁用";
                     }
                     emp.setEmpPower(emp_power);
                     empList.add(emp);
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
             return empList;
         }
    }
}
