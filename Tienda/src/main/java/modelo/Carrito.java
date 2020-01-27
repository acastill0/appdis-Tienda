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
 * Clase que se generara en la base de dato par la tabla Carrito
 * @author: Lucy Garay, Adriana Castillo
 * */
@Entity
public class Carrito {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String fecha;
	private double total;
	private boolean estado;
	//EAGER
	@OneToMany(cascade = { CascadeType.ALL },fetch=FetchType.EAGER)
	@JoinColumn(name = "carrito")
	private List<Detalle> detalles;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "Carrito [id=" + id + ", fecha=" + fecha + ", total=" + total + ", estado=" + estado + "]";
	}

}
