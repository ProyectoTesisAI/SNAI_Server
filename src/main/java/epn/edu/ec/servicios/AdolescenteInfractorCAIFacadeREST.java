package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.AdolescenteInfractor;
import epn.edu.ec.entidades.AdolescenteInfractorCAI;
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
    
    @GET
    @Path("AdolescentesAsignadosCAI")
    @Produces({MediaType.APPLICATION_JSON})
    public Response listarAdolescentesConCaiAsignado() {

        try {
            Query query = em.createNativeQuery("SELECT a.id_adolescente_pk, a.nombres, a.apellidos, a.cedula, a.documento, c.cai FROM t_adolescente as a \n"
                    + "inner join t_deta_infraccion_cai as di on a.id_adolescente_pk= di.id_adolescente_cai_fk\n"
                    + "inner join t_ejecucion_medida_cai as emc on emc.id_deta_infrac_cai_fk= di.id_deta_infrac_cai_pk\n"
                    + "inner join t_cai as c ON c.id_cai_pk=emc.id_cai_fk ");

            List<Object[]> objetos = (List<Object[]>) query.getResultList();

            if (objetos != null && objetos.size() > 0) {
                List<AdolescenteInfractorCAI> lista = new ArrayList<>();

                for (Object[] p : objetos) {
                    AdolescenteInfractor adolescenteInfractor = new AdolescenteInfractor();
                    AdolescenteInfractorCAI adolescenteInfractorCAI = new AdolescenteInfractorCAI();

                    if (p[0] != null) {
                        adolescenteInfractor.setIdAdolescenteInfractor(Integer.parseInt(p[0].toString()));
                    }
                    if (p[1] != null) {
                        adolescenteInfractor.setNombres(p[1].toString());
                    }
                    if (p[2] != null) {
                        adolescenteInfractor.setApellidos(p[2].toString());
                    }
                    if (p[3] != null) {
                        adolescenteInfractor.setCedula(p[3].toString());
                    }
                    if (p[4] != null) {
                        adolescenteInfractor.setDocumento(p[4].toString());
                    }
                    if (p[5] != null) {
                        adolescenteInfractor.setNacionalidad(p[5].toString());
                    }

                    adolescenteInfractorCAI.setIdAdolescenteInfractor(adolescenteInfractor);
                    lista.add(adolescenteInfractorCAI);
                }
                GenericEntity<List<AdolescenteInfractorCAI>> entidad = new GenericEntity<List<AdolescenteInfractorCAI>>(lista) {
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
    @Path("AdolescentesNoAsignadosCAI")
    @Produces({MediaType.APPLICATION_JSON})
    public Response listarAdolescentesSinCaiAsignado() {

        try {
            Query query = em.createNativeQuery("Select a.id_adolescente_pk, a.nombres, a.apellidos, a.cedula, a.documento from t_adolescente_cai as ac\n" +
                "inner join t_adolescente as a on a.id_adolescente_pk=ac.id_adolescente_cai_pk\n" +
                "where a.id_adolescente_pk not in ( SELECT a.id_adolescente_pk as id_adolescente FROM t_adolescente as a \n" +
                "inner join t_deta_infraccion_cai as di on a.id_adolescente_pk= di.id_adolescente_cai_fk\n" +
                "inner join t_ejecucion_medida_cai as emc on emc.id_deta_infrac_cai_fk= di.id_deta_infrac_cai_pk\n" +
                "inner join t_cai as c ON c.id_cai_pk=emc.id_cai_fk )");

            List<Object[]> objetos = (List<Object[]>) query.getResultList();

            if (objetos != null && objetos.size() > 0) {
                List<AdolescenteInfractorCAI> lista = new ArrayList<>();

                for (Object[] p : objetos) {
                    AdolescenteInfractor adolescenteInfractor = new AdolescenteInfractor();
                    AdolescenteInfractorCAI adolescenteInfractorCAI = new AdolescenteInfractorCAI();

                    if (p[0] != null) {
                        adolescenteInfractor.setIdAdolescenteInfractor(Integer.parseInt(p[0].toString()));
                    }
                    if (p[1] != null) {
                        adolescenteInfractor.setNombres(p[1].toString());
                    }
                    if (p[2] != null) {
                        adolescenteInfractor.setApellidos(p[2].toString());
                    }
                    if (p[3] != null) {
                        adolescenteInfractor.setCedula(p[3].toString());
                    }
                    if (p[4] != null) {
                        adolescenteInfractor.setDocumento(p[4].toString());
                    }

                    adolescenteInfractorCAI.setIdAdolescenteInfractor(adolescenteInfractor);
                    lista.add(adolescenteInfractorCAI);
                }
                GenericEntity<List<AdolescenteInfractorCAI>> entidad = new GenericEntity<List<AdolescenteInfractorCAI>>(lista) {
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
