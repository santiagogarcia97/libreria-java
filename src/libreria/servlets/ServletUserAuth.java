package libreria.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import libreria.controllers.CtrlSocio;
import libreria.entities.Socio;

/**
 * Servlet implementation class ServletUserAuth
 */
@WebServlet({ "/auth/*"})
public class ServletUserAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUserAuth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		switch (request.getPathInfo()) {
		case "/signup":
			this.signUp(request,response);
			break;
			
		case "/signin":
			this.signIn(request,response);
			break;
			
		case "/logout":
			this.logOut(request,response);
			break;

		default:
			this.error(request,response);
			break;
		}
	}
	
	private void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Socio s = new Socio();
		s.setNombre(request.getParameter("inputNombre"));
		s.setApellido(request.getParameter("inputApellido"));
		s.setEmail(request.getParameter("inputEmail"));
		s.setDomicilio(request.getParameter("inputDomicilio"));
		s.setTelefono(request.getParameter("inputTelefono"));
		s.setDni(request.getParameter("inputDni"));
		s.setUsername(request.getParameter("inputUsername"));
		s.setPassword(request.getParameter("inputPassword"));
		
		
		CtrlSocio ctrl = new CtrlSocio();
		try {
			ctrl.add(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("loggedUser", s);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	private void signIn(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		Socio loginSocio = new Socio();
		loginSocio.setUsername(request.getParameter("inputUsername"));
		loginSocio.setPassword(request.getParameter("inputPassword"));
		
		CtrlSocio ctrl = new CtrlSocio();
		try {
			if(ctrl.validateUser(loginSocio).getId() == -1) {
				response.sendRedirect(request.getContextPath() + "/signin.jsp");
			}
			else {
				request.getSession().setAttribute("loggedUser", loginSocio);
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	private void error(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setStatus(404);
	}
}
