package mom.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import mom.entities.Users;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Users getUser(String userId) {
		Query q=em.createQuery("from Users u where u.userId=:userId");
	    q.setParameter("userId", userId);
	    Users user = (Users) q.getSingleResult();
	    em.close();
		return user;
	}

	/*@Override
	public Users getUsers(String userId) {
		Query q=em.createQuery("from Users u where u.userId=:userId");
	    q.setParameter("userId", userId);
	    Users user = (Users) q.getSingleResult();
	    em.close();
		return user;
	}
*/
}
