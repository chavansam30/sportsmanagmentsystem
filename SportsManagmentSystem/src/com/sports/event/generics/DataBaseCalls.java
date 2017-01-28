package com.sports.event.generics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sports.dbconnections.DataBaseConnection;

public class DataBaseCalls {
	
	public DataBaseCalls() throws ClassNotFoundException, SQLException{
		DataBaseConnection dbcon = new DataBaseConnection();
	}
	
	
	public ArrayList<Integer>  getCount(String TableName , int gameId){	
		String query = "SELECT PART_ID FROM Sport_Database.PARTI_GAME WHERE GAME_ID = " + gameId;
		Statement stmt;
		ResultSet rs = null;
		
		try {
			stmt = DataBaseConnection.getCon().createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Integer> participantList = new ArrayList<Integer>();
		try {
			while(rs.next()){
				participantList.add(rs.getInt("PART_ID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return participantList;
	}
	
public String getGameDetail(int gameId) throws ClassNotFoundException, SQLException{
		
		String query = "SELECT TYPE FROM Sport_Database.GAME WHERE GAME_ID = " + gameId;
		Statement stmt = DataBaseConnection.getCon().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		String gameType = "";
		while(rs.next()){
			gameType = rs.getString("TYPE");
		}
		return gameType;
	}

public String getGameID(String gameName,String age,String gender) throws ClassNotFoundException, SQLException{
	
	String query = "SELECT GAME_ID FROM Sport_Database.GAME WHERE DISCIPLINE = '"+gameName+"'"+ 
			" and AGE_GRP = '"+age+"' and CATEGORY = '"+gender+"'"   ;
	
	System.out.println("Query " + query);
	Statement stmt = DataBaseConnection.getCon().createStatement();
	ResultSet rs = stmt.executeQuery(query);
	
	String gameId = "";
	while(rs.next()){
		gameId = rs.getString("GAME_ID");
	}
	return gameId;
}


private List<String> getValues(String coulmnName){

	String query = "SELECT DISTINCT("+coulmnName+")FROM Sport_Database.GAME";
	Statement stmt;
	ResultSet rs = null;
	List<String> arrValue = new ArrayList<String>();
	
	try {
		stmt = DataBaseConnection.getCon().createStatement();
		 rs = stmt.executeQuery(query);
		 if(null != rs){
				while(rs.next()){
					arrValue.add(rs.getString(1).trim());
				}
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return arrValue;
}


public Map<Integer,List<String>> getInitialRoundDetail() throws ClassNotFoundException, SQLException{
	
	Map<Integer,List<String>> map_GameDtl = new HashMap<Integer,List<String>>();
	
	map_GameDtl.put(1, getValues("DISCIPLINE"));
	map_GameDtl.put(2, getValues("AGE_GRP"));
	map_GameDtl.put(3, getValues("CATEGORY"));
	
	return map_GameDtl;
}


}
