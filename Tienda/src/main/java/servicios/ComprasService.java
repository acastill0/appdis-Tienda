package servicios;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import modelo.Compra;
import modelo.Detalle;
import modelo.Producto;
import modelo.Usuario;
import on.TiendaON;
/*
 * Clase de servicios para la Compra
 * @author: Lucy Garay, Adriana Castillo
 * */
@Path("/compras")
public class ComprasService {

	@Inject
	private TiendaON tiendaON;
	/**
     * Método que agrega productos al carrito
     */
	@POST
	@Path("/agregarAlCarrito")
	@Produces("application/json")
	@Consumes("application/json")
	public String addCarrito(@QueryParam("cedula") String cedula,Detalle det) {
		try {
			tiendaON.agregarCarrito(cedula, det.getPelicula().getId(), det.getCantidad());
		} catch (Exception e) {
			e.getMessage();
		}
		return "Carrito agregado";
	}
	
	/**
     * Método que finaliza la compra de un cliente
     */
	@POST
	@Path("/finCompra")
	@Produces("application/json")
	@Consumes("application/json")
	public String finCompra(Usuario usuario) {
		try {
			tiendaON.finalizarCompra(usuario.getCedula());
		} catch (Exception e) {
			e.getMessage();
		}
		return "Compra finalizada";
	}
	/**
     * Método que lista la compras de un cliente
     */
	@GET
	@Path("getCompras")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Compra> getCompras(@QueryParam("cedula") String cedula) {
		try {
			return tiendaON.listaCompras(cedula);
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}
	/**
     * Método que obtiene los carritos de los clientes
     */
	@GET
	@Path("getCarrito")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producto> getCarrito(@QueryParam("cedula") String cedula) {
		try {
			return tiendaON.listaCarrito(cedula);
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}
	/**
     * Método que elimina un producto del carrito de un cliente
     */
	@POST
	@Path("deleteDelCarrito")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta deleteDelCar(Detalle detalle) {
		Respuesta r = new Respuesta();
		try {
			System.out.println("Detalle "+detalle.getId());
			tiendaON.eliminarDelCarrito(detalle.getId());
			r.setCodigo(0);
			r.setMensajes("OK");
		}catch(Exception e) {
			r.setCodigo(99);
			r.setMensajes("Error al eliminar");
		}
		return r;
	}
	
	@GET
	@Path("ListaCompraDetalle")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producto> listarDetalleCompras(@QueryParam("idC")int idC) {
		return tiendaON.listarDetalleCompras(idC);
	}
}
