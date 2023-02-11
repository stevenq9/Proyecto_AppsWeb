package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ColeccionDeTransacciones;

@WebServlet("/GestionarTransaccionesController")
public class GestionarTransaccionesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ColeccionDeTransacciones coleccionDeTransacciones;

	public GestionarTransaccionesController() {
		super();
		this.coleccionDeTransacciones = ColeccionDeTransacciones.getInstancia();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		procesar(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		procesar(request, response);
	}
	
	private void procesar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = "regresar";

		if (request.getParameter("ruta") != null)
			ruta = request.getParameter("ruta");

		switch (ruta) {
		case "registrarIngreso":
			this.registrarIngreso(request, response);
			break;
		case "registrarTransaccion":
			this.registrarTransaccion(request, response);
			break;
		case "detallarCuenta":
			this.detallarCuenta(request, response);
			break;
		case "mostrarEstado":
			this.mostrarEstado(request, response);
			break;
		case "confirmar":
			this.confirmar(request, response);
			break;
		case "regresar":
			this.regresar(request, response);
		}
	}

	private void registrarIngreso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	private void registrarTransaccion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	private void detallarCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	private void mostrarEstado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	private void confirmar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	private void regresar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/GestionarCuentasController");
	}
}
