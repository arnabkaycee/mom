package mom.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mom.models.Location;
import mom.models.Project;
import mom.utils.CommonConstants;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CommonDaoImpl implements CommonDao{

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * 
	 * Method to get sequence number from DB
	 * 
	 * @param sequence
	 * @return
	 */
	public String getSequenceNo(Integer length) {
		Character lpadChar = '0';
		BigInteger seqNo = null;
		Query q = em.createNativeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'mom' AND TABLE_NAME = 'mom'");
		seqNo = (BigInteger) q.getSingleResult();
		String retVal = StringUtils.leftPad(seqNo.toString(), length, lpadChar);
		em.close();
		return retVal;
	}
	
	@Transactional
	public <T> T persist(final T t) {
		em.persist(t);
		return null;
	}


	@Transactional
	public <T> T merge(T t) {
		em.merge(t);
		return null;
	}

	@Override
	public List<String> getEmails(String email) {
		Query q=em.createQuery("select u.emailId from Users u where lower(u.userId) like lower(:userId)");
	    q.setParameter("userId", CommonConstants.PERCENTAGE+ email + CommonConstants.PERCENTAGE);
	    List<String> users = (ArrayList<String>) q.getResultList();
	    em.close();
		return users;
	}
	
	@Override
	public List<Location> getLocations(String location) {
		Query q=em.createNativeQuery("select mr.room_id as id , mr.room_name as name from meeting_rooms mr where mr.room_name COLLATE UTF8_GENERAL_CI like :roomName", Location.class);
	    q.setParameter("roomName", CommonConstants.PERCENTAGE+ location + CommonConstants.PERCENTAGE);
	    List<Location> rooms = (ArrayList<Location>)q.getResultList();
	    em.close();
		return rooms;
	}
	
	@Override
	public List<Project> getProjects(String project) {
		Query q=em.createNativeQuery("select p.project_id as id , p.project_name as name from projects p where p.project_name COLLATE UTF8_GENERAL_CI like :projectName", Project.class);
	    q.setParameter("projectName", CommonConstants.PERCENTAGE+ project + CommonConstants.PERCENTAGE);
	    List<Project> projects = (ArrayList<Project>)q.getResultList();
	    em.close();
		return projects;
	}
}
