package com.experian.finance.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.experian.finance.master.Contract;
import com.experian.finance.master.Slab;
import com.experian.finance.work.Output;

public class GetData {
	private static DatabaseConnection dbConnection;
	private static Connection connection;
	private Statement statement;
	List<Slab> normalSlabList = null;
	List<Slab> warmUpSlabList = null;

	private String legalEntity;
	private String businessunit;
	private String clientId;
	private String clientName;
	private String clientLocationNo;
	private String productId;
	
	public GetData() {
		insertObjInit();
	}

	public static void insertObjInit() {
		dbConnection = DatabaseConnection.getObject();
		connection = dbConnection.getConnection();

		try {
			connection.setAutoCommit(false);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Slab> getSlab() {
		List<Slab> slabList = new ArrayList<Slab>();

		ResultSet resultSetSlab = null;
		String selectSlab = "select LEGAL_ENTITY,BUSINESS_UNIT,CLIENT_ID,CLIENT_LOCATION_NO,PRODUCT_ID,CLIENT_NAME,SLAB_NO,SLAB_CATEGORY,"
				+ "SLAB_TYPE,MIN_SLAB_VOLUME,MAX_SLAB_VOLUME,CHARGES,ACTIVE, SLAB_END_DATE from FIN_CONTRACT_SLAB";
		try {
			if (connection.isClosed())
				connection = dbConnection.getConnection();

			statement = connection.createStatement();
			resultSetSlab = statement.executeQuery(selectSlab);
			System.out.println(resultSetSlab);
			while (resultSetSlab.next()) {
				Slab slab = new Slab();
				// Retrieve by column name
				slab.setLegalEntity(resultSetSlab.getString("LEGAL_ENTITY"));
				slab.setBusinessUnit(resultSetSlab.getString("BUSINESS_UNIT"));
				slab.setClientId(resultSetSlab.getInt("CLIENT_ID"));
				slab.setClientLocationNo(resultSetSlab
						.getString("CLIENT_LOCATION_NO"));
				slab.setProductId(resultSetSlab.getString("PRODUCT_ID"));
				slab.setProductName(resultSetSlab.getString("CLIENT_NAME"));
				slab.setSlabNo(resultSetSlab.getInt("SLAB_NO"));
				slab.setSlabCategory(resultSetSlab.getString("SLAB_CATEGORY"));
				slab.setSlabType(resultSetSlab.getString("SLAB_TYPE"));
				slab.setMinSlabVolume(resultSetSlab.getLong("MIN_SLAB_VOLUME"));
				slab.setMaxSlabVolume(resultSetSlab.getLong("MAX_SLAB_VOLUME"));
				slab.setCharges(resultSetSlab.getFloat("CHARGES"));
				slab.setActive(resultSetSlab.getInt("ACTIVE"));
				slab.setSlabEndDate(resultSetSlab
						.getDate("SLAB_END_DATE"));
				slabList.add(slab);
			}
			resultSetSlab.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return slabList;
	}

	
	public List<Contract> getContract() {
		List<Contract> contractList = new ArrayList<Contract>();

		ResultSet resultSetContract = null;

		List<Slab> listSlab = getSlab();
		String selectContract = "select LEGAL_ENTITY,BUSINESS_UNIT,CLIENT_ID,CLIENT_LOCATION_NO,CLIENT_NAME,PRODUCT_ID,"
				+ "PRODUCT_NAME,CATEGORY,CONTRACT_START_DATE,CONTRACT_END_DATE,"
				+ "AR_PROCESS_DATE,ONE_TIME_SETUP_FLAG,ONE_TIME_SETUP_CHARGE_DATE,ONE_TIME_SETUP_FEE,ADDITIONAL_SERVICE_CHARGES,EARLY_TERMINATION_FLAG,"
				+ "EARLY_TERMINATION_DATE,EARLY_TERMINATION_MINIMUM_VOLUME,PRO_RATA_CHARGES_FOR_TERMINATION,NO_MONTHS_OF_COMMITMENT,"
				+ "COMMITMENT_QTY,DISCOUNT_RATE_FLAG,DISCOUNT_START_DATE,DISCOUNT_END_DATE,DISCOUNT_RATE,DISCOUNT_EXTENSION_FLAG,"
				+ "DISCOUNT_EXTENSION_END_DATE,SPECIAL_RATE_FLAG,SPECIAL_START_DATE,SPECIAL_END_DATE,SPECIAL_RATE,WARM_UP_FLAG,WARM_UP_START_DATE,"
				+ "WARM_UP_END_DATE,WARM_UP_EXTENSION_FLAG,WARM_UP_EXTENSION_END_DATE,MONTHLY_SLAB_FLAG,MIN_MONTHLY_SLAB_VOLUME,MAX_MONTHLY_SLAB_VOLUME,"
				+ "MONTHLY_RATE,QUARTERLY_SLAB_FLAG,MIN_QUARTERLY_SLAB_VOLUME,MAX_QUARTERLY_SLAB_VOLUME,QUARTERLY_RATE,SEMI_ANNUAL_SLAB_FLAG,"
				+ "MIN_SEMI_ANNUAL_SLAB_VOLUME,MAX_SEMI_ANNUAL_SLAB_VOLUME,SEMI_ANNUAL_SLAB_RATE,ANNUAL_SLAB_FLAG,"
				+ "MIN_ANNUAL_SLAB_VOLUME,MAX_ANNUAL_SLAB_VOLUME,ANNUAL_SLAB_RATE,PROCESSED_VOLUME_TILL_DATE,PROCESSED_VOLUME_QUARTER,"
				+ "PROCESSED_VOLUME_SEMI_ANNUAL,PROCESSED_VOLUME_ANNUAL,PROCESSED_VOLUME_UPDATE_DATE from FIN_CONTRACT";

		try {
			if (connection.isClosed())
				connection = dbConnection.getConnection();

			statement = connection.createStatement();
			resultSetContract = statement.executeQuery(selectContract);
			System.out.println(resultSetContract);
			while (resultSetContract.next()) {
				normalSlabList = new ArrayList<Slab>();
				warmUpSlabList = new ArrayList<Slab>();
				Contract contract = new Contract();
				contract.setLegalEntity(resultSetContract
						.getString("LEGAL_ENTITY"));
				contract.setBusinessUnit(resultSetContract
						.getString("BUSINESS_UNIT"));
				contract.setClientId(resultSetContract.getInt("CLIENT_ID"));
				contract.setClientLocationNo(resultSetContract
						.getString("CLIENT_LOCATION_NO"));
				contract.setClientName(resultSetContract
						.getString("CLIENT_NAME"));
				contract.setProductId(resultSetContract.getString("PRODUCT_ID"));
				contract.setProductName(resultSetContract
						.getString("PRODUCT_NAME"));
				contract.setCategory(resultSetContract.getString("CATEGORY"));
				contract.setContractStartDate(resultSetContract
						.getDate("CONTRACT_START_DATE"));
				contract.setContractEndDate(resultSetContract
						.getDate("CONTRACT_END_DATE"));
				contract.setArProcessDate(resultSetContract
						.getDate("AR_PROCESS_DATE"));
				contract.setOneTimeSetupFlag(resultSetContract
						.getString("ONE_TIME_SETUP_FLAG"));
				contract.setOneTimeSetupChargeDate(resultSetContract
						.getDate("ONE_TIME_SETUP_CHARGE_DATE"));
				contract.setOneTimeSetupFee(resultSetContract
						.getFloat("ONE_TIME_SETUP_FEE"));
				contract.setAdditionalServiceCharges(resultSetContract
						.getFloat("ADDITIONAL_SERVICE_CHARGES"));
				contract.setEarlyTerminationFlag(resultSetContract
						.getString("EARLY_TERMINATION_FLAG"));
				contract.setEarlyTerminationDate(resultSetContract
						.getDate("EARLY_TERMINATION_DATE"));
				contract.setEarlyTerminationMinimumVolume(resultSetContract
						.getLong("EARLY_TERMINATION_MINIMUM_VOLUME"));
				contract.setProrataCharges4Termination(resultSetContract
						.getFloat("PRO_RATA_CHARGES_FOR_TERMINATION"));
				contract.setNoMonthsOfCommitment(resultSetContract
						.getInt("NO_MONTHS_OF_COMMITMENT"));
				contract.setCommitmentQty(resultSetContract
						.getInt("COMMITMENT_QTY"));
				contract.setDiscountRateFlag(resultSetContract
						.getString("DISCOUNT_RATE_FLAG"));
				contract.setDiscountStartDate(resultSetContract
						.getDate("DISCOUNT_START_DATE"));
				contract.setDiscountEndDate(resultSetContract
						.getDate("DISCOUNT_END_DATE"));
				contract.setDiscountRate(resultSetContract
						.getFloat("DISCOUNT_RATE"));
				contract.setDiscountExtensionFlag(resultSetContract
						.getString("DISCOUNT_EXTENSION_FLAG"));
				contract.setDiscountExtensionEndDate(resultSetContract
						.getDate("DISCOUNT_EXTENSION_END_DATE"));
				contract.setSpecialRateFlag(resultSetContract
						.getString("SPECIAL_RATE_FLAG"));
				contract.setSpecialStartDate(resultSetContract
						.getDate("SPECIAL_START_DATE"));
				contract.setSpecialEndDate(resultSetContract
						.getDate("SPECIAL_END_DATE"));
				contract.setSpecialRate(resultSetContract
						.getFloat("SPECIAL_RATE"));
				contract.setWarmupFlag(resultSetContract
						.getString("WARM_UP_FLAG"));
				contract.setWarmupStartDate(resultSetContract
						.getDate("WARM_UP_START_DATE"));
				contract.setWarmupEndDate(resultSetContract
						.getDate("WARM_UP_END_DATE"));
				contract.setWarmupExtensionFlag(resultSetContract
						.getString("WARM_UP_EXTENSION_FLAG"));
				contract.setWarmupExtensionEndDate(resultSetContract
						.getDate("WARM_UP_EXTENSION_END_DATE"));
				contract.setMonthlySlabFlag(resultSetContract
						.getString("MONTHLY_SLAB_FLAG"));
				contract.setMinmonthlySlabVolume(resultSetContract
						.getLong("MIN_MONTHLY_SLAB_VOLUME"));
				contract.setMaxmonthlySlabVolume(resultSetContract
						.getLong("MAX_MONTHLY_SLAB_VOLUME"));
				contract.setMonthlyRate(resultSetContract
						.getFloat("MONTHLY_RATE"));
				contract.setQuarterlySlabFlag(resultSetContract
						.getString("QUARTERLY_SLAB_FLAG"));
				contract.setMinQuarterlySlabVolume(resultSetContract
						.getLong("MIN_QUARTERLY_SLAB_VOLUME"));
				contract.setMaxQuarterlySlabVolume(resultSetContract
						.getLong("MAX_QUARTERLY_SLAB_VOLUME"));
				contract.setQuarterlyRate(resultSetContract
						.getFloat("QUARTERLY_RATE"));
				contract.setSemiAnnualSlabFlag(resultSetContract
						.getString("SEMI_ANNUAL_SLAB_FLAG"));
				contract.setMinSemiAnnualSlabVolume(resultSetContract
						.getLong("MIN_SEMI_ANNUAL_SLAB_VOLUME"));
				contract.setMaxSemiAnnualSlabVolume(resultSetContract
						.getLong("MAX_SEMI_ANNUAL_SLAB_VOLUME"));
				contract.setSemiAnnualSlabRate(resultSetContract
						.getFloat("SEMI_ANNUAL_SLAB_RATE"));
				contract.setAnnualSlabFlag(resultSetContract
						.getString("ANNUAL_SLAB_FLAG"));
				contract.setMinAnnualSlabVolume(resultSetContract
						.getLong("MIN_ANNUAL_SLAB_VOLUME"));
				contract.setMaxAnnualSlabVolume(resultSetContract
						.getLong("MAX_ANNUAL_SLAB_VOLUME"));
				contract.setAnnualSlabRate(resultSetContract
						.getFloat("ANNUAL_SLAB_RATE"));
				contract.setProcessedVolumeTillDate(resultSetContract
						.getLong("PROCESSED_VOLUME_TILL_DATE"));
				contract.setProcessedVolumeQuarter(resultSetContract
						.getLong("PROCESSED_VOLUME_QUARTER"));
				contract.setProcessedVolumeSemiAnnual(resultSetContract
						.getLong("PROCESSED_VOLUME_SEMI_ANNUAL"));
				contract.setProcessedVolumeAnnual(resultSetContract
						.getLong("PROCESSED_VOLUME_ANNUAL"));
				contract.setProcessedVolumeUpdateDate(resultSetContract
						.getDate("PROCESSED_VOLUME_UPDATE_DATE"));

				for (Slab slab : listSlab) {
					if (StringUtils.equals(contract.getProductId(),
							slab.getProductId())
							&& contract.getClientId()
									.equals(slab.getClientId())
							&& StringUtils.equals(
									contract.getClientLocationNo(),
									slab.getClientLocationNo())
							&& StringUtils.equals(contract.getLegalEntity(),
									slab.getLegalEntity())
							&& StringUtils.equals(contract.getBusinessUnit(),
									slab.getBusinessUnit())) {
						if (slab.getSlabCategory().equalsIgnoreCase("NORMAL")) {
							normalSlabList.add(slab);
						} else if (slab.getSlabCategory().equalsIgnoreCase(
								"WARM UP")) {
							warmUpSlabList.add(slab);
						}
					}
				}
				if (!normalSlabList.isEmpty())
					contract.setNormalSlabList(normalSlabList);
				if (!warmUpSlabList.isEmpty()) {
					contract.setWarmSlabList(warmUpSlabList);
				}
				contractList.add(contract);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contractList;
	}

	public List<Output> getOutput(String clientId, String clientName,String month, String year) {
		List<Output> outputList = new ArrayList<Output>();

		final String STR_BLANK = "";
		final String STR_NULL="null";
		final String STR_NOTSELECTED = "NotSelected";
		
		ResultSet resultSetOutput = null;
		
		String whereStr="";
		
		if((!clientName.contains(STR_NULL)) && (!clientName.equalsIgnoreCase(STR_BLANK) && !clientName.equalsIgnoreCase(STR_NOTSELECTED))){
			whereStr +=  (whereStr == "") ? " WHERE " + "CLIENT_NAME = '" + clientName +"'": " AND " + "CLIENT_NAME = '" + clientName+"'";
		}
		if((!clientId.contains(STR_NULL)) && (!clientId.equalsIgnoreCase(STR_BLANK) && !clientId.equalsIgnoreCase(STR_NOTSELECTED)))
		{
			whereStr +=  (whereStr == "") ? " WHERE " + "CLIENT_ID = " + clientId : " AND " + "CLIENT_ID= " + clientId;
		}
		if((!month.contains(STR_NULL)) && (!month.equalsIgnoreCase(STR_BLANK) && !month.equalsIgnoreCase(STR_NOTSELECTED)))
		{
			whereStr +=  (whereStr == "") ? " WHERE " + "month(PROCESS_DATE) = " + month : " AND " + "month(PROCESS_DATE) = " + month;
		}
		if((!year.contains(STR_NULL)) && (!year.equalsIgnoreCase(STR_BLANK) && !year.equalsIgnoreCase(STR_NOTSELECTED)))
		{
			whereStr +=  (whereStr == "") ? " WHERE " + "year(PROCESS_DATE) = " + year : " AND " + "year(PROCESS_DATE) = " + year;
		}
		
		String selectOutput = "select SR_NO,BUSINESS_UNIT,CLIENT_NAME,CLIENT_ID,CLIENT_LOCATION_NO,"
				+ "NICK_NAME,TRANSACTION_TYPE,CHARGES,PRODUCT_ID,QUANTITY,RATE,"
				+ "AMOUNT,DEFAULT_TEXT,REFERENCE_NO,LINE,PROCESS_DATE from FIN_OUTPUT "+whereStr;

		System.out.println("selectOutput "+selectOutput);
		try {
			if (connection.isClosed())
				connection = dbConnection.getConnection();

			statement = connection.createStatement();
			resultSetOutput = statement.executeQuery(selectOutput);
			System.out.println(resultSetOutput.toString());
			while (resultSetOutput.next()) {
				Output output = new Output();
				// Retrieve by column name
				output.setSrNumber(resultSetOutput.getInt("SR_NO"));
				output.setBusinessUnit(resultSetOutput
						.getString("BUSINESS_UNIT"));
				output.setClientName(resultSetOutput.getString("CLIENT_NAME"));
				output.setClientId(resultSetOutput.getInt("CLIENT_ID"));
				output.setClientLocationNo(resultSetOutput
						.getString("CLIENT_LOCATION_NO"));
				output.setNickName(resultSetOutput.getString("NICK_NAME"));
				output.setTransactionType(resultSetOutput
						.getString("TRANSACTION_TYPE"));
				output.setCharges(resultSetOutput.getString("CHARGES"));
				output.setProductId(resultSetOutput.getString("PRODUCT_ID"));
				output.setQuantity(resultSetOutput.getLong("QUANTITY"));
				output.setRate(resultSetOutput.getFloat("RATE"));
				output.setAmount(resultSetOutput.getFloat("AMOUNT"));
				output.setDefaultText(resultSetOutput.getString("DEFAULT_TEXT"));
				output.setReferenceNo(resultSetOutput.getString("REFERENCE_NO"));
				output.setLine(resultSetOutput.getString("LINE"));
				output.setProcessDate(resultSetOutput.getDate("PROCESS_DATE"));

				outputList.add(output);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return outputList;
	}

	public static synchronized String getOutputRefSeq() {
		String refNo = "";
		ResultSet resultSetOutput = null;
		String selectOutput = "select SEQ_FIN_OUTPUT_REF_NO.NEXTVAL FROM FIN_OUTPUT";
		Statement statement = null;

		try {
			if (connection.isClosed())
				connection = dbConnection.getConnection();

			statement = connection.createStatement();
			resultSetOutput = statement.executeQuery(selectOutput);
			while (resultSetOutput.next() && refNo.length() == 0) {
				refNo = resultSetOutput.getString(1);
			}

			resultSetOutput.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != statement) {
					statement.close();
				}
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return refNo;
	}

	public List<Map<String, Object>> getSearchContract(String legalEntity, String businessunit,
			String clientId, String clientLocationNo, String productId, String clientName) {
		
		final String STR_BLANK = "";
		final String STR_NULL="null";
		final String STR_NOTSELECTED = "NotSelected";
		
		List<Map<String, Object>> contractList = new ArrayList<Map<String,Object>>();
		ResultSet resultSetSearchContract = null;
		
		String whereStr="";
		
		System.out.println(legalEntity+businessunit+clientId+clientName+clientLocationNo+productId);
		
		if((!legalEntity.contains(STR_NULL)) && !legalEntity.equalsIgnoreCase(STR_BLANK) && !legalEntity.equalsIgnoreCase(STR_NOTSELECTED)){
			whereStr += (whereStr == "") ? " WHERE " + " LEGAL_ENTITY = '"+legalEntity +"'": " AND " + " LEGAL_ENTITY = '"+legalEntity+"'";
		}
		if((!businessunit.contains(STR_NULL)) && (!businessunit.equalsIgnoreCase(STR_BLANK) && !businessunit.equalsIgnoreCase(STR_NOTSELECTED))){
			whereStr +=  (whereStr == "") ? " WHERE " + "BUSINESS_UNIT = '" + businessunit+"'": " AND " + "BUSINESS_UNIT = '" + businessunit+"'";
		}
		if((!clientId.contains(STR_NULL)) && (!clientId.equalsIgnoreCase(STR_BLANK) && !clientId.equalsIgnoreCase(STR_NOTSELECTED))){
			whereStr +=  (whereStr == "") ? " WHERE " + "CLIENT_ID = " + clientId : " AND " + "CLIENT_ID = " + clientId;
		}
		if((!clientName.contains(STR_NULL)) && (!clientName.equalsIgnoreCase(STR_BLANK) && !clientName.equalsIgnoreCase(STR_NOTSELECTED))){
			whereStr +=  (whereStr == "") ? " WHERE " + "CLIENT_NAME = '" + clientName +"'": " AND " + "CLIENT_NAME = '" + clientName+"'";
		}
		if((!clientLocationNo.contains(STR_NULL)) && (!clientLocationNo.equalsIgnoreCase(STR_BLANK) && !clientLocationNo.equalsIgnoreCase(STR_NOTSELECTED)))
		{
			whereStr +=  (whereStr == "") ? " WHERE " + "CLIENT_LOCATION_NO = '" + clientLocationNo +"'": " AND " + "CLIENT_LOCATION_NO = '" + clientLocationNo+"'";
		}
		if((!productId.contains(STR_NULL)) && (!productId.equalsIgnoreCase(STR_BLANK) && !productId.equalsIgnoreCase(STR_NOTSELECTED))){
			whereStr +=  (whereStr == "") ? " WHERE " + "PRODUCT_ID = '" + productId +"'": " AND " + "PRODUCT_ID = '" + productId+"'";
		}
		
		String selectContract = "select LEGAL_ENTITY,BUSINESS_UNIT,CLIENT_ID,CLIENT_LOCATION_NO,CLIENT_NAME,PRODUCT_ID,"
				+ "PRODUCT_NAME,CATEGORY,CONTRACT_START_DATE,CONTRACT_END_DATE,"
				+ "AR_PROCESS_DATE,ONE_TIME_SETUP_FLAG,ONE_TIME_SETUP_CHARGE_DATE,ONE_TIME_SETUP_FEE,ADDITIONAL_SERVICE_CHARGES,EARLY_TERMINATION_FLAG,"
				+ "EARLY_TERMINATION_DATE,EARLY_TERMINATION_MINIMUM_VOLUME,PRO_RATA_CHARGES_FOR_TERMINATION,NO_MONTHS_OF_COMMITMENT,"
				+ "COMMITMENT_QTY,DISCOUNT_RATE_FLAG,DISCOUNT_START_DATE,DISCOUNT_END_DATE,DISCOUNT_RATE,DISCOUNT_EXTENSION_FLAG,"
				+ "DISCOUNT_EXTENSION_END_DATE,SPECIAL_RATE_FLAG,SPECIAL_START_DATE,SPECIAL_END_DATE,SPECIAL_RATE,WARM_UP_FLAG,WARM_UP_START_DATE,"
				+ "WARM_UP_END_DATE,WARM_UP_EXTENSION_FLAG,WARM_UP_EXTENSION_END_DATE,MONTHLY_SLAB_FLAG,MIN_MONTHLY_SLAB_VOLUME,MAX_MONTHLY_SLAB_VOLUME,"
				+ "MONTHLY_RATE,QUARTERLY_SLAB_FLAG,MIN_QUARTERLY_SLAB_VOLUME,MAX_QUARTERLY_SLAB_VOLUME,QUARTERLY_RATE,SEMI_ANNUAL_SLAB_FLAG,"
				+ "MIN_SEMI_ANNUAL_SLAB_VOLUME,MAX_SEMI_ANNUAL_SLAB_VOLUME,SEMI_ANNUAL_SLAB_RATE,ANNUAL_SLAB_FLAG,"
				+ "MIN_ANNUAL_SLAB_VOLUME,MAX_ANNUAL_SLAB_VOLUME,ANNUAL_SLAB_RATE,PROCESSED_VOLUME_TILL_DATE,PROCESSED_VOLUME_QUARTER,"
				+ "PROCESSED_VOLUME_SEMI_ANNUAL,PROCESSED_VOLUME_ANNUAL,PROCESSED_VOLUME_UPDATE_DATE from FIN_CONTRACT"+whereStr;
		
		try {
			if (connection.isClosed())
				connection = dbConnection.getConnection();

				PreparedStatement statement = connection.prepareStatement(selectContract);
				
				resultSetSearchContract=statement.executeQuery();
				
				ResultSetMetaData resultSetMetaData = resultSetSearchContract.getMetaData();	
				while (resultSetSearchContract.next()) {
					Map<String, Object> contract_map = new HashMap<String, Object>();
					      int numColumns = resultSetMetaData.getColumnCount();
					      
					      for (int i=1; i<numColumns+1; i++) {
					        String column_name = resultSetMetaData.getColumnName(i);

					        if(resultSetMetaData.getColumnType(i)==java.sql.Types.ARRAY){
					         contract_map.put(column_name, resultSetSearchContract.getArray(column_name));
					        }
					        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.BIGINT){
					         contract_map.put(column_name, resultSetSearchContract.getLong(column_name));
					        }
					        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.BOOLEAN){
					         contract_map.put(column_name, resultSetSearchContract.getBoolean(column_name));
					        }
					        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.BLOB){
					         contract_map.put(column_name, resultSetSearchContract.getBlob(column_name));
					        }
					        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.DOUBLE){
					         contract_map.put(column_name, resultSetSearchContract.getDouble(column_name)); 
					        }
					        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.FLOAT){
					         contract_map.put(column_name, resultSetSearchContract.getFloat(column_name));
					        }
					        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.INTEGER){
					         contract_map.put(column_name, resultSetSearchContract.getInt(column_name));
					        }
					        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.NVARCHAR){
					         contract_map.put(column_name, resultSetSearchContract.getNString(column_name));
					        }
					        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.VARCHAR){
					         contract_map.put(column_name, resultSetSearchContract.getString(column_name));
					        }
					        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.TINYINT){
					         contract_map.put(column_name, resultSetSearchContract.getInt(column_name));
					        }
					        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.SMALLINT){
					         contract_map.put(column_name, resultSetSearchContract.getInt(column_name));
					        }
					        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.DATE){
					         contract_map.put(column_name, resultSetSearchContract.getDate(column_name));
					        }
					        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.TIMESTAMP){
					        contract_map.put(column_name, resultSetSearchContract.getTimestamp(column_name));   
					        }
					        else{
					         contract_map.put(column_name, resultSetSearchContract.getObject(column_name));
					        }
					      }
					int count= resultSetMetaData.getColumnCount();
					for(int i = 1; i<=count; i++)
					{
						System.out.println("column_name :"+resultSetMetaData.getColumnName(i) + " Column_value :"+resultSetSearchContract.getString(resultSetMetaData.getColumnName(i)));
					}
					contractList.add(contract_map);
				}
			//	resultSetSlab.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return contractList;
		
	}

	public List<Map<String, Object>> getSearchSlab(String legalEntity,
			String businessunit, String clientId, String clientName,
			String clientLocationNo, String productId) {
		final String STR_BLANK = "";
		final String STR_NULL="null";
		final String STR_NOTSELECTED = "NotSelected";
		
		
		List<Map<String, Object>> slabList = new ArrayList<Map<String,Object>>();
		
		ResultSet resultSetSlab = null;
		
		String whereStr="";
		
		System.out.println(legalEntity+businessunit+clientId+clientName+clientLocationNo+productId);
		
		if((!legalEntity.contains(STR_NULL)) && !legalEntity.equalsIgnoreCase(STR_BLANK) && !legalEntity.equalsIgnoreCase(STR_NOTSELECTED)){
			whereStr += (whereStr == "") ? " WHERE " + " LEGAL_ENTITY = '"+legalEntity +"'": " AND " + " LEGAL_ENTITY = '"+legalEntity+"'";
		}
		if((!businessunit.contains(STR_NULL)) && (!businessunit.equalsIgnoreCase(STR_BLANK) && !businessunit.equalsIgnoreCase(STR_NOTSELECTED))){
			whereStr +=  (whereStr == "") ? " WHERE " + "BUSINESS_UNIT = '" + businessunit+"'": " AND " + "BUSINESS_UNIT = '" + businessunit+"'";
		}
		if((!clientId.contains(STR_NULL)) && (!clientId.equalsIgnoreCase(STR_BLANK) && !clientId.equalsIgnoreCase(STR_NOTSELECTED))){
			whereStr +=  (whereStr == "") ? " WHERE " + "CLIENT_ID = " + clientId : " AND " + "CLIENT_ID = " + clientId;
		}
		if((!clientName.contains(STR_NULL)) && (!clientName.equalsIgnoreCase(STR_BLANK) && !clientName.equalsIgnoreCase(STR_NOTSELECTED))){
			whereStr +=  (whereStr == "") ? " WHERE " + "CLIENT_NAME = '" + clientName +"'": " AND " + "CLIENT_NAME = '" + clientName+"'";
		}
		if((!clientLocationNo.contains(STR_NULL)) && (!clientLocationNo.equalsIgnoreCase(STR_BLANK) && !clientLocationNo.equalsIgnoreCase(STR_NOTSELECTED)))
		{
			whereStr +=  (whereStr == "") ? " WHERE " + "CLIENT_LOCATION_NO = '" + clientLocationNo +"'": " AND " + "CLIENT_LOCATION_NO = '" + clientLocationNo+"'";
		}
		if((!productId.contains(STR_NULL)) && (!productId.equalsIgnoreCase(STR_BLANK) && !productId.equalsIgnoreCase(STR_NOTSELECTED))){
			whereStr +=  (whereStr == "") ? " WHERE " + "PRODUCT_ID = '" + productId +"'": " AND " + "PRODUCT_ID = '" + productId+"'";
		}
		
		String selectSlab = "select LEGAL_ENTITY,BUSINESS_UNIT,CLIENT_ID,CLIENT_LOCATION_NO,PRODUCT_ID,CLIENT_NAME,SLAB_NO,SLAB_CATEGORY,"
				+ "SLAB_TYPE,MIN_SLAB_VOLUME,MAX_SLAB_VOLUME,CHARGES,ACTIVE, SLAB_END_DATE from FIN_CONTRACT_SLAB"+whereStr;
			
		//System.out.println("select : "+selectSlab);		
		
		try {
			if (connection.isClosed())
				connection = dbConnection.getConnection();
			
			PreparedStatement statement= connection.prepareStatement(selectSlab);
					
			resultSetSlab = statement.executeQuery();
			//System.out.println(resultSetSlab);
						
			ResultSetMetaData resultSetMetaData = resultSetSlab.getMetaData();	
			while (resultSetSlab.next()) {
				Map<String, Object> slab_map = new HashMap<String, Object>();
				      int numColumns = resultSetMetaData.getColumnCount();
				      
				      for (int i=1; i<numColumns+1; i++) {
				        String column_name = resultSetMetaData.getColumnName(i);

				        if(resultSetMetaData.getColumnType(i)==java.sql.Types.ARRAY){
				         slab_map.put(column_name, resultSetSlab.getArray(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.BIGINT){
				         slab_map.put(column_name, resultSetSlab.getLong(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.BOOLEAN){
				         slab_map.put(column_name, resultSetSlab.getBoolean(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.BLOB){
				         slab_map.put(column_name, resultSetSlab.getBlob(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.DOUBLE){
				         slab_map.put(column_name, resultSetSlab.getDouble(column_name)); 
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.FLOAT){
				         slab_map.put(column_name, resultSetSlab.getFloat(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.INTEGER){
				         slab_map.put(column_name, resultSetSlab.getInt(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.NVARCHAR){
				         slab_map.put(column_name, resultSetSlab.getNString(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.VARCHAR){
				         slab_map.put(column_name, resultSetSlab.getString(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.TINYINT){
				         slab_map.put(column_name, resultSetSlab.getInt(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.SMALLINT){
				         slab_map.put(column_name, resultSetSlab.getInt(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.DATE){
				         slab_map.put(column_name, resultSetSlab.getDate(column_name));
				        }
				        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.TIMESTAMP){
				        slab_map.put(column_name, resultSetSlab.getTimestamp(column_name));   
				        }
				        else{
				         slab_map.put(column_name, resultSetSlab.getObject(column_name));
				        }
				      }
				slabList.add(slab_map);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return slabList;
	}
	
		
	/*public boolean recordExist(String legalEntity, String businessUnit, int clientId , 
			String clientLocationNo, String productId ){
		boolean flag= false;
		PreparedStatement preparedStatement;
		ResultSet resultSet = null;
		String selectString = "select * from FIN_CONTRACT"
				+ " where LEGAL_ENTITY = '"+legalEntity+"' AND BUSINESS_UNIT = '"+businessUnit+"' AND "
				+ "CLIENT_ID = "+clientId+" AND CLIENT_LOCATION_NO = '"+clientLocationNo+"' AND PRODUCT_ID ='"+productId+"'";
		
			try {
				if (connection.isClosed())
					connection = dbConnection.getConnection();
			

			preparedStatement = connection.prepareStatement(selectString);
						
			resultSet = preparedStatement.executeQuery();
			
			flag = resultSet.next();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return flag;
		
	}*/
	

	
public boolean recordExist(String selectString){
		boolean flag= false;
		PreparedStatement preparedStatement;
		ResultSet resultSet = null;
		
			try {
				if (connection.isClosed())
					connection = dbConnection.getConnection();
			

			preparedStatement = connection.prepareStatement(selectString);
						
			resultSet = preparedStatement.executeQuery();
			
			flag = resultSet.next();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return flag;
		
	}

}
