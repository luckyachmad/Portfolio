package inv.com.controller;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import inv.com.entity.Employee;
import inv.com.entity.Role;
import inv.com.entity.User;
import inv.com.util.Constraint;
import inv.com.util.Encryption;
import inv.com.util.PagingInfo;
import inv.com.util.SearchResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserManagementController extends BaseController {
//	private final String EXTERNAL_VIEW_FOLDER = "external";
	protected final String VIEW_FOLDER = "admin";
	
	
	@RequestMapping("/master/user/user_search")
	public String search(@ModelAttribute User user,@ModelAttribute PagingInfo pagingInfo, ModelMap modelMap){
		
//		untuk penambahan query Like % %, untuk search
		Constraint constraint = resolveSearch(user);
				
		SearchResult searchResult = databaseService.searchUserWithRoles(constraint, null, true, pagingInfo.getOffset(), pagingInfo.getPageSize());	
//		Hibernate: select count(*) as col_0_0_ from user user0_, employee employee1_ where user0_.id=employee1_.id
//		Hibernate: select user0_.id as col_0_0_, employee1_.name as col_1_0_, user0_.id as id8_, user0_.enable as enable8_, user0_.password as password8_ from user user0_, employee employee1_ where user0_.id=employee1_.id limit ?
//		Hibernate: select roles0_.users_id as users1_1_, roles0_.roles_id as roles2_1_, role1_.id as id7_0_, role1_.description as descript2_7_0_ from user_role roles0_ left outer join role role1_ on roles0_.roles_id=role1_.id where roles0_.users_id=?
		modelMap.put("user", searchResult.getRecs());
		modelMap.put("pagingInfo", searchResult.getPagingInfo());
		
		modelMap.put("roles", databaseService.searchRoles(null, null, true, -1, -1).getRecs());
//			Hibernate: select count(*) as col_0_0_ from role role0_
//			Hibernate: select role0_.id as id7_, role0_.description as descript2_7_ from role role0_ limit ?

		
		return "/master/user/user_search";
	}
	
	protected void specificSearch(Object object, StringBuffer buffer,
			Map<String, Object> parameters, String operator, String prefix, String suffix) {
		
		User user = (User) object;
		
		boolean isAppendAnd = false;
		
		if (StringUtils.isNotEmpty(user.getId())) {
			buffer.append(" user.id " + operator + " :id");

			parameters.put("id", prefix + user.getId() + suffix);

			isAppendAnd = true;
		}

		if (user.getRoles() != null && user.getRoles().size() > 0) {
			if (isAppendAnd) {
				buffer.append(" and ");
			}
			buffer.append(" :role in elements(user.roles) ");

			parameters.put("role", user.getRoles().iterator().next());

			isAppendAnd = true;
		}
	}
	
	@RequestMapping(value="master/user/open/{npk}", method = RequestMethod.GET)
	public String open(@PathVariable String npk,ModelMap modelMap){
		
		modelMap.put("user", databaseService.findUserByWithRoles(npk));
		modelMap.put("employee", databaseService.findEmployeeById(npk));
		
		return "/master/user/user_edit";
	}
	
	@RequestMapping(value="/master/user/saveUser", method = RequestMethod.POST)
	public String save(@ModelAttribute User user, BindingResult result){
		
		User user2 = databaseService.findUserByWithRoles(user.getId());
		
		String[] role = result.getFieldValue("roles").toString().split(",");
		System.out.println(role.length+"testing lenght");
		Set<Role> rolesss = new HashSet<Role>();
		System.out.println(role+"roles coba");
		for (int i  = 0 ; i < role.length; i++){
			System.out.println(role+"roles coba");
			
			String rolee = role[i];
			Role role2 = databaseService.rolebyId(rolee);
														// di non aktifkan jika menggunakan code save user2.getRoles().add(role2);
			rolesss.add(role2);						  // di non aktifkan jika menggunakan code save user2.getRoles().add(role2);
														// di non aktifkan jika menggunakan code save user2.getRoles().add(role2);

//			user2.getRoles().add(role2);
			
		}
		user2.setRoles(rolesss);
		databaseService.saveUser(user2);
						
		System.out.println("testing menggunakan role yang mendapatkan akses");
		return "redirect:open/"+user.getId();
	}
	
	@RequestMapping(value="/master/user/user_add", method = RequestMethod.GET)
	public String add(){
			
		return "/master/user/user_add";
	}
	
	@RequestMapping(value="/master/user/saveEmployee", method =  RequestMethod.POST)
	public String save (@ModelAttribute Employee employee, @ModelAttribute User user, BindingResult result){
		String pass = result.getFieldValue("password").toString();
		String [] role = result.getFieldValue("roles").toString().split(",");			
		Set<Role> roless = new HashSet<Role>();
		
		System.out.println(role.length+"testing menggunakan requestParam");
		for (int i=0;i<role.length;i++){
			String rolee = role[i];
			Role role1 = databaseService.rolebyId(rolee);
			roless.add(role1);
		}		
		databaseService.saveEmployee(employee);
		user.setId(employee.getId());
		user.setPassword(Encryption.md5(pass));
		user.setRoles(roless);		
//		user.getRoles().add(e);
		databaseService.saveUser(user);
				
		return "redirect:user_add?id="+ employee.getId();
	}
	
}
