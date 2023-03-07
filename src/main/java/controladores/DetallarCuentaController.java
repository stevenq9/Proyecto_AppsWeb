package controladores;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.EstadoDeCuenta;
import modelo.dao.DAOFactory;
import modelo.entidades.Movimiento;
import modelo.entidades.Persona;

@WebServlet("/DetallarCuentaController")
public class DetallarCuentaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Persona persona;

	public DetallarCuentaController() {
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

		String ruta = "mostrarDetalleCuenta";

		if (request.getParameter("ruta") != null)
			ruta = request.getParameter("ruta");

		switch (ruta) {
		case "mostrarDetalleCuenta":
			this.mostrarDetalleCuenta(request, response);
		}
	}

	private void mostrarDetalleCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		Date fechaInicio = (Date) request.getSession().getAttribute("fechaInicio");
		Date fechaFin = (Date) request.getSession().getAttribute("fechaFin");

		EstadoDeCuenta estadoDeCuenta = DAOFactory.getFactory().getCuentaDAO().getEstadoDeCuentaPorIdCuenta(id,
				fechaInicio, fechaFin);
		List<Movimiento> movimientos = DAOFactory.getFactory().getMovimientoDAO().getMovimientosPorCuentaYFechas(
				DAOFactory.getFactory().getCuentaDAO().getById(id), fechaInicio, fechaFin);

		request.setAttribute("estadoDeCuenta", estadoDeCuenta);
		request.setAttribute("movimientos", movimientos);
		request.getRequestDispatcher("/jsp/detallarCuenta.jsp").forward(request, response);

	}

}
