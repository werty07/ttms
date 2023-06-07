package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iuserDAO;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.user;
import xupt.se.ttms.service.userSrv;
import xupt.se.util.DBUtil;

public class userDAO implements iuserDAO
{
    @SuppressWarnings("finally")
    @Override
    public int insert(user user)
    {
    	int flag=0;
        int result=0;
        DBUtil db=new DBUtil();
        db.openConnection();
        ResultSet rst;
        ResultSet rst1;
        try
        {
        	String sql1="select user_name from user where user_name like '"+user.getUsername()+"'";
        	rst=db.execQuery(sql1);
            while(rst.next())
            {
            	flag=1;
            }
        }
        catch(Exception e)
        {
        	throw new RuntimeException("查找信息失败",e);
        }
        if(flag==1) {
        	return 2;
        }
        try
        {
        	String sql="insert into user(user_name, user_password,user_question,user_answer,user_tel,user_status)"
                    + " values('" + user.getUsername() + "', '" + user.getPassword() + "', '" +user.getQuestion()+"','"+user.getAnswer()+"','"+ user.getPhonenumber()+"','"+ user.getStatus()+ "')";
            rst1=db.getInsertObjectIDs(sql);
            db.close();
            result=1;
        }
        catch(Exception e)
        {
        	throw new RuntimeException("注册失败",e);
        }
        finally
        {
            return result;
        }
    }


    


    @SuppressWarnings("finally")
    public String selectuserpassword(String username)
    {
        DBUtil db=null;
        String result="";
        try
        {
            String sql="select user_password from user where user_name = '" + username+"'";
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    result=rst.getString("user_password");
                }
            }
            db.close(rst);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    @SuppressWarnings("finally")
    @Override
    public List<user> select(String username)
    {
        DBUtil db=null;
        List<user> userList=null;
        userList=new LinkedList<user>();
        try
        {
            username.trim();
            String sql="";
            if(username != null && username.length() > 0) {
            	sql="select user_name,user_password,user_status,user_question,user_answer,user_tel from user where user_name like '" + username + "'";
            }
            else {
            	sql="select user_name,user_password,user_status,user_question,user_answer,user_tel from user where user_name like '%" + username + "%'";
            }
           // System.out.println(sql);
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    user user=new user();
                    user.setPassword(rst.getString("user_password"));
                    if(rst.getString("user_status").equalsIgnoreCase("1"))
                    {
                    	 user.setStatus("正常");
                    }
                    else if(rst.getString("user_status").equalsIgnoreCase("0"))
                    {
                    	 user.setStatus("禁用");
                    }
                    user.setUsername(rst.getString("user_name"));
                    user.setQuestion(rst.getString("user_question"));
                    user.setAnswer(rst.getString("user_answer"));
                    user.setPhonenumber(rst.getString("user_tel"));
                    userList.add(user);
                }
            }
            db.close(rst);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return userList;
        }
    }
    @Override
    public int delete(String username)
    {
        int result=0;
        try
        {
            DBUtil db=new DBUtil(); 
            db.openConnection(); 
            String sql="delete from  user where user_name = " + username;
            result=db.execCommand(sql);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public int modify(user user)
    {
        int result=0;
        try
        {
        	String user_status="";
        	if(user.getStatus().equalsIgnoreCase("正常")){
        		user_status="1";
        	}
        	else if(user.getStatus().equalsIgnoreCase("禁止")){
        		user_status="0";
        	}
            String sql="update user set " + " user_name ='" + user.getUsername() + "'," + " user_password = '"
                    + user.getPassword() + "', " + " user_question = '" + user.getQuestion() + "', "
                    + " user_answer = '" + user.getAnswer() + "' ,"+"user_tel = '"+user.getPhonenumber()+"',"+"user_status='"+user_status+"'";
            sql+=" where user_name = '" + user.getUsername()+"'";
            System.out.println("sql:"+sql);
            DBUtil db=new DBUtil();
            db.openConnection();
            result=db.execCommand(sql);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    
  }
