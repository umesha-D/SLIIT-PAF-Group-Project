package model;

public class Favourite {

	private int userid;
	private int productId;
	private String addedAt;
	
	public Favourite() {
		super();
	}

	public Favourite(int userid, int productId) {
		super();
		this.userid = userid;
		this.productId = productId;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(String addedAt) {
		this.addedAt = addedAt;
	}

	@Override
	public String toString() {
		return "Favourite [userid=" + userid + ", productId=" + productId + ", addedAt=" + addedAt + "]";
	}

	
}
