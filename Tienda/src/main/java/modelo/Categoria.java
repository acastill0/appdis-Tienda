package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Categoria {

	@Id
	private int idC;
	
	private String nombre;

//	@OneToMany(cascade= {CascadeType.ALL})
//	@JoinColumn(name="pelicula",referencedColumnName = "id")
//	private List<Pelicula> peliculas;

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

	@Override
	public String toString() {
		return "Categoria [id=" + idC + ", nombre=" + nombre + ", peliculas="  + "]";
	}

}
