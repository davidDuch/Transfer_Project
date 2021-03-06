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
	public static final String SQL_ADD_WALLETKNOTS = "INSERT INTO tblWalletKnots ( address, discount )\r\n" + 
			"VALUES (?, ?);\r\n" + 
			";";
	public static final String SQL_ADD_WALLETSPACE = "INSERT INTO tblWalletSpace ( address, maxTransactionSize )\r\n" + 
			"VALUES (?, ?);\r\n" + 
			"";
	public static final String SQL_UPGRADE_KNOTS = "UPDATE tblWalletKnots SET tblWalletKnots.discount = ?\r\n" + 
			"WHERE (((tblWalletKnots.address)= ?));\r\n" + 
			"";
	public static final String SQL_UPGRADE_SPACE = "UPDATE tblWalletSpace SET tblWalletSpace.maxTransactionSize = ?\r\n" + 
			"WHERE (((tblWalletSpace.address)= ?));\r\n" + 
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
	public static final String SQL_SEL_WORKERS = "SELECT * FROM tblWorkers";
	public static final String SQL_DEL_DELETEUSER = "{ call  deleteUser(?,?) }";
	public static final String SQL_ADD_USER = "{ call addUser(?,?,?,?,?,?) }";
	public static final String SQL_GET_USERSADVICE = "{ call getUsersAdvice(?,?) };";
	public static final String SQL_GET_PRODUCTS_PRICE = "{ call getProductsByPrice(?,?) };";
	public static final String SQL_SEL_PRODUCTS = "SELECT * FROM tblProduct";
	public static final String SQL_GET_USERS_PAY = "{ call getUsersPay(?,?) };";
	public static final String SQL_GET_USERS_CONFIRM = "{ call getUsersConfirm(?,?) };";
	public static final String SQL_UPDATE_QUANTITY = "{ call updateProduct(?,?) };";
	public static final String SQL_ADD_PRODUCT = "{call addProduct(?,?,?,?,?,?,?,?,?) };";
	public static final String SQL_GET_KNOTS = "SELECT * FROM tblWalletKnots";
	public static final String SQL_GET_SPACE = "SELECT * FROM tblWalletSpace";
	public static final String SQL_USERS_REPORT = "SELECT * FROM UsersReport";
	public static final String SQL_USERS_REPORT_WORKED = "SELECT unionTransactions.Address, Count(unionTransactions.transactionId), Avg(unionTransactions.commission), Sum(IIf(unionTransactions.status='executed',1,0))/Count(unionTransactions.transactionId)\r\n" + 
			"FROM unionTransactions, Statistics \r\n" + 
			"GROUP BY unionTransactions.Address; \r\n" + 
			"";


//==================================== Advise logic ==============================	
	public static final String SQL_SEL_ADVICE = "SELECT * FROM tblAdvice";
	public static final String SQL_INS_NEWCOMMITMENT = "{ call addCommitment(?,?,?,?) }";
	public static final String SQL_INS_NEWADVICE = "{ call addAdvice(?,?,?,?) }";
	public static final String SQL_GET_USERSADVICECOMMIT = "{ call getCommitmentPerUserPerAdvice(?,?,?) }";
	public static final String SQL_GET_ADVICE_PER_USER = "{ call advicePerUser(?,?) }";
	public static final String SQL_SEL_CATEGORIES = "SELECT * FROM tblCategory";

	
	
	
	
	
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
	public static final String WORKER_UPDATE_DATE = "UPDATE tblSystem SET tblSystem.currentTime = ?\r\n" +
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
