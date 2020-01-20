package servicios;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.CategoriaDAO;
import dao.PeliculaDAO;
import modelo.Categoria;
import modelo.Pelicula;

@Path("/peliculas")
public class PeliculasService {

	@Inject
	private PeliculaDAO g;

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

}
