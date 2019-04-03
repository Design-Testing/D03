
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Answer extends DomainEntity {

	private String	explanation;
	private String	link;


	@NotBlank
	public String getExplanation() {
		return this.explanation;
	}

	public void setExplanation(final String explanation) {
		this.explanation = explanation;
	}

	@NotNull
	@URL
	public String getLink() {
		return this.link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

}
