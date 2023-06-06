package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Ticket;
import xupt.se.util.DBUtil;

public class TicketDAO implements iTicketDAO
{
    @SuppressWarnings("finally")
    @Override
    public int insert(Ticket tic)
    {
		int result=0;
    	try
        {
            int StudioId=0;
            int SeatId=0;
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst;
            String sql1="select studio_id from schedule where sched_id = " + tic.getSchedID();
            try {
            	ResultSet resultSet=db.execQuery(sql1);
        	    while(resultSet.next())
        	    {
        		    StudioId=resultSet.getInt("studio_id");
        		    System.out.println("StudioId:"+StudioId);
        	    }
            }catch(Exception e) {
        	    throw new RuntimeException("查找影厅编号失败",e);
            }
            String sql2="select seat_id,seat_status from seat where studio_id=" + StudioId;
            try {
            	ResultSet resultSet=db.execQuery(sql2);
        	    while(resultSet.next())
        	    {
        		    SeatId=resultSet.getInt("seat_id");
        		    String seat_status=resultSet.getString("seat_status");
        		    //System.out.println("SeatId:"+SeatId);
        		    if(seat_status.equals("0")) {
            		String sql3="insert into ticket(seat_id,sched_id,ticket_price,ticket_status,ticket_locktime)"
                            + " values(" 
                            + SeatId + ", " 
            				+ tic.getSchedID() + ", '"
                            + tic.getTicketPrice()+"','" 
                            + "1" +"','"
                            + tic.getTicketLocktime() +"' )";
            		//System.out.println("sql3"+sql3);
            		rst=db.getInsertObjectIDs(sql3); 
            		if(rst != null && rst.first())
                    {
                        tic.setTicketID(rst.getInt(1));
                    }
        		    }
                   
            	 } 
            }catch(Exception e) {
        	    throw new RuntimeException("查找座位编号失败",e);
            }
          //  db.close(rst);
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
    public int update(Ticket stu)
    {
        int result=0;
        try
        {
        	String TicketStatus="";
        	if(stu.getTicketStatus().equals("已售"))
        	{
        		TicketStatus="3";
        	}
        	else if(stu.getTicketStatus().equals("锁定"))
        	{
        		TicketStatus="2";
        	}
        	else {
        		TicketStatus="1";
        	}
        	 String sql="update ticket set " + " seat_id ='" + stu.getSeatID() + "', " + " sched_id = "
                     + stu.getSchedID() + ", " + " ticket_price = " + stu.getTicketPrice() + ", "
                     + " ticket_status = '" + TicketStatus + " ticket_locktime ='" + stu.getTicketLocktime() +"' ";
            sql+=" where ticket_id = " + stu.getTicketID();
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
    public int delete(int TicketID)
    {
        int result=0;
        try
        {
            String sql="delete from  ticket where ticket_id = " + TicketID;
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
    public List<Ticket> select(int TicketID)
    {
        DBUtil db=null;
        List<Ticket> stuList=null;
        stuList=new LinkedList<Ticket>();
        //System.out.println(TicketID+"This is TicketDAO!");//!!!!!!!!!!!!!!!!!1
        String sql="";
        try
        {
        	if(TicketID>0)
        	{
        		sql="select * from ticket where ticket_id = " + TicketID;
        	}
        	else
        	{
        		sql="select * from ticket where ticket_id >0 ";
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
            	String ticket_status="";
                while(rst.next())
                {
                    Ticket stu=new Ticket();
                    stu.setTicketID(rst.getInt("ticket_id"));
                 //   System.out.println("seat_id:"+rst.getInt("ticket_id"));//!!!!!!!!!!!
                    stu.setSeatID(rst.getInt("seat_id"));
                    stu.setSchedID(rst.getInt("sched_id"));
                    stu.setTicketPrice(rst.getString("ticket_price"));
                 //   System.out.println("ticket_price:"+rst.getFloat("ticket_price"));
                    if(rst.getInt("ticket_status")==1)
                    {
                    	ticket_status="待售";
                    }
                    else if(rst.getInt("ticket_status")==2)
                    {
                    	ticket_status="锁定";
                    }
                    else
                    {
                    	ticket_status="已售";
                    }
                    stu.setTicketStatus(ticket_status);
                  //  System.out.println("TicketLocktime:"+rst.getString("ticket_locktime"));
                    stu.setTicketLocktime(rst.getString("ticket_locktime"));//????????????????????????
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
    public int amount(String name)
    {
        DBUtil db=new DBUtil();
        db.openConnection();
        int amount=0;
        int PlayId=0;
        int SchedId=0;
    	String sql="select play_id from play where ticket_status = 1 and play_name = '"+name+"'";
    	System.out.println("sql:"+sql);
    	try {
        	ResultSet resultSet=db.execQuery(sql);
        	while(resultSet.next())
        	{
        		PlayId=resultSet.getInt("play_id");
        		//System.out.println("StudioId:"+StudioId);
        	}
        }catch(Exception e) {
        	throw new RuntimeException("查找剧目编号失败",e);
        }
    	String sql2="select sched_id from schedule where play_id = "+SchedId;
    	try {
        	ResultSet resultSet1=db.execQuery(sql2);
        	while(resultSet1.next())
        	{
        		SchedId=resultSet1.getInt("sched_id");
        		String sql3="select ticket_id from ticket where ticket_status=3 and sched_id = "+SchedId;
        		try {
                	ResultSet resultSet2=db.execQuery(sql3);
                	while(resultSet2.next())
                	{
                	    amount++;	
                	}
                }catch(Exception e) {
                	throw new RuntimeException("查找剧目编号失败",e);
                }
        	}
        }catch(Exception e) {
        	throw new RuntimeException("查找剧目编号失败",e);
        }
        //ResultSet rst=db.getInsertObjectIDs(sql);
    	return amount;
    }

}
