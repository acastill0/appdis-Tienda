package on;

import java.util.List;

import javax.ejb.Stateless;

import dao.UsuarioDAO;
import modelo.Usuario;

/*
 * Clase mantenimiento de la WEB de la tienda
 * @author: Lucy Garay, Adriana Castillo
 * */

@Stateless
public class GestionTiendaON {

	UsuarioDAO ud = new UsuarioDAO();
	/**
     * Método que crea un usuario
     */
	public void crearUsu(Usuario u) {
		ud.insertar(u);
	}
	/**
     * Método que busca un usuario
     */
	public Usuario buscarUsu(String cedula) {
		return ud.buscar(cedula);
	}
	/**
     * Método que actualiza un usuario
     */
	public boolean actulizarUsu(Usuario u) {
		boolean aux = false;
		Usuario ub = buscarUsu(u.getCedula());
		if (ub.getCedula() != (null)) {
			ud.actualizar(u);
			aux = true;
		}
		return aux;
	}
	/**
     * Método que elimina un usuario
     */
	public boolean eliminarUsu(String cedula) {
		boolean aux = false;

		Usuario u = buscarUsu(cedula);
		if (u.getCedula() != (null)) {
			ud.borrar(cedula);
			aux = true;
		}
		return aux;
	}
	/**
     * Método que crea lista todos los usuarios
     * @return Lista de usuarios
     */
	public List<Usuario> listadoUsuarios() {
		return ud.listadoUsuarios();
	}
	/**
     * Método que crea lista todos los usuarios buscados
     * @return Lista de usuarios buscados
     */
	public List<Usuario> listadoUsuBuscado(String cedula) {
		return ud.listadoUsuarioBuscado(cedula);
	}

}
