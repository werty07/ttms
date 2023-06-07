package xupt.se.ttms.idao;

import xupt.se.ttms.model.Myticket;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;

import java.util.List;

public interface iScheduleDAO {
    public int insert(Schedule schedule);

    public int insertticket(Myticket myticket);

    public int update(Schedule schedule);

    public int delete(int id);

    public List<Integer> selectrc(int id);

    public List<Schedule> select(int play_Id);

    public List<Schedule> selectplayid(int play_Id);
}
