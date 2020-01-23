package servicios;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import dao.UsuarioDAO;
import modelo.Usuario;
import on.TiendaON;
@Path("/usuarios")
public class UsuarioService {
	@Inject
	private TiendaON on;
	//private UsuarioDAO usuarioDAO;
	
	@GET
	@Path("getUsuarios")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuario() {
		return on.listadoUsuarios();
	}
	
	@GET
	@Path("setUsuarios")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta createUsuario(@QueryParam("cedula") String cedula, @QueryParam("nombre") String nombre, @QueryParam("apellidos") String apellidos, @QueryParam("telefono") String telefono, @QueryParam("correo") String correo, @QueryParam("admin") boolean admin, @QueryParam("cliente") boolean cliente) {
		Respuesta r = new Respuesta();
		try {
			Usuario u=new Usuario();
			u.setCedula(cedula);
			u.setNombre(nombre);
			u.setApellido(apellidos);
			u.setTelefono(telefono);
			u.setCorreo(correo);
			u.setAdmin(admin);
			u.setCliente(cliente);
			on.crearUsu(u);

			r.setCodigo(0);
			r.setMensajes("OK");
		} catch (Exception e) {
			r.setCodigo(99);
			r.setMensajes("Error Insertar");
		}
		return r;
	}

}
