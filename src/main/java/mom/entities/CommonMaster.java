package mom.entities;

// Generated 16 May, 2016 12:35:57 PM by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CommonMaster generated by hbm2java
 */
@Entity
@Table(name = "common_master", catalog = "mom")
public class CommonMaster implements java.io.Serializable {

	private CommonMasterId id;

	public CommonMaster() {
	}

	public CommonMaster(CommonMasterId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "commonId", column = @Column(name = "common_id", nullable = false)),
			@AttributeOverride(name = "commonSubId", column = @Column(name = "common_sub_id", nullable = false)),
			@AttributeOverride(name = "description", column = @Column(name = "description", nullable = false, length = 40)),
			@AttributeOverride(name = "updDate", column = @Column(name = "upd_date", nullable = false, length = 19)),
			@AttributeOverride(name = "version", column = @Column(name = "version")) })
	public CommonMasterId getId() {
		return this.id;
	}

	public void setId(CommonMasterId id) {
		this.id = id;
	}

}
