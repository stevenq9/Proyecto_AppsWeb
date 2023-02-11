package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EstadoContable implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<EstadoDeCuenta> estadosDeCuenta;
	
	public EstadoContable() {
		this.estadosDeCuenta = new ArrayList<EstadoDeCuenta>();
	}

	public List<EstadoDeCuenta> getEstadosDeCuenta() {
		return estadosDeCuenta;
	}

	public void setEstadosDeCuenta(List<EstadoDeCuenta> estadosDeCuenta) {
		this.estadosDeCuenta = estadosDeCuenta;
	}
	
	public void agregar(EstadoDeCuenta estadoDeCuenta) {
		this.estadosDeCuenta.add(estadoDeCuenta);
	}
}
