package inv.com.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import inv.com.entity.ImageDetail;
import inv.com.entity.Product;
import inv.com.entity.SalesOrder;
import inv.com.entity.SalesOrderDetail;
import inv.com.service.DatabaseService;
import inv.com.util.Constant;
import inv.com.util.Constraint;
import inv.com.util.IdGenerator;
import inv.com.util.PagingInfo;
import inv.com.util.SearchResult;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping(value="/master/admin/*")
public class SalesOrderController extends BaseController {
	
	@Autowired
	private DatabaseService databaseService;
	
	@RequestMapping(value="/searchSO")
	public String search(@ModelAttribute SalesOrder salesOrder, @ModelAttribute PagingInfo pagingInfo, ModelMap modelMap){
						
		Constraint constraint = resolveSearch(salesOrder);
		
		SearchResult searchResult = databaseService.searchSalesOrder(constraint, null, true, pagingInfo.getOffset(), pagingInfo.getPageSize());
		
		modelMap.put("salesOrders", searchResult.getRecs());
		modelMap.put("pagingInfo", searchResult.getPagingInfo());
		
		return "master/admin/searchSO";
	}
	
	@RequestMapping(value="detailSO", method = RequestMethod.GET)
	public String open(@ModelAttribute SalesOrder salesOrder, ModelMap modelMap){
		
		if (StringUtils.isBlank(salesOrder.getId())){
			IdGenerator generator = new IdGenerator();
			modelMap.put("idGenerator", generator.getNipSalesOrder());
			modelMap.put("mode", Constant.MODE_CREATE);
		}else if (StringUtils.isNotBlank(salesOrder.getId())){			
			SalesOrder salesOrder2 = databaseService.loadSalesOrderById(salesOrder.getId());
			List test = databaseService.getSum(salesOrder2.getId());
			modelMap.put("mode", Constant.MODE_UPDATE);
			modelMap.put("salesOrder", salesOrder2);
			modelMap.put("total", test.toArray());
		}
		return "master/admin/detailSO";
	}
	
	@RequestMapping(value="saveSO", method = RequestMethod.POST)
	public String save(@RequestParam String jumlah,
			@RequestParam String harga,
			@RequestParam String total,
			@ModelAttribute SalesOrder salesOrder,@ModelAttribute SalesOrderDetail salesOrderDetail, 
			BindingResult result, @RequestParam("file") MultipartFile multipartFile, HttpSession session) throws Exception{
		
			databaseService.saveSalesOrder(salesOrder);
			String [] jumlah1 = jumlah.toString().split(",");
			String [] harga1 = harga.toString().split(",");
//			String [] total1 = total.toString().split(",");
			
			String [] idProduct = result.getFieldValue("product.id").toString().split(",");
			String [] salesId = result.getFieldValue("salesOrder.id").toString().split(",");
			
			for (int i = 0; i <idProduct.length;i++){
				
				 String idd = idProduct[i];
				 if (idd != "" ){
				 int jumlah2 =  Integer.parseInt(jumlah1[i]);
//				 double total2 = Double.parseDouble(total1 [i]);
				 double harga2 = Double.parseDouble(harga1[i]);
				 
				
				 String salesid2 = salesId[i];

					Product product = databaseService.findProductById(idd);
					product.setStock(product.getStock() - jumlah2);
					databaseService.saveCustomer(product);
					
					SalesOrder salesOrder2 = databaseService.findSalesOrderById(salesid2); 
					
					
					SalesOrderDetail salesOrderDetail2 = new SalesOrderDetail();
					
					salesOrderDetail2.setHarga(harga2);	
					salesOrderDetail2.setJumlah(jumlah2);
					salesOrderDetail2.setTotal(jumlah2 * harga2);
//					salesOrderDetail.setMaps()
					salesOrderDetail2.setProduct(product);
					salesOrderDetail2.setSalesOrder(salesOrder2);	
					
					String folder = "resources/upload/salesOrder/";
					String fileName = getOriginalFilename(multipartFile).toLowerCase();
					
					uploadFileToServer(multipartFile, getRealPath(session) + folder + fileName);
					
					ImageDetail imageDetail = new ImageDetail();
					imageDetail.setFolder(folder);
					imageDetail.setFileName(fileName);
					imageDetail.setContentType(multipartFile.getContentType());
					
					imageDetail.setSalesOrderDetail(salesOrderDetail2);
					
					databaseService.saveSalesOrderDetail(salesOrderDetail2);
					List<SalesOrderDetail> details = databaseService.getId(salesOrder2.getId());
					
										
					String tye = details.toString();
				
//					String tesss = tye[1];
					System.out.println(tye+"tetetetetssssssssss");
//					
//					for(String key : tye){
//						String tes = key;
//						System.out.println("testt"+ tes);
//					}
//					details.toArray();
//					Map test = new HashMap();
//					test.put(0, details.toArray());
//					Iterator e = test.keySet().iterator();
//					while(e.hasNext()){
//						String key = (String) e.next();
//						System.out.println("key" + key);
//						System.out.println("isi" + test.get(key));
//					}
					
					System.out.println("tetetetettesssss");
//					imageDetail.setSalesOrderDetail();
					databaseService.saveImagePO(imageDetail);
				}
			}
	return "redirect:detailSO?id="+salesOrder.getId();
	}
	
	@RequestMapping(value= "uploadSO")
	public String uploadImage(@RequestParam("file") MultipartFile multipartFile, HttpSession session) throws Exception{
		
		String folder = "resource/upload/salesOrder/";
		String filename= getOriginalFilename(multipartFile).toLowerCase();
		
		uploadFileToServer(multipartFile, getRealPath(session) + folder + filename);
		
		ImageDetail imageDetail = new ImageDetail();
		imageDetail.setFolder(folder);
		imageDetail.setFileName(filename);
		imageDetail.setContentType(multipartFile.getContentType());
		
		SalesOrderDetail orderDetail = databaseService.findSalesOrderDetailById(1);
		
		orderDetail.setMaps(imageDetail);
		imageDetail.setSalesOrderDetail(orderDetail);
		
		databaseService.saveImagePO(imageDetail);
		databaseService.saveSalesOrderDetail(orderDetail);
		
		
		return "master/admin/detailSO";

	}
	
	protected void specificSearch(Object object, StringBuilder buffer,
			Map<String, Object> parameters, String operator, String prefix, String suffix) {
		
		SalesOrder salesOrder = (SalesOrder) object;
				
		boolean isAppendAnd = false;
//		untuk searcing bedasarkan id
		if(StringUtils.isNotBlank(salesOrder.getId())) {
			buffer.append("salesOrder.id " + operator + " :id");
			
			parameters.put("id", prefix + salesOrder.getId() + suffix);
			
			isAppendAnd = true;
		}
//		untuk searcing bedasarkan name
		if(StringUtils.isNotBlank(salesOrder.getName())) {
			if(isAppendAnd) {
				buffer.append(" and ");
			}
			buffer.append("salesOrder.name " + operator + " :name");
			
			parameters.put("name", prefix + salesOrder.getName() + suffix);
			
			isAppendAnd = true;
		}
	}
}
