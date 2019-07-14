
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProblemRepository;
import security.Authority;
import domain.Application;
import domain.Company;
import domain.Position;
import domain.Problem;

@Service
@Transactional
public class ProblemService {

	@Autowired
	private ProblemRepository	problemRepository;

	@Autowired
	private CompanyService		companyService;

	@Autowired
	private PositionService		positionService;

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private ActorService		actorService;


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
		final Company company = this.companyService.findByPrincipal();
		this.actorService.checkAuthority(company, Authority.COMPANY);
		if (problem.getId() == 0) {
			Assert.isTrue(problem.getPosition() == null);
			problem.setCompany(company);
			problem.setMode("DRAFT");
		} else {
			final Problem retrieved = this.findOne(problem.getId());
			if (problem.getPosition() != null) {
				final Position position = problem.getPosition();
				Assert.isTrue(retrieved.getPosition().equals(position), "position can not change");
			}
			Assert.isTrue(problem.getMode().equals("DRAFT"), "Not possible to modify a problem in final mode");
			Assert.isTrue(problem.getCompany().equals(company), "You can not modify a problem taht is not yours");

		}
		res = this.problemRepository.save(problem);
		return res;
	}

	public void asign(final Problem problem, final int positionId) {
		Assert.notNull(problem);
		Assert.isTrue(positionId != 0);
		Assert.isTrue(problem.getPosition() == null, "The problem has already been asigned to a position");
		final Company company = this.companyService.findByPrincipal();
		this.actorService.checkAuthority(company, Authority.COMPANY);
		Assert.isTrue(problem.getId() != 0);
		Assert.isTrue(problem.getMode().equals("FINAL"));
		Assert.isTrue(problem.getCompany().equals(company), "No puede modificar un problem que no le pertenezca");
		final Position position = this.positionService.findOne(positionId);
		Assert.isTrue(position.getMode().equals("DRAFT"));
		final Collection<Position> positions = this.positionService.findAllByCompany();
		Assert.isTrue(positions.contains(position));
		problem.setPosition(position);
		problem.setCompany(company);
		this.problemRepository.save(problem);
	}

	public void unasign(final Problem problem, final int positionId) {
		Assert.notNull(problem);
		Assert.isTrue(positionId != 0);
		Assert.isTrue(problem.getPosition() != null, "The problem is not assigned to any position");
		final Company company = this.companyService.findByPrincipal();
		this.actorService.checkAuthority(company, Authority.COMPANY);
		Assert.isTrue(problem.getId() != 0);
		Assert.isTrue(problem.getMode().equals("FINAL"));
		Assert.isTrue(problem.getCompany().equals(company), "No puede modificar un problem que no le pertenezca");
		final Position position = this.positionService.findOne(positionId);
		Assert.isTrue(position.getMode().equals("DRAFT"));
		final Collection<Position> positions = this.positionService.findAllByCompany();
		Assert.isTrue(positions.contains(position));
		problem.setPosition(null);
		problem.setCompany(company);
		this.problemRepository.save(problem);

	}

	public void delete(final Problem problem) {
		Assert.notNull(problem);
		Assert.isTrue(problem.getId() != 0);
		final Company company = this.companyService.findByPrincipal();
		Assert.isTrue(company.equals(problem.getCompany()), "No puedes borrar un problema que no le pertenezca.");
		final Problem retrieved = this.findOne(problem.getId());
		Assert.isTrue(retrieved.equals(problem));
		Assert.isTrue(this.problemRepository.exists(problem.getId()));
		final Collection<Application> applications = this.applicationService.findAllByProblem(retrieved.getId());
		Assert.isTrue(applications.isEmpty(), "No puedes borrar este problema, ya que tiene solicitudes asociadas.");
		if (problem.getPosition() != null)
			Assert.isTrue(!problem.getPosition().getMode().equals("FINAL"), "Not possible to delete a problem associated to a position in final mode");
		this.problemRepository.delete(retrieved.getId());

	}

	public Problem toFinalMode(final int problemId) {
		final Problem problem = this.findOne(problemId);
		final Problem result;
		final Company company = this.companyService.findByPrincipal();
		Assert.isTrue(problem.getCompany() == company, "Actor who want to edit parade mode to FINAL is not his owner");
		Assert.isTrue(problem.getMode().equals("DRAFT"), "To set final mode, parade must be in draft mode");
		problem.setMode("FINAL");
		result = this.problemRepository.save(problem);
		return result;
	}

	public Problem findProblemByApplication(final int applicationId) {
		Problem res;
		res = this.problemRepository.findProblemByApplication(applicationId);
		Assert.notNull(res);
		return res;
	}

	public Collection<Problem> findProblemsByPosition(final int positionId) {
		Collection<Problem> res = new ArrayList<>();
		res = this.problemRepository.findProblemsByPosition(positionId);
		Assert.notNull(res);
		return res;
	}

	public Collection<Problem> findFinalProblemsByPosition(final int positionId) {
		Collection<Problem> res = new ArrayList<>();
		res = this.problemRepository.findFinalProblemsByPosition(positionId);
		Assert.notNull(res);
		return res;
	}

	public Collection<Problem> findProblemByCompany() {
		final Company company = this.companyService.findByPrincipal();
		Collection<Problem> res = new ArrayList<>();
		res = this.problemRepository.findProblemsByCompany(company.getUserAccount().getId());
		Assert.notNull(res);
		return res;
	}

	public Collection<Problem> findProblemsByPositionAndHacker(final Integer positionId, final Integer hackerUAId) {
		Collection<Problem> res = new ArrayList<>();
		res = this.problemRepository.findProblemsByPositionAndHacker(positionId, hackerUAId);
		return res;
	}

	public void deleteInBatch(final Collection<Problem> problems) {
		this.problemRepository.deleteInBatch(problems);
	}

	public void flush() {
		this.problemRepository.flush();
	}

	public Collection<Problem> findFinalNotAsignedProblemsByCompany() {
		Collection<Problem> res = new ArrayList<>();
		res = this.problemRepository.findFinalNotAsignedProblemsByCompany(this.companyService.findByPrincipal().getUserAccount().getId());
		return res;
	}

	public Collection<Problem> findAllByHacker(final int hackerId) {
		final Collection<Problem> res = this.problemRepository.findAllByHacker(hackerId);
		Assert.notNull(res);
		return res;
	}

}
