package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.AdolescenteInfractor;
import epn.edu.ec.entidades.AsistenciaAdolescente;
import epn.edu.ec.entidades.CAI;
import epn.edu.ec.entidades.RegistroAsistencia;
import epn.edu.ec.entidades.Taller;
import epn.edu.ec.entidades.UDI;
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
@Path("Registro_Asistencia")
@Secured
public class RegistroAsistenciaFacadeREST extends AbstractFacade<RegistroAsistencia> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public RegistroAsistenciaFacadeREST() {
        super(RegistroAsistencia.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public RegistroAsistencia crear(RegistroAsistencia entidad) {
        return super.crear(entidad);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public RegistroAsistencia guardarRegistroAsistencia(RegistroAsistencia entidad) {
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
    public RegistroAsistencia buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<RegistroAsistencia> listarTodo() {
        return super.listarTodo();
    }

    @POST
    @Path("ListaAdolescentesPorUzdi")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response listaAdolescentesInfractoresPorUzdi(UDI udi) {

        if (udi == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(udi).build();
        } else {
            try {
                Query query = em.createNativeQuery("SELECT a.id_adolescente_pk as id_Adolescente, a.nombres, a.apellidos, a.cedula FROM t_adolescente as a INNER JOIN t_adolescente_udi as au ON au.id_adolescente_udi_pk = a.id_adolescente_pk INNER JOIN t_unidad_zonal as uz ON uz.id_unidad_zonal_pk = au.id_adolescente_udi_pk INNER JOIN t_udi as u ON u.id_udi_pk = uz.id_udi_fk WHERE u.udi= ?1");
                query.setParameter(1, udi.getUdi());

                List<Object[]> lista = (List<Object[]>) query.getResultList();

                if (lista != null && lista.size() > 0) {

                    List<AdolescenteInfractor> registroAsistencia = new ArrayList<>();
                    for (Object[] a : lista) {

                        AdolescenteInfractor adolescente = new AdolescenteInfractor();
                        adolescente.setIdAdolescenteInfractor(Integer.parseInt(a[0].toString()));
                        adolescente.setNombres(a[1].toString());
                        adolescente.setApellidos(a[2].toString());
                        adolescente.setCedula(a[3].toString());

                        registroAsistencia.add(adolescente);
                    }

                    GenericEntity<List<AdolescenteInfractor>> entidad = new GenericEntity<List<AdolescenteInfractor>>(registroAsistencia) {
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
    
    @POST
    @Path("ListaAdolescentesPorCai")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response listaAdolescentesInfractoresPorCai(CAI cai) {

        if (cai == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(cai).build();
        } else {
            try {
                Query query = em.createNativeQuery("SELECT a.id_adolescente_pk as id_Adolescente, a.nombres, a.apellidos, a.cedula FROM t_adolescente as a INNER JOIN t_adolescente_cai as ac ON ac.id_adolescente_cai_pk= a.id_adolescente_pk INNER JOIN t_deta_infraccion_cai as di ON di.id_adolescente_cai_fk = ac.id_adolescente_cai_pk INNER JOIN t_ejecucion_medida_cai as emc ON emc.id_ejecucion_medida_cai_pk = di.id_deta_infrac_cai_pk INNER JOIN t_cai as c ON c.id_cai_pk = emc.id_cai_fk WHERE c.cai = ?1");
                query.setParameter(1, cai.getCai());

                List<Object[]> lista = (List<Object[]>) query.getResultList();

                if (lista != null && lista.size() > 0) {

                    List<AdolescenteInfractor> registroAsistencia = new ArrayList<>();
                    for (Object[] a : lista) {

                        AdolescenteInfractor adolescente = new AdolescenteInfractor();
                        adolescente.setIdAdolescenteInfractor(Integer.parseInt(a[0].toString()));
                        adolescente.setNombres(a[1].toString());
                        adolescente.setApellidos(a[2].toString());
                        adolescente.setCedula(a[3].toString());

                        registroAsistencia.add(adolescente);
                    }

                    GenericEntity<List<AdolescenteInfractor>> entidad = new GenericEntity<List<AdolescenteInfractor>>(registroAsistencia) {
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

    @POST
    @Path("ListaAdolescentesPorTaller")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response listaAdolescentesInfractoresPorTaller(Taller taller) {

        if (taller == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(taller).build();
        } else {
            try {
                          
                Query query = em.createNativeQuery("SELECT aa.id_asistencia_adolescente_pk, a.id_adolescente_pk as id_Adolescente, a.nombres, a.apellidos, a.cedula, aa.asistio, aa.id_reg_asistencia_fk FROM t_asistencia_adolescente as aa INNER JOIN t_adolescente as a ON a.id_adolescente_pk = aa.id_adolescente_fk WHERE aa.id_reg_asistencia_fk = ?1");
                query.setParameter(1, taller.getIdTaller());

                List<Object[]> lista = (List<Object[]>) query.getResultList();

                if (lista != null && lista.size() > 0) {

                    List<AsistenciaAdolescente> registroAsistencia = new ArrayList<>();
                    for (Object[] a : lista) {
                        AdolescenteInfractor adolescente=new AdolescenteInfractor();
                        AsistenciaAdolescente asistencia= new  AsistenciaAdolescente();
                        RegistroAsistencia registro= new  RegistroAsistencia();
                        Taller tallerAux= new Taller();
                        if(a[0]!=null){
                            asistencia.setIdAsistenciaAdolescente(Integer.parseInt(a[0].toString()));
                        }
                        
                        if(a[1]!=null){
                            adolescente.setIdAdolescenteInfractor(Integer.parseInt(a[1].toString()));
                        }
                        if(a[2]!=null){
                            adolescente.setNombres(a[2].toString());
                        }
                        if(a[3]!=null){
                            adolescente.setApellidos(a[3].toString());
                        }
                        if(a[4]!=null){
                            adolescente.setCedula(a[4].toString());
                        }
                        if(a[5]!=null){
                            asistencia.setAsistio(Boolean.valueOf(a[5].toString()));
                        }
                        if(a[6]!=null){
                            tallerAux.setIdTaller(Integer.parseInt(a[6].toString()));
                            registro.setIdTaller(tallerAux);
                                                 
                        }
                        asistencia.setIdRegistroAsistencia(registro);
                        asistencia.setIdAdolescenteInfractor(adolescente);
                        registroAsistencia.add(asistencia);
                    }

                    GenericEntity<List<AsistenciaAdolescente>> entidad = new GenericEntity<List<AsistenciaAdolescente>>(registroAsistencia) {
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
    @Path("Taller/NumeroAsistentes/{id_taller}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response numeroAsistentesATallerPsicologia(@PathParam("id_taller") Integer id) {

        if ( id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(id).build();
        } 
        else {
            try {
                
                Query query = em.createNativeQuery("SELECT count(*) FROM t_asistencia_adolescente as aa where aa.asistio=true and aa.id_reg_asistencia_fk = ?1");
                query.setParameter(1, id);

                Integer cantidadAsistentesAlTaller=Integer.parseInt(query.getSingleResult().toString());

                return Response.ok().entity(cantidadAsistentesAlTaller.toString()).build();
                


            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
