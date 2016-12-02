package mom.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mom.entities.Projects;

import org.springframework.stereotype.Repository;

@Repository
public class ProjectsDaoImpl implements ProjectsDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Projects getProject(Integer projectId) {
		Query q=em.createQuery("from Projects p where p.projectId=:projectId");
	    q.setParameter("projectId", projectId);
	    Projects project = (Projects) q.getSingleResult();
	    em.close();
		return project;
	}

}
