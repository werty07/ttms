package xupt.se.ttms.idao;

import xupt.se.ttms.model.Myticket;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticketsell;

import java.util.List;

public interface iTicketDAO {
    public int update(Ticketsell ticketsell);

    public int update2(Ticketsell ticketsell);

    public List<Myticket> select();

    public List<Myticket> selectemp(int emp);
}
