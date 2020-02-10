/*package controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import modelo.Usuario;
import on.TiendaON;
import utilidades.SessionUtils;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {


	private static final long serialVersionUID = 1L;

	@Inject
	private TiendaON tiendaON;

	private String email = "ac@gmail.com";
	private String clave = "1";
	private Usuario usuario;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}


	public String login() {
		usuario = tiendaON.logueadoAdmin(email,clave);
		if (usuario != null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", usuario);
			return "main";
		} else {
			return null;
		}
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "index.xhtml";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}*/