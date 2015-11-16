package inv.com.service;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import inv.com.dao.GenericDao;
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
import inv.com.util.Constant;
import inv.com.util.Constraint;
import inv.com.util.OrderMap;
import inv.com.util.SearchResult;

@Service("databaseService")
@Transactional
public class DatabaseImpl implements DatabaseService {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private GenericDao genericDao;
	
	
//	customer
	@Override
	public SearchResult searchCustomer(Constraint constraint,
			OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return genericDao.searchHQL("customer", "Customer customer", constraint, orderMap, isUsingPaging, offset, pageSize);
	}

	@Override
	public SearchResult lookupCustomer(Constraint constraint, int offset,
			int pageSize) {
		
		if (constraint != null && StringUtils.isNotBlank(constraint.getWhereClause())){
			constraint.setWhereClause(constraint.getWhereClause() + " and customer.enable=true");
		}else{
			constraint = new Constraint();
			constraint.setWhereClause("customer.enable=true");
		}
		
		OrderMap orderMap = new OrderMap();
		orderMap.put("customer.id", Constant.ORDER_ASC);
		return null;
	}

	@Override
	public Customer findCustomerById(String id) {
		// TODO Auto-generated method stub
		return (Customer) sessionFactory.getCurrentSession().get(Customer.class,  id);
	}

	@Transactional(readOnly = false)
	public void saveCustomer(Customer customer) {
		sessionFactory.getCurrentSession().saveOrUpdate(customer);

	}
	

//	product
	@Override
	public SearchResult searchProduct(Constraint constraint, OrderMap orderMap,
			boolean isUsingPaging, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return genericDao.searchHQL("product", "Product product", constraint, orderMap, isUsingPaging, offset, pageSize);
	}

	@Override
	public SearchResult lookupProduct(Constraint constraint, int offset,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findProductById(String id) {
		// TODO Auto-generated method stub
		return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
	}

	@Override
	@Transactional(readOnly=false)
	public void saveCustomer(Product product) {
		sessionFactory.getCurrentSession().saveOrUpdate(product);
		
	}
	
	@Override
	public void deleteProduct(Product product) {
		sessionFactory.getCurrentSession().delete(product);
		
	}
	

//	PurchaseOrder
	@Override
	public SearchResult searchPurchaseOrder(Constraint constraint,
			OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return genericDao.searchHQL("purchaseOrder", "PurchaseOrder purchaseOrder", constraint, orderMap, isUsingPaging, offset, pageSize);
	}

	@Override
	public SearchResult lookupPurchaseOrder(Constraint constraint, int offset,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchaseOrder findPurchaseOrderById(String id) {
		// TODO Auto-generated method stub
		return (PurchaseOrder) sessionFactory.getCurrentSession().get(PurchaseOrder.class, id);
	}

	@Transactional(readOnly=false)
	@Override
	public void savePurchaseOrder(PurchaseOrder purchaseOrder) {
		sessionFactory.getCurrentSession().saveOrUpdate(purchaseOrder);
		
	}
	
	@Override
	public void deletePurchaseOrder(PurchaseOrder purchaseOrder) {
		sessionFactory.getCurrentSession().delete(purchaseOrder);
		
	}
	
	@Override
	public PurchaseOrder loadPurcahseOrderById(String id) {
		PurchaseOrder order = findPurchaseOrderById(id);
		
		Hibernate.initialize(order.getPurchaseOrderDetails());
		return order;
	}
	

//	purchaseOrderDetail
	@Override
	public SearchResult searchPurchaseOrderDetail(Constraint constraint,
			OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return genericDao.searchHQL("purchaseOrderDetail", "PurchaseOrderDetail purchaseOrderDetail", constraint, orderMap, isUsingPaging, offset, pageSize);
	}

	@Override
	public SearchResult lookupPurchaseOrderDetail(Constraint constraint,
			int offset, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchaseOrderDetail findPurchaseOrderDetailById(String purchaseOrder) {
		// TODO Auto-generated method stub
		return (PurchaseOrderDetail) sessionFactory.getCurrentSession().get(PurchaseOrderDetail.class, purchaseOrder);
	}

	@Transactional(readOnly=false)
	@Override
	public void savePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
		sessionFactory.getCurrentSession().saveOrUpdate(purchaseOrderDetail);
		
	}
	
	@Override
	public void deletePurchaseOrderDetail(
			PurchaseOrderDetail purchaseOrderDetail) {
		sessionFactory.getCurrentSession().delete(purchaseOrderDetail);
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<PurchaseOrderDetail> getTotal(
			String purchaseOrder) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createSQLQuery("SELECT SUM(total) FROM po_detail WHERE purchaseOrder_id= :id").setParameter("id", purchaseOrder).list();
	}
	

//	SalesOeder
	@Override
	public SearchResult searchSalesOrder(Constraint constraint,
			OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return genericDao.searchHQL("salesOrder", "SalesOrder salesOrder", constraint, orderMap, isUsingPaging, offset, pageSize);
	}

	@Override
	public SearchResult lookupSalesOrder(Constraint constraint, int offset,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalesOrder findSalesOrderById(String id) {
		// TODO Auto-generated method stub
		return (SalesOrder) sessionFactory.getCurrentSession().get(SalesOrder.class, id);
	}

	@Override
	public void saveSalesOrder(SalesOrder salesOrder) {
		sessionFactory.getCurrentSession().saveOrUpdate(salesOrder);
		
	}
	
		
	@Override
	public SalesOrder loadSalesOrderById(String id) {
		SalesOrder order = findSalesOrderById(id);
		
		Hibernate.initialize(order.getSalesOrderDetails());
		return order;
	}
	
	
	
//	SalesOrderDetail
	@Override
	public SearchResult searchSalesOrderDetail(Constraint constraint,
			OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return genericDao.searchHQL("salesOrderDetail", "SalesOrderDetail salesOrderDetail", constraint, orderMap, isUsingPaging, offset, pageSize);
	}

	@Override
	public SearchResult lookupSalesOrderDetail(Constraint constraint,
			int offset, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalesOrderDetail findSalesOrderDetailById(int id) {
		// TODO Auto-generated method stub
		return (SalesOrderDetail) sessionFactory.getCurrentSession().get(SalesOrderDetail.class, id);
	}

	@Override
	public void saveSalesOrderDetail(SalesOrderDetail salesOrderDetail) {
		sessionFactory.getCurrentSession().saveOrUpdate(salesOrderDetail);
				
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesOrderDetail> getId(String id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createSQLQuery("select * from salesorderdetail where salesOrder_id = :tes ").setParameter("tes", id).list();
	}
	
	@Override
	public List getSum(String id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createSQLQuery("SELECT SUM(total) FROM salesorderdetail WHERE salesOrder_id = :id").setParameter("id", id).list();
	}


//	untuk supplier
	@Override
	public SearchResult searchSupplier(Constraint constraint,
			OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return genericDao.searchHQL("supplier", "Supplier supplier", constraint, orderMap, isUsingPaging, offset, pageSize);
	}

	@Override
	public SearchResult lookupSupplier(Constraint constraint, int offset,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supplier findSupplierById(String id) {
		// TODO Auto-generated method stub
		return (Supplier) sessionFactory.getCurrentSession().get(Supplier.class, id);
	}

	@Override
	public void saveSupplier(Supplier supplier) {
		sessionFactory.getCurrentSession().saveOrUpdate(supplier);
		
	}

//	untuk image
	@Override
	public void saveProductImage(ImageDetail imageDetail) {
		sessionFactory.getCurrentSession().saveOrUpdate(imageDetail);
		
	}

	@Override
	public void deleteProductImage(ImageDetail imageDetail) {
		sessionFactory.getCurrentSession().delete(imageDetail);
		
	}

	@Override
	public List listImage(Product product) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from ImageDetail where product = :product ")
				.setParameter("product", product).list();
	}

	@Override
	public void saveImagePO(ImageDetail imageDetail) {
		sessionFactory.getCurrentSession().saveOrUpdate(imageDetail);
		
	}

	@Override
	public void deleteImagePO(ImageDetail imageDetail) {
		sessionFactory.getCurrentSession().delete(imageDetail);
		
	}

	@Override
	public List listImagePo(SalesOrderDetail salesOrderDetail) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from ImageDetail where salesOrderDetail = :salesOrderDetail")
				.setParameter("salesOrderDetail", salesOrderDetail).list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<PurchaseOrderDetail> getByPurchaseOrderId(String id) {
		// TODO Auto-generated method stub
		return (List<PurchaseOrderDetail>) sessionFactory.getCurrentSession().createSQLQuery("select * from po_detail where purchaseOrder_id = :tes ").setParameter("tes", id).list();
	}

	
//	 untuk user
	@Override
	public SearchResult searchUser(Constraint constraint, OrderMap orderMap,
			boolean isUsingPaging, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return genericDao.searchHQL("user", "User user", constraint, orderMap, isUsingPaging, offset, pageSize);
	}

	@Override
	public SearchResult searchUserWithRoles(Constraint constraint,
			OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize) {

//		untuk menambahkan element "and" jika constraint tidak null
		constraint = Constraint.precheckConstraint(constraint);
		
		constraint.setWhereClause(constraint.getWhereClause() + " user.id = employee.id");
		
		SearchResult searchResult = genericDao.searchHQL("user, employee.name", "User user, Employee employee"
					, constraint, orderMap, isUsingPaging, offset, pageSize);
		
		for (Iterator iterator = searchResult.getRecs().iterator(); iterator.hasNext();){
			Object [] row = (Object[]) iterator.next();
			
			User user = (User) row[0];
			
			Hibernate.initialize(user.getRoles());
		}
		
		return searchResult;
	}


	public User findUserByPaging(String id) {
		// TODO Auto-generated method stub
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}


	public User findUserByWithRoles(String id) {
		User user = findUserByPaging(id);		
		if (user != null){			
			System.out.println("testing usermanagement 1");
			Hibernate.initialize(user.getRoles());
		}
		System.out.println("testing usermanagement 2");
		return user;
	}

	
	public void saveUser(User user) {
		System.out.println("testing usermanagement 3");
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		
	}

	@Override
	public void deleteUserRoles(User user) {
			user.getRoles().clear();
			saveUser(user);		
	}
	
	
// untuk employee
	@Override
	public SearchResult searchEmployee(Constraint constraint,
			OrderMap orderMap, boolean isUsingPaging, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return genericDao.searchHQL("employee", "Employee employee", constraint, orderMap, isUsingPaging, offset, pageSize);
	}

	@Override
	public SearchResult lookupEmployee(Constraint constraint, int offset,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findEmployeeById(String id) {
		// TODO Auto-generated method stub
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, id);
	}

	@Override
	public void saveEmployee(Employee employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
		
	}

//	untuk role
	@Override
	public SearchResult searchRoles(Constraint constraint, OrderMap orderMap,
			boolean isUsingPaging, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return genericDao.searchHQL("role", "Role role", constraint, orderMap, isUsingPaging, offset, pageSize);
	}


	@Override
	public Role rolebyId(String id) {
		// TODO Auto-generated method stub
		return (Role) sessionFactory.getCurrentSession().get(Role.class, id);
	}

	@Override
	public void save(Role role) {
		sessionFactory.getCurrentSession().saveOrUpdate(role);
		
	}

	


}
