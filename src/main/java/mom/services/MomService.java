package mom.services;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mom.dao.CommonDao;
import mom.dao.MeetingRoomsDao;
import mom.dao.MomDao;
import mom.dao.ProjectsDao;
import mom.dao.UserDao;
import mom.entities.MeetingRooms;
import mom.entities.Mom;
import mom.entities.MomActionOwner;
import mom.entities.MomActionOwnerId;
import mom.entities.MomActions;
import mom.entities.MomActionsId;
import mom.entities.MomDiscussions;
import mom.entities.MomDiscussionsId;
import mom.entities.MomParticipants;
import mom.entities.MomParticipantsId;
import mom.entities.MomRecipients;
import mom.entities.MomRecipientsId;
import mom.entities.Projects;
import mom.entities.Users;
import mom.models.ActionItem;
import mom.models.DataHolder;
import mom.models.Location;
import mom.models.MomData;
import mom.models.MomListData;
import mom.models.Project;
import mom.models.Status;
import mom.utils.CommonConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MomService {
	
	@Autowired
	CommonDao commonDao;
	
	@Autowired
	ProjectsDao projectsDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	MeetingRoomsDao meetingRoomDao;
	
	@Autowired
	MomDao momDao;
	
	public void updateMom(MomData momData){
		
	}
	
	public void addMom(MomData momData){
		String momId = commonDao.getSequenceNo(10);
		
		//Create Mom master data -- Start
		Mom momDto = new Mom();
		momDto.setMomId(Long.parseLong(momId));
		momDto.setMomDesc(momData.getMeetingName());
		
		// Check whether location id or location name is given.
		Integer projectId = momData.getProject().get(0).getId();
		if (null != projectId) {
			//Fetch Project DTO from database and set to Mom DTO.
			Projects project = projectsDao.getProject(momData.getProject().get(0).getId());
			momDto.setProjects(project);
		}
		momDto.setProjectName(momData.getProject().get(0).getName());
		
		//Fetch Project DTO from database and set to Mom DTO.
		Users orgnizer = userDao.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		momDto.setUsers(orgnizer);
		
		momDto.setMeetingDate(momData.getMeetingDate());
		momDto.setStartTime(momData.getStartTime());
		momDto.setEndTime(momData.getEndTime());
		
		//Check whether location id or location name is given.
		Integer locationId = momData.getLocation().get(0).getId();
		if(null != locationId){
			MeetingRooms meetingRoom = meetingRoomDao.getMeetingRoom(locationId);
			momDto.setMeetingRooms(meetingRoom);
		}
		momDto.setMeetingRoom(momData.getLocation().get(0).getName());
		
		momDto.setUpdDate(new Date());
		//Create Mom master data -- End
		
		//Create MomActions data -- Start
		HashSet<MomActions> momActions = new HashSet<MomActions>();
		List<ActionItem> actionItems = momData.getActionItems();
		int actionIdCount = 1;
		for (ActionItem actionItem : actionItems) {
			MomActions action = new MomActions();
			MomActionsId actionId = new MomActionsId();
			actionId.setMomId(momDto.getMomId());
			actionId.setActionId(actionIdCount++);
			action.setId(actionId);
			action.setActionDesc(actionItem.getActionItem());
			action.setTargetDate(actionItem.getTargetDate());
			action.setActualEndDate(actionItem.getActualEndDate());
			action.setRevisedTargetDate(actionItem.getRevisedTargetDate());
			action.setStatusCode(actionItem.getStatus().getVal());
			action.setRemarks(actionItem.getRemarks());
			action.setUpdDate(new Date());
			//action.setMom(momDto);
			
			HashSet<MomActionOwner> actionOwners = new HashSet<MomActionOwner>();
			
			List<String> actionItemResponsibles = actionItem.getResponsibility();
			for (String resposibleEmailId : actionItemResponsibles) {
				String responsible = resposibleEmailId.split("@")[0];
				//Create MomActionOwner data -- Start
				MomActionOwner actionOwner = new MomActionOwner();
				MomActionOwnerId actionOwnerId = new MomActionOwnerId();
				actionOwnerId.setMomId(momDto.getMomId());
				actionOwnerId.setActionId(action.getId().getActionId());
				actionOwnerId.setUserId(responsible);
				actionOwner.setId(actionOwnerId);
				
				//Fetch User DTO from database and set to MomActionOwner DTO.
				Users user = userDao.getUser(responsible);
				actionOwner.setUsers(user);
				actionOwner.setUpdDate(new Date());
				//Create MomActionOwner data -- End
				actionOwners.add(actionOwner);
			}
			action.setMomActionOwners(actionOwners);
			momActions.add(action);
		}
		//Create MomActions data -- End
		momDto.setMomActionses(momActions);
		
		HashSet<MomRecipients> momRecipients = new HashSet<MomRecipients>();
		
		//Create MomRecipients data -- Start
		List<String> recipients = momData.getRecipients();
		for (String recipientEmailId : recipients) {
			String recipientName = recipientEmailId.split("@")[0];
			MomRecipients recipient = new MomRecipients();
			MomRecipientsId recipientsId = new MomRecipientsId();
			recipientsId.setMomId(momDto.getMomId());
			recipientsId.setUserId(recipientName);
			recipient.setId(recipientsId);
			recipient.setUpdDate(new Date());
			//Fetch User DTO from database and set to MomActionOwner DTO.
			Users user = userDao.getUser(recipientName);
			recipient.setUsers(user);
			//recipient.setMom(momDto);
			momRecipients.add(recipient);
		}
		//Create MomRecipients data -- End
		
		momDto.setMomRecipientses(momRecipients);
		
		HashSet<MomParticipants> momParticipants = new HashSet<MomParticipants>();
		
		//Create MomParticpants data -- Start
		List<String> particpants = momData.getParticipants();
		for (String particpantEmailId : particpants) {
			String participantName = particpantEmailId.split("@")[0];
			MomParticipants particpant = new MomParticipants();
			MomParticipantsId particpantId = new MomParticipantsId();
			particpantId.setMomId(momDto.getMomId());
			particpantId.setUserId(participantName);
			particpant.setId(particpantId);
			particpant.setUpdDate(new Date());
			// Fetch User DTO from database and set to MomActionOwner DTO.
			Users user = userDao.getUser(participantName);
			particpant.setUsers(user);
			//particpant.setMom(momDto);
			momParticipants.add(particpant);
		}
		// Create MomRecipients data -- End		
		
		momDto.setMomParticipantses(momParticipants);
		
		
		HashSet<MomDiscussions> momDiscussions = new HashSet<MomDiscussions>();
		int discussionIdCount = 1;
		//Create MomDiscussions data -- Start
		List<DataHolder> discussionPoints = momData.getDiscussionPoints();
		
		for (DataHolder point : discussionPoints) {
			MomDiscussions discussion = new MomDiscussions();
			MomDiscussionsId discussionId = new MomDiscussionsId(momDto.getMomId(), discussionIdCount++);
			discussion.setId(discussionId);
			discussion.setUpdDate(new Date());
			discussion.setDiscussion("Y");
			discussion.setDescription(point.getName());
			//discussion.setMom(momDto);
			momDiscussions.add(discussion);
		}
		
		List<DataHolder> decisionPoints = momData.getDecisionPoints();
		for (DataHolder point : decisionPoints) {
			MomDiscussions discussion = new MomDiscussions();
			MomDiscussionsId discussionId = new MomDiscussionsId(momDto.getMomId(), discussionIdCount++);
			discussion.setId(discussionId);
			discussion.setUpdDate(new Date());
			discussion.setDiscussion("N");
			discussion.setDescription(point.getName());
			//discussion.setMom(momDto);
			momDiscussions.add(discussion);
		}
		// Create MomDiscussions data -- End		
		momDto.setMomDiscussionses(momDiscussions);
		momDao.insertMom(momDto);
		
	}
	
	public MomData getMom(long momId){
		Mom momDto = momDao.getMom(momId);
		MomData momData = DtoToDataConverter(momDto);	
		return momData;
	}
	
	private MomData DtoToDataConverter(Mom momDto){
		MomData momData = new MomData();
		momData.setId(momDto.getMomId());
		momData.setMeetingName(momDto.getMomDesc());
		
		Projects projectDto = momDto.getProjects();
		List<Project> projects = new ArrayList<Project>();
		Project projectData = new Project();
		if(null != projectDto){
			projectData.setName(projectDto.getProjectName());
			projectData.setId(projectDto.getProjectId());
		}else{
			projectData.setName(momDto.getProjectName());
		}
		projects.add(projectData);
		momData.setProject(projects);
		
		MeetingRooms meetingRoom = momDto.getMeetingRooms();
		List<Location> locations = new ArrayList<Location>();
		Location location = new Location();
		if(null != meetingRoom){
			location.setName(meetingRoom.getRoomName());
			location.setId(meetingRoom.getRoomId());
		}else{
			location.setName(momDto.getMeetingRoom());
		}
		locations.add(location);
		momData.setLocation(locations);
		
		momData.setMeetingDate(momDto.getMeetingDate());
		momData.setStartTime(momDto.getStartTime());
		momData.setEndTime(momDto.getEndTime());
		
		List<String> participants = new ArrayList<String>();
		Set<MomParticipants> momParticipants = momDto.getMomParticipantses();
		for (MomParticipants participant : momParticipants) {
			participants.add(participant.getId().getUserId()+CommonConstants.EMAIL_SUFFIX);
		}
		momData.setParticipants(participants);
		
		List<String> recipients = new ArrayList<String>();
		Set<MomRecipients> momRecipients = momDto.getMomRecipientses();
		for (MomRecipients recipient : momRecipients) {
			recipients.add(recipient.getId().getUserId()+CommonConstants.EMAIL_SUFFIX);
		}
		momData.setRecipients(recipients);
		
		List<DataHolder> discussionPoints = new ArrayList<DataHolder>();
		List<DataHolder> decisionPoints = new ArrayList<DataHolder>();
		
		Set<MomDiscussions> momDiscussions = momDto.getMomDiscussionses();
		for (MomDiscussions momDiscussion : momDiscussions) {
			DataHolder point = new DataHolder();
			if(momDiscussion.getDiscussion().equals("Y")){
				point.setName(momDiscussion.getDescription());
				discussionPoints.add(point);
			}else{
				point.setName(momDiscussion.getDescription());
				decisionPoints.add(point);
			}
		}
		momData.setDecisionPoints(decisionPoints);
		momData.setDiscussionPoints(discussionPoints);
		
		Set<MomActions> momActions = momDto.getMomActionses();
		List<ActionItem> actionItems = new ArrayList<ActionItem>();
		for (MomActions momAction : momActions) {
			ActionItem actionItem = new ActionItem();
			actionItem.setActionItem(momAction.getActionDesc());
			actionItem.setRemarks(momAction.getRemarks());
			actionItem.setTargetDate(momAction.getTargetDate());
			actionItem.setRevisedTargetDate(momAction.getRevisedTargetDate());
			actionItem.setActualEndDate(momAction.getActualEndDate());
			Status status = new Status();
			status.setVal(momAction.getStatusCode());
			actionItem.setStatus(status);
			List<String> resonsibles = new ArrayList<String>();
			Set<MomActionOwner> momActionOwners = momAction.getMomActionOwners();
			for (MomActionOwner momActionOwner : momActionOwners) {
				resonsibles.add(momActionOwner.getId().getUserId()+CommonConstants.EMAIL_SUFFIX);
			}
			actionItem.setResponsibility(resonsibles);
			actionItems.add(actionItem);
		}
		momData.setActionItems(actionItems);
	
		return momData;
	}
	
	public List<MomListData> getMoms(MomListData momSearchData) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException{
		//momSearchData.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
	    BeanInfo info = Introspector.getBeanInfo(momSearchData.getClass());
	    for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
	        Method reader = pd.getReadMethod();
	        if (reader != null)
	        	searchMap.put(pd.getName(),reader.invoke(momSearchData));
	    }
	    searchMap.put("userId", "gourav.singh");
		return momDao.getMoms(searchMap);
	}
	
}
