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
import xupt.se.ttms.service.ScheduleSrv;

@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet
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
        {
            search(request, response);
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {  
        Schedule sch=null;
        int SchedID=0;
        try
        {
            //System.out.println("运行到servlet里面了！");
        	//int SchedID=Integer.valueOf(request.getParameter("SchedID"));
        	int StudioID=Integer.valueOf(request.getParameter("StudioID"));
        	int PlayID=Integer.valueOf(request.getParameter("PlayID"));
        	String SchedTime=request.getParameter("SchedTime");
        	String SchedTicketPrice=request.getParameter("SchedTicketPrice");
        	String SchedStatus=request.getParameter("SchedStatus");
        	//System.out.println("信息打印："+StudioID+PlayID+SchedTime+SchedTicketPrice+SchedStatus);            
        	sch=new Schedule(SchedID,StudioID,PlayID,SchedTime,SchedTicketPrice,SchedStatus );
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            if(new ScheduleSrv().add(sch) == 1)
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
            int SchedID=Integer.valueOf(request.getParameter("SchedID"));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.write("" + new ScheduleSrv().delete(SchedID));
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
        Schedule stu=null;
        int SchedID=0;
        try
        {
        	SchedID=Integer.valueOf(request.getParameter("SchedID"));
        	int StudioID=Integer.valueOf(request.getParameter("StudioID"));
        	int PlayID=Integer.valueOf(request.getParameter("PlayID"));
        	String SchedTime=request.getParameter("SchedTime");
        	String SchedTicketPrice=request.getParameter("SchedTicketPrice");
        	String SchedStatus=request.getParameter("SchedStatus");
            stu=new Schedule(SchedID,StudioID,PlayID,SchedTime,SchedTicketPrice,SchedStatus );
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            if(new ScheduleSrv().modify(stu) == 1)
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
        int PlayID=0;
        if(request.getParameter("PlayID")!=null&&request.getParameter("PlayID").length()>0)
        {
        	//System.out.println("这不是空的");
            PlayID=Integer.valueOf(request.getParameter("PlayID").trim());
        }
        else
        {
        	//System.out.println("这是空的！");
        }
        //System.out.println("票务编码"+PlayID);
        List<Schedule> result=null;
        if(PlayID!=0/*request.getParameter("TicketID")!=null&request.getParameter("TicketID").length() > 0*/) {
            result=new ScheduleSrv().Fetch(PlayID);
        }
        else
            result=new ScheduleSrv().FetchAll();
        String jsonStr="";
        try
        {
        	////////////////////////////////未改
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Schedule s : result)
            {
                json=new JSONObject();
                json.put("SchedID", s.getSchedID());
              //  System.out.println("SERLET  SCHEID:"+s.getSchedID());//!!!!!!!!!!1
                json.put("StudioID", s.getStudioID());
               // System.out.println("STUDIO:"+s.getStudioID());//!!!!!!!!!!1
                json.put("PlayID", s.getPlayID());
              //  System.out.println("PLAYID:"+s.getPlayID());//!!!!!!!!!!1
                json.put("SchedTime", s.getSchedTime());
               // System.out.println("SCHEDTIME:"+s.getSchedTime());//!!!!!!!!!!1
                json.put("SchedTicketPrice", s.getSchedTicketPrice());
                //System.out.println("SchedTicketPrice:"+s.getSchedTicketPrice());//!!!!!!!!!!1
                json.put("SchedStatus", s.getSchedStatus());
                //System.out.println("SERLET SCHEDSTATUS:"+s.getSchedStatus());//!!!!!!!!!!1
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
