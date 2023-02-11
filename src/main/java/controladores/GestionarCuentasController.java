package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GestionarCuentasController")
public class GestionarCuentasController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GestionarCuentasController() {
		super();
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
		String ruta = "listar";

		if (request.getParameter("ruta") != null)
			ruta = request.getParameter("ruta");

		switch (ruta) {
		case "listar":
			this.listar(request, response);
			break;
		case "crear":
			this.crear(request, response);
			break;
		case "modificar":
			this.modificar(request, response);
			break;
		case "guardar":
			this.guardar(request, response);
			break;
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
		}
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
	}
	
	private void crear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	private void guardar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	private void registrarIngreso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/GestionarTransaccionesController?ruta=registrarIngreso").forward(request, response);
	}
	
	private void registrarTransaccion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/GestionarTransaccionesController?ruta=registrarTransaccion").forward(request, response);
	}
	
	private void detallarCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.getRequestDispatcher("/GestionarTransaccionesController?ruta=detallarCuenta&id=" + id).forward(request, response);
	}
	
	private void mostrarEstado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/GestionarTransaccionesController?ruta=mostrarEstado").forward(request, response);
	}
}
