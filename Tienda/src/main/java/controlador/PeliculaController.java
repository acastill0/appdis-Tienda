package controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import dao.PeliculaDAO;
import modelo.Categoria;
import modelo.Pelicula;

@ManagedBean
@SessionScoped
public class PeliculaController {

	@Inject
	private PeliculaDAO g;

	private Pelicula p;
	private List<Pelicula> peliculas;

	@PostConstruct
	public void init() {
		p = new Pelicula();
		p.setCategoria(new Categoria());
		peliculas = g.ListadoPeliculas();
	}

	public Pelicula getP() {
		return p;
	}

	public void setP(Pelicula p) {
		this.p = p;
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public String guardar() {
		g.insertar(p);
		listado();
		limpiar();
		return null;
	}

	public String buscar() {
		Pelicula pb = g.buscar(p.getId());
		listado();
		p = pb;
		return null;
	}

	public String actualizar() {
		g.actualizar(p);
		listado();
		limpiar();
		return null;
	}

	public String borrar(int id) {
		g.borrar(id);
		listado();
		return null;
	}

	public String listado() {
		peliculas = g.ListadoPeliculas();
		return null;
	}

	public String listadoBuscado() {
		peliculas = g.listadoPeliculaBuscado(p.getId());
		return null;
	}

	public String limpiar() {
		p.setId(0);
		p.setTitulo("");
		p.setImagen("");
		p.setVotacion(0);
		p.setPrecio(0);
		p.setCantidad(0);
		return null;
	}
}
