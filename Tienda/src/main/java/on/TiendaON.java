package on;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.PeliculaDAO;
import dao.UsuarioDAO;
import modelo.Carrito;
import modelo.Detalle;
import modelo.Pelicula;
import modelo.Usuario;

@Stateless
public class TiendaON {

	@Inject
	private UsuarioDAO usu;

	@Inject
	private PeliculaDAO peliculaDAO;

	public void crearUsu(Usuario u) {
		usu.insertar(u);
	}

	public List<Usuario> listadoUsuarios() {
		return usu.listadoUsuarios();
	}

	public List<Pelicula> listaProductosVendidos() {
		return peliculaDAO.listaPeliculasPopulares();
	}

	public void agregarCarrito(String cedula, int idP, int cantidad) throws Exception {
		Usuario usuario = usu.buscar(cedula);
		Carrito carrito2 = null;

		if (usuario.getCarritos() == null) {
			usuario.setCarritos(new ArrayList<Carrito>());
		}
		for (Carrito carrito : usuario.getCarritos()) {
			if (!carrito.isEstado()) {
				carrito2 = carrito;
				break;
			}
		}
		if (carrito2 == null) {
			carrito2 = new Carrito();
			carrito2.setFecha((new Date()).toString());
			carrito2.setDetalles(new ArrayList<Detalle>());
			usuario.getCarritos().add(carrito2);
		}
		
		Detalle detalle = new Detalle();
	
		Pelicula pelicula = peliculaDAO.buscar(idP);
		if (pelicula!=null) {
			
		
		detalle.setPelicula(pelicula);
		detalle.setCantidad(cantidad);
		detalle.setPrecio(pelicula.getPrecio() * cantidad);
		carrito2.getDetalles().add(detalle);
		usu.actualizar(usuario);
		}else {
			new Exception("Producto no existe");
		}
		
	}

	public boolean finalizarCompra(String cedula)throws Exception {
		Usuario usuario = usu.buscar(cedula);
		Carrito carrito2 = null;
		if (usuario.getCarritos() == null) {
			return false;
			// usuario.setCarritos(new ArrayList<Carrito>());
		}
		for (Carrito carrito : usuario.getCarritos()) {
			if (!carrito.isEstado()) {
				carrito2 = carrito;
				break;
			}
		}
		if (carrito2 == null) {
			return false;
		}
		double suma = 0.0;
		for (Detalle detalle : carrito2.getDetalles()){
			suma = suma + detalle.getPrecio();
			detalle.getPelicula().setVotacion(detalle.getPelicula().getVotacion() + detalle.getCantidad());
			if (detalle.getPelicula().getCantidad()>detalle.getCantidad()) {
				detalle.getPelicula().setCantidad(detalle.getPelicula().getCantidad()-detalle.getCantidad());
			}else {
				new Exception("fuera de stock");
			}
			
		}
		carrito2.setTotal(suma);
		carrito2.setEstado(true);

		usu.actualizar(usuario);
		return true;
	}

}
