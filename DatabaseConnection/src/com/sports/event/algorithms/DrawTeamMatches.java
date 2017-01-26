package com.sports.event.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sports.event.generics.DataBaseCalls;

public class DrawTeamMatches {

	public static void main(String args[]){
		DeriveNoOfMatches(1,"1");
	}
	
	public static void DeriveNoOfMatches(int gameId,String round_Type){

		DataBaseCalls dbCalls = new DataBaseCalls();
		
		// ## Go in games table and search for respective Game Type
		//String gameType = database call;
		//String gameType = "TEAM";
		String gameType = "IND";
		int participatsCount = 0;
		int lowerLimit = 0;
		int extraParticipant = 0;
		int totalMatches = 0;
		//int extraLowerLimit = 0;
		
		//boolean finalMatchCount =false;
		if(gameType.equalsIgnoreCase("IND")){
			// ## Go in PART_GAME Table and get 
			participatsCount = dbCalls.getCount();
		}else if(gameType.equalsIgnoreCase("TEAM")){
			// ## Go in PART_GAME Table and get 
			participatsCount = dbCalls.getCount();
		}
		
		System.out.println("Toatal Participant :" + participatsCount);
//		int c = 1;
		
//		int totalLowerLimit = 0;
//		while(!finalMatchCount){
			
			lowerLimit = getMatchCount(participatsCount);
			System.out.println("Lower Limit  : " + lowerLimit);
			
//			totalLowerLimit += lowerLimit;
//			System.out.println("TotalLowerLimit  : -> At Iteration"  + c + "...." + totalLowerLimit);
			
			if(participatsCount>lowerLimit){
				extraParticipant = participatsCount - lowerLimit;
				System.out.println("totalMatches at Round 0 (extraParticipant) : " + extraParticipant);
				
			}
			
//			if(extraParticipant == 0 || extraParticipant == 1){
//				finalMatchCount = true;
//			}
//			System.out.println("extraParticipant : -> At Iteration" + c + "...." + extraParticipant);
			
//			participatsCount=extraParticipant;
//			c++;
//		}
			
		totalMatches = lowerLimit / 2;
		if(extraParticipant ==1){
			totalMatches++;
		}
		
				
		System.out.println("totalMatches at Round 1 :" + totalMatches);

	}
	
	
	private static int getMatchCount(int participatsCount){

		int lhs = 2;
		int rhs = 0;
		int lowerLimit = 0;
		
		while(participatsCount > rhs){
			rhs = lhs * 2;				
			if(participatsCount >= lhs && participatsCount < rhs){
				lowerLimit = lhs;
				//System.out.println("Lower Limit is :" + lowerLimit);
				break;
			}		
			lhs = rhs;
		}
		return lowerLimit;
	}
	
	private static Map<Integer,String> getMatchSchedule(ArrayList participantList,int TotalMatches){
		
		Map<Integer,String> matchParticipantPair = new HashMap<Integer, String>();
		
		for(int i =0 ; i< TotalMatches;i++){
			
			participantList.get(i);
			participantList.get(i+TotalMatches);
			
			matchParticipantPair.put(i+1, String.valueOf(participantList.get(i))+","+String.valueOf(participantList.get(i+TotalMatches)));
		}
		
		return matchParticipantPair;
		
		
	}
	
}
