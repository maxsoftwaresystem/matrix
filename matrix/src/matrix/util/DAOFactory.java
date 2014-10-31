package matrix.util;

import matrix.departamento.DepartamentoDAO;
import matrix.departamento.DepartamentoDAOHibernate;
import matrix.usuario.UsuarioDAO;
import matrix.usuario.UsuarioDAOHibernate;

public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {

		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setManager(JPAUtil.getEntityManager());
		return usuarioDAO;
	}
	
	public static DepartamentoDAO criarDepartamentoDAO() {

		DepartamentoDAOHibernate departamentoDAO = new DepartamentoDAOHibernate();
		departamentoDAO.setManager(JPAUtil.getEntityManager());
		return departamentoDAO;
	}

}
