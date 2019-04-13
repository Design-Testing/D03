
package forms;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class ActorForm extends DomainEntity {

	private String				name;
	private Collection<String>	surname;
	private String				photo;
	private String				email;
	private String				phone;
	private String				address;
	private double				vat;
	private String				holderName;
	private String				make;
	private String				number;
	private Integer				expirationMonth;
	private Integer				expirationYear;
	private Integer				cvv;

	private Boolean				termsAndCondicions;


	@AssertTrue
	public Boolean getTermsAndCondicions() {
		return this.termsAndCondicions;
	}

	public void setTermsAndCondicions(final Boolean termsAndCondicions) {
		this.termsAndCondicions = termsAndCondicions;
	}


	//Relational attributes
	private String	userAccountuser;
	private String	userAccountpassword;


	@Size(min = 5, max = 32)
	public String getUserAccountuser() {
		return this.userAccountuser;
	}

	public void setUserAccountuser(final String userAccountuser) {
		this.userAccountuser = userAccountuser;
	}
	@Size(min = 5, max = 32)
	public String getUserAccountpassword() {
		return this.userAccountpassword;
	}

	public void setUserAccountpassword(final String userAccountpassword) {
		this.userAccountpassword = userAccountpassword;
	}

	@NotBlank
	@SafeHtml
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@ElementCollection
	public Collection<String> getSurname() {
		return this.surname;
	}

	public void setSurname(final Collection<String> surname) {
		this.surname = surname;
	}

	@URL
	@SafeHtml
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	@NotBlank
	@Email
	@SafeHtml
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Pattern(regexp = "((\\+34|0034|34)?[ -]*(6|7)[ -]*([0-9][ -]*){8})||''")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	@SafeHtml
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	//	@Range(min = (long) -1.00, max = (long) 1.00)
	//	public Double getScore() {
	//		return this.score;
	//	}
	//
	//	public void setScore(final Double score) {
	//		this.score = score;
	//	}

	//	public Boolean getSpammer() {
	//		return this.spammer;
	//	}
	//
	//	public void setSpammer(final Boolean spammer) {
	//		this.spammer = spammer;
	//	}

	@Range(min = 0, max = 0)
	@Digits(integer = 1, fraction = 2)
	public double getVat() {
		return this.vat;
	}

	public void setVat(final double vat) {
		this.vat = vat;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@NotBlank
	@SafeHtml
	public String getHolderName() {
		return this.holderName;
	}

	public void setHolderName(final String holderName) {
		this.holderName = holderName;
	}

	@NotBlank
	public String getMake() {
		return this.make;
	}

	public void setMake(final String make) {
		this.make = make;
	}

	@Column(unique = true)
	@NotBlank
	@CreditCardNumber
	public String getNumber() {
		return this.number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	@Range(min = 1, max = 12)
	public Integer getExpirationMonth() {
		return this.expirationMonth;
	}

	public void setExpirationMonth(final Integer expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	@Range(min = 0, max = 99)
	public Integer getExpirationYear() {
		return this.expirationYear;
	}

	public void setExpirationYear(final Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

	@Range(min = 100, max = 999)
	public Integer getCvv() {
		return this.cvv;
	}

	public void setCvv(final Integer cvv) {
		this.cvv = cvv;
	}

}
