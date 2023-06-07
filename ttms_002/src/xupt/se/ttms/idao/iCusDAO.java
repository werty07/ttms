package xupt.se.ttms.idao;

import xupt.se.ttms.model.Cus;
import xupt.se.ttms.model.Studio;

import java.util.List;

public interface iCusDAO {
    public int insert(Cus cus);

    public int update(Cus cus);

    public int delete(int Id);

    public List<Cus> select(String Name);
}
