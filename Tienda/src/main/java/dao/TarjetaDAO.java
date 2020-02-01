package dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Tarjeta;

@Stateless
public class TarjetaDAO {

	@Inject
	private EntityManager em;
	
	public int borrar(int id) throws Exception {
        String sql = "DELETE FROM " + Tarjeta.class.getName() + " d where d.id= :c";
      //  System.out.println("Name "+Detalle.class.getName());
        Query query = em.createQuery(sql).setParameter("c", id);
        int records = query.executeUpdate();
        return records;
    }
	
	
	

}
