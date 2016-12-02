package mom.entities;

// Generated 16 May, 2016 12:35:57 PM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * UserRelations generated by hbm2java
 */
@Entity
@Table(name = "user_relations", catalog = "mom")
public class UserRelations implements java.io.Serializable {

	private UserRelationsId id;
	private Integer version;
	private Departments departments;
	private Projects projects;
	private Users usersByUserId;
	private Users usersByManagerId;
	private Date updDate;

	public UserRelations() {
	}

	public UserRelations(UserRelationsId id, Departments departments,
			Projects projects, Users usersByUserId, Users usersByManagerId,
			Date updDate) {
		this.id = id;
		this.departments = departments;
		this.projects = projects;
		this.usersByUserId = usersByUserId;
		this.usersByManagerId = usersByManagerId;
		this.updDate = updDate;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false, length = 40)),
			@AttributeOverride(name = "managerId", column = @Column(name = "manager_id", nullable = false, length = 40)),
			@AttributeOverride(name = "departmentId", column = @Column(name = "department_id", nullable = false)),
			@AttributeOverride(name = "projectId", column = @Column(name = "project_id", nullable = false)) })
	public UserRelationsId getId() {
		return this.id;
	}

	public void setId(UserRelationsId id) {
		this.id = id;
	}

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false, insertable = false, updatable = false)
	public Departments getDepartments() {
		return this.departments;
	}

	public void setDepartments(Departments departments) {
		this.departments = departments;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false, insertable = false, updatable = false)
	public Projects getProjects() {
		return this.projects;
	}

	public void setProjects(Projects projects) {
		this.projects = projects;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	public Users getUsersByUserId() {
		return this.usersByUserId;
	}

	public void setUsersByUserId(Users usersByUserId) {
		this.usersByUserId = usersByUserId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id", nullable = false, insertable = false, updatable = false)
	public Users getUsersByManagerId() {
		return this.usersByManagerId;
	}

	public void setUsersByManagerId(Users usersByManagerId) {
		this.usersByManagerId = usersByManagerId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upd_date", nullable = false, length = 19)
	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

}