package recurso;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import modelo.dao.DAOFactory;
import modelo.entidades.Persona;

@Path("/personas")
public class RecursoPersona {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Persona> listar() {
		return DAOFactory.getFactory().getPersonaDAO().getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona getPersonaByPathParam(@PathParam("id") int id) {
		return DAOFactory.getFactory().getPersonaDAO().getById(id);
	}

	@GET
	@Path("/autorizar")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona getPersonaByQueryParam(@QueryParam("usuario") String usuario, @QueryParam("clave") String clave) {
		return DAOFactory.getFactory().getPersonaDAO().autorizar(usuario, clave);
	}

	@POST
	@Path("/crear")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean crear(Persona persona) {
		try {
			DAOFactory.getFactory().getPersonaDAO().create(persona);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
