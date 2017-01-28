package com.sports.userinterface;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sports.algorithms.DrawTeamMatches;
import com.sports.event.generics.DataBaseCalls;

public class RulesUI {
	
	String selectedGameName;
	String selectedAge;
	String selectedGender;
	String CalculatedgameId ;
	DataBaseCalls dbCalls = null;
	DrawTeamMatches drawTeamMatches = null ;

	public static void main(String args[]){
		new RulesUI().drawInitialRound();
	}
	
	public void drawInitialRound(){
		
				
		Map<Integer,List<String>> map_GameDtl = new HashMap<Integer,List<String>>();

		drawTeamMatches = new DrawTeamMatches();
		
		
		try {
			dbCalls = new DataBaseCalls();
			map_GameDtl = dbCalls.getInitialRoundDetail();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    JFrame frame = new JFrame("Draw initial round.");
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(700, 500);
	    frame.setLocation(430, 100);

	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // added code

	    frame.add(panel);

	    JLabel lbl = new JLabel("Select one of the Game Name");
	    lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
	    //lbl.setVisible(true); // Not needed

	    panel.add(lbl);


	    String[] arr = null;
	    
	    arr  = map_GameDtl.get(1).toArray(new String[map_GameDtl.get(1).size()]);
	      final JComboBox<String> dd_gameName = new JComboBox<String>(arr);    
	    dd_gameName.setMaximumSize(dd_gameName.getPreferredSize()); // added code
	    dd_gameName.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
	    //cb.setVisible(true); // Not needed
	    panel.add(dd_gameName);
	    	      
	    arr  = map_GameDtl.get(2).toArray(new String[map_GameDtl.get(2).size()]);
	    final JComboBox<String> dd_Age = new JComboBox<String>(arr);    
	    dd_Age.setMaximumSize(dd_Age.getPreferredSize()); // added code
	    dd_Age.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
	    //cb.setVisible(true); // Not needed
	    panel.add(dd_Age);
	    System.out.println(dd_Age.getSelectedItem());
	    
	    arr  = map_GameDtl.get(3).toArray(new String[map_GameDtl.get(3).size()]);
	    final JComboBox<String> dd_Gender = new JComboBox<String>(arr);    
	    dd_Gender.setMaximumSize(dd_Gender.getPreferredSize()); // added code
	    dd_Gender.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
	    //cb.setVisible(true); // Not needed
	    panel.add(dd_Gender);
	    System.out.println(dd_Gender.getSelectedItem());

	    
	    /*JTABLE
	     * 
	     *  String[] columns = new String[] {
	            "Select Game Name", "Select Age", "Select Gender"
	        };
	         
	        //actual data for the table in a 2d array
	        Object[][] data = new Object[][] {
	            {dd_gameName, dd_Age, arr }
	        };
	 
	        //create table with data
	        JTable table = new JTable(data, columns);
	         
	        //add the table to the frame
	        this.add(new JScrollPane(table));
	         
	        this.setTitle("Table Example");
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
	        this.pack();
	        this.setVisible(true);
	     * 
	     * */	
	    
	    
       

	 
	        
	    JButton btnSubmit = new JButton("OK");
	    btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT); // added code
	    panel.add(btnSubmit);

	    ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        
	        	selectedGameName = String.valueOf(dd_gameName.getSelectedItem());
	        	selectedAge = String.valueOf(dd_Age.getSelectedItem());
	        	selectedGender = String.valueOf(dd_Gender.getSelectedItem());
	        	
	          System.out.println("Game Name: " + dd_gameName.getSelectedItem());	          
	          System.out.println("Age: " + dd_Age.getSelectedItem());
	          System.out.println("Gender: " + dd_Gender.getSelectedItem());
	 
	          try {
	        	  CalculatedgameId = String.valueOf(dbCalls.getGameID(selectedGameName, selectedAge, selectedGender));				
			} catch (ClassNotFoundException e) {				
				e.printStackTrace();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
	          System.out.println("CalculatedgameId:  " +CalculatedgameId);
	          drawTeamMatches.DeriveNoOfMatches(Integer.valueOf(CalculatedgameId) , "round_Type");
	          
	        }
	      };
	      btnSubmit.addActionListener(actionListener);

	      
	      
	    frame.setVisible(true); // added code

	}
	
	
	
}
