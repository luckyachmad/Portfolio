package inv.com.controller;

import inv.com.util.SpringSecurityUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(){
		
//		untuk pengecekan apabila login behasil
		if (SpringSecurityUtil.isAuthenticated()){
			return "redirect:/master/menu/";
		}
		
		return "login";
	}
	
	@RequestMapping(value="/master/menu", method=RequestMethod.GET)
	public String userRole(ModelMap model){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = user.getUsername();
		
		model.addAttribute("username",name);
		return "/master/menu";
	}
}
