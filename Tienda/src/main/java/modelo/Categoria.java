package modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
/*
 * Clase que se generara en la base de dato par la tabla Categoría
 * @author: Lucy Garay, Adriana Castillo
 * */
@Entity
public class Categoria{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nombre;

	@OneToMany(cascade = { CascadeType.ALL },fetch=FetchType.LAZY)
	@JoinColumn(name = "categoria")
	private List<Pelicula> peliculas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + "]";
	}

}
