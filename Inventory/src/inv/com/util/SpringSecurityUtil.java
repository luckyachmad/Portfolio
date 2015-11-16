package inv.com.util;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SpringSecurityUtil {

	public static String getUsername() {
		if (getAuthentication() == null) {
			return null;
		}
		
//		untuk menyimpan name di dalam sever
		Object principal = getAuthentication().getPrincipal();
//		untuk pengecekan apakah UserDetail bagian dari principal
		if (principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		} else {
			return principal.toString();
		}
	}
	
	public static Collection<GrantedAuthority> getAuthorities() {
		
		
		if (getAuthentication() == null) {
			
			return null;
		}
		return getAuthentication().getAuthorities();
	}
	
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public static boolean isAuthenticated() {
		if (getAuthentication() == null) {
			
			System.out.println("return false");
			return false;
			
		}
				
		Collection<GrantedAuthority> authorities = getAuthorities();
		if (authorities.size() == 1 && 
				StringUtils.equals(authorities.iterator().next().getAuthority(), "ROLE_ANONYMOUS")) {
			System.out.println("return false2");
			return false;
		}
		
		System.out.println("return true;");
		return true;
	}
		
	public static String encodePassword(String password) {
		PasswordEncoder encoder = new Md5PasswordEncoder();
		
		return encoder.encodePassword(password, null);
	}

    public static boolean haveAuthority(String role) {
    	if (getAuthentication() == null) {
			return false;
		}
    	
    	Collection<GrantedAuthority> authorities = getAuthorities();
        for (Iterator<GrantedAuthority> iterator=authorities.iterator();iterator.hasNext();) {
        	GrantedAuthority authority = iterator.next();
        	if (StringUtils.equals(authority.getAuthority(), role)) {
                return true;
            }
        }
        return false;
    }
}
