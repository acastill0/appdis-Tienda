package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Voto  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private boolean  estado;
	private int idP;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getIdP() {
		return idP;
	}

	public void setIdP(int idP) {
		this.idP = idP;
	}

	@Override
	public String toString() {
		return "Voto [id=" + id + ", estado=" + estado + ", idP=" + idP + "]";
	}

}