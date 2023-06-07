package xupt.se.ttms.dao;

import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Myticket;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticketsell;
import xupt.se.util.DBUtil;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TicketDAO implements iTicketDAO {

    @SuppressWarnings("finally")
    @Override
    public int update(Ticketsell ticketsell) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        int result = 0;
        try {
            String sql = "update myticket set " + " sale_time ='" + df.format(new Date()) + "', " + " emp_id = "
                    + ticketsell.getEmp() + ", " + " cus_id = " + ticketsell.getCus() + ", "
                    + " seat_status = 1 ";
            sql += " where sched_id = " + ticketsell.getSched_id() + " and seat_row = " + ticketsell.getRow() + " and seat_col = " + ticketsell.getCol() + " and seat_status != 1";
            DBUtil db = new DBUtil();
            db.openConnection();
            result = db.execCommand(sql);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @Override
    public int update2(Ticketsell ticketsell) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        int result = 0;
        try {
            String sql = "update myticket set " + " sale_time ='" + df.format(new Date()) + "', " + " emp_id = 0"
                    + ", " + " cus_id = 0" + ", "
                    + " seat_status = 2 ";
            sql += " where sched_id = " + ticketsell.getSched_id() + " and seat_row = " + ticketsell.getRow() + " and seat_col = " + ticketsell.getCol() + " and seat_status = 1";
            DBUtil db = new DBUtil();
            db.openConnection();
            result = db.execCommand(sql);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @Override
    public List<Myticket> select()
    {
        DBUtil db=null;
        List<Myticket> myticketList=null;
        myticketList=new LinkedList<Myticket>();
        try
        {
            String sql="select * from myticket where seat_status = 1 ";
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
                    Myticket stu=new Myticket();
                    stu.setId(rst.getInt("myticket_id"));
                    stu.setSched_id(rst.getInt("sched_id"));
                    stu.setRow(rst.getInt("seat_row"));
                    stu.setCol(rst.getInt("seat_col"));
                    stu.setPrice(rst.getInt("sale_price"));
                    stu.setEmp(rst.getInt("emp_id"));
                    stu.setCus(rst.getInt("cus_id"));
                    myticketList.add(stu);
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
            return myticketList;
        }
    }

    @Override
    public List<Myticket> selectemp(int emp)
    {
        DBUtil db=null;
        List<Myticket> myticketList=null;
        myticketList=new LinkedList<Myticket>();
        try
        {
            String sql="select * from myticket where seat_status = 1 and emp_id = " +emp;
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

                    Myticket stu=new Myticket();
                    stu.setId(rst.getInt("myticket_id"));
                    stu.setSched_id(rst.getInt("sched_id"));
                    stu.setRow(rst.getInt("seat_row"));
                    stu.setCol(rst.getInt("seat_col"));
                    stu.setPrice(rst.getInt("sale_price"));
                    stu.setEmp(rst.getInt("emp_id"));
                    stu.setCus(rst.getInt("cus_id"));
                    myticketList.add(stu);
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
            return myticketList;
        }
    }
}