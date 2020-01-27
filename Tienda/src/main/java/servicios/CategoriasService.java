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
import modelo.Categoria;
/*
 * Clase de servicios para la Categoría
 * @author: Lucy Garay, Adriana Castillo
 * */
@Path("/categorias")
public class CategoriasService {
	
	@Inject
	private CategoriaDAO g;
	/**
     * Método que devuelve la lista de categorias
     * @return Lista de categorias
     */
	@GET
	@Path("getCategorias")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Categoria> getCategorias() {
		return g.listadoCategorias();
	}
	/**
     * Método que crea categorias
     * @return una respuesta de si se realiza o no
     */
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
