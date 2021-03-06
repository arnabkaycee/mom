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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * MomActionOwner generated by hbm2java
 */
@Entity
@Table(name = "mom_action_owner", catalog = "mom")
public class MomActionOwner implements java.io.Serializable {

	private MomActionOwnerId id;
	private Integer version;
	private MomActions momActions;
	private Users users;
	private Date updDate;

	public MomActionOwner() {
	}

	public MomActionOwner(MomActionOwnerId id, MomActions momActions,
			Users users, Date updDate) {
		this.id = id;
		this.momActions = momActions;
		this.users = users;
		this.updDate = updDate;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "momId", column = @Column(name = "mom_id", nullable = false)),
			@AttributeOverride(name = "actionId", column = @Column(name = "action_id", nullable = false)),
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false, length = 40)) })
	public MomActionOwnerId getId() {
		return this.id;
	}

	public void setId(MomActionOwnerId id) {
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
	@JoinColumns({
			@JoinColumn(name = "mom_id", referencedColumnName = "mom_id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "action_id", referencedColumnName = "action_id", nullable = false, insertable = false, updatable = false) })
	public MomActions getMomActions() {
		return this.momActions;
	}

	public void setMomActions(MomActions momActions) {
		this.momActions = momActions;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
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
