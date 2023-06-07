package xupt.se.ttms.dao;

import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class PlayDAO implements iPlayDAO {
    @SuppressWarnings("finally")
    @Override
    public int insert(Play play)
    {
        int result=0;
        try
        {
            String sql="insert into play(dict_type_id, dict_lang_id, play_name , play_introduction , play_image , play_video , play_length , play_ticket_price )"
                    + " values(" + play.getType_id() + ", " + play.getLang_id() + ", '" + play.getName() + "', '"
                    + play.getIntroduction() + "', '"+ play.getImage() + "', '" + play.getVideo() + "', " + play.getLength() + ","
                    + play.getPrice() + " )";
            DBUtil db=new DBUtil();
            db.openConnection();
            ResultSet rst=db.getInsertObjectIDs(sql);
            if(rst != null && rst.first())
            {
                play.setId(rst.getInt(1));
            }
            db.close(rst);
            db.close();
            result=1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    @SuppressWarnings("finally")
    @Override
    public int update(Play play)
    {
        int result=0;
        try
        {
            String sql="update play set " + " dict_type_id =" + play.getType_id() + ", " + " dict_lang_id = "
                    + play.getLang_id() + ", " + " play_name = '" + play.getName() + "', "
                    + " play_introduction = '" + play.getIntroduction() + "', play_image = '"
                    + play.getImage() + "', " + " play_video = '" + play.getVideo()+ "', play_length = " + play.getLength()
                    + ", play_ticket_price = " + play.getPrice();
            sql+=" where play_id = " + play.getId();
            DBUtil db=new DBUtil();
            db.openConnection();
            result=db.execCommand(sql);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    @Override
    public int delete(int id)
    {
        int result=0;
        try
        {
            String sql="delete from  play where play_id = " + id;
            DBUtil db=new DBUtil();
            db.openConnection();
            result=db.execCommand(sql);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("finally")
    public String selectplayid(int condt)
    {
        DBUtil db=null;
        String result="";
        try
        {
            String sql="select play_name from play  where play_id= " + condt;
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    result=rst.getString("play_name");
                }
            }
            db.close(rst);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    @SuppressWarnings("finally")
    @Override
    public List<Play> select(String Name)
    {
        DBUtil db=null;
        List<Play> playList=null;
        playList=new LinkedList<Play>();
        try
        {
            Name.trim();
            String sql="select * from play where play_name like '%" + Name + "%'";
            db=new DBUtil();
            if(!db.openConnection())
            {
                System.out.print("fail to connect database");
                return null;
            }
            ResultSet rst=db.execQuery(sql);
            if(rst != null)
            {
                while(rst.next())
                {
                    Play play=new Play();
                    play.setId(rst.getInt("play_id"));
                    play.setType_id(rst.getInt("dict_type_id"));
                    play.setLang_id(rst.getInt("dict_lang_id"));
                    play.setName(rst.getString("play_name"));
                    play.setIntroduction(rst.getString("play_introduction"));
                    play.setImage(rst.getString("play_image"));
                    play.setVideo(rst.getString("play_video"));
                    play.setLength(rst.getInt("play_length"));
                    play.setPrice(rst.getInt("play_ticket_price"));
                    playList.add(play);
                }
            }
            db.close(rst);
            db.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return playList;
        }
    }

}
