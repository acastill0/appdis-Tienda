package controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import dao.PeliculaDAO;
import modelo.Categoria;
import modelo.Pelicula;
import on.TiendaON;
/*
 * Clase que se crea para el mantenimiento de Pelicula en la WEB
 * @author: Lucy Garay, Adriana Castillo
 * */
@ManagedBean
@SessionScoped
public class PeliculaController {

	@Inject
	private PeliculaDAO g;

	@Inject
	private TiendaON tiendaOn;

	private Pelicula p;
	private List<Pelicula> peliculas;
	private List<Pelicula> peliculasTopVentas;

	@PostConstruct
	public void init() {
		p = new Pelicula();
		p.setCategoria(new Categoria());
		peliculas = g.ListadoPeliculas();
		peliculasTopVentas = tiendaOn.listaPeliculasMasVendidas();
	}
	/*
	 * Getter y setters de los atributos
	 */
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

	public List<Pelicula> getPeliculasTopVentas() {
		return peliculasTopVentas;
	}

	public void setPeliculasTopVentas(List<Pelicula> peliculasTopVentas) {
		this.peliculasTopVentas = peliculasTopVentas;
	}
	/*
	 * Metodo guardar pelicula
	 */
	public String guardar() {
		g.insertar(p);
		listado();
		limpiar();
		return null;
	}
	/*
	 * Metodo buscar pelicula
	 */
	public String buscar() {
		Pelicula pb = g.buscar(p.getId());
		listado();
		p = pb;
		return null;
	}
	/*
	 * Metodo actualizar pelicula
	 */
	public String actualizar() {
		g.actualizar(p);
		listado();
		limpiar();
		return null;
	}
	/*
	 * Metodo eliminar pelicula
	 */
	public String borrar(int id) {
		g.borrar(id);
		listado();
		return null;
	}
	/*
	 * Metodo listar peliculas
	 */
	public String listado() {
		peliculas = g.ListadoPeliculas();
		return null;
	}
	/*
	 * Metodo listar peliculas más vendidas
	 */
	public String peliculasMasVendidas() {
		peliculasTopVentas = tiendaOn.listaPeliculasMasVendidas();
		return null;
	}
	/*
	 * Metodo listar peliculas buscadas
	 */
	public String listadoBuscado() {
		peliculas = g.listadoPeliculaBuscado(p.getId());
		return null;
	}
	/*
	 * Metodo limpiar campos
	 */
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
//
