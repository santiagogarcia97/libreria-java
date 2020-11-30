package libreria.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import libreria.controllers.CtrlEjemplar;
import libreria.controllers.CtrlLibro;
import libreria.controllers.CtrlPrestamo;
import libreria.controllers.CtrlUsuario;
import libreria.entities.Ejemplar;
import libreria.entities.Libro;
import libreria.entities.LineaDePrestamo;
import libreria.entities.Prestamo;
import libreria.entities.Usuario;

/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/prestamos/*")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletPrestamos() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch (request.getPathInfo()) {
		case "/carrito":
			request.setAttribute("nroEjemplares", this.consultarCantEjemplares(request,response));
			Usuario u = (Usuario) request.getSession().getAttribute("loggedUser");
			request.setAttribute("sancionado", u.getSancionado());
			request.setAttribute("prestamosPage", "carrito");
			break;
		case "/prestamos-activos":
			CtrlPrestamo ctrl = new CtrlPrestamo();
			request.setAttribute("prestamos", ctrl.getAll());
			request.setAttribute("prestamosPage", "prestamosActivos");
		}
		
			
		request.getRequestDispatcher( "/WEB-INF/pages/prestamos.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch(request.getPathInfo()) {
		case "/agregar":
			this.add(request, response);
			response.sendRedirect("/libreria-java/prestamos/carrito");
			break;
		case "/carrito/eliminar":
			this.remove(request,response);
			response.sendRedirect("/libreria-java/prestamos/carrito");
			break;
		case "/carrito/confirmar":
			this.registrarPrestamo(request,response);
			response.sendRedirect("/libreria-java/home");
		}
	}

	/////////////////////////////////////
	// AGREGAR LIBRO AL PRESTAMO ACTUAL
	/////////////////////////////////////
	@SuppressWarnings("unchecked")
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("inputIDLibro") != null) {
			ArrayList<Libro> carrito;
			if (request.getSession().getAttribute("carrito") != null) {
				carrito = (ArrayList<Libro>) request.getSession().getAttribute("carrito");
			} else {
				carrito = new ArrayList<Libro>();
			}
			CtrlLibro ctrl = new CtrlLibro();
			Libro l = new Libro();
			l.setId(Integer.parseInt(request.getParameter("inputIDLibro")));
			carrito.add(ctrl.getById(l));

			request.getSession().setAttribute("carrito", carrito);
		}
	}

	/////////////////////////////////////
	// ELIMINAR LIBRO DEL PRESTAMO ACTUAL
	/////////////////////////////////////
	@SuppressWarnings("unchecked")
	private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("index") != null) {
			if (request.getSession().getAttribute("carrito") != null) {
				ArrayList<Libro> carrito = (ArrayList<Libro>) request.getSession()
						.getAttribute("carrito");
				carrito.remove(Integer.parseInt(request.getParameter("index")));
				if(carrito.size() == 0) carrito = null;
				request.getSession().setAttribute("carrito", carrito);
			}
		}
	}

	/////////////////////////////////////
	// CONSULTAR DISPONIBILIDAD DE EJEMPLARES
	/////////////////////////////////////
	@SuppressWarnings("unchecked")
	private ArrayList<Integer> consultarCantEjemplares(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<Integer> nroEjemplares = new ArrayList<Integer>();
		if (request.getSession().getAttribute("carrito") != null) {
			ArrayList<Libro> carrito = (ArrayList<Libro>) request.getSession().getAttribute("carrito");
			
			if (carrito.size() > 0) {
				CtrlEjemplar ctrlEjemplar = new CtrlEjemplar();

				for (Libro l : carrito) {
					Integer ejemplaresDisponibles = ctrlEjemplar.getCountByLibro(l);
					nroEjemplares.add(ejemplaresDisponibles);
				}
			}
		}
		return nroEjemplares;
	}
	
	
	/////////////////////////////////////
	// REGISTRAR PRESTAMO
	/////////////////////////////////////	
	@SuppressWarnings("unchecked")
	Prestamo registrarPrestamo(HttpServletRequest request, HttpServletResponse response) {
		Prestamo prestamo = new Prestamo();		
		Usuario u = (Usuario)request.getSession().getAttribute("loggedUser");
		prestamo.setSocioId(u.getId());
		prestamo.setFechaHoraSolicitud(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		prestamo.setEstado("preparacion");
		ArrayList<Libro> libros = (ArrayList<Libro>) request.getSession().getAttribute("carrito");
		
		for(Libro l : libros) {
			LineaDePrestamo lp = new LineaDePrestamo();
			CtrlEjemplar ctrl = new CtrlEjemplar();
			Ejemplar ej = ctrl.getOneByLibro(l);
			ej.setEstado("deshabilitado");
			ctrl.update(ej);
			lp.setEjemplar(ej);
			lp.setEstado("preparacion");
			prestamo.addLinea(lp);
		}
		
		CtrlPrestamo ctrlPrestamo = new CtrlPrestamo();
		prestamo = ctrlPrestamo.add(prestamo);
		request.getSession().setAttribute("carrito", null);
		return prestamo;
	}
}
