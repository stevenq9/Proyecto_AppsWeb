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
import javax.servlet.http.HttpSession;

import modelo.EstadoContable;
import modelo.EstadoDeCuenta;
import modelo.dao.DAOFactory;
import modelo.entidades.Persona;

@WebServlet("/VisualizarEstadoContableController")
public class VisualizarEstadoContableController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Persona persona;

	public VisualizarEstadoContableController() {
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
		String ruta = "mostrarEstado";

		if (request.getParameter("ruta") != null)
			ruta = request.getParameter("ruta");

		switch (ruta) {
		case "mostrarEstado":
			this.mostrarEstado(request, response);
			break;
		case "mostrarDetalleCuenta":
			this.mostrarDetalleCuenta(request, response);
		}
	}

	private void mostrarEstado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Date fechaInicio = Date.valueOf(LocalDate.parse(request.getParameter("fechaInicial")));
		Date fechaFin = Date.valueOf(LocalDate.parse(request.getParameter("fechaFinal")));

		List<EstadoDeCuenta> estadoContableIngresos = DAOFactory.getFactory().getMovimientoDAO()
				.getEstadoContableDeIngresos(persona, fechaInicio, fechaFin);
		List<EstadoDeCuenta> estadoContableIngresosYGastos = DAOFactory.getFactory().getMovimientoDAO()
				.getEstadoContableDeIngresosYGastos(persona, fechaInicio, fechaFin);
		List<EstadoDeCuenta> estadoContableGastos = DAOFactory.getFactory().getMovimientoDAO()
				.getEstadoContableDeGastos(persona, fechaInicio, fechaFin);

		HttpSession session = request.getSession();

		session.setAttribute("fechaInicial", fechaInicio);
		session.setAttribute("fechaFinal", fechaFin);
		session.setAttribute("estadoContableIngresos", estadoContableIngresos);
		session.setAttribute("estadoContableIngresosYGastos", estadoContableIngresosYGastos);
		session.setAttribute("estadoContableGastos", estadoContableGastos);
		request.getRequestDispatcher("/jsp/detallarEstadoContable.jsp").forward(request, response);
	}

	private void mostrarDetalleCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tipo = request.getParameter("tipo");

		EstadoContable ec = (EstadoContable) request.getSession().getAttribute(tipo);

		int index = Integer.parseInt(request.getParameter("index"));

		EstadoDeCuenta estadocuenta = ec.getEstadosDeCuenta().get(index);
		request.setAttribute("estadocuenta", estadocuenta);

		request.getRequestDispatcher("/jsp/detallarCuenta.jsp").forward(request, response);
	}

	public static void removeEstadoContableSession(HttpSession session) {
		session.removeAttribute("fechaInicial");
		session.removeAttribute("fechaFinal");
		session.removeAttribute("estadoContableIngresos");
		session.removeAttribute("estadoContableIngresosYGastos");
		session.removeAttribute("estadoContableGastos");
	}

}