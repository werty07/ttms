package xupt.se.ttms.service;

import org.json.JSONObject;
import xupt.se.ttms.dao.TicketDAO;
import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Myticket;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticketsell;

import java.util.List;

public class TicketSrv {
    private iTicketDAO ticketDAO= DAOFactory.creatTicketDAO();

    public int modify2(Ticketsell ticketsell) {return ticketDAO.update2(ticketsell);}

    public int modify(Ticketsell ticketsell){
        return ticketDAO.update(ticketsell);
    }

    public List<Myticket> FetchAll(){
        return ticketDAO.select();
    }

    public List<Myticket> FetchAllemp(int emp){
        return ticketDAO.selectemp(emp);
    }
}
