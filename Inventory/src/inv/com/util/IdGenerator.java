package inv.com.util;

import inv.com.entity.Supplier;

import java.sql.DriverManager;
import java.sql.ResultSet;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class IdGenerator {
	
	private Connection cn = null;
	
	private String nipProduct;
	
	private String nipCustomer;
	
	private String nipPo;
	
	private String nipSalesOrder;
	
	private String nipSupplier;
	
//	private String nip
	
	public IdGenerator(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getNipCustomer(){
		try {
			String sql = "select id from customer order by id desc";
			PreparedStatement pd = (PreparedStatement)cn.prepareStatement(sql);
			ResultSet rs= pd.executeQuery();
			System.out.println(rs+"rsrsrs");
			if (rs.next()){
				nipCustomer = rs.getString("id");
				System.out.println(nipCustomer+"nipCustomer1");
				
//				untuk menghilangkan huruf pertama nya "C" sehinnga mendapat kan angka semuanya
				nipCustomer = nipCustomer.substring(1);
				System.out.println(nipCustomer+"nipCustomer2");
				int temp = Integer.parseInt(nipCustomer)+1;
				String j = String.valueOf(temp);
				int h = j.length();
				switch (h) {
				case 1:nipCustomer = "C000" + j; break;
				case 2:nipCustomer = "C00" + j; break;
				case 3:nipCustomer = "C0" + j; break;
				case 4:nipCustomer = "C" + j; break;
				
				}
			}else{
				nipCustomer = "C0001";
			}
			 rs.close();
		} catch (Exception e) {
		System.err.println(e.getMessage());
		}
		return nipCustomer;
	}
	
	public String getNipProduct (){		
		try {
			String sql = "select id from product order by id desc";
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				nipProduct = rs.getString("id");
				System.out.println(nipProduct+"nipProduct");
				nipProduct = nipProduct.substring(1);
				System.out.println(nipProduct+"nipProduct2");
				int temp = Integer.parseInt(nipProduct)+1;
				String s = String.valueOf(temp);
				int j = s.length();
				switch (j) {
				case 1:	nipProduct = "P000" + s; break;
				case 2:	nipProduct = "P00" + s; break;
				case 3:	nipProduct = "P0" + s; break;
				case 4:	nipProduct = "P" + s; break;
				}
			}else{
				nipProduct = "P0001";
			}
			rs.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
			System.out.println(nipProduct);
		return nipProduct;
		}
	
	public String getNipPo(){
		try {
			String sql = "select id from po order by id desc";
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				nipPo = rs.getString("id");
				nipPo = nipPo.substring(2);
				int j = Integer.parseInt(nipPo)+1;
				String c = String.valueOf(j);
				int g = c.length();
				switch (g) {
				case 1: nipPo = "PO000"+ c; break;
				case 2: nipPo = "PO00"+ c; break;
				case 3: nipPo = "PO0"+ c; break;
				case 4: nipPo = "PO"+ c; break;
				}			
				
			}else{
				nipPo = "PO0001";
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return nipPo;
	}
	
	public String noSupplier(){
		try {
			String sql = "select id from supplier order by id desc";
			PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				nipSupplier = rs.getString("id");
				nipSupplier = nipSupplier.substring(1);
				int i = Integer.parseInt(nipSupplier)+1;
				String c = String.valueOf(i);
				int g = c.length();
				switch (g) {
				case 1: nipSupplier = "S000" + c; break;
				case 2: nipSupplier = "S00" + c; break;
				case 3: nipSupplier = "S0" + c; break;
				case 4: nipSupplier = "S" + c; break;
				}
				}else{
					nipSupplier = "S0001";
				}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return nipSupplier;
	}
	
	public String getNipSalesOrder(){
		try {
			
			String sql = "select id from salesOrder order by id desc";
			PreparedStatement pr = (PreparedStatement) cn.prepareStatement(sql);
			ResultSet rs = pr.executeQuery();
			
			if (rs.next()){
				nipSalesOrder = rs.getString("id");
				nipSalesOrder = nipSalesOrder.substring(2);
				int i = Integer.parseInt(nipSalesOrder)+1;
				String j = String.valueOf(i);
				int k = j.length();
				switch (k) {
					case 1:	nipSalesOrder = "SO000"+ j; break;
					case 2:	nipSalesOrder = "SO00"+ j; break;
					case 3:	nipSalesOrder = "SO0"+ j; break;
					case 4:	nipSalesOrder = "SO"+ j; break;
				}
				}else {
					nipSalesOrder = "SO0001";
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return nipSalesOrder;
	}
}
