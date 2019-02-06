package Utils;

import java.net.URLDecoder;

public class Consts {

	protected static final String DB_FILEPATH = getDBPath();
	public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";
	public static final String SQL_PARAMETERS = "SELECT tblSystem.discountExpandPrice, tblSystem.sizeExpendPrice, tblSystem.defaultWalletSize, tblSystem.expandWalletSize, tblSystem.expandDiscountSize, tblSystem.maxPossibleExpansionSize\r\n" + 
			"FROM tblSystem\r\n" + 
			"WHERE (((tblSystem.id)=111));\r\n" + 
			"";


//==================================== Wallet logic ==============================	
	public static final String SQL_SEL_KNOTSWALLRT = "SELECT * FROM tblWalletKnots";
	public static final String SQL_SEL_SPACEWALLRT = "SELECT * FROM tblWalletSpace";
	public static final String SQL_ADD_WALLET = "{ call addWallet(?,?,?,?,?,?,?,?) };";
	public static final String SQL_COUNT_WALLETS = "SELECT Count(tblWallet.address) AS CountOfaddress\r\n" + 
			"FROM tblWallet;\r\n" + 
			"";



//==================================== Transactions logic ==============================	
	public static final String SQL_INS_NEWPAY = "{ call addPay(?,?,?,?,?,?,?,?,?,?,?,?,?) }";
	public static final String SQL_INS_NEWCONFIRM = "{ call addConfirm(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
	public static final String SQL_GET_USERPAYBYSTATUES = "{ call userPayByStatus(?,?,?) }";
	public static final String SQL_SEL_PAYTRANSACTIONS = "SELECT * FROM tblTransactionPay";
	public static final String SQL_SEL_CONFIRMTRANSACTIONS = "SELECT * FROM tblTransactionConfirm";
	public static final String SQL_GET_ALLWAITING = "SELECT unionTransactions.transactionId, unionTransactions.size, unionTransactions.type, unionTransactions.commission\r\n" + 
			"FROM unionTransactions\r\n" + 
			"WHERE (((unionTransactions.status)=\"Waiting\"));\r\n" + 
			"";


//==================================== User logic ==============================	
	public static final String SQL_SEL_USERS = "SELECT * FROM tblUser";
	public static final String SQL_DEL_DELETEUSER = "{ call  deleteUser(?,?) }";
	public static final String SQL_ADD_USER = "{ call addUser(?,?,?,?,?,?) }";
	public static final String SQL_GET_USERSADVICE = "{ call getUsersAdvice(?,?) };";
	public static final String SQL_GET_PRODUCTS_PRICE = "{ call getProductsByPrice(?,?) };";
	public static final String SQL_GET_USERS_PAY = "{ call getUsersPay(?,?) };";
	public static final String SQL_GET_USERS_CONFIRM = "{ call getUsersConfirm(?,?) };";



//==================================== Advise logic ==============================	
	public static final String SQL_SEL_ADVICE = "SELECT * FROM tblAdvice";
	public static final String SQL_INS_NEWCOMMITMENT = "{ call addCommitment(?,?,?,?) }";
	public static final String SQL_INS_NEWADVICE = "{ call addAdvice(?,?,?,?) }";
	public static final String SQL_GET_USERSADVICECOMMIT = "{ call getCommitmentPerUserPerAdvice(?,?,?) }";
	
	
	
	
	
	
//	+++++++++++++++++++++++++++++++ Worker Logic =====================================================
	public static final String WORKER_UPDATE_discountExpandPrice = "UPDATE tblSystem SET tblSystem.discountExpandPrice = ?\r\n" + 
			"WHERE (((tblSystem.id)='111'));\r\n" + 
			"";
	public static final String WORKER_UPDATE_sizeExpendPrice = "UPDATE tblSystem SET tblSystem.sizeExpendPrice = ?\r\n" + 
			"WHERE (((tblSystem.id)='111'));\r\n" + 
			"";
	public static final String WORKER_UPDATE_defaultWalletSize = "UPDATE tblSystem SET tblSystem.defaultWalletSize = ?\r\n" + 
			"WHERE (((tblSystem.id)= '111'));\r\n" + 
			"";
	public static final String WORKER_UPDATE_expandWalletSize = "UPDATE tblSystem SET tblSystem.expandWalletSize = ?\r\n" + 
			"WHERE (((tblSystem.id)='111'));\r\n" + 
			"";
	public static final String WORKER_UPDATE_expandDiscountSize = "UPDATE tblSystem SET tblSystem.expandDiscountSize = ?\r\n" + 
			"WHERE (((tblSystem.id)='111'));\r\n" + 
			"";
	public static final String WORKER_UPDATE_maxPossibleExpansionSize = "UPDATE tblSystem SET tblSystem.maxPossibleExpansionSize = ?\r\n" + 
			"WHERE (((tblSystem.id)='111'));\r\n" + 
			"";
	public static final String SQL_ADD_CATEGORY = "{ call  addCategory(?,?) }";


	private static String getDBPath() {
		try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			if (decoded.contains(".jar")) {

				decoded = decoded.substring(0, decoded.lastIndexOf('/'));
				return decoded + "/Model/DataBase_Hw1_319412094_312181605.accdb";
			} else {
				decoded = decoded.substring(0, decoded.lastIndexOf("/"));
				return decoded + "/Model/DataBase_Hw1_319412094_312181605.accdb";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
