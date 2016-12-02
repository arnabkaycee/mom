package mom.entities;

// Generated 16 May, 2016 12:35:57 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Departments generated by hbm2java
 */
@Entity
@Table(name = "departments", catalog = "mom")
public class Departments implements java.io.Serializable {

	private Integer departmentId;
	private Integer version;
	private String departmentName;
	private Date updDate;
	private Set<UserRelations> userRelationses = new HashSet<UserRelations>(0);

	public Departments() {
	}

	public Departments(String departmentName, Date updDate) {
		this.departmentName = departmentName;
		this.updDate = updDate;
	}

	public Departments(String departmentName, Date updDate, Set userRelationses) {
		this.departmentName = departmentName;
		this.updDate = updDate;
		this.userRelationses = userRelationses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "department_id", unique = true, nullable = false)
	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "department_name", nullable = false, length = 40)
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upd_date", nullable = false, length = 19)
	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departments")
	public Set<UserRelations> getUserRelationses() {
		return this.userRelationses;
	}

	public void setUserRelationses(Set<UserRelations> userRelationses) {
		this.userRelationses = userRelationses;
	}

}