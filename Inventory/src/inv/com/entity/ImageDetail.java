package inv.com.entity;

import java.io.Serializable;

import inv.com.util.BaseImage;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="produk_image")
public class ImageDetail extends BaseImage implements Serializable {
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private SalesOrderDetail salesOrderDetail;
	
	public ImageDetail(){}
	public ImageDetail(long id){
		setId(id);
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public SalesOrderDetail getSalesOrderDetail() {
		return salesOrderDetail;
	}
	public void setSalesOrderDetail(SalesOrderDetail salesOrderDetail) {
		this.salesOrderDetail = salesOrderDetail;
	}
	

}
