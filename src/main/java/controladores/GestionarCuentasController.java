package controladores;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.EstadoDeCuenta;
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
		persona = (Persona) request.getSession().getAttribute(LoginController.USER_SESSION_NAME);

		if (persona == null) {
			LoginController.redirectMe(request, response);
			return;
		}
		
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
		}
	}

	private void listarCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.- Obtengo datos de la solicitud
		String fechaInicial = request.getParameter("fechaInicial");
		String fechaFinal = request.getParameter("fechaFinal");
		
		Date fechaInicialDate;
		Date fechaFinalDate;
		
		if(fechaInicial == null || fechaFinal == null) {
			fechaInicialDate = Date.valueOf(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1));
			fechaFinalDate = Date.valueOf(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), (LocalDate.now().isLeapYear())?LocalDate.now().getMonth().maxLength():LocalDate.now().getMonth().minLength()));
		}else if(LocalDate.parse(fechaInicial).isAfter(LocalDate.parse(fechaFinal))){
			fechaInicialDate = Date.valueOf(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1));
			fechaFinalDate = Date.valueOf(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), (LocalDate.now().isLeapYear())?LocalDate.now().getMonth().maxLength():LocalDate.now().getMonth().minLength()));
		}else {
			fechaInicialDate = Date.valueOf(fechaInicial);
			fechaFinalDate = Date.valueOf(fechaFinal);
		}
		
		request.getSession().setAttribute("fechaInicio", fechaInicialDate);
		request.getSession().setAttribute("fechaFin", fechaFinalDate);
		
		// 2.- Llamo al modelo
		List<EstadoDeCuenta> cuentasDeGastos = DAOFactory.getFactory().getCuentaDAO().getEstadoContableDeGastos(persona, fechaInicialDate, fechaFinalDate);
		List<EstadoDeCuenta> cuentasDeIngresos = DAOFactory.getFactory().getCuentaDAO().getEstadoContableDeIngresos(persona, fechaInicialDate, fechaFinalDate);
		List<EstadoDeCuenta> cuentaDeIngresosYGastos = DAOFactory.getFactory().getCuentaDAO().getEstadoContableDeIngresosYGastos(persona, fechaInicialDate, fechaFinalDate);

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
			DAOFactory.getFactory().getCuentaDAO().update(cuentaAModificar);
			break;
		}

		response.sendRedirect(request.getContextPath() + "/GestionarCuentasController?ruta=listarCuenta");
	}
}