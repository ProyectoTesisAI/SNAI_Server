package epn.edu.ec.servicios;

import epn.edu.ec.entidades.IdentificacionGeografica;
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
@Path("Identificacion_Geografica")
public class IdentificacionGeograficaFacadeREST extends AbstractFacade<IdentificacionGeografica> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public IdentificacionGeograficaFacadeREST() {
        super(IdentificacionGeografica.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public IdentificacionGeografica crear(IdentificacionGeografica entidad) {
        return super.crear(entidad);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public IdentificacionGeografica guardarIdentificacionGeografica(IdentificacionGeografica entidad) {
        return super.editar(entidad);
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public IdentificacionGeografica editar(@PathParam("id") Integer id, IdentificacionGeografica entidad) {
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
    public IdentificacionGeografica buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<IdentificacionGeografica> listarTodo() {
        return super.listarTodo();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
