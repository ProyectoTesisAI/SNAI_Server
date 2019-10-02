package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.AdolescenteInfractor;
import epn.edu.ec.entidades.AdolescenteInfractorUDI;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
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
@Path("Adolescente_Udi")
public class AdolescenteInfractorUDIFacadeREST extends AbstractFacade<AdolescenteInfractorUDI> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public AdolescenteInfractorUDIFacadeREST() {
        super(AdolescenteInfractorUDI.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public AdolescenteInfractorUDI crear(AdolescenteInfractorUDI entidad) {
        return super.crear(entidad);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public AdolescenteInfractorUDI guardarEdicion(AdolescenteInfractorUDI entidad) {
        return super.editar(entidad);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public AdolescenteInfractorUDI buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<AdolescenteInfractorUDI> listarTodo() {
        return super.listarTodo();
    }

    @GET
    @Path("listarAdolescenteConZona")
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response listarAdolescentesConZona() {
        try {
            Query query = em.createNativeQuery("select ai.id_adolescente_pk,ai.nombres, ai.apellidos, ai.cedula, ai.documento, u.udi from t_unidad_zonal as uz\n"
                    + "inner join t_udi as u on u.id_udi_pk=uz.id_udi_fk\n"
                    + "inner join t_adolescente_udi as aiu on aiu.id_adolescente_udi_pk=uz.id_unidad_zonal_pk\n"
                    + "inner join t_adolescente as ai on ai.id_adolescente_pk=aiu.id_adolescente_udi_pk\n"
                    + "where aiu.id_adolescente_udi_pk is not null");

            List<Object[]> objetos = (List<Object[]>) query.getResultList();

            if (objetos != null && objetos.size() > 0) {
                List<AdolescenteInfractorUDI> lista = new ArrayList<>();

                for (Object[] p : objetos) {
                    AdolescenteInfractorUDI aiuAux = new AdolescenteInfractorUDI();
                    AdolescenteInfractor aiAux = new AdolescenteInfractor();
                    if (p[0] != null) {
                        aiAux.setIdAdolescenteInfractor(Integer.parseInt(p[0].toString()));
                    }
                    if (p[1] != null) {
                        aiAux.setNombres(p[1].toString());
                    }
                    if (p[2] != null) {
                        aiAux.setApellidos(p[2].toString());
                    }
                    if (p[3] != null) {
                        aiAux.setCedula(p[3].toString());
                    }
                    if (p[4] != null) {
                        aiAux.setDocumento(p[4].toString());
                    }
                    if (p[5] != null) {
                        aiAux.setNacionalidad(p[5].toString());
                    }
                    aiuAux.setIdAdolescenteInfractor(aiAux);
                    lista.add(aiuAux);
                }
                GenericEntity<List<AdolescenteInfractorUDI>> entidad = new GenericEntity<List<AdolescenteInfractorUDI>>(lista) {
                };
                return Response.ok().entity(entidad).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("listarAdolescenteSinZona")
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response listarAdolescentesSinZona() {
        try {
            Query query = em.createNativeQuery("SELECT ai.id_adolescente_pk,ai.nombres, ai.apellidos, ai.cedula, ai.documento FROM t_adolescente as ai\n"
                    + "inner join t_adolescente_udi as aiu on aiu.id_adolescente_udi_pk=ai.id_adolescente_pk\n"
                    + "where aiu.id_adolescente_udi_pk not in (\n"
                    + "select uz.id_unidad_zonal_pk from t_unidad_zonal as uz)");

            List<Object[]> objetos = (List<Object[]>) query.getResultList();

            if (objetos != null && objetos.size() > 0) {
                List<AdolescenteInfractorUDI> lista = new ArrayList<>();

                for (Object[] p : objetos) {
                    AdolescenteInfractorUDI aiuAux = new AdolescenteInfractorUDI();
                    AdolescenteInfractor aiAux = new AdolescenteInfractor();
                    if (p[0] != null) {
                        aiAux.setIdAdolescenteInfractor(Integer.parseInt(p[0].toString()));
                    }
                    if (p[1] != null) {
                        aiAux.setNombres(p[1].toString());
                    }
                    if (p[2] != null) {
                        aiAux.setApellidos(p[2].toString());
                    }
                    if (p[3] != null) {
                        aiAux.setCedula(p[3].toString());
                    }
                    if (p[4] != null) {
                        aiAux.setDocumento(p[4].toString());
                    }
                    aiuAux.setIdAdolescenteInfractor(aiAux);
                    lista.add(aiuAux);
                }
                GenericEntity<List<AdolescenteInfractorUDI>> entidad = new GenericEntity<List<AdolescenteInfractorUDI>>(lista) {
                };
                return Response.ok().entity(entidad).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
