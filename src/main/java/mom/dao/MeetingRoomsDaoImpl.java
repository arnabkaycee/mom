package mom.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import mom.entities.MeetingRooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MeetingRoomsDaoImpl implements MeetingRoomsDao {

	@Autowired
	EntityManager em;
	
	@Override
	public MeetingRooms getMeetingRoom(Integer roomId) {
		Query q=em.createQuery("from MeetingRooms mr where mr.roomId=:roomId");
	    q.setParameter("roomId", roomId);
	    MeetingRooms meetingRoom = (MeetingRooms) q.getSingleResult();
	    em.close();
		return meetingRoom;
	}

}
