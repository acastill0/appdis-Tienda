package on;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import dao.CarritoDAO;
import dao.CategoriaDAO;
import dao.DetalleDAO;
import dao.PeliculaDAO;
import dao.UsuarioDAO;
import modelo.Carrito;
import modelo.Categoria;
import modelo.Compra;
import modelo.Detalle;
import modelo.Direccion;
import modelo.Pelicula;
import modelo.Producto;
import modelo.Tarjeta;
import modelo.Usuario;
import modelo.Voto;

/*
 * Clase para el mantenimiento de la Tienda
 * @author: Lucy Garay, Adriana Castillo
 * */
@Stateless
public class TiendaON {

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private CategoriaDAO categoriaDAO;

	@Inject
	private PeliculaDAO peliculaDAO;

	@Inject
	private CarritoDAO carritoDAO;

	@Inject
	private DetalleDAO detalleDAO;

	/* USUARIO */

	/**
	 * Método que crea un Usuario
	 */

	public void crearUsu(Usuario u) {
		usuarioDAO.insertar(u);
	}

	/**
	 * Método que busca al Usuario
	 * 
	 * @return El usuario buscado
	 */
	public Usuario buscarUsu(String cedula) {
		return usuarioDAO.buscar(cedula);
	}

	/**
	 * Método que actualiza al Usuario
	 */
	public void actualizarUsu(Usuario u) {
		usuarioDAO.actualizar(u);
	}

	/**
	 * Método que borra al Usuario
	 */
	public void borrarUsu(String cedula) {
		usuarioDAO.borrar(cedula);
	}

	/**
	 * Método que actualiza al Carrito
	 */
	public void actualizarCarr(Carrito c) {
		carritoDAO.actualizar(c);
	}

	/**
	 * Método que lista los Usuarios
	 */
	public List<Usuario> listadoUsuarios() {
		return usuarioDAO.listadoUsuarios();
	}

	/**
	 * Método que lista los usuarios buscadoss
	 * 
	 * @return Lista de usuarios buscados
	 */
	public List<Usuario> listadoUsuarioBuscado(String cedula) {
		Usuario ub = buscarUsu(cedula);
		List<Usuario> usuariosB = new ArrayList<Usuario>();
		usuariosB.add(ub);
		return usuariosB;
	}

	/* CATEGORIA */

	/**
	 * Método que crea una Categoría
	 */
	public void insertarCate(Categoria c) {
		categoriaDAO.insertar(c);
	}

	/**
	 * Método que actualiza una Categoría
	 */
	public void actualizarCate(Categoria c) {
		categoriaDAO.actualizar(c);
	}

	/**
	 * Método que borra una Categoría
	 */
	public void borrarCate(int id) {
		categoriaDAO.borrar(id);
	}

	/**
	 * Método que busca una Categoría
	 * 
	 * @return La Categoría buscada
	 */
	public Categoria buscarCate(int id) {
		return categoriaDAO.buscar(id);
	}

	/**
	 * Método que devuelve la lista de las Categorias
	 * 
	 * @return Las Categorías existentes
	 */
	public List<Categoria> listadoCategorias() {
		return categoriaDAO.listadoCategorias();
	}

	/**
	 * Método que devuelve la lista de las Categorias buscadas
	 * 
	 * @return Las Categorías buscadas
	 */
	public List<Categoria> listadoCategoriaBuscado(int id) {
		Categoria cb = buscarCate(id);
		List<Categoria> categoriaB = new ArrayList<Categoria>();
		categoriaB.add(cb);
		return categoriaB;
	}

	/* PELICULA */

	/**
	 * Método que crea una Pelicula
	 */
	public void insertarPeli(Pelicula p) {
		peliculaDAO.insertar(p);
	}

	/**
	 * Método que actualiza una Pelicula
	 */
	public void actualizarPeli(Pelicula p) {
		peliculaDAO.actualizar(p);
	}

	/**
	 * Método que borra una Pelicula
	 */
	public void borrarPeli(int id) {
		peliculaDAO.borrar(id);
	}

	/**
	 * Método que busca una Película
	 * 
	 * @return La Película buscada
	 */
	public Pelicula buscarPeli(int id) {
		return peliculaDAO.buscar(id);
	}

	/**
	 * Método retorna una lista de Películas
	 * 
	 * @return Las películas existentes
	 */
	public List<Pelicula> ListadoPeliculas() {
		return peliculaDAO.ListadoPeliculas();
	}

	/**
	 * Método retorna una lista de Películas buscadas
	 * 
	 * @return Las películas buscadas
	 */
	public List<Pelicula> listadoPeliculaBuscado(int id) {
		Pelicula pb = buscarPeli(id);
		List<Pelicula> peliculaB = new ArrayList<Pelicula>();
		peliculaB.add(pb);
		return peliculaB;
	}

	/* PRODUCTO */

	public Producto buscarPelicula(int id) {
		Producto pro = new Producto();
		Pelicula pel = peliculaDAO.buscar(id);
		if (pel != null) {
			pro.setId(pel.getId());
			pro.setTitulo(pel.getTitulo());
			pro.setImagen(pel.getImagen());
			pro.setCategoria(pel.getCategoria().getNombre());
			pro.setPrecio(pel.getPrecio());
			pro.setVotacion(pel.getVotacion());
			return pro;
		}
		return null;
	}

	/**
	 * Método que lista las Películas para el consumo del cliente movil
	 */
	public List<Producto> listadoProductos() {
		return peliculaDAO.ListadoProductos();
	}

	/* INICIO SESION */

	/**
	 * Método que verifica si inicia sesión o no el Admin
	 */
	public boolean incioSesion(String correo, String pass) {
		return usuarioDAO.logueado(correo, pass);
	}

	/**
	 * Método que verifica si inicia sesión o no el Cliente
	 */
	public boolean incioSesionCliente(String correo, String pass) {
		return usuarioDAO.logueadoCliente(correo, pass);
	}

	/**
	 * Método que verifica si inicia sesión o no el Cliente
	 * 
	 * @return El Cliente logueado
	 */
	public Usuario logueadoUsuario(String correo, String pass) {
		Usuario usu = usuarioDAO.logueadoUsuario(correo, pass);
		Usuario usuario = new Usuario();

		usuario.setCedula(usu.getCedula());
		usuario.setNombre(usu.getNombre());
		usuario.setApellido(usu.getApellido());
		usuario.setCorreo(usu.getCorreo());
		usuario.setPassword(usu.getPassword());
		usuario.setTelefono(usu.getTelefono());
		usuario.setAdmin(usu.isAdmin());
		usuario.setCliente(usu.isCliente());
		usuario.setCarritos(new ArrayList<Carrito>());

		return usuario;
	}

	/* REPORTES */

	/**
	 * Método que lista las 10 películas más vendidas
	 * 
	 * @return El top de las listas
	 */
	public List<Pelicula> listaPeliculasMasVendidas() {
		List<Pelicula> top = new ArrayList<>();
		int cont = 0;
		for (Pelicula pelicula : peliculaDAO.listaPeliculasVendidasTop()) {
			if (cont < 10) {
				top.add(pelicula);
				cont++;
			}
		}
		return top;
	}

	/**
	 * Método que lista las 10 películas más votadas
	 * 
	 * @return El top de las listas
	 */
	public List<Pelicula> listaPeliculasMasVotadas() {
		List<Pelicula> top = new ArrayList<>();
		int cont = 0;
		for (Pelicula pelicula : peliculaDAO.listaPeliculasVotadasTop()) {
			if (cont < 10) {
				top.add(pelicula);
				cont++;
			}
		}
		return top;
	}

	/**
	 * Método que lista los 10 usuarios que más compran
	 * 
	 * @return El top de los usuarios
	 */
	public List<Usuario> listaUsuariosMasCompras() {
		List<Usuario> top = new ArrayList<>();
		int cont = 0;
		for (Usuario user : usuarioDAO.listaUsuariosComprasTop()) {
			if (cont < 10) {
				top.add(user);
				cont++;
			}
		}
		return top;
	}

	/**
	 * Método que agregar peliculas al carrito del cliente
	 */
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

	/*
	 * public void agregarCarrito(String cedula, int idP, int cantidad) throws
	 * Exception { Usuario usuario = usuarioDAO.buscar(cedula); Carrito carrito2 =
	 * null;
	 * 
	 * if (usuario.getCarritos() == null) { usuario.setCarritos(new
	 * ArrayList<Carrito>()); } for (Carrito carrito : usuario.getCarritos()) { if
	 * (!carrito.isEstado()) { carrito2 = carrito; break; } } if (carrito2 == null)
	 * { carrito2 = new Carrito(); carrito2.setFecha((new Date()).toString());
	 * carrito2.setDetalles(new ArrayList<Detalle>());
	 * usuario.getCarritos().add(carrito2); }
	 * 
	 * Detalle detalle = new Detalle();
	 * 
	 * Pelicula pelicula = peliculaDAO.buscar(idP); if (pelicula != null) {
	 * 
	 * detalle.setPelicula(pelicula); detalle.setCantidad(cantidad);
	 * detalle.setPrecio(pelicula.getPrecio() * cantidad);
	 * carrito2.getDetalles().add(detalle); usuarioDAO.actualizar(usuario); } else {
	 * new Exception("Producto no existe"); }
	 * 
	 * }
	 */

	/*
	 * public boolean finalizarCompra(String cedula) throws Exception { Usuario
	 * usuario = usuarioDAO.buscar(cedula); Carrito carrito2 = null; if
	 * (usuario.getCarritos() == null) { return false; // usuario.setCarritos(new
	 * ArrayList<Carrito>()); } for (Carrito carrito : usuario.getCarritos()) { if
	 * (!carrito.isEstado()) { carrito2 = carrito; break; } } if (carrito2 == null)
	 * { return false; } double suma = 0.0; for (Detalle detalle :
	 * carrito2.getDetalles()) { suma = suma + detalle.getPrecio();
	 * detalle.getPelicula().setVotacion(detalle.getPelicula().getVotacion() +
	 * detalle.getCantidad()); if (detalle.getPelicula().getCantidad() >
	 * detalle.getCantidad()) {
	 * detalle.getPelicula().setCantidad(detalle.getPelicula().getCantidad() -
	 * detalle.getCantidad()); } else { new Exception("fuera de stock"); }
	 * 
	 * } carrito2.setTotal(suma); carrito2.setEstado(true);
	 * 
	 * usuarioDAO.actualizar(usuario); return true; }
	 */

	/**
	 * Método que finaliza una compra de un cliente
	 */

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
			detalle.getPelicula().setVendidos(detalle.getPelicula().getVendidos() + detalle.getCantidad());
			if (detalle.getPelicula().getCantidad() > detalle.getCantidad()) {
				detalle.getPelicula().setCantidad(detalle.getPelicula().getCantidad() - detalle.getCantidad());
			} else {
				new Exception("fuera de stock");
			}

		}
		carrito2.setTotal(suma);
		carrito2.setEstado(true);
		usuario.setCompras(usuario.getCompras() + 1);
		usuarioDAO.actualizar(usuario);
		return true;
	}

	/*
	 * public boolean finalizarCompra(String cedula) throws Exception { Usuario
	 * usuario = usuarioDAO.buscar(cedula); Carrito carrito2 = null; if
	 * (usuario.getCarritos() == null) { return false; // usuario.setCarritos(new
	 * ArrayList<Carrito>()); } for (Carrito carrito : usuario.getCarritos()) { if
	 * (!carrito.isEstado()) { carrito2 = carrito; break; } } if (carrito2 == null)
	 * { return false; } if (carrito2.getDetalles().isEmpty()) { return false; }
	 * double suma = 0.0; try {
	 * 
	 * 
	 * for (Detalle detalle : carrito2.getDetalles()) {
	 * System.out.println("DEtalles "+carrito2.getDetalles()); suma = suma +
	 * detalle.getPrecio();
	 * detalle.getPelicula().setVendidos(detalle.getPelicula().getVendidos() +
	 * detalle.getCantidad());
	 * 
	 * if (detalle.getPelicula().getCantidad() > detalle.getCantidad()) {
	 * System.out.println("Valida Cantidad");
	 * detalle.getPelicula().setCantidad(detalle.getPelicula().getCantidad() -
	 * detalle.getCantidad()); System.out.println("1"); carrito2.setTotal(suma);
	 * System.out.println("2");
	 * 
	 * carrito2.setEstado(true); System.out.println("3");
	 * 
	 * usuario.setCompras(usuario.getCompras() + 1); System.out.println("4");
	 * 
	 * usuarioDAO.actualizar(usuario); System.out.println("5");
	 * 
	 * 
	 * } else { new Exception("fuera de stock"); System.out.println("fuer"); } }
	 * 
	 * 
	 * return true; }
	 */

	/**
	 * Método que lista las compras de un Cliente
	 * 
	 * @return La lista de compras realizadas
	 */
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
					compra.setDetalles(new ArrayList<Detalle>());
					resultado.add(compra);

				}
			}
		}
		return resultado;
	}

	/**
	 * Método que lista los items del carrito de un Cliente
	 * 
	 * @return La lista de items
	 */
	public List<Producto> listaCarrito(String cedula) {
		List<Producto> resultado = new ArrayList<>();
		Usuario usuario = usuarioDAO.buscar(cedula);
		if (usuario.getCarritos() == null) {
			usuario.setCarritos(new ArrayList<Carrito>());
		}
		for (Carrito carrito : usuario.getCarritos()) {
			if (!carrito.isEstado()) {
				for (Detalle det : carrito.getDetalles()) {
					Producto p = new Producto();
					p.setCantidadTotal(det.getCantidad());
					p.setPrecioTotal(det.getPrecio());
					p.setCantidad(det.getPelicula().getCantidad());
					p.setId(det.getId());
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

	/**
	 * Método que elimina peliculas del carrito del cliente
	 */
	public String eliminarDelCarrito(int id) {
		try {
			System.out.println("ID a Eliminar " + id);
			System.out.println("--> " + detalleDAO.borrar(id));

			return "Eliminado";
		} catch (Exception e) {
			return e.getStackTrace().toString();
		}
	}

	/**
	 * Método que convierte la fecha
	 */
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

	/**
	 * Método que lista los Usuarios
	 */
	public List<Detalle> listadoDetalles() {
		return detalleDAO.ListadoDetalles();
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
	//

	public boolean ControlVoto(String cedula, int idP, boolean estado) {
		System.out.println("entro1");
		boolean crear = true;
		Usuario usuario = usuarioDAO.buscar(cedula);
		Pelicula pelicula = peliculaDAO.buscar(idP);
		System.out.println("------> peli " + pelicula);
		System.out.println("------> user " + usuario);
		System.out.println(usuario.getVotos());
		if (usuario.getVotos().isEmpty()) {
			System.out.println("no hay votos");
			Voto voto3 = new Voto();
			voto3.setEstado(true);
			voto3.setIdP(idP);
			usuario.getVotos().add(voto3);
			pelicula.setVotacion(pelicula.getVotacion() + 1);
			peliculaDAO.actualizar(pelicula);
			usuarioDAO.actualizar(usuario);
		} else {
			for (Voto voto : usuario.getVotos()) {
				if (voto.getIdP() == idP) {
					if (voto.isEstado()) {
						System.out.println("1");
						voto.setEstado(false);
						pelicula.setVotacion(pelicula.getVotacion() - 1);
						peliculaDAO.actualizar(pelicula);
						return true;
					} else {
						System.out.println("2");
						voto.setEstado(true);
						pelicula.setVotacion(pelicula.getVotacion() + 1);
						peliculaDAO.actualizar(pelicula);
						return true;
					}
				}
			}
			System.out.println("agregar voto");
			Voto voto3 = new Voto();
			voto3.setEstado(true);
			voto3.setIdP(idP);
			usuario.getVotos().add(voto3);
			pelicula.setVotacion(pelicula.getVotacion() + 1);
			peliculaDAO.actualizar(pelicula);
			usuarioDAO.actualizar(usuario);
			return true;
		}

		return false;
	}

	public boolean estadoProductoUsuario(String cedula, int idP) {
		System.out.println("entro1");
		Usuario usuario = usuarioDAO.buscar(cedula);
		for (Voto voto : usuario.getVotos()) {
			if (voto.getIdP() == idP) {
				System.out.println("REtorna voto bol");
				return voto.isEstado();
			}
		}
		return false;
	}

	public List<Producto> listarDetalleCompras(int idC) {
		List<Producto> resultado = new ArrayList<>();
		Carrito carrito = carritoDAO.buscar(idC);
		System.out.println(carrito.getDetalles().size());
		for (Detalle det : carrito.getDetalles()) {
			Producto p = new Producto();
			p.setPrecioTotal(det.getPrecio());
			p.setCantidadTotal(det.getCantidad());
			Pelicula pelicula = peliculaDAO.buscar(det.getPelicula().getId());
			System.out.println("aaaa" + peliculaDAO.buscar(det.getPelicula().getId()));
			p.setTitulo(det.getPelicula().getTitulo());
			p.setImagen(det.getPelicula().getImagen());
			resultado.add(p);
		}
		return resultado;
	}
}
