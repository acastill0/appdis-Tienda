package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity

public class Categoria {



	@Id

	private int idC;
	

	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String nombre;


//	@OneToMany(cascade= {CascadeType.ALL})
//	@JoinColumn(name="pelicula",referencedColumnName = "id")
//	private List<Pelicula> peliculas;
	@OneToMany(cascade = { CascadeType.ALL },fetch=FetchType.EAGER)
	@JoinColumn(name = "categoria")
	private List<Pelicula> peliculas;

	public int getId() {
		return idC;
	}

	public void setId(int id) {
		this.idC = id;
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
		return "Categoria [id=" + idC + ", nombre=" + nombre + ", peliculas="  + "]";
	}

}
