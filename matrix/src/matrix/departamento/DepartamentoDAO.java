package matrix.departamento;

import java.util.List;
import matrix.util.DAOException;

public interface DepartamentoDAO {

	public void salvar(Departamento departamento) throws DAOException;

	public void excluir(Departamento departamento) throws DAOException;

	public List<Departamento> listar();

	public List<Departamento> listar(int maximoResultado, int primeiroResultado);

	public List<Departamento> listar(boolean tudo, int maximoResultado,	int primeiroResultado);

	public Departamento carregar(Long pk);

	public int getQuantidadeDepartamentos();
}
