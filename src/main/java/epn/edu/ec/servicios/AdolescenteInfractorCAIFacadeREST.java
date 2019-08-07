package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.AdolescenteInfractorCAI;
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
@Path("Adolescente_Cai")
public class AdolescenteInfractorCAIFacadeREST extends AbstractFacade<AdolescenteInfractorCAI> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public AdolescenteInfractorCAIFacadeREST() {
        super(AdolescenteInfractorCAI.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public AdolescenteInfractorCAI crear(AdolescenteInfractorCAI entidad) {
        return super.crear(entidad);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public AdolescenteInfractorCAI guardarEdicion(AdolescenteInfractorCAI entidad) {
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
    public AdolescenteInfractorCAI buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<AdolescenteInfractorCAI> listarTodo() {
        return super.listarTodo();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
