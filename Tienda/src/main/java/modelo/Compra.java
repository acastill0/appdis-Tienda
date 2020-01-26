package modelo;

import java.util.List;

public class Compra {

	private int id;
	private String fecha;
	private double total;
	private boolean estado;
	
	private List<Producto> detalles;

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

	public List<Producto> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Producto> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", fecha=" + fecha + ", total=" + total + ", estado=" + estado + "]";
	}

}
