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

import dao.CategoriaDAO;
import dao.PeliculaDAO;
import modelo.Categoria;
import modelo.Pelicula;
<<<<<<< HEAD
import modelo.Producto;
=======
>>>>>>> branch 'lucy' of https://github.com/acastill0/appdis-Tienda.git
import on.TiendaON;

@Path("/peliculas")
public class PeliculasService {

	@Inject
	private PeliculaDAO g;
	
	@Inject
<<<<<<< HEAD
	private TiendaON t;
=======
	private TiendaON tiendaON;
	
	
>>>>>>> branch 'lucy' of https://github.com/acastill0/appdis-Tienda.git

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
	
	@GET
	@Path("getPeliculas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pelicula> getCategorias() {
		return g.ListadoPeliculas();
	}
	
	@GET
<<<<<<< HEAD
	@Path("getProductos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producto> getProductos() {
		return t.listadoProductos();
	}
=======
	@Path("/agregarCarrito")
	@Produces("application/json")
	@Consumes("application/json")
	public String agregarCarrito(@QueryParam("cedula")String cedula, @QueryParam("intP")int idP, @QueryParam("cantidad")int cantidad) {
		try {
			 tiendaON.agregarCarrito(cedula, idP, cantidad);
		} catch (Exception e) {
			e.getMessage();
		}	
		return "Carrito agregado";
		
	}
	
	@GET
	@Path("/finalizarCompra")
	@Produces("application/json")
	@Consumes("application/json")
	public String  finalizarCompra(@QueryParam("cedula")String cedula) {
		try {
			 tiendaON.finalizarCompra(cedula);
		} catch (Exception e) {
			e.getMessage();
		}
		return "Compra finalizada";
	}
	
	
	
>>>>>>> branch 'lucy' of https://github.com/acastill0/appdis-Tienda.git

}
