package springsecurity.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import matrix.usuario.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
@Scope("request")
public class Authenticator implements AuthenticationProvider {

	@Autowired
	private AcessarDAO acessarDAO;

	@Autowired
	private UsuarioSessao sessao = null;

	private String login;
	private String senha;

	public String acessar() {
		try {
			Usuario usuario = acessarDAO.acessar(login, senha);
			loginSpringSecurity(usuario);
			sessao.setUser(usuario);
			return "/modulos/principal.jsf";
		} catch (IllegalArgumentException ex) {
			message(ex.getMessage());
		}
		return "";
	}

	private void loginSpringSecurity(Usuario usuario) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usuario.getLogin(), null, null);
		token.setDetails(usuario);
		SecurityContextHolder.createEmptyContext();
		SecurityContextHolder.getContext().setAuthentication(token);
	}

	public String logout() {
		SecurityContextHolder.clearContext();
		sessao.setUser(null);
		return "index";
	}

	private void message(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensagem));
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public Authentication authenticate(Authentication arg0)	throws AuthenticationException {
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

}