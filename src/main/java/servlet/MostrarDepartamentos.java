package main.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import main.java.dao.DepartamentoDao;
import main.java.entities.Departamento;




/**
 * Servlet implementation class MostrarDepartamentos
 */
@WebServlet("/MostrarDepartamentos")
public class MostrarDepartamentos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static SessionFactory sessionFactory;
	List<Departamento> listaDepartamentos;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarDepartamentos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		sessionFactory = buildSessionFactory();
	}
		

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter res = response.getWriter();
		listaDepartamentos  = DepartamentoDao.listarDepartamentos(sessionFactory.openSession());
		res.println("<html>");
			res.println("<head>");
				res.println("<title>Listado departamentos</title>");
			res.println("</head>");
			res.println("<body>");
				res.println("<table style='border: 1px solid black;'>");
					res.println("<tr>");
						res.println("<th>Codigo Departamento</th>");
						res.println("<th>Nombre</th>");
						res.println("<th>Codigo Responsable</th>");
					res.println("</tr>");
					for(Departamento departamento: listaDepartamentos) {
					res.println("<tr>");
						res.println("<td>"+departamento.getCodigo()+"<td>");
						res.println("<td>"+departamento.getNombre()+"<td>");
						res.println("<td>"+departamento.getNombre()+"<td>");
					res.println("</tr>");
					}
				res.print("</table>");
			res.println("</body>");
		res.println("</html>");
		res.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static SessionFactory buildSessionFactory() {
		String methodName = MostrarDepartamentos.class.getSimpleName() + ".buildSessionFactory()";

		final StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

		try {
			Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			SessionFactory sessionFactory= metadata.getSessionFactoryBuilder().build();
			return sessionFactory;
		} catch (Exception ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

}
