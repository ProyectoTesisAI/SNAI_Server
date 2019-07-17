package epn.edu.ec.servicios;

import epn.edu.ec.entidades.MotivoEgresoCAI;
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
@Path("Motivo_Egreso_Cai")
public class MotivoEgresoCAIFacadeREST extends AbstractFacade<MotivoEgresoCAI> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public MotivoEgresoCAIFacadeREST() {
        super(MotivoEgresoCAI.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public MotivoEgresoCAI crear(MotivoEgresoCAI entidad) {
        return super.crear(entidad);
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public MotivoEgresoCAI guardarMotivoEgresoCAI(MotivoEgresoCAI entidad) {
        return super.editar(entidad);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public MotivoEgresoCAI editar(@PathParam("id") Integer id, MotivoEgresoCAI entidad) {
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
    public MotivoEgresoCAI buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<MotivoEgresoCAI> listarTodo() {
        return super.listarTodo();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
