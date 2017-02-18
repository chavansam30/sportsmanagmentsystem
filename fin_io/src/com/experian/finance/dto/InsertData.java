package com.experian.finance.dto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

import com.experian.finance.master.Contract;
import com.experian.finance.master.Slab;
import com.experian.finance.work.Input;
import com.experian.finance.work.Output;

public class InsertData {
	private static DatabaseConnection dbConnection;
	private static Connection connection;
	private PreparedStatement preparedStatement1;
	private PreparedStatement preparedStatement2;
	private static final String STR_NULL = null;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public InsertData() {
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

	public void insertInput(Input input) {
		String insertString = "insert into FIN_INPUT(LEGAL_ENTITY,BUSINESS_UNIT,CLIENT_ID,CLIENT_LOCATION_NO,CLIENT_NAME,"
				+ "NICK_NAME,TRANSACTION_TYPE,CHARGES,PRODUCT_ID,QUANTITY,ACCOUNT_SETUP_FLAG,PROCESS_DATE,PROCESS_RESULT) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			if (connection.isClosed())
				connection = dbConnection.getConnection();
			preparedStatement1 = connection.prepareStatement(insertString);

			preparedStatement1.setString(1, input.getLegalEntity());
			preparedStatement1.setString(2, input.getBusinessUnit());
			preparedStatement1.setInt(3, input.getClientId());
			preparedStatement1.setString(4, input.getClientLocationNo());
			preparedStatement1.setString(5, input.getClientName());
			preparedStatement1.setString(6, input.getNickName());
			preparedStatement1.setString(7, input.getTransactionType());
			preparedStatement1.setLong(8, input.getCharges());
			preparedStatement1.setString(9, input.getProductId());
			preparedStatement1.setLong(10, input.getQuantity());
			preparedStatement1.setString(11, input.getAccountSetupFlag());
			Date date = new Date(input.getProcessDate().getTime());
			preparedStatement1.setString(12, sdf.format(date));
			preparedStatement1.setString(13, input.getProcessResult());

			preparedStatement1.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static String oldRef = null;
	private static String oldRefClientInfo = "";

	public void insertOutput(Output output) {
		String insertString = "insert into FIN_OUTPUT(BUSINESS_UNIT,CLIENT_NAME,CLIENT_ID,CLIENT_LOCATION_NO,"
				+ "NICK_NAME,TRANSACTION_TYPE,CHARGES,PRODUCT_ID,QUANTITY,RATE,AMOUNT,DEFAULT_TEXT,"
				+ "REFERENCE_NO,LINE,PROCESS_DATE) values ("
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		if (oldRefClientInfo.equalsIgnoreCase(output.getBusinessUnit() + "_"
				+ output.getLegalEntity() + "_" + output.getClientId() + "_"
				+ output.getClientLocationNo())) {
			// Do Nothing
		} else {
			oldRefClientInfo = output.getBusinessUnit() + "_"
					+ output.getLegalEntity() + "_" + output.getClientId()
					+ "_" + output.getClientLocationNo();
			oldRef = GetData.getOutputRefSeq();
		}
		
		if (oldRef.length() == 0){
			oldRef = GetData.getOutputRefSeq();
		}

		output.setReferenceNo(oldRef);

		try {
			if (connection.isClosed())
				connection = dbConnection.getConnection();

			preparedStatement2 = connection.prepareStatement(insertString);

			preparedStatement2.setString(1, output.getBusinessUnit());
			preparedStatement2.setString(2, output.getClientName());
			preparedStatement2.setInt(3, output.getClientId());
			preparedStatement2.setString(4, output.getClientLocationNo());
			preparedStatement2.setString(5, output.getNickName());
			preparedStatement2.setString(6, output.getTransactionType());
			preparedStatement2.setString(7, output.getCharges());
			preparedStatement2.setString(8, output.getProductId());
			preparedStatement2.setLong(9, output.getQuantity());
			preparedStatement2.setFloat(10, output.getRate());
			preparedStatement2.setFloat(11, output.getAmount());
			preparedStatement2.setString(12, output.getDefaultText());
			preparedStatement2.setString(13, output.getReferenceNo());
			preparedStatement2.setString(14, output.getLine());
			if (null != output.getProcessDate()) {
				Date processDate = new Date(output.getProcessDate().getTime());
				preparedStatement2.setString(15, sdf.format(processDate));
			} else {
				preparedStatement2.setString(15, STR_NULL);
			}

			preparedStatement2.executeUpdate();

			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateContract(String setupFlag, Contract contract) {
		try {
			if (connection.isClosed())
				connection = dbConnection.getConnection();
			PreparedStatement preparedStatement = null;
			if (setupFlag.equalsIgnoreCase("ONE_TIME_SETUP")) {

				String updateString = "update FIN_CONTRACT set ONE_TIME_SETUP_FLAG=?,ONE_TIME_SETUP_CHARGE_DATE=?,"
						+ "PROCESSED_VOLUME_TILL_DATE=?,PROCESSED_VOLUME_UPDATE_DATE=? "
						+ "where CLIENT_ID = ? AND CLIENT_LOCATION_NO = ? AND PRODUCT_ID = ? ";

				preparedStatement = connection
						.prepareStatement(updateString);

				if (null != contract.getOneTimeSetupFee()
						&& null != contract.getOneTimeSetupFlag()) {
					preparedStatement.setString(1,
							contract.getOneTimeSetupFlag());
					if (null != contract.getOneTimeSetupChargeDate()) {
						Date contractOneTimeSetupChargeDate = new Date(contract
								.getOneTimeSetupChargeDate().getTime());
						preparedStatement.setString(2,
								sdf.format(contractOneTimeSetupChargeDate));
					} else {
						preparedStatement.setString(2, STR_NULL);
					}
				} else {
					preparedStatement.setString(1, null);
					if (null != contract.getOneTimeSetupChargeDate()) {
						Date contractOneTimeSetupChargeDate = new Date(contract
								.getOneTimeSetupChargeDate().getTime());
						preparedStatement.setString(2,
								sdf.format(contractOneTimeSetupChargeDate));
					} else {
						preparedStatement.setString(2, STR_NULL);
					}
					preparedStatement.setFloat(3, -1);
				}
				if (null != contract.getProcessedVolumeTillDate()) {
					preparedStatement.setLong(4,
							contract.getProcessedVolumeTillDate());
				} else {
					preparedStatement.setInt(4, 0);
				}
				if (null != contract.getProcessedVolumeUpdateDate()) {
					Date contractProcessedVolumeUpdateDate = new Date(contract
							.getProcessedVolumeUpdateDate().getTime());
					preparedStatement.setString(5,
							sdf.format(contractProcessedVolumeUpdateDate));
				} else {
					preparedStatement.setString(5, STR_NULL);
				}
				preparedStatement.setInt(4, contract.getClientId());
				preparedStatement.setString(5, contract.getClientLocationNo());
				preparedStatement.setString(6, contract.getProductId());

			} else if (setupFlag.equalsIgnoreCase("EarlyTermination")) {
				String updateString = "update FIN_CONTRACT set PROCESSED_VOLUME_TILL_DATE=?,PROCESSED_VOLUME_UPDATE_DATE=? "
						+ "where CLIENT_ID = ? AND CLIENT_LOCATION_NO = ? AND PRODUCT_ID = ? ";

				preparedStatement = connection
						.prepareStatement(updateString);

				if (null != contract.getProcessedVolumeTillDate()) {
					preparedStatement.setLong(1,
							contract.getProcessedVolumeTillDate());
				} else {
					preparedStatement.setInt(1, 0);
				}
				if (null != contract.getProcessedVolumeUpdateDate()) {
					Date contractProcessedVolumeUpdateDate = new Date(contract
							.getProcessedVolumeUpdateDate().getTime());
					preparedStatement.setString(2,
							sdf.format(contractProcessedVolumeUpdateDate));
				} else {
					preparedStatement.setString(2, STR_NULL);
				}

				preparedStatement.setInt(3, contract.getClientId());
				preparedStatement.setString(4, contract.getClientLocationNo());
				preparedStatement.setString(5, contract.getProductId());

			} else if (setupFlag.equalsIgnoreCase("SpecialRate")) {
				String updateString = "update FIN_CONTRACT set PROCESSED_VOLUME_TILL_DATE=?,PROCESSED_VOLUME_UPDATE_DATE=? "
						+ "where CLIENT_ID = ? AND CLIENT_LOCATION_NO = ? AND PRODUCT_ID = ? ";
				preparedStatement = connection
						.prepareStatement(updateString);

				if (null != contract.getProcessedVolumeTillDate()) {
					preparedStatement.setLong(1,
							contract.getProcessedVolumeTillDate());
				} else {
					preparedStatement.setInt(1, 0);
				}
				if (null != contract.getProcessedVolumeUpdateDate()) {
					Date contractProcessedVolumeUpdateDate = new Date(contract
							.getProcessedVolumeUpdateDate().getTime());
					preparedStatement.setString(2,
							sdf.format(contractProcessedVolumeUpdateDate));
				} else {
					preparedStatement.setString(2, STR_NULL);
				}

				preparedStatement.setInt(3, contract.getClientId());
				preparedStatement.setString(4, contract.getClientLocationNo());
				preparedStatement.setString(5, contract.getProductId());

			} else if (setupFlag.equalsIgnoreCase("Discount")) {
				String updateString = "update FIN_CONTRACT set PROCESSED_VOLUME_TILL_DATE=?,PROCESSED_VOLUME_UPDATE_DATE=? "
						+ "where CLIENT_ID = ? AND CLIENT_LOCATION_NO = ? AND PRODUCT_ID = ? ";
				preparedStatement = connection
						.prepareStatement(updateString);

				if (null != contract.getProcessedVolumeTillDate()) {
					preparedStatement.setLong(1,
							contract.getProcessedVolumeTillDate());
				} else {
					preparedStatement.setInt(1, 0);
				}
				if (null != contract.getProcessedVolumeUpdateDate()) {
					Date contractProcessedVolumeUpdateDate = new Date(contract
							.getProcessedVolumeUpdateDate().getTime());
					preparedStatement.setString(2,
							sdf.format(contractProcessedVolumeUpdateDate));
				} else {
					preparedStatement.setString(2, STR_NULL);
				}
				preparedStatement.setInt(3, contract.getClientId());
				preparedStatement.setString(4, contract.getClientLocationNo());
				preparedStatement.setString(5, contract.getProductId());

			} else if (setupFlag.equalsIgnoreCase("WarmUpSlabs")) {
				String updateString = "update FIN_CONTRACT set PROCESSED_VOLUME_TILL_DATE=?,PROCESSED_VOLUME_UPDATE_DATE=? "
						+ "where CLIENT_ID = ? AND CLIENT_LOCATION_NO = ? AND PRODUCT_ID = ? ";
				preparedStatement = connection
						.prepareStatement(updateString);

				if (null != contract.getProcessedVolumeTillDate()) {
					preparedStatement.setLong(1,
							contract.getProcessedVolumeTillDate());
				} else {
					preparedStatement.setInt(1, 0);
				}
				if (null != contract.getProcessedVolumeUpdateDate()) {
					Date contractProcessedVolumeUpdateDate = new Date(contract
							.getProcessedVolumeUpdateDate().getTime());
					preparedStatement.setString(2,
							sdf.format(contractProcessedVolumeUpdateDate));
				} else {
					preparedStatement.setString(2, STR_NULL);
				}
				preparedStatement.setInt(3, contract.getClientId());
				preparedStatement.setString(4, contract.getClientLocationNo());
				preparedStatement.setString(5, contract.getProductId());

			} else if (setupFlag.equalsIgnoreCase("AnnualSlab")) {
				String updateString = "update FIN_CONTRACT set PROCESSED_VOLUME_ANNUAL=?,PROCESSED_VOLUME_TILL_DATE=?,PROCESSED_VOLUME_UPDATE_DATE=? "
						+ "where CLIENT_ID = ? AND CLIENT_LOCATION_NO = ? AND PRODUCT_ID = ? ";
				preparedStatement = connection
						.prepareStatement(updateString);

				if (null != contract.getProcessedVolumeAnnual()) {
					preparedStatement.setLong(1,
							contract.getProcessedVolumeAnnual());
				} else {
					preparedStatement.setInt(1, 0);
				}
				if (null != contract.getProcessedVolumeTillDate()) {
					preparedStatement.setLong(2,
							contract.getProcessedVolumeTillDate());
				} else {
					preparedStatement.setInt(2, 0);
				}
				if (null != contract.getProcessedVolumeUpdateDate()) {
					Date contractProcessedVolumeUpdateDate = new Date(contract
							.getProcessedVolumeUpdateDate().getTime());
					preparedStatement.setString(3,
							sdf.format(contractProcessedVolumeUpdateDate));
				} else {
					preparedStatement.setString(3, STR_NULL);
				}
				preparedStatement.setInt(4, contract.getClientId());
				preparedStatement.setString(5, contract.getClientLocationNo());
				preparedStatement.setString(6, contract.getProductId());

			} else if (setupFlag.equalsIgnoreCase("SemiAnnualSlab")) {
				String updateString = "update FIN_CONTRACT set PROCESSED_VOLUME_SEMI_ANNUAL=?,PROCESSED_VOLUME_TILL_DATE=?,PROCESSED_VOLUME_UPDATE_DATE=? "
						+ "where CLIENT_ID = ? AND CLIENT_LOCATION_NO = ? AND PRODUCT_ID = ? ";
				preparedStatement = connection
						.prepareStatement(updateString);

				if (null != contract.getProcessedVolumeSemiAnnual()) {
					preparedStatement.setLong(1,
							contract.getProcessedVolumeSemiAnnual());
				} else {
					preparedStatement.setInt(1, 0);
				}
				if (null != contract.getProcessedVolumeTillDate()) {
					preparedStatement.setLong(2,
							contract.getProcessedVolumeTillDate());
				} else {
					preparedStatement.setInt(2, 0);
				}
				if (null != contract.getProcessedVolumeUpdateDate()) {
					Date contractProcessedVolumeUpdateDate = new Date(contract
							.getProcessedVolumeUpdateDate().getTime());
					preparedStatement.setString(3,
							sdf.format(contractProcessedVolumeUpdateDate));
				} else {
					preparedStatement.setString(3, STR_NULL);
				}
				preparedStatement.setInt(4, contract.getClientId());
				preparedStatement.setString(5, contract.getClientLocationNo());
				preparedStatement.setString(6, contract.getProductId());

			} else if (setupFlag.equalsIgnoreCase("QuarterlySlab")) {
				String updateString = "update FIN_CONTRACT set PROCESSED_VOLUME_QUARTER=?,PROCESSED_VOLUME_TILL_DATE=?,PROCESSED_VOLUME_UPDATE_DATE=? "
						+ "where CLIENT_ID = ? AND CLIENT_LOCATION_NO = ? AND PRODUCT_ID = ? ";
				preparedStatement = connection
						.prepareStatement(updateString);

				if (null != contract.getProcessedVolumeQuarter()) {
					preparedStatement.setLong(1,
							contract.getProcessedVolumeQuarter());
				} else {
					preparedStatement.setInt(1, 0);
				}
				if (null != contract.getProcessedVolumeTillDate()) {
					preparedStatement.setLong(2,
							contract.getProcessedVolumeTillDate());
				} else {
					preparedStatement.setInt(2, 0);
				}
				if (null != contract.getProcessedVolumeUpdateDate()) {
					Date contractProcessedVolumeUpdateDate = new Date(contract
							.getProcessedVolumeUpdateDate().getTime());
					preparedStatement.setString(3,
							sdf.format(contractProcessedVolumeUpdateDate));
				} else {
					preparedStatement.setString(3, STR_NULL);
				}
				preparedStatement.setInt(4, contract.getClientId());
				preparedStatement.setString(5, contract.getClientLocationNo());
				preparedStatement.setString(6, contract.getProductId());

			} else if (setupFlag.equalsIgnoreCase("MonthlySlab")) {
				String updateString = "update FIN_CONTRACT set PROCESSED_VOLUME_TILL_DATE=?,PROCESSED_VOLUME_UPDATE_DATE=? "
						+ "where CLIENT_ID = ? AND CLIENT_LOCATION_NO = ? AND PRODUCT_ID = ? ";
				preparedStatement = connection
						.prepareStatement(updateString);

				if (null != contract.getProcessedVolumeTillDate()) {
					preparedStatement.setLong(1,
							contract.getProcessedVolumeTillDate());
				} else {
					preparedStatement.setInt(1, 0);
				}
				if (null != contract.getProcessedVolumeUpdateDate()) {
					Date contractProcessedVolumeUpdateDate = new Date(contract
							.getProcessedVolumeUpdateDate().getTime());
					preparedStatement.setString(2,
							sdf.format(contractProcessedVolumeUpdateDate));
				} else {
					preparedStatement.setString(2, STR_NULL);
				}
				preparedStatement.setInt(3, contract.getClientId());
				preparedStatement.setString(4, contract.getClientLocationNo());
				preparedStatement.setString(5, contract.getProductId());

			}
			
			if (null != preparedStatement){
				preparedStatement.executeUpdate();
				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insertSlab(Slab slab) {
		String insertString = "Insert into FIN_CONTRACT_SLAB ("
				+ "LEGAL_ENTITY,BUSINESS_UNIT,CLIENT_ID,CLIENT_LOCATION_NO,PRODUCT_ID,"
				+ "CLIENT_NAME,SLAB_CATEGORY,"
				+ "SLAB_TYPE,MIN_SLAB_VOLUME,MAX_SLAB_VOLUME,CHARGES,"
				+ "ACTIVE,SLAB_END_DATE,SLAB_NO)"
				+ "values("
				+ "?,?,?,?,?,"
				+ "?,?,"
				+ "?,?,?,?,"
				+ "?,?,SEQ_FIN_CONTRACT_SLAB_REF_NO.NEXTVAL)";

		try {

			if (connection.isClosed())
				connection = dbConnection.getConnection();

			preparedStatement1 = connection.prepareStatement(insertString);

			preparedStatement1.setString(1, slab.getLegalEntity());
			preparedStatement1.setString(2, slab.getBusinessUnit());
			preparedStatement1.setInt(3, slab.getClientId());
			preparedStatement1.setString(4, slab.getClientLocationNo());
			preparedStatement1.setString(5, slab.getProductId());
			preparedStatement1.setString(6, slab.getClientName());
			preparedStatement1.setString(7, slab.getSlabCategory());
			preparedStatement1.setString(8, slab.getSlabType());
			if (null != slab.getMinSlabVolume()) {
				preparedStatement1.setLong(9, slab.getMinSlabVolume());
			} else
				preparedStatement1.setString(9, null);
			if (null != slab.getMaxSlabVolume()) {
				preparedStatement1.setLong(10, slab.getMaxSlabVolume());
			} else
				preparedStatement1.setString(10, null);
			if (null != slab.getCharges()) {
				preparedStatement1.setFloat(11, slab.getCharges());
			} else
				preparedStatement1.setFloat(11, 0);
			if (null != slab.getActive()) {
				preparedStatement1.setInt(12, slab.getActive());
			} else {
				preparedStatement1.setInt(12, -1);
			}

			if (null != slab.getSlabEndDate()) {
				Date slabEndDate = new Date(slab.getSlabEndDate().getTime());
				preparedStatement1.setString(13, sdf.format(slabEndDate));
			} else {
				preparedStatement1.setString(13, STR_NULL);
			}
			preparedStatement1.executeUpdate();

			connection.commit();

			// connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Contract
	public void insertContract(Contract contract) {

		String insertString = "insert into FIN_CONTRACT( "
				+ "LEGAL_ENTITY,BUSINESS_UNIT,"
				+ "CLIENT_ID,CLIENT_LOCATION_NO,CLIENT_NAME,"
				+ "PRODUCT_ID,PRODUCT_NAME,CATEGORY,"
				+ "CONTRACT_START_DATE,CONTRACT_END_DATE,AR_PROCESS_DATE,"
				+ "ONE_TIME_SETUP_FLAG,ONE_TIME_SETUP_CHARGE_DATE,ONE_TIME_SETUP_FEE,"
				+ "ADDITIONAL_SERVICE_CHARGES,"
				+ "EARLY_TERMINATION_FLAG,EARLY_TERMINATION_DATE,EARLY_TERMINATION_MINIMUM_VOLUME,PRO_RATA_CHARGES_FOR_TERMINATION,"
				+ "NO_MONTHS_OF_COMMITMENT,COMMITMENT_QTY,"
				+ "DISCOUNT_RATE_FLAG,DISCOUNT_START_DATE,DISCOUNT_END_DATE,DISCOUNT_RATE,"
				+ "DISCOUNT_EXTENSION_FLAG,DISCOUNT_EXTENSION_END_DATE,"
				+ "SPECIAL_RATE_FLAG,SPECIAL_START_DATE,SPECIAL_END_DATE,SPECIAL_RATE,"
				+ "WARM_UP_FLAG,WARM_UP_START_DATE,WARM_UP_END_DATE,"
				+ "WARM_UP_EXTENSION_FLAG,WARM_UP_EXTENSION_END_DATE,"
				+ "MONTHLY_SLAB_FLAG,MIN_MONTHLY_SLAB_VOLUME,MAX_MONTHLY_SLAB_VOLUME,MONTHLY_RATE,"
				+ "QUARTERLY_SLAB_FLAG,MIN_QUARTERLY_SLAB_VOLUME,MAX_QUARTERLY_SLAB_VOLUME,QUARTERLY_RATE,"
				+ "SEMI_ANNUAL_SLAB_FLAG,MIN_SEMI_ANNUAL_SLAB_VOLUME,MAX_SEMI_ANNUAL_SLAB_VOLUME,SEMI_ANNUAL_SLAB_RATE,"
				+ "ANNUAL_SLAB_FLAG,MIN_ANNUAL_SLAB_VOLUME,MAX_ANNUAL_SLAB_VOLUME,ANNUAL_SLAB_RATE,"
				+ "PROCESSED_VOLUME_TILL_DATE,PROCESSED_VOLUME_QUARTER,"
				+ "PROCESSED_VOLUME_SEMI_ANNUAL,PROCESSED_VOLUME_ANNUAL,"
				+ "PROCESSED_VOLUME_UPDATE_DATE) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			if (connection.isClosed())
				connection = dbConnection.getConnection();

			preparedStatement2 = connection.prepareStatement(insertString);
			preparedStatement2.setString(1, contract.getLegalEntity());
			preparedStatement2.setString(2, contract.getBusinessUnit());
			preparedStatement2.setInt(3, contract.getClientId());
			preparedStatement2.setString(4, contract.getClientLocationNo());
			preparedStatement2.setString(5, contract.getClientName());
			preparedStatement2.setString(6, contract.getProductId());
			preparedStatement2.setString(7, contract.getProductName());
			preparedStatement2.setString(8, contract.getCategory());

			if (null != contract.getContractStartDate()) {
				Date contractStartDate = new Date(contract
						.getContractStartDate().getTime());
				preparedStatement2.setString(9, sdf.format(contractStartDate));
			} else {
				preparedStatement2.setString(9, STR_NULL);
			}

			if (null != contract.getContractEndDate()) {
				Date contractEndDate = new Date(contract.getContractEndDate()
						.getTime());
				preparedStatement2.setString(10, sdf.format(contractEndDate));
			} else {
				preparedStatement2.setString(10, STR_NULL);
			}

			if (null != contract.getArProcessDate()) {
				Date contractArProcessDate = new Date(contract
						.getArProcessDate().getTime());
				preparedStatement2.setString(11,
						sdf.format(contractArProcessDate));
			} else {
				preparedStatement2.setString(11, STR_NULL);
			}

			if (null != contract.getOneTimeSetupFee()
					&& null != contract.getOneTimeSetupFlag()) {
				preparedStatement2
						.setString(12, contract.getOneTimeSetupFlag());
				if (null != contract.getOneTimeSetupChargeDate()) {
					Date contractOneTimeSetupChargeDate = new Date(contract
							.getOneTimeSetupChargeDate().getTime());
					preparedStatement2.setString(13,
							sdf.format(contractOneTimeSetupChargeDate));
				} else {
					preparedStatement2.setString(13, STR_NULL);
				}
				preparedStatement2.setFloat(14, contract.getOneTimeSetupFee());
			} else {
				preparedStatement2.setString(12, null);
				if (null != contract.getOneTimeSetupChargeDate()) {
					Date contractOneTimeSetupChargeDate = new Date(contract
							.getOneTimeSetupChargeDate().getTime());
					preparedStatement2.setString(13,
							sdf.format(contractOneTimeSetupChargeDate));
				} else {
					preparedStatement2.setString(13, STR_NULL);
				}
				preparedStatement2.setFloat(14, -1);
			}

			if (null != contract.getAdditionalServiceCharges()) {
				preparedStatement2.setFloat(15,
						contract.getAdditionalServiceCharges());
			} else {
				preparedStatement2.setFloat(15, -1);
			}

			if (null != contract.getEarlyTerminationFlag()
					&& null != contract.getProrataCharges4Termination()) {

				preparedStatement2.setString(16,
						contract.getEarlyTerminationFlag());

				if (null != contract.getEarlyTerminationDate()) {
					Date contractEarlyTerminationDate = new Date(contract
							.getEarlyTerminationDate().getTime());
					preparedStatement2.setString(17,
							sdf.format(contractEarlyTerminationDate));
				} else {
					preparedStatement2.setString(17, STR_NULL);
				}
				preparedStatement2.setLong(18,
						contract.getEarlyTerminationMinimumVolume());
				preparedStatement2.setFloat(19,
						contract.getProrataCharges4Termination());
			} else {
				preparedStatement2.setString(16, null);

				if (null != contract.getEarlyTerminationDate()) {
					Date contractEarlyTerminationDate = new Date(contract
							.getEarlyTerminationDate().getTime());
					preparedStatement2.setString(17,
							sdf.format(contractEarlyTerminationDate));
				} else {
					preparedStatement2.setString(17, STR_NULL);
				}
				preparedStatement2.setLong(18, -1);
				preparedStatement2.setFloat(19, -1);
			}

			if (null != contract.getNoMonthsOfCommitment()) {
				preparedStatement2.setInt(20,
						contract.getNoMonthsOfCommitment());
			} else {
				preparedStatement2.setInt(20, 0);
			}

			if (null != contract.getCommitmentQty()) {
				preparedStatement2.setInt(21, contract.getCommitmentQty());
			} else {
				preparedStatement2.setInt(21, 0);
			}

			if (null != contract.getDiscountRateFlag()
					&& null != contract.getDiscountRate()) {

				preparedStatement2
						.setString(22, contract.getDiscountRateFlag());

				if (null != contract.getDiscountStartDate()) {
					Date contractDiscountStartDate = new Date(contract
							.getDiscountStartDate().getTime());
					preparedStatement2.setString(23,
							sdf.format(contractDiscountStartDate));
				} else {
					preparedStatement2.setString(23, STR_NULL);
				}
				if (null != contract.getDiscountEndDate()) {
					Date contractDiscountEndDate = new Date(contract
							.getDiscountEndDate().getTime());
					preparedStatement2.setString(24,
							sdf.format(contractDiscountEndDate));
				} else {
					preparedStatement2.setString(24, STR_NULL);
				}
				preparedStatement2.setFloat(25, contract.getDiscountRate());

			} else {
				preparedStatement2.setString(22, null);

				if (null != contract.getDiscountStartDate()) {
					Date contractDiscountStartDate = new Date(contract
							.getDiscountStartDate().getTime());
					preparedStatement2.setString(23,
							sdf.format(contractDiscountStartDate));
				} else {
					preparedStatement2.setString(23, STR_NULL);
				}
				if (null != contract.getDiscountEndDate()) {
					Date contractDiscountEndDate = new Date(contract
							.getDiscountEndDate().getTime());
					preparedStatement2.setString(24,
							sdf.format(contractDiscountEndDate));
				} else {
					preparedStatement2.setString(24, STR_NULL);
				}
				preparedStatement2.setFloat(25, -1);
			}

			if (null != contract.getDiscountExtensionFlag()) {

				preparedStatement2.setString(26,
						contract.getDiscountExtensionFlag());

				if (null != contract.getDiscountExtensionEndDate()) {
					Date contractDiscountExtensionEndDate = new Date(contract
							.getDiscountExtensionEndDate().getTime());
					preparedStatement2.setString(27,
							sdf.format(contractDiscountExtensionEndDate));
				} else {
					preparedStatement2.setString(27, STR_NULL);
				}
			} else {
				preparedStatement2.setString(26, null);

				if (null != contract.getDiscountExtensionEndDate()) {
					Date contractDiscountExtensionEndDate = new Date(contract
							.getDiscountExtensionEndDate().getTime());
					preparedStatement2.setString(27,
							sdf.format(contractDiscountExtensionEndDate));
				} else {
					preparedStatement2.setString(27, STR_NULL);
				}
			}
			if (null != contract.getSpecialRateFlag()
					&& null != contract.getSpecialRate()) {

				preparedStatement2.setString(28, contract.getSpecialRateFlag());

				if (null != contract.getSpecialStartDate()) {
					Date contractSpecialStartDate = new Date(contract
							.getSpecialStartDate().getTime());
					preparedStatement2.setString(29,
							sdf.format(contractSpecialStartDate));
				} else {
					preparedStatement2.setString(29, STR_NULL);
				}
				if (null != contract.getSpecialEndDate()) {
					Date contractSpecialEndDate = new Date(contract
							.getSpecialEndDate().getTime());
					preparedStatement2.setString(30,
							sdf.format(contractSpecialEndDate));
				} else {
					preparedStatement2.setString(30, STR_NULL);
				}
				preparedStatement2.setFloat(31, contract.getSpecialRate());
			} else {
				preparedStatement2.setString(28, null);

				if (null != contract.getSpecialStartDate()) {
					Date contractSpecialStartDate = new Date(contract
							.getSpecialStartDate().getTime());
					preparedStatement2.setString(29,
							sdf.format(contractSpecialStartDate));
				} else {
					preparedStatement2.setString(29, STR_NULL);
				}
				if (null != contract.getSpecialEndDate()) {
					Date contractSpecialEndDate = new Date(contract
							.getSpecialEndDate().getTime());
					preparedStatement2.setString(30,
							sdf.format(contractSpecialEndDate));
				} else {
					preparedStatement2.setString(30, STR_NULL);
				}
				preparedStatement2.setFloat(31, -1);
			}

			if (null != contract.getWarmupFlag()) {

				preparedStatement2.setString(32, contract.getWarmupFlag());

				if (null != contract.getWarmupStartDate()) {
					Date contractWarmupStartDate = new Date(contract
							.getWarmupStartDate().getTime());
					preparedStatement2.setString(33,
							sdf.format(contractWarmupStartDate));
				} else {
					preparedStatement2.setString(33, STR_NULL);
				}

				if (null != contract.getWarmupEndDate()) {
					Date contractWarmupEndDate = new Date(contract
							.getWarmupEndDate().getTime());
					preparedStatement2.setString(34,
							sdf.format(contractWarmupEndDate));
				} else {
					preparedStatement2.setString(34, STR_NULL);
				}
			} else {
				preparedStatement2.setString(32, null);

				if (null != contract.getWarmupStartDate()) {
					Date contractWarmupStartDate = new Date(contract
							.getWarmupStartDate().getTime());
					preparedStatement2.setString(33,
							sdf.format(contractWarmupStartDate));
				} else {
					preparedStatement2.setString(33, STR_NULL);
				}

				if (null != contract.getWarmupEndDate()) {
					Date contractWarmupEndDate = new Date(contract
							.getWarmupEndDate().getTime());
					preparedStatement2.setString(34,
							sdf.format(contractWarmupEndDate));
				} else {
					preparedStatement2.setString(34, STR_NULL);
				}
			}

			if (null != contract.getWarmupExtensionFlag()) {

				preparedStatement2.setString(35,
						contract.getWarmupExtensionFlag());

				if (null != contract.getWarmupExtensionEndDate()) {
					Date contractWarmupExtensionEndDate = new Date(contract
							.getWarmupExtensionEndDate().getTime());
					preparedStatement2.setString(36,
							sdf.format(contractWarmupExtensionEndDate));
				} else {
					preparedStatement2.setString(36, STR_NULL);
				}
			} else {
				preparedStatement2.setString(35, null);

				if (null != contract.getWarmupExtensionEndDate()) {
					Date contractWarmupExtensionEndDate = new Date(contract
							.getWarmupExtensionEndDate().getTime());
					preparedStatement2.setString(36,
							sdf.format(contractWarmupExtensionEndDate));
				} else {
					preparedStatement2.setString(36, STR_NULL);
				}
			}

			if (null != contract.getMonthlySlabFlag()) {

				preparedStatement2.setString(37, contract.getMonthlySlabFlag());
				preparedStatement2.setLong(38,
						contract.getMinmonthlySlabVolume());
				if (null != contract.getMaxmonthlySlabVolume()) {
					preparedStatement2.setLong(39,
							contract.getMaxmonthlySlabVolume());
				} else
					preparedStatement2.setString(39, null);
				preparedStatement2.setFloat(40, contract.getMonthlyRate());
			} else {
				preparedStatement2.setString(37, null);
				preparedStatement2.setLong(38, -1);
				preparedStatement2.setLong(39, -1);
				preparedStatement2.setFloat(40, -1);
			}

			if (null != contract.getQuarterlySlabFlag()) {

				preparedStatement2.setString(41,
						contract.getQuarterlySlabFlag());
				preparedStatement2.setLong(42,
						contract.getMinQuarterlySlabVolume());
				if (null != contract.getMaxQuarterlySlabVolume()) {
					preparedStatement2.setLong(43,
							contract.getMaxQuarterlySlabVolume());
				} else
					preparedStatement2.setString(43, null);
				preparedStatement2.setFloat(44, contract.getQuarterlyRate());
			} else {
				preparedStatement2.setString(41, null);
				preparedStatement2.setLong(42, -1);
				preparedStatement2.setLong(43, -1);
				preparedStatement2.setFloat(44, -1);
			}

			if (null != contract.getSemiAnnualSlabFlag()) {

				preparedStatement2.setString(45,
						contract.getSemiAnnualSlabFlag());
				preparedStatement2.setLong(46,
						contract.getMinSemiAnnualSlabVolume());
				if (null != contract.getMaxSemiAnnualSlabVolume()) {
					preparedStatement2.setLong(47,
							contract.getMaxSemiAnnualSlabVolume());
				} else
					preparedStatement2.setString(47, null);
				preparedStatement2.setFloat(48,
						contract.getSemiAnnualSlabRate());
			} else {
				preparedStatement2.setString(45, null);
				preparedStatement2.setLong(46, -1);
				preparedStatement2.setLong(47, -1);
				preparedStatement2.setFloat(48, -1);
			}

			if (null != contract.getAnnualSlabFlag()) {
				preparedStatement2.setString(49, contract.getAnnualSlabFlag());
				preparedStatement2.setLong(50,
						contract.getMinAnnualSlabVolume());
				if (null != contract.getMaxAnnualSlabVolume()) {
					preparedStatement2.setLong(51,
							contract.getMaxAnnualSlabVolume());
				} else
					preparedStatement2.setString(51, null);
				preparedStatement2.setFloat(52, contract.getAnnualSlabRate());
			} else {
				preparedStatement2.setString(49, null);
				preparedStatement2.setLong(50, -1);
				preparedStatement2.setLong(51, -1);
				preparedStatement2.setFloat(52, -1);
			}

			if (null != contract.getProcessedVolumeTillDate()) {
				preparedStatement2.setLong(53,
						contract.getProcessedVolumeTillDate());
			} else {
				preparedStatement2.setInt(53, 0);
			}
			if (null != contract.getProcessedVolumeQuarter()) {
				preparedStatement2.setLong(54,
						contract.getProcessedVolumeQuarter());
			} else {
				preparedStatement2.setInt(54, 0);
			}
			if (null != contract.getProcessedVolumeSemiAnnual()) {
				preparedStatement2.setLong(55,
						contract.getProcessedVolumeSemiAnnual());
			} else {
				preparedStatement2.setInt(55, 0);
			}
			if (null != contract.getProcessedVolumeAnnual()) {
				preparedStatement2.setLong(56,
						contract.getProcessedVolumeAnnual());
			} else {
				preparedStatement2.setInt(56, 0);
			}

			if (null != contract.getProcessedVolumeUpdateDate()) {
				Date contractProcessedVolumeUpdateDate = new Date(contract
						.getProcessedVolumeUpdateDate().getTime());
				preparedStatement2.setString(57,
						sdf.format(contractProcessedVolumeUpdateDate));
			} else {
				preparedStatement2.setString(57, STR_NULL);
			}
			preparedStatement2.executeUpdate();
			connection.commit();

			// connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	//Update Slab
	public boolean updateSlab(Map<String, String> mapSlab)
	{
		boolean flag = false;
		PreparedStatement preparedStatement = null;
		String updateSlabStr = "UPDATE FIN_CONTRACT_SLAB SET SLAB_CATEGORY=?,SLAB_TYPE=?,"
				+ "MIN_SLAB_VOLUME=?,MAX_SLAB_VOLUME=?,CHARGES=?,SLAB_END_DATE=?,ACTIVE=? "
				+ "WHERE LEGAL_ENTITY=? and BUSINESS_UNIT=? and CLIENT_ID=? and CLIENT_LOCATION_NO=? "
				+ "and PRODUCT_ID=? and SLAB_NO=? and CLIENT_NAME=?";
		
		try {
			if (connection.isClosed())
				connection = dbConnection.getConnection();
			preparedStatement = connection.prepareStatement(updateSlabStr);
			
			for(Entry<String, String> entry : mapSlab.entrySet())
			{ 
				if (entry.getKey().equalsIgnoreCase("SLAB_CATEGORY")) {
					preparedStatement.setString(1, entry.getValue());
				}else if (entry.getKey().equalsIgnoreCase("SLAB_TYPE")) {
					preparedStatement.setString(2, entry.getValue());
				}else if (entry.getKey().equalsIgnoreCase("MIN_SLAB_VOLUME")) {
					preparedStatement.setLong(3,Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("MAX_SLAB_VOLUME")) {
					preparedStatement.setLong(4, Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("CHARGES")) {
					preparedStatement.setFloat(5,Float.parseFloat(entry.getValue()) );
				}else if (entry.getKey().equalsIgnoreCase("SLAB_END_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(6,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
					System.out.println("Date :" +dateStr);
				}else if(entry.getKey().equalsIgnoreCase("ACTIVE")){
					preparedStatement.setString(7,entry.getValue());
				}else if(entry.getKey().equalsIgnoreCase("LEGAL_ENTITY")){
					preparedStatement.setString(8,entry.getValue());
				}else if (entry.getKey().equalsIgnoreCase("BUSINESS_UNIT")) {
					preparedStatement.setString(9,entry.getValue());
				}else if (entry.getKey().equalsIgnoreCase("CLIENT_ID")) {
					preparedStatement.setInt(10,Integer.parseInt(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("CLIENT_LOCATION_NO")) {
					preparedStatement.setString(11,entry.getValue());
				}else if (entry.getKey().equalsIgnoreCase("PRODUCT_ID")) {
					preparedStatement.setString(12,entry.getValue());
				}else if (entry.getKey().equalsIgnoreCase("SLAB_NO")) {
					preparedStatement.setInt(13,Integer.parseInt(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("CLIENT_NAME")) {
					preparedStatement.setString(14, entry.getValue());
				}
			}
			
			System.out.println("Preparestatement : "+preparedStatement.toString());
			int rowUpdate =preparedStatement.executeUpdate();
			connection.commit();
			if(rowUpdate == 0)
			{
				flag =false;
			}else {
				flag = true;
				
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}

	public boolean updateContract(Map<String, String> mapContract) {
		
		boolean flag = false;
		PreparedStatement preparedStatement = null;
		String updateSlabStr = "UPDATE FIN_CONTRACT SET CLIENT_NAME=? ,PRODUCT_NAME=? ,CATEGORY=? ,"
				+ "CONTRACT_START_DATE=? ,CONTRACT_END_DATE=? ,AR_PROCESS_DATE=? ,ONE_TIME_SETUP_FLAG=? ,"
				+ "ONE_TIME_SETUP_CHARGE_DATE=? ,ONE_TIME_SETUP_FEE=? ,ADDITIONAL_SERVICE_CHARGES=? ,"
				+ "EARLY_TERMINATION_FLAG=? ,EARLY_TERMINATION_DATE=? ,EARLY_TERMINATION_MINIMUM_VOLUME=? ,"
				+ "PRO_RATA_CHARGES_FOR_TERMINATION=? ,NO_MONTHS_OF_COMMITMENT=? ,COMMITMENT_QTY=? ,"
				+ "DISCOUNT_RATE_FLAG=? ,DISCOUNT_START_DATE=? ,DISCOUNT_END_DATE=? ,DISCOUNT_RATE=? ,"
				+ "DISCOUNT_EXTENSION_FLAG=? ,DISCOUNT_EXTENSION_END_DATE=? ,SPECIAL_RATE_FLAG=? ,"
				+ "SPECIAL_START_DATE=? ,SPECIAL_END_DATE=? ,SPECIAL_RATE=? ,WARM_UP_FLAG=? ,"
				+ "WARM_UP_START_DATE=? ,WARM_UP_END_DATE=? ,WARM_UP_EXTENSION_FLAG=? ,"
				+ "WARM_UP_EXTENSION_END_DATE=? ,MONTHLY_SLAB_FLAG=? ,MIN_MONTHLY_SLAB_VOLUME=? ,"
				+ "MAX_MONTHLY_SLAB_VOLUME=? ,MONTHLY_RATE=? ,QUARTERLY_SLAB_FLAG=? ,"
				+ "MIN_QUARTERLY_SLAB_VOLUME=? ,MAX_QUARTERLY_SLAB_VOLUME=? ,QUARTERLY_RATE=? ,"
				+ "SEMI_ANNUAL_SLAB_FLAG=? ,MIN_SEMI_ANNUAL_SLAB_VOLUME=? ,MAX_SEMI_ANNUAL_SLAB_VOLUME=? ,"
				+ "SEMI_ANNUAL_SLAB_RATE=? ,ANNUAL_SLAB_FLAG=? ,MIN_ANNUAL_SLAB_VOLUME=? ,"
				+ "MAX_ANNUAL_SLAB_VOLUME=? ,ANNUAL_SLAB_RATE=? ,PROCESSED_VOLUME_TILL_DATE=? ,"
				+ "PROCESSED_VOLUME_QUARTER=? ,PROCESSED_VOLUME_SEMI_ANNUAL=? ,PROCESSED_VOLUME_ANNUAL=? ,"
				+ "PROCESSED_VOLUME_UPDATE_DATE=? "
				+ "WHERE LEGAL_ENTITY=? and BUSINESS_UNIT=? and CLIENT_ID=? and CLIENT_LOCATION_NO=? and PRODUCT_ID=? and SLAB_NO=?";
		
		try {
			if (connection.isClosed())
				connection = dbConnection.getConnection();
			preparedStatement = connection.prepareStatement(updateSlabStr);
			
			for(Entry<String, String> entry : mapContract.entrySet())
			{ 
				if (entry.getKey().equalsIgnoreCase("CLIENT_NAME")) {
					preparedStatement.setString(1, entry.getValue());
				}else if (entry.getKey().equalsIgnoreCase("PRODUCT_NAME")) {
					preparedStatement.setString(2, entry.getValue());
				}else if (entry.getKey().equalsIgnoreCase("CATEGORY")) {
					preparedStatement.setString(3, entry.getValue());
				}else if (entry.getKey().equalsIgnoreCase("CONTRACT_START_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(4,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if (entry.getKey().equalsIgnoreCase("CONTRACT_END_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(5,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if (entry.getKey().equalsIgnoreCase("AR_PROCESS_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(6,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if (entry.getKey().equalsIgnoreCase("ONE_TIME_SETUP_FLAG")) {
					if(null != entry.getValue()|| !entry.getValue().equalsIgnoreCase("undefined")){
						preparedStatement.setString(7,entry.getValue());
					}else {
						preparedStatement.setString(7,STR_NULL);
					}
				}else if (entry.getKey().equalsIgnoreCase("ONE_TIME_SETUP_CHARGE_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(8,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if (entry.getKey().equalsIgnoreCase("ONE_TIME_SETUP_FEE")) {
					preparedStatement.setFloat(9,Float.parseFloat(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("ADDITIONAL_SERVICE_CHARGES")) {
					preparedStatement.setFloat(10,Float.parseFloat(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("EARLY_TERMINATION_FLAG")) {
					if(null != entry.getValue()|| !entry.getValue().equalsIgnoreCase("undefined")){
						preparedStatement.setString(11,entry.getValue());
					}else {
						preparedStatement.setString(11,STR_NULL);
					}
				}else if (entry.getKey().equalsIgnoreCase("EARLY_TERMINATION_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(12,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if (entry.getKey().equalsIgnoreCase("EARLY_TERMINATION_MINIMUM_VOLUME")) {
					preparedStatement.setLong(13,Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("PRO_RATA_CHARGES_FOR_TERMINATION")) {
					preparedStatement.setFloat(14,Float.parseFloat(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("NO_MONTHS_OF_COMMITMENT")) {
					preparedStatement.setInt(15,Integer.parseInt(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("COMMITMENT_QTY")) {
					preparedStatement.setInt(16,Integer.parseInt(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("DISCOUNT_RATE_FLAG")) {
					if(null != entry.getValue()|| !entry.getValue().equalsIgnoreCase("undefined")){
						preparedStatement.setString(17,entry.getValue());
					}else {
						preparedStatement.setString(17,STR_NULL);
					}
				}else if (entry.getKey().equalsIgnoreCase("DISCOUNT_START_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(18,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if (entry.getKey().equalsIgnoreCase("DISCOUNT_END_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(19,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if (entry.getKey().equalsIgnoreCase("DISCOUNT_RATE")) {
					preparedStatement.setFloat(20,Float.parseFloat(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("DISCOUNT_EXTENSION_FLAG")) {
					if(null != entry.getValue()|| !entry.getValue().equalsIgnoreCase("undefined")){
						preparedStatement.setString(21,entry.getValue());
					}else {
						preparedStatement.setString(21,STR_NULL);
					}
				}else if (entry.getKey().equalsIgnoreCase("DISCOUNT_EXTENSION_END_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(22,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if (entry.getKey().equalsIgnoreCase("SPECIAL_RATE_FLAG")) {
					if(null != entry.getValue()|| !entry.getValue().equalsIgnoreCase("undefined")){
						preparedStatement.setString(23, entry.getValue());
					}else {
						preparedStatement.setString(23,STR_NULL);
					}
				}else if (entry.getKey().equalsIgnoreCase("SPECIAL_START_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(24,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if (entry.getKey().equalsIgnoreCase("SPECIAL_END_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(25,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if (entry.getKey().equalsIgnoreCase("SPECIAL_RATE")) {
					preparedStatement.setFloat(26,Float.parseFloat(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("WARM_UP_FLAG")) {
					if(null != entry.getValue()|| !entry.getValue().equalsIgnoreCase("undefined")){
						preparedStatement.setString(27,entry.getValue());
					}else {
						preparedStatement.setString(27,STR_NULL);
					}
				}else if (entry.getKey().equalsIgnoreCase("WARM_UP_START_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(28,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if (entry.getKey().equalsIgnoreCase("WARM_UP_END_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(29,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if (entry.getKey().equalsIgnoreCase("WARM_UP_EXTENSION_FLAG")) {
					if(null != entry.getValue()|| !entry.getValue().equalsIgnoreCase("undefined")){
						preparedStatement.setString(30, entry.getValue());
					}else {
						preparedStatement.setString(30,STR_NULL);
					}
				}else if (entry.getKey().equalsIgnoreCase("WARM_UP_EXTENSION_END_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(31,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if (entry.getKey().equalsIgnoreCase("MONTHLY_SLAB_FLAG")) {
					if(null != entry.getValue()|| !entry.getValue().equalsIgnoreCase("undefined")){
						preparedStatement.setString(32, entry.getValue());
					}else {
						preparedStatement.setString(32, STR_NULL);
					}
				}else if (entry.getKey().equalsIgnoreCase("MIN_MONTHLY_SLAB_VOLUME")) {
					preparedStatement.setLong(33,Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("MAX_MONTHLY_SLAB_VOLUME")) {
					preparedStatement.setLong(34,Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("MONTHLY_RATE")) {
					preparedStatement.setFloat(35,Float.parseFloat(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("QUARTERLY_SLAB_FLAG")) {
					if(null != entry.getValue() || !entry.getValue().equalsIgnoreCase("undefined")){
						preparedStatement.setString(36, entry.getValue());
					}else {
						preparedStatement.setString(36, STR_NULL);
					}
				}else if (entry.getKey().equalsIgnoreCase("MIN_QUARTERLY_SLAB_VOLUME")) {
					preparedStatement.setLong(37,Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("MAX_QUARTERLY_SLAB_VOLUME")) {
					preparedStatement.setLong(38,Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("QUARTERLY_RATE")) {
					preparedStatement.setFloat(39,Float.parseFloat(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("SEMI_ANNUAL_SLAB_FLAG")) {
					if(null != entry.getValue() || !entry.getValue().equalsIgnoreCase("undefined")){
						preparedStatement.setString(40, entry.getValue());
					}else {
						preparedStatement.setString(40, STR_NULL);
					}
				}else if (entry.getKey().equalsIgnoreCase("MIN_SEMI_ANNUAL_SLAB_VOLUME")) {
					preparedStatement.setLong(41,Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("MAX_SEMI_ANNUAL_SLAB_VOLUME")) {
					preparedStatement.setLong(42,Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("SEMI_ANNUAL_SLAB_RATE")) {
					preparedStatement.setFloat(43,Float.parseFloat(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("ANNUAL_SLAB_FLAG")) {
					if(null != entry.getValue() || !entry.getValue().equalsIgnoreCase("undefined")){
						preparedStatement.setString(44, entry.getValue());
					}else {
						preparedStatement.setString(44, STR_NULL);
					}
				}else if (entry.getKey().equalsIgnoreCase("MIN_ANNUAL_SLAB_VOLUME")) {
					preparedStatement.setLong(45,Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("MAX_ANNUAL_SLAB_VOLUME")) {
					preparedStatement.setLong(46,Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("ANNUAL_SLAB_RATE")) {
					preparedStatement.setFloat(47,Float.parseFloat(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("PROCESSED_VOLUME_TILL_DATE")) {
					preparedStatement.setLong(48,Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("PROCESSED_VOLUME_QUARTER")) {
					preparedStatement.setLong(49,Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("PROCESSED_VOLUME_SEMI_ANNUAL")) {
					preparedStatement.setLong(50,Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("PROCESSED_VOLUME_ANNUAL")) {
					preparedStatement.setLong(51,Long.parseLong(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("PROCESSED_VOLUME_UPDATE_DATE")) {
					String dateStr = entry.getValue().replace(",", "");
					DateFormat format = new SimpleDateFormat("MMM dd yyyy");
					try {
						java.util.Date date =format.parse(dateStr);
						Date dateSql= new Date(date.getTime());
						preparedStatement.setString(52,sdf.format(dateSql));	
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if(entry.getKey().equalsIgnoreCase("LEGAL_ENTITY")){
					preparedStatement.setString(53,entry.getValue());
				}else if (entry.getKey().equalsIgnoreCase("BUSINESS_UNIT")) {
					preparedStatement.setString(54,entry.getValue());
				}else if (entry.getKey().equalsIgnoreCase("CLIENT_ID")) {
					preparedStatement.setInt(55,Integer.parseInt(entry.getValue()));
				}else if (entry.getKey().equalsIgnoreCase("CLIENT_LOCATION_NO")) {
					preparedStatement.setString(56,entry.getValue());
				}else if (entry.getKey().equalsIgnoreCase("PRODUCT_ID")) {
					preparedStatement.setString(57,entry.getValue());
				}
			}
			
			System.out.println("Preparestatement : "+preparedStatement.toString());
			int rowUpdate =preparedStatement.executeUpdate();
			connection.commit();
			if(rowUpdate == 0)
			{
				flag =false;
			}else {
				flag = true;
				
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}

}
