
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.HackerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Hacker;

@Service
@Transactional
public class HackerService {

	@Autowired
	private HackerRepository	hackerRepository;

	@Autowired
	private ActorService		actorService;


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
			this.actorService.setAuthorityUserAccount(Authority.HACKER, hacker);
			result = this.hackerRepository.save(hacker);
			//			this.folderService.setFoldersByDefault(result);

		} else {
			this.actorService.checkForSpamWords(brotherhood);
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

	public void flush() {
		this.hackerRepository.flush();
	}

}
