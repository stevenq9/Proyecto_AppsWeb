package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.entidades.Persona;

@WebServlet("/VisualizarEstadoContableController")
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

		/*String tipo = request.getParameter("tipo");

		EstadoContable ec = (EstadoContable) request.getSession().getAttribute(tipo);

		int index = Integer.parseInt(request.getParameter("index"));

		EstadoDeCuenta estadocuenta = ec.getEstadosDeCuenta().get(index);
		request.setAttribute("estadocuenta", estadocuenta);

		request.getRequestDispatcher("/jsp/detallarCuenta.jsp").forward(request, response);*/
	}
}
