package Entity;

import Utils.Status;

public class Worker extends User {

	public Worker(String userName, String phoneNumber, String email, String password, String publicAddress,
			String digitalSignature) {

		super(digitalSignature, digitalSignature, digitalSignature, digitalSignature, digitalSignature,
				digitalSignature);

	}

	public Transaction[] transReport(Status status) {
		return null;

	}

	public User[] UserReport(User user) {
		return null;
	}

}