package libreria.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import libreria.controllers.CtrlLibro;
import libreria.entities.Libro;
import libreria.utils.CustomException;
import java.sql.Date;

/**
 * Servlet implementation class ServletAdmin
 */
@WebServlet("/admin/*")
public class ServletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(isAdmin(request)) {
			try {
				switch (request.getPathInfo()) {
				case "/panel":
					request.getRequestDispatcher( "/WEB-INF/pages/admin/adminPanel.jsp" ).forward( request, response );
					break;				
				case "/alta-libro":
					request.getSession().setAttribute("adminPage", "altaLibro");
					request.getRequestDispatcher( "/WEB-INF/pages/admin/adminPanel.jsp" ).forward( request, response );
					break;
				case "/alta-cat-libro":
					request.getSession().setAttribute("adminPage", "altaCatLibro");
					request.getRequestDispatcher( "/WEB-INF/pages/admin/adminPanel.jsp" ).forward( request, response );
					break;				
				}
			} catch (CustomException e) {
			request.getSession().setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher( "/WEB-INF/pages/error.jsp" ).forward( request, response );
			}	
		}
		else {
			response.sendRedirect("/libreria-java/home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(isAdmin(request)) {
			try {
				switch (request.getPathInfo()) {		
				case "/alta-libro":
					this.altaLibro(request, response);
					break;				
				}
			} catch (CustomException e) {
			request.getSession().setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher( "/WEB-INF/pages/error.jsp" ).forward( request, response );
			}
		}
		else {
			response.sendRedirect("/libreria-java/home");
		}
	}
	
	
	
	
	
	
	
	//////////////////////
	// VALIDAR SESION
	//////////////////////
	private boolean isAdmin(HttpServletRequest req) {
		if(req.getSession().getAttribute("loggedType") != null && 
				((String)req.getSession().getAttribute("loggedType")).equals("admin")) { return true;
		} else { return false;}
	}
	//////////////////////
	// ALTA LIBRO LOGIC
	//////////////////////
	private void altaLibro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Libro l = new Libro();
		
		l.setAutor(request.getParameter("inputAutor"));
		l.setTitulo(request.getParameter("inputTitulo"));
		l.setEdicion(request.getParameter("inputEdicion"));
		l.setFechaEdicion(Date.valueOf(request.getParameter("inputFechaEdicion")));
		l.setIsbn(request.getParameter("inputISBN"));
		l.setDiasMaxPrestamo(Integer.parseInt(request.getParameter("inputMaxDias")));
		l.setEstado("disponible");
		
		CtrlLibro ctrl = new CtrlLibro();
		l = ctrl.add(l);
		request.getRequestDispatcher("/libreria-java/admin/panel").forward( request, response );
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

