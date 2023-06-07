package xupt.se.ttms.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xupt.se.ttms.model.Myticket;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticketsell;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
        if(type.equalsIgnoreCase("update"))
            update(request, response);
        else if(type.equalsIgnoreCase("update2"))
            update2(request, response);
        else if(type.equalsIgnoreCase("search"))
            search(request, response);
        else if(type.equalsIgnoreCase("searchemp"))
            searchemp(request, response);

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Ticketsell ticketsell=null;
        int sched_id=0;
        try
        {
            sched_id=Integer.valueOf(request.getParameter("sched_id"));
            int row=Integer.valueOf(request.getParameter("row"));
            int col=Integer.valueOf(request.getParameter("col"));
            int emp=Integer.valueOf(request.getParameter("emp"));
            int cus=Integer.valueOf(request.getParameter("cus"));
            ticketsell=new Ticketsell(sched_id,row,col,emp,cus);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new TicketSrv().modify(ticketsell) == 1)
                out.write("售票成功");
            else
                out.write("售票失败，请重试");

            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }

    private void update2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Ticketsell ticketsell=null;
        int sched_id=0;
        try
        {
            sched_id=Integer.valueOf(request.getParameter("sched_id"));
            int row=Integer.valueOf(request.getParameter("row"));
            int col=Integer.valueOf(request.getParameter("col"));
            int emp=0;
            int cus=0;
            ticketsell=new Ticketsell(sched_id,row,col,emp,cus);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new TicketSrv().modify2(ticketsell) == 1)
                out.write("退票成功");
            else
                out.write("退票失败，请重试");

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
        List<Myticket> result=null;

        result=new TicketSrv().FetchAll();

        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Myticket s : result)
            {
                json=new JSONObject();
                json.put("id", s.getId());
                json.put("sched_id", s.getSched_id());
                json.put("row", s.getRow());
                json.put("col", s.getCol());
                json.put("price", s.getPrice());
                json.put("emp", s.getEmp());
                json.put("cus", s.getCus());
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

    private void searchemp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        int emp=Integer.valueOf(request.getParameter("emp"));
        List<Myticket> result=null;
        result=new TicketSrv().FetchAllemp(emp);

        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Myticket s : result)
            {
                json=new JSONObject();
                json.put("id", s.getId());
                json.put("sched_id", s.getSched_id());
                json.put("row", s.getRow());
                json.put("col", s.getCol());
                json.put("price", s.getPrice());
                json.put("emp", s.getEmp());
                json.put("cus", s.getCus());
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

