package controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import modelo.Usuario;
import on.TiendaON;

/*
 * Clase que se crea para el mantenimiento del inicio de sesión en la WEB
 * @author: Lucy Garay, Adriana Castillo
 * */
@ManagedBean
@ViewScoped

public class InicioSesionController implements Serializable {
	@Inject
	private TiendaON tiendaON;

	private String correo;
	private String pass;

	private Usuario usuario;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String login() {

		Usuario usuAd = tiendaON.logueadoAdmin(correo, pass);
		if (usuAd != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("user", correo);
			limpiar();
			return "main";
		} else {
			FacesMessage fmss = new FacesMessage("Error", "Error de inicio de sesión");
			FacesContext.getCurrentInstance().addMessage(null, fmss);
			limpiar();
			return null;
		}
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
		return "login";
	}

	public void limpiar() {
		correo = "";
		pass = "";
	}

}
