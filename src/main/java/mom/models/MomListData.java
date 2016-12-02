package mom.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class MomListData {
	
	@Id
	@SerializedName("id")
	@Expose
	private Long id;
	
	@SerializedName("meetingName")
	@Expose
	private String meetingName;
	
	@SerializedName("projectName")
	@Expose
	private String projectName;
	
	@SerializedName("meetingDate")
	@Expose
	private Date meetingDate;
	
	@SerializedName("startTime")
	@Expose
	private Date startTime;
	
	@SerializedName("endTime")
	@Expose
	private Date endTime;
	
	@SerializedName("userId")
	@Expose
	private String userId;
	
	@SerializedName("meetingFromDate")
	@Expose
	private Date meetingFromDate;
	
	@SerializedName("meetingEndDate")
	@Expose
	private Date meetingEndDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getMeetingFromDate() {
		return meetingFromDate;
	}

	public void setMeetingFromDate(Date meetingFromDate) {
		this.meetingFromDate = meetingFromDate;
	}

	public Date getMeetingEndDate() {
		return meetingEndDate;
	}

	public void setMeetingEndDate(Date meetingEndDate) {
		this.meetingEndDate = meetingEndDate;
	}

}
