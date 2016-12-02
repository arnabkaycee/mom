package mom.entities;

// Generated 16 May, 2016 12:35:57 PM by Hibernate Tools 4.3.1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Projects generated by hbm2java
 */
@Entity
@Table(name = "projects", catalog = "mom")
public class Projects implements java.io.Serializable {

	private Integer projectId;
	private Integer version;
	private Users users;
	private String projectName;
	private Date updDate;
	private Set<Mom> moms = new HashSet<Mom>(0);
	private Set<UserRelations> userRelationses = new HashSet<UserRelations>(0);

	public Projects() {
	}

	public Projects(Users users, String projectName, Date updDate) {
		this.users = users;
		this.projectName = projectName;
		this.updDate = updDate;
	}

	public Projects(Users users, String projectName, Date updDate, Set moms,
			Set userRelationses) {
		this.users = users;
		this.projectName = projectName;
		this.updDate = updDate;
		this.moms = moms;
		this.userRelationses = userRelationses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "project_id", unique = true, nullable = false)
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
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
	@JoinColumn(name = "manager_id", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "project_name", nullable = false, length = 40)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upd_date", nullable = false, length = 19)
	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projects")
	public Set<Mom> getMoms() {
		return this.moms;
	}

	public void setMoms(Set<Mom> moms) {
		this.moms = moms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projects")
	public Set<UserRelations> getUserRelationses() {
		return this.userRelationses;
	}

	public void setUserRelationses(Set<UserRelations> userRelationses) {
		this.userRelationses = userRelationses;
	}

}
