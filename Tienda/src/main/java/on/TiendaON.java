package on;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.PeliculaDAO;
import dao.UsuarioDAO;
import modelo.Producto;
import modelo.Usuario;

@Stateless
public class TiendaON {

	@Inject
	private UsuarioDAO usu;
	
	@Inject
	private PeliculaDAO pel;

	public void crearUsu(Usuario u) {
		usu.insertar(u);
	}

	public List<Usuario> listadoUsuarios() {
		return usu.listadoUsuarios();
	}
	
	public boolean incioSesion(String correo, String pass) {
		return usu.logueado(correo, pass);
	}
	
	public List<Producto> listadoProductos() {
		return pel.ListadoProductos();
	}
	
	public Usuario logueadoUsuario(String correo, String pass) {
		return usu.logueadoUsuario(correo, pass);
	}

	
}
