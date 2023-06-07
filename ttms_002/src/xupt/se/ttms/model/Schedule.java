package xupt.se.ttms.model;

public class Schedule {
    private int id=0;
    private int studio_id=0;
    private int play_id=0;
    private String time="";
    private int price=0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudio_id() {
        return studio_id;
    }

    public void setStudio_id(int studio_id) {
        this.studio_id = studio_id;
    }

    public int getPlay_id() {
        return play_id;
    }

    public void setPlay_id(int play_id) {
        this.play_id = play_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Schedule(int id, int studio_id, int play_id, String time, int price) {
        this.id = id;
        this.studio_id = studio_id;
        this.play_id = play_id;
        this.time = time;
        this.price = price;
    }

    public Schedule() {
        id=0;
    }
}
/*
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

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Schedule schedule=null;
        int id=0;
        try
        {
            id=Integer.valueOf(request.getParameter("id"));
            int studio_id=Integer.valueOf(request.getParameter("studio_id"));
            int play_id=Integer.valueOf(request.getParameter("play_id"));
            String time=request.getParameter("time");
            int price=Integer.valueOf(request.getParameter("price"));

            schedule=new Schedule(id, studio_id, play_id, time, price);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new ScheduleSrv().modify(schedule) == 1) {
                out.write("数据修改成功");
            }else
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

        private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Myticket myticket=null;
        Schedule schedule=null;
        int id=0,mid=0;
        try
        {
            int studio_id=Integer.valueOf(request.getParameter("studio_id"));
            int play_id=Integer.valueOf(request.getParameter("play_id"));
            String time=request.getParameter("time");
            int price=Integer.valueOf(request.getParameter("price"));
            schedule=new Schedule(id, studio_id, play_id, time, price);
            int df=0;

            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();

            if(new ScheduleSrv().add(schedule) == 1) {
                int sid=schedule.getId();
                List<Integer> rc =null;
                rc=new ScheduleSrv().getrc(sid);
                for (int i = 1; i <= rc.get(1); i++) {
                    for (int j = 1; j <= rc.get(2); j++) {
                        myticket = new Myticket(mid, sid, i, j, price,df,df);
                        new ScheduleSrv().addticket(myticket);
                    }
                }
                out.write("数据添加成功");
            }else
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

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            int id=Integer.valueOf(request.getParameter("id"));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.write("" + new ScheduleSrv().delete(id));
            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("操作错误，请重试");
        }
    }

 */