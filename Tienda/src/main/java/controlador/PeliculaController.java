package controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

import modelo.Categoria;
import modelo.Pelicula;
import on.TiendaON;

/*
 * Clase que se crea para el mantenimiento de Pelicula en la WEB
 * @author: Lucy Garay, Adriana Castillo
 * */
@ManagedBean
//@SessionScoped
public class PeliculaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * @Inject private PeliculaDAO g;
	 */

	@Inject
	private TiendaON tiendaOn;

	private Pelicula p;
	private List<Pelicula> peliculas;
	private List<Pelicula> peliculasTopVentas;
	private List<Pelicula> peliculasTopVotos;
	private PieChartModel modelVentas;
	private PieChartModel modelVotos;

	@PostConstruct
	public void init() {
		p = new Pelicula();
		p.setCategoria(new Categoria());
		peliculas = tiendaOn.ListadoPeliculas();
		peliculasTopVentas = tiendaOn.listaPeliculasMasVendidas();
		peliculasTopVotos = tiendaOn.listaPeliculasMasVotadas();

		graficoVentas();
		graficoVotos();
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

	public List<Pelicula> getPeliculasTopVotos() {
		return peliculasTopVotos;
	}

	public void setPeliculasTopVotos(List<Pelicula> peliculasTopVotos) {
		this.peliculasTopVotos = peliculasTopVotos;
	}

	public PieChartModel getModelVentas() {
		return modelVentas;
	}

	public void setModelVentas(PieChartModel modelVentas) {
		this.modelVentas = modelVentas;
	}

	public PieChartModel getModelVotos() {
		return modelVotos;
	}

	public void setModelVotos(PieChartModel modelVotos) {
		this.modelVotos = modelVotos;
	}

	public void graficoVotos() {
		modelVotos = new PieChartModel();

		for (Pelicula pelicula : peliculasTopVotos) {
			modelVotos.set(pelicula.getTitulo(), pelicula.getVotacion());
		}
		modelVotos.setTitle("Top 10 Películas Más Votadas");
		modelVotos.setLegendPosition("w");
		modelVotos.setShadow(false);

	}

	public void graficoVentas() {
		modelVentas = new PieChartModel();

		for (Pelicula pelicula : peliculasTopVentas) {
			modelVentas.set(pelicula.getTitulo(), pelicula.getVendidos());

		}
		modelVentas.setTitle("Top 10 Películas Más Vendidas");
		modelVentas.setLegendPosition("w");
		modelVentas.setShadow(false);
	}

	/*
	 * Metodo guardar pelicula
	 */
	public String guardar() {
		tiendaOn.insertarPeli(p);
		listado();
		limpiar();
		return "pel-buscar.xhtml";
	}

	/*
	 * Metodo buscar pelicula
	 */
	public String buscar() {
		Pelicula pb = tiendaOn.buscarPeli(p.getId());
		listado();
		p = pb;
		return null;
	}

	/*
	 * Metodo actualizar pelicula
	 */
	public String actualizar() {
		tiendaOn.actualizarPeli(p);
		listado();
		limpiar();
		return "pel-buscar.xhtml";
	}

	/*
	 * Metodo eliminar pelicula
	 */
	public String borrar(int id) {
		tiendaOn.borrarPeli(id);
		listado();
		return null;
	}

	/*
	 * Metodo listar peliculas
	 */
	public String listado() {
		peliculas = tiendaOn.ListadoPeliculas();
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
	 * Metodo listar peliculas más votadas
	 */
	public String peliculasMasVotadas() {
		peliculasTopVotos = tiendaOn.listaPeliculasMasVotadas();
		return null;
	}

	/*
	 * Metodo listar peliculas buscadas
	 */
	public String listadoBuscado() {
		peliculas = tiendaOn.listadoPeliculaBuscado(p.getId());
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

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información",
				"Posición en el top: " + (event.getItemIndex() + 1));

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
//
