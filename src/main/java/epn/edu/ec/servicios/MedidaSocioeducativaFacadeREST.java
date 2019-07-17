package epn.edu.ec.servicios;

import epn.edu.ec.entidades.AdolescenteInfractorUDI;
import epn.edu.ec.entidades.MedidaSocioeducativa;
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
@Path("Medida_Socioeducativa")
public class MedidaSocioeducativaFacadeREST extends AbstractFacade<MedidaSocioeducativa> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public MedidaSocioeducativaFacadeREST() {
        super(MedidaSocioeducativa.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public MedidaSocioeducativa crear(MedidaSocioeducativa entidad) {
        return super.crear(entidad);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public MedidaSocioeducativa guardarMedidaSocioeducativa(MedidaSocioeducativa entidad) {
        return super.editar(entidad);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public MedidaSocioeducativa editar(@PathParam("id") Integer id, MedidaSocioeducativa entidad) {
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
    public MedidaSocioeducativa buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<MedidaSocioeducativa> listarTodo() {
        return super.listarTodo();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Path("Adolescente/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response listaMedidasSocioeducativasPorAdolescente(@PathParam("id") Integer id) {
 
        if ( id < 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(id).build();
        } else {
            try {
                Query query = em.createNativeQuery("SELECT * FROM t_med_socioeducativa as m where m.id_adolescente_udi_fk = ?1", MedidaSocioeducativa.class);
                query.setParameter(1, id);
                List<MedidaSocioeducativa> listado = query.getResultList();

                if (!listado.isEmpty()) {

                    List<MedidaSocioeducativa> medidasSocioeducativas = new ArrayList<>();

                    for (MedidaSocioeducativa m : listado) {

                        MedidaSocioeducativa medida = new MedidaSocioeducativa();
                        medida.setIdMedidaSocioeducativa(m.getIdMedidaSocioeducativa());
                        medida.setMedidaSocioeducativa(m.getMedidaSocioeducativa());
                        medida.setTiempoMeses(m.getTiempoMeses());
                        medida.setTiempoDia(m.getTiempoDia());
                        medida.setTiempoHoras(m.getTiempoHoras());
                        medida.setCumplioMedida(m.getCumplioMedida());
                        medida.setFechaFinMedida(m.getFechaFinMedida());
                        medida.setInstitucionEjecutoraMedida(m.getInstitucionEjecutoraMedida());

                        medidasSocioeducativas.add(medida);
                    }
                    GenericEntity<List<MedidaSocioeducativa>> entidad = new GenericEntity<List<MedidaSocioeducativa>>(medidasSocioeducativas) {
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
    @Path("MedidaMasAlta/AdolescenteUDI/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response obtenerMedidaMasAlta(@PathParam("id") Integer id) {

        if (id<0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(id).build();
        } else {
            try {
                Query query = em.createNativeQuery("SELECT med_socioeducativa, tiempo_meses,tiempo_dia, (tiempo_meses*30 + tiempo_dia ) as tiempo_final_dias, tiempo_horas "
                        + "FROM t_med_socioeducativa where t_med_socioeducativa.id_adolescente_udi_fk = ?1 order by tiempo_final_dias desc, tiempo_horas desc limit 1");
                query.setParameter(1, id);

                List<Object[]> lista = (List<Object[]>) query.getResultList();

                if (lista != null && lista.size() > 0) {

                    List<MedidaSocioeducativa> medidaMasAlta = new ArrayList<>();
                    MedidaSocioeducativa medida = new MedidaSocioeducativa();
                    for (Object[] a : lista) {

                        medida.setMedidaSocioeducativa(a[0].toString());
                        medida.setTiempoMeses(Integer.parseInt(a[1].toString()));
                        medida.setTiempoDia(Integer.parseInt(a[2].toString()));
                        medidaMasAlta.add(medida);
                    }

                    GenericEntity<List<MedidaSocioeducativa>> entidad = new GenericEntity<List<MedidaSocioeducativa>>(medidaMasAlta) {
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
}
