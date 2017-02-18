package com.event.sports.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.sport.dto.DatabaseConnection;

@Controller
public class UpdateParticipantEntry {

	@RequestMapping(value="/updatePartipant",method=RequestMethod.GET)
	public String updateParticipant(@RequestParam("partId") String partID,@RequestParam("fname") String fname,@RequestParam("mname") String mname,@RequestParam("lname") String lname, 
			@RequestParam("dob") String dob,@RequestParam("age") String age,@RequestParam("schoolName") String school_name,
			@RequestParam("add1") String add1,@RequestParam("add2") String add2,@RequestParam("state") String state,@RequestParam("city") String city,
			@RequestParam("pincode") String pincode,@RequestParam("sch_add1") String sch_add1,@RequestParam("sch_add2") String sch_add2,
			@RequestParam("sch_state") String sch_state,@RequestParam("sch_city") String sch_city,@RequestParam("sch_pin") String sch_pin,
			@RequestParam("gender") String gender,@RequestParam("phone") String phone,@RequestParam("emer_phone") String emer_phone,
			@RequestParam("email") String email,@RequestParam("in_user") String in_user,@RequestParam("update_user") String update_user,ModelMap modelMap){
				
		
		
		DatabaseConnection databaseConnection = DatabaseConnection.getObject();
		Connection connection = databaseConnection.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("Update Sport_Database.PARTICIPANT set FNAME=?,MNAME=?,LNAME=?,"
					+ "DOB=?,AGE=?,SCHOOL=?,ADDRESS_LINE1=?,ADDRESS_LINE2=?,STATE=?,CITY=?,PINCODE=?,SCHOOL_ADDRESS_LINE1=?,"
					+ "SCHOOL_ADDRESS_LINE2=?,SCHOOL_STATE=?,SCHOOL_CITY=?,SCHOOL_PINCODE=?,GENDER=?,PHONE=?,EMER_PHONE=?,EMAIL_ID=?,INSERT_USER_NAME=?,"
					+ "UPDATE_USER_NAME=? where PART_ID=?");
			stmt.setString(1, fname);
			stmt.setString(2, mname);
			stmt.setString(3, lname);
			stmt.setString(4, dob);
			stmt.setString(5, age);
			stmt.setString(6,school_name);
			stmt.setString(7,add1);
			stmt.setString(8,add2);
			stmt.setString(9,state);
			stmt.setString(10,city);
			stmt.setString(11,pincode);
			stmt.setString(12,sch_add1);
			stmt.setString(13,sch_add2);
			stmt.setString(14,sch_state);
			stmt.setString(15,sch_city);
			stmt.setString(16,sch_pin);
			stmt.setString(17,gender);
			stmt.setString(18,phone);
			stmt.setString(19,emer_phone);
			stmt.setString(20,email);
			stmt.setString(21,in_user);
			stmt.setString(22, update_user);
			stmt.setString(23, partID);
			
			stmt.executeUpdate();
			
			connection.commit();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		modelMap.put("updateMassage", "Update sucessful !!!!");
		return "UpdateParticipant";
	}
}