package matrix.web;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import matrix.departamento.Departamento;
import matrix.departamento.DepartamentoRN;
import matrix.usuario.Usuario;
import matrix.usuario.UsuarioRN;
import matrix.util.DAOException;
import matrix.util.MyAES;

@ManagedBean
@RequestScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private List<Usuario> lista;
	private String destinoSalvar;
	private Date dataInclusaoUsuario;
	
	private List<SelectItem> tipos;
	private MyAES aes = new MyAES();	

	public String novo() {

		this.destinoSalvar = "usuarioLista";
		this.usuario = new Usuario();
		return "usuarioCadastro";
	}
	
	public String editar() {
		this.dataInclusaoUsuario = this.usuario.getDataInclusao();
		return "usuarioCadastro";
	}

	public String salvar() throws DAOException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException {

		String senhaOriginal = this.usuario.getSenha();
		String senhaCriptografada = aes.encriptar(aes.getValorHexdecimal(senhaOriginal));
		this.usuario.setSenha(senhaCriptografada);
		
		UsuarioRN usuarioRN = new UsuarioRN();
		this.usuario.setDataInclusao(this.dataInclusaoUsuario);
		usuarioRN.salvar(this.usuario);

		return this.destinoSalvar;

	}

	public String excluir() throws DAOException {

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		this.lista = null;
		return null;
	}

	public List<Usuario> getlista() {
		if (this.lista == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return this.lista;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public List<Departamento> getDepartamentos() {

		DepartamentoRN listaDepartamentos = new DepartamentoRN();
		List<Departamento> departamentos;
		departamentos = listaDepartamentos.listar();
		return departamentos;
		
	}

	public List<SelectItem> getTipos() {
		
		if(this.tipos == null){
			this.tipos = new ArrayList<SelectItem>();
			this.tipos.add(new SelectItem("ROLE_ADMINISTRADOR", "Administrador"));
			this.tipos.add(new SelectItem("ROLE_USUARIO", "Usuário"));
		}
		
		return this.tipos;
	}

}
