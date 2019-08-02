package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.RolCentroUsuario;
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
@Path("RolCentroUsuario")
@Secured
public class RolCentroUsuarioFacadeREST extends AbstractFacade<RolCentroUsuario> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public RolCentroUsuarioFacadeREST() {
        super(RolCentroUsuario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public RolCentroUsuario crear(RolCentroUsuario entidad) {
        super.crear(entidad);
        return entidad;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public RolCentroUsuario guardarRol(RolCentroUsuario entidad) {
        super.editar(entidad);
        return entidad;
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
    public RolCentroUsuario buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<RolCentroUsuario> listarTodo() {
        return super.listarTodo();
    }

    @POST
    @Path("ObtenerRolAdministrativo")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response obtenerRolAdministrativo(RolCentroUsuario rcu) {
        if (rcu == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(rcu).build();
        } else {
            try {
                Query query = em.createNativeQuery("select * from t_rol_centro_usuario as rcu\n"
                        + "inner join t_rol as r ON r.id_rol_pk = rcu.id_rol_fk\n"
                        + "where rcu.id_cai_fk is null and rcu.id_udi_fk is null and r.rol=?1", RolCentroUsuario.class);
                query.setParameter(1, rcu.getIdRol().getRol());

                List<RolCentroUsuario> listado = (List<RolCentroUsuario>) query.getResultList();

                if (!listado.isEmpty()) {
                    RolCentroUsuario rcuAux = listado.get(0);
                    GenericEntity<RolCentroUsuario> entidad = new GenericEntity<RolCentroUsuario>(rcuAux) {
                    };
                    return Response.ok().entity(entidad).build();
                } else {
                    return Response.status(Response.Status.NO_CONTENT).build();
                }
            } catch (NumberFormatException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @POST
    @Path("ObtenerRolSoloUDI")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response obtenerRolSoloUDI(RolCentroUsuario rcu) {
        if (rcu == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(rcu).build();
        } else {
            try {
                Query query = em.createNativeQuery("select * from t_rol_centro_usuario as rcu\n"
                        + "inner join t_rol as r ON r.id_rol_pk = rcu.id_rol_fk\n"
                        + "inner join t_udi as u ON u.id_udi_pk = rcu.id_udi_fk\n"
                        + "where rcu.id_cai_fk is null and rcu.id_udi_fk is not null and r.rol=?1 and u.udi=?2", RolCentroUsuario.class);
                query.setParameter(1, rcu.getIdRol().getRol());
                query.setParameter(2, rcu.getIdUdi().getUdi());

                List<RolCentroUsuario> listado = (List<RolCentroUsuario>) query.getResultList();

                if (!listado.isEmpty()) {
                    RolCentroUsuario rcuAux = listado.get(0);
                    GenericEntity<RolCentroUsuario> entidad = new GenericEntity<RolCentroUsuario>(rcuAux) {
                    };
                    return Response.ok().entity(entidad).build();
                } else {
                    return Response.status(Response.Status.NO_CONTENT).build();
                }
            } catch (NumberFormatException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @POST
    @Path("ObtenerRolSoloCAI")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response obtenerRolSoloCAI(RolCentroUsuario rcu) {
        if (rcu == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(rcu).build();
        } else {
            try {
                Query query = em.createNativeQuery("select * from t_rol_centro_usuario as rcu\n"
                        + "inner join t_rol as r ON r.id_rol_pk = rcu.id_rol_fk\n"
                        + "inner join t_cai as cai ON cai.id_cai_pk = rcu.id_cai_fk\n"
                        + "where rcu.id_cai_fk is not null and rcu.id_udi_fk is null and r.rol=?1 and cai.cai=?2", RolCentroUsuario.class);
                query.setParameter(1, rcu.getIdRol().getRol());
                query.setParameter(2, rcu.getIdCai().getCai());

                List<RolCentroUsuario> listado = (List<RolCentroUsuario>) query.getResultList();

                if (!listado.isEmpty()) {
                    RolCentroUsuario rcuAux = listado.get(0);
                    GenericEntity<RolCentroUsuario> entidad = new GenericEntity<RolCentroUsuario>(rcuAux) {
                    };
                    return Response.ok().entity(entidad).build();
                } else {
                    return Response.status(Response.Status.NO_CONTENT).build();
                }
            } catch (NumberFormatException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
