
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.HackerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.CreditCard;
import domain.Finder;
import domain.Hacker;
import forms.ActorForm;

@Service
@Transactional
public class HackerService {

	@Autowired
	private HackerRepository	hackerRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private FinderService		finderService;

	@Autowired
	private UserAccountService	userAccountService;

	@Autowired
	private Validator			validator;


	public Hacker create() {
		final Hacker hacker = new Hacker();
		this.actorService.setAuthorityUserAccount(Authority.HACKER, hacker);

		return hacker;
	}

	public Hacker findOne(final int hackerId) {
		Assert.isTrue(hackerId != 0);
		final Hacker result = this.hackerRepository.findOne(hackerId);
		Assert.notNull(result);
		return result;
	}

	public Hacker save(final Hacker hacker) {
		Assert.notNull(hacker);
		Hacker result;

		if (hacker.getId() == 0) {
			final Finder finder = this.finderService.createForNewHacker();
			hacker.setFinder(finder);
			this.actorService.setAuthorityUserAccount(Authority.HACKER, hacker);
			result = this.hackerRepository.save(hacker);
			//			this.folderService.setFoldersByDefault(result);

		} else {
			this.actorService.checkForSpamWords(hacker);
			final Actor principal = this.actorService.findByPrincipal();
			Assert.isTrue(principal.getId() == hacker.getId(), "You only can edit your info");
			result = (Hacker) this.actorService.save(hacker);
		}
		return result;
	}

	// TODO: delete all information but name including folders and their messages (but no as senders!!)
	public void delete(final Hacker hacker) {
		Assert.notNull(hacker);
		Assert.isTrue(this.findByPrincipal().equals(hacker));
		Assert.isTrue(hacker.getId() != 0);
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(principal.getId() == hacker.getId(), "You only can edit your info");
		Assert.isTrue(this.hackerRepository.exists(hacker.getId()));
		this.hackerRepository.delete(hacker);
	}

	/* ========================= OTHER METHODS =========================== */

	public Hacker findByPrincipal() {
		final UserAccount user = LoginService.getPrincipal();
		Assert.notNull(user);

		final Hacker hacker = this.findByUserId(user.getId());
		Assert.notNull(hacker);
		final boolean bool = this.actorService.checkAuthority(hacker, Authority.HACKER);
		Assert.isTrue(bool);

		return hacker;
	}

	public Hacker findByUserId(final int id) {
		Assert.isTrue(id != 0);
		final Hacker hacker = this.hackerRepository.findByUserId(id);
		return hacker;
	}

	/**
	 * The average, minimum, maximum and standard deviation of the number of applications per hacker
	 * 
	 * @author a8081
	 */
	public Double[] getStatisticsOfApplicationsPerHacker() {
		final Double[] res = this.hackerRepository.getStatisticsOfApplicationsPerHacker();
		Assert.notNull(res);
		return res;
	}

	/**
	 * Hackers who have made more applications
	 * 
	 * @author a8081
	 */
	public Collection<Hacker> getHackersMoreApplications() {
		final Collection<Hacker> res = this.hackerRepository.getHackersMoreApplications();
		Assert.notNull(res);
		return res;
	}

	public void flush() {
		this.hackerRepository.flush();
	}

	public Hacker reconstruct(final ActorForm actorForm, final BindingResult binding) {
		Hacker hacker;
		final CreditCard c = new CreditCard();
		c.setHolderName(actorForm.getHolderName());
		c.setNumber(actorForm.getNumber());
		c.setMake(actorForm.getMake());
		c.setExpirationMonth(actorForm.getExpirationMonth());
		c.setExpirationYear(actorForm.getExpirationYear());
		c.setCvv(actorForm.getCvv());

		if (actorForm.getId() == 0) {
			hacker = this.create();
			hacker.setName(actorForm.getName());
			hacker.setSurname(actorForm.getSurname());
			hacker.setPhoto(actorForm.getPhoto());
			hacker.setPhone(actorForm.getPhone());
			hacker.setEmail(actorForm.getEmail());
			hacker.setAddress(actorForm.getAddress());
			hacker.setVat(actorForm.getVat());
			hacker.setVersion(actorForm.getVersion());
			//			hacker.setScore(0.0);
			//			hacker.setSpammer(false);
			final UserAccount account = this.userAccountService.create();
			final Collection<Authority> authorities = new ArrayList<>();
			final Authority auth = new Authority();
			auth.setAuthority(Authority.HACKER);
			authorities.add(auth);
			account.setAuthorities(authorities);
			account.setUsername(actorForm.getUserAccountuser());
			account.setPassword(actorForm.getUserAccountpassword());
			hacker.setUserAccount(account);
		} else {
			hacker = this.hackerRepository.findOne(actorForm.getId());
			hacker.setName(actorForm.getName());
			hacker.setSurname(actorForm.getSurname());
			hacker.setPhoto(actorForm.getPhoto());
			hacker.setPhone(actorForm.getPhone());
			hacker.setEmail(actorForm.getEmail());
			hacker.setAddress(actorForm.getAddress());
			hacker.setVat(actorForm.getVat());
			hacker.setVersion(actorForm.getVersion());
			final UserAccount account = this.userAccountService.findOne(hacker.getUserAccount().getId());
			account.setUsername(actorForm.getUserAccountuser());
			account.setPassword(actorForm.getUserAccountpassword());
			hacker.setUserAccount(account);
		}

		hacker.setCreditCard(c);

		this.validator.validate(hacker, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return hacker;
	}

	public Hacker findHackerByCurricula(int id) {
		Hacker result = this.hackerRepository.findHackerByCurricula(id);
		return result;
	}
	
	public Hacker findHackerByPersonalData(int id) {
		Hacker result = this.hackerRepository.findHackerByPersonalData(id);
		return result;
	}

	public Hacker findHackerByMiscellaneous(int id) {
		final Hacker result = this.hackerRepository.findHackerByMiscellaneous(id);
		return result;
	}
	
	public Hacker findHackerByEducationDatas(int id) {
		final Hacker result = this.hackerRepository.findHackerByEducationDatas(id);
		return result;
	}
	
	public Hacker findHackerByPositionDatas(int id) {
		final Hacker result = this.hackerRepository.findHackerByPositionDatas(id);
		return result;
	}

}
