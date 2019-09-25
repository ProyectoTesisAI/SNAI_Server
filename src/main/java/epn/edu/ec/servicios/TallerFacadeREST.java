package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.CAI;
import epn.edu.ec.entidades.ItemTaller;
import epn.edu.ec.entidades.Taller;
import epn.edu.ec.entidades.UDI;
import epn.edu.ec.entidades.Usuario;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Stateless
@Path("Taller")
@Secured
public class TallerFacadeREST extends AbstractFacade<Taller> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public TallerFacadeREST() {
        super(Taller.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Taller crear(Taller entidad) {
        return super.crear(entidad);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Taller guardarTaller(Taller entidad) {
        Taller taller = super.editar(entidad);
        return taller;
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
    public Taller buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Path("TalleresSinInforme")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Taller> listarTalleresSinInforme() {
        Query query = em.createQuery("SELECT tp FROM Taller AS tp WHERE NOT tp.idTaller in (SELECT t.idTaller FROM Informe as i INNER JOIN i.idTaller as t)");
        List<Taller> talleres = (List<Taller>) query.getResultList();

        return talleres;
    }

    @POST
    @Path("TalleresSinInformePorUsuario")//este estapor terminarse
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response listarTalleresSinInformePorUsuario(Usuario usuario) {
        if (usuario.getIdUsuario() < 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(usuario).build();
        } else {
            try {
                Query query = em.createNativeQuery("select * from t_taller as t where not t.id_taller_pk in (select i.id_taller_fk from t_informe as i) and t.usuario_fk=?1", Taller.class);
                query.setParameter(1, usuario.getIdUsuario());
                List<Taller> listado = query.getResultList();

                if (!listado.isEmpty()) {
                    List<Taller> talleres = new ArrayList<>();
                    for (Taller t : listado) {
                        Taller taller = new Taller();
                        CAI cai = new CAI();
                        UDI udi = new UDI();
                        Usuario user = new Usuario();

                        taller.setIdTaller(t.getIdTaller());
                        taller.setTema(t.getTema());
                        taller.setNumeroTaller(t.getNumeroTaller());
                        taller.setFecha(t.getFecha());
                        taller.setHoraInicio(t.getHoraInicio());
                        taller.setObjetivo(t.getObjetivo());
                        taller.setNumeroTotalParticipantes(t.getNumeroTotalParticipantes());
                        taller.setRecomendaciones(t.getRecomendaciones());
                        taller.setTipo(t.getTipo());

                        if (t.getIdCai() != null) {
                            cai.setIdCai(t.getIdCai().getIdCai());
                            cai.setCai(t.getIdCai().getCai());
                            taller.setIdCai(cai);
                        } else if (t.getIdCai() == null) {
                            cai = new CAI();
                            taller.setIdCai(cai);
                        }
                        if (t.getIdUdi() != null) {
                            udi.setIdUdi(t.getIdUdi().getIdUdi());
                            udi.setUdi(t.getIdUdi().getUdi());
                            taller.setIdUdi(udi);
                        } else {
                            udi = null;
                            taller.setIdUdi(udi);
                        }

                        user = t.getIdUsuario();

                        talleres.add(t);
                    }
                    GenericEntity<List<Taller>> entidad = new GenericEntity<List<Taller>>(talleres) {
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
    @Path("TalleresSinInformeSoloUZDI")//este estapor terminarse
    @Produces({MediaType.APPLICATION_JSON})
    public Response listarTalleresSinInformeSoloUZDI() {
        try {
            Query query = em.createNativeQuery("select * from t_taller as t where not t.id_taller_pk in (select i.id_taller_fk from t_informe as i) and t.id_udi_fk is not null and t.id_cai_fk is null", Taller.class);
            List<Taller> listado = query.getResultList();

            if (!listado.isEmpty()) {
                GenericEntity<List<Taller>> entidad = new GenericEntity<List<Taller>>(listado) {
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
    @Path("TalleresSinInformeSoloCAI")//este estapor terminarse
    @Produces({MediaType.APPLICATION_JSON})
    public Response listarTalleresSinInformeSoloCAI() {
        try {
            Query query = em.createNativeQuery("select * from t_taller as t where not t.id_taller_pk in (select i.id_taller_fk from t_informe as i) and t.id_cai_fk is not null and t.id_udi_fk is null", Taller.class);
            List<Taller> listado = query.getResultList();

            if (!listado.isEmpty()) {
                GenericEntity<List<Taller>> entidad = new GenericEntity<List<Taller>>(listado) {
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
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Taller> listarTodo() {
        return super.listarTodo();
    }

    @POST
    @Path("NumeroAdolescentesPorUzdi")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response adolescentesInfractoresPorUzdi(UDI unidadZonal) {

        if (unidadZonal == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(unidadZonal).build();
        } else {
            try {
                Query query = em.createNativeQuery("SELECT count(*) FROM t_unidad_zonal as uz INNER JOIN t_udi as u ON u.id_udi_pk = uz.id_udi_fk WHERE u.udi = ?1");
                query.setParameter(1, unidadZonal.getUdi());
                Integer cantidadAdolescentesUDI = Integer.parseInt(query.getSingleResult().toString());

                if (cantidadAdolescentesUDI > 0) {
                    return Response.ok().entity(cantidadAdolescentesUDI.toString()).build();
                } else {
                    return Response.status(Response.Status.NO_CONTENT).build();
                }

            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @POST
    @Path("NumeroAdolescentesPorCai")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response adolescentesInfractoresPorCai(CAI cai) {

        if (cai == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(cai).build();
        } else {
            try {
                Query query = em.createNativeQuery("SELECT count(*) FROM t_ejecucion_medida_cai as emc INNER JOIN t_cai as c ON c.id_cai_pk=emc.id_cai_fk WHERE c.cai = ?1");
                query.setParameter(1, cai.getCai());
                Integer cantidadAdolescentesUDI = Integer.parseInt(query.getSingleResult().toString());

                if (cantidadAdolescentesUDI > 0) {
                    return Response.ok().entity(cantidadAdolescentesUDI.toString()).build();
                } else {
                    return Response.status(Response.Status.NO_CONTENT).build();
                }

            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }

    }

    @GET
    @Path("ItemsTaller/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response listarItemsPorTaller(@PathParam("id") Integer id) {

        if (id == null || id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(id).build();
        } else {
            try {
                Query query = em.createNativeQuery("SELECT * FROM t_item_taller as i WHERE i.id_taller_fk = ?1");
                query.setParameter(1, id);

                List<Object[]> lista = (List<Object[]>) query.getResultList();

                if (lista != null && lista.size() > 0) {

                    List<ItemTaller> items = new ArrayList<>();
                    for (Object[] a : lista) {

                        ItemTaller item = new ItemTaller();
                        item.setIdItemTaller(Integer.parseInt(a[0].toString()));
                        item.setDuracion(Integer.parseInt((a[1].toString())));
                        item.setActividad(a[2].toString());
                        item.setObjetivoEspecifico(a[3].toString());
                        item.setMateriales(a[4].toString());
                        item.setResponsable(a[5].toString());

                        items.add(item);
                    }

                    GenericEntity<List<ItemTaller>> entidad = new GenericEntity<List<ItemTaller>>(items) {
                    };
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
