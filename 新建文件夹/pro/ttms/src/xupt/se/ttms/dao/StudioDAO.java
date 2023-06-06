package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;

public class StudioDAO implements iStudioDAO
{
    @SuppressWarnings("finally")
    @Override
    public int insert(Studio stu)
    {
        int result=0;
        try
        {
        	int StudioId=0;
            String sql="insert into studio(studio_name, studio_row_count, studio_col_count, studio_introduction )"
                    + " values('" + stu.getName() + "', " + stu.getRowCount() + ", " + stu.getColCount() + ", '"
                    + stu.getIntroduction() + "' )";
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            String sql2="select studio_id from studio where studio_name like '%" + stu.getName() + "%' ";
            try {
            	ResultSet resultSet=db.execQuery(sql2);
            	while(resultSet.next())
            	{
            		StudioId=resultSet.getInt("studio_id");
            		//System.out.println("StudioId:"+StudioId);
            	}
            }catch(Exception e) {
            	throw new RuntimeException("查找影厅编号失败",e);
            }

            for(int i=1;i<=stu.getRowCount();i++)
            {
            	for(int j=1;j<=stu.getColCount();j++)
            	{
            		String sql3="insert into seat(studio_id,seat_row,seat_column,seat_status)"
            	                +"values("
            	                +StudioId+","//需要获取演出厅座位
            				    +i+","
            				    +j+","
            				    +"0"+")";
            		//System.out.println("sql2"+sql2);
            		db.getInsertObjectIDs(sql3);
            	}
            } 
            if(rst != null && rst.first())
            {
                stu.setID(rst.getInt(1));
            }
            db.close(rst);
            db.close();
            result=1;
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
    public int update(Studio stu)
    {
        int result=0;
        try
        {
            String sql="update studio set " + " studio_name ='" + stu.getName() + "', " + " studio_row_count = "
                    + stu.getRowCount() + ", " + " studio_col_count = " + stu.getColCount() + ", "
                    + " studio_introduction = '" + stu.getIntroduction() + "' ";
            sql+=" where studio_id = " + stu.getID();
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
    public int seat_update(Studio stu)//gai
    {
        int result=0;
        try
        {
            String sql="update studio set " + " studio_name ='" + stu.getName() + "', " + " studio_row_count = "
                    + stu.getRowCount() + ", " + " studio_col_count = " + stu.getColCount() + ", "
                    + " studio_introduction = '" + stu.getIntroduction() + "' ";
            sql+=" where studio_id = " + stu.getID();
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
    @Override
    public int delete(int ID)
    {
        int result=0;
        try
        {
            String sql="delete from  studio where studio_id = " + ID;
            String sql2="delete from seat where studio_id = " + ID;
            DBUtil db=new DBUtil();
            db.openConnection();
            db.execCommand(sql2);
            result=db.execCommand(sql);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("finally")
    public String selectstudioid(int condt)
    {
        DBUtil db=null;
        String result="";
        try
        {
            String sql="select studio_name from studio  where studio_id= " + condt;
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
                    result=rst.getString("studio_name");
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
    public List<Studio> select(String studioName)
    {
        DBUtil db=null;
        List<Studio> stuList=null;
        stuList=new LinkedList<Studio>();
        try
        {
            studioName.trim();
            String sql="select * from studio where studio_name like '%" + studioName + "%'";
            //System.out.println("sql:"+sql);
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
                    Studio stu=new Studio();
                    stu.setID(rst.getInt("studio_id"));
                    stu.setName(rst.getString("studio_name"));
                    stu.setRowCount(rst.getInt("studio_row_count"));
                    stu.setColCount(rst.getInt("studio_col_count"));
                    stu.setIntroduction(rst.getString("studio_introduction"));
                    stuList.add(stu);
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
            return stuList;
        }
    }
    @SuppressWarnings("finally")
    @Override
	public List<Studio> select(int studioid){
    	DBUtil db=null;
        List<Studio> stuList=null;
        stuList=new LinkedList<Studio>();
        String sql="";
        try
        {
        	if(studioid>0)
        	{
        		sql="select * from studio where studio_id = " + studioid;
        	}
        	else
        	{
        		sql="select * from studio where studio_id >0 ";
        	}
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return null;
            }
           // System.out.println(sql+"success to connect database");//!!!!
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
            	
                while(rst.next())
                {
                    Studio stu=new Studio();
                    stu.setID(rst.getInt("studio_id"));
                    stu.setName(rst.getString("studio_name"));
                    stu.setRowCount(rst.getInt("studio_row_count"));
                    stu.setColCount(rst.getInt("studio_col_count"));
                    stu.setIntroduction(rst.getString("studio_introduction"));
                    stuList.add(stu);
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
            return stuList;
        }
    }
   
}
