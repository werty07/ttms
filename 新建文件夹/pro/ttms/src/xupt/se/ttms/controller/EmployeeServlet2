package xupt.se.ttms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.service.EmployeeSrv;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet
{
    private static final long serialVersionUID=1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String type=request.getParameter("type");
        // 根据请求操作类型，执行相应的增、删、该、查
        //System.out.println("type:"+type);
        if(type.equalsIgnoreCase("add"))
            add(request, response);
        else if(type.equalsIgnoreCase("delete"))
            delete(request, response);
        else if(type.equalsIgnoreCase("update"))
            update(request, response);
        else if(type.equalsIgnoreCase("search"))
        {
            search(request, response);
        }
        else if(type.equalsIgnoreCase("login"))
        {
        	login(request,response);
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {  
    	Employee emp=null;
        int EmpId=0;
        try
        {
        	//EmpId=Integer.valueOf(request.getParameter("EmpId")); 
        	String EmpName=request.getParameter("EmpName");
        	String EmpGender=request.getParameter("EmpGender"); 
        	String EmpTelnum=request.getParameter("EmpTelnum");
        	String EmpEmail=request.getParameter("EmpEmail");
        	String EmpPwd=request.getParameter("EmpPwd");
        	String EmpPower=request.getParameter("EmpPower");
            emp=new Employee(EmpId,EmpName,EmpGender,EmpTelnum,EmpEmail,EmpPwd,EmpPower);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            if(new EmployeeSrv().add(emp) == 1)
                out.write("注册成功");
            else
                out.write("注册失败失败，请重试");

            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            int EmpId=Integer.valueOf(request.getParameter("EmpId"));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.write("" + new EmployeeSrv().delete(EmpId));
            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Employee emp=null;
        int EmpId=0;
        try
        {
        	EmpId=Integer.valueOf(request.getParameter("EmpId")); 
        	String EmpName=request.getParameter("EmpName");
        	String EmpGender=request.getParameter("EmpGender"); 
        	String EmpTelnum=request.getParameter("EmpTelnum");
        	String EmpEmail=request.getParameter("EmpEmail");
        	String EmpPwd=request.getParameter("EmpPwd");
        	String EmpPower=request.getParameter("EmpPower");
            emp=new Employee(EmpId,EmpName,EmpGender,EmpTelnum,EmpEmail,EmpPwd,EmpPower);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new EmployeeSrv().modify(emp) == 1)
                out.write("数据修改成功");
            else
                out.write("数据修改失败，请重试");

            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        String EmpName=request.getParameter("EmpName");
        List<Employee> result=null;
        if(request.getParameter("EmpName")!=null&&request.getParameter("EmpName").length()>0) {
            result=new EmployeeSrv().Fetch(EmpName);
        }
        else
            result=new EmployeeSrv().FetchAll();
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Employee s : result)
            {
                json=new JSONObject();
                json.put("EmpId", s.getEmpId());
                json.put("EmpName", s.getEmpName());
                json.put("EmpGender", s.getEmpGender());
                json.put("EmpTelnum", s.getEmpTelnum());
                json.put("EmpEmail", s.getEmpEmail());
                json.put("EmpPwd", s.getEmpPwd());
                json.put("EmpPower", s.getEmpPower());
                array.put(json);
            }
            jsonStr=array.toString();
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            out.println(jsonStr);
            out.flush();
            out.close();
        }
    }
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	//System.out.println("wozai");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        int EmpId=Integer.valueOf(request.getParameter("empid"));
        List<Employee> result=null;
        result=new EmployeeSrv().login(EmpId);
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Employee s : result)
            {
                json=new JSONObject();
                json.put("EmpId", s.getEmpId());
                json.put("EmpName", s.getEmpName());
                json.put("EmpGender", s.getEmpGender());
                json.put("EmpTelnum", s.getEmpTelnum());
                json.put("EmpEmail", s.getEmpEmail());
                json.put("EmpPwd", s.getEmpPwd());
                json.put("EmpPower", s.getEmpPower());
                array.put(json);
            }
            jsonStr=array.toString();
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            out.println(jsonStr);
            out.flush();
            out.close();
        }
    }
}
