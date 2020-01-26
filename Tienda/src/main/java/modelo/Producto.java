package modelo;

public class Producto {

	private int id;
	private String titulo;
	private String imagen;
	private int votacion;
	private double precio;
	private int cantidad;
	
	private double precioTotal;
	private int cantidadTotal;

	private String categoria;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getVotacion() {
		return votacion;
	}

	public void setVotacion(int votacion) {
		this.votacion = votacion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public int getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(int cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", titulo=" + titulo + ", imagen=" + imagen + ", votacion=" + votacion
				+ ", precio=" + precio + ", cantidad=" + cantidad + ", categoria=" + categoria + "]";
	}

	/*@Override
	public String toString() {
		return "Producto [id=" + id + ", titulo=" + titulo + ", imagen=" + imagen + ", votacion=" + votacion
				+ ", precio=" + precio + ", cantidad=" + cantidad + ", categoria=" + categoria + "]";
	}*/
	
	

}
