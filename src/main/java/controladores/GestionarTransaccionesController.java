package controladores;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Chaucherita;
import modelo.ColeccionDeTransacciones;
import modelo.Cuenta;
import modelo.CuentaDeIngresos;
import modelo.EstadoContable;
import modelo.EstadoDeCuenta;
import modelo.GeneradorEstadoContable;
import modelo.Transaccion;

@WebServlet("/GestionarTransaccionesController")
public class GestionarTransaccionesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ColeccionDeTransacciones coleccionDeTransacciones;

	public GestionarTransaccionesController() {
		super();
		this.coleccionDeTransacciones = ColeccionDeTransacciones.getInstancia();
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
		String ruta = "regresar";

		if (request.getParameter("ruta") != null)
			ruta = request.getParameter("ruta");

		switch (ruta) {
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
			break;
		case "confirmar":
			this.confirmar(request, response);
			break;
		case "regresar":
			this.regresar(request, response);
		}
	}

	private void registrarIngreso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtención de datos del modelo
		List<Cuenta> c = coleccionDeTransacciones.getChaucherita().getCuentasDeIngresos();

		// Envio de datos hacia la vista
		request.setAttribute("cuentasDestino", c);
		request.getRequestDispatcher("/jsp/ingresarDatosTransaccion.jsp").forward(request, response);
	}

	private void registrarTransaccion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private void detallarCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private void mostrarEstado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LocalDate fechaInicio = LocalDate.parse(request.getParameter("fechaInicial"));
		LocalDate fechaFin = LocalDate.parse(request.getParameter("fechaFinal"));
		
		GeneradorEstadoContable gec = new GeneradorEstadoContable(fechaInicio, fechaFin);
		EstadoContable estadoContableIngresos = gec.crearEstadoContableDeIngresos(coleccionDeTransacciones);
		EstadoContable estadoContableIngresosYGastos = gec.crearEstadoContableDeIngresos(coleccionDeTransacciones);
		EstadoContable estadoContableGastos = gec.crearEstadoContableDeIngresos(coleccionDeTransacciones);
		
		request.setAttribute("estadoContableIngresos", estadoContableIngresos);
		request.setAttribute("estadoContableIngresosYGastos", estadoContableIngresosYGastos);
		request.setAttribute("estadoContableGastos", estadoContableGastos);
		request.getRequestDispatcher("/jsp/detallarEstadoContable.jsp").forward(request, response);
	}

	private void confirmar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		/********* Obtención de datos ******/
		if(request.getParameter("selCuentaOrigen") == null) {
			//INGRESO
			double cantidad = Double.parseDouble(request.getParameter("nmbCantidad"));
			int idCuentaDestino = Integer.parseInt(request.getParameter("selCuentaDestino"));
			
			//Obtención cuenta
			Cuenta cuentaDestino = coleccionDeTransacciones.getChaucherita().obtenerCuentaPorId(idCuentaDestino);
			
			//Crear transaccion 
			Transaccion t = new Transaccion(0, null, null, cuentaDestino, "INGRESO", cantidad);
			
			//Realizar deposito
			try{
				cuentaDestino.depositar(t);
			}catch(Exception e) {
				request.setAttribute("huboError", true);
				request.getRequestDispatcher("/jsp/confirmarTransaccion.jsp").forward(request, response);
			}
			
			//Agregar transaccion 
			coleccionDeTransacciones.agregar(t);
			
			//Confirmar ingreso
			request.getRequestDispatcher("/jsp/confirmarTransaccion.jsp").forward(request, response);
		}else {
			//TRANSACCION

		}
	}

	private void regresar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/GestionarCuentasController");
	}
}
