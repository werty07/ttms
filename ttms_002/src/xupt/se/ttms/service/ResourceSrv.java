package xupt.se.ttms.service;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iResourceDAO;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Resource;
import xupt.se.ttms.model.Studio;

import java.util.List;

public class ResourceSrv {
    private iResourceDAO resourceDAO= DAOFactory.creatResourceDAO();

    public int add(Resource resource){
        return resourceDAO.insert(resource);
    }

    public int modify(Resource resource){
        return resourceDAO.update(resource);
    }

    public int delete(int Id){
        return resourceDAO.delete(Id);
    }

    public List<Resource> Fetch(String condt){
        return resourceDAO.select(condt);
    }

    public List<Resource> FetchAll(){
        return resourceDAO.select("");
    }
}
