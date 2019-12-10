package controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.CategoriaDAO;
import modelo.Categoria;

@ManagedBean
public class CategoriaController {

	@Inject
	private CategoriaDAO g;

	private Categoria c;
	private List<Categoria> categorias;

	@PostConstruct
	public void init() {
		c = new Categoria();
		categorias = g.listadoCategorias();
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

	public String guardar() {
		g.insertar(c);
		limpiar();
		listado();
		return null;
	}

	public String buscar() {
		Categoria cb = g.buscar(c.getId());
		listado();
		c = cb;
		return null;
	}

	public String actualizar() {
		g.actualizar(c);
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
		categorias = g.listadoCategorias();
		return null;
	}

	public String listadoBuscado() {
		categorias = g.listadoCategoriaBuscado(c.getId());
		return null;
	}

	public String limpiar() {
		c.setId(0);
		c.setNombre("");
		return null;
	}
}
