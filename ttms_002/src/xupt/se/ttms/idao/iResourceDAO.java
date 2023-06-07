package xupt.se.ttms.idao;

import xupt.se.ttms.model.Resource;
import xupt.se.ttms.model.Studio;

import java.util.List;

public interface iResourceDAO {
    public int insert(Resource resource);

    public int update(Resource resource);

    public int delete(int Id);

    public List<Resource> select(String Name);
}
