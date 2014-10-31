package matrix.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import matrix.departamento.Departamento;
import matrix.departamento.DepartamentoRN;
import matrix.util.DAOException;

@ManagedBean
@RequestScoped
public class DepartamentoBean {

	private Departamento departamento = new Departamento();
	private List<Departamento> lista;

	public void novo() {
		this.departamento = new Departamento();
	}

	public String editar() {
		return "/modulos/departamento/lista";
	}

	public void salvar() throws DAOException {

		DepartamentoRN departamentoRN = new DepartamentoRN();
		departamentoRN.salvar(departamento);
	}

	public void excluir() throws DAOException {

		DepartamentoRN departamentoRN = new DepartamentoRN();
		departamentoRN.excluir(this.departamento);
		this.lista = null;
	}

	public List<Departamento> Listar() {
		if (this.lista == null) {
			DepartamentoRN departamentoRN = new DepartamentoRN();
			this.lista = departamentoRN.listar();
		}
		return this.lista;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Departamento> getLista() {
		return lista;
	}

}
