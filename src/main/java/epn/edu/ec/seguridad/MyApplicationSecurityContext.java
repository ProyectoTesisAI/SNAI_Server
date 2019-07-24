package epn.edu.ec.seguridad;

import epn.edu.ec.entidades.Usuario;
import java.security.Principal;

import javax.ws.rs.core.SecurityContext;


public class MyApplicationSecurityContext implements SecurityContext {
	
	private Usuario usuario;
	private boolean secure;
	
	public MyApplicationSecurityContext(Usuario usuario, boolean secure){
		this.usuario = usuario;
		this.secure = secure;
	}

	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.FORM_AUTH;
	}

	@Override
	public Principal getUserPrincipal() {
		return usuario;
	}

	@Override
	public boolean isSecure() {
		return secure;	
	}

	@Override
	public boolean isUserInRole(String rol) {
            if (usuario.getIdRolUsuarioCentro().getIdRol().getRol()!= null) {
                return usuario.getIdRolUsuarioCentro().getIdRol().getRol().contains(rol);
            }
            return false;
	}

}
