package Model;
public class ProductsInProcess {

	private Double profitFromItems;
	private int boughtAmount;

	
	public ProductsInProcess(Double profitFromItems, int boughtAmount) {
		super();
		this.profitFromItems = profitFromItems;
		this.boughtAmount = boughtAmount;
	}


	public Double getProfitFromItems() {
		return profitFromItems;
	}


	public void setProfitFromItems(Double profitFromItems) {
		this.profitFromItems = profitFromItems;
	}


	public int getBoughtAmount() {
		return boughtAmount;
	}


	public void setBoughtAmount(int boughtAmount) {
		this.boughtAmount = boughtAmount;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + boughtAmount;
		result = prime * result + ((profitFromItems == null) ? 0 : profitFromItems.hashCode());
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
		ProductsInProcess other = (ProductsInProcess) obj;
		if (boughtAmount != other.boughtAmount)
			return false;
		if (profitFromItems == null) {
			if (other.profitFromItems != null)
				return false;
		} else if (!profitFromItems.equals(other.profitFromItems))
			return false;
		return true;
	}

	
	
	
}