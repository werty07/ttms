package xupt.se.ttms.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xupt.se.ttms.model.Myticket;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

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
        else if(type.equalsIgnoreCase("update")){
            update(request,response);
        }
        else if(type.equalsIgnoreCase("search"))
            search(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Myticket myticket=null;
        Schedule schedule=null;
        int id=0,mid=0;
        try
        {
            int studio_id=Integer.valueOf(request.getParameter("studio_id"));
            int play_id=Integer.valueOf(request.getParameter("play_id"));
            String time=request.getParameter("time");
            int price=Integer.valueOf(request.getParameter("price"));
            schedule=new Schedule(id, studio_id, play_id, time, price);
            int df=0;

            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new ScheduleSrv().add(schedule) == 1) {
                int sid=schedule.getId();
                List<Integer> rc =null;
                rc=new ScheduleSrv().getrc(sid);
                for (int i = 1; i <= rc.get(1); i++) {
                    for (int j = 1; j <= rc.get(2); j++) {
                        myticket = new Myticket(mid, sid, i, j, price,df,df);
                        new ScheduleSrv().addticket(myticket);
                    }
                }
                out.write("数据添加成功");
            }else
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
            out.write("" + new ScheduleSrv().delete(id));
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
        Myticket myticket=null;
        Schedule schedule=null;
        int id=0,mid=0;
        try {
            id = Integer.valueOf(request.getParameter("id"));
            response.setContentType("text/html;charset=utf-8");
            new ScheduleSrv().delete(id);

            int studio_id = Integer.valueOf(request.getParameter("studio_id"));
            int play_id = Integer.valueOf(request.getParameter("play_id"));
            String time = request.getParameter("time");
            int price = Integer.valueOf(request.getParameter("price"));
            schedule = new Schedule(id, studio_id, play_id, time, price);
            int df = 0;

            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();

            if (new ScheduleSrv().add(schedule) == 1) {
                int sid = schedule.getId();
                List<Integer> rc = null;
                rc = new ScheduleSrv().getrc(sid);
                for (int i = 1; i <= rc.get(1); i++) {
                    for (int j = 1; j <= rc.get(2); j++) {
                        myticket = new Myticket(mid, sid, i, j, price, df, df);
                        new ScheduleSrv().addticket(myticket);
                    }
                }
                out.write("数据修改成功");
            } else
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
        //int play_id=Integer.parseInt(request.getParameter("play_id"));
        //int play_id=Integer.valueOf(request.getParameter("play_id"));
        int play_id=request.getIntHeader("play_id");
        List<Schedule> result=null;
        //System.out.println(play_id);
        if(play_id > 0)
            result=new ScheduleSrv().Fetch(play_id);
        else
            result=new ScheduleSrv().FetchAll();
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Schedule s : result)
            {
                json=new JSONObject();
                json.put("id", s.getId());
                json.put("studio_id", s.getStudio_id());
                json.put("play_id", s.getPlay_id());
                json.put("time", s.getTime());
                json.put("price", s.getPrice());
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
