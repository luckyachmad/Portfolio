package inv.com.controller;

import java.util.List;

import inv.com.entity.Product;
import inv.com.entity.PurchaseOrder;
import inv.com.entity.PurchaseOrderDetail;
import inv.com.service.DatabaseService;
import inv.com.util.Constant;
import inv.com.util.Constraint;
import inv.com.util.IdGenerator;
import inv.com.util.PagingInfo;
import inv.com.util.SearchResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="master/admin/*")
public class PurchaseOrderController extends BaseController{
	
	@Autowired
	private DatabaseService databaseService;
	
	
	@RequestMapping(value="/searchPO")
	public String search(@ModelAttribute PurchaseOrder purchaseOrder, PagingInfo pagingInfo, ModelMap modelMap){
		
		Constraint constraint = resolveSearch(purchaseOrder);
		
		SearchResult searchResult = databaseService.searchPurchaseOrder(constraint, null, true, pagingInfo.getOffset(), pagingInfo.getPageSize());
		
		modelMap.put("po", searchResult.getRecs());
		modelMap.put("pagingInfo", searchResult.getPagingInfo());
		
		return "master/admin/searchPO";
	}
	
	@RequestMapping(value="/detailPo", method = RequestMethod.GET)
	public String add(@ModelAttribute PurchaseOrder purchaseOrder, ModelMap modelMap){
					
//		Constraint constraint = resolveSearch(purchaseOrderDetail.get);
		
		if(StringUtils.isBlank(purchaseOrder.getId())){
			IdGenerator id = new IdGenerator();
			modelMap.put("idGenerator", id.getNipPo());
			modelMap.put("mode", Constant.MODE_CREATE);
		}else if (StringUtils.isNotBlank(purchaseOrder.getId())){
			PurchaseOrder order = databaseService.loadPurcahseOrderById(purchaseOrder.getId());
			List<PurchaseOrderDetail> total2 = databaseService.getTotal(order.getId());
			
			modelMap.put("mode", Constant.MODE_UPDATE);
			modelMap.put("po", order);
			modelMap.put("total", total2.toArray());

		}
		
		return "master/admin/detailPo";
	}
	
		
	@RequestMapping(value = "/detailPo", method = RequestMethod.POST)
	public String save(@RequestParam String harga,
			@RequestParam String jumlah,
			@RequestParam String total,
			@ModelAttribute PurchaseOrder purchaseOrder,@ModelAttribute PurchaseOrderDetail purchaseOrderDetail,
			BindingResult result){
				
		databaseService.savePurchaseOrder(purchaseOrder);
		
		String [] jumlah1 = jumlah.toString().split(",");
		String [] harga1 = harga.toString().split(",");
//		String [] total1 = total.toString().split(",");
		
		String [] idProduct = result.getFieldValue("product.id").toString().split(",");
		String [] idPO = result.getFieldValue("purchaseOrder.id").toString().split(",");
		float tambah = 0;
		float totalJumlah = 0 ;
		tambah = totalJumlah + tambah;
		for (int i = 0 ; i < idProduct.length; i++){
			String idP = idProduct[i];
			String idPOo = idPO[i];
			int jumlah2 = Integer.parseInt(jumlah1[i]);
			float harga2 = Float.parseFloat(harga1[i]);
//			float total2 = Float.parseFloat(total1[i]);	
			
			totalJumlah = jumlah2 * harga2;			
			
			if (idP != ""){								
				
				Product product2 = databaseService.findProductById(idP);
				
				product2.setStock(product2.getStock() + jumlah2);
				databaseService.saveCustomer(product2);
				
				PurchaseOrder order  = databaseService.findPurchaseOrderById(idPOo);
				
				PurchaseOrderDetail orderDetail = new PurchaseOrderDetail();
				orderDetail.setHarga(harga2);
				orderDetail.setJumlah(jumlah2);
				orderDetail.setProduct(product2);
				orderDetail.setTotal(jumlah2 * harga2);
				orderDetail.setPurchaseOrder(order);
				databaseService.savePurchaseOrderDetail(orderDetail);
			}
		}		
		
		return "redirect:detailPo?id="+purchaseOrder.getId();
	}
	
	
	

}
