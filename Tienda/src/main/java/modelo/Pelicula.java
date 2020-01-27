/*package modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "pelicula")
public class Pelicula implements Serializable {

	/**
	 * 
	 */
/*
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String titulo;
	private String imagen;
	private int votacion;
	private double precio;
	private int cantidad;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria")
	private Categoria categoria;

	/*@OneToOne(mappedBy = "pelicula")
	private Detalle detalle;*/

	/*
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", imagen=" + imagen + ", votacion=" + votacion
				+ ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}

}*/

package modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
/*
 * Clase que se generara en la base de dato par la tabla Pelicula
 * @author: Lucy Garay, Adriana Castillo
 * */
@Entity
@Table(name = "pelicula")
public class Pelicula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String titulo;
	private String imagen;
	private int votacion;
	private double precio;
	private int cantidad;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria")
	private Categoria categoria;

	/*@OneToOne(mappedBy = "pelicula")
	private Detalle detalle;*/

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", imagen=" + imagen + ", votacion=" + votacion
				+ ", precio=" + precio + ", cantidad=" + cantidad + ", categoria=" + categoria + "]";
	}

}