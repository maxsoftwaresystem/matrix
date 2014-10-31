package springsecurity.beans;

import matrix.usuario.Usuario;
import matrix.usuario.UsuarioDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AcessarDAOUsuario implements AcessarDAO {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	public Usuario acessar(String login, String senha) throws IllegalArgumentException {
		if (isEmptyOrNull(login) || isEmptyOrNull(senha)) {
			throw new IllegalArgumentException("Para acessar o sistema, é necessario informar 'Login' e 'Senha'.");
		}
		Usuario u = usuarioDAO.acessar(login, senha);

		if (u == null) {
			throw new IllegalArgumentException("Erro: 'Login' ou 'Senha' incorretos.");
		}
		return u;
	}

	private boolean isEmptyOrNull(String s) {
		return s == null || s.equals("");
	}
}
