package libreria.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.result.LocalDateTimeValueFactory;

import libreria.data.DataLibro;
import libreria.entities.Libro;

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
		
		DataLibro dl = new DataLibro();
/*		for(int i =0; i<50;i++) {
			Libro l = new Libro();
			l.setTitulo("Titulo "+i);
			l.setAutor("autor " + i);
			l.setIsbn(Integer.toString(i));
			l.setDiasMaxPrestamo(i);
			l.setEdicion(Integer.toString(i));
			l.setEstado("d");
			l.setFechaEdicion(Date.valueOf("2000-10-02"));
			dl.add(l);
		}*/
		
		List<Libro> libros	= dl.getAll();
		request.setAttribute("libros", libros);
		
		request.getRequestDispatcher( "/WEB-INF/pages/index.jsp" ).forward( request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
