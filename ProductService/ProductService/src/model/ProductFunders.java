package model;

public class ProductFunders {
	private int id;
	private int funderId;
	
	public ProductFunders() {
		super();
	}

	public ProductFunders(int id, int funderId) {
		super();
		this.id = id;
		this.funderId = funderId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFunderId() {
		return funderId;
	}

	public void setFunderId(int funderId) {
		this.funderId = funderId;
	}

	@Override
	public String toString() {
		return "ProductFunders [id=" + id + ", funderId=" + funderId + "]";
	}

}

