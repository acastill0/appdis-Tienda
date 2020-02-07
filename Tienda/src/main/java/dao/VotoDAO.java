package dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class VotoDAO {
	@Inject
	private EntityManager em;

}
