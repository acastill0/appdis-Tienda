package servicios;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import dao.UsuarioDAO;

import modelo.Usuario;

@Path("/usuarios")
public class UsuarioService {
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@GET
	@Path("getUsuarios")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuario() {
		return usuarioDAO.listadoUsuarios();
	}
	
	@POST
	@Path("setUsuarios")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta createUsuario(Usuario usuario) {
		Respuesta r = new Respuesta();
		try {
			usuarioDAO.insertar(usuario);
			r.setCodigo(0);
			r.setMensajes("OK");
		} catch (Exception e) {
			r.setCodigo(99);
			r.setMensajes("Error Insertar");
		}
		return r;
	}

}
