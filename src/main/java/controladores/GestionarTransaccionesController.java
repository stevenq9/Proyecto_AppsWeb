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
import modelo.CuentaDeGastos;
import modelo.CuentaDeIngresos;
import modelo.CuentaDeIngresosYGastos;
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
		request.setAttribute("ruta", "ingreso");
		request.getRequestDispatcher("/jsp/ingresarDatosTransaccion.jsp").forward(request, response);
		
	}

	private void registrarTransaccion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtención de datos del modelo
		List<Cuenta> cuentaOrigen = coleccionDeTransacciones.getChaucherita().getCuentasConRetiro();
		List<Cuenta> cuentaDestino = coleccionDeTransacciones.getChaucherita().getCuentas();
		/*
		for (Cuenta cuenta : cuentaIngreyGast) {
			cuentaOrigen.add(cuenta);
		}*/
		
		// Envio de datos hacia la vista
		request.setAttribute("cuentasOrigen", cuentaOrigen);
		request.setAttribute("cuentasDestino", cuentaDestino);
		request.setAttribute("ruta", "transaccion");
		request.getRequestDispatcher("/jsp/ingresarDatosTransaccion.jsp").forward(request, response);
	}

	private void detallarCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));

		List<Transaccion> transaccionesTemp = new ArrayList<Transaccion>();
		transaccionesTemp = ColeccionDeTransacciones.getTransaccionesByID(id);
		Cuenta cuentaTemporal = Chaucherita.getInstancia().obtenerCuentaPorId(id);
		double total = cuentaTemporal.obtenerValorTotal(transaccionesTemp);
		double saldo = -1;
		
		if(!(cuentaTemporal instanceof CuentaDeGastos)) 
			saldo = ((CuentaConRetiro)cuentaTemporal).getSaldo() ;
		
		request.setAttribute("transacciones", transaccionesTemp);
		request.setAttribute("cuenta", cuentaTemporal);
		request.setAttribute("total", total);
		request.setAttribute("saldo", saldo);
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
	
		String ruta = request.getParameter("txtRuta");
		
		if(ruta.equals("ingreso")) {
			// INGRESO/*
			double cantidad = Double.parseDouble(request.getParameter("nmbCantidad"));	
			int idCuentaDestino = Integer.parseInt(request.getParameter("selCuentaDestino"));
			String descripcion = request.getParameter("txtDescripcion");

			// Obtención cuenta
			Cuenta cuentaDestino = coleccionDeTransacciones.getChaucherita().obtenerCuentaPorId(idCuentaDestino);

			// Crear transaccion
			Transaccion t = null;
			try {
				t = new Transaccion(0, LocalDate.now(), null, cuentaDestino, descripcion, cantidad);
			} catch(Exception e) {
				this.enviarPantallaDeError(e, request, response);
				return;
			}
			
			// Realizar deposito
			try {
				cuentaDestino.depositar(t);
			} catch (Exception e) {
				this.enviarPantallaDeError(e, request, response);
				return;
			}

			// Agregar transaccion
			coleccionDeTransacciones.agregar(t);

			// Confirmar ingreso
			request.getRequestDispatcher("/jsp/confirmarTransaccion.jsp").forward(request, response);
		} else {
			// TRANSACCION
			double cantidad = Double.parseDouble(request.getParameter("nmbCantidad"));
			int idCuentaOrigen = Integer.parseInt(request.getParameter("selCuentaOrigen"));
			int idCuentaDestino = Integer.parseInt(request.getParameter("selCuentaDestino"));
			String descripcion = request.getParameter("txtDescripcion");
			
			//Obtención de cuentas
			CuentaConRetiro cuentaOrigen = (CuentaConRetiro) coleccionDeTransacciones.getChaucherita().obtenerCuentaPorId(idCuentaOrigen);
			Cuenta cuentaDestino = coleccionDeTransacciones.getChaucherita().obtenerCuentaPorId(idCuentaDestino);
			
			//Crear transaccion 
			Transaccion t = null;
			try {
				t = new Transaccion(0, LocalDate.now(), cuentaOrigen, cuentaDestino, descripcion, cantidad);
			} catch(Exception e) {
				this.enviarPantallaDeError(e, request, response);
				return;
			}
			
			//Realizar retiro y deposito
			try{
				cuentaOrigen.retirar(t);
			}catch(Exception e) {
				this.enviarPantallaDeError(e, request, response);
				return;
			}
			try{
				cuentaDestino.depositar(t);
			}catch(Exception e) {
				double rollback = (cuentaOrigen).getSaldo() + t.getCantidad();
				cuentaOrigen.setSaldo(rollback);
				this.enviarPantallaDeError(e, request, response);
				return;
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
	
	private void enviarPantallaDeConfirmacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/confirmarTransaccion.jsp").forward(request, response);
	}
	
	private void enviarPantallaDeError(Exception e, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setAttribute("huboError", true);
		request.setAttribute("mensajeDeError", e.getMessage());
		this.enviarPantallaDeConfirmacion(request, response);
	}
}
