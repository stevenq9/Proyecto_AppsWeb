package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Login;
import modelo.entidades.Persona;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Login login;

	public LoginController() {
		super();
		this.login = new Login();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.procesarRuta(request, response);
	}


	private void procesarRuta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = "login";

		if (request.getParameter("ruta") != null)
			ruta = request.getParameter("ruta");

		switch (ruta) {
		case "login":
			this.login(request, response);
			break;
		case "registro":
			this.registrar(request, response);
			break;
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usuario = request.getParameter("txtUsuarioLogin");
		String clave = request.getParameter("txtClaveLogin");
		
		Persona persona = this.login.ingresar(usuario, clave);
		
		//TESTING
		if(persona == null)
			redirectMe(request, response);
		else			
			response.sendRedirect(request.getContextPath() + "/GestionarCuentasController");
	}
	
	private void registrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public static void redirectMe(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/LoginController");
	}
}
