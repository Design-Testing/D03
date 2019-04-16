
package services.auxiliary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import security.UserAccount;
import security.UserAccountRepository;
import services.AdministratorService;
import services.CompanyService;
import services.HackerService;
import services.UserAccountService;
import domain.Actor;
import domain.Administrator;
import domain.Company;
import domain.CreditCard;
import domain.Hacker;
import forms.ActorForm;
import forms.CompanyForm;

@Service
@Transactional
public class RegisterService {

	@Autowired
	private UserAccountService		userAccountService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private UserAccountRepository	userAccountRepository;

	@Autowired
	private HackerService			hackerService;

	@Autowired
	private CompanyService			companyService;


	public Administrator saveAdmin(final Administrator admin, final BindingResult binding) {
		Administrator result;
		final UserAccount ua = admin.getUserAccount();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String hash = encoder.encodePassword(ua.getPassword(), null);
		if (admin.getId() == 0) {
			Assert.isTrue(this.userAccountRepository.findByUsername(ua.getUsername()) == null, "The username is register");
			ua.setPassword(hash);
			admin.setUserAccount(ua);
			result = this.administratorService.save(admin);
			UserAccount uaSaved = result.getUserAccount();
			uaSaved.setAuthorities(ua.getAuthorities());
			uaSaved.setUsername(ua.getUsername());
			uaSaved.setPassword(ua.getPassword());
			uaSaved = this.userAccountService.save(uaSaved);
			result.setUserAccount(uaSaved);
		} else {
			final Administrator old = this.administratorService.findOne(admin.getId());

			ua.setPassword(hash);
			if (!old.getUserAccount().getUsername().equals(ua.getUsername()))
				Assert.isTrue(this.userAccountRepository.findByUsername(ua.getUsername()) == null, "The username is register");

			result = this.administratorService.save(admin);

		}

		return result;
	}

	public Hacker saveHacker(final Hacker hacker, final BindingResult binding) {
		Hacker result;
		final UserAccount ua = hacker.getUserAccount();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String hash = encoder.encodePassword(ua.getPassword(), null);
		if (hacker.getId() == 0) {
			Assert.isTrue(this.userAccountRepository.findByUsername(ua.getUsername()) == null, "The username is register");
			ua.setPassword(hash);
			hacker.setUserAccount(ua);
			result = this.hackerService.save(hacker);
			UserAccount uaSaved = result.getUserAccount();
			uaSaved.setAuthorities(ua.getAuthorities());
			uaSaved.setUsername(ua.getUsername());
			uaSaved.setPassword(ua.getPassword());
			uaSaved = this.userAccountService.save(uaSaved);
			result.setUserAccount(uaSaved);
		} else {
			final Hacker old = this.hackerService.findOne(hacker.getId());

			ua.setPassword(hash);
			if (!old.getUserAccount().getUsername().equals(ua.getUsername()))
				Assert.isTrue(this.userAccountRepository.findByUsername(ua.getUsername()) == null, "The username is register");

			result = this.hackerService.save(hacker);

		}

		return result;
	}

	public Company saveCompany(final Company company, final BindingResult binding) {
		Company result;
		final UserAccount ua = company.getUserAccount();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String hash = encoder.encodePassword(ua.getPassword(), null);
		if (company.getId() == 0) {
			Assert.isTrue(this.userAccountRepository.findByUsername(ua.getUsername()) == null, "The username is register");
			ua.setPassword(hash);
			company.setUserAccount(ua);
			result = this.companyService.save(company);
			UserAccount uaSaved = result.getUserAccount();
			uaSaved.setAuthorities(ua.getAuthorities());
			uaSaved.setUsername(ua.getUsername());
			uaSaved.setPassword(ua.getPassword());
			uaSaved = this.userAccountService.save(uaSaved);
			result.setUserAccount(uaSaved);
		} else {
			final Company old = this.companyService.findOne(company.getId());

			ua.setPassword(hash);
			if (!old.getUserAccount().getUsername().equals(ua.getUsername()))
				Assert.isTrue(this.userAccountRepository.findByUsername(ua.getUsername()) == null, "The username is register");

			result = this.companyService.save(company);

		}

		return result;
	}

	public ActorForm inyect(final Actor actor) {
		final ActorForm result = new ActorForm();

		final CreditCard c = actor.getCreditCard();

		result.setAddress(actor.getAddress());
		result.setEmail(actor.getEmail());
		result.setId(actor.getId());
		result.setName(actor.getName());
		result.setPhone(actor.getPhone());
		result.setPhoto(actor.getPhoto());
		result.setSurname(actor.getSurname());
		result.setVat(actor.getVat());
		result.setVersion(actor.getVersion());
		// CreditCard
		result.setHolderName(c.getHolderName());
		result.setExpirationMonth(c.getExpirationMonth());
		result.setExpirationYear(c.getExpirationYear());
		result.setMake(c.getMake());
		result.setNumber(c.getNumber());
		result.setCvv(c.getCvv());

		result.setUserAccountpassword(actor.getUserAccount().getPassword());
		result.setUserAccountuser(actor.getUserAccount().getUsername());
		result.setVersion(actor.getVersion());

		return result;
	}

	public CompanyForm inyect(final Company company) {
		final CompanyForm result = new CompanyForm();

		final CreditCard c = company.getCreditCard();

		result.setAddress(company.getAddress());
		result.setEmail(company.getEmail());
		result.setId(company.getId());
		result.setName(company.getName());
		result.setPhone(company.getPhone());
		result.setPhoto(company.getPhoto());
		result.setSurname(company.getSurname());
		result.setVat(company.getVat());
		result.setVersion(company.getVersion());
		result.setCommercialName(company.getCommercialName());
		// CreditCard
		result.setHolderName(c.getHolderName());
		result.setExpirationMonth(c.getExpirationMonth());
		result.setExpirationYear(c.getExpirationYear());
		result.setMake(c.getMake());
		result.setNumber(c.getNumber());
		result.setCvv(c.getCvv());

		result.setUserAccountpassword(company.getUserAccount().getPassword());
		result.setUserAccountuser(company.getUserAccount().getUsername());
		result.setVersion(company.getVersion());

		return result;
	}

}
