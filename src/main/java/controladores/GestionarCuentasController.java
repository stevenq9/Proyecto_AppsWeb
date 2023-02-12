package controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Chaucherita;
import modelo.Cuenta;
import modelo.CuentaDeGastos;
import modelo.CuentaDeIngresos;
import modelo.CuentaDeIngresosYGastos;

@WebServlet("/GestionarCuentasController")
public class GestionarCuentasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Chaucherita chaucherita;

	public GestionarCuentasController() {
		super();
		this.chaucherita = Chaucherita.getInstancia();
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
		String ruta = "listarCuenta";

		if (request.getParameter("ruta") != null)
			ruta = request.getParameter("ruta");

		switch (ruta) {
		case "listarCuenta":
			this.listarCuenta(request, response);
			break;
		case "crearCuenta":
			this.crearCuenta(request, response);
			break;
		case "modificarCuenta":
			this.modificar(request, response);
			break;
		case "guardar":
			this.guardar(request, response);
			break;
		case "detallarCuenta":
			this.detallarCuenta(request, response);
			break;
		}
	}

	private void listarCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.- Obtengo datos de la solicitud
		// Ninguno
		// 2.- Llamo al modelo
		List<Cuenta> cuentasDeGastos = chaucherita.getCuentas(CuentaDeGastos.class);
		List<Cuenta> cuentasDeIngresos = chaucherita.getCuentas(CuentaDeIngresos.class);
		List<Cuenta> cuentaDeIngresosYGastos = chaucherita.getCuentas(CuentaDeIngresosYGastos.class);

		// 3.- Llamo a la vista
		request.setAttribute("cuentasDeGastos", cuentasDeGastos);
		request.setAttribute("cuentasDeIngresos", cuentasDeIngresos);
		request.setAttribute("cuentaDeIngresosYGastos", cuentaDeIngresosYGastos);

		request.getRequestDispatcher("jsp/listarCuenta.jsp").forward(request, response);
	}

	private void crearCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("jsp/crearCuenta.jsp").forward(request, response);
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Cuenta cuenta = chaucherita.obtenerCuentaPorId(Integer.parseInt(id));	
		request.setAttribute("cuenta", cuenta);
		request.getRequestDispatcher("jsp/ingresarDatosCuenta.jsp").forward(request, response);
	}

	private void guardar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = request.getParameter("txtRuta");
		String nombre = request.getParameter("txtNombre");
		String id = request.getParameter("id");

		switch (ruta) {
		case "crearCuenta":
			String tipo = request.getParameter("txtTipo");
			Cuenta cuenta = null;

			switch (tipo) {
			case "modelo.CuentaDeIngresos":
				cuenta = new CuentaDeIngresos(0, nombre);
				break;

			case "modelo.CuentaDeGastos":
				cuenta = new CuentaDeGastos(0, nombre);
				break;

			case "modelo.CuentaDeIngresosYGastos":
				cuenta = new CuentaDeIngresosYGastos(0, nombre);
				break;
			}
			if (cuenta != null) {
				this.chaucherita.agregar(cuenta);
			}
		case "modificarC":
			//Metodo
			chaucherita.modificar(Integer.parseInt(id), nombre);
			break;
		}
		
		response.sendRedirect(request.getContextPath() + "/GestionarCuentasController?ruta=listarCuenta");
	}

	private void detallarCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.getRequestDispatcher("/GestionarTransaccionesController?ruta=detallarCuenta&id=" + id).forward(request,
				response);
	}
}
