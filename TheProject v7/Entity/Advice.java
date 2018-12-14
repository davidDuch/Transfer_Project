package Entity;

import java.util.Calendar;

public class Advice {

	private int adviceId;
	private Calendar date;
	private Double adviceComission;
	private Double prefPercent;

	public Advice(int adviceId) {
		super();
		this.adviceId = adviceId;

	}

	public Advice(int adviceId, Calendar date, Double adviceComission, double prefPercent) {
		super();
		this.adviceId = adviceId;
		this.date = date;
		this.adviceComission = adviceComission;
		this.prefPercent = prefPercent;
	}

	public int getAdviceId() {
		return adviceId;
	}

	public void setAdviceId(int adviceId) {
		this.adviceId = adviceId;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Double getAdviceComission() {
		return adviceComission;
	}

	public void setAdviceComission(Double adviceComission) {
		this.adviceComission = adviceComission;
	}

	public double getPrefPercent() {
		return prefPercent;
	}

	public void setPrefPercent(double prefPercent) {
		this.prefPercent = prefPercent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adviceId;
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
		Advice other = (Advice) obj;
		if (adviceId != other.adviceId)
			return false;
		return true;
	}

}
