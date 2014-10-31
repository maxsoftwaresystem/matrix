package matrix.departamento;

import java.util.List;

import matrix.util.DAOException;
import matrix.util.DAOFactory;

public class DepartamentoRN {

	private DepartamentoDAO departamentoDAO;

	public DepartamentoRN() {
		this.departamentoDAO = DAOFactory.criarDepartamentoDAO();
	}

	public Departamento carregar(Long pk) {
		return this.departamentoDAO.carregar(pk);
	}

	public void salvar(Departamento departamento) throws DAOException {

		this.departamentoDAO.salvar(departamento);

	}

	public void excluir(Departamento departamento) throws DAOException {
		this.departamentoDAO.excluir(departamento);
	}

	public List<Departamento> listar() {
		return this.departamentoDAO.listar();
	}

}
