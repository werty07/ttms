/**
 * 
 */
package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Studio;

public interface iStudioDAO
{
    public int insert(Studio stu);

    public int update(Studio stu);
    
    public int seat_update(Studio stu);//gai

    public int delete(int ID);

    public List<Studio> select(String studioName);
    
    public List<Studio> select(int studioid);
}
