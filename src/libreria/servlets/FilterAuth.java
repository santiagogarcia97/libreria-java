package libreria.servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FilterAuth
 */
@WebFilter("/auth/*")
public class FilterAuth implements Filter {

    /**
     * Default constructor. 
     */
    public FilterAuth() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
			switch (req.getPathInfo()) {
			case "/signup":
			case "/login":
				if(req.getSession().getAttribute("loggedType") == null) {
					chain.doFilter(request, response);
				}
				else {
					res.sendRedirect("/libreria-java/home");			
				}
				break;								
			case "/logout":
				chain.doFilter(request, response);
				break;	
			default:
				chain.doFilter(request, response);
				break;
			}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
