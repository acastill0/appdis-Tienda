package controlador;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.PeliculaDAO;
import modelo.Pelicula;

@ManagedBean
public class PeliculaController {
	private Pelicula p;
	@Inject
	private PeliculaDAO pdao;

	@PostConstruct
	public void inir() {
		p = new Pelicula();
	}

	public Pelicula getP() {
		return p;
	}

	public void setP(Pelicula p) {
		this.p = p;
	}

	public String guardar() {
		System.out.println(p.toString());
		pdao.insertar(p);
		return null;
	}
}
