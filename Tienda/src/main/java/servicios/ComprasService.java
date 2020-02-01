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
import modelo.Direccion;
import modelo.Producto;
import modelo.Tarjeta;
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
	
	@GET
	@Path("/eliminarCarrito")
	@Produces("application/json")
	@Consumes("application/json")
	public String eliminarCarrito(@QueryParam("cedula")String cedula, @QueryParam("id")int id) {
		try {
			 tiendaON.eliminarCarrito(cedula, id);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return "Carrito eliminada";
	}
	
	@POST
	@Path("eliminarTarjeta")
	@Produces("application/json")
	@Consumes("application/json")
	  public Respuesta eliminarTarjeta(Tarjeta detalle) {
	    Respuesta r = new Respuesta();
	    try {
	      System.out.println("Detalle "+detalle.getId());
	      tiendaON.eliminarTarjeta(detalle.getId());
	      r.setCodigo(0);
	      r.setMensajes("OK");
	    }catch(Exception e) {
	      r.setCodigo(99);
	      r.setMensajes("Error al eliminar tarjeta");
	    }
	    return r;
	  }
	
	@POST
	@Path("eliminarDireccion")
	@Produces("application/json")
	@Consumes("application/json")
	  public Respuesta eliminarDireccion(Direccion detalle) {
	    Respuesta r = new Respuesta();
	    try {
	      System.out.println("Detalle "+detalle.getId());
	      tiendaON.eliminarDireccion(detalle.getId());
	      r.setCodigo(0);
	      r.setMensajes("OK");
	    }catch(Exception e) {
	      r.setCodigo(99);
	      r.setMensajes("Error al eliminar tarjeta");
	    }
	    return r;
	  }
	
}
