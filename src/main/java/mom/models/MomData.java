package mom.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MomData {

@SerializedName("id")
@Expose
private Long id;

@SerializedName("meetingName")
@Expose
private String meetingName;

@SerializedName("location")
@Expose
private List<Location> location = new ArrayList<Location>();

@SerializedName("meetingDate")
@Expose
private Date meetingDate;

@SerializedName("startTime")
@Expose
private Date startTime;

@SerializedName("endTime")
@Expose
private Date endTime;

@SerializedName("actionItems")
@Expose
private List<ActionItem> actionItems = new ArrayList<ActionItem>();

@SerializedName("recipients")
@Expose
private List<String> recipients = new ArrayList<String>();


@SerializedName("participants")
@Expose
private List<String> participants = new ArrayList<String>();


@SerializedName("project")
@Expose
private List<Project> project = new ArrayList<Project>();

@SerializedName("discussionPoints")
@Expose
private List<DataHolder> discussionPoints = new ArrayList<DataHolder>();

@SerializedName("decisionPoints")
@Expose
private List<DataHolder> decisionPoints = new ArrayList<DataHolder>();
/**
* 
* @return
* The id
*/
public Long getId() {
return id;
}

/**
* 
* @param id
* The id
*/
public void setId(Long id) {
this.id = id;
}

/**
* 
* @return
* The meetingName
*/
public String getMeetingName() {
return meetingName;
}

/**
* 
* @param meetingName
* The meetingName
*/
public void setMeetingName(String meetingName) {
this.meetingName = meetingName;
}

/**
* 
* @return
* The location
*/
public List<Location> getLocation() {
return location;
}

/**
* 
* @param location
* The location
*/
public void setLocation(List<Location> location) {
this.location = location;
}

/**
* 
* @return
* The meetingDate
*/
public Date getMeetingDate() {
return meetingDate;
}

/**
* 
* @param meetingDate
* The meetingDate
*/
public void setMeetingDate(Date meetingDate) {
this.meetingDate = meetingDate;
}

/**
* 
* @return
* The startTime
*/
public Date getStartTime() {
return startTime;
}

/**
* 
* @param startTime
* The startTime
*/
public void setStartTime(Date startTime) {
this.startTime = startTime;
}

/**
* 
* @return
* The endTime
*/
public Date getEndTime() {
return endTime;
}

/**
* 
* @param endTime
* The endTime
*/
public void setEndTime(Date endTime) {
this.endTime = endTime;
}

/**
* 
* @return
* The actionItems
*/
public List<ActionItem> getActionItems() {
return actionItems;
}

/**
* 
* @param actionItems
* The actionItems
*/
public void setActionItems(List<ActionItem> actionItems) {
this.actionItems = actionItems;
}

public List<DataHolder> getDiscussionPoints() {
	return discussionPoints;
}

public void setDiscussionPoints(List<DataHolder> discussionPoints) {
	this.discussionPoints = discussionPoints;
}

public List<DataHolder> getDecisionPoints() {
	return decisionPoints;
}

public void setDecisionPoints(List<DataHolder> decisionPoints) {
	this.decisionPoints = decisionPoints;
}

public List<String> getRecipients() {
	return recipients;
}

public void setRecipients(List<String> recipients) {
	this.recipients = recipients;
}

public List<String> getParticipants() {
	return participants;
}

public void setParticipants(List<String> participants) {
	this.participants = participants;
}

public List<Project> getProject() {
	return project;
}

public void setProject(List<Project> project) {
	this.project = project;
}



}

