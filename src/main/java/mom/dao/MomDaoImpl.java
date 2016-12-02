package mom.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mom.entities.Mom;
import mom.models.MomListData;
import mom.utils.CommonConstants;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MomDaoImpl implements MomDao{

	@Autowired
	CommonDao commonDao;
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Mom getMom(Long momId) {
		Query q=em.createQuery("from Mom m where m.momId=:momId");
	    q.setParameter("momId", momId);
	    Mom mom = (Mom) q.getSingleResult();
	    em.close();
		return mom;
	}

	@Override
	public List<MomListData> getMoms(Map<String, Object> momSearchData) {
		StringBuilder query = new StringBuilder(
				"select distinct m.mom_id as id, m.organizer as userId, m.project_name as projectName, m.mom_desc as meetingName, m.meeting_date as meetingDate,m.meeting_date as meetingFromDate, m.meeting_date as meetingEndDate, m.start_time as startTime, m.end_time as endTime from mom m, mom_participants mp, mom_recipients mr where (m.mom_Id=mp.mom_id and m.mom_id=mr.mom_id) and (mp.user_id = :userId or mr.user_id = :userId or m.organizer =:userId)");
		if(!StringUtils.isBlank((String) momSearchData.get("meetingName"))){
			query.append(" and m.mom_desc COLLATE UTF8_GENERAL_CI like :meetingName");
		}
		if(!StringUtils.isBlank((String) momSearchData.get("projectName"))){
			query.append(" and m.project_name COLLATE UTF8_GENERAL_CI like :projectName");
		}
		if(null != momSearchData.get("meetingFromDate")){
			query.append(" and m.meeting_date >= :meetingFromDate ");
		}
		if(null != momSearchData.get("meetingEndDate")){
			query.append(" and m.meeting_date <= :meetingEndDate");
		}
		Query q=em.createNativeQuery(query.toString(), MomListData.class);
	    
		Set<Parameter<?>> params = q.getParameters();
		for (Parameter<?> object : params) {
				if(object.getName().contains("Date")){
					q.setParameter(object.getName(),(Date) momSearchData.get(object.getName()));
				}else if(object.getName().equals("userId")){
					q.setParameter(object.getName(),(String) momSearchData.get(object.getName()));
				}else{
					q.setParameter(object.getName(),
							CommonConstants.PERCENTAGE+
							(String) momSearchData.get(object.getName())+
							CommonConstants.PERCENTAGE);
				}
		}	
	    List <MomListData> momList = (ArrayList<MomListData>)q.getResultList();
	    em.close();
		return momList;
	}
	
	@Override
	public void insertMom(Mom mom) {
		commonDao.merge(mom);
	}

}
