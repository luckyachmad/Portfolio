package inv.com.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import inv.com.entity.ImageDetail;
import inv.com.entity.Product;
import inv.com.filter.FlashMap.MessageType;
import inv.com.service.DatabaseService;
import inv.com.util.Constant;
import inv.com.util.Constraint;
import inv.com.util.IdGenerator;
import inv.com.util.PagingInfo;
import inv.com.util.SearchResult;
import inv.com.validator.BaseValidator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

import org.apache.commons.lang.StringUtils;
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
public class ProductController extends BaseController{
	
	@Autowired
	private DatabaseService databaseService;
	
	@RequestMapping(value="/searchProduct")
	public String search(@ModelAttribute Product product, @ModelAttribute PagingInfo pagingInfo, ModelMap modelMap){
//		mengolah dari ke class BaseControl
				
		Constraint constraint = resolveSearch(product);
		
		SearchResult searchResult = databaseService.searchProduct(constraint, null, true, pagingInfo.getOffset(), pagingInfo.getPageSize());
		
		modelMap.put("product", searchResult.getRecs());
		modelMap.put("pagingInfo", searchResult.getPagingInfo());
		return "master/admin/searchProduct";
	}
	
//	data dari class baseControl di olah lagi ke method ini
	@Override
	protected void specificSearch(Object object, StringBuilder buffer,
			Map<String, Object> parameters, String operator, String prefix, String suffix) {
		
		Product product = (Product) object;
				
		boolean isAppendAnd = false;
//		untuk searcing bedasarkan id
		if(StringUtils.isNotBlank(product.getId())) {
			buffer.append("product.id " + operator + " :id");
			
			parameters.put("id", prefix + product.getId() + suffix);
			
			isAppendAnd = true;
		}
//		untuk searcing bedasarkan name
		if(StringUtils.isNotBlank(product.getName())) {
			if(isAppendAnd) {
				buffer.append(" and ");
			}
			buffer.append("product.name " + operator + " :name");
			
			parameters.put("name", prefix + product.getName() + suffix);
			
			isAppendAnd = true;
		}
//		untuk searcing bedasarkan email
//		if(StringUtils.isNotBlank(product.getCode())) {
//			if(isAppendAnd) {
//				buffer.append(" and ");
//			}
//			buffer.append("product.code " + operator + " :code");
//			
//			parameters.put("code", prefix + product.getCode()+ suffix);
//			
//			isAppendAnd = true;
//		}
//		
	}
	
	@RequestMapping(value="/detailProduct",method =RequestMethod.GET)
	public String open(@ModelAttribute Product product, ModelMap modelMap){
		
				
				if (StringUtils.isBlank(product.getId())){
					modelMap.put("mode", Constant.MODE_CREATE);
					IdGenerator id = new IdGenerator();
					modelMap.put("idGenrator", id.getNipProduct());
					
				}else if  (StringUtils.isNotBlank(product.getId())){
					
						Product product2 = databaseService.findProductById(product.getId());
					
							modelMap.put("mode", Constant.MODE_UPDATE);
							modelMap.put("product", product2);
						
				}
		return"master/admin/detailProduct";
	}
	
	@RequestMapping(value="detailProduct", method = RequestMethod.POST)
	public String save(@RequestParam int mode, @ModelAttribute Product product, BindingResult result ,ModelMap modelMap 
			, @RequestParam("file") MultipartFile multipartFile, HttpSession session) throws Exception{		
		
		modelMap.put("mode", mode);
		
		IdGenerator id = new IdGenerator();
		modelMap.put("idGenrator", id.getNipProduct());
		
		new BaseValidator().validate(product, result);
		
		if (result.hasErrors()){
			System.out.println("testing");
			addMessage(modelMap, "masih ada yang belum di isi, coba di cek kembali.", MessageType.error, false);
			return "master/admin/detailProduct";
		}
		
		if(mode == Constant.MODE_CREATE){
			Product check = databaseService.findProductById(product.getId());
			if (check != null){
				addMessage(modelMap, " Product  dengan ID" + product.getId()+" sudah ada. ", MessageType.error, false);
				return "master/admin/detailProduct";
			}
			databaseService.saveCustomer(product);
			addMessage(modelMap, " Data karyawan berhasil di tambah.", MessageType.success, true);
		}else{
			databaseService.saveCustomer(product);
			addMessage(modelMap, " Data karyawan berhasil di update.", MessageType.success, true);
		}
		
		String folder = "resources/upload/productImage/";
		String fileName = getOriginalFilename(multipartFile).toLowerCase();
		
		uploadFileToServer(multipartFile, getRealPath(session) + folder + fileName);
		
		ImageDetail imageDetail = new ImageDetail();
		imageDetail.setFolder(folder);
		imageDetail.setFileName(fileName);
		imageDetail.setContentType(multipartFile.getContentType());
		
		Product product2 = databaseService.findProductById(product.getId());
		
		imageDetail.setProduct(product2);
		
		product2.setMap(imageDetail);
		
		databaseService.saveProductImage(imageDetail);
		databaseService.saveCustomer(product2);
		
		return "master/admin/detailProduct";
	}
	
	@RequestMapping(value="/report", method = RequestMethod.GET)
	public String productReport(){
		return "master/admin/report";
	}
	
//	report dengan menggunakan exel 
	@RequestMapping(value="/productReport/", method=RequestMethod.POST)
//	requestParam fieldnya harus benar benar di gunakan dalam method ini jika tidak akan terjadi error di jspnya
	public String productReport (ModelMap modelMap){
		
		Constraint constraint = new Constraint();		
		Map parameters = constraint.getParameters();
		
//		if (StringUtils.isNotEmpty(code)){
//			constraint.setWhereClause("product.code =: code");
//			parameters.put("code", code);
//		}
		
//		if (startDate != null && endDate !=null){
//			constraint = constraint.precheckConstraint(constraint);
//			
//			constraint.setWhereClause(constraint.getWhereClause()+"product.createDate between :startDate and endDate");
//					
//		}
//		

		
//		untuk membatasi jumlah field yang akan di keluarkan k report
		SearchResult searchResult = databaseService.searchProduct(constraint, null, false, -1, -1);
		
//		modelMap.put("startDate", startDate);
//		modelMap.put("endDate", endDate);
		
//		data yang di lempar
		modelMap.put("recs", searchResult.getRecs());
	
//		belum di gunakan 
//		putLookupMapIntoRequest(modelMap, "statuses", Constant.TABLE_TYPE_STATUS);
//		putLookupMapIntoRequest(modelMap, "statuses", Constant.TABLE_TYPE_TAB);
		
		
//		untuk melempar data ke tamplet class reportToExel yang nantinya di keluarkan ke exel datanya 
		return "reportToExel";
	}
	
//	report menggunakan ireport dengan membuka window baru 
	@RequestMapping("/suratTugasKerja")
	public String ireport(ModelMap modelMap){
		
		System.out.println("testing masuk dari yang menggunakan semuanya dari beberap hal yang baik");
		
		Product pro = databaseService.findProductById("P0001");
		
		modelMap.put("vendorName", pro.getName());
		modelMap.put("pic", "helmi");
		
		System.out.println(pro.getName()+"name"+pro.getPrice());
		modelMap.put("nama", "songong");
		
		modelMap.put("no","200923");
		modelMap.put("ref", null);
//		modelMap.put("vendorName", investigationVendor.getVendor().getName());
//		modelMap.put("pic", investigationVendor.getVendor().getPic());
		modelMap.put("vendorAddress", null);
		modelMap.put("vendorPhone", null);
		
		
		modelMap.put("pekerjaan", "Programmer");
		
		modelMap.put("lokasi", "jakarta");
		modelMap.put("waktuPelaksanaan", null);
		modelMap.put("waktuTarget", null);
		
		
		
		
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		
//		JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("../../../../../../../../template/suratTugasKerja.jasper"), modelMap, new JREmptyDataSource());
//		
//		JRDocxExporter exporter = new JRDocxExporter();
//    	exporter.setParameter(JRDocxExporterParameter.JASPER_PRINT, jasperPrint);
//    	exporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, baos);
//    	exporter.exportReport();
////    						untuk penamaan file yang mau di cetak
//    	response.setHeader("Content-Disposition", "inline; filename = spk_.doc");
////    	response.setContentType("application/vnd.ms-word");
//    	response.setContentType("application/msword");
//    	response.setContentLength(baos.size());
//		
//    	ServletOutputStream servletOutputStream = response.getOutputStream();
//    	baos.writeTo(servletOutputStream);
//    	servletOutputStream.flush();
    	
		JRDataSource datasource = new JREmptyDataSource();
		modelMap.put("datasource", datasource);
		System.out.println(datasource+"datasourcedatasourcex");
		
		return "suratTugasInvestigasi";
		
	}
	@RequestMapping(value="upload")
	public String upload(@RequestParam("file") MultipartFile multipartFile, HttpSession session) throws Exception{
		if (multipartFile.isEmpty()){
//			addMessage(modelMap, "File Belum di Isi", MessageType.error, true);
		}else{
			String folder = "resources/upload/room/";
			String filename = getOriginalFilename(multipartFile).toLowerCase();
			
		uploadFileToServer(multipartFile, getRealPath(session)+ folder + filename);
		ImageDetail imageDetail = new ImageDetail();
		
		imageDetail.setFolder(folder);
		imageDetail.setFileName(filename);
		imageDetail.setContentType(multipartFile.getContentType());
		
		Product product = databaseService.findProductById("P0001");
		product.setMap(imageDetail);
		imageDetail.setProduct(product);
		
		databaseService.saveProductImage(imageDetail);
		databaseService.saveCustomer(product);
		
//		addMessage(modelMap, "File brasil di simpan.", MessageType.success, true);
		
		}
		return "master/admin/productReport";
	}
}

