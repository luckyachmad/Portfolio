package inv.com.util;

import inv.com.entity.Employee;
import inv.com.service.DatabaseService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
	implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

//	@Autowired private UserManagementService userManagementService;
	@Autowired private DatabaseService databaseService;
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
//		User user = databaseService.findUserByPaging(SpringSecurityUtil.getUsername());
		
//		System.out.println(user+"useruseruser");
//		jika user gagal login maka akan ada penambahan setFailedLoginAttempts pada database
//		if (user.getFailedLoginAttempts() > 0) {
//			user.setFailedLoginAttempts(0);
//			
//			databaseService.saveUser(user);
//		}
		
//		mengambil nama dari login yang akan di masukan ke tampilan
		Employee employee = databaseService.findEmployeeById(SpringSecurityUtil.getUsername());
//			String testt = employee.getName();
//			System.out.println(testt+"testttestttestt");
		if (employee == null) {
			employee = new Employee(SpringSecurityUtil.getUsername(), "No Name");
		}
		
//		untuk penamaan pada headheader pada ui
		request.getSession().setAttribute("loginEmployee", employee);
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
