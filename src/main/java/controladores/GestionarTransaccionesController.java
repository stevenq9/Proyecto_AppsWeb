package controladores;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Chaucherita;
import modelo.ColeccionDeTransacciones;
import modelo.Cuenta;
import modelo.CuentaConRetiro;
import modelo.EstadoContable;
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
		// Obtención de datos del modelo
		List<Cuenta> cuentaOrigen = coleccionDeTransacciones.getChaucherita().getCuentasDeIngresos();
		List<Cuenta> cuentaIngreyGast = coleccionDeTransacciones.getChaucherita().getCuentasDeIngresosYGastos();
		List<Cuenta> cuentaDestino = coleccionDeTransacciones.getChaucherita().getCuentas();
		
		for (Cuenta cuenta : cuentaIngreyGast) {
			cuentaOrigen.add(cuenta);
		}
		
		// Envio de datos hacia la vista
		request.setAttribute("cuentasOrigen", cuentaOrigen);
		request.setAttribute("cuentasDestino", cuentaDestino);
		request.getRequestDispatcher("/jsp/ingresarDatosTransaccion.jsp").forward(request, response);
	}

	private void detallarCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));

		List<Transaccion> transaccionesTemp = new ArrayList<Transaccion>();
		transaccionesTemp = coleccionDeTransacciones.getTransaccionesByID(id);
		Cuenta cuentaTemporal = Chaucherita.getInstancia().obtenerCuentaPorId(id);
		double total = cuentaTemporal.obtenerValorTotal(transaccionesTemp);
		request.setAttribute("transacciones", transaccionesTemp);
		request.setAttribute("cuenta", cuentaTemporal);
		request.setAttribute("total", total);
		request.getRequestDispatcher("/jsp/detallarCuenta.jsp").forward(request, response);

	}

	private void mostrarEstado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LocalDate fechaInicio = LocalDate.parse(request.getParameter("fechaInicial"));
		LocalDate fechaFin = LocalDate.parse(request.getParameter("fechaFinal"));

		GeneradorEstadoContable gec = new GeneradorEstadoContable();
		EstadoContable estadoContableIngresos = gec.crearEstadoContableDeIngresos(coleccionDeTransacciones, fechaInicio, fechaFin);
		EstadoContable estadoContableIngresosYGastos = gec.crearEstadoContableDeIngresosYGastos(coleccionDeTransacciones, fechaInicio, fechaFin);
		EstadoContable estadoContableGastos = gec.crearEstadoContableDeGastos(coleccionDeTransacciones, fechaInicio, fechaFin);

		request.setAttribute("estadoContableIngresos", estadoContableIngresos);
		request.setAttribute("estadoContableIngresosYGastos", estadoContableIngresosYGastos);
		request.setAttribute("estadoContableGastos", estadoContableGastos);
		request.getRequestDispatcher("/jsp/detallarEstadoContable.jsp").forward(request, response);
	}

	private void confirmar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/********* Obtención de datos ******/
	
		if(request.getParameter("selCuentaOrigen").equals("false")) {
			// INGRESO/*
			double cantidad = Double.parseDouble(request.getParameter("nmbCantidad"));	
			int idCuentaDestino = Integer.parseInt(request.getParameter("selCuentaDestino"));

			// Obtención cuenta
			Cuenta cuentaDestino = coleccionDeTransacciones.getChaucherita().obtenerCuentaPorId(idCuentaDestino);

			// Crear transaccion
			Transaccion t = new Transaccion(0, null, null, cuentaDestino, "INGRESO", cantidad);

			// Realizar deposito
			try {
				cuentaDestino.depositar(t);
			} catch (Exception e) {
				request.setAttribute("huboError", true);
				request.getRequestDispatcher("/jsp/confirmarTransaccion.jsp").forward(request, response);
			}

			// Agregar transaccion
			coleccionDeTransacciones.agregar(t);

			// Confirmar ingreso
			request.getRequestDispatcher("/jsp/confirmarTransaccion.jsp").forward(request, response);
		} else {
			// TRANSACCION
			double cantidad = Double.parseDouble(request.getParameter("nmbCantidadT"));
			int idCuentaOrigen = Integer.parseInt(request.getParameter("selCuentaOrigen"));
			int idCuentaDestino = Integer.parseInt(request.getParameter("selCuentaDestinoT"));
			
			//Obtención de cuentas
			CuentaConRetiro cuentaOrigen = (CuentaConRetiro) coleccionDeTransacciones.getChaucherita().obtenerCuentaPorId(idCuentaOrigen);
			Cuenta cuentaDestino = coleccionDeTransacciones.getChaucherita().obtenerCuentaPorId(idCuentaDestino);
			
			//Crear transaccion 
			Transaccion t = new Transaccion(0, null, cuentaOrigen, cuentaDestino, "TRANSACCION", cantidad);
			
			//Realizar retiro y deposito
			try{
				cuentaOrigen.retirar(t);
			}catch(Exception e) {
				request.setAttribute("huboError", true);
				request.getRequestDispatcher("/jsp/confirmarTransaccion.jsp").forward(request, response);
			}
			try{
				cuentaDestino.depositar(t);
			}catch(Exception e) {
				request.setAttribute("huboError", true);
				request.getRequestDispatcher("/jsp/confirmarTransaccion.jsp").forward(request, response);
			}

			//Agregar transaccion 
			coleccionDeTransacciones.agregar(t);
			
			//Confirmar transaccion
			request.getRequestDispatcher("/jsp/confirmarTransaccion.jsp").forward(request, response);
			
		}
	}

	private void regresar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/GestionarCuentasController");
	}
}
