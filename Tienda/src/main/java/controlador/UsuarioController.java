package controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.chart.PieChartModel;

import modelo.Usuario;
import on.TiendaON;

/*
 * Clase que se crea para el mantenimiento de Usuario en la WEB
 * @author: Lucy Garay, Adriana Castillo
 * */
@ManagedBean
@ViewScoped

public class UsuarioController {

	@Inject
	private TiendaON tiendaOn;

	private Usuario u;
	private List<Usuario> usuarios;
	private List<Usuario> usuariosCompras;
	private PieChartModel modelCompras;

	@PostConstruct
	public void init() {
		u = new Usuario();
		usuarios = tiendaOn.listadoUsuarios();
		usuariosCompras = tiendaOn.listaUsuariosMasCompras();
		graficoCompras();
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

	public List<Usuario> getUsuariosCompras() {
		return usuariosCompras;
	}

	public void setUsuariosCompras(List<Usuario> usuariosCompras) {
		this.usuariosCompras = usuariosCompras;
	}

	public PieChartModel getModelCompras() {
		return modelCompras;
	}

	public void setModelCompras(PieChartModel modelCompras) {
		this.modelCompras = modelCompras;
	}

	public void graficoCompras() {
		modelCompras = new PieChartModel();

		for (Usuario user : usuariosCompras) {
			modelCompras.set(user.getNombre()+" "+user.getApellido(), user.getCompras());
		}
		modelCompras.setTitle("Top Clientes Que Más Compran");
		modelCompras.setLegendPosition("w");
		modelCompras.setShadow(false);

	}

	public String guardar() {
		System.out.println(u.toString());
		u.setAdmin(true);
		tiendaOn.crearUsu(u);
		listado();
		limpiar();
		return null;
	}

	public String buscar() {
		Usuario ub = tiendaOn.buscarUsu(u.getCedula());
		listado();
		u = ub;
		return null;
	}

	public String actualizar() {
		tiendaOn.actualizarUsu(u);
		listado();
		limpiar();
		return null;
	}
	
	public String borrar(String cedula) {
		tiendaOn.borrarUsu(cedula);
		System.out.println(cedula);
		listado();
		return null;
	}

	public String listado() {
		usuarios = tiendaOn.listadoUsuarios();
		return null;
	}

	public String compras() {
		usuariosCompras = tiendaOn.listaUsuariosMasCompras();
		return null;
	}

	public String listadoBuscado() {
		usuarios = tiendaOn.listadoUsuarioBuscado(u.getCedula());
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