package xupt.se.ttms.idao;

import xupt.se.ttms.dao.StudioDAO;
import xupt.se.ttms.dao.PlayDAO;
import xupt.se.ttms.dao.TicketDAO;
import xupt.se.ttms.dao.ScheduleDAO;
import xupt.se.ttms.dao.EmployeeDAO;
import xupt.se.ttms.dao.FinanceDAO;
import xupt.se.ttms.dao.userDAO;
public class DAOFactory
{
    private static iStudioDAO stuDao;
    private static iTicketDAO ticketDao;
    private static iScheduleDAO scheduleDao;
    private static iPlayDAO playDao;
    private static iEmployeeDAO employeeDao;
    private static iFinanceDAO financeDao;
    private static iuserDAO userDao;
    public static synchronized iStudioDAO creatStudioDAO()
    {
        if(null == stuDao)
            stuDao=new StudioDAO();
        return stuDao;
    }
    public static synchronized iPlayDAO creatPlayDAO()
    {
        if(null == playDao)
            playDao=new PlayDAO();
        return playDao;
    }
    public static synchronized iTicketDAO creatTicketDAO()
    {
        if(null == ticketDao)
            ticketDao=new TicketDAO();
        return ticketDao;
    }
    public static synchronized iScheduleDAO creatScheduleDAO()
    {
        if(null == scheduleDao)
            scheduleDao=new ScheduleDAO();
        return scheduleDao;
    }
    public static synchronized iEmployeeDAO creatEmployeeDAO()
    {
        if(null == employeeDao)
            employeeDao=new EmployeeDAO();
        return employeeDao;
    }
    public static synchronized iFinanceDAO creatFinanceDAO()
    {
        if(null == financeDao)
            financeDao=new FinanceDAO();
        return financeDao;
    }
    public static synchronized iuserDAO creatuserDAO()
    {
        if(null == userDao)
            userDao=new userDAO();
        return userDao;
    }
}
