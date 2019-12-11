package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Usuario;

@Stateless
public class UsuarioDAO {

	@Inject
	private EntityManager em;

	public void insertar(Usuario u) {
		em.persist(u);
	}

	public void actualizar(Usuario u) {
		em.merge(u);
	}

	public void borrar(String cedula) {
		em.remove(buscar(cedula));
	}

	public Usuario buscar(String cedula) {
		Usuario u;
		u = em.find(Usuario.class, cedula);
		return u;
	}

	public List<Usuario> listadoUsuarios() {
		String jpql = "SELECT u FROM Usuario u";
		Query q = em.createQuery(jpql, Usuario.class);
		List<Usuario> usuarios = q.getResultList();
		return usuarios;
	}

	public List<Usuario> listadoUsuarioBuscado(String cedula) {
		Usuario ub = buscar(cedula);
		List<Usuario> usuariosB = new ArrayList<Usuario>();
		usuariosB.add(ub);
		return usuariosB;
	}

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
}