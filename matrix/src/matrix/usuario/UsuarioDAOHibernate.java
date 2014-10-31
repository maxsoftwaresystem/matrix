package matrix.usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import matrix.util.DAOException;

public class UsuarioDAOHibernate implements UsuarioDAO {

	public EntityManager manager;

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void salvar(Usuario usuario) throws DAOException {
		try {
			this.manager.getTransaction().begin();
			this.manager.merge(usuario);
			this.manager.flush();
			this.manager.getTransaction().commit();
		} catch (RuntimeException e) {
			try {
				this.manager.getTransaction().rollback();
			} catch (Exception ex) {
				throw new DAOException("Ocorreu erro ao tentar realizar o Rollback do metodo salvar usuário.", ex);
			}
			String msg = e.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long pk = usuario.getPk();
				if (carregar(pk) == null) {
					throw new DAOException("O usuário com o código " + pk + " não existe.");
				}
			}
			throw e;
		} finally {
			if (this.manager != null) {
				this.manager.close();
			}
		}

	}

	@Override
	public void excluir(Usuario usuario) throws DAOException {
		
		Usuario usuarioExcluir = this.manager.find(Usuario.class, usuario.getPk());
		
		try {		
			
			this.manager.getTransaction().begin();
			this.manager.remove(usuarioExcluir);
			this.manager.flush();
			this.manager.getTransaction().commit();
		} catch (RuntimeException e) {
			try {
				this.manager.getTransaction().rollback();
			} catch (Exception ex) {
				throw new DAOException("Ocorreu erro ao tentar realizar o Rollback do metodo excluir usuário.", ex);
			}
			String msg = e.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long pk = usuario.getPk();
				if (carregar(pk) == null) {
					throw new DAOException("O usuário com o código " + pk + " não existe.");
				}
			}
			throw e;
		} finally {
			if (this.manager != null) {
				this.manager.close();
			}
		}
	}

	public List<Usuario> listar() {
		return listar(true, -1, -1);
	}

	public List<Usuario> listar(int maximoResultado, int primeiroResultado) {
		return listar(false, maximoResultado, primeiroResultado);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Usuario> listar(boolean tudo, int maximoResultado, int primeiroResultado) {

		try {			
			CriteriaQuery criteria = this.manager.getCriteriaBuilder().createQuery();
			criteria.select(criteria.from(Usuario.class));

			Query query = this.manager.createQuery(criteria);
			if (!tudo) {
				query.setMaxResults(maximoResultado);
				query.setFirstResult(primeiroResultado);
			}
			return query.getResultList();
		} finally {
			if (this.manager != null) {
				this.manager.close();
			}
		}
	}

	public Usuario carregar(Long pk) {

		try {
			return this.manager.find(Usuario.class, pk);
		} finally {
			if (this.manager != null) {
				this.manager.close();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int getQuantidadeUsuario() {

		try {
			CriteriaQuery criteria = this.manager.getCriteriaBuilder().createQuery();
			Root<Usuario> root = criteria.from(Usuario.class);
			criteria.select(this.manager.getCriteriaBuilder().count(root));
			Query query = this.manager.createQuery(criteria);
			return ((Long) query.getSingleResult()).intValue();
		} finally {
			if (this.manager != null) {
				this.manager.close();
			}
		}
	}

	@Override
	public Usuario buscarPorLogin(String login) {
		try {
			this.manager.getTransaction().begin();
			Usuario usuario = this.manager.find(Usuario.class, login);
			this.manager.getTransaction().commit();
			this.manager.flush();
			return usuario;
		} catch (RuntimeException e) {
			this.manager.getTransaction().rollback();
			throw e;
		} finally {
			if (this.manager != null) {
				this.manager.close();
			}
		}

	}

	@Override
	public Usuario acessar(String login, String senha) {return null;}	

}
