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

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;

@WebServlet("/StudioServlet")
public class StudioServlet extends HttpServlet
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
        else if(type.equalsIgnoreCase("search1"))
        	search1(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {  
        Studio stu=null;
        int id=0;
        try
        {
            String name=request.getParameter("studioname");//getParameter根据参数名获取参数值
            int rowCount=Integer.valueOf(request.getParameter("rowcount"));
            int colCount=Integer.valueOf(request.getParameter("colcount"));
            String intro=request.getParameter("intro");
            stu=new Studio(id, name, rowCount, colCount, intro);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new StudioSrv().add(stu) == 1)
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

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            int id=Integer.valueOf(request.getParameter("id"));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.write("" + new StudioSrv().delete(id));
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
        Studio stu=null;
        int id=0;
        try
        {
            id=Integer.valueOf(request.getParameter("studioid"));
            String name=request.getParameter("studioname");
            int rowCount=Integer.valueOf(request.getParameter("rowcount"));
            int colCount=Integer.valueOf(request.getParameter("colcount"));
            String intro=request.getParameter("intro");
            stu=new Studio(id, name, rowCount, colCount, intro);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new StudioSrv().modify(stu) == 1)
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
    private void seat_update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Studio stu=null;
        int id=0;
        try
        {
            id=Integer.valueOf(request.getParameter("studioid"));
            String name=request.getParameter("studioname");
            int rowCount=Integer.valueOf(request.getParameter("rowcount"));
            int colCount=Integer.valueOf(request.getParameter("colcount"));
            String intro=request.getParameter("intro");
            stu=new Studio(id, name, rowCount, colCount, intro);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new StudioSrv().manage(stu) == 1)
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
     //   String name="";
     //   if(request.getParameter("name")!=null&&request.getParameter("name").length()>0)
     //   {
     //   	System.out.println("这不是空的");
     //       name=request.getParameter("name");
     //   }
     //   else
     //   {
     //   	System.out.println("这是空的！");
     //   }//！！！！！！！！！！！！
        List<Studio> result=null;
        if(name != null && name.length() > 0)
            result=new StudioSrv().Fetch(name);
        else
            result=new StudioSrv().FetchAll();
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Studio s : result)
            {
                json=new JSONObject();
                json.put("id", s.getID());
                json.put("name", s.getName());
                json.put("rowCount", s.getRowCount());
                json.put("colCount", s.getColCount());
                json.put("introduction", s.getIntroduction());
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
    
    private void search1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        int ID=0;
        if(request.getParameter("ID")!=null&&request.getParameter("ID").length()>0)
        {
        	//System.out.println("这不是空的");
            ID=Integer.valueOf(request.getParameter("ID").trim());
        }
        else
        {
        	//System.out.println("这是空的！");
        }
        //System.out.println("票务编码"+PlayID);
        List<Studio> result=null;
        if(ID!=0/*request.getParameter("TicketID")!=null&request.getParameter("TicketID").length() > 0*/) {
            result=new StudioSrv().Fetch(ID);
        }
        else
            result=new StudioSrv().FetchAll();
        String jsonStr="";
        try
        {
        	////////////////////////////////未改
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Studio s : result)
            {
            	json=new JSONObject();
                json.put("id", s.getID());
                json.put("name", s.getName());
                json.put("rowCount", s.getRowCount());
                json.put("colCount", s.getColCount());
                json.put("introduction", s.getIntroduction());
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
         //System.out.print("jsonStr:"+jsonStr);//!!!!!!!!!!!111
    }
    

}
