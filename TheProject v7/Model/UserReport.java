package Model;

public class UserReport {
	public String address;
	public int transactionNum;
	public double avg;
	public double percentage;
	public UserReport(String address, int transactionNum, double avg, double percentage) {
		super();
		this.address = address;
		this.transactionNum = transactionNum;
		this.avg = avg;
		this.percentage = percentage/transactionNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTransactionNum() {
		return transactionNum;
	}
	public void setTransactionNum(int transactionNum) {
		this.transactionNum = transactionNum;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	@Override
	public String toString() {
		return "UserReport [address=" + address + ", transactionNum=" + transactionNum + ", avg=" + avg
				+ ", percentage=" + percentage + "]";
	} 
	
}
