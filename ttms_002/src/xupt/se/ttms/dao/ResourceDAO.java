package xupt.se.ttms.dao;

import xupt.se.ttms.idao.iResourceDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Resource;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class ResourceDAO implements iResourceDAO
{
    @SuppressWarnings("finally")
    @Override
    public int insert(Resource resource)
    {
        int result=0;
        try
        {
            String sql="insert into resource(res_parent, res_name, res_URL )"
                    + " values('" + resource.getParent() + "', '" + resource.getName() + "', '" + resource.getURL() +"' )";
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            if(rst != null && rst.first())
            {
                resource.setId(rst.getInt(1));
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
    public int update(Resource resource)
    {
        int result=0;
        try
        {
            String sql="update resource set " + " res_parent ='" + resource.getParent() + "', " + " res_name = '"
                    + resource.getName() + "', " + " res_URL = '" + resource.getURL() + "'";
            sql+=" where res_id = " + resource.getId();
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
    public int delete(int Id)
    {
        int result=0;
        try
        {
            String sql="delete from  resource where res_id = " + Id;
            DBUtil db=new DBUtil();
            db.openConnection();
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
    @Override
    public List<Resource> select(String Name)
    {
        DBUtil db=null;
        List<Resource> resourcesList=null;
        resourcesList=new LinkedList<Resource>();
        try
        {
            Name.trim();
            String sql="select * from resource where res_name like '%" + Name + "%'";
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
                    Resource resource=new Resource();
                    resource.setId(rst.getInt("res_id"));
                    resource.setParent(rst.getString("res_parent"));
                    resource.setName(rst.getString("res_name"));
                    resource.setURL(rst.getString("res_URL"));
                    resourcesList.add(resource);
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
            return resourcesList;
        }
    }

}

