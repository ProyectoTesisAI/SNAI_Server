package epn.edu.ec.servicios;

import epn.edu.ec.entidades.AdolescenteInfractorCAI;
import epn.edu.ec.entidades.DetalleInfraccionCAI;
import java.util.ArrayList;
import java.util.List;
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

    @POST
    @Path("ListaInfraccionesPorAdolescente")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response listarInfraccionPorAdolescente(AdolescenteInfractorCAI adolescente) {
        if (adolescente == null || adolescente.getIdAdolescenteInfractor().getIdAdolescenteInfractor() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(adolescente).build();
        } else {
            try {
                Query query = em.createNativeQuery("SELECT * FROM t_deta_infraccion_cai as di  WHERE di.id_adolescente_cai_fk = ?1", DetalleInfraccionCAI.class);
                query.setParameter(1, adolescente.getIdAdolescenteInfractor().getIdAdolescenteInfractor());

                List<DetalleInfraccionCAI> lista = query.getResultList();

                if (!lista.isEmpty()) {

                    List<DetalleInfraccionCAI> infracciones = new ArrayList<>();
                    for (DetalleInfraccionCAI a : lista) {

                        DetalleInfraccionCAI infraccion = new DetalleInfraccionCAI();
                        infraccion.setIdDetalleInfraccion(a.getIdDetalleInfraccion());
                        infraccion.setTipoPenal(a.getTipoPenal());
                        infraccion.setProvinciaInfraccion(a.getProvinciaInfraccion());
                        infraccion.setCantonInfraccion(a.getCantonInfraccion());
                        infraccion.setUnidadJudicial(a.getUnidadJudicial());
                        infraccion.setNombreUnidadJudicial(a.getNombreUnidadJudicial());
                        infraccion.setNumeroCausa(a.getNumeroCausa());
                        infraccion.setNombreJuez(a.getNombreJuez());
                        infraccion.setIdAdolescenteInfractorCAI(a.getIdAdolescenteInfractorCAI());
                        infracciones.add(infraccion);
                    }

                    GenericEntity<List<DetalleInfraccionCAI>> entidad = new GenericEntity<List<DetalleInfraccionCAI>>(infracciones) {};
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
