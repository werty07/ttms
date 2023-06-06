package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Schedule;
import xupt.se.util.DBUtil;

public class ScheduleDAO implements iScheduleDAO
{
    @SuppressWarnings("finally")
    @Override
    public int insert(Schedule sch)
    {
        int result=0;
        String SchedStatus="";
        try
        {
        	if(sch.getSchedStatus().equals("待演出"))
            {
                SchedStatus="0";	
            }
            else if(sch.getSchedStatus().equals("进行中"))
            {
            	SchedStatus="1";
            }
            else
            {
            	SchedStatus="-1";
            }
            String sql="insert into schedule(studio_id,play_id,sched_time,sched_ticket_price,sched_status )"
                    + " values( "+sch.getStudioID() + ", " + sch.getPlayID() + ", '"
                    + sch.getSchedTime() +"','"+ sch.getSchedTicketPrice() +"','"+ SchedStatus + "' );";
            String sql2="update play set play_status = '1' where play_id = "+sch.getPlayID();
            DBUtil db=new DBUtil();
            System.out.println("sql:"+sql);
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            db.execCommand(sql2);
            if(rst != null && rst.first())
            {
                sch.setSchedID(rst.getInt(1));
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
    public int update(Schedule sch)
    {
        int result=0;
        String SchedStatus="";
        try
        {
        	if(sch.getSchedStatus().equals("待演出"))
            {
                SchedStatus="0";	
            }
            else if(sch.getSchedStatus().equals("进行中"))
            {
            	SchedStatus="1";
            }
            else
            {
            	SchedStatus="-1";
            }
        	 String sql="update schedule set "  + " studio_id = " + sch.getStudioID() + ", " + " play_id = " + sch.getPlayID() + ", "
                     + " sched_time = '" + sch.getSchedTime() + "', sched_ticket_price ='" + sch.getSchedTicketPrice() +"',"+ " sched_status ='" + SchedStatus + "' ";
            sql+=" where sched_id = " + sch.getSchedID();
            //System.out.println("sql"+sql);
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
    public int delete(int SchedID)
    {
        int result=0;
        try
        {

            String sql="delete from schedule where sched_id = " + SchedID;
            String sql2="delete from ticket where sched_id = " + SchedID;
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
    public List<Schedule> select(int PlayID)
    {
        DBUtil db=null;
        List<Schedule> stuList=null;
        stuList=new LinkedList<Schedule>();
        //System.out.println(TicketID+"This is TicketDAO!");//!!!!!!!!!!!!!!!!!1
        String sql="";
        try
        {
        	if(PlayID>0)
        	{
        		sql="select * from schedule where play_id = " + PlayID;
        	}
        	else
        	{
        		sql="select * from schedule where play_id >0 ";
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
            	String SchedStatus="";
                while(rst.next())
                {
                    Schedule sch=new Schedule();
                    sch.setSchedID(rst.getInt("sched_id"));
                   // System.out.println("DAOsched_id:"+rst.getInt("sched_id"));//!!!!!!!!!!!
                    sch.setStudioID(rst.getInt("studio_id"));
                    sch.setPlayID(rst.getInt("play_id"));
                   // System.out.println("play_id:"+rst.getString("play_id"));
                    sch.setSchedTime(rst.getString("sched_time"));
                   // System.out.println("sched_time:"+rst.getString("sched_time"));
                    sch.setSchedTicketPrice(rst.getString("sched_ticket_price"));
                   // System.out.println("DAOTicketPrice:"+rst.getString("sched_ticket_price"));
                    if(rst.getString("sched_status").equals("0"))
                    {
                        SchedStatus="待演出";	
                    }
                    else if(rst.getString("sched_status").equals("1"))
                    {
                    	SchedStatus="进行中";
                    }
                    else
                    {
                    	SchedStatus="已结束";
                    }
                    sch.setSchedStatus(SchedStatus);//????????????????????????
                    stuList.add(sch);
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
