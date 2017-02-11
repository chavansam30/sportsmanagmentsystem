package com.sport.event.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sports.dbconnections.DataBaseConnection;



/**
 * Servlet implementation class TeamEntryServlet
 */
public class TeamEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DataBaseConnection dbConnection;
	//private static Connection connection;
	private static Statement statement;

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamEntryServlet() {    	
        super();       
        try {
			dbConnection = new  DataBaseConnection ();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        
        String getType = request.getParameter("dataSend");
        System.out.println( "Selected Type" + getType);
        
        JSONObject jsonObject = null;
        
		if(null != getType && !getType.trim().equalsIgnoreCase("")){
			if(getType.equalsIgnoreCase("category")){
				jsonObject = getGameCategory();
			}else if(getType.equalsIgnoreCase("age")){
				String category=request.getParameter("category");
				System.out.println(category);
				jsonObject = getAge(category);
			}
			else if(getType.equalsIgnoreCase("searchBtn")){
				String category=request.getParameter("category");
				System.out.println("Category on select button :" + category);
				String age=request.getParameter("age");
				System.out.println("Age on select button :" + age);
				String gender=request.getParameter("gender");
				System.out.println("Gender on select button :" + gender);
				jsonObject = getCaptainName(category,age,gender);
			}else if(getType.equalsIgnoreCase("createTeamBtn")){
				
				System.out.println("in..." + request.getParameter("TableData"));
				try{
					JSONArray slideContent = new JSONArray(request.getParameter("TableData"));
					for (int i = 0; i < slideContent.length(); i++) {
					    JSONObject jsonobject = slideContent.getJSONObject(i);
					    String name = jsonobject.getString("firstname");
					    String url = jsonobject.getString("middlename");
					    
					    System.out.println(name  + "...." + url);
					}
				}catch(Exception e){
					
				}
				
		        
		        /*
				JSONArray jsonarray;
				try {
					jsonarray = new JSONArray(request.getParameter("TableData"));
					for (int i = 0; i < jsonarray.length(); i++) {
					    JSONObject jsonobject = jsonarray.getJSONObject(i);
					    String name = jsonobject.getString("firstname");
					    String url = jsonobject.getString("middlename");
					    
					    System.out.println(name  + "...." + url);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/

				
			
			}
		}		
		out.println(jsonObject.toString());
	}
	
	private JSONObject executeData(String query,JSONObject jsonobject){
		try {
			jsonobject=new JSONObject();
			statement = dbConnection.getCon().createStatement();
			ResultSet resultSetSlab = null;					
			resultSetSlab = statement.executeQuery(query);
			
			while(resultSetSlab.next()){
				String jsonValue=resultSetSlab.getString(1);
				jsonobject.put(jsonValue, jsonValue);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonobject;
	}
	private JSONObject getAge(String category){
		JSONObject jsonobject=null;
		String query="SELECT distinct(AGE_GRP) FROM Sport_Database.GAME WHERE TYPE='TEAM' and DISCIPLINE='"+category+"';" ;
		System.out.println(query);
		jsonobject = executeData(query,jsonobject);
		return jsonobject;
	}
	
	
	private JSONObject getGameCategory(){
		JSONObject jsonobject=null;
		String selectSlab="SELECT distinct(DISCIPLINE) FROM Sport_Database.GAME WHERE TYPE='TEAM'";
		jsonobject = executeData(selectSlab,jsonobject);
		return jsonobject;
	}
	
	private JSONObject getCaptainName(String category,String age,String gender){
		JSONObject jsonobject=null;
		
		String selectSlab="SELECT PART_ID,CONCAT(FNAME,' ',MNAME,' ',LNAME) FROM Sport_Database.PARTICIPANT WHERE PART_ID IN (SELECT PART_ID from Sport_Database.PARTI_GAME"
				+" where GAME_ID = (select GAME_ID from Sport_Database.GAME WHERE DISCIPLINE='"+category+"' " +
						"and AGE_GRP='"+age+"' and CATEGORY='"+gender+"'))";
		
		try {
			jsonobject=new JSONObject();
			statement = dbConnection.getCon().createStatement();
			ResultSet resultSetSlab = null;					
			resultSetSlab = statement.executeQuery(selectSlab);
			
			while(resultSetSlab.next()){
				String jsonKey=resultSetSlab.getString(1);
				String jsonValue=resultSetSlab.getString(2);
				jsonobject.put(jsonKey, jsonValue);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}		
		//jsonobject = executeData(selectSlab,jsonobject);
		return jsonobject;

	}
}
