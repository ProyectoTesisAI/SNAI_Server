package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.Informe;
import epn.edu.ec.entidades.Usuario;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Stateless
@Path("Informe")
@Secured
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

    @DELETE
    @Path("{id}")
    public void eliminar(@Context SecurityContext securityContext, @PathParam("id") Integer id) {
        if (securityContext.isUserInRole("ADMINISTRADOR")) {
            super.eliminar(super.buscarPorId(id));
        }
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

    @POST
    @Path("InformesPorUsuario")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response listarInformePorUsuario(Usuario usuario) {
        if (usuario.getIdUsuario() < 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(usuario).build();
        } else {
            try {
                Query query = em.createNativeQuery("select * from t_informe as i where i.id_taller_fk in (select t.id_taller_pk from t_taller as t where t.usuario_fk=?1)", Informe.class);
                query.setParameter(1, usuario.getIdUsuario());
                List<Informe> listado = query.getResultList();

                if (!listado.isEmpty()) {
                    GenericEntity<List<Informe>> entidad = new GenericEntity<List<Informe>>(listado) {
                    };

                    return Response.ok().entity(entidad).build();
                } else {
                    return Response.status(Response.Status.NO_CONTENT).build();
                }
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @GET
    @Path("InformeSoloUZDI")//solo para COORDINADOR/LIDER UZDI
    @Produces({MediaType.APPLICATION_JSON})
    public Response listarInformesSoloUZDI() {
        try {
            Query query = em.createNativeQuery("select * from t_informe as i where i.id_taller_fk in (select t.id_taller_pk from t_taller as t where t.id_udi_fk is not null and t.id_cai_fk is null)", Informe.class);
            List<Informe> listado = query.getResultList();

            if (!listado.isEmpty()) {
                GenericEntity<List<Informe>> entidad = new GenericEntity<List<Informe>>(listado) {
                };

                return Response.ok().entity(entidad).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("InformeSoloCAI")//SOLO PARA COORDINADOR CAI
    @Produces({MediaType.APPLICATION_JSON})
    public Response listarInformesSoloCAI() {
        try {
            Query query = em.createNativeQuery("select * from t_informe as i where i.id_taller_fk in (select t.id_taller_pk from t_taller as t where t.id_udi_fk is null and t.id_cai_fk is not null)", Informe.class);
            List<Informe> listado = query.getResultList();

            if (!listado.isEmpty()) {
                GenericEntity<List<Informe>> entidad = new GenericEntity<List<Informe>>(listado) {
                };

                return Response.ok().entity(entidad).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
