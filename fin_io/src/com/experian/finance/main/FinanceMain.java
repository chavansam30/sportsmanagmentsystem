package com.experian.finance.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.experian.finance.dto.DatabaseConnection;
import com.experian.finance.dto.GetData;
import com.experian.finance.work.Input;
import com.experian.finance.work.Output;

public class FinanceMain {
	
	static List<Input> inputColl=new ArrayList<Input>();
	
	static List<String> inputList=new ArrayList<String>();
	static List<Output> outputList=new ArrayList<Output>();
	
	public static void main(String[] args) {
		
		File inputFile= new File(args[0]);
	
		FinanceWorking financeWorking=new FinanceWorking();
	
		if(inputFile.exists())
		{
			System.out.println(inputFile + "File Exist");
		
			File fileNameObj=new File(inputFile.getName());
			System.out.println("fileNameObj "+ fileNameObj);
			
					try {
						FileReader fileReader =new FileReader(inputFile);
						BufferedReader bufferedReader= new BufferedReader(fileReader);
						String readerString=bufferedReader.readLine();
						
						// Skip first Line
						readerString=bufferedReader.readLine();
						while(null != readerString)
						{
							inputList.add(readerString);		
							readerString=bufferedReader.readLine();	
						}
						bufferedReader.close();
						fileReader.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					for(String str : inputList)
					{
						String[] splitArr = StringUtils.splitPreserveAllTokens(str, ',');
						if (splitArr.length < 10)
							continue;
						Input input=new Input(splitArr);
						inputColl.add(input);
					}
					
					outputList=financeWorking.processAndRegisterInput(inputColl);
					
		}	
		
		
		String outputPath=inputFile.getAbsolutePath();			
		outputPath= (String) inputFile.getAbsolutePath().subSequence(0, outputPath.lastIndexOf("\\"));
		File outputFile= new File(outputPath+"\\"+"out_" +inputFile.getName());
		
		String headings="SR_NO,BUSINESS_UNIT,CLIENT_NAME,CLIENT_ID,CLIENT_LOCATION_NO,NICK_NAME,TRANSACTION_TYPE"
				+ ",CHARGES,PRODUCT_ID,QUANTITY,RATE,AMOUNT,DEFAULT_TEXT,REFERENCE_NO,LINE,PROCESS_DATE";
		
	
		if(!outputFile.exists())
		{
			try {
				outputFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				FileWriter outputfileWriter= new FileWriter(outputFile.getAbsoluteFile(), true);
				BufferedWriter bufferedWriter= new BufferedWriter(outputfileWriter);
				bufferedWriter.write(headings+"\n");
				for (Input input : inputColl) {
					for (Output output : outputList) {
											
						if(input.getProductId().equalsIgnoreCase(output.getProductId())
								&& input.getLegalEntity().equalsIgnoreCase(output.getLegalEntity())
								&& input.getBusinessUnit().equalsIgnoreCase(output.getBusinessUnit())
								&& input.getClientName().equalsIgnoreCase(output.getClientName())
								&& input.getClientLocationNo().equalsIgnoreCase(output.getClientLocationNo())
								) {
							bufferedWriter.write(output.toString());
							bufferedWriter.newLine();
						}
					}			
				} 
				bufferedWriter.flush();
				bufferedWriter.close();
			}catch (IOException e) {
					e.printStackTrace();
			}
		}
		else {
			System.out.println(outputFile + " File already exists");
		}
			
	}			
	
	public boolean inputOutputFile(File file,String fieldValue)
	{
		boolean outfileFlag= false;
		List<Input> inputColl=new ArrayList<Input>();
		List<String> inputList=new ArrayList<String>();
		List<Output> outputList=new ArrayList<Output>();
		
		InputStream outputInStream = null;
		System.out.println("get Path : "+file.getAbsolutePath());
		System.out.println("File Name :"+file.getName());
		File inputFile= new File(file.getAbsolutePath());
		
		FinanceWorking financeWorking=new FinanceWorking();
		//FileWriter outputfileWriter = null;
		if(inputFile.exists())
		{
			System.out.println(inputFile + "File Exist");
		
			File fileNameObj=new File(inputFile.getName());
			System.out.println("fileNameObj "+ fileNameObj);
			
					try {
						FileReader fileReader =new FileReader(inputFile);
						BufferedReader bufferedReader= new BufferedReader(fileReader);
						String readerString=bufferedReader.readLine();
						
						// Skip first Line
						readerString=bufferedReader.readLine();
						while(null != readerString)
						{
							inputList.add(readerString);		
							readerString=bufferedReader.readLine();	
						}
						bufferedReader.close();
						fileReader.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					for(String str : inputList)
					{
						String[] splitArr = StringUtils.splitPreserveAllTokens(str, ',');
						if (splitArr.length < 10)
							continue;
						Input input=new Input(splitArr);
						inputColl.add(input);
					}
					
					if(null != fieldValue && fieldValue.equalsIgnoreCase("Cancel")){
						outputList =financeWorking.processInput(inputColl);
						System.out.println(outputList);
					}else if (null != fieldValue && fieldValue.equalsIgnoreCase("Register")) {
						outputList=financeWorking.processAndRegisterInput(inputColl);
					}
					
					
		}	
		
		
		String outputPath=inputFile.getAbsolutePath();
		System.out.println("outputPath ***** "+outputPath);
		outputPath= (String) inputFile.getAbsolutePath().substring(0, outputPath.lastIndexOf(File.separatorChar));
		System.out.println("outputPath "+outputPath);
		File outputFile= new File(outputPath+File.separatorChar+"out_" +inputFile.getName());
		System.out.println("outputFile : "+outputFile);
		String headings="SR_NO,BUSINESS_UNIT,CLIENT_NAME,CLIENT_ID,CLIENT_LOCATION_NO,NICK_NAME,TRANSACTION_TYPE"
				+ ",CHARGES,PRODUCT_ID,QUANTITY,RATE,AMOUNT,DEFAULT_TEXT,REFERENCE_NO,LINE,PROCESS_DATE";
		
	
		if(!outputFile.exists())
		{
			try {
				outputFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				FileWriter outputfileWriter= new FileWriter(outputFile.getAbsoluteFile(), true);
				
				BufferedWriter bufferedWriter= new BufferedWriter(outputfileWriter);
				bufferedWriter.write(headings+"\n");
				for (Input input : inputColl) {
					for (Output output : outputList) {
											
						if(input.getProductId().equalsIgnoreCase(output.getProductId())
								&& input.getLegalEntity().equalsIgnoreCase(output.getLegalEntity())
								&& input.getBusinessUnit().equalsIgnoreCase(output.getBusinessUnit())
								&& input.getClientName().equalsIgnoreCase(output.getClientName())
								&& input.getClientLocationNo().equalsIgnoreCase(output.getClientLocationNo())
								) {
							bufferedWriter.write(output.toString());
							bufferedWriter.newLine();
						}
					}			
				} 
				bufferedWriter.flush();
				bufferedWriter.close();
				
			}catch (IOException e) {
					e.printStackTrace();
			}
			outfileFlag = true;
		}
		else {
			try {
				outputFile.delete();
				outputFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				FileWriter outputfileWriter= new FileWriter(outputFile.getAbsoluteFile(), true);
				
				BufferedWriter bufferedWriter= new BufferedWriter(outputfileWriter);
				bufferedWriter.write(headings+"\n");
				for (Input input : inputColl) {
					for (Output output : outputList) {
											
						if(input.getProductId().equalsIgnoreCase(output.getProductId())
								&& input.getLegalEntity().equalsIgnoreCase(output.getLegalEntity())
								&& input.getBusinessUnit().equalsIgnoreCase(output.getBusinessUnit())
								&& input.getClientName().equalsIgnoreCase(output.getClientName())
								&& input.getClientLocationNo().equalsIgnoreCase(output.getClientLocationNo())
								) {
							bufferedWriter.write(output.toString());
							bufferedWriter.newLine();
						}
					}			
				} 
				bufferedWriter.flush();
				bufferedWriter.close();
				
			}catch (IOException e) {
					e.printStackTrace();
			}
			outfileFlag = true;
		}
		
		return outfileFlag;
	
	}
	
}