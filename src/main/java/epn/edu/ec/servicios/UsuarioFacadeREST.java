/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.Rol;
import epn.edu.ec.entidades.Usuario;
import epn.edu.ec.filter.RestSecurityFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Calendar;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
@Stateless
@Path("Usuario")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "SistemaSNAI_UnidadPersistencia")
    private EntityManager em;

    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    @POST
    @Secured
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public Usuario crear(Usuario entidad) {
        super.crear(entidad);
        return entidad;
    }
    
    @PUT
    @Secured
    @Consumes({MediaType.APPLICATION_JSON})
    public Usuario guardarUsuario(Usuario entidad) {
        super.editar(entidad);
        return entidad;
    }

    @DELETE
    @Secured
    @Path("{id}")
    public void eliminar(@PathParam("id") Integer id) {
        super.eliminar(super.buscarPorId(id));
    }

    @GET
    @Secured
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario buscarPorId(@PathParam("id") Integer id) {
        return super.buscarPorId(id);
    }

    @GET
    @Secured
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuario> listarTodo() {
        return super.listarTodo();
    }

    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
    public Response login(Usuario usuario) {

        if (usuario == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(usuario).build();
        } else {
            try {
                Query query = em.createNativeQuery("select u.id_usuario_pk,u.nombres,u.apellidos,u.cedula,u.telefono,u.usuario, r.rol from t_usuario as u\n" +
                                                    "inner join t_rol as r ON r.id_rol_pk = u.id_rol_fk where u.usuario = ? and u.contrasenia = ? and u.activo=true ");
                query.setParameter(1, usuario.getUsuario());
                query.setParameter(2, usuario.getContraseña());
                
                List<Object[]> objetos = (List<Object[]>) query.getResultList(); 
                
                if (objetos != null && objetos.size() > 0) {
                
                    Usuario usuarioAux= new Usuario();
                    Rol rolAux= new Rol();
                    
                    String token=null;
                    
                    for (Object[] p : objetos) {
                        
                        if(p[0]!=null){
                            usuarioAux.setIdUsuario(Integer.parseInt(p[0].toString()));
                        }
                        if(p[1]!=null){
                            usuarioAux.setNombres(p[1].toString());
                        }
                        if(p[2]!=null){
                            usuarioAux.setApellidos(p[2].toString());
                        }
                        if(p[3]!=null){
                            usuarioAux.setCedula(p[3].toString());
                        }
                        if(p[4]!=null){
                            usuarioAux.setTelefono(p[4].toString());
                        }
                        if(p[5]!=null){
                            usuarioAux.setUsuario(p[5].toString());
                        }
                        if(p[6]!=null){
                            rolAux.setRol(p[6].toString()); 
                        }
                        
                        usuarioAux.setIdRol(rolAux);
                        
                        token=generarToken(usuarioAux.getUsuario(), usuarioAux.getIdRol().getRol());
                        usuarioAux.setToken(token);
                    }
                    GenericEntity<Usuario> entidad = new GenericEntity<Usuario>(usuarioAux) {
                    };
                    //return Response.ok().header(HttpHeaders.AUTHORIZATION, token).entity(entidad).build();
                    return Response.ok().entity(entidad).build();
                } else {
                    return Response.status(Response.Status.NO_CONTENT).build();
                }
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
    }
    
    private String generarToken(String usuario, String rol) {
    	//Calculamos la fecha de expiración del token
    	Date issueDate = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(issueDate);
    	calendar.add(Calendar.MINUTE, 60);
        Date expireDate = calendar.getTime();
        
		//Creamos el token
        String jwtToken = Jwts.builder()
        		.claim("roles", rol)
                .setSubject(usuario)
                .setIssuer("http://localhost:40040/Sistema_SNAI_Servidor/webresources/Usuario/login")
                .setIssuedAt(issueDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, RestSecurityFilter.KEY)
                .compact();
        return jwtToken;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
