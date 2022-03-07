package com.doctorbari.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.doctorbari.model.User;
import com.doctorbari.service.UserService;


@Component
public class AuthenticationUser implements AuthenticationSuccessHandler {

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		User theUser = userService.findByUserName(authentication.getName());
		
		
//		String url = "";
//		//HttpSession session = request.getSession();
//		if (theUser.getRolename().equals("ROLE_ADMIN")) {
//			url = "/admin/home";
//			//session.setAttribute("admin", theUser);
//		}
//		response.sendRedirect(request.getContextPath() + url);
		
		
		String url = "";
		
		if (theUser.getRolename().equals("ROLE_USER")) {
			url = "/";
		}
		
		if (theUser.getRolename().equals("ROLE_ADMIN")) {
			url = "/admin/home";
		}
		
		if (theUser.getRolename().equals("ROLE_DOCTOR")) {
			url = "/doctor/dashboard";
		}
		
		response.sendRedirect(request.getContextPath() + url);
	
		
	}

}
