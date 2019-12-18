package servicios;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import dao.CategoriaDAO;
import dao.PeliculaDAO;
import modelo.Categoria;
import modelo.Pelicula;

@Path("/categorias")
public class CategoriasService {
	@Inject
	private CategoriaDAO g;

	@GET
	@Path("getCategorias")
	@Produces("application/json")
	public List<Categoria> getCategorias() {
		return g.listadoCategorias();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta createCategoria(Categoria c) {
		Respuesta r = new Respuesta();
		try {
			g.insertar(c);
			r.setCodigo(0);
			r.setMensajes("OK");
		} catch (Exception e) {
			r.setCodigo(99);
			r.setMensajes("Error Insertar");
		}
		return r;
	}

}
