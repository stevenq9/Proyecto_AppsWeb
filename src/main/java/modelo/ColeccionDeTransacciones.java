package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ColeccionDeTransacciones implements Serializable{

	private static final long serialVersionUID = 1L;
	private static List<Transaccion> transacciones;
	private static ColeccionDeTransacciones instancia;
	
	private ColeccionDeTransacciones() {
		transacciones = new ArrayList<Transaccion>();
	}

	public static List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public static ColeccionDeTransacciones getInstancia() {
		if(instancia == null)
			instancia = new ColeccionDeTransacciones();
		return instancia;
	}
	
	public static List<Transaccion> getTransacciones(int idCuenta){
		return null;
	}
	
	public static List<Transaccion> getTransacciones(Date fechaInicio, Date fechaFin, int idCuenta){
		return null;
	}
	
	public void agregar(Transaccion transaccion) {
		transacciones.add(transaccion);
	}
}
