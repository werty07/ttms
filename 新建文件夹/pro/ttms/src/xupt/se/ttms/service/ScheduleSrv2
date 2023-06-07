package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Schedule;

public class ScheduleSrv {
	private iScheduleDAO scheduleDAO=DAOFactory.creatScheduleDAO();
	public int add(Schedule stu){
		System.out.println("运行到scheduleDAO里面了！");
		return scheduleDAO.insert(stu); 	
	}
	
	public int modify(Schedule stu){
		return scheduleDAO.update(stu); 		
	}
	public int delete(int SchedID){
		return scheduleDAO.delete(SchedID); 		
	}
	
	public List<Schedule> Fetch(int condt){
		return scheduleDAO.select(condt);		
	}
	
	public List<Schedule> FetchAll(){
		return scheduleDAO.select(0);	//????????????????????????????????????	
	}
}
