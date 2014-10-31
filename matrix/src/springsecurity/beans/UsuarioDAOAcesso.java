package springsecurity.beans;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import matrix.usuario.Usuario;
import matrix.usuario.UsuarioDAO;

import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOAcesso implements UsuarioDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("rawtypes")
	@Override
	public Usuario acessar(String login, String senha) {
		String query = "FROM Usuario u WHERE u.login = ? AND u.senha = ? AND u.situacao = true";
		Query q = entityManager.createQuery(query);
		q.setParameter(1, login);
		q.setParameter(2, senha);

		List l = q.getResultList();

		if (l == null || l.isEmpty() || l.size() > 1) {
			return null;
		}
		return (Usuario) l.get(0);
	}

	@Override
	public void salvar(Usuario usuario) {}

	@Override
	public void excluir(Usuario usuario) {}

	@Override
	public Usuario carregar(Long pk) {return null;}

	@Override
	public Usuario buscarPorLogin(String login) {return null;}

	@Override
	public List<Usuario> listar() {return null;}

	@Override
	public List<Usuario> listar(int maximoResultado, int primeiroResultado) {return null;}

	@Override
	public int getQuantidadeUsuario() {return 0;}

}
