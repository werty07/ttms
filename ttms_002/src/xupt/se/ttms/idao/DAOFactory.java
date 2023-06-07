package xupt.se.ttms.idao;

import xupt.se.ttms.dao.*;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Resource;

public class DAOFactory
{
    private static iStudioDAO stuDao;
    private static iPlayDAO playDao;
    private static iScheduleDAO scheduleDao;
    private static iEmployeeDAO employeeDao;
    private static iCusDAO cusDao;
    private static iResourceDAO resourceDao;
    private static iRolesDAO rolesDao;
    private static iTicketDAO ticketDao;

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

    public static synchronized iCusDAO creatCusDAO()
    {
        if(null == cusDao)
            cusDao=new CusDAO();
        return cusDao;
    }

    public static synchronized iResourceDAO creatResourceDAO()
    {
        if(null == resourceDao)
            resourceDao=new ResourceDAO();
        return resourceDao;
    }

    public static synchronized iRolesDAO creatRolesDAO()
    {
        if(null == rolesDao)
            rolesDao=new RolesDAO();
        return rolesDao;
    }

    public static synchronized iTicketDAO creatTicketDAO()
    {
        if(null == ticketDao)
            ticketDao=new TicketDAO();
        return ticketDao;
    }
}
