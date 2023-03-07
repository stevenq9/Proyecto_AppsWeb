package recurso;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import modelo.EstadoDeCuenta;
import modelo.dao.DAOFactory;
import modelo.dto.CuentaRestDTO;
import modelo.entidades.Cuenta;
import modelo.entidades.CuentaDeIngresosYGastos;
import modelo.entidades.Persona;

@Path("/cuentas")
public class RecursoCuenta {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cuenta> listar() {
		return DAOFactory.getFactory().getCuentaDAO().getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cuenta getPersonaByPathParam(@PathParam("id") int id) {
		return DAOFactory.getFactory().getCuentaDAO().getById(id);
	}
	
	@POST
	@Path("/crear")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean crear(Cuenta cuenta) {
		try {
			DAOFactory.getFactory().getCuentaDAO().create(cuenta);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarCuentasIngresos")
	public List<Cuenta> listarCuentasDeIngresos(@QueryParam("idUsuario") int idUsuario) {
		Persona persona = DAOFactory.getFactory().getPersonaDAO().getById(idUsuario);
		return DAOFactory.getFactory().getCuentaDAO().getCuentasIngresosPorPersona(persona);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarCuentasIngresosYGastos")
	public List<Cuenta> listarCuentasDeIngresosYGastos(@QueryParam("idUsuario") int idUsuario) {
		Persona persona = DAOFactory.getFactory().getPersonaDAO().getById(idUsuario);
		return DAOFactory.getFactory().getCuentaDAO().getCuentasIngresosYGastosPorPersona(persona);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarCuentasGastos")
	public List<Cuenta> listarCuentasDeGastos(@QueryParam("idUsuario") int idUsuario) {
		Persona persona = DAOFactory.getFactory().getPersonaDAO().getById(idUsuario);
		return DAOFactory.getFactory().getCuentaDAO().getCuentasGastosPorPersona(persona);
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean actualizar(CuentaRestDTO cuentaDTO) {
		try {
			Cuenta cuenta = DAOFactory.getFactory().getCuentaDAO().getById(cuentaDTO.getId());
			
			cuenta.setNombre(cuentaDTO.getNombre());
			if(cuenta instanceof CuentaDeIngresosYGastos)
				((CuentaDeIngresosYGastos)cuenta).setSaldo(cuentaDTO.getSaldo());
			
			DAOFactory.getFactory().getCuentaDAO().update(cuenta);
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/obtenerEstadoContableIngresos")
	public List<EstadoDeCuenta> listarCuentasDeIngresos(@QueryParam("idUsuario") int idUsuario, @QueryParam("fechaInicio") String fechaInicio, @QueryParam("fechaFin") String fechaFin) {
		Persona persona = DAOFactory.getFactory().getPersonaDAO().getById(idUsuario);
		Date fechaInicioDate = Date.valueOf(fechaInicio);
		Date fechaFinDate = Date.valueOf(fechaFin);
		return DAOFactory.getFactory().getCuentaDAO().getEstadoContableDeIngresos(persona, fechaInicioDate, fechaFinDate);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/obtenerEstadoContableIngresosYGastos")
	public List<EstadoDeCuenta> listarCuentasDeIngresosYGastos(@QueryParam("idUsuario") int idUsuario, @QueryParam("fechaInicio") String fechaInicio, @QueryParam("fechaFin") String fechaFin) {
		Persona persona = DAOFactory.getFactory().getPersonaDAO().getById(idUsuario);
		Date fechaInicioDate = Date.valueOf(fechaInicio);
		Date fechaFinDate = Date.valueOf(fechaFin);
		return DAOFactory.getFactory().getCuentaDAO().getEstadoContableDeIngresosYGastos(persona, fechaInicioDate, fechaFinDate);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/obtenerEstadoContableGastos")
	public List<EstadoDeCuenta> listarCuentasDeGastos(@QueryParam("idUsuario") int idUsuario, @QueryParam("fechaInicio") String fechaInicio, @QueryParam("fechaFin") String fechaFin) {
		Persona persona = DAOFactory.getFactory().getPersonaDAO().getById(idUsuario);
		Date fechaInicioDate = Date.valueOf(fechaInicio);
		Date fechaFinDate = Date.valueOf(fechaFin);
		return DAOFactory.getFactory().getCuentaDAO().getEstadoContableDeGastos(persona, fechaInicioDate, fechaFinDate);
	}
}
