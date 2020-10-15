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
import java.util.Enumeration;

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
					case "/alta-libro":{
						request.setAttribute("adminPage", "altaLibro");
						CtrlCategoria ctrl = new CtrlCategoria();
						request.setAttribute("categorias", ctrl.getAll());
						}
						break;
					case "/listado-cat-libro":{
						CtrlCategoria ctrl = new CtrlCategoria();
						request.setAttribute("categorias", ctrl.getAll());
						request.setAttribute("adminPage", "listadoCatLibro");
						}
						break;
					case "/listado-libro":{
						CtrlLibro ctrl = new CtrlLibro();
						request.setAttribute("libros",ctrl.getAll());
						request.setAttribute("adminPage", "listadoLibro");
						}
						break;
					case "/edit-libro":{
						CtrlLibro ctrl = new CtrlLibro();
						request.setAttribute("libros",ctrl.getAll());
						CtrlCategoria ctrlCat = new CtrlCategoria();
						request.setAttribute("categorias", ctrlCat.getAll());
						request.setAttribute("adminPage", "editLibro");
						}
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
					response.sendRedirect("/libreria-java/admin/listado-libro");
					break;	
				case "/listado-cat-libro/alta":
					this.altaCatLibro(request, response);
					response.sendRedirect("/libreria-java/admin/listado-cat-libro");
					break;	
				case "/listado-cat-libro/modificar":
					this.modificarCatLibro(request,response);
					response.sendRedirect("/libreria-java/admin/listado-cat-libro");
					break;
				case "/listado-cat-libro/eliminar":
					this.eliminarCatLibro(request,response);
					response.sendRedirect("/libreria-java/admin/listado-cat-libro");
					break;
				case "/edit-libro":
					this.modificarLibro(request,response);
					response.sendRedirect("/libreria-java/admin/listado-libro");
					break;
				case "/listado-libro/eliminar":
					this.eliminarLibro(request,response);
					response.sendRedirect("/libreria-java/admin/listado-libro");
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
		if(req.getSession().getAttribute("loggedUser") != null) { 
			Usuario u = (Usuario)req.getSession().getAttribute("loggedUser");
			if(u.getTipoUsuario().equals("admin"))
				return true;
		} 
		return false;
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
	// MODIFICAR LIBRO LOGIC
	//////////////////////
	private void modificarLibro(HttpServletRequest req, HttpServletResponse res) throws CustomException, ServletException, IOException {
		Libro l = new Libro();
		l.setId(Integer.parseInt(req.getParameter("inputID")));
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
		//TODO: este set de estado está bien?
		//TODO: no se establece la desc de la categoría?
		CtrlLibro ctrl = new CtrlLibro();
		ctrl.update(l);
		
	}
	
	
	//////////////////////
	// ELIMINAR LIBRO LOGIC
	//////////////////////	
	private void eliminarLibro(HttpServletRequest req, HttpServletResponse res) {
		Libro l = new Libro();
		l.setId(Integer.parseInt(req.getParameter("inputID")));
		CtrlLibro ctrl = new CtrlLibro();
		ctrl.delete(l);
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
	
	
	//////////////////////
	// MODIFICAR CATEGORIA LOGIC
	//////////////////////	
	private void modificarCatLibro(HttpServletRequest req, HttpServletResponse res) {
		CtrlCategoria ctrl = new CtrlCategoria();
		Categoria c = new Categoria();		
		c.setId(Integer.parseInt(req.getParameter("inputID")));
		c.setDesc(req.getParameter("inputDesc"));
		c.setEstado(req.getParameter("inputEstado"));
		ctrl.update(c);
		
	}
	
	//////////////////////
	// ELIMINAR CATEGORIA LOGIC
	//////////////////////	
	private void eliminarCatLibro(HttpServletRequest req, HttpServletResponse res) {
		CtrlCategoria ctrl = new CtrlCategoria();
		Categoria c = new Categoria();		
		c.setId(Integer.parseInt(req.getParameter("inputID")));
		c.setDesc(req.getParameter("inputDesc"));
		c.setEstado(req.getParameter("inputEstado"));
		ctrl.delete(c);
	}
}

