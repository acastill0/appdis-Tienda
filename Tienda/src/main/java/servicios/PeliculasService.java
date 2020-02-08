package servicios;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.primefaces.*;

import modelo.Producto;
import on.TiendaON;

/*
 * Clase de servicios para la Pelicula
 * @author: Lucy Garay, Adriana Castillo
 * */
@Path("/peliculas")
public class PeliculasService {

	@Inject
	private TiendaON tiendaON;

	/**
	 * Método que obtiene todas las peliculas
	 */
	@GET
	@Path("getProductos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producto> getProductos() {
		try {
			return tiendaON.listadoProductos();
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}

	@GET
	@Path("getPelicula")
	@Produces(MediaType.APPLICATION_JSON)
	public Producto getPelicula(@QueryParam("id") int id) {
		try {
			return tiendaON.buscarPelicula(id);
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}
}
