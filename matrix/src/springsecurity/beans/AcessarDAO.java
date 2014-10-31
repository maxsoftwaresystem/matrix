package springsecurity.beans;

import matrix.usuario.Usuario;

public interface AcessarDAO {
	
	Usuario acessar(String login, String senha) throws IllegalArgumentException;

}
