package epn.edu.ec.servicios;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.RolCentroUsuario;
import epn.edu.ec.entidades.Usuario;
import epn.edu.ec.entidades.User;
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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
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
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario crear(Usuario entidad) {
        super.crear(entidad);
        return entidad;
    }

    @PUT
    @Secured
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario guardarUsuario(Usuario entidad) {
        super.editar(entidad);
        return entidad;
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

    @GET
    @Secured
    @Path("UsuariosActivos")
    @Produces({MediaType.APPLICATION_JSON})
    public Response listarUsuariosActivos() {
        try {
            Query query = em.createNativeQuery("SELECT * FROM t_usuario as u where u.activo=true", Usuario.class);

            List<Usuario> listado = (List<Usuario>) query.getResultList();

            if (!listado.isEmpty()) {
                GenericEntity<List<Usuario>> entidad = new GenericEntity<List<Usuario>>(listado) {
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
    @Secured
    @Path("UsuariosDesactivados")
    @Produces({MediaType.APPLICATION_JSON})
    public Response listarUsuariosDesactivados() {
        try {
            Query query = em.createNativeQuery("SELECT * FROM t_usuario as u where u.activo=false", Usuario.class);

            List<Usuario> listado = (List<Usuario>) query.getResultList();

            if (!listado.isEmpty()) {
                GenericEntity<List<Usuario>> entidad = new GenericEntity<List<Usuario>>(listado) {
                };
                return Response.ok().entity(entidad).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
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
                Query query = em.createNativeQuery(""
                        + "select * from t_usuario as u "
                        + "inner join t_rol_centro_usuario as rcu on rcu.id_rcu_pk=u.id_rcu_fk "
                        + "inner join t_rol as r on r.id_rol_pk = rcu.id_rol_fk "
                        + "where u.usuario = ? and u.contrasenia = ? and u.activo=true", Usuario.class);
                query.setParameter(1, usuario.getUsuario());
                query.setParameter(2, usuario.getContraseña());

                List<Usuario> objetos = (List<Usuario>) query.getResultList();

                if (!objetos.isEmpty()) {
                    Usuario usuarioAux = new Usuario();
                    usuarioAux=objetos.get(0);
                    
                    User user = new User();
                    RolCentroUsuario rcu = new RolCentroUsuario();

                    user.setIdUsuario(usuarioAux.getIdUsuario());
                    user.setApellidos(usuarioAux.getApellidos());
                    user.setCedula(usuarioAux.getCedula());
                    user.setNombres(usuarioAux.getNombres());
                    user.setTelefono(usuarioAux.getTelefono());
                    user.setUsuario(usuarioAux.getUsuario());
                    
                    rcu = usuarioAux.getIdRolUsuarioCentro();
                    user.setIdRolUsuarioCentro(rcu);

                    String token = generarToken(user.getUsuario(), rcu.getIdRol().getRol());
                    user.setToken(token);

                    GenericEntity<User> entidad = new GenericEntity<User>(user) {
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
                .setIssuer("http://localhost:8181/Sistema_SNAI_Servidor")
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
