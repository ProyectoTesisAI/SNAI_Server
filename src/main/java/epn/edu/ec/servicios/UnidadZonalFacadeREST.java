package epn.edu.ec.servicios;

import epn.edu.ec.entidades.UnidadZonal;
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
@Path("Unidad_Zonal")
public class UnidadZonalFacadeREST extends AbstractFacade<UnidadZonal> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public UnidadZonalFacadeREST() {
        super(UnidadZonal.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public UnidadZonal crear(UnidadZonal entidad) {
        return super.crear(entidad);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public UnidadZonal guardarUnidadZonal(UnidadZonal entidad) {
        return super.editar(entidad);
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public UnidadZonal editar(@PathParam("id") Integer id, UnidadZonal entidad) {
        return super.editar(entidad);
    }

    @DELETE
    @Path("{id}")
    public void eliminar(@PathParam("id") Integer id) {
        super.eliminar(super.buscarPorId(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public UnidadZonal buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<UnidadZonal> listarTodo() {
        return super.listarTodo();
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
