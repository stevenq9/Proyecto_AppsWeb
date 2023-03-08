package recurso;

import java.sql.Date;
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
import modelo.entidades.Movimiento;

@Path("/movimientos")
public class RecursoMovimientos {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movimiento> listar() {
		return DAOFactory.getFactory().getMovimientoDAO().getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movimiento getPersonaByPathParam(@PathParam("id") int id) {
		return DAOFactory.getFactory().getMovimientoDAO().getById(id);
	}
	
	@POST
	@Path("/crear")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean crear(Movimiento movimiento) {
		try {
			DAOFactory.getFactory().getMovimientoDAO().create(movimiento);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarMovimientosPorCuenta")
	public List<Movimiento> listarMovimientosPorCuenta(@QueryParam("idCuenta") int idCuenta) {
		Cuenta cuenta = DAOFactory.getFactory().getCuentaDAO().getById(idCuenta);
		return DAOFactory.getFactory().getMovimientoDAO().getMovimientosPorCuenta(cuenta);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarMovimientosPorCuentaYFechas")
	public List<Movimiento> listarMovimientosPorCuentaYFechas(@QueryParam("idCuenta") int idCuenta, @QueryParam("fechaInicio") String fechaInicio, @QueryParam("fechaFin") String fechaFin) {
		Cuenta cuenta = DAOFactory.getFactory().getCuentaDAO().getById(idCuenta);
		Date fechaInicioDate = Date.valueOf(fechaInicio);
		Date fechaFinDate = Date.valueOf(fechaFin);
		return DAOFactory.getFactory().getMovimientoDAO().getMovimientosPorCuentaYFechas(cuenta, fechaInicioDate, fechaFinDate);
	}
}
