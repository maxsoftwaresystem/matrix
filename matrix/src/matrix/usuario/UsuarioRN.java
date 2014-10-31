package matrix.usuario;

import java.util.List;

import matrix.util.DAOException;
import matrix.util.DAOFactory;

public class UsuarioRN {

	private UsuarioDAO usuarioDAO;

	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}

	public void salvar(Usuario usuario) throws DAOException {

		Long pk = usuario.getPk();
		long time = System.currentTimeMillis();

		if (pk == null || pk == 0) {
			usuario.setDataInclusao(new java.sql.Date(time));
			this.usuarioDAO.salvar(usuario);
		} else {
			usuario.setDataAlteracao(new java.sql.Date(time));
			this.usuarioDAO.salvar(usuario);
		}

	}

	public List<Usuario> listar() {

		return this.usuarioDAO.listar();
	}

	public void excluir(Usuario usuario) throws DAOException {
		this.usuarioDAO.excluir(usuario);

	}

	public Usuario carregar(Long pk) {

		return this.usuarioDAO.carregar(pk);
	}

	public Usuario buscarPorLogin(String login) {

		return this.usuarioDAO.buscarPorLogin(login);
	}

}
