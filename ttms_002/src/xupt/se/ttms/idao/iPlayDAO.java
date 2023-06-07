package xupt.se.ttms.idao;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Studio;

import java.util.List;

public interface iPlayDAO {
    public int insert(Play play);

    public int update(Play play);

    public int delete(int id);

    public List<Play> select(String Name);
}
