package xupt.se.ttms.dao;

import xupt.se.ttms.idao.iRolesDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Roles;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class RolesDAO implements iRolesDAO
{
    @SuppressWarnings("finally")
    @Override
    public int insert(Roles roles)
    {
        int result=0;
        try
        {
            String sql="insert into roles(role_name)"
                    + " values('" + roles.getName() + "' )";
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            if(rst != null && rst.first())
            {
                roles.setId(rst.getInt(1));
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
    public int update(Roles roles)
    {
        int result=0;
        try
        {
            String sql="update roles set " + " role_name ='" + roles.getName() + "'";
            sql+=" where role_id = " + roles.getId();
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
            String sql="delete from  roles where role_id = " + Id;
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
    public List<Roles> select(String Name)
    {
        DBUtil db=null;
        List<Roles> rolesList=null;
        rolesList=new LinkedList<Roles>();
        try
        {
            Name.trim();
            String sql="select * from roles where role_name like '%" + Name + "%'";
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
                    Roles roles=new Roles();
                    roles.setId(rst.getInt("role_id"));
                    roles.setName(rst.getString("role_name"));
                    rolesList.add(roles);
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
            return rolesList;
        }
    }

}
