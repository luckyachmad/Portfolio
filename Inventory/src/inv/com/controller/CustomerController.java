package inv.com.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import inv.com.entity.Customer;
import inv.com.filter.FlashMap.MessageType;
import inv.com.service.DatabaseService;
import inv.com.util.Constant;
import inv.com.util.Constraint;
import inv.com.util.IdGenerator;
import inv.com.util.PagingInfo;
import inv.com.util.SearchResult;
import inv.com.validator.BaseValidator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("master/admin/*")
public class CustomerController extends BaseController{
	
	@Autowired
	private DatabaseService databaseService;
	
	@InitBinder
	public void initBinder(DataBinder dataBinder){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat timerFormat = new SimpleDateFormat("HH:mm");
		
		dataBinder.registerCustomEditor(Date.class, "dob", new CustomDateEditor(dateFormat, true));
		dataBinder.registerCustomEditor(Date.class, "worktime", new CustomDateEditor(timerFormat, true));
		
	}
	
	@RequestMapping(value="/")
	public String search(@ModelAttribute Customer customer, @ModelAttribute PagingInfo pagingInfo, ModelMap modelMap){
		Constraint constraint = resolveSearch(customer);
				
//		untuk menampung inputan dari 
String coba = constraint.getWhereClause();
		
		Map<String, Object> test = constraint.getParameters();
		
		System.out.println(coba+"cobaaaaaaa"+test+"tetetetetetesssss");
		
		SearchResult searchResult = databaseService.searchCustomer(constraint, null, true,pagingInfo.getOffset(),pagingInfo.getPageSize());
		
		modelMap.put("customer", searchResult.getRecs());
		modelMap.put("pagingInfo", searchResult.getPagingInfo());
		
		return "master/admin/search";
	}
	
	
	
	@Override
	protected void specificSearch(Object object, StringBuilder buffer,
			Map<String, Object> parameters, String operator, String prefix, String suffix) {
		
		Customer customer = (Customer) object;
		
		System.out.println(customer +"::Employee employee = (Employee) object; employeeController");
		
		boolean isAppendAnd = false;
//		untuk searcing bedasarkan id
		if(StringUtils.isNotBlank(customer.getId())) {
			buffer.append("customer.id " + operator + " :id");
			
			parameters.put("id", prefix + customer.getId() + suffix);
			
			isAppendAnd = true;
		}
//		untuk searcing bedasarkan name
		if(StringUtils.isNotBlank(customer.getName())) {
			if(isAppendAnd) {
				buffer.append(" and ");
			}
			buffer.append("customer.name " + operator + " :name");
			
			parameters.put("name", prefix + customer.getName() + suffix);
			
			isAppendAnd = true;
		}
//		untuk searcing bedasarkan email
		if(StringUtils.isNotBlank(customer.getEmail())) {
			if(isAppendAnd) {
				buffer.append(" and ");
			}
			buffer.append("customer.email " + operator + " :email");
			
			parameters.put("email", prefix + customer.getEmail()+ suffix);
			
			isAppendAnd = true;
		}
//		untuk searcing bedasarkan phone
		if(StringUtils.isNotBlank(customer.getNo_tlp())) {
			if(isAppendAnd) {
				buffer.append(" and ");
			}
			buffer.append("customer.No_tlp " + operator + " :No_tlp");
			
			parameters.put("No_tlp", prefix + customer.getNo_tlp()+ suffix);
			
			isAppendAnd = true;
		}
	}
		
	@RequestMapping(value="/detail",method =RequestMethod.GET)
	public String open(@ModelAttribute Customer customer, ModelMap modelMap){
		
		modelMap.put("mode", Constant.MODE_CREATE);
		
				if (StringUtils.isBlank(customer.getId())){
					IdGenerator id = new IdGenerator();
					modelMap.put("idGenrator", id.getNipCustomer());
				}else if  (StringUtils.isNotBlank(customer.getId())){
					customer = databaseService.findCustomerById(customer.getId());
						
						if(customer != null){
							modelMap.put("mode", Constant.MODE_UPDATE);
							modelMap.put("customer", customer);
						}
				}
		return"master/admin/detail";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public String save(@RequestParam int mode, @ModelAttribute Customer customer, BindingResult result, ModelMap modelMap){
		modelMap.put("mode", mode);
		IdGenerator id = new IdGenerator();
		modelMap.put("idGenrator", id.getNipCustomer());
		new BaseValidator().validate(customer, result);
		
		if (result.hasErrors()){
			addMessage(modelMap, "masih ada yang belum di isi, silahkan di cek kembali. ", MessageType.error, false);
			return "master/admin/detail";
		}
		if (mode == Constant.MODE_CREATE){
			Customer existing = databaseService.findCustomerById(customer.getId());
			if(existing != null){
				addMessage(modelMap, " Karyawan dengan ID: " + customer.getId() +" sudah ada. ", MessageType.error, false);
				return "master/admin/detail";
			}
			databaseService.saveCustomer(customer);
			addMessage(modelMap, " Data karyawan berhasil di tambah.", MessageType.success, true);
		}else{
			
			databaseService.saveCustomer(customer);
			addMessage(modelMap, " Data karyawan berhasil di update.", MessageType.success, true);
		}
		return "redirect:detail?id=" + customer.getId();
	}

}
