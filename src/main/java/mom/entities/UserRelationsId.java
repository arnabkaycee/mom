package mom.entities;

// Generated 16 May, 2016 12:35:57 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserRelationsId generated by hbm2java
 */
@Embeddable
public class UserRelationsId implements java.io.Serializable {

	private String userId;
	private String managerId;
	private int departmentId;
	private int projectId;

	public UserRelationsId() {
	}

	public UserRelationsId(String userId, String managerId, int departmentId,
			int projectId) {
		this.userId = userId;
		this.managerId = managerId;
		this.departmentId = departmentId;
		this.projectId = projectId;
	}

	@Column(name = "user_id", nullable = false, length = 40)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "manager_id", nullable = false, length = 40)
	public String getManagerId() {
		return this.managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Column(name = "department_id", nullable = false)
	public int getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "project_id", nullable = false)
	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserRelationsId))
			return false;
		UserRelationsId castOther = (UserRelationsId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getManagerId() == castOther.getManagerId()) || (this
						.getManagerId() != null
						&& castOther.getManagerId() != null && this
						.getManagerId().equals(castOther.getManagerId())))
				&& (this.getDepartmentId() == castOther.getDepartmentId())
				&& (this.getProjectId() == castOther.getProjectId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getManagerId() == null ? 0 : this.getManagerId().hashCode());
		result = 37 * result + this.getDepartmentId();
		result = 37 * result + this.getProjectId();
		return result;
	}

}
