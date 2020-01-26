package servicios;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import modelo.Compra;
import modelo.Producto;
import on.TiendaON;

@Path("/compras")
public class ComprasService {

	@Inject
	private TiendaON tiendaON;

	@GET
	@Path("/agregarCarrito")
	@Produces("application/json")
	@Consumes("application/json")
	public String agregarCarrito(@QueryParam("cedula") String cedula, @QueryParam("intP") int idP,
			@QueryParam("cantidad") int cantidad) {
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
	public String finalizarCompra(@QueryParam("cedula") String cedula) {
		try {
			tiendaON.finalizarCompra(cedula);
		} catch (Exception e) {
			e.getMessage();
		}
		return "Compra finalizada";
	}
	
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
	
}
