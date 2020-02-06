package on;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import dao.CarritoDAO;
import dao.DetalleDAO;
import dao.DireccionDAO;
import dao.PeliculaDAO;
import dao.TarjetaDAO;
import dao.UsuarioDAO;
import modelo.Carrito;
import modelo.Compra;
import modelo.Detalle;
import modelo.Direccion;
import modelo.Pelicula;
import modelo.Producto;
import modelo.Tarjeta;
import modelo.Usuario;

@Stateless
public class TiendaON {

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private PeliculaDAO peliculaDAO;

	@Inject
	private CarritoDAO carritoDAO;

	@Inject
	private DetalleDAO detalleDAO;

	@Inject
	private TarjetaDAO tarjetaDAO;

	@Inject
	private DireccionDAO direccionDAO;

	public void crearUsu(Usuario u) {
		usuarioDAO.insertar(u);
	}

	public List<Usuario> listadoUsuarios() {
		return usuarioDAO.listadoUsuarios();
	}

	public boolean incioSesion(String correo, String pass) {
		return usuarioDAO.logueado(correo, pass);
	}

	public boolean incioSesionCliente(String correo, String pass) {
		return usuarioDAO.logueadoCliente(correo, pass);
	}

	public List<Producto> listadoProductos() {
		return peliculaDAO.ListadoProductos();
	}

	public Usuario logueadoUsuario(String correo, String pass) {
		return usuarioDAO.logueadoUsuario(correo, pass);
	}

	public List<Pelicula> listaProductosVotados() {
		return peliculaDAO.listaPeliculasPopulares();
	}

	public List<Pelicula> listaProductosVendidos() {
		return peliculaDAO.listaPeliculasVendidas();
	}

	public void agregarCarrito(String cedula, int idP, int cantidad) throws Exception {
		boolean crear = true;
		Usuario usuario = usuarioDAO.buscar(cedula);
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
		if (pelicula != null) {
			if (pelicula.getCantidad() > cantidad) {
				for (Detalle detalle2 : carrito2.getDetalles()) {
					System.out.println(detalle2.getPelicula().getId() + "   ----" + idP);
					if (detalle2.getPelicula().getId() == idP) {
						System.out.println("Igual " + detalle2);
						// detalleDAO.buscar(detalle2.getId());
						Detalle detalle3 = new Detalle();
						detalle3.setId(detalle2.getId());
						detalle3.setCantidad(cantidad + detalle2.getCantidad());
						detalle3.setPrecio(pelicula.getPrecio() * (cantidad + detalle2.getCantidad()));
						detalle3.setPelicula(pelicula);
						detalleDAO.actualizar(detalle3);
						crear = false;
					}
				}
				if (crear) {
					detalle.setPelicula(pelicula);
					detalle.setCantidad(cantidad);
					detalle.setPrecio(pelicula.getPrecio() * cantidad);
					carrito2.getDetalles().add(detalle);
					usuarioDAO.actualizar(usuario);
				}
			} else {
				new Exception("fuera de stock");
				System.out.println("fuer");
			}
		} else {
			new Exception("Producto no existe");
		}
	}

	public boolean finalizarCompra(String cedula) throws Exception {
		Usuario usuario = usuarioDAO.buscar(cedula);
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
		if (carrito2.getDetalles().isEmpty()) {
			return false;
		}
		double suma = 0.0;
	
		for (Detalle detalle : carrito2.getDetalles()) {
			suma = suma + detalle.getPrecio();
			System.out.println("Stockkkkasfsd");
			System.out.println("stock" + detalle.getPelicula().getCantidad());
			System.out.println("stock2" + detalle.getCantidad());
			detalle.getPelicula().setVendidos(detalle.getPelicula().getVendidos() + detalle.getCantidad());
			if (detalle.getPelicula().getCantidad() > detalle.getCantidad()) {
				detalle.getPelicula().setCantidad(detalle.getPelicula().getCantidad() - detalle.getCantidad());
				System.out.println("-> Stockkkk");
				//
				carrito2.setTotal(suma);
				carrito2.setEstado(true);
				usuario.setCompras(usuario.getCompras() + 1);
				usuarioDAO.actualizar(usuario);
				System.out.println(usuario.getCompras());
			} else {
				new Exception("fuera de stock");
				System.out.println("fuer");
			}
		}

		return true;
	}

	public List<Compra> listaCompras(String cedula) {
		List<Compra> resultado = new ArrayList<>();
		Usuario u = usuarioDAO.buscar(cedula);
		int id = 0;
		List<Carrito> carritosFin = carritoDAO.comprasCliente();
		if (carritosFin == null) {
			return null;
		}
		for (Carrito carritoC : u.getCarritos()) {
			System.out.println("Carrito U " + u.getCarritos());
			for (Carrito carritoF : carritosFin) {
				if (carritoC.getId() == carritoF.getId() && id != carritoF.getId()) {
					id = carritoF.getId();
					Compra compra = new Compra();
					compra.setId(carritoF.getId());
					compra.setEstado(carritoF.isEstado());
					compra.setFecha(obtenerFecha(carritoF.getFecha()));
					compra.setTotal(carritoF.getTotal());
					resultado.add(compra);
				}
			}
		}
		return resultado;
	}

	public List<Producto> listaCarrito(String cedula) {
		List<Producto> resultado = new ArrayList<>();
		Usuario usuario = usuarioDAO.buscar(cedula);
		if (usuario.getCarritos() == null) {
			usuario.setCarritos(new ArrayList<Carrito>());
		}
		for (Carrito carrito : usuario.getCarritos()) {
			if (!carrito.isEstado()) {
				System.out.println("----> ID :" + carrito.getId());
				System.out.println(" ........ DE: " + carrito.getDetalles());
				for (Detalle det : carrito.getDetalles()) {
					Producto p = new Producto();
					p.setCantidadTotal(det.getCantidad());
					p.setPrecioTotal(det.getPrecio());
					p.setCantidad(det.getPelicula().getCantidad());
					p.setId(det.getPelicula().getId());
					p.setTitulo(det.getPelicula().getTitulo());
					p.setImagen(det.getPelicula().getImagen());
					p.setVotacion(det.getPelicula().getVotacion());
					p.setPrecio(det.getPelicula().getPrecio());
					p.setCantidad(det.getPelicula().getCantidad());
					resultado.add(p);
				}
				break;
			}
		}

		return resultado;
	}

	public void eliminarCarrito(String cedula, int id) {
		List<Detalle> resultado = new ArrayList<>();
		Usuario usuario = usuarioDAO.buscar(cedula);
		if (usuario.getCarritos() == null) {
			usuario.setCarritos(new ArrayList<Carrito>());
		}
		for (Carrito carrito : usuario.getCarritos()) {
			if (!carrito.isEstado()) {
				System.out.println("----> ID :" + carrito.getId());
				System.out.println(" ........ DE: " + carrito.getDetalles());
				for (Detalle det : carrito.getDetalles()) {
					if (det.getPelicula().getId() != id) {
						resultado.add(det);
						System.out.println(det + "det");

					}
				}
				carrito.setDetalles(resultado);
				System.out.println(resultado);

				usuarioDAO.actualizar(usuario);
				break;
			}
		}
	}

	public String obtenerFecha(String date) {
		String fecha = "";
		String dia = date.substring(8, 10);
		String mes = date.substring(4, 7);
		String anio = date.substring(24, 28);

		if (mes.equals("Jan")) {
			mes = "1";
		} else if (mes.equals("Feb")) {
			mes = "2";
		} else if (mes.equals("Mar")) {
			mes = "3";
		} else if (mes.equals("Apr")) {
			mes = "4";
		} else if (mes.equals("May")) {
			mes = "5";
		} else if (mes.equals("Jun")) {
			mes = "6";
		} else if (mes.equals("Jul")) {
			mes = "7";
		} else if (mes.equals("Aug")) {
			mes = "8";
		} else if (mes.equals("Sept")) {
			mes = "9";
		} else if (mes.equals("Oct")) {
			mes = "10";
		} else if (mes.equals("Nov")) {
			mes = "11";
		} else if (mes.equals("Dec")) {
			mes = "12";
		}
		fecha = dia + "/" + mes + "/" + anio;
		return fecha;
	}

	public void liked(String cedula) {
		List<Detalle> resultado = new ArrayList<>();
		Usuario usuario = usuarioDAO.buscar(cedula);
	}

	public void agregarTarjetasUsuario(String cedula, String fechaVencimiento, String numero, String titutar) {
		Usuario usuario = usuarioDAO.buscar(cedula);
		System.out.println(usuario);
		Tarjeta detalle = new Tarjeta();
		if (usuario != null) {
			detalle.setFechaVencimiento(fechaVencimiento);
			detalle.setNumero(numero);
			detalle.setTitular(titutar);
			usuario.getTarjetas().add(detalle);
			usuarioDAO.actualizar(usuario);
		} else {
			new Exception("Usuario no existe");
		}
	}

	public void agregarDireccionesUsuario(String cedula, String direccion) {
		Usuario usuario = usuarioDAO.buscar(cedula);
		System.out.println(usuario);
		Direccion detalle = new Direccion();
		if (usuario != null) {
			detalle.setDirecciones(direccion);
			usuario.getDirecciones().add(detalle);
			usuarioDAO.actualizar(usuario);
		} else {
			new Exception("Usuario no existe");
		}
	}

	public List<Tarjeta> listaTarjetasUsuario(String cedula) {
		Usuario usuario = usuarioDAO.buscar(cedula);
		if (usuario == null) {
			return new ArrayList<Tarjeta>();
		}
		return usuario.getTarjetas();
	}

	public List<Direccion> listaDireccionesUsuario(String cedula) {
		Usuario usuario = usuarioDAO.buscar(cedula);
		if (usuario == null) {
			return new ArrayList<Direccion>();
		}
		return usuario.getDirecciones();
	}

	public String eliminarTarjeta(int id) {
		try {
			System.out.println("ID a Eliminar " + id);
			System.out.println("--> " + tarjetaDAO.borrar(id));

			return "Tarjeta eliminada";
		} catch (Exception e) {
			return e.getStackTrace().toString();
		}
	}

	public String eliminarDireccion(int id) {
		try {
			System.out.println("ID a Eliminar " + id);
			System.out.println("--> " + direccionDAO.borrar(id));

			return "Direccion eliminada";
		} catch (Exception e) {
			return e.getStackTrace().toString();
		}
	}

	public List<Pelicula> buscarPeliculas(String titulo) {
		return peliculaDAO.buscarPelicula(titulo);
	}
	
	public List<Direccion>listarDireccionesUsuario(String cedula){
		List<Direccion> direcciones= new ArrayList<Direccion>();
		Usuario usu=usuarioDAO.buscar(cedula);
		direcciones=usu.getDirecciones();
		return direcciones;
	}
	
	public List<Tarjeta>listarTarjetasUsuario(String cedula){
		List<Tarjeta> tarjeta= new ArrayList<Tarjeta>();
		Usuario u=usuarioDAO.buscar(cedula);
		tarjeta=u.getTarjetas();
		return tarjeta;
	}
	
	

}
