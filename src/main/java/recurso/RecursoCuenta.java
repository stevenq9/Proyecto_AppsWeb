package recurso;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import modelo.dao.DAOFactory;
import modelo.entidades.Cuenta;
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
}
