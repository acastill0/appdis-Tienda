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
public class Direccion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String  direcciones;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(String direcciones) {
		this.direcciones = direcciones;
	}
	
	@Override
	public String toString() {
		return "Direccion [id=" + id + ", direcciones=" + direcciones + "]";
	}


}
