package controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import dao.UsuarioDAO;

@ManagedBean
@SessionScoped
public class InicioSesionController {
	@Inject
	private UsuarioDAO ud;

	private String correo;
	private String pass;

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

	public String login() {

		Boolean aux = ud.logueado(correo, pass);
		if (aux) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("user", correo);
			limpiar();
			return "inicio";
		} else {
			FacesMessage fmss = new FacesMessage("Error", "Error de inicio de sesión");
			FacesContext.getCurrentInstance().addMessage(null, fmss);
			limpiar();
			return null;
		}
	}

	public void limpiar() {
		correo = "";
		pass = "";
	}

}
