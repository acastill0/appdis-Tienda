package on;

import java.util.List;

import dao.CategoriaDAO;
import dao.PeliculaDAO;
import dao.UsuarioDAO;
import modelo.Usuario;

public class GestionTiendaON {

	UsuarioDAO ud = new UsuarioDAO();
	CategoriaDAO cd = new CategoriaDAO();
	PeliculaDAO pd = new PeliculaDAO();

	public void crearUsu(Usuario u) {
		ud.insertar(u);
	}

	public Usuario buscarUsu(String cedula) {
		return ud.buscar(cedula);
	}

	public boolean actulizarUsu(Usuario u) {
		boolean aux = false;
		Usuario ub = buscarUsu(u.getCedula());
		if (ub.getCedula() != (null)) {
			ud.actualizar(u);
			aux = true;
		}
		return aux;
	}

	public boolean eliminarUsu(String cedula) {
		boolean aux = false;
		
		Usuario u = buscarUsu(cedula);
		if (u.getCedula() != (null)) {
			ud.borrar(cedula);
			aux = true;
		}
		return aux;
	}

	public List<Usuario> listadoUsuarios() {
		return ud.listadoUsuarios();
	}
	
	public List<Usuario> listadoUsuBuscado(String cedula) {
		return ud.listadoUsuarioBuscado(cedula);
	}
}
