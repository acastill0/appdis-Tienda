package on;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.CategoriaDAO;
import dao.PeliculaDAO;
import dao.UsuarioDAO;
import modelo.Categoria;
import modelo.Pelicula;
import modelo.Usuario;

/**
 * 
 * @author Castillo, Garay
 *
 */

@Stateless
public class GestionTiendaON {
	
	@Inject
	private UsuarioDAO ud;
	@Inject
	private PeliculaDAO peliculaDAO;
	@Inject
	private CategoriaDAO categoriaDAO;
	

	
	public void crearUsu(Usuario u) {
		ud.insertar(u);
	}
	
	public void crearPelicula(Pelicula pelicula) {
		peliculaDAO.insertar(pelicula);
	}
	
	public void crearCategoria(Categoria categoria) {
		categoriaDAO.insertar(categoria);
	}
	
	
	public void actualizarPelicula(Pelicula pelicula) {
		peliculaDAO.actualizar(pelicula);
	}
	
	public void actualizarCategoria(Categoria categoria) {
		categoriaDAO.actualizar(categoria);
	}

	public Usuario buscarUsu(String cedula) {
		return ud.buscar(cedula);
	}
	
	public Categoria buscarCategoria(int id) {
		return categoriaDAO.buscar(id);
	}
	
	public Pelicula buscarPelicula(int id){
		return peliculaDAO.buscar(id);
		
	}
	
	
	public boolean actulizarUsu(Usuario u) {
		boolean aux = false;
		Usuario ub = buscarUsu(u.getCedula());
		if (ub.getCedula() != (null)) {
			ud.actualizar(u);
			aux = true;
		}
		return aux;
	}

	public boolean eliminarUsu(String cedula) {
		boolean aux = false;
		
		Usuario u = buscarUsu(cedula);
		if (u.getCedula() != (null)) {
			ud.borrar(cedula);
			aux = true;
		}
		return aux;
	}
	
	public void eliminarCategoria(int id) {
		 categoriaDAO.borrar(id);
	}
	
	public void eliminarPelicula(int id) {
		peliculaDAO.borrar(id);
	}
	
	public List<Categoria>liCategorias(){
		return categoriaDAO.listadoCategorias();
	}
	
	public List<Pelicula>lPeliculas(){
		return peliculaDAO.ListadoPeliculas();
	}

	public List<Usuario> listadoUsuarios() {
		return ud.listadoUsuarios();
	}
	
	public List<Usuario> listadoUsuBuscado(String cedula) {
		return ud.listadoUsuarioBuscado(cedula);
	}
	
	public List<Categoria>listadoCategoriasBuscando(int id){
		return categoriaDAO.listadoCategoriaBuscado(id);
	}
	
	public List<Pelicula>listadoPeliculasBuscando(int id){
		return peliculaDAO.listadoPeliculaBuscado(id);
	}
}
