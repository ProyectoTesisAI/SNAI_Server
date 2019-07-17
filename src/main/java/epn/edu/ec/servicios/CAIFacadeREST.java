package epn.edu.ec.servicios;

import epn.edu.ec.entidades.CAI;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("Cai")
public class CAIFacadeREST extends AbstractFacade<CAI> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public CAIFacadeREST() {
        super(CAI.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public CAI crear(CAI entity) {
        return super.crear(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public CAI editar(@PathParam("id") Integer id, CAI entity) {
        return super.editar(entity);
    }

    @DELETE
    @Path("{id}")
    public void eliminar(@PathParam("id") Integer id) {
        super.eliminar(super.buscarPorId(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public CAI buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({ MediaType.APPLICATION_JSON})
    public List<CAI> listarTodo() {
        return super.listarTodo();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
