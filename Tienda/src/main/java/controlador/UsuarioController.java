package controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.UsuarioDAO;
import modelo.Usuario;
import on.GestionTiendaON;

@ManagedBean
public class UsuarioController {

	@Inject
	//private GestionTiendaON g;
	 private UsuarioDAO g;

	private Usuario u;
	private List<Usuario> usuarios;

	@PostConstruct
	public void init() {
		u = new Usuario();
		usuarios = g.listadoUsuarios();
	}

	public Usuario getU() {
		return u;
	}

	public void setU(Usuario u) {
		this.u = u;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String guardar() {
		System.out.println(u.toString());
		u.setAdmin(true);
		g.insertar(u);
		//g.crearUsu(u);
		listado();
		limpiar();
		return null;
	}

	public String buscar() {
		Usuario ub = g.buscar(u.getCedula());
		//Usuario ub = g.buscarUsu(u.getCedula());
		listado();
		u = ub;
		return null;
	}

	public String actualizar() {

		g.actualizar(u);
		//g.actulizarUsu(u);
		listado();
		limpiar();
		return null;
	}

	public String borrar(String cedula) {
		g.borrar(cedula);
		System.out.println(cedula);
		//g.eliminarUsu(cedula);
		listado();
		return null;
	}

	public String listado() {
		usuarios = g.listadoUsuarios();
		return null;
	}

	public String listadoBuscado() {
		usuarios = g.listadoUsuarioBuscado(u.getCedula());
		//usuarios = g.listadoUsuBuscado(u.getCedula());
		if (u.getCedula() == null) {
			System.out.println("vacio");
			listado();
		}
		return null;
	}

	public String limpiar() {
		u.setCedula("");
		u.setNombre("");
		u.setApellido("");
		u.setTelefono("");
		u.setCorreo("");
		u.setPassword("");
		return null;
	}
}