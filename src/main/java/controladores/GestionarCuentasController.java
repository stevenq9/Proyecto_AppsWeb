package controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.CatalogoDeCuentas;
import modelo.CatalogoDeMovimientos;
import modelo.Chaucherita;
import modelo.dao.DAOFactory;
import modelo.entidades.Cuenta;
import modelo.entidades.CuentaDeGastos;
import modelo.entidades.CuentaDeIngresos;
import modelo.entidades.CuentaDeIngresosYGastos;
import modelo.entidades.Persona;

@WebServlet("/GestionarCuentasController")
public class GestionarCuentasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Persona persona;

	public GestionarCuentasController() {
		super();
		persona = DAOFactory.getFactory().getPersonaDAO().getById(1);
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
		List<Cuenta> cuentasDeGastos = DAOFactory.getFactory().getCuentaDAO().getCuentasGastosPorPersona(persona);
		List<Cuenta> cuentasDeIngresos = DAOFactory.getFactory().getCuentaDAO().getCuentasIngresosPorPersona(persona);
		List<Cuenta> cuentaDeIngresosYGastos = DAOFactory.getFactory().getCuentaDAO().getCuentasIngresosYGastosPorPersona(persona);

		// 3.- Llamo a la vista
		request.setAttribute("cuentasDeGastos", cuentasDeGastos);
		request.setAttribute("cuentasDeIngresos", cuentasDeIngresos);
		request.setAttribute("cuentaDeIngresosYGastos", cuentaDeIngresosYGastos);

		request.getRequestDispatcher("jsp/listarCuenta.jsp").forward(request, response);
	}

	private void crearCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("jsp/ingresarDatosCuenta.jsp").forward(request, response);
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Cuenta cuenta = DAOFactory.getFactory().getCuentaDAO().getById(Integer.parseInt(id));
		request.setAttribute("cuenta", cuenta);
		request.getRequestDispatcher("jsp/ingresarDatosCuenta.jsp").forward(request, response);
	}

	private void guardar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = request.getParameter("txtRuta");
		String nombre = request.getParameter("txtNombre");

		switch (ruta) {
		case "crearCuenta":
			String tipo = request.getParameter("txtTipo");
			Cuenta cuenta = null;

			switch (tipo) {
			case "modelo.CuentaDeIngresos":
				cuenta = new CuentaDeIngresos(nombre, persona);
				break;

			case "modelo.CuentaDeGastos":
				cuenta = new CuentaDeGastos(nombre, persona);
				break;

			case "modelo.CuentaDeIngresosYGastos":
				cuenta = new CuentaDeIngresosYGastos(nombre, persona);
				break;
			}

			if (cuenta != null) {
				DAOFactory.getFactory().getCuentaDAO().create(cuenta);
			}
			break;
		case "modificarCuenta":
			// Metodo
			String id = request.getParameter("txtId");
			
			Cuenta cuentaAModificar = DAOFactory.getFactory().getCuentaDAO().getById(Integer.parseInt(id));
			cuentaAModificar.setNombre(nombre);
			DAOFactory.getFactory().getPersonaDAO().update(persona);
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