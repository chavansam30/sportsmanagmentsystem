package com.sports.event.generics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
}
