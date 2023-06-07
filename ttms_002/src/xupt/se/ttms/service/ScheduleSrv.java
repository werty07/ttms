package xupt.se.ttms.service;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Myticket;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;

import java.util.List;

public class ScheduleSrv {
    private iScheduleDAO scheduleDAO= DAOFactory.creatScheduleDAO();

    public int addticket(Myticket myticket){
        return scheduleDAO.insertticket(myticket);
    }

    public List<Integer> getrc(int id){return scheduleDAO.selectrc(id);}

    public int add(Schedule schedule){
        return scheduleDAO.insert(schedule);
    }

    public int modify(Schedule schedule){
        return scheduleDAO.update(schedule);
    }


    public int delete(int id){
        return scheduleDAO.delete(id);
    }

    public List<Schedule> Fetch(int condt){
        return scheduleDAO.selectplayid(condt);
    }

    public List<Schedule> FetchAll(){
        return scheduleDAO.select(0);
    }
}
