package xupt.se.ttms.service;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iRolesDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Roles;
import xupt.se.ttms.model.Studio;

import java.util.List;

public class RolesSrv {
    private iRolesDAO rolesDAO= DAOFactory.creatRolesDAO();

    public int add(Roles roles){
        return rolesDAO.insert(roles);
    }

    public int modify(Roles roles){
        return rolesDAO.update(roles);
    }

    public int delete(int Id){
        return rolesDAO.delete(Id);
    }

    public List<Roles> Fetch(String condt){
        return rolesDAO.select(condt);
    }

    public List<Roles> FetchAll(){
        return rolesDAO.select("");
    }
}
