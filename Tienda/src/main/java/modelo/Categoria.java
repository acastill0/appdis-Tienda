package modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Categoria {
	@Id
	private int id;
	@NotEmpty
	private String nombre;

	@OneToMany(cascade= {CascadeType.ALL})
	@JoinColumn(name="pelicula",referencedColumnName = "id")
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

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", peliculas=" + peliculas + "]";
	}

}
