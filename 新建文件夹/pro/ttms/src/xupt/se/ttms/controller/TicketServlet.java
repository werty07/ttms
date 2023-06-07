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

import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.TicketSrv;

@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet
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
        Ticket stu=null;
        int TicketID=0;
        try
        {
        	int SeatID=0;//Integer.valueOf(request.getParameter("SeatID"));
        	int SchedID=Integer.valueOf(request.getParameter("SchedID"));
        	String TicketPrice=request.getParameter("SchedTicketPrice");
        	String TicketStatus="";//Integer.valueOf(request.getParameter("TicketStatus"));
        	String TicketLocktime="2022-06-01 00:00:00";//request.getParameter("TicketLocktime");
            stu=new Ticket(TicketID,SeatID,SchedID,TicketPrice,TicketStatus,TicketLocktime );
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            if(new TicketSrv().add(stu) == 1)
                out.write("成功生成演出票");
            else
                out.write("演出票生成失败，请重试");

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
            int TicketID=Integer.valueOf(request.getParameter("TicketID"));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.write("" + new TicketSrv().delete(TicketID));
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
        Ticket stu=null;
        int TicketID=0;
        try
        {
            TicketID=Integer.valueOf(request.getParameter("ticketid"));
            int SeatID=Integer.valueOf(request.getParameter("SeatID"));
        	int SchedID=Integer.valueOf(request.getParameter("SchedID"));
        	String TicketPrice=request.getParameter("TicketID");
        	String TicketStatus=request.getParameter("TicketStatus");
        	String TicketLocktime=request.getParameter("TicketLocktime");
            stu=new Ticket(TicketID,SeatID,SchedID,TicketPrice,TicketStatus,TicketLocktime );
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new TicketSrv().modify(stu) == 1)
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
        int TicketID=0;
        if(request.getParameter("TicketID")!=null&&request.getParameter("TicketID").length()>0)
        {
        	//System.out.println("这不是空的");
            TicketID=Integer.valueOf(request.getParameter("TicketID").trim());
        }
        else
        {
        	//System.out.println("这是空的！");
        }
        //System.out.println("票务编码"+TicketID);
        List<Ticket> result=null;
        if(TicketID!=0/*request.getParameter("TicketID")!=null&request.getParameter("TicketID").length() > 0*/) {
            result=new TicketSrv().Fetch(TicketID);
        }
        else
            result=new TicketSrv().FetchAll();
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Ticket s : result)
            {
                json=new JSONObject();
                json.put("TicketID", s.getTicketID());
                //System.out.println("TICKETID:"+s.getTicketID());//!!!!!!!!!!1
                json.put("SeatID", s.getSeatID());
                //System.out.println("SEATID:"+s.getSeatID());//!!!!!!!!!!1
                json.put("SchedID", s.getSchedID());
                //System.out.println("SCHEDID:"+s.getSchedID());//!!!!!!!!!!1
                json.put("TicketPrice", s.getTicketPrice());
                //System.out.println("TICKETPRICE:"+s.getTicketPrice());//!!!!!!!!!!1
                json.put("TicketStatus", s.getTicketStatus());
                //System.out.println("TICKETSTATUS:"+s.getTicketStatus());//!!!!!!!!!!1
                json.put("TicketLocktime", s.getTicketLocktime());
                //System.out.println("TICKETLOCKTIME:"+s.getTicketLocktime());//!!!!!!!!!!1
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
