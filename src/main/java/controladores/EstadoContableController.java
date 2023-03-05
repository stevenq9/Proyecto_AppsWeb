package controladores;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Chaucherita;
import modelo.EstadoContable;
import modelo.EstadoContableFactory;
import modelo.EstadoDeCuenta;

@WebServlet("/EstadoContableController")
public class EstadoContableController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Chaucherita chaucherita;

	public EstadoContableController() {
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
		/*LocalDate fechaInicio = LocalDate.parse(request.getParameter("fechaInicial"));
		LocalDate fechaFin = LocalDate.parse(request.getParameter("fechaFinal"));

		EstadoContableFactory ecf = new EstadoContableFactory();
		EstadoContable estadoContableIngresos = ecf.crearEstadoContableDeIngresos(this.chaucherita, fechaInicio,
				fechaFin);
		EstadoContable estadoContableIngresosYGastos = ecf.crearEstadoContableDeIngresosYGastos(this.chaucherita,
				fechaInicio, fechaFin);
		EstadoContable estadoContableGastos = ecf.crearEstadoContableDeGastos(this.chaucherita, fechaInicio, fechaFin);

		HttpSession session = request.getSession();

		session.setAttribute("estadoContableIngresos", estadoContableIngresos);
		session.setAttribute("estadoContableIngresosYGastos", estadoContableIngresosYGastos);
		session.setAttribute("estadoContableGastos", estadoContableGastos);
		request.getRequestDispatcher("/jsp/detallarEstadoContable.jsp").forward(request, response);*/
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

}
