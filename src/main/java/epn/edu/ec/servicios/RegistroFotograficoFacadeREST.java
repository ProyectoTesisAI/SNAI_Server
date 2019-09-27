package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.Informe;
import epn.edu.ec.entidades.RegistroFotografico;
import epn.edu.ec.entidades.RegistroFotograficoAux;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("Registro_Fotografico")
@Secured
public class RegistroFotograficoFacadeREST extends AbstractFacade<RegistroFotografico> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public RegistroFotograficoFacadeREST() {
        super(RegistroFotografico.class);
    }
    

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public RegistroFotografico guardarRegistroFotografico(RegistroFotografico entidad) {
        return super.editar(entidad);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public RegistroFotografico buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<RegistroFotografico> listarTodo() {
        return super.listarTodo();
    }
    
    
    @GET
    @Path("Informe/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RegistroFotografico> obtenerRegistroFotograficoPorInforme(@PathParam("id") Integer id) {
        Query query= em.createNativeQuery("SELECT * FROM t_registro_fotografico where id_informe_fk = ?1");
        query.setParameter(1, id);
        
        List<Object[]> lista = (List<Object[]>) query.getResultList();
        
        if (lista != null && lista.size() > 0) {

            List<RegistroFotografico> registroFotograficoAux= new ArrayList<>();
            
            for (Object[] a : lista) {
                
                RegistroFotografico registro= new RegistroFotografico();
                
                
                registro.setIdRegistroFotografico(Integer.parseInt(a[0].toString()));
                if(a[1]!=null){
                    registro.setImagen((byte[]) a[1]);
                }
                if(a[2]!= null){
                    
                    Informe informe= new Informe();
                    informe.setIdInforme(Integer.parseInt(a[2].toString()));
                    registro.setIdInforme(informe);
                }
                registroFotograficoAux.add(registro);

            }
            
            
            return registroFotograficoAux;
        } else {
            return null;
        }
        
    }
    
    @GET
    @Path("Informe/Movil/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<RegistroFotograficoAux> obtenerRegistroFotograficoPorInforme2(@PathParam("id") Integer id) {
        Query query= em.createNativeQuery("SELECT * FROM t_registro_fotografico where id_informe_fk = ?1");
        query.setParameter(1, id);
        
        List<Object[]> lista = (List<Object[]>) query.getResultList();
        
        if (lista != null && lista.size() > 0) {

            List<RegistroFotograficoAux> registroFotograficoAux= new ArrayList<>();
            
            for (Object[] a : lista) {
                
                RegistroFotograficoAux registro2= new RegistroFotograficoAux();
                
                registro2.setIdRegistroFotografico(Integer.parseInt(a[0].toString()));
                if(a[1]!=null){
                    String imagen=Base64.getEncoder().encodeToString((byte[]) a[1]);
                    registro2.setImagenAux(imagen);
                }
                if(a[2]!= null){
                    
                    Informe informe= new Informe();
                    informe.setIdInforme(Integer.parseInt(a[2].toString()));
                    registro2.setIdInforme(informe);
                }
                registroFotograficoAux.add(registro2);

            }
            
            
            return registroFotograficoAux;
        } else {
            return null;
        }
        
    }
    
 
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
