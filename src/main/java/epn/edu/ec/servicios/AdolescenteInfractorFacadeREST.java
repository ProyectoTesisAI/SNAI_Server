package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.AdolescenteInfractor;
import epn.edu.ec.entidades.EjecucionMedidaCAI;
import epn.edu.ec.entidades.Reporte1;
import epn.edu.ec.entidades.Reporte2;
import epn.edu.ec.entidades.Reporte3;
import epn.edu.ec.entidades.Reporte4;
import epn.edu.ec.entidades.Reporte5;
import epn.edu.ec.entidades.Reporte6N;
import epn.edu.ec.entidades.Reporte6S;
import epn.edu.ec.entidades.Reporte7;
import epn.edu.ec.entidades.Reporte8;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@Secured
@Path("Adolescente_Infractor")
public class AdolescenteInfractorFacadeREST extends AbstractFacade<AdolescenteInfractor> {

    //Inyecta una referencia válida al objeto EntityManager 
    //creada a partir de la unidad de persistencia SistemaSNAI_UnidadPersistencia
    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public AdolescenteInfractorFacadeREST() {
        super(AdolescenteInfractor.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public AdolescenteInfractor crear(AdolescenteInfractor entidad) {
        return super.crear(entidad);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public AdolescenteInfractor guardarEdicion(AdolescenteInfractor entidad) {
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
    public AdolescenteInfractor buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<AdolescenteInfractor> listarTodo() {
        return super.listarTodo();
    }

    @POST
    @Path("reporteTipoDelitoUDI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteTipoDelitoUDI(Reporte1 detalle) {

        if (detalle == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(detalle).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, u.udi, ai.nombres, ai.apellidos, ii.tipo_infraccion from t_info_infraccion as ii\n"
                        + "inner join t_adolescente_udi as audi on audi.id_adolescente_udi_pk = ii.id_info_infrac_pk\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = audi.id_adolescente_udi_pk\n"
                        + "inner join t_unidad_zonal as uz on uz.id_unidad_zonal_pk = audi.id_adolescente_udi_pk\n"
                        + "inner join t_udi as u ON u.id_udi_pk = uz.id_udi_fk\n"
                        + "where ii.tipo_infraccion=?1");
                query.setParameter(1, detalle.getTipoDelto());

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte1> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte1 aux = new Reporte1();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setTipoDelto(p[4].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte1>> entidad = new GenericEntity<List<Reporte1>>(lista) {
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
    @Path("reporteTipoDelitoCAI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteTipoDelitoCAI(Reporte1 detalle) {

        if (detalle == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(detalle).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, ca.cai, ai.nombres, ai.apellidos, dicai.tipo_penal from t_deta_infraccion_cai as dicai\n"
                        + "inner join t_adolescente_cai as acai on acai.id_adolescente_cai_pk = dicai.id_adolescente_cai_fk\n"
                        + "inner join t_adolescente as ai on ai.id_adolescente_pk = acai.id_adolescente_cai_pk\n"
                        + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = dicai.id_deta_infrac_cai_pk\n"
                        + "inner join t_cai as ca on ca.id_cai_pk = emc.id_cai_fk\n"
                        + "where dicai.tipo_penal=?1");
                query.setParameter(1, detalle.getTipoDelto());

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte1> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte1 aux = new Reporte1();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setTipoDelto(p[4].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte1>> entidad = new GenericEntity<List<Reporte1>>(lista) {
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
    @Path("reporteEdadCAI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteEdadCAI(Reporte2 edad) {

        if (edad == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(edad).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, cai.cai, ai.nombres, ai.apellidos, ai.genero, cast(date_part('year',age(ai.fecha_nacimiento)) as integer) as EDAD from t_adolescente as ai\n"
                        + "inner join t_adolescente_cai as aic ON aic.id_adolescente_cai_pk = ai.id_adolescente_pk\n"
                        + "inner join t_deta_infraccion_cai as di ON di.id_adolescente_cai_fk = aic.id_adolescente_cai_pk\n"
                        + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = di.id_deta_infrac_cai_pk\n"
                        + "inner join t_cai as cai ON cai.id_cai_pk = emc.id_cai_fk\n"
                        + "where date_part('year',age(ai.fecha_nacimiento))=?1");
                query.setParameter(1, edad.getEdad());

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte2> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte2 aux = new Reporte2();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setGenero(p[4].toString());
                        }
                        if (p[5] != null) {
                            aux.setEdad(Integer.parseInt(p[5].toString()));
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte2>> entidad = new GenericEntity<List<Reporte2>>(lista) {
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
    @Path("reporteEdadUDI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteEdadUDI(Reporte2 edad) {

        if (edad == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(edad).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, u.udi, ai.nombres, ai.apellidos, ai.genero, cast(date_part('year',age(ai.fecha_nacimiento)) as integer) as EDAD from t_adolescente as ai\n"
                        + "inner join t_adolescente_udi as aiu ON aiu.id_adolescente_udi_pk = ai.id_adolescente_pk\n"
                        + "inner join t_unidad_zonal as uz ON uz.id_unidad_zonal_pk = aiu.id_adolescente_udi_pk\n"
                        + "inner join t_udi as u ON u.id_udi_pk = uz.id_udi_fk\n"
                        + "where date_part('year',age(ai.fecha_nacimiento))=?1");
                query.setParameter(1, edad.getEdad());

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte2> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte2 aux = new Reporte2();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setGenero(p[4].toString());
                        }
                        if (p[5] != null) {
                            aux.setEdad(Integer.parseInt(p[5].toString()));
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte2>> entidad = new GenericEntity<List<Reporte2>>(lista) {
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
    @Path("reporteEdadFechaUDI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteEdadFechaUDI(Date fecha) {

        if (fecha == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(fecha).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, u.udi, ai.nombres, ai.apellidos, ai.genero, cast(date_part('year',age(?1, ai.fecha_nacimiento)) as integer) as EDAD from t_adolescente as ai\n"
                        + "inner join t_adolescente_udi as aiu ON aiu.id_adolescente_udi_pk = ai.id_adolescente_pk\n"
                        + "inner join t_unidad_zonal as uz ON uz.id_unidad_zonal_pk = aiu.id_adolescente_udi_pk\n"
                        + "inner join t_udi as u ON u.id_udi_pk = uz.id_udi_fk");
                query.setParameter(1, fecha);

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte2> lista = new ArrayList<>();
                    for (Object[] p : objetos) {
                        Reporte2 aux = new Reporte2();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setGenero(p[4].toString());
                        }
                        if (p[5] != null) {
                            aux.setEdad(Integer.parseInt(p[5].toString()));
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte2>> entidad = new GenericEntity<List<Reporte2>>(lista) {
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
    @Path("reporteEdadFechaCAI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteEdadFechaCAI(Date fecha) {

        if (fecha == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(fecha).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, cai.cai, ai.nombres, ai.apellidos, ai.genero, cast(date_part('year',age(?1, ai.fecha_nacimiento)) as integer) as EDAD from t_adolescente as ai\n"
                        + "inner join t_adolescente_cai as aic ON aic.id_adolescente_cai_pk = ai.id_adolescente_pk\n"
                        + "inner join t_deta_infraccion_cai as di ON di.id_adolescente_cai_fk = aic.id_adolescente_cai_pk\n"
                        + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = di.id_deta_infrac_cai_pk\n"
                        + "inner join t_cai as cai ON cai.id_cai_pk = emc.id_cai_fk");
                query.setParameter(1, fecha);

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte2> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte2 aux = new Reporte2();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setGenero(p[4].toString());
                        }
                        if (p[5] != null) {
                            aux.setEdad(Integer.parseInt(p[5].toString()));
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte2>> entidad = new GenericEntity<List<Reporte2>>(lista) {
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
    @Path("reporteNacionalidadCAI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteNacionalidadPaisCAI(String nacionalidadCAI) {

        if (nacionalidadCAI == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(nacionalidadCAI).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, ca.cai, ai.nombres, ai.apellidos, ai.nacionalidad, ig.pais_nacimiento, dicai.tipo_penal from t_deta_infraccion_cai as dicai\n"
                        + "inner join t_adolescente_cai as acai on acai.id_adolescente_cai_pk = dicai.id_adolescente_cai_fk\n"
                        + "inner join t_adolescente as ai on ai.id_adolescente_pk = acai.id_adolescente_cai_pk\n"
                        + "inner join t_id_geografica as ig ON ig.id_geografica_pk = ai.id_adolescente_pk\n"
                        + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = dicai.id_deta_infrac_cai_pk\n"
                        + "inner join t_cai as ca on ca.id_cai_pk = emc.id_cai_fk\n"
                        + "where ai.nacionalidad = ?1");
                query.setParameter(1, nacionalidadCAI);

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte3> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte3 aux = new Reporte3();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setNacionalidad(p[4].toString());
                        }
                        if (p[5] != null) {
                            aux.setPaisOrigen(p[5].toString());
                        }
                        if (p[6] != null) {
                            aux.setTipoDelito(p[6].toString());
                        }
                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte3>> entidad = new GenericEntity<List<Reporte3>>(lista) {
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
    @Path("reporteNacionalidadUDI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteNacionalidadPaisUDI(String nacionalidadUDI) {

        if (nacionalidadUDI == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(nacionalidadUDI).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, u.udi, ai.nombres, ai.apellidos, ai.nacionalidad, ig.pais_nacimiento ,ii.tipo_infraccion from t_info_infraccion as ii\n"
                        + "inner join t_adolescente_udi as audi on audi.id_adolescente_udi_pk = ii.id_info_infrac_pk\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = audi.id_adolescente_udi_pk\n"
                        + "inner join t_id_geografica as ig ON ig.id_geografica_pk = ai.id_adolescente_pk\n"
                        + "inner join t_unidad_zonal as uz on uz.id_unidad_zonal_pk = audi.id_adolescente_udi_pk\n"
                        + "inner join t_udi as u ON u.id_udi_pk = uz.id_udi_fk\n"
                        + "where ai.nacionalidad =?1");
                query.setParameter(1, nacionalidadUDI);

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte3> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte3 aux = new Reporte3();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setNacionalidad(p[4].toString());
                        }
                        if (p[5] != null) {
                            aux.setPaisOrigen(p[5].toString());
                        }
                        if (p[6] != null) {
                            aux.setTipoDelito(p[6].toString());
                        }
                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte3>> entidad = new GenericEntity<List<Reporte3>>(lista) {
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
    @Path("movil/reporteNacionalidadCAI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteNacionalidadPaisCAIMovil(Reporte3 nacionalidadCAI) {

        if (nacionalidadCAI == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(nacionalidadCAI).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, ca.cai, ai.nombres, ai.apellidos, ai.nacionalidad, ig.pais_nacimiento, dicai.tipo_penal from t_deta_infraccion_cai as dicai\n"
                        + "inner join t_adolescente_cai as acai on acai.id_adolescente_cai_pk = dicai.id_adolescente_cai_fk\n"
                        + "inner join t_adolescente as ai on ai.id_adolescente_pk = acai.id_adolescente_cai_pk\n"
                        + "inner join t_id_geografica as ig ON ig.id_geografica_pk = ai.id_adolescente_pk\n"
                        + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = dicai.id_deta_infrac_cai_pk\n"
                        + "inner join t_cai as ca on ca.id_cai_pk = emc.id_cai_fk\n"
                        + "where ai.nacionalidad = ?1");
                query.setParameter(1, nacionalidadCAI.getNacionalidad());

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte3> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte3 aux = new Reporte3();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setNacionalidad(p[4].toString());
                        }
                        if (p[5] != null) {
                            aux.setPaisOrigen(p[5].toString());
                        }
                        if (p[6] != null) {
                            aux.setTipoDelito(p[6].toString());
                        }
                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte3>> entidad = new GenericEntity<List<Reporte3>>(lista) {
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
    @Path("movil/reporteNacionalidadUDI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteNacionalidadPaisUDIMovil(Reporte3 nacionalidadUDI) {

        if (nacionalidadUDI == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(nacionalidadUDI).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, u.udi, ai.nombres, ai.apellidos, ai.nacionalidad, ig.pais_nacimiento ,ii.tipo_infraccion from t_info_infraccion as ii\n"
                        + "inner join t_adolescente_udi as audi on audi.id_adolescente_udi_pk = ii.id_info_infrac_pk\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = audi.id_adolescente_udi_pk\n"
                        + "inner join t_id_geografica as ig ON ig.id_geografica_pk = ai.id_adolescente_pk\n"
                        + "inner join t_unidad_zonal as uz on uz.id_unidad_zonal_pk = audi.id_adolescente_udi_pk\n"
                        + "inner join t_udi as u ON u.id_udi_pk = uz.id_udi_fk\n"
                        + "where ai.nacionalidad =?1");
                query.setParameter(1, nacionalidadUDI.getNacionalidad());

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte3> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte3 aux = new Reporte3();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setNacionalidad(p[4].toString());
                        }
                        if (p[5] != null) {
                            aux.setPaisOrigen(p[5].toString());
                        }
                        if (p[6] != null) {
                            aux.setTipoDelito(p[6].toString());
                        }
                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte3>> entidad = new GenericEntity<List<Reporte3>>(lista) {
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
    @Path("reporteMedidaSocioeducativaUDI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteMedidaSocioeducativaUDI(String medidaSocioeducativa) {

        if (medidaSocioeducativa == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(medidaSocioeducativa).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, u.udi, ai.nombres, ai.apellidos, ii.tipo_infraccion, ms.med_socioeducativa from t_info_infraccion as ii\n"
                        + "inner join t_adolescente_udi as audi on audi.id_adolescente_udi_pk = ii.id_info_infrac_pk\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = audi.id_adolescente_udi_pk\n"
                        + "inner join t_unidad_zonal as uz on uz.id_unidad_zonal_pk = audi.id_adolescente_udi_pk\n"
                        + "inner join t_udi as u ON u.id_udi_pk = uz.id_udi_fk\n"
                        + "inner join t_med_socioeducativa as ms ON ms.id_adolescente_udi_fk = audi.id_adolescente_udi_pk\n"
                        + "where ms.med_socioeducativa=?1");
                query.setParameter(1, medidaSocioeducativa);

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte4> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte4 aux = new Reporte4();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setTipoDelito(p[4].toString());
                        }
                        if (p[5] != null) {
                            aux.setMedidaSocioeducativa(p[5].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte4>> entidad = new GenericEntity<List<Reporte4>>(lista) {
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
    @Path("reporteMedidaSocioeducativaCAI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteMedidaSocioeducativaCAI(EjecucionMedidaCAI medida) {

        if (medida == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(medida).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, ca.cai, ai.nombres, ai.apellidos, dicai.tipo_penal, emc.med_cautelar from t_deta_infraccion_cai as dicai\n"
                        + "inner join t_adolescente_cai as acai on acai.id_adolescente_cai_pk = dicai.id_adolescente_cai_fk\n"
                        + "inner join t_adolescente as ai on ai.id_adolescente_pk = acai.id_adolescente_cai_pk\n"
                        + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = dicai.id_deta_infrac_cai_pk\n"
                        + "inner join t_cai as ca on ca.id_cai_pk = emc.id_cai_fk\n"
                        + "where emc.tipo_medida=?1 and emc.med_cautelar=?2");
                query.setParameter(1, medida.getTipoMedida());
                query.setParameter(2, medida.getMedidaCautelar());

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte4> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte4 aux = new Reporte4();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setTipoDelito(p[4].toString());
                        }
                        if (p[5] != null) {
                            aux.setMedidaSocioeducativa(p[5].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte4>> entidad = new GenericEntity<List<Reporte4>>(lista) {
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
    @Path("reporteFechaIngesoCAI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteFechaIngesoCAI(Date fechaIngreso) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (fechaIngreso == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(fechaIngreso).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, ca.cai, ai.nombres, ai.apellidos, emc.fecha_aprhension, emc.tiempo_senten_dias,cast(emc.fecha_aprhension+make_interval(days=>cast(emc.tiempo_senten_dias*0.6 as integer))::interval as date) as \"Fecha de cumplimiento 60%\",cast(emc.fecha_aprhension+make_interval(days=>cast(emc.tiempo_senten_dias*0.8 as integer))::interval as date) as \"Fecha de cumplimiento 80%\",cast((extract(day from now()-emc.fecha_aprhension))/emc.tiempo_senten_dias as DOUBLE PRECISION) as Porcentaje\n"
                        + "from t_deta_infraccion_cai as dicai\n"
                        + "inner join t_adolescente_cai as acai on acai.id_adolescente_cai_pk = dicai.id_adolescente_cai_fk\n"
                        + "inner join t_adolescente as ai on ai.id_adolescente_pk = acai.id_adolescente_cai_pk\n"
                        + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = dicai.id_deta_infrac_cai_pk\n"
                        + "inner join t_cai as ca on ca.id_cai_pk = emc.id_cai_fk\n"
                        + "where emc.fecha_ingreso_cai>=?1");
                query.setParameter(1, fechaIngreso);

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte5> lista = new ArrayList<>();
                    for (Object[] p : objetos) {
                        Reporte5 aux = new Reporte5();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            Date f1 = new Date();
                            f1 = sdf.parse(p[4].toString());
                            aux.setFechaAprehension(f1);
                        }
                        if (p[5] != null) {
                            aux.setTiempoSetenciaMedida(Integer.parseInt(p[5].toString()));
                        }
                        if (p[6] != null) {
                            Date f2 = new Date();
                            f2 = sdf.parse(p[6].toString());
                            aux.setFechaCumplimiento60(f2);
                        }
                        if (p[7] != null) {
                            Date f3 = new Date();
                            f3 = sdf.parse(p[7].toString());
                            aux.setFechaCumplimiento80(f3);
                        }
                        if (p[8] != null) {
                            aux.setPorcentajeCumplimiento(Double.parseDouble(p[8].toString()));
                        }
                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte5>> entidad = new GenericEntity<List<Reporte5>>(lista) {
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
    @Path("reporteNivelEducacionSUDI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteNivelEducacionSUDI(String nivelEducacion) {

        if (nivelEducacion == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(nivelEducacion).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, u.udi, ai.nombres, ai.apellidos, ee.estudia, ee.nivel_edu_actual from t_eje_educativo as ee\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = ee.id_eje_educativo_pk\n"
                        + "inner join t_adolescente_udi as aiu ON aiu.id_adolescente_udi_pk = ai.id_adolescente_pk\n"
                        + "inner join t_unidad_zonal as uz ON uz.id_unidad_zonal_pk = aiu.id_adolescente_udi_pk\n"
                        + "inner join t_udi as u ON u.id_udi_pk = uz.id_udi_fk\n"
                        + "where ee.estudia=true and ee.nivel_edu_actual=?1");
                query.setParameter(1, nivelEducacion);

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte6S> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte6S aux = new Reporte6S();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setEstudia(Boolean.parseBoolean(p[4].toString()));
                        }
                        if (p[5] != null) {
                            aux.setNivelEducativo(p[5].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte6S>> entidad = new GenericEntity<List<Reporte6S>>(lista) {
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
    @Path("movil/reporteNivelEducacionSUDI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteNivelEducacionMovilSUDI(Reporte6S nivelEducacion) {

        if (nivelEducacion == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(nivelEducacion).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, u.udi, ai.nombres, ai.apellidos, ee.estudia, ee.nivel_edu_actual from t_eje_educativo as ee\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = ee.id_eje_educativo_pk\n"
                        + "inner join t_adolescente_udi as aiu ON aiu.id_adolescente_udi_pk = ai.id_adolescente_pk\n"
                        + "inner join t_unidad_zonal as uz ON uz.id_unidad_zonal_pk = aiu.id_adolescente_udi_pk\n"
                        + "inner join t_udi as u ON u.id_udi_pk = uz.id_udi_fk\n"
                        + "where ee.estudia=true and ee.nivel_edu_actual=?1");
                query.setParameter(1, nivelEducacion.getNivelEducativo());

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte6S> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte6S aux = new Reporte6S();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setEstudia(Boolean.parseBoolean(p[4].toString()));
                        }
                        if (p[5] != null) {
                            aux.setNivelEducativo(p[5].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte6S>> entidad = new GenericEntity<List<Reporte6S>>(lista) {
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
    @Path("reporteNivelEducacionSCAI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteNivelEducacionSCAI(String nivelEducacion) {

        if (nivelEducacion == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(nivelEducacion).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, cai.cai, ai.nombres, ai.apellidos, ee.estudia, ee.nivel_edu_actual from t_eje_educativo as ee\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = ee.id_eje_educativo_pk\n"
                        + "inner join t_adolescente_cai as aic ON aic.id_adolescente_cai_pk = ai.id_adolescente_pk\n"
                        + "inner join t_deta_infraccion_cai as di ON di.id_adolescente_cai_fk = aic.id_adolescente_cai_pk\n"
                        + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = di.id_deta_infrac_cai_pk\n"
                        + "inner join t_cai as cai ON cai.id_cai_pk = emc.id_cai_fk\n"
                        + "where ee.estudia=true and ee.nivel_edu_actual=?1");
                query.setParameter(1, nivelEducacion);

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte6S> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte6S aux = new Reporte6S();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setEstudia(Boolean.parseBoolean(p[4].toString()));
                        }
                        if (p[5] != null) {
                            aux.setNivelEducativo(p[5].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte6S>> entidad = new GenericEntity<List<Reporte6S>>(lista) {
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
    @Path("movil/reporteNivelEducacionSCAI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteNivelEducacionMovilSCAI(Reporte6S nivelEducacion) {

        if (nivelEducacion == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(nivelEducacion).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, cai.cai, ai.nombres, ai.apellidos, ee.estudia, ee.nivel_edu_actual from t_eje_educativo as ee\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = ee.id_eje_educativo_pk\n"
                        + "inner join t_adolescente_cai as aic ON aic.id_adolescente_cai_pk = ai.id_adolescente_pk\n"
                        + "inner join t_deta_infraccion_cai as di ON di.id_adolescente_cai_fk = aic.id_adolescente_cai_pk\n"
                        + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = di.id_deta_infrac_cai_pk\n"
                        + "inner join t_cai as cai ON cai.id_cai_pk = emc.id_cai_fk\n"
                        + "where ee.estudia=true and ee.nivel_edu_actual=?1");
                query.setParameter(1, nivelEducacion.getNivelEducativo());

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte6S> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte6S aux = new Reporte6S();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setEstudia(Boolean.parseBoolean(p[4].toString()));
                        }
                        if (p[5] != null) {
                            aux.setNivelEducativo(p[5].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte6S>> entidad = new GenericEntity<List<Reporte6S>>(lista) {
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
    @Path("reporteNivelEducacionNCAI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteNivelEducacionNCAI(String nivelEducacion) {

        if (nivelEducacion == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(nivelEducacion).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, cai.cai, ai.nombres, ai.apellidos, ee.estudia, ee.razon_no_estudia from t_eje_educativo as ee\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = ee.id_eje_educativo_pk\n"
                        + "inner join t_adolescente_cai as aic ON aic.id_adolescente_cai_pk = ai.id_adolescente_pk\n"
                        + "inner join t_deta_infraccion_cai as di ON di.id_adolescente_cai_fk = aic.id_adolescente_cai_pk\n"
                        + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = di.id_deta_infrac_cai_pk\n"
                        + "inner join t_cai as cai ON cai.id_cai_pk = emc.id_cai_fk\n"
                        + "where ee.estudia=false");
                query.setParameter(1, nivelEducacion);

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte6N> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte6N aux = new Reporte6N();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setEstudia(Boolean.parseBoolean(p[4].toString()));
                        }
                        if (p[5] != null) {
                            aux.setRazonNoEstudia(p[5].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte6N>> entidad = new GenericEntity<List<Reporte6N>>(lista) {
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
    @Path("reporteNivelEducacionNUDI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteNivelEducacionNUDI(String nivelEducacion) {

        if (nivelEducacion == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(nivelEducacion).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, u.udi, ai.nombres, ai.apellidos, ee.estudia, ee.razon_no_estudia from t_eje_educativo as ee\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = ee.id_eje_educativo_pk\n"
                        + "inner join t_adolescente_udi as aiu ON aiu.id_adolescente_udi_pk = ai.id_adolescente_pk\n"
                        + "inner join t_unidad_zonal as uz ON uz.id_unidad_zonal_pk = aiu.id_adolescente_udi_pk\n"
                        + "inner join t_udi as u ON u.id_udi_pk = uz.id_udi_fk\n"
                        + "where ee.estudia=false");
                query.setParameter(1, nivelEducacion);

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte6N> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte6N aux = new Reporte6N();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setEstudia(Boolean.parseBoolean(p[4].toString()));
                        }
                        if (p[5] != null) {
                            aux.setRazonNoEstudia(p[5].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte6N>> entidad = new GenericEntity<List<Reporte6N>>(lista) {
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
    @Path("reporteEdadNivelEducativoNoUDI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteEdadNivelEducativoNoUDI(String edad) {

        if (edad == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(edad).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, u.udi, ai.nombres, ai.apellidos,cast(date_part('year',age(ai.fecha_nacimiento)) as integer) as EDAD, ee.estudia, ee.razon_no_estudia from t_eje_educativo as ee\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = ee.id_eje_educativo_pk\n"
                        + "inner join t_adolescente_udi as aiu ON aiu.id_adolescente_udi_pk = ai.id_adolescente_pk\n"
                        + "inner join t_unidad_zonal as uz ON uz.id_unidad_zonal_pk = aiu.id_adolescente_udi_pk\n"
                        + "inner join t_udi as u ON u.id_udi_pk = uz.id_udi_fk\n"
                        + "where ee.estudia=false and cast(date_part('year',age(ai.fecha_nacimiento)) as integer)=?1");
                query.setParameter(1, Integer.parseInt(edad));

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte6N> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte6N aux = new Reporte6N();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setEdad(Integer.parseInt(p[4].toString()));
                        }
                        if (p[5] != null) {
                            aux.setEstudia(Boolean.parseBoolean(p[5].toString()));
                        }
                        if (p[6] != null) {
                            aux.setRazonNoEstudia(p[6].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte6N>> entidad = new GenericEntity<List<Reporte6N>>(lista) {
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
    @Path("reporteEdadNivelEducativoNoCAI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteEdadNivelEducativoNoCAI(String edad) {

        if (edad == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(edad).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, cai.cai, ai.nombres, ai.apellidos,cast(date_part('year',age(ai.fecha_nacimiento)) as integer) as EDAD, ee.estudia, ee.razon_no_estudia from t_eje_educativo as ee\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = ee.id_eje_educativo_pk\n"
                        + "inner join t_adolescente_cai as aic ON aic.id_adolescente_cai_pk = ai.id_adolescente_pk\n"
                        + "inner join t_deta_infraccion_cai as di ON di.id_adolescente_cai_fk = aic.id_adolescente_cai_pk\n"
                        + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = di.id_deta_infrac_cai_pk\n"
                        + "inner join t_cai as cai ON cai.id_cai_pk = emc.id_cai_fk\n"
                        + "where ee.estudia=false and cast(date_part('year',age(ai.fecha_nacimiento)) as integer)=?1");
                query.setParameter(1, Integer.parseInt(edad));

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte6N> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte6N aux = new Reporte6N();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setEdad(Integer.parseInt(p[4].toString()));
                        }
                        if (p[5] != null) {
                            aux.setEstudia(Boolean.parseBoolean(p[5].toString()));
                        }
                        if (p[6] != null) {
                            aux.setRazonNoEstudia(p[6].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte6N>> entidad = new GenericEntity<List<Reporte6N>>(lista) {
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
    @Path("reporteEdadNivelEducativoSiCAI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteEdadNivelEducativoSiCAI(String edad) {

        if (edad == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(edad).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, cai.cai, ai.nombres, ai.apellidos,cast(date_part('year',age(ai.fecha_nacimiento)) as integer) as EDAD, ee.estudia, ee.nivel_edu_actual from t_eje_educativo as ee\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = ee.id_eje_educativo_pk\n"
                        + "inner join t_adolescente_cai as aic ON aic.id_adolescente_cai_pk = ai.id_adolescente_pk\n"
                        + "inner join t_deta_infraccion_cai as di ON di.id_adolescente_cai_fk = aic.id_adolescente_cai_pk\n"
                        + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = di.id_deta_infrac_cai_pk\n"
                        + "inner join t_cai as cai ON cai.id_cai_pk = emc.id_cai_fk\n"
                        + "where ee.estudia=true and cast(date_part('year',age(ai.fecha_nacimiento)) as integer)=?1");
                query.setParameter(1, Integer.parseInt(edad));

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte6S> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte6S aux = new Reporte6S();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setEdad(Integer.parseInt(p[4].toString()));
                        }
                        if (p[5] != null) {
                            aux.setEstudia(Boolean.parseBoolean(p[5].toString()));
                        }
                        if (p[6] != null) {
                            aux.setNivelEducativo(p[6].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte6S>> entidad = new GenericEntity<List<Reporte6S>>(lista) {
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
    @Path("reporteEdadNivelEducativoSiUDI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteEdadNivelEducativoSiUDI(String edad) {

        if (edad == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(edad).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, u.udi, ai.nombres, ai.apellidos,cast(date_part('year',age(ai.fecha_nacimiento)) as integer) as EDAD, ee.estudia, ee.nivel_edu_actual from t_eje_educativo as ee\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = ee.id_eje_educativo_pk\n"
                        + "inner join t_adolescente_udi as aiu ON aiu.id_adolescente_udi_pk = ai.id_adolescente_pk\n"
                        + "inner join t_unidad_zonal as uz ON uz.id_unidad_zonal_pk = aiu.id_adolescente_udi_pk\n"
                        + "inner join t_udi as u ON u.id_udi_pk = uz.id_udi_fk\n"
                        + "where ee.estudia=true and cast(date_part('year',age(ai.fecha_nacimiento)) as integer)=?1");
                query.setParameter(1, Integer.parseInt(edad));

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte6S> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte6S aux = new Reporte6S();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setEdad(Integer.parseInt(p[4].toString()));
                        }
                        if (p[5] != null) {
                            aux.setEstudia(Boolean.parseBoolean(p[5].toString()));
                        }
                        if (p[6] != null) {
                            aux.setNivelEducativo(p[6].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte6S>> entidad = new GenericEntity<List<Reporte6S>>(lista) {
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
    @Path("movil/reporteEdadNivelEducativoNoUDI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteEdadNivelEducativoNoUDI(Reporte6N edad) {

        if (edad == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(edad).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, u.udi, ai.nombres, ai.apellidos,cast(date_part('year',age(ai.fecha_nacimiento)) as integer) as EDAD, ee.estudia, ee.razon_no_estudia from t_eje_educativo as ee\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = ee.id_eje_educativo_pk\n"
                        + "inner join t_adolescente_udi as aiu ON aiu.id_adolescente_udi_pk = ai.id_adolescente_pk\n"
                        + "inner join t_unidad_zonal as uz ON uz.id_unidad_zonal_pk = aiu.id_adolescente_udi_pk\n"
                        + "inner join t_udi as u ON u.id_udi_pk = uz.id_udi_fk\n"
                        + "where ee.estudia=false and cast(date_part('year',age(ai.fecha_nacimiento)) as integer)=?1");
                query.setParameter(1, edad.getEdad());

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte6N> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte6N aux = new Reporte6N();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setEdad(Integer.parseInt(p[4].toString()));
                        }
                        if (p[5] != null) {
                            aux.setEstudia(Boolean.parseBoolean(p[5].toString()));
                        }
                        if (p[6] != null) {
                            aux.setRazonNoEstudia(p[6].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte6N>> entidad = new GenericEntity<List<Reporte6N>>(lista) {
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
    @Path("movil/reporteEdadNivelEducativoNoCAI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteEdadNivelEducativoNoCAI(Reporte6N edad) {

        if (edad == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(edad).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, cai.cai, ai.nombres, ai.apellidos,cast(date_part('year',age(ai.fecha_nacimiento)) as integer) as EDAD, ee.estudia, ee.razon_no_estudia from t_eje_educativo as ee\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = ee.id_eje_educativo_pk\n"
                        + "inner join t_adolescente_cai as aic ON aic.id_adolescente_cai_pk = ai.id_adolescente_pk\n"
                        + "inner join t_deta_infraccion_cai as di ON di.id_adolescente_cai_fk = aic.id_adolescente_cai_pk\n"
                        + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = di.id_deta_infrac_cai_pk\n"
                        + "inner join t_cai as cai ON cai.id_cai_pk = emc.id_cai_fk\n"
                        + "where ee.estudia=false and cast(date_part('year',age(ai.fecha_nacimiento)) as integer)=?1");
                query.setParameter(1, edad.getEdad());

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte6N> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte6N aux = new Reporte6N();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setEdad(Integer.parseInt(p[4].toString()));
                        }
                        if (p[5] != null) {
                            aux.setEstudia(Boolean.parseBoolean(p[5].toString()));
                        }
                        if (p[6] != null) {
                            aux.setRazonNoEstudia(p[6].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte6N>> entidad = new GenericEntity<List<Reporte6N>>(lista) {
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
    @Path("movil/reporteEdadNivelEducativoSiCAI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteEdadNivelEducativoSiCAI(Reporte6S edad) {

        if (edad == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(edad).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, cai.cai, ai.nombres, ai.apellidos,cast(date_part('year',age(ai.fecha_nacimiento)) as integer) as EDAD, ee.estudia, ee.nivel_edu_actual from t_eje_educativo as ee\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = ee.id_eje_educativo_pk\n"
                        + "inner join t_adolescente_cai as aic ON aic.id_adolescente_cai_pk = ai.id_adolescente_pk\n"
                        + "inner join t_deta_infraccion_cai as di ON di.id_adolescente_cai_fk = aic.id_adolescente_cai_pk\n"
                        + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = di.id_deta_infrac_cai_pk\n"
                        + "inner join t_cai as cai ON cai.id_cai_pk = emc.id_cai_fk\n"
                        + "where ee.estudia=true and cast(date_part('year',age(ai.fecha_nacimiento)) as integer)=?1");
                query.setParameter(1, edad.getEdad());

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte6S> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte6S aux = new Reporte6S();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setEdad(Integer.parseInt(p[4].toString()));
                        }
                        if (p[5] != null) {
                            aux.setEstudia(Boolean.parseBoolean(p[5].toString()));
                        }
                        if (p[6] != null) {
                            aux.setNivelEducativo(p[6].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte6S>> entidad = new GenericEntity<List<Reporte6S>>(lista) {
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
    @Path("movil/reporteEdadNivelEducativoSiUDI")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteEdadNivelEducativoSiUDI(Reporte6S edad) {

        if (edad == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(edad).build();
        } else {
            try {
                Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, u.udi, ai.nombres, ai.apellidos,cast(date_part('year',age(ai.fecha_nacimiento)) as integer) as EDAD, ee.estudia, ee.nivel_edu_actual from t_eje_educativo as ee\n"
                        + "inner join t_adolescente as ai ON ai.id_adolescente_pk = ee.id_eje_educativo_pk\n"
                        + "inner join t_adolescente_udi as aiu ON aiu.id_adolescente_udi_pk = ai.id_adolescente_pk\n"
                        + "inner join t_unidad_zonal as uz ON uz.id_unidad_zonal_pk = aiu.id_adolescente_udi_pk\n"
                        + "inner join t_udi as u ON u.id_udi_pk = uz.id_udi_fk\n"
                        + "where ee.estudia=true and cast(date_part('year',age(ai.fecha_nacimiento)) as integer)=?1");
                query.setParameter(1, edad.getEdad());

                List<Object[]> objetos = (List<Object[]>) query.getResultList();

                if (objetos != null && objetos.size() > 0) {
                    List<Reporte6S> lista = new ArrayList<>();

                    for (Object[] p : objetos) {
                        Reporte6S aux = new Reporte6S();
                        if (p[0] != null) {
                            aux.setNumero(Integer.parseInt(p[0].toString()));
                        }
                        if (p[1] != null) {
                            aux.setCai_uzdi(p[1].toString());
                        }
                        if (p[2] != null) {
                            aux.setNombres(p[2].toString());
                        }
                        if (p[3] != null) {
                            aux.setApellidos(p[3].toString());
                        }
                        if (p[4] != null) {
                            aux.setEdad(Integer.parseInt(p[4].toString()));
                        }
                        if (p[5] != null) {
                            aux.setEstudia(Boolean.parseBoolean(p[5].toString()));
                        }
                        if (p[6] != null) {
                            aux.setNivelEducativo(p[6].toString());
                        }

                        lista.add(aux);
                    }
                    GenericEntity<List<Reporte6S>> entidad = new GenericEntity<List<Reporte6S>>(lista) {
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

    @GET
    @Path("reporteLugarResidenciaUDI")
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteLugarResidenciaUDI() {
        try {
            Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, u.udi, ai.nombres, ai.apellidos, ii.tipo_infraccion,ig.provincia_residencia, ig.canton_residencia, ig.direccion_domicilio from t_info_infraccion as ii\n"
                    + "inner join t_adolescente_udi as audi on audi.id_adolescente_udi_pk = ii.id_info_infrac_pk\n"
                    + "inner join t_adolescente as ai ON ai.id_adolescente_pk = audi.id_adolescente_udi_pk\n"
                    + "inner join t_unidad_zonal as uz on uz.id_unidad_zonal_pk = audi.id_adolescente_udi_pk\n"
                    + "inner join t_id_geografica as ig ON ig.id_geografica_pk = ai.id_adolescente_pk\n"
                    + "inner join t_udi as u ON u.id_udi_pk = uz.id_udi_fk");

            List<Object[]> objetos = (List<Object[]>) query.getResultList();

            if (objetos != null && objetos.size() > 0) {
                List<Reporte7> lista = new ArrayList<>();

                for (Object[] p : objetos) {
                    Reporte7 aux = new Reporte7();
                    if (p[0] != null) {
                        aux.setNumero(Integer.parseInt(p[0].toString()));
                    }
                    if (p[1] != null) {
                        aux.setCai_uzdi(p[1].toString());
                    }
                    if (p[2] != null) {
                        aux.setNombres(p[2].toString());
                    }
                    if (p[3] != null) {
                        aux.setApellidos(p[3].toString());
                    }
                    if (p[4] != null) {
                        aux.setTipoDelto(p[4].toString());
                    }
                    if (p[5] != null) {
                        aux.setProvinciaResidencia(p[5].toString());
                    }
                    if (p[6] != null) {
                        aux.setCantonResidencia(p[6].toString());
                    }
                    if (p[7] != null) {
                        aux.setDireccionResidencia(p[7].toString());
                    }
                    lista.add(aux);
                }
                GenericEntity<List<Reporte7>> entidad = new GenericEntity<List<Reporte7>>(lista) {
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
    @Path("reporteLugarResidenciaCAI")
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteLugarResidenciaCAI() {
        try {
            Query query = em.createNativeQuery("select row_number() over (order by ai.nombres) as ide, ca.cai, ai.nombres, ai.apellidos, dicai.tipo_penal,ig.provincia_residencia, ig.canton_residencia, ig.direccion_domicilio from t_deta_infraccion_cai as dicai\n"
                    + "inner join t_adolescente_cai as acai on acai.id_adolescente_cai_pk = dicai.id_adolescente_cai_fk\n"
                    + "inner join t_adolescente as ai on ai.id_adolescente_pk = acai.id_adolescente_cai_pk\n"
                    + "inner join t_ejecucion_medida_cai as emc ON emc.id_deta_infrac_cai_fk = dicai.id_deta_infrac_cai_pk\n"
                    + "inner join t_id_geografica as ig ON ig.id_geografica_pk = ai.id_adolescente_pk\n"
                    + "inner join t_cai as ca on ca.id_cai_pk = emc.id_cai_fk");

            List<Object[]> objetos = (List<Object[]>) query.getResultList();

            if (objetos != null && objetos.size() > 0) {
                List<Reporte7> lista = new ArrayList<>();

                for (Object[] p : objetos) {
                    Reporte7 aux = new Reporte7();
                    if (p[0] != null) {
                        aux.setNumero(Integer.parseInt(p[0].toString()));
                    }
                    if (p[1] != null) {
                        aux.setCai_uzdi(p[1].toString());
                    }
                    if (p[2] != null) {
                        aux.setNombres(p[2].toString());
                    }
                    if (p[3] != null) {
                        aux.setApellidos(p[3].toString());
                    }
                    if (p[4] != null) {
                        aux.setTipoDelto(p[4].toString());
                    }
                    if (p[5] != null) {
                        aux.setProvinciaResidencia(p[5].toString());
                    }
                    if (p[6] != null) {
                        aux.setCantonResidencia(p[6].toString());
                    }
                    if (p[7] != null) {
                        aux.setDireccionResidencia(p[7].toString());
                    }
                    lista.add(aux);
                }
                GenericEntity<List<Reporte7>> entidad = new GenericEntity<List<Reporte7>>(lista) {
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
    @Path("reporteInformesCompletos")
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response reporteInformesCompletos() {
        try {
            Query query = em.createNativeQuery("select u.cedula, u.nombres, u.apellidos, r.rol ,count(i.id_informe_pk) as \"Informes completos\" from t_informe as i\n"
                    + "inner join t_taller as t ON t.id_taller_pk = i.id_taller_fk\n"
                    + "inner join t_usuario as u ON u.id_usuario_pk = t.usuario_fk\n"
                    + "inner join t_rol_centro_usuario as rcu ON rcu.id_rcu_pk = u.id_rcu_fk\n"
                    + "inner join t_rol as r ON r.id_rol_pk = rcu.id_rol_fk\n"
                    + "group by u.cedula, u.nombres, u.apellidos, r.rol");

            List<Object[]> objetos = (List<Object[]>) query.getResultList();

            if (objetos != null && objetos.size() > 0) {
                List<Reporte8> lista = new ArrayList<>();

                for (Object[] p : objetos) {
                    Reporte8 aux = new Reporte8();
                    if (p[0] != null) {
                        aux.setCedula(p[0].toString());
                    }
                    if (p[1] != null) {
                        aux.setNombres(p[1].toString());
                    }
                    if (p[2] != null) {
                        aux.setApellidos(p[2].toString());
                    }
                    if (p[3] != null) {
                        aux.setRol(p[3].toString());
                    }
                    if (p[4] != null) {
                        aux.setCantidadInformesCompletos(Integer.parseInt(p[4].toString()));
                    }
                    lista.add(aux);
                }
                GenericEntity<List<Reporte8>> entidad = new GenericEntity<List<Reporte8>>(lista) {
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
