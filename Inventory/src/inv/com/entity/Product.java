package inv.com.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String name;
	
	private String jenis;
	
	private String type;
	
	private String description;
	
	private double price;
	
	private int stock;
	
	@OneToMany(mappedBy="product")
	private Set<PurchaseOrderDetail>purchaseOrderDetails;
	
	@OneToMany(mappedBy="product")
	private Set<SalesOrderDetail> salesOrderDetails;
	
	@ManyToOne
	private ImageDetail map;
	
	public Product(){}	
	
	

	
	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public String getJenis() {
		return jenis;
	}




	public void setJenis(String jenis) {
		this.jenis = jenis;
	}




	public ImageDetail getMap() {
		return map;
	}


	public void setMap(ImageDetail map) {
		this.map = map;
	}


	public Set<SalesOrderDetail> getSalesOrderDetails() {
		return salesOrderDetails;
	}



	public void setSalesOrderDetails(Set<SalesOrderDetail> salesOrderDetails) {
		this.salesOrderDetails = salesOrderDetails;
	}



	public Set<PurchaseOrderDetail> getPurchaseOrderDetails() {
		return purchaseOrderDetails;
	}



	public void setPurchaseOrderDetails(
			Set<PurchaseOrderDetail> purchaseOrderDetails) {
		this.purchaseOrderDetails = purchaseOrderDetails;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	

}
