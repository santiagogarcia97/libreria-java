package libreria.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import libreria.controllers.CtrlUsuario;
import libreria.entities.Usuario;
import libreria.utils.CustomException;

/**
 * Servlet implementation class ServletUserAuth
 */
@WebServlet({ "/auth/*" })
public class ServletUserAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletUserAuth() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			switch (request.getPathInfo()) {
			case "/signup":
				if (request.getSession().getAttribute("loggedUser") == null) {
					request.getRequestDispatcher("/WEB-INF/pages/signup.jsp").forward(request, response);
				} else {
					response.sendRedirect("/libreria-java/home");
				}
				break;
			case "/login":
				if (request.getSession().getAttribute("loggedUser") == null) {
					request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
				} else {
					response.sendRedirect("/libreria-java/home");
				}
				break;
			case "/logout":
				this.logOut(request, response);
				break;
			default:
				response.sendRedirect("/libreria-java/home");
				break;
			}
		} catch (CustomException e) {
			request.getSession().setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, CustomException, ServletException {
		try {
			switch (request.getPathInfo()) {
			case "/signup":
				this.signUp(request, response);
				break;

			case "/login":
				this.logIn(request, response);
				break;

			case "/logout":
				this.logOut(request, response);
				break;

			default:
				response.sendRedirect("/libreria-java/home");
				break;
			}
		} catch (CustomException e) {
			request.getSession().setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
		}
	}

	//////////////////////
	// SIGN UP LOGIC
	//////////////////////
	private void signUp(HttpServletRequest request, HttpServletResponse response) throws CustomException, IOException {

		Usuario u = new Usuario();
		u.setNombre(request.getParameter("inputNombre"));
		u.setApellido(request.getParameter("inputApellido"));
		u.setEmail(request.getParameter("inputEmail"));
		u.setDomicilio(request.getParameter("inputDomicilio"));
		u.setTelefono(request.getParameter("inputTelefono"));
		u.setDni(request.getParameter("inputDni"));
		u.setPassword(request.getParameter("inputPassword"));

		CtrlUsuario ctrl = new CtrlUsuario();

		if (ctrl.getByEmail(u) == null) { // El username est� disponible

			Usuario nuevoUsuario = ctrl.add(u);
			request.getSession().setAttribute("loggedUser", nuevoUsuario);

			response.sendRedirect("/libreria-java/home");
		} else {
			request.getSession().setAttribute("errorMsg", "El nombre de usuario no se encuentra disponible");
			response.sendRedirect("signup");
		}
	}

	//////////////////////
	// LOG IN LOGIC
	//////////////////////
	private void logIn(HttpServletRequest request, HttpServletResponse response) throws CustomException, IOException {
		Usuario loginUser = new Usuario();
		loginUser.setEmail(request.getParameter("inputEmail"));
		loginUser.setPassword(request.getParameter("inputPassword"));

		CtrlUsuario ctrl = new CtrlUsuario();

		if (ctrl.validateLogin(loginUser).getId() == -1) {
			request.getSession().setAttribute("errorMsg", "El usuario no existe o la contraseña es incorrecta");
			response.sendRedirect("login");
		} else {
			loginUser = ctrl.getByEmail(loginUser);
			request.getSession().setAttribute("loggedUser", loginUser);
			response.sendRedirect("/libreria-java/home");
		}

	}

	//////////////////////
	// LOG OUT LOGIC
	//////////////////////
	private void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect("/libreria-java/home");
	}
}
