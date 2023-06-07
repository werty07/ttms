package xupt.se.ttms.idao;

import xupt.se.ttms.model.Roles;
import xupt.se.ttms.model.Studio;

import java.util.List;

public interface iRolesDAO {
    public int insert(Roles roles);

    public int update(Roles roles);

    public int delete(int Id);

    public List<Roles> select(String Name);
}
