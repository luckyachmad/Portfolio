package inv.com.service;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import inv.com.entity.Customer;
import inv.com.entity.Employee;
import inv.com.entity.ImageDetail;
import inv.com.entity.Product;
import inv.com.entity.PurchaseOrder;
import inv.com.entity.PurchaseOrderDetail;
import inv.com.entity.Role;
import inv.com.entity.SalesOrder;
import inv.com.entity.SalesOrderDetail;
import inv.com.entity.Supplier;
import inv.com.entity.User;
import inv.com.util.Constraint;
import inv.com.util.OrderMap;
import inv.com.util.SearchResult;

public interface DatabaseService {

	
	public SearchResult searchCustomer(Constraint constraint, OrderMap orderMap,
			boolean isUsingPaging, int offset, int pageSize);
	public SearchResult lookupCustomer(Constraint constraint, int offset, int pageSize);
	public Customer findCustomerById(String id);
	public void saveCustomer(Customer customer);
	
	public SearchResult searchProduct(Constraint constraint, OrderMap orderMap,
			boolean isUsingPaging, int offset, int pageSize);
	public SearchResult lookupProduct(Constraint constraint, int offset, int pageSize);
	public Product findProductById(String id);
	public void saveCustomer(Product product);
	public void saveProductImage(ImageDetail imageDetail);
	public void deleteProductImage(ImageDetail imageDetail);
	public List listImage (Product product);
	public void deleteProduct(Product product);
	
	public SearchResult searchPurchaseOrder(Constraint constraint, OrderMap orderMap,
			boolean isUsingPaging, int offset, int pageSize);
	public SearchResult lookupPurchaseOrder(Constraint constraint, int offset, int pageSize);
	public PurchaseOrder loadPurcahseOrderById (String id);
	public PurchaseOrder findPurchaseOrderById(String id);
	public void savePurchaseOrder(PurchaseOrder purchaseOrder);
	public void deletePurchaseOrder(PurchaseOrder purchaseOrder);
	
	public SearchResult searchPurchaseOrderDetail(Constraint constraint, OrderMap orderMap,
			boolean isUsingPaging, int offset, int pageSize);
	public List<PurchaseOrderDetail> getByPurchaseOrderId(String id);
	public SearchResult lookupPurchaseOrderDetail(Constraint constraint, int offset, int pageSize);
	public PurchaseOrderDetail findPurchaseOrderDetailById(String purchaseOrder);
	public void savePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail);
	public void deletePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail);
	public List<PurchaseOrderDetail> getTotal (String purchaseOrder);
	
	
	
	public SearchResult searchSalesOrder(Constraint constraint, OrderMap orderMap,
			boolean isUsingPaging, int offset, int pageSize);
	public SalesOrder loadSalesOrderById(String id);	
	public SearchResult lookupSalesOrder(Constraint constraint, int offset, int pageSize);
	public SalesOrder findSalesOrderById(String id);
	public void saveSalesOrder(SalesOrder salesOrder);
	
	
	public SearchResult searchSalesOrderDetail(Constraint constraint, OrderMap orderMap,
			boolean isUsingPaging, int offset, int pageSize);
	public List<SalesOrderDetail> getId(String id);
	public List getSum(String id);
	public SearchResult lookupSalesOrderDetail(Constraint constraint, int offset, int pageSize);
	public SalesOrderDetail findSalesOrderDetailById(int id);
	public void saveSalesOrderDetail(SalesOrderDetail salesOrderDetail);
	public void saveImagePO(ImageDetail imageDetail);
	public void deleteImagePO(ImageDetail imageDetail);
	public List listImagePo(SalesOrderDetail  salesOrderDetail);
	
	public SearchResult searchSupplier(Constraint constraint, OrderMap orderMap,
			boolean isUsingPaging, int offset, int pageSize);
	public SearchResult lookupSupplier(Constraint constraint, int offset, int pageSize);
	public Supplier findSupplierById(String id);
	public void saveSupplier(Supplier supplier);
	
	public SearchResult searchUser(Constraint constraint, OrderMap orderMap, boolean isUsingPaging,
			int offset, int pageSize);
	public SearchResult searchUserWithRoles(Constraint constraint, OrderMap orderMap, boolean isUsingPaging,
			int offset, int pageSize);
	public User findUserByPaging(String id);	
	public User findUserByWithRoles(String id);
	public void saveUser(User user);
	public void deleteUserRoles(User user);
	
	public SearchResult searchEmployee(Constraint constraint, OrderMap orderMap,
			boolean isUsingPaging, int offset, int pageSize);
	public SearchResult lookupEmployee(Constraint constraint, int offset, int pageSize);
	public Employee findEmployeeById(String id);
	public void saveEmployee(Employee employee);
	
	public SearchResult searchRoles(Constraint constraint, OrderMap orderMap,
			boolean isUsingPaging, int offset, int pageSize);
	
	public Role rolebyId(String id);
	public void save(Role role);
}
