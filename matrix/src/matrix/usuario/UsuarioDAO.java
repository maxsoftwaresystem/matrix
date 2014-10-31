package matrix.usuario;

import java.util.List;

import matrix.util.DAOException;

public interface UsuarioDAO {

	public void salvar(Usuario usuario) throws DAOException;

	public void excluir(Usuario usuario) throws DAOException;

	public List<Usuario> listar();

	public List<Usuario> listar(int maximoResultado, int primeiroResultado);

	public Usuario carregar(Long pk);

	public int getQuantidadeUsuario();

	public Usuario buscarPorLogin(String login);

	public Usuario acessar(String login, String senha);

}
