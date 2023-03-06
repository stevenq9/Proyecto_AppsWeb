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

import modelo.dao.DAOFactory;
import modelo.entidades.Cuenta;
import modelo.entidades.CuentaDeGastos;
import modelo.entidades.CuentaDeIngresosYGastos;
import modelo.entidades.Movimiento;
import modelo.entidades.Persona;

@WebServlet("/RetirarController")
public class RetirarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Persona persona;

	public RetirarController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.procesar(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.procesar(request, response);
	}

	private void procesar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		persona = (Persona) request.getSession().getAttribute(LoginController.USER_SESSION_NAME);

		if (persona == null) {
			LoginController.redirectMe(request, response);
			return;
		}

		String ruta = "registrarIngreso";

		if (request.getParameter("ruta") != null)
			ruta = request.getParameter("ruta");

		switch (ruta) {
		case "registrarGasto":
			this.registrarGasto(request, response);
			break;
		case "confirmar":
			this.confirmar(request, response);
			break;
		}
	}

	private void registrarGasto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtención de datos del modelo

		List<Cuenta> cuentasOrigen = DAOFactory.getFactory().getCuentaDAO()
				.getCuentasIngresosYGastosPorPersona(persona);
		List<Cuenta> cuentasDestino = DAOFactory.getFactory().getCuentaDAO().getCuentasGastosPorPersona(persona);

		// Envio de datos hacia la vista
		request.setAttribute("cuentasOrigen", cuentasOrigen);
		request.setAttribute("cuentasDestino", cuentasDestino);
		request.setAttribute("ruta", "RetirarController?ruta=confirmar");
		request.getRequestDispatcher("/jsp/ingresarDatosMovimiento.jsp").forward(request, response);
	}

	private void confirmar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		double cantidad = Double.parseDouble(request.getParameter("nmbCantidad"));
		int idCuentaOrigen = Integer.parseInt(request.getParameter("selCuentaOrigen"));
		int idCuentaDestino = Integer.parseInt(request.getParameter("selCuentaDestino"));
		String descripcion = request.getParameter("txtDescripcion");

		CuentaDeIngresosYGastos cuentaDeOrigen = (CuentaDeIngresosYGastos) DAOFactory.getFactory().getCuentaDAO()
				.getById(idCuentaOrigen);
		CuentaDeGastos cuentaDeDestino = (CuentaDeGastos) DAOFactory.getFactory().getCuentaDAO()
				.getById(idCuentaDestino);

		Movimiento m = new Movimiento(Date.valueOf(LocalDate.now()), descripcion, cantidad);
		m.configurarComoGasto(cuentaDeOrigen, cuentaDeDestino);

		try {
			cuentaDeOrigen.retirar(m);
		} catch (Exception e) {
			enviarPantallaDeError(e, request, response);
			return;
		}

		DAOFactory.getFactory().getCuentaDAO().update(cuentaDeOrigen);

		DAOFactory.getFactory().getMovimientoDAO().create(m);

		request.getRequestDispatcher("/jsp/confirmarMovimiento.jsp").forward(request, response);
	}

	private void enviarPantallaDeError(Exception e, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("huboError", true);
		request.setAttribute("mensajeDeError", e.getMessage());
		request.getRequestDispatcher("/jsp/confirmarMovimiento.jsp").forward(request, response);
	}
}
