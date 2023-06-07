package xupt.se.ttms.service;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iCusDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Cus;
import xupt.se.ttms.model.Studio;

import java.util.List;

public class CusSrv {
    private iCusDAO cusDAO= DAOFactory.creatCusDAO();

    public int add(Cus cus){
        return cusDAO.insert(cus);
    }

    public int modify(Cus cus){
        return cusDAO.update(cus);
    }

    public int delete(int Id){
        return cusDAO.delete(Id);
    }

    public List<Cus> Fetch(String condt){
        return cusDAO.select(condt);
    }

    public List<Cus> FetchAll(){
        return cusDAO.select("");
    }
}
