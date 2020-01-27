package modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/*
 * Clase que se generara en la base de dato par la tabla detalle
 * @author: Lucy Garay, Adriana Castillo
 * */
@Entity
@Table(name = "detalle")
public class Detalle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int cantidad;
	private double precio;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pelicula_id", referencedColumnName = "id")
	private Pelicula pelicula;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	@Override
	public String toString() {
		return "Detalle [id=" + id + ", cantidad=" + cantidad + ", precio=" + precio + "]\n";
	}
	
	

}
