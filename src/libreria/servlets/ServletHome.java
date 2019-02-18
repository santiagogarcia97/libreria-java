package libreria.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import libreria.controllers.CtrlCategoria;
import libreria.controllers.CtrlLibro;
import libreria.entities.*;

/**
 * Servlet implementation class ServletHome
 */
@WebServlet("/home")
public class ServletHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlCategoria ctrlCat = new CtrlCategoria();
		CtrlLibro ctrlLibro = new CtrlLibro();
		
		request.setAttribute("categorias", ctrlCat.getAll());
		
		if(request.getParameter("cat") != null && request.getParameter("libro") == null) {
			Categoria c = new Categoria();
			c.setId(Integer.parseInt(request.getParameter("cat")));
			
			request.setAttribute("libros", ctrlLibro.getByCat(c));
		}
		else if(request.getParameter("cat") == null && request.getParameter("libro") != null){
			Libro l = new Libro();
			l.setId(Integer.parseInt(request.getParameter("libro")));
			
			request.setAttribute("libro", ctrlLibro.getById(l));
			
		}
		else {
			request.setAttribute("libros", ctrlLibro.getAll());
		}
		

		request.getRequestDispatcher( "/WEB-INF/pages/index.jsp" ).forward( request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
