package mom.entities;

// Generated 16 May, 2016 12:35:57 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MomDiscussionsId generated by hbm2java
 */
@Embeddable
public class MomDiscussionsId implements java.io.Serializable {

	private long momId;
	private int discussionId;

	public MomDiscussionsId() {
	}

	public MomDiscussionsId(long momId, int discussionId) {
		this.momId = momId;
		this.discussionId = discussionId;
	}

	@Column(name = "mom_id", nullable = false)
	public long getMomId() {
		return this.momId;
	}

	public void setMomId(long momId) {
		this.momId = momId;
	}

	@Column(name = "discussion_id", nullable = false)
	public int getDiscussionId() {
		return this.discussionId;
	}

	public void setDiscussionId(int discussionId) {
		this.discussionId = discussionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MomDiscussionsId))
			return false;
		MomDiscussionsId castOther = (MomDiscussionsId) other;

		return (this.getMomId() == castOther.getMomId())
				&& (this.getDiscussionId() == castOther.getDiscussionId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getMomId();
		result = 37 * result + this.getDiscussionId();
		return result;
	}

}