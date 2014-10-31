package springsecurity.beans;

import matrix.usuario.Usuario;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class UsuarioSessao {
	
	private Usuario usuario;

	public Usuario getUser() {
		return this.usuario;
	}

	public void setUser(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLoggedIn() {
		return this.usuario != null;
	}
}