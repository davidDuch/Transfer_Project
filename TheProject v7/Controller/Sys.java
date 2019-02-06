package Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Model.Transaction;
import Model.Wallet;

public class Sys {

	public Calendar currentTime;
	public double discountExpandPrice;
	public double sizeExpandPrice;
	public double defualtWalletSize;
	public double expendWalletSize;
	public double expendDiscountSize;
	public double maxPossibleExpansionSize;

	/**
	 * 
	 * @param transaction
	 */
	@SuppressWarnings("unchecked")
	public void SendTransactions(ArrayList<Transaction> transactions) {

		JSONArray transcationsToSend = new JSONArray();

		for (Transaction temp : transactions) {
			JSONObject trans = new JSONObject();
			trans.put("ID", temp.getId());
			trans.put("Size", temp.getSize());
			trans.put("Type", temp.getSendType());
			trans.put("Comission", temp.getCommission());
			transcationsToSend.add(trans);
		}

		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter(
				"C:\\Users\\Gabi Malin\\Desktop\\Transfer_Project\\TheProject v7\\JSON.txt")) {
			file.write(transcationsToSend.toJSONString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Sys() {
		Wallet.walletsCount =(int) UserLogic.counts_All_Wallets() + 15;

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

	public Transaction[] RecieveTransactions() {
		// TODO - implement System.RecieveTransactions
		throw new UnsupportedOperationException();
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