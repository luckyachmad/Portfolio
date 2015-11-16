package inv.com.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="salesOrderDetail")
public class SalesOrderDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int idd;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private SalesOrder salesOrder;
	
	@ManyToOne
	private ImageDetail maps;
	
	private int jumlah;
	
	private double harga;
	
	private double total;
	
	public SalesOrderDetail(){}
	
	

	public ImageDetail getMaps() {
		return maps;
	}



	public void setMaps(ImageDetail maps) {
		this.maps = maps;
	}


	

	public int getIdd() {
		return idd;
	}



	public void setIdd(int idd) {
		this.idd = idd;
	}



	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}

	public double getHarga() {
		return harga;
	}

	public void setHarga(double harga) {
		this.harga = harga;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
