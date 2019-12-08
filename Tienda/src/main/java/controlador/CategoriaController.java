package controlador;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.CategoriaDAO;
import modelo.Categoria;

@ManagedBean
public class CategoriaController {
	
	private Categoria c;
	
	@Inject
	private CategoriaDAO cdao;
	
	@PostConstruct
	public void init() {
		c = new Categoria();
	}

	public Categoria getC() {
		return c;
	}

	public void setC(Categoria c) {
		this.c = c;
	}

	public String guardar() {
		System.out.println(c.toString());
		cdao.insertar(c);
		return null;
	}
}
