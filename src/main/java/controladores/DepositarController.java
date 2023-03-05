package controladores;

import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.DAOFactory;
import modelo.entidades.Cuenta;
import modelo.entidades.CuentaDeIngresos;
import modelo.entidades.CuentaDeIngresosYGastos;
import modelo.entidades.Movimiento;
import modelo.entidades.Persona;

@WebServlet("/DepositarController")
public class DepositarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Persona persona;
       
    public DepositarController() {
        super();
        persona = DAOFactory.getFactory().getPersonaDAO().getById(1);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.procesar(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.procesar(request, response);
	}

	private void procesar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = "registrarIngreso";

		if (request.getParameter("ruta") != null)
			ruta = request.getParameter("ruta");

		switch (ruta) {
		case "registrarIngreso":
			this.registrarIngreso(request, response);
			break;
		case "confirmar":
			this.confirmar(request, response);
			break;
		}
	}
	
	private void registrarIngreso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtención de datos del modelo

		List<Cuenta> cuentasOrigen = DAOFactory.getFactory().getCuentaDAO().getCuentasIngresosPorPersona(persona);
		List<Cuenta> cuentasDestino = DAOFactory.getFactory().getCuentaDAO().getCuentasIngresosYGastosPorPersona(persona);

		// Envio de datos hacia la vista
		request.setAttribute("cuentasOrigen", cuentasOrigen);
		request.setAttribute("cuentasDestino", cuentasDestino);
		request.setAttribute("ruta", "DepositarController?ruta=confirmar");
		request.getRequestDispatcher("/jsp/ingresarDatosMovimiento.jsp").forward(request, response);
	}
	
	private void confirmar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		double cantidad = Double.parseDouble(request.getParameter("nmbCantidad"));
		int idCuentaOrigen = Integer.parseInt(request.getParameter("selCuentaOrigen"));
		int idCuentaDestino = Integer.parseInt(request.getParameter("selCuentaDestino"));
		String descripcion = request.getParameter("txtDescripcion");
		
		CuentaDeIngresos cuentaDeOrigen = (CuentaDeIngresos) DAOFactory.getFactory().getCuentaDAO().getById(idCuentaOrigen);
		CuentaDeIngresosYGastos cuentaDeDestino = (CuentaDeIngresosYGastos) DAOFactory.getFactory().getCuentaDAO().getById(idCuentaDestino);
		
		Movimiento m = new Movimiento(Date.valueOf(LocalDate.now()), descripcion, cantidad);
		m.configurarComoIngreso(cuentaDeOrigen, cuentaDeDestino);
		
		try {
			cuentaDeDestino.depositar(m);
		} catch (Exception e) {
			enviarPantallaDeError(e, request, response);
			return;
		}
		
		DAOFactory.getFactory().getCuentaDAO().update(cuentaDeDestino);
		
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
