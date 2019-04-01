
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnswerRepository;
import security.Authority;
import domain.Actor;
import domain.Answer;
import domain.Application;
import domain.Hacker;

@Service
@Transactional
public class AnswerService {

	@Autowired
	private AnswerRepository	answerRepository;

	@Autowired
	private HackerService		hackerService;

	@Autowired
	private ApplicationService	applicationService;


	public Answer create() {
		final Answer answer = new Answer();
		return answer;
	}

	public Collection<Answer> findAll() {
		Collection<Answer> res = new ArrayList<>();
		final Actor principal = this.actorService.findByPrincipal();
		final Boolean isHacker = this.actorService.checkAuthority(principal, Authority.HACKER);
		final Boolean isCompany = this.actorService.checkAuthority(principal, Authority.COMPANY);

		if (isHacker)
			res = this.answerRepository.findAllByHackerId(principal.getUserAccount().getId());
		else if (isCompany)
			res = this.answerRepository.findAllByCompanyId(principal.getUserAccount().getId());
		//Si salta puede ser un Admin
		Assert.notNull(res);
		return res;
	}

	public Answer findOne(final int answerId) {
		Assert.isTrue(answerId != 0);
		final Answer res = this.answerRepository.findOne(answerId);
		Assert.notNull(res);
		return res;
	}

	public Answer save(final Answer answer, final int applicationId) {
		Assert.notNull(answer);
		Assert.isTrue(applicationId != 0);
		Answer result;
		final Hacker hacker = this.hackerService.findByPrincipal();
		final Application application = this.applicationService.findOne(applicationId);

		if (answer.getId() == 0)
			application.setAnswer(answer);
		else
			Assert.isTrue(application.getHacker().equals(hacker));
		//		Assert.isTrue(answer.getExplanation() == null, "Debe explicar la solución que ofrece.");
		result = this.answerRepository.save(answer);
		return result;
	}
	public void delete(final Answer answer) {
		Assert.notNull(answer);
		Assert.isTrue(answer.getId() != 0);
		Assert.isTrue(this.answerRepository.exists(answer.getId()));
		this.answerRepository.delete(answer);
	}

	/* ========================= OTHER METHODS =========================== */

}
