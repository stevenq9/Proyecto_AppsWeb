package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.UsuarioYaExisteException;
import modelo.dao.DAOFactory;
import modelo.entidades.Persona;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String USER_SESSION_NAME = "personaLogeada";
	private Persona personaLogeada;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.procesarRuta(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.procesarRuta(request, response);
	}

	private void procesarRuta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.personaLogeada = (Persona) request.getSession().getAttribute(USER_SESSION_NAME);

		String ruta = "ingresar";

		if (request.getParameter("ruta") != null)
			ruta = request.getParameter("ruta");

		if (personaLogeada != null && !ruta.equals("salir")) {
			response.sendRedirect(request.getContextPath() + "/GestionarCuentasController");
			return;
		}

		switch (ruta) {
		case "ingresar":
			this.ingresar(request, response);
			break;
		case "login":
			this.login(request, response);
			break;
		case "registro":
			this.registrar(request, response);
			break;
		case "salir":
			this.salir(request, response);
		}
	}

	private void ingresar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("txtUsuarioLogin");
		String clave = request.getParameter("txtClaveLogin");

		Persona persona = DAOFactory.getFactory().getPersonaDAO().autorizar(usuario, clave);

		if (persona == null) {
			request.setAttribute("mensaje", "El usuario o la contraseña no son correctas.");
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute(USER_SESSION_NAME, persona);
			response.sendRedirect(request.getContextPath() + "/GestionarCuentasController");
		}

	}

	private void registrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usuario = request.getParameter("txtUsuarioRegistro");
		String clave = request.getParameter("txtClaveRegistro");
		String apellido = request.getParameter("txtApellidoRegistro");
		String nombre = request.getParameter("txtNombreRegistro");

		Persona persona = new Persona(apellido, nombre, usuario, clave);

		try {
			DAOFactory.getFactory().getPersonaDAO().create(persona);
			request.setAttribute("mensaje", "La cuenta se ha creado exitosamente.");
		} catch (UsuarioYaExisteException e) {
			request.setAttribute("mensaje", e.getMessage());
		}
		request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	}

	private void salir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		redirectMe(request, response);
	}

	public static void redirectMe(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/LoginController");
	}

}
