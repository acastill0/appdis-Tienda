package modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Persona {

	@Id
//	@Column(name="per_cedula")
	// @Pattern(regexp="[^0-9]*",message="Ingrese solo números.")
	private String cedula;
	@NotEmpty
	@Size(min = 4, max = 40)
	private String nombre;
	@NotEmpty
	private String cargo;
	@NotEmpty
	private String direccion;
	@Email
	private String correo;
	@NotEmpty
	private String password;
	@NotEmpty
	@Size(min = 8, max = 15)
	private String telefono;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Persona [cedula=" + cedula + ", nombre=" + nombre + ", cargo=" + cargo + ", direccion=" + direccion
				+ ", correo=" + correo + ", contraseña=" + password + ", telefono=" + telefono + "]";
	}
}
