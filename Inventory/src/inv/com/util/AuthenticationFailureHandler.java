package inv.com.util;

import inv.com.entity.User;
import inv.com.service.DatabaseService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component("authenticationFailureHandler")
public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {

	@Autowired private DatabaseService databaseService;
	
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {
		
		int failedLoginAttempts = 1;
//		untuk validasi error
		request.getSession().setAttribute("authenticationFailedMessage", "NPK atau PASSWORD salah.");
		
		String npk = (String) e.getAuthentication().getPrincipal();
		
		User user = databaseService.findUserByPaging(npk);
//		if (user != null && user.getEnabled()) {
			// increment failedLoginAttempts..
//			failedLoginAttempts = user.getFailedLoginAttempts() + 1;
		
			
//			untuk message error jika login gagal
//			request.getSession().setAttribute("authenticationFailedMessage", "NPK atau PASSWORD salah.<br/>Your account will be disabled after "+ (User.MAX_FAILED_LOGIN_ATTEMPTS - failedLoginAttempts) +" failed login attempt(s)");
//			untuk men disable karena kesalahan melebihi dari 6
//			if (failedLoginAttempts >= User.MAX_FAILED_LOGIN_ATTEMPTS) {
//				user.setEnabled(false);
//				
//				request.getSession().setAttribute("authenticationFailedMessage", "Your account is disabled. <br/>Silakan menghubungi e-CRES Administrator <a id='contact-link' style='cursor:pointer;'>(klik disini)</a>");
//			}
//			
//			user.setFailedLoginAttempts(failedLoginAttempts + 1);
//			
//			userManagementService.saveUser(user);
//		}
			
//			jika user disable
//		if (user != null && !user.getEnabled()) {
//			request.getSession().setAttribute("authenticationFailedMessage", "Your account is disabled. <br/>Silakan menghubungi e-CRES Administrator <a id='contact-link' style='cursor:pointer;'>(klik disini)</a>");
//		}
		
		response.sendRedirect("login?error=" + failedLoginAttempts);
	}

	
}
