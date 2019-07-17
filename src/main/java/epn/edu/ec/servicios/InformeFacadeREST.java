package epn.edu.ec.servicios;

import epn.edu.ec.entidades.Informe;
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
@Path("Informe")
public class InformeFacadeREST extends AbstractFacade<Informe> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public InformeFacadeREST() {
        super(Informe.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public Informe crear(Informe entidad) {
        return super.crear(entidad);
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Informe guardarInforme(Informe entidad) {
        return super.editar(entidad);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Informe editar(@PathParam("id") Integer id, Informe entidad) {
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
    public Informe buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Informe> listarTodo() {
        return super.listarTodo();
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
