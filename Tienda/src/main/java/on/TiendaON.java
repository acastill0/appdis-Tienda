package on;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.UsuarioDAO;
import modelo.Usuario;

@Stateless
public class TiendaON {

	@Inject
	private UsuarioDAO usu;

	public void crearUsu(Usuario u) {
		usu.insertar(u);
	}

	public List<Usuario> listadoUsuarios() {
		return usu.listadoUsuarios();
	}
}
