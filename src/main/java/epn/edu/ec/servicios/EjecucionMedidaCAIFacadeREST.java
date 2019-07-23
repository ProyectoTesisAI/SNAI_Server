package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.DetalleInfraccionCAI;
import epn.edu.ec.entidades.EjecucionMedidaCAI;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Secured
@Path("Ejecucion_Medida_Cai")
public class EjecucionMedidaCAIFacadeREST extends AbstractFacade<EjecucionMedidaCAI> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public EjecucionMedidaCAIFacadeREST() {
        super(EjecucionMedidaCAI.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public EjecucionMedidaCAI crear(EjecucionMedidaCAI entidad) {
        return super.crear(entidad);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public EjecucionMedidaCAI guardarEjecucionMedidaCAI(EjecucionMedidaCAI entidad) {
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
    public EjecucionMedidaCAI buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<EjecucionMedidaCAI> listarTodo() {
        return super.listarTodo();
    }

    @POST
    @Path("ListaMedidasPorInfraccionCAI")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response listarMedidasPorInfraccionCAI(DetalleInfraccionCAI infraccion) {
        if (infraccion == null || infraccion.getIdDetalleInfraccion() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(infraccion).build();
        } else {
            try {
                Query query = em.createNativeQuery("SELECT * FROM t_ejecucion_medida_cai as emc WHERE emc.id_deta_infrac_cai_fk = ?1", EjecucionMedidaCAI.class);
                query.setParameter(1, infraccion.getIdDetalleInfraccion());

                List<EjecucionMedidaCAI> lista = query.getResultList();

                if (!lista.isEmpty()) {

                    List<EjecucionMedidaCAI> infracciones = new ArrayList<>();
                    infracciones=lista;

                    GenericEntity<List<EjecucionMedidaCAI>> entidad = new GenericEntity<List<EjecucionMedidaCAI>>(infracciones) {};
                    return Response.ok().entity(entidad).build();
                } else {
                    return Response.status(Response.Status.NO_CONTENT).build();
                }
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage() + ";" + e.getLocalizedMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
