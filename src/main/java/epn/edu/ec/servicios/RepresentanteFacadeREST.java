package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.Representante;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Stateless
@Path("Representante")
@Secured
public class RepresentanteFacadeREST extends AbstractFacade<Representante> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public RepresentanteFacadeREST() {
        super(Representante.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public Representante crear(Representante entidad) {
        return super.crear(entidad);
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Representante editarRepresentante(Representante entidad) {
        return super.editar(entidad);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Representante buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Representante> listarTodo() {
        return super.listarTodo();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
