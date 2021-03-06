package Controller;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Model.Pay;
import Model.Product;
import Model.Transaction;
import Model.User;
import Model.Wallet;
import Utils.Consts;

public class Sys {

	public static Sys system;

	public Calendar currentTime;
	public static double discountExpandPrice;
	public static double sizeExpandPrice;
	public static double defualtWalletSize;
	public static double expendWalletSize;
	public static double expendDiscountSize;
	public static double maxPossibleExpansionSize;
	public static double maxPossibleDiscount = 50;
	public static User currentUser; 
	public static Product chosenProduct;
	public static Product myProduct; 
	public static Pay currentPay;
	/**
	 * 
	 * @param transaction
	 */
//	public Product getChosenProduct() {
//		return chosenProduct;
//	}
//	public void setChosenProduct(Product product) {
//		Sys.chosenProduct = product;
//	}
	public void setUser(User user) {
	Sys.currentUser = user;
	}
	public static ArrayList<String> getListSpace(){
	ArrayList<String> al = new ArrayList<String>();
	double i = defualtWalletSize;
	while(i < maxPossibleExpansionSize) {
		al.add(Double.toString(i) + " Price :" + Double.toString(i*sizeExpandPrice));
		i = i+expendWalletSize;
	}
	return al;
	}
	public static ArrayList<String> getListKnots(){
	ArrayList<String> al = new ArrayList<String>();
	double i = 1;
	while(i < maxPossibleDiscount) {
		al.add(Double.toString(i) + " Price :" + Double.toString(i*discountExpandPrice));
		i = i+expendDiscountSize;
	}
	return al;
	}
	public static ArrayList<String> getListProduct(){
	ArrayList<String> al = new ArrayList<String>();
	double price = Sys.chosenProduct.getPrice();
	int i=1;
	while(i<=Sys.chosenProduct.getAmountAvailable()) {
		al.add(i + " Price: " + i*price);
		i++;
	}
	return al;
	}
	public User getUser() {
		return Sys.currentUser;
	}
	@SuppressWarnings("unchecked")
	public static void SendTransactions(ArrayList<Transaction> transactions) throws UnsupportedEncodingException {

		JSONArray transcationsToSend = new JSONArray();

		for (Transaction temp : transactions) {
			JSONObject trans = new JSONObject();
			trans.put("ID", temp.getId());
			trans.put("Size", temp.getSize());
			trans.put("Type", temp.getSendType());
			trans.put("Comission", temp.getCommission());
			transcationsToSend.add(trans);
		}
		String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decoded = URLDecoder.decode(path, "UTF-8");
		if (decoded.contains(".jar"))
			decoded = decoded.substring(0, decoded.lastIndexOf("/"));
		else {
			System.out.println("1 " + decoded);
			decoded = decoded.substring(0, decoded.indexOf("/TheProject v7"));
			System.out.println("2 "+ decoded);

		}

		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter(
				decoded + "\\FlipCoin-Mining-after-gabi\\JSON.txt")) {
			file.write(transcationsToSend.toJSONString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void RecieveTransactions() {

		try {

			File fXmlFile = new File("file.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();


			NodeList nList = doc.getDocumentElement().getChildNodes();

			
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				
//				System.out.println("k");
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					TransactionLogic.updateTransaction(eElement.getElementsByTagName("transId").item(0).getTextContent(), eElement.getElementsByTagName("type").item(0).getTextContent());

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Sys() {
		Wallet.walletsCount = (int) UserLogic.counts_All_Wallets() + 15;
		ArrayList<Double> parameters = WorkerLogic.getParameters();
		discountExpandPrice = parameters.get(0);
		sizeExpandPrice = parameters.get(1);
		defualtWalletSize = parameters.get(2);
		expendWalletSize = parameters.get(3);
		expendDiscountSize = parameters.get(4);
		maxPossibleExpansionSize = parameters.get(5);

	}

	public Sys(Calendar currentTime, double discountExpandPrice, double sizeExpandPrice, double defualtWalletSize,
			double expendWalletSize, double expendDiscountSize, double maxPossibleExpansionSize) {
		super();
		this.currentTime = currentTime;
		this.discountExpandPrice = discountExpandPrice;
		this.sizeExpandPrice = sizeExpandPrice;
		this.defualtWalletSize = defualtWalletSize;
		this.expendWalletSize = expendWalletSize;
		this.expendDiscountSize = expendDiscountSize;
		this.maxPossibleExpansionSize = maxPossibleExpansionSize;
	}

	public Calendar getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Calendar currentTime) {
		this.currentTime = currentTime;
	}

	public double getDiscountExpandPrice() {
		return discountExpandPrice;
	}

	public void setDiscountExpandPrice(double discountExpandPrice) {
		this.discountExpandPrice = discountExpandPrice;
	}

	public double getSizeExpandPrice() {
		return sizeExpandPrice;
	}

	public void setSizeExpandPrice(double sizeExpandPrice) {
		this.sizeExpandPrice = sizeExpandPrice;
	}

	public double getDefualtWalletSize() {
		return defualtWalletSize;
	}

	public void setDefualtWalletSize(double defualtWalletSize) {
		this.defualtWalletSize = defualtWalletSize;
	}

	public double getExpendWalletSize() {
		return expendWalletSize;
	}

	public void setExpendWalletSize(double expendWalletSize) {
		this.expendWalletSize = expendWalletSize;
	}

	public double getExpendDiscountSize() {
		return expendDiscountSize;
	}

	public void setExpendDiscountSize(double expendDiscountSize) {
		this.expendDiscountSize = expendDiscountSize;
	}

	public double getMaxPossibleExpansionSize() {
		return maxPossibleExpansionSize;
	}

	public void setMaxPossibleExpansionSize(double maxPossibleExpansionSize) {
		this.maxPossibleExpansionSize = maxPossibleExpansionSize;
	}


	public void sendEmail() {
		// TODO - implement System.sendEmail
		throw new UnsupportedOperationException();
	}

	public static Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentTime == null) ? 0 : currentTime.hashCode());
		long temp;
		temp = Double.doubleToLongBits(defualtWalletSize);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(discountExpandPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(expendDiscountSize);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(expendWalletSize);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(maxPossibleExpansionSize);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(sizeExpandPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sys other = (Sys) obj;
		if (currentTime == null) {
			if (other.currentTime != null)
				return false;
		} else if (!currentTime.equals(other.currentTime))
			return false;
		if (Double.doubleToLongBits(defualtWalletSize) != Double.doubleToLongBits(other.defualtWalletSize))
			return false;
		if (Double.doubleToLongBits(discountExpandPrice) != Double.doubleToLongBits(other.discountExpandPrice))
			return false;
		if (Double.doubleToLongBits(expendDiscountSize) != Double.doubleToLongBits(other.expendDiscountSize))
			return false;
		if (Double.doubleToLongBits(expendWalletSize) != Double.doubleToLongBits(other.expendWalletSize))
			return false;
		if (Double.doubleToLongBits(maxPossibleExpansionSize) != Double
				.doubleToLongBits(other.maxPossibleExpansionSize))
			return false;
		if (Double.doubleToLongBits(sizeExpandPrice) != Double.doubleToLongBits(other.sizeExpandPrice))
			return false;
		return true;
	}

}