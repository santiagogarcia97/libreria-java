package libreria.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import libreria.controllers.CtrlLibro;
import libreria.entities.Libro;
import libreria.entities.Socio;

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
			Socio loggedUser = (Socio) request.getSession().getAttribute("loggedUser");
			if (loggedUser.getTipoUsuario().equals("socio")) {
				if (request.getParameter("action") != null) {
					switch (request.getParameter("action")) {
					case "add":
						this.add(request, response);
						break;
					case "remove":
						this.remove(request, response);
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
	
	@SuppressWarnings("unchecked")
	private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("index") != null) {
			ArrayList<Libro> prestamoActual;
			if (request.getSession().getAttribute("prestamoActual") != null) {
				prestamoActual = (ArrayList<Libro>)request.getSession().getAttribute("prestamoActual");
				prestamoActual.remove(Integer.parseInt(request.getParameter("index")));
				
				request.getSession().setAttribute("prestamoActual", prestamoActual);	
			}
			response.sendRedirect("/libreria-java/prestamos");	
		}		
		
	}

}
