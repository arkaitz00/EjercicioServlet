package main.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import main.java.dao.EmpleadoDao;
import main.java.entities.Departamento;
import main.java.entities.Empleado;

/**
 * Servlet implementation class MostrarEmpleados
 */
@WebServlet("/MostrarEmpleados")
public class MostrarEmpleados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static SessionFactory sessionFactory;
	static Logger logger = LogManager.getLogger(MostrarDatos.class);
	List<Empleado> listaEmpleados;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarEmpleados() {
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
		PrintWriter pw = response.getWriter();
		listaEmpleados = EmpleadoDao.listarDepartamentos(sessionFactory.openSession());
		if(listaEmpleados != null) {
			logger.info("Lista empleados tiene valores");
			pw.println("<html>");
				pw.println("<head>");
					pw.println("<title>Listado departamentos</title>");
				pw.println("</head>");
				pw.println("<body>");
					pw.println("<table border= \"2\">");
						pw.println("<tr>");
							pw.println("<th>Codigo Empleado</th>");
							pw.println("<th>Nombre</th>");
							pw.println("<th>Primer apellido</th>");
							pw.println("<th>Segundo apellido</th>");
							pw.println("<th>Lugar nacimiento</th>");
							pw.println("<th>Fecha nacimiento</th>");
							pw.println("<th>Direccion</th>");
							pw.println("<th>Telefono</th>");
							pw.println("<th>Puesto</th>");
							pw.println("<th>Codigo Departamento</th>");
						pw.println("</tr>");
					for(Empleado empleado: listaEmpleados) {
						pw.println("<tr>");
							pw.println("<td>"+empleado.getCodigo()+"</td>");
							pw.println("<td>"+empleado.getNombre()+"</td>");
							pw.println("<td>"+empleado.getApellido1()+"</td>");
							pw.println("<td>"+empleado.getApellido2()+"</td>");
							pw.println("<td>"+empleado.getLugarNacimiento()+"</td>");
							pw.println("<td>"+empleado.getFechaNacimiento()+"</td>");
							pw.println("<td>"+empleado.getDireccion()+"</td>");
							pw.println("<td>"+empleado.getTelefono()+"</td>");
							pw.println("<td>"+empleado.getPuesto()+"</td>");
							pw.println("<td>"+empleado.getCodDepartamento()+"</td>");
						pw.println("</tr>");
					}
					pw.print("</table>");
				pw.println("</body>");
			pw.println("</html>");
			pw.close();
		}else {
			logger.error("Lista de empleados no tiene ningun valor");
		}
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
