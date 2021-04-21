package model;

public class Recipient {
	private int id;
	private String bank;
	private String branch;
	private String accountNumber;
	private String issuedAt;
	
	public Recipient() {
		super();
	}

	public Recipient(String bank, String branch, String accountNumber) {
		super();
		this.bank = bank;
		this.branch = branch;
		this.accountNumber = accountNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(String issuedAt) {
		this.issuedAt = issuedAt;
	}

	@Override
	public String toString() {
		return "Recipient [id=" + id + ", bank=" + bank + ", branch=" + branch + ", accountNumber=" + accountNumber
				+ ", issuedAt=" + issuedAt + "]";
	}

}
