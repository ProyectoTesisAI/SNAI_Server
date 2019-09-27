package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.ItemTaller;
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
@Path("Item_Taller")
@Secured
public class ItemTallerFacadeREST extends AbstractFacade<ItemTaller> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public ItemTallerFacadeREST() {
        super(ItemTaller.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ItemTaller crear(ItemTaller entidad) {
        return super.crear(entidad);
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ItemTaller guardarItemTaller(ItemTaller entidad) {
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
    public ItemTaller buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<ItemTaller> listarTodo() {
        return super.listarTodo();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
