package inv.com.controller;

import java.util.Map;

import inv.com.entity.Product;
import inv.com.entity.Supplier;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="master/admin/*")
public class SupplierController extends BaseController {
	
	@Autowired
	private DatabaseService databaseService;
	
	@RequestMapping(value="searchSupplier")
	public String search(@ModelAttribute Supplier supplier, ModelMap modelMap, PagingInfo pagingInfo){
		
		Constraint constraint = resolveSearch(supplier);
		
		SearchResult searchResult = databaseService.searchSupplier(constraint, null, true, pagingInfo.getOffset(), pagingInfo.getPageSize());
		
		modelMap.put("supplier", searchResult.getRecs());
		modelMap.put("pagingInfo", searchResult.getPagingInfo());
//		SearchResult searchResult = databaseService.sea
		
		return "/master/admin/searchSupplier";
	}
	
	@Override
	protected void specificSearch(Object object, StringBuilder buffer,
			Map<String, Object> parameters, String operator, String prefix, String suffix) {
		
		Supplier supplier = (Supplier) object;
				
		boolean isAppendAnd = false;
//		untuk searcing bedasarkan id
		if(StringUtils.isNotBlank(supplier.getId())) {
			buffer.append("product.id " + operator + " :id");
			
			parameters.put("id", prefix + supplier.getId() + suffix);
			
			isAppendAnd = true;
		}
//		untuk searcing bedasarkan name
		if(StringUtils.isNotBlank(supplier.getName())) {
			if(isAppendAnd) {
				buffer.append(" and ");
			}
			buffer.append("product.name " + operator + " :name");
			
			parameters.put("name", prefix + supplier.getName() + suffix);
			
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

	}
	
	@RequestMapping(value="detailSupplier", method = RequestMethod.GET)
	public String add(ModelMap modelMap){
		
		IdGenerator idGenerator = new IdGenerator();
		modelMap.put("idGenerator", idGenerator.noSupplier());
		modelMap.put("mode", Constant.MODE_CREATE);		
	return "/master/admin/detailSupplier";
	}
	
	@RequestMapping(value="save", method = RequestMethod.POST)
	public String save(@RequestParam String idd,
			@RequestParam String iddd,
			@RequestParam String idddd,@ModelAttribute Supplier supplier, ModelMap modelMap){
		String [] testt = idd.toString().split(",");
		String [] testt1 = iddd.toString().split(",");
		String [] testt2 = idddd.toString().split(",");
		System.out.println(testt.length+"testing");
		System.out.println(testt1.length+"testing");
		System.out.println(testt2.length+"testing");
		
		databaseService.saveSupplier(supplier);
		
		return "redirect:detailSupplier?id"+supplier.getId();
		
	}
}
