package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.ActividadesInstrumentos;
import java.util.List;
import javax.annotation.security.RolesAllowed;
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
@Secured
@Path("Actividades_Instrumentos")
public class ActividadesInstrumentosFacadeREST extends AbstractFacade<ActividadesInstrumentos> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public ActividadesInstrumentosFacadeREST() {
        super(ActividadesInstrumentos.class);
    }

    @POST
    @Override
    @Consumes({ MediaType.APPLICATION_JSON})
    public ActividadesInstrumentos crear(ActividadesInstrumentos entidad) {
        super.crear(entidad);
        return entidad;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public ActividadesInstrumentos guardarActividadesInstrumentos(ActividadesInstrumentos entidad) {
        return super.editar(entidad);
    }

    @DELETE
    @RolesAllowed("ADMINISTRADOR")
    @Path("{id}")
    public void eliminar(@PathParam("id") Integer id) {
        super.eliminar(super.buscarPorId(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ActividadesInstrumentos buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<ActividadesInstrumentos> listarTodo() {
        return super.listarTodo();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
