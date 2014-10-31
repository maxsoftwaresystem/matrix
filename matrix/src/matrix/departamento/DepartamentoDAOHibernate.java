package matrix.departamento;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import matrix.util.JPAUtil;

public class DepartamentoDAOHibernate implements DepartamentoDAO {

	public EntityManager manager;

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void salvar(Departamento departamento) {
		try {
			this.manager.getTransaction().begin();
			this.manager.merge(departamento);
			this.manager.flush();
			this.manager.getTransaction().commit();			
		} catch (RuntimeException e) {
			this.manager.getTransaction().rollback();
			throw e;
		} finally {
			this.manager.close();
		}

	}

	@Override
	public void excluir(Departamento departamento) {
		try {
			EntityManager manager = JPAUtil.getEntityManager();
			manager.getTransaction().begin();
			manager.remove(departamento);
			manager.getTransaction().commit();
			manager.close();
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public Departamento carregar(Long pk) {
		return null;

	}

	public List<Departamento> listar() {
		return listar(true, -1, -1);
	}

	public List<Departamento> listar(int maximoResultado, int primeiroResultado) {
		return listar(false, maximoResultado, primeiroResultado);
	}

	@SuppressWarnings("unchecked")
	public List<Departamento> listar(boolean tudo, int maximoResultado,
			int primeiroResultado) {

		try {
			@SuppressWarnings("rawtypes")
			CriteriaQuery criteria = manager.getCriteriaBuilder().createQuery();
			criteria.select(criteria.from(Departamento.class));

			Query query = manager.createQuery(criteria);
			if (!tudo) {
				query.setMaxResults(maximoResultado);
				query.setFirstResult(primeiroResultado);
			}
			return query.getResultList();
		} finally {
			manager.close();
		}
	}

	@Override
	public int getQuantidadeDepartamentos() {

		return 0;
	}
}
