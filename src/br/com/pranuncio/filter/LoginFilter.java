package br.com.pranuncio.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.pranuncio.controller.LoginController;

public class LoginFilter implements Filter {
	
	@Inject
	private LoginController loginController;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
						 FilterChain chain)
			throws IOException, ServletException { 
		HttpServletRequest hsrq = ((HttpServletRequest) request); 
		String contextPath = hsrq.getContextPath(); 
		HttpServletResponse hsrp = ((HttpServletResponse) response);  
		if (!loginController.isLogado()) { 
			hsrp.sendRedirect(contextPath + "/index.xhtml"); 
		} 
		chain.doFilter(request, response); 
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}
	
	public String cadastroUsuario(){
		return "cadUsuario";
	}

}
