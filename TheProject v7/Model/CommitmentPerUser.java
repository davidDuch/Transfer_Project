package Model;

import Utils.Commitment;

public class CommitmentPerUser {

	private Commitment commitmentLvl;


	public CommitmentPerUser(Commitment commitmentLvl) {
		super();
		this.commitmentLvl = commitmentLvl;
	}


	public Commitment getCommitmentLvl() {
		return commitmentLvl;
	}


	public void setCommitmentLvl(Commitment commitmentLvl) {
		this.commitmentLvl = commitmentLvl;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commitmentLvl == null) ? 0 : commitmentLvl.hashCode());
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
		CommitmentPerUser other = (CommitmentPerUser) obj;
		if (commitmentLvl != other.commitmentLvl)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CommitmentPerUser [commitmentLvl=" + commitmentLvl + "]";
	}
	
	
	

}