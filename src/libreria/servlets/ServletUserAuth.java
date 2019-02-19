package libreria.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import libreria.controllers.CtrlSocio;
import libreria.entities.Socio;
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
				if (request.getSession().getAttribute("loggedType") == null) {
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

		if (ctrl.getByUsername(s) == null) { // El username está disponible

			Socio nuevoSocio = ctrl.add(s);
			request.getSession().setAttribute("loggedUser", nuevoSocio);

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
		Socio loginSocio = new Socio();
		loginSocio.setUsername(request.getParameter("inputUsername"));
		loginSocio.setPassword(request.getParameter("inputPassword"));

		CtrlSocio ctrl = new CtrlSocio();

		if (ctrl.validateLogin(loginSocio).getId() == -1) {
			request.getSession().setAttribute("errorMsg", "El usuario no existe o la contraseña es incorrecta");
			response.sendRedirect("login");
		} else {
			loginSocio = ctrl.getByUsername(loginSocio);
			request.getSession().setAttribute("loggedUser", loginSocio);
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
