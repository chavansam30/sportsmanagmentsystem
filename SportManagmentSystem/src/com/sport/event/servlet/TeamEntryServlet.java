package com.sport.event.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
	private static int selectedGameId ;
	
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
        
        String getType = null != request.getParameter("dataSend") ? request.getParameter("dataSend") : "";
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
				selectedGameId = getGameId(category, age, gender);
				jsonObject = getCaptainName(selectedGameId);
			}else if(getType.equalsIgnoreCase("createTeamBtn")){
				
				System.out.println("in..." + request.getParameter("TableData"));
				System.out.println("in..." + request.getParameter("TeamName"));
				// Insert into Team Table
				
				String teamName = null != request.getParameter("TeamName") ? request.getParameter("TeamName") : "";
				String teamSchoolName = null != request.getParameter("TeamSchoolName") ? request.getParameter("TeamSchoolName") : "";
				int CaptainId = null != request.getParameter("CaptainId") ? Integer.parseInt(request.getParameter("CaptainId"))  : 0;
				
				int teamId = insertTeam(teamName,teamSchoolName);
				
				ArrayList<Integer> partiId = new ArrayList<Integer>();
				
				//## Insert Captain ID here , as captain will be part of team
				partiId.add(CaptainId);
				
				try{
					JSONArray slideContent = new JSONArray(request.getParameter("TableData"));
					for (int i = 0; i < slideContent.length(); i++) {
					    
						JSONObject jsonobject = slideContent.getJSONObject(i);
					    
					    String fname = jsonobject.getString("fistnameText");
					    String lname = jsonobject.getString("lastnameText");
					    String mname = jsonobject.getString("middlenameText");

					    String day = jsonobject.getString("day");
					    String month = jsonobject.getString("month");
					    String year = jsonobject.getString("year");

					    String dob = day+"-"+month+"-"+year;
					    
					    System.out.println("Date of birth of participant" + dob);
					    String age = jsonobject.getString("ageText");
					    String gender = jsonobject.getString("genderParti");

					    String address = jsonobject.getString("addressText");
					    String city = jsonobject.getString("cityText");
					    String state = jsonobject.getString("stateText");
					    String pincode = jsonobject.getString("pincodeText");
					    
					    String phone = jsonobject.getString("phoneText");
					    String emgphone = jsonobject.getString("emergencyphone");
					    String email = jsonobject.getString("emailText");
					    
					    int participantId = insertParticipantDetails(fname,lname,mname,dob,age,gender,
					    		address,city,state,pincode,phone,emgphone,email);
					    
					    System.out.println("Participant Id" + participantId);
					    partiId.add(participantId);
	
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
				// ## Insert into TEAM_PARTI
				insertTeamParti(teamId,partiId);
				
				// ## Insert into TEAM_GAME
				insertTeamGame(teamId,selectedGameId);
		        
					
			}
		}
		if(null!=jsonObject){
			out.println(jsonObject.toString());
		}
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
	
	private JSONObject getCaptainName(int selectedGameId){
		JSONObject jsonobject=null;
		
		String selectSlab="SELECT PART_ID,CONCAT(FNAME,' ',MNAME,' ',LNAME) FROM Sport_Database.PARTICIPANT WHERE PART_ID IN " +
				"(SELECT PART_ID from Sport_Database.PARTI_GAME"
				+" where GAME_ID =" + selectedGameId + ")" ;
		
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
	
	
	private int getGameId(String category,String age,String gender){

		int gameId = 0;
		
		String selectSlab="select GAME_ID from Sport_Database.GAME WHERE DISCIPLINE='"+category+"' " +
						"and AGE_GRP='"+age+"' and CATEGORY='"+gender+"'";
		System.out.println(selectSlab);
		try {
			statement = dbConnection.getCon().createStatement();
			ResultSet resultSetSlab = null;					
			resultSetSlab = statement.executeQuery(selectSlab);		
			while(resultSetSlab.next()){
				gameId=resultSetSlab.getInt (1);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return gameId;

	}
	
	private int insertTeam(String teamName,String teamSchoolName){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int teamId = 0;
		try {
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
		    
			 String query = "insert into Sport_Database.TEAM (TEAM_NAME,TEAM_SCHOOL,INSERT_DATE_TIME,UPDATE_DATE_TIME) values (?,?,?,?)";
	         pstmt = dbConnection.getCon().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
	         pstmt.setString(1, teamName);
	         pstmt.setString(2, teamSchoolName);	
	         pstmt.setDate(3,ourJavaDateObject);
	         pstmt.setDate(4,ourJavaDateObject);
	         
	         pstmt.executeUpdate();
         
			 rs = pstmt.getGeneratedKeys();
			 if(rs != null && rs.next()){
	             System.out.println("Generated Team Id: "+rs.getInt(1));
	             teamId = rs.getInt(1);
	         }
		} catch (SQLException e) {
			e.printStackTrace();
		}        
         return teamId;         
	}
	
	private int insertParticipantDetails(String fname,String lname,String mname,String dob,String age,String gender,
			String address,String city,String state,String pincode,String phone,String emgphone,String email){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int participantId = 0;
		try {
			
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
		    
		     SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyyy"); 
	         Date date = dt.parse(dob);
	         
			 String query = "insert into Sport_Database.PARTICIPANT (FNAME,MNAME,LNAME,DOB,AGE,ADDRESS_LINE1,STATE,CITY,PINCODE," +
			 		"GENDER,PHONE,EMER_PHONE,EMAIL_ID,INSERT_DATE_TIME,UPDATE_DATE_TIME) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	         pstmt = dbConnection.getCon().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
	         pstmt.setString(1, fname);
	         pstmt.setString(2, mname);	         
	         pstmt.setString(3, lname);
	         pstmt.setDate(4,new java.sql.Date(date.getTime()));	         
	         pstmt.setFloat(5,Float.parseFloat(age));
	         pstmt.setString(6, address);	         
	         pstmt.setString(7, state);
	         pstmt.setString(8, city);	         
	         pstmt.setInt(9, Integer.parseInt(pincode));
	         pstmt.setString(10, gender);	         
	         //pstmt.setInt(11, Integer.parseInt(phone));
	         pstmt.setLong(11, Long.parseLong(phone));
	         pstmt.setLong(12,Long.parseLong(emgphone));	         
	         
	         pstmt.setString(13, email);	         
	         pstmt.setDate(14,ourJavaDateObject);
	         pstmt.setDate(15,ourJavaDateObject);
	         
	         pstmt.executeUpdate();
         
			rs = pstmt.getGeneratedKeys();
			if(rs != null && rs.next()){
	             System.out.println("Generated Participant Id: "+rs.getInt(1));
	             participantId = rs.getInt(1);
	         }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}        
         return participantId;         
	}
	
	private void insertTeamParti(int teamId,ArrayList<Integer> partiId){
		
		PreparedStatement pstmt = null;
		for(int participantId : partiId){
			
			try {
				 String query = "insert into TEAM_PARTI (TEAM_ID,PART_ID) values (?,?)";
		         pstmt = dbConnection.getCon().prepareStatement(query);
		         pstmt.setInt(1, teamId);
		         pstmt.setInt(2, participantId);
		         pstmt.executeUpdate();         
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				System.out.println("Data Inserted sucessfully in Team Parti.");
			}                 
		}
         
	}
	
	
	private void insertTeamGame(int teamId,int selectedGameId){
		
		PreparedStatement pstmt = null;
			try {
				 Calendar calendar = Calendar.getInstance();
			     java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
			    
				 String query = "insert into TEAM_GAME (TEAM_ID,GAME_ID,INSERT_DATE_TIME,UPDATE_DATE_TIME) values (?,?,?,?)";
		         pstmt = dbConnection.getCon().prepareStatement(query);
		         pstmt.setInt(1, teamId);
		         pstmt.setInt(2, selectedGameId);
		         pstmt.setDate(3,ourJavaDateObject);
		         pstmt.setDate(4,ourJavaDateObject);		         
		         pstmt.executeUpdate();         
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				System.out.println("Data Inserted sucessfully in TEAM_GAME.");
			}       

	}
	
}




