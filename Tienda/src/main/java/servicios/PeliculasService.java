package servicios;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.PeliculaDAO;
import modelo.Pelicula;
import modelo.Producto;
import on.TiendaON;
/*
 * Clase de servicios para la Pelicula
 * @author: Lucy Garay, Adriana Castillo
 * */
@Path("/peliculas")
public class PeliculasService {

	@Inject
	private PeliculaDAO g;

	@Inject
	private TiendaON tiendaON;
	/**
     * Método que acre Peliculas
     */
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta createPelicula(Pelicula p) {
		Respuesta r = new Respuesta();
		try {
			g.insertar(p);
			r.setCodigo(0);
			r.setMensajes("OK");
		} catch (Exception e) {
			r.setCodigo(99);
			r.setMensajes("Error Insertar");
		}
		return r;
	}
	/**
     * Método que obtiene todas las peliculas
     */
	@GET
	@Path("getProductos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producto> getProductos() {
		try {
			return g.ListadoProductos();
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}
}
