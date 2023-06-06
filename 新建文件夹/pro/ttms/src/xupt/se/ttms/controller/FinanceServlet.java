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

import xupt.se.ttms.model.Finance;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.service.FinanceSrv;
import xupt.se.ttms.service.PlaySrv;

@WebServlet("/FinanceServlet")
public class FinanceServlet extends HttpServlet
{
    private static final long serialVersionUID=1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String type=request.getParameter("type");
      //  System.out.println("wojinlaile");
        // 根据请求操作类型，执行相应的增、删、该、查
        if(type.equalsIgnoreCase("add"))
            add(request, response);
//        else if(type.equalsIgnoreCase("delete"))
//            delete(request, response);
        else if(type.equalsIgnoreCase("update"))
            update(request, response);
        else if(type.equalsIgnoreCase("search"))
            search(request, response);
        //else if(type.equalsIgnoreCase("seat_update"))
            //seat_update(request,response);
        else if(type.equalsIgnoreCase("datesale"))
        {
        	datesale(request,response);
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {  
        Finance fin=null;
        int sale_id=0;
        try
        {
        	sale_id=Integer.valueOf(request.getParameter("sale_id"));
        	int emp_id=Integer.valueOf(request.getParameter("emp_id"));
        	String sale_time=request.getParameter("sale_time");
        	String sale_payment=request.getParameter("sale_payment");
        	String sale_change=request.getParameter("sale_change");
        	int sale_type=Integer.valueOf(request.getParameter("sale_type"));
        	int sale_status=Integer.valueOf(request.getParameter("sale_status"));
            String play_name=request.getParameter("play_name");
            fin=new Finance(sale_id,emp_id,sale_time,sale_payment,sale_change,sale_type,sale_status,play_name);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new FinanceSrv().add(fin) == 1)
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

//    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//    {
//        try
//        {
//            int id=Integer.valueOf(request.getParameter("id"));
//            response.setContentType("text/html;charset=utf-8");
//            PrintWriter out=response.getWriter();
//            out.write("" + new StudioSrv().delete(id));
//            out.close();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().write("操作错误，请重试");
//        }
//    }
//
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Finance fin=null;
        int id=0;
        try
        {
//        	int sale_id=Integer.valueOf(request.getParameter("sale_id"));
//        	int emp_id=Integer.valueOf(request.getParameter("emp_id"));
//        	String sale_time=String.valueOf(request.getParameter("sale_time"));
//        	String sale_payment=String.valueOf(request.getParameter("sale_payment"));
//        	String sale_change=String.valueOf(request.getParameter("sale_change"));
//        	int sale_type=Integer.valueOf(request.getParameter("sale_type"));
        	int sale_status=Integer.valueOf(request.getParameter("sale_status"));
      
            fin=new Finance(sale_status);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

//            if(new FinanceSrv().modify(fin) == 1)
//                out.write("数据修改成功");
//            else
//                out.write("数据修改失败，请重试");

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
    	//System.out.println("wozai search limianle !");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        int emp_id=0;
        if(request.getParameter("emp_id")!=null&&request.getParameter("emp_id").length()>0)
        {
        	//System.out.println("这不是空的");
            emp_id= Integer.valueOf(request.getParameter("emp_id").trim());
        }
       
        List<Finance> result=null;
        if(emp_id !=0 )
            result=new FinanceSrv().Fetch(emp_id);
        else
            result=new FinanceSrv().FetchAll();
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Finance s : result)
            {
            	//200112;
                json=new JSONObject();
                json.put("sale_id", s.getsale_id());
                json.put("emp_id",s.getemp_id());
                json.put("sale_time", s.getsale_time());
                json.put("sale_payment", s.getsale_payment());
                json.put("sale_change", s.getsale_change());
                if(s.getsale_status()==1)
                {
                	json.put("sale_type","已付款");
                }
                else
                {
                	json.put("sale_type", "待付款");
                }
                if(s.getsale_type()==1)
                {
                	json.put("sale_status","销售单");
                }
                else
                {
                	json.put("sale_status", "退款单");
                }
                
                json.put("play_name", s.getplay_name());
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
        //System.out.print(jsonStr);
    }
    private void datesale(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        String saledate=request.getParameter("sale_date");
        List<Finance> result=null;
        if(saledate != null && saledate.length() > 0)
        {
        	 result=new FinanceSrv().datesale(saledate);
        }
        else
        {
        	result=new FinanceSrv().datesale("");
        }
            
        String jsonStr="";
        try
        {
            JSONArray array=new JSONArray();
            JSONObject json;
            for(Finance f : result)
            {
            	json=new JSONObject();
                json.put("SaleTime", f.getsale_time());
                json.put("SalePayment", f.getsale_payment());
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
        
        //System.out.print(jsonStr);//
    }
}
