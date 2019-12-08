package controlador;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.PersonaDAO;
import modelo.Persona;

@ManagedBean
public class PersonaController {
	private Persona p;

	@Inject
	private PersonaDAO pdao;

	@PostConstruct
	public void inir() {
		p = new Persona();
	}

	public Persona getP() {
		return p;
	}

	public void setP(Persona p) {
		this.p = p;
	}

	public String guardar() {
		System.out.println(p.toString());
		pdao.insertar(p);
		return null;
	}
}