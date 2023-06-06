package xupt.se.ttms.controicketller;

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

import xupt.se.ttms.model.Play;
//import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.PlaySrv;
//import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.service.FinanceSrv;

@WebServlet("/PlayServlet")
public class PlayServlet extends HttpServlet
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
        else if(type.equalsIgnoreCase("searchsale"))
            searchsale(request,response);
        else if(type.equalsIgnoreCase("searchrefund"))
            searchrefund(request,response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {  
    	System.out.println("wozaizherer");
        Play play=null;
        int PlayId=0;
        try
        {	
        	String DictTypeId=request.getParameter("DictTypeId");
        	String DictLangId=request.getParameter("DictLangId");
            String PlayName=request.getParameter("PlayName");
            String PlayIntroduction=request.getParameter("PlayIntroduction");
            int PlayLength=Integer.valueOf(request.getParameter("PlayLength"));
            String PlayTicketPrice=request.getParameter("PlayTicketPrice");
            //System.out.println(DictTypeId+DictLangId+PlayName+PlayIntroduction+PlayLength+PlayTicketPrice);
            String PlayStatus=request.getParameter("PlayStatus");//Integer.valueOf(request.getParameter("PlayStatus"));
            String PlayImage=request.getParameter("PlayImage");
            //System.out.println("7");
           // System.out.println(PlayStatus);
            // System.out.println(DictTypeId+DictLangId+PlayName+PlayIntroduction+PlayLength+PlayTicketPrice+PlayStatus);
            play=new Play(PlayId,DictTypeId,DictLangId, PlayName, PlayIntroduction, PlayLength, PlayTicketPrice, PlayStatus,PlayImage);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new PlaySrv().add(play) == 1)
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
            int PlayId=Integer.valueOf(request.getParameter("PlayId"));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            //if(new PlaySrv().delete(PlayId)==1)
             //   out.write("数据删除成功");
            //else
            //	out.write("数据删除失败");
            out.write("" + new PlaySrv().delete(PlayId));
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
        Play play=null;
        int PlayId=0;
        try
        {
        	PlayId=Integer.valueOf(request.getParameter("PlayId"));
        	String DictTypeId=request.getParameter("DictTypeId");
        	String DictLangId=request.getParameter("DictLangId");
            String PlayName=request.getParameter("PlayName");
            String PlayIntroduction=request.getParameter("PlayIntroduction");
            int PlayLength=Integer.valueOf(request.getParameter("PlayLength"));
            String PlayTicketPrice=request.getParameter("PlayTicketPrice");
            String PlayStatus=request.getParameter("PlayStatus");
            String PlayImage=request.getParameter("PlayImage");
           // System.out.println(PlayStatus);//又是Status报错！！！！？？？？
            play=new Play(PlayId,DictTypeId,DictLangId, PlayName, PlayIntroduction, PlayLength, PlayTicketPrice, PlayStatus,PlayImage);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            if(new PlaySrv().modify(play) == 1)
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
        List<Play> result=null;
        if(name != null && name.length() > 0)
            result=new PlaySrv().Fetch(name);
        else
            result=new PlaySrv().FetchAll();
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Play s : result)
            {
            	json=new JSONObject();
                json.put("PlayId", s.getPlayId());
                json.put("DictTypeId",s.getDictTypeId());
                json.put("DictLangId", s.getDictLangId());
                json.put("PlayName", s.getPlayName());
                json.put("PlayIntroduction", s.getPlayIntroduction());
                json.put("PlayLength", s.getPlayLength());
                json.put("PlayTicketPrice", s.getPlayTicketPrice());
                json.put("PlayStatus", s. getPlayStatus());
                json.put("PlayImage", s.getPlayImage());
               // System.out.println("dd:"+s.getPlayImage());
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
    private void searchsale(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        String name=request.getParameter("name");
        int TicketAmount=0;
        List<Play> result=null;
        if(name != null && name.length() > 0)
            result=new PlaySrv().Fetch(name);
        else
            result=new PlaySrv().FetchAll();
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Play s : result)
            {
                TicketAmount=new FinanceSrv().FetchAmount(s.getPlayName());//!!!!!!!!!!!!!!!1
            	json=new JSONObject();
                json.put("PlayName", s.getPlayName());
                json.put("PlayTicketPrice", s.getPlayTicketPrice());
                json.put("TicketAmount", TicketAmount);
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
    private void searchrefund(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        String name=request.getParameter("name");
        int TicketAmount=0;
        List<Play> result=null;
        if(name != null && name.length() > 0)
            result=new PlaySrv().Fetch(name);
        else
            result=new PlaySrv().FetchAll();
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Play s : result)
            {
                TicketAmount=new FinanceSrv().FetchRefund(s.getPlayName());//!!!!!!!!!!!!!!!1
            	json=new JSONObject();
                json.put("PlayName", s.getPlayName());
                json.put("PlayTicketPrice", s.getPlayTicketPrice());
                json.put("TicketAmount", TicketAmount);
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
