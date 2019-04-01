
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

@Service
@Transactional
public class AnswerService {

	@Autowired
	private AnswerRepository	answerRepository;


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
}
