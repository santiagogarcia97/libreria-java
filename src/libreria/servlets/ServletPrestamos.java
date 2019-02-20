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
import libreria.entities.Ejemplar;
import libreria.entities.Libro;
import libreria.entities.LineaDePrestamo;
import libreria.entities.Prestamo;
import libreria.entities.Usuario;

/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/prestamos")
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

		if (request.getSession().getAttribute("loggedUser") != null) {
			Usuario loggedUser = (Usuario) request.getSession().getAttribute("loggedUser");

			if (loggedUser.getTipoUsuario().equals("socio")) {
				if (request.getParameter("action") != null) {
					switch (request.getParameter("action")) {
					case "add":
						this.add(request, response);
						break;
					case "remove":
						this.remove(request, response);
						break;
					case "solicitar":
						this.consultarDisponibilidad(request, response);
						break;
					}
				} else {
					request.getRequestDispatcher("/WEB-INF/pages/prestamos.jsp").forward(request, response);
				}
			}
		} else {
			response.sendRedirect("/libreria-java/auth/login");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/////////////////////////////////////
	// AGREGAR LIBRO AL PRESTAMO ACTUAL
	/////////////////////////////////////
	@SuppressWarnings("unchecked")
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("id") != null) {
			ArrayList<Libro> prestamoActual;
			if (request.getSession().getAttribute("prestamoActual") != null) {
				prestamoActual = (ArrayList<Libro>) request.getSession().getAttribute("prestamoActual");
			} else {
				prestamoActual = new ArrayList<>();
			}
			CtrlLibro ctrl = new CtrlLibro();
			Libro l = new Libro();
			l.setId(Integer.parseInt(request.getParameter("id")));
			prestamoActual.add(ctrl.getById(l));

			request.getSession().setAttribute("prestamoActual", prestamoActual);
			response.sendRedirect("/libreria-java/prestamos");
		}
	}

	/////////////////////////////////////
	// ELIMINAR LIBRO DEL PRESTAMO ACTUAL
	/////////////////////////////////////
	@SuppressWarnings("unchecked")
	private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("index") != null) {
			if (request.getSession().getAttribute("prestamoActual") != null) {
				ArrayList<Libro> prestamoActual = (ArrayList<Libro>) request.getSession()
						.getAttribute("prestamoActual");
				prestamoActual.remove(Integer.parseInt(request.getParameter("index")));

				request.getSession().setAttribute("prestamoActual", prestamoActual);
			}
			response.sendRedirect("/libreria-java/prestamos");
		}

	}

	/////////////////////////////////////
	// CONSULTAR DISPONIBILIDAD DE EJEMPLARES
	/////////////////////////////////////
	@SuppressWarnings("unchecked")
	private void consultarDisponibilidad(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (request.getSession().getAttribute("prestamoActual") != null) {
			ArrayList<Libro> prestamoActual = (ArrayList<Libro>) request.getSession().getAttribute("prestamoActual");
			
			if (prestamoActual.size() > 0) {
				CtrlEjemplar ctrlEjemplar = new CtrlEjemplar();

				ArrayList<Ejemplar> ejemplaresPrestamo = new ArrayList<>();
				boolean ejemplaresDisponibles = true;

				for (Libro l : prestamoActual) {
					Ejemplar e = ctrlEjemplar.getOneByLibro(l);
					if (e.getId() != -1) {
						ejemplaresPrestamo.add(e);
					} else {
						ejemplaresDisponibles = false;
						break;
					}
				}
				
				if( ejemplaresDisponibles == true) {
					this.registrarPrestamo(request, response, ejemplaresPrestamo);
				}
				else {
					request.getSession().setAttribute("errorMsg", "No se pudo solicitar el prestamo. No hay ejemplares disponibles");
					response.sendRedirect("/libreria-java/prestamos");
				}
			}
		}
	}
	
	
	/////////////////////////////////////
	// REGISTRAR PRESTAMO
	/////////////////////////////////////	
	private void registrarPrestamo(HttpServletRequest request, HttpServletResponse response, ArrayList<Ejemplar> ejemplares) {
		Prestamo prestamo = new Prestamo();
		prestamo.setSocio((Usuario)request.getSession().getAttribute("loggedUser"));
		prestamo.setFechaHoraSolicitud(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		prestamo.setEstado("Preparacion");
		
		for(Ejemplar e : ejemplares) {
			LineaDePrestamo lp = new LineaDePrestamo();
			lp.setEjemplar(e);
			lp.setEstado("Preparacion");
			prestamo.addLinea(lp);
		}
		
		CtrlPrestamo ctrlPrestamo = new CtrlPrestamo();
		ctrlPrestamo.add(prestamo);
		
	}

}
