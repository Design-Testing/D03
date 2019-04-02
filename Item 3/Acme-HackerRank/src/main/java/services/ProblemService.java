
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProblemRepository;
import domain.Actor;
import domain.Company;
import domain.Problem;

@Service
@Transactional
public class ProblemService {

	@Autowired
	private ProblemRepository	problemRepository;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private CompanyService		companyService;


	//Metodos CRUD

	public Problem create() {
		final Problem problem = new Problem();
		final Company company = new Company();
		final Collection<String> attachments = new ArrayList<>();
		problem.setCompany(company);
		problem.setAttachments(attachments);
		return problem;
	}

	public Collection<Problem> findAll() {
		final Collection<Problem> problems = this.problemRepository.findAll();
		Assert.notNull(problems);
		return problems;
	}

	public Problem findOne(final int problemId) {
		Assert.isTrue(problemId != 0);
		final Problem result = this.problemRepository.findOne(problemId);
		Assert.notNull(result);
		return result;
	}

	public Problem save(final Problem problem) {
		Assert.notNull(problem);
		final Problem res;
		final Actor principal = this.actorService.findByPrincipal();
		final Company company = this.companyService.findByPrincipal();
		Assert.isTrue(principal.equals(company));
		if (problem.getId() == 0)
			problem.setMode("DRAFT");
		else
			Assert.isTrue(problem.getMode().equals("DRAFT"));

		res = this.problemRepository.save(problem);
		return res;
	}
	public void delete(final Problem problem) {
		Assert.notNull(problem);
		Assert.isTrue(problem.getId() != 0);
		final Problem retrieved = this.findOne(problem.getId());
		Assert.isTrue(retrieved.equals(problem));
		Assert.isTrue(this.problemRepository.exists(problem.getId()));
		this.problemRepository.delete(problem.getId());

	}

	public Problem findProblemByApplication(final int applicationId) {
		Problem res;
		res = this.problemRepository.findProblemByApplication(applicationId);
		Assert.notNull(res);
		return res;
	}

	public Collection<Problem> findProblemByPosition(final int positionId) {
		Collection<Problem> res = new ArrayList<>();
		res = this.problemRepository.findProblemByPosition(positionId);
		Assert.notNull(res);
		return res;
	}

}
