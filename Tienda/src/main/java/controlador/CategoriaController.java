package controlador;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import dao.CategoriaDAO;
import modelo.Categoria;
import on.TiendaON;
/*
 * Clase que se crea para el mantenimiento de Categoría en la WEB
 * @author: Lucy Garay, Adriana Castillo
 * */
@ManagedBean
@SessionScoped

public class CategoriaController {

	@Inject
	private TiendaON tiendaON;

	private Categoria c;
	private List<Categoria> categorias;
	private Map<String, Object> categoriasProducto;

	@PostConstruct
	public void init() {
		c = new Categoria();
		categorias = tiendaON.listadoCategorias();
	}

	public Categoria getC() {
		return c;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void setC(Categoria c) {
		this.c = c;
	}

	public Map<String, Object> getCategoriasProducto() {
		return categoriasProducto;
	}

	public void setCategoriasProducto(Map<String, Object> categoriasProducto) {
		this.categoriasProducto = categoriasProducto;
	}

	public String guardar() {
		tiendaON.insertarCate(c);
		limpiar();
		listado();
		return "cat-buscar.xhtml";
	}

	public String buscar() {
		Categoria cb = tiendaON.buscarCate(c.getId());
		listado();
		c = cb;
		return null;
	}

	public String actualizar() {
		tiendaON.actualizarCate(c);
		listado();
		limpiar();
		return "cat-buscar.xhtml";
	}

	public String borrar(int id) {
		tiendaON.borrarCate(id);
		listado();
		return null;
	}

	public String listado() {
		categorias = tiendaON.listadoCategorias();
		return null;
	}

	public String listadoBuscado() {
		categorias = tiendaON.listadoCategoriaBuscado(c.getId());
		return null;
	}

	public String limpiar() {
		c.setId(0);
		c.setNombre("");
		return null;
	}

	public void loadCategoria() {
		c = new Categoria();
		c.getId();
		categorias = tiendaON.listadoCategorias();

	}

	public Map<String, Object> listaCategoriaPelicula() {
		categoriasProducto = new LinkedHashMap<String, Object>();
		loadCategoria();
		for (Categoria c : categorias) {
			categoriasProducto.put(c.getNombre(), c.getId());
		}
		return categoriasProducto;
	}
	
}
