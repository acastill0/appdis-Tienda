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
	// private UsuarioDAO usuarioDAO;

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
	public Respuesta createUsuario(@QueryParam("cedula") String cedula, @QueryParam("nombre") String nombre,
			@QueryParam("apellidos") String apellidos, @QueryParam("telefono") String telefono,
			@QueryParam("correo") String correo, @QueryParam("password") String password) {
		Respuesta r = new Respuesta();
		try {
			Usuario u = new Usuario();
			u.setCedula(cedula);
			u.setNombre(nombre);
			u.setApellido(apellidos);
			u.setTelefono(telefono);
			u.setCorreo(correo);
			u.setPassword(password);
			u.setAdmin(false);
			u.setCliente(true);
			on.crearUsu(u);

			r.setCodigo(0);
			r.setMensajes("OK");
		} catch (Exception e) {
			r.setCodigo(99);
			r.setMensajes("Error Insertar");
		}
		return r;
	}

	@GET
	@Path("loguinUsu")
	@Produces("application/json")
	@Consumes("application/json")
	public boolean loguinUsuario(@QueryParam("correo") String correo, @QueryParam("password") String password) {
		boolean res = false;
		try {
			res = on.incioSesionCliente(correo, password);
		} catch (Exception e) {
			res = false;
		}
		return res;
	}
	
	@GET
	@Path("buscarCliente")
	@Produces("application/json")
	@Consumes("application/json")
	public Usuario buscarCliente(@QueryParam("correo") String correo,@QueryParam("pass") String pass) {
		return on.logueadoUsuario(correo, pass);
	}
	

}
