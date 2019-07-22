package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.InformacionInfraccion;
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
@Path("Informacion_Infraccion")
@Secured
public class InformacionInfraccionFacadeREST extends AbstractFacade<InformacionInfraccion> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public InformacionInfraccionFacadeREST() {
        super(InformacionInfraccion.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public InformacionInfraccion crear(InformacionInfraccion entidad) {
        return super.crear(entidad);
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public InformacionInfraccion guardarInformacionInfraccion(InformacionInfraccion entidad) {
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
    public InformacionInfraccion find(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<InformacionInfraccion> listarTodo() {
        return super.listarTodo();
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
