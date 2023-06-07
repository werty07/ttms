package xupt.se.ttms.dao;

import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Myticket;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class ScheduleDAO implements iScheduleDAO
{
    @Override
    public int insertticket(Myticket myticket)
    {
        int result=0;
        try
        {
            String sql="insert into `myticket`(myticket_id, sched_id, seat_row, seat_col ,sale_price )"
                    + " values(" + myticket.getId() + ", " + myticket.getSched_id() + ", " + myticket.getRow() + ", "
                    + myticket.getCol() + "," + myticket.getPrice() + " )";
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            if(rst != null && rst.first())
            {
                myticket.setId(rst.getInt(1));
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
    public int insert(Schedule schedule)
    {
        int result=0;
        try
        {
            String sql="insert into `schedule`(studio_id, play_id, sched_time, sched_ticket_price )"
                    + " values(" + schedule.getStudio_id() + ", " + schedule.getPlay_id() + ", '" + schedule.getTime() + "' , "
                    + schedule.getPrice() + " )";
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            if(rst != null && rst.first())
            {
                schedule.setId(rst.getInt(1));
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
    public int update(Schedule schedule)
    {
        int result=0;
        try
        {
            String sql="update `schedule` set " + " studio_id =" + schedule.getStudio_id() + ", " + " play_id = "
                    + schedule.getPlay_id() + ", " + " sched_time = '" + schedule.getTime() + "', "
                    + " sched_ticket_price = " + schedule.getPrice();
            sql+=" where sched_id = " + schedule.getId();
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
    public int delete(int id)
    {
        int result=0;
        int result1=0;
        try
        {
            String sql="delete from  `schedule` where sched_id = " + id;
            DBUtil db=new DBUtil();
            db.openConnection();
            result=db.execCommand(sql);
            db.close();
            String sql1="delete from myticket where sched_id = " +id;
            DBUtil db1=new DBUtil();
            db1.openConnection();
            result1=db1.execCommand(sql1);
            db1.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("finally")
    public String selectscheduleid(int condt)
    {
        DBUtil db=null;
        String result="";
        try
        {
            String sql="select play_id from `schedule`  where sched_id= " + condt;
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
                    result=rst.getString("play_id");
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
    public List<Schedule> select(int play_id)
    {
        DBUtil db=null;
        List<Schedule> scheduleList=null;
        scheduleList=new LinkedList<Schedule>();
        try
        {
            String sql="select * from `schedule` where play_id!="+play_id;
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
                    Schedule schedule=new Schedule();
                    schedule.setId(rst.getInt("sched_id"));
                    schedule.setStudio_id(rst.getInt("studio_id"));
                    schedule.setPlay_id(rst.getInt("play_id"));
                    schedule.setTime(rst.getString("sched_time"));
                    schedule.setPrice(rst.getInt("sched_ticket_price"));
                    scheduleList.add(schedule);
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
            return scheduleList;
        }
    }

    @Override
    public List<Integer> selectrc(int id)
    {
        DBUtil db=null;
        List<Integer> rcList=null;
        rcList=new LinkedList<Integer>();
        try
        {
            String sql="select * from `schedule` where sched_id="+id;
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
                    Schedule schedule=new Schedule();
                    schedule.setId(rst.getInt("sched_id"));
                    schedule.setStudio_id(rst.getInt("studio_id"));
                    schedule.setPlay_id(rst.getInt("play_id"));
                    schedule.setTime(rst.getString("sched_time"));
                    schedule.setPrice(rst.getInt("sched_ticket_price"));
                    rcList.add(schedule.getStudio_id());
                }
            }
            String sql1="select * from `studio` where studio_id="+rcList.get(0);
            ResultSet rst1=db.execQuery(sql1);
            if(rst1 != null)
            {
                while(rst1.next())
                {
                    Studio stu=new Studio();
                    stu.setId(rst1.getInt("studio_id"));
                    stu.setName(rst1.getString("studio_name"));
                    stu.setRowCount(rst1.getInt("studio_row_count"));
                    stu.setColCount(rst1.getInt("studio_col_count"));
                    stu.setIntroduction(rst1.getString("studio_introduction"));
                    rcList.add(stu.getRowCount());
                    rcList.add(stu.getColCount());
                }
            }
            db.close(rst1);
            db.close(rst);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return rcList;
        }
    }
    @Override
    public List<Schedule> selectplayid(int play_id)
    {
        DBUtil db=null;
        List<Schedule> scheduleList=null;
        scheduleList=new LinkedList<Schedule>();
        try
        {
            String sql="select * from `schedule` where play_id= "+play_id;
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
                    Schedule schedule=new Schedule();
                    schedule.setId(rst.getInt("sched_id"));
                    schedule.setStudio_id(rst.getInt("studio_id"));
                    schedule.setPlay_id(rst.getInt("play_id"));
                    schedule.setTime(rst.getString("sched_time"));
                    schedule.setPrice(rst.getInt("sched_ticket_price"));
                    scheduleList.add(schedule);
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
            return scheduleList;
        }
    }

}
