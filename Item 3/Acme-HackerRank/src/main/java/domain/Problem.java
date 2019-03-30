
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Problem extends DomainEntity {

	//Relationships
	private String				title;
	private String				statement;
	private String				hint;
	private Collection<String>	attachments;
	private String				mode;

	private Company				company;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getStatement() {
		return this.statement;
	}

	public void setStatement(final String statement) {
		this.statement = statement;
	}

	public String getHint() {
		return this.hint;
	}

	public void setHint(final String hint) {
		this.hint = hint;
	}

	public Collection<String> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(final Collection<String> attachments) {
		this.attachments = attachments;
	}

	@NotBlank
	@Pattern(regexp = "^(DRAFT|FINAL)$")
	public String getMode() {
		return this.mode;
	}

	public void setMode(final String mode) {
		this.mode = mode;
	}

	@Valid
	@ManyToOne(optional = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(final Company company) {
		this.company = company;
	}

}
