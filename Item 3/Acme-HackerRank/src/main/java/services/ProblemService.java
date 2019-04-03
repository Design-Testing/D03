
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProblemRepository;
import security.Authority;
import domain.Company;
import domain.Position;
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
	@Autowired
	private MessageService		messageService;
	@Autowired
	private PositionService		positionService;


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

	public Problem save(final Problem problem, final int positionId) {
		Assert.notNull(problem);
		Assert.isTrue(positionId != 0);
		final Problem res;
		final Company company = this.companyService.findByPrincipal();
		this.actorService.checkAuthority(company, Authority.COMPANY);
		if (problem.getId() == 0) {
			final Position position = this.positionService.findOne(positionId);
			position.getProblems().add(problem);
			problem.setMode("DRAFT");
		} else {
			Assert.isTrue(problem.getMode().equals("DRAFT"), "No puedes modificar un problem que está en FINAL MODE");
			Assert.isTrue(problem.getCompany().equals(company), "No puede modificar un problem que no le pertenezca");
		}
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

	public Problem toFinalMode(final int problemId) {
		final Problem problem = this.findOne(problemId);
		final Problem result;
		final Company company = this.companyService.findByPrincipal();
		Assert.isTrue(problem.getCompany() == company, "Actor who want to edit parade mode to FINAL is not his owner");
		Assert.isTrue(problem.getMode().equals("DRAFT"), "To set final mode, parade must be in draft mode");
		problem.setMode("FINAL");
		result = this.problemRepository.save(problem);
		//		this.messageService.processionPublished(problem);
		return result;
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

	public Collection<Problem> findProblemByCompany(final int companyId) {
		Collection<Problem> res = new ArrayList<>();
		res = this.problemRepository.findProblemByCompany(companyId);
		Assert.notNull(res);
		return res;
	}

}
