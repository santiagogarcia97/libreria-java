package libreria.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import libreria.controllers.*;
import libreria.entities.*;
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
				CtrlCategoria ctrl;
				
				switch (request.getPathInfo()) {			
					case "/alta-libro":
						request.setAttribute("adminPage", "altaLibro");
						ctrl = new CtrlCategoria();
						request.setAttribute("categorias", ctrl.getAll());
						break;
					case "/alta-cat-libro":
						ctrl = new CtrlCategoria();
						request.setAttribute("categorias", ctrl.getAll());
						request.setAttribute("adminPage", "altaCatLibro");
						break;	
					default:
						request.setAttribute("adminPage", "stats");
						break;
				}
				
				request.getRequestDispatcher( "/WEB-INF/pages/admin/adminPanel.jsp" ).forward( request, response );
			
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
		
		try {
			
			if(isAdmin(request)) {
				switch (request.getPathInfo()) {		
				case "/alta-libro":
					this.altaLibro(request, response);
					response.sendRedirect("/libreria-java/admin/alta-libro");
					break;	
				case "/alta-cat-libro":
					this.altaCatLibro(request, response);
					response.sendRedirect("/libreria-java/admin/alta-cat-libro");
					break;	
				}
				
			} else {
				response.sendRedirect("/libreria-java/home");
			}	
			
			
		} catch (CustomException e) {
			request.getSession().setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher( "/WEB-INF/pages/error.jsp" ).forward( request, response );
		} catch (Exception ex) {
			CustomException e = new CustomException("Error desconocido", "ServletAdmin", ex);
			request.getSession().setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher( "/WEB-INF/pages/error.jsp" ).forward( request, response );
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
	private void altaLibro(HttpServletRequest req, HttpServletResponse res) throws CustomException, ServletException, IOException {
		Libro l = new Libro();
		
		l.setAutor(req.getParameter("inputAutor"));
		l.setTitulo(req.getParameter("inputTitulo"));
		l.setEdicion(req.getParameter("inputEdicion"));
		l.setFechaEdicion(Date.valueOf(req.getParameter("inputFechaEdicion")));
		l.setIsbn(req.getParameter("inputISBN"));
		l.setDiasMaxPrestamo(Integer.parseInt(req.getParameter("inputMaxDias")));
		l.setTapa(req.getParameter("inputTapa"));
		
		l.setCat(new Categoria());
		l.getCat().setId(Integer.parseInt(req.getParameter("inputCategoria")));
		
		l.setEstado("disponible");
		
		CtrlLibro ctrl = new CtrlLibro();
		l = ctrl.add(l);
		
	}
	//////////////////////
	// ALTA CATEGORIA LOGIC
	//////////////////////
	private void altaCatLibro(HttpServletRequest req, HttpServletResponse res) {
		Categoria c = new Categoria();
		
		c.setDesc(req.getParameter("inputDesc"));
		c.setEstado("habilitado");
		
		CtrlCategoria ctrl = new CtrlCategoria();
		c = ctrl.add(c);
		
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

