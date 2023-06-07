package xupt.se.ttms.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.ls.LSOutput;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.EmployeeSrv;
import xupt.se.ttms.service.StudioSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.jar.JarOutputStream;

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
        if(type.equalsIgnoreCase("add"))
            add(request, response);
        else if(type.equalsIgnoreCase("delete"))
            delete(request, response);
        else if(type.equalsIgnoreCase("update"))
            update(request, response);
        else if(type.equalsIgnoreCase("search"))
            search(request, response);
        else if(type.equalsIgnoreCase("log"))
            log(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Employee employee=null;
        int id=0;
        try
        {
            int dict_id=Integer.valueOf(request.getParameter("dict_id"));
            String no=request.getParameter("no");
            String name=request.getParameter("name");
            int gender=Integer.valueOf(request.getParameter("gender"));
            String telnum=request.getParameter("telnum");
            String email=request.getParameter("email");
            String pwd=request.getParameter("pwd");
            employee=new Employee(id, dict_id,no,name,gender,telnum,email,pwd);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new EmployeeSrv().add(employee) == 1)
                out.write("数据添加成功");
            else
                out.write("数据添加失败，请重试");

            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }

    private void log(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int n=0;
        try {
            String name = request.getParameter("name");
            String pwd = request.getParameter("pwd");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            int k = new EmployeeSrv().log(name, pwd);
            if(k == 1) {
                out.write("boss");
            }
            else if(k==2){
                out.write("saleman");
            }
            else {
                out.write("error");
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            int id=Integer.valueOf(request.getParameter("id"));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.write("" + new EmployeeSrv().delete(id));
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
        Employee employee=null;
        int id=0;
        try
        {
            id=Integer.valueOf(request.getParameter("id"));
            int dict_id=Integer.valueOf(request.getParameter("dict_id"));
            String no=request.getParameter("no");
            String name=request.getParameter("name");
            int gender=Integer.valueOf(request.getParameter("gender"));
            String telnum=request.getParameter("telnum");
            String email=request.getParameter("email");
            String pwd=request.getParameter("pwd");

            employee=new Employee(id, dict_id,no,name, gender, telnum, email,pwd);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new EmployeeSrv().modify(employee) == 1)
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
        String name=request.getParameter("name");
        List<Employee> result=null;
        if(name != null && name.length() > 0)
            result=new EmployeeSrv().Fetch(name);
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
                json.put("id", s.getId());
                json.put("dict_id", s.getDict_id());
                json.put("no", s.getNo());
                json.put("name", s.getName());
                json.put("gender", s.getGender());
                json.put("telnum", s.getTelnum());
                json.put("email", s.getEmail());
                json.put("pwd", s.getPwd());
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
        // System.out.print(jsonStr);
    }

}
