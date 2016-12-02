package mom.entities;

// Generated 16 May, 2016 12:35:57 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", catalog = "mom")
public class Users implements java.io.Serializable {

	private String userId;
	private Integer version;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private Date updDate;
	private boolean registered;
	private Set<MomParticipants> momParticipantses = new HashSet<MomParticipants>(0);
	private Set<MomActionOwner> momActionOwners = new HashSet<MomActionOwner>(0);
	private Set<Mom> moms = new HashSet<Mom>(0);
	private Set<Projects> projectses = new HashSet<Projects>(0);
	private Set<MomRecipients> momRecipientses = new HashSet<MomRecipients>(0);
	private Set<UserRelations> userRelationsesForUserId = new HashSet<UserRelations>(0);
	private Set<UserRelations> userRelationsesForManagerId = new HashSet<UserRelations>(0);

	public Users() {
	}

	public Users(String userId, String firstName, String lastName,
			String emailId, String password, Date updDate, boolean registered) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.updDate = updDate;
		this.registered = registered;
	}

	public Users(String userId, String firstName, String lastName,
			String emailId, String password, Date updDate, boolean registered,
			Set momParticipantses, Set momActionOwners, Set moms,
			Set projectses, Set momRecipientses, Set userRelationsesForUserId,
			Set userRelationsesForManagerId) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.updDate = updDate;
		this.registered = registered;
		this.momParticipantses = momParticipantses;
		this.momActionOwners = momActionOwners;
		this.moms = moms;
		this.projectses = projectses;
		this.momRecipientses = momRecipientses;
		this.userRelationsesForUserId = userRelationsesForUserId;
		this.userRelationsesForManagerId = userRelationsesForManagerId;
	}

	@Id
	@Column(name = "user_id", unique = true, nullable = false, length = 40)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "first_name", nullable = false, length = 40)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 40)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email_id", nullable = false, length = 128)
	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "password", nullable = false, length = 256)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "upd_date", nullable = false, length = 19)
	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	@Column(name = "registered", nullable = false)
	public boolean isRegistered() {
		return this.registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<MomParticipants> getMomParticipantses() {
		return this.momParticipantses;
	}

	public void setMomParticipantses(Set<MomParticipants> momParticipantses) {
		this.momParticipantses = momParticipantses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<MomActionOwner> getMomActionOwners() {
		return this.momActionOwners;
	}

	public void setMomActionOwners(Set<MomActionOwner> momActionOwners) {
		this.momActionOwners = momActionOwners;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "users")
	public Set<Mom> getMoms() {
		return this.moms;
	}

	public void setMoms(Set<Mom> moms) {
		this.moms = moms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Projects> getProjectses() {
		return this.projectses;
	}

	public void setProjectses(Set<Projects> projectses) {
		this.projectses = projectses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<MomRecipients> getMomRecipientses() {
		return this.momRecipientses;
	}

	public void setMomRecipientses(Set<MomRecipients> momRecipientses) {
		this.momRecipientses = momRecipientses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usersByUserId")
	public Set<UserRelations> getUserRelationsesForUserId() {
		return this.userRelationsesForUserId;
	}

	public void setUserRelationsesForUserId(Set<UserRelations> userRelationsesForUserId) {
		this.userRelationsesForUserId = userRelationsesForUserId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usersByManagerId")
	public Set<UserRelations> getUserRelationsesForManagerId() {
		return this.userRelationsesForManagerId;
	}

	public void setUserRelationsesForManagerId(Set<UserRelations> userRelationsesForManagerId) {
		this.userRelationsesForManagerId = userRelationsesForManagerId;
	}

}