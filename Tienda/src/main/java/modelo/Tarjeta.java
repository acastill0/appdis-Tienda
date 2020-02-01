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

@Entity
public class Tarjeta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String  numero;
	private String titular;
	private String fechaVencimiento;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	@Override
	public String toString() {
		return "Tarjeta [id=" + id + ", numero=" + numero + ", titular=" + titular + ", fechaVencimiento="
				+ fechaVencimiento + "]";
	}
	
	



	
	


}
