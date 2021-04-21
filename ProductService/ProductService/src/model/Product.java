package model;

public class Product {
	private int id;
	private String productName;
	private double productPrice;
	private int ownerId;
	private int buyerId;
	private String created_at;
	private String updated_at;
	private boolean isCompleted;
	private int categoryId;
	
	public Product() {
		super();
	}

	public Product(String productName, double productPrice, int ownerId, boolean isCompleted, int categoryId) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.ownerId = ownerId;
		this.isCompleted = isCompleted;
		this.categoryId = categoryId;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productPrice=" + productPrice + ", ownerId=" + ownerId
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + ", isCompleted=" + isCompleted
				+ ", categoryId=" + categoryId + "]";
	}
}
