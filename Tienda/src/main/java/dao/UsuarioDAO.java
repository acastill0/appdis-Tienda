package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Usuario;

/*
 * Clase Crud para Usuario
 * @author: Lucy Garay, Adriana Castillo
 * */

@Stateless
public class UsuarioDAO {

	@Inject
	private EntityManager em;
	/**
     * Método que crea al Usuario
     */
	public void insertar(Usuario u) {
		em.persist(u);
	}
	/**
     * Método que actualiza al Usuario
     */
	public void actualizar(Usuario u) {
		em.merge(u);
	}
	/**
     * Método que borra al Usuario
     */
	public void borrar(String cedula) {
		em.remove(buscar(cedula));
	}
	/**
     * Método que busca al Usuario
     * @return El usuario buscado
     */
	public Usuario buscar(String cedula) {
		Usuario u;
		u = em.find(Usuario.class, cedula);
		return u;
	}
	/**
     * Método lista los usuarios existentes
     * @return Lista de usuarios
     */
	public List<Usuario> listadoUsuarios() {
		String jpql = "SELECT u FROM Usuario u";
		Query q = em.createQuery(jpql, Usuario.class);
		List<Usuario> usuarios = q.getResultList();
		return usuarios;
	}
	
	/**
     * Método lista las direcciones de los usuarios
     * @return Lista de direcciones
     */
	public List<Usuario> ListadoDirecciones() {
		String jpql = "SELECT d FROM Direccion d";
		Query q = em.createQuery(jpql, Usuario.class);
		List<Usuario> direcciones = q.getResultList();
		return direcciones;
	}
	/**
     * Método lista los carritos
     * @return Lista de carritos
     */
	public List<Usuario> ListadoCarritos() {
		String jpql = "SELECT c FROM Carrito c";
		Query q = em.createQuery(jpql, Usuario.class);
		List<Usuario> carritos = q.getResultList();
		return carritos;
	}
	/**
     * Método lista las tarjetas de los usuarios
     * @return Lista de tarjetas
     */
	public List<Usuario> ListadoTarjetas() {
		String jpql = "SELECT t FROM Tarjeta t";
		Query q = em.createQuery(jpql, Usuario.class);
		List<Usuario> tarjetas = q.getResultList();
		return tarjetas;
	}
	/**
     * Método lista los votos de los usuarios
     * @return Lista de votos
     */
	public List<Usuario> ListadoVotos() {
		String jpql = "SELECT v FROM Voto v";
		Query q = em.createQuery(jpql, Usuario.class);
		List<Usuario> votos = q.getResultList();
		return votos;
	}
	/**
     * Método verifica si un correo y contraseña proporcionados son correctos
     * @return Si se ha logueado o no el administrador
     */
	public boolean logueado(String correo, String pass) {
		String jpql = "SELECT u FROM Usuario u WHERE u.correo = :c AND u.password = :p";
		Query q = em.createQuery(jpql, Usuario.class);
		q.setParameter("c", correo);
		q.setParameter("p", pass);
		try {
			Usuario u = (Usuario) q.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
     * Método verifica si un correo y contraseña proporcionados son correctos
     * @return Si se ha logueado o no el cliente
     */
	public Usuario logueadoAdmin(String correo, String pass) {
		String jpql = "SELECT u FROM Usuario u WHERE u.correo = :c AND u.password = :p AND u.admin = :t";
		Query q = em.createQuery(jpql, Usuario.class);
		q.setParameter("c", correo);
		q.setParameter("p", pass);
		q.setParameter("t", true);
		try {
			Usuario u = (Usuario) q.getSingleResult();
			return u;
		} catch (Exception e) {
			return null;
		}
	}
	/**
     * Método verifica si un correo y contraseña proporcionados son correctos
     * @return Si se ha logueado o no el cliente
     */
	public boolean logueadoCliente(String correo, String pass) {
		String jpql = "SELECT u FROM Usuario u WHERE u.correo = :c AND u.password = :p AND u.cliente = :t";
		Query q = em.createQuery(jpql, Usuario.class);
		q.setParameter("c", correo);
		q.setParameter("p", pass);
		q.setParameter("t", true);
		try {
			Usuario u = (Usuario) q.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
     * Método verifica si un correo y contraseña proporcionados son correctos
     * @return Cliente logueado
     */
	
	public Usuario logueadoUsuario(String correo, String pass) {
		String jpql = "SELECT u FROM Usuario u WHERE u.correo = :c AND u.password = :p AND u.cliente = :t";
		Query q = em.createQuery(jpql, Usuario.class);
		q.setParameter("c", correo);
		q.setParameter("p", pass);
		q.setParameter("t", true);
		try {
			Usuario u = (Usuario) q.getSingleResult();
			return u;
		} catch (Exception e) {
			return null;
		}
	}
	/**
     * Método retorna una lista de Usuarios que más compran
     *  @return Los usuarios con más compras
     */
	public List<Usuario> listaUsuariosComprasTop() {
		String jpql = "SELECT u FROM Usuario u WHERE u.cliente= true ORDER BY u.compras DESC";
		Query query = em.createQuery(jpql, Usuario.class);
		return query.getResultList();
	}
}