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

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.user;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.userSrv;

@SuppressWarnings("serial")
@WebServlet("/userServlet")
public class userServlet extends HttpServlet {
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doPost(request,response);//如果提交方式为GET，跳转到dopost执行
	    }
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	String type=request.getParameter("type");
	    	// System.out.println("type"+type);
	    	 if(type.equalsIgnoreCase("login")||type.equalsIgnoreCase("search")) {
	    		 login(request, response);
	    	 }
	    	 else if(type.equalsIgnoreCase("resign"))
	    	 {
	    		 resign(request,response);
	    	 }
	    	 else if(type.equalsIgnoreCase("delete"))
	    	 {
	    		 delete(request,response);
	    	 }
	    	 else if(type.equalsIgnoreCase("modify"))
	    	 {
	    		 modify(request,response);
	    	 }
	   }
	    
	    	    
	    
	//用户登录
		private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	    {
	    	
		    response.setCharacterEncoding("UTF-8");
	        PrintWriter out=response.getWriter();
	        String username=request.getParameter("username");
	        List<user> result=null;
	        if(username != null && username.length() > 0)
	            result=new userSrv().Fetch(username);
	        else
	            result=new userSrv().FetchAll();
	        String jsonStr="";
	        try
	        {
	            JSONArray array=new JSONArray();
	            JSONObject json;
	            for(user s : result)
	            {
	                json=new JSONObject();
	                json.put("username",s.getUsername());
	                json.put("password", s.getPassword());
	                json.put("status", s.getStatus());
	                json.put("userquestion", s.getQuestion());
	                json.put("useranswer",s.getAnswer());
	                json.put("usertel", s.getPhonenumber());
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
		private void resign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	    {
			user user=null;
			String status="1";
	        try
	        {	
	        	String username=request.getParameter("username");
	        	String password=request.getParameter("password");
	            String question=request.getParameter("question");
	            String answer=request.getParameter("answer");
	            String usertel=request.getParameter("usertel");
	            user=new user(username,password, usertel,question, answer,status);
	            response.setContentType("text/html;charset=utf-8");
	            PrintWriter out=response.getWriter();
	            if(new userSrv().add(user) == 1)
	                out.write("注册成功");
	            else if(new userSrv().add(user) == 2)
	            	out.write("用户名存在，请重试");
	            else
	                out.write("注册失败，请重试");

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
	            String username=request.getParameter("username");
	            response.setContentType("text/html;charset=utf-8");
	            PrintWriter out=response.getWriter();
	            out.write("" + new userSrv().delete(username));
	            out.close();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            response.setContentType("text/html;charset=utf-8");
	            response.getWriter().write("操作错误，请重试");
	        }
	    }
		 private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		    {
		        user user=null;
		        try
		        {
		        	System.out.println(request.getParameter("userquestion"));
		        	System.out.println(request.getParameter("useranswer"));
		        	System.out.println(request.getParameter("status"));
		        	String username=request.getParameter("username");
		        	String password=request.getParameter("password");
		            String question=request.getParameter("userquestion");
		            String answer=request.getParameter("useranswer");
		            String usertel=request.getParameter("usertel");
		            String status=request.getParameter("status");
		            user=new user(username,password, usertel,question, answer,status);
		            response.setContentType("text/html;charset=utf-8");
		            PrintWriter out=response.getWriter();
		            if(new userSrv().modify(user) == 1)
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
}
