package epn.edu.ec.filter;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import epn.edu.ec.anotacion.Secured;
import epn.edu.ec.entidades.Rol;
import epn.edu.ec.entidades.RolCentroUsuario;
import epn.edu.ec.entidades.Usuario;
import epn.edu.ec.seguridad.MyApplicationSecurityContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class RestSecurityFilter implements ContainerRequestFilter {
	
	public static final Key KEY = MacProvider.generateKey();
 
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
 
        // Recupera la cabecera HTTP Authorization de la petici�n
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
 
        try {
        	// Extrae el token de la cabecera
            String token = authorizationHeader.substring("Bearer".length()).trim();
 
            // Valida el token utilizando la cadena secreta
            Jws<Claims> claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
            
            //Creamos el usuario a partir de la informaci�n del token
            Usuario usuario = new Usuario();
            usuario.setUsuario(claims.getBody().getSubject());
            
            String roles = (String) claims.getBody().get("roles");
            Rol rol = new Rol();
            rol.setRol(roles);
            
            
            RolCentroUsuario rolCentroUsuario= new RolCentroUsuario();
            rolCentroUsuario.setIdRol(rol);
            
            usuario.setIdRolUsuarioCentro(rolCentroUsuario);
            
            // Creamos el SecurityContext
            MyApplicationSecurityContext secContext = new MyApplicationSecurityContext(usuario, requestContext.getSecurityContext().isSecure());
            
            //Seteamos el contexto de seguridad
            requestContext.setSecurityContext(secContext);
            
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
