package epn.edu.ec.servicios;

import epn.edu.ec.entidades.DetalleInfraccionCAI;
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
@Path("Detalle_Infraccion_Cai")
public class DetalleInfraccionCAIFacadeREST extends AbstractFacade<DetalleInfraccionCAI> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public DetalleInfraccionCAIFacadeREST() {
        super(DetalleInfraccionCAI.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public DetalleInfraccionCAI crear(DetalleInfraccionCAI entidad) {
        return super.crear(entidad);
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public DetalleInfraccionCAI guardarDetalleInfraccionCAI(DetalleInfraccionCAI entidad) {
        return super.editar(entidad);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public DetalleInfraccionCAI editar(@PathParam("id") Integer id, DetalleInfraccionCAI entidad) {
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
    public DetalleInfraccionCAI buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<DetalleInfraccionCAI> listarTodo() {
        return super.listarTodo();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
   
}
