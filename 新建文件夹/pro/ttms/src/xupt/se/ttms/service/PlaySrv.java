package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;

public class PlaySrv {
	private iPlayDAO playDAO=DAOFactory.creatPlayDAO();
	
	public int add(Play ply){
		//System.out.println("已经进入PlaySrv");
		return playDAO.insert(ply); 		
	}
	
	public int modify(Play ply){
		return playDAO.update(ply); 		
	}
	
	public int delete(int PlayID){
		return playDAO.delete(PlayID); 		
	}
	
	public List<Play> Fetch(String condt){
		return playDAO.select(condt);		
	}
	
	public List<Play> FetchAll(){
		return playDAO.select("");		
	}
}