
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.Authority;
import domain.Actor;
import domain.Answer;
import domain.Application;
import domain.Company;
import domain.Hacker;
import domain.Position;
import domain.Problem;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository	applicationRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private CompanyService			companyService;

	@Autowired
	private AnswerService			answerService;

	@Autowired
	private PositionService			positionService;

	@Autowired
	private HackerService			hackerService;


	public Application create() {
		final Application application = new Application();
		return application;
	}

	public Collection<Application> findAll() {
		Collection<Application> res = new ArrayList<>();
		final Actor principal = this.actorService.findByPrincipal();
		final Boolean isHacker = this.actorService.checkAuthority(principal, Authority.HACKER);
		final Boolean isCompany = this.actorService.checkAuthority(principal, Authority.COMPANY);

		if (isHacker)
			res = this.applicationRepository.findAllByHackerId(principal.getUserAccount().getId());
		else if (isCompany)
			res = this.applicationRepository.findAllByCompanyId(principal.getUserAccount().getId());
		//Si salta puede ser un Admin
		Assert.notNull(res);
		return res;
	}

	public Application findOne(final int applicationId) {
		Assert.isTrue(applicationId != 0);
		final Application res = this.applicationRepository.findOne(applicationId);
		Assert.notNull(res);
		return res;
	}

	public Application save(final Application application, final int positionId) {
		Assert.notNull(application);
		Assert.isTrue(positionId != 0);
		final Actor principal = this.actorService.findByPrincipal();
		final Position position = this.positionService.findOne(positionId);
		final Application result;
		final Boolean isCompany = this.actorService.checkAuthority(principal, Authority.COMPANY);
		final Boolean isHacker = this.actorService.checkAuthority(principal, Authority.HACKER);

		if (isHacker) {
			if (application.getId() == 0) {
				Assert.isTrue(isHacker);
				//Se asigna un problema aleatorio del conjunto de problemas que posee esa position.
				List<Problem> problems = new ArrayList<>();
				problems = (List<Problem>) position.getProblems();
				final Integer numRandom = (int) (Math.random() * (problems.size() - 1));
				final Problem assigned = problems.get(numRandom);
				application.setProblem(assigned);
				application.setStatus("PENDING");
				final Date moment = new Date(System.currentTimeMillis() - 1);
				application.setMoment(moment);
				application.setSubmitMoment(null);

			} else {
				Assert.isTrue(application.getStatus() == "PENDING", "No puede actualizar una solicitud que no esté en estado PENDING.");
				Assert.isTrue(application.getHacker() == principal, "No puede actualizar una solicitud que no le pertenece.");
				final Answer answer = this.answerService.create();
				this.answerService.save(answer, application.getId());
				application.setStatus("SUBMITTED");
				final Date submitMoment = new Date(System.currentTimeMillis() - 1);
				application.setSubmitMoment(submitMoment);
			}
		} else { //COMPANY
			Assert.isTrue(application.getPosition().getCompany() == this.companyService.findByPrincipal(), "No puede actualizar una solicitud que no le pertenece.");
			Assert.isTrue(application.getStatus() == "SUBMITTED");
		}
		result = this.applicationRepository.save(application);
		return result;
	}
	public void delete(final Application application) {
		Assert.notNull(application);
		Assert.isTrue(application.getId() != 0);
		Assert.isTrue(this.applicationRepository.exists(application.getId()));
		this.applicationRepository.delete(application);
	}

	/* ========================= OTHER METHODS =========================== */

	public Application acceptApplication(final int applicationId) {
		final Application application = this.findOne(applicationId);
		final Application result;
		final Company company = this.companyService.findByPrincipal();

		Assert.isTrue(application.getPosition().getCompany() == company, "No puede actualizar una solicitud que no le pertenece.");
		Assert.isTrue(application.getStatus().equals("SUBMITTED"), "No puede aceptar una solicitud que su estado sea distinto a Submitted");
		application.setStatus("ACCEPTED");
		result = this.applicationRepository.save(application);
		return result;
	}

	public Application rejectApplication(final int applicationId) {
		final Application application = this.findOne(applicationId);
		final Application result;
		final Company company = this.companyService.findByPrincipal();

		Assert.isTrue(application.getPosition().getCompany() == company, "No puede actualizar una solicitud que no le pertenece.");
		Assert.isTrue(application.getStatus().equals("SUBMITTED"), "No puede aceptar una solicitud que su estado sea distinto a Submitted");
		application.setStatus("REJECTED");
		result = this.applicationRepository.save(application);
		return result;
	}

	public Application apply(final int positionId) {
		final Position position = this.positionService.findOne(positionId);
		final Hacker hacker = this.hackerService.findByPrincipal();
		final Application application = this.create();

		final Application retreived = this.save(application, positionId);
		return retreived;
	}

	public Collection<Application> findAllSubmittedByCompany() {
		final Company principal = this.companyService.findByPrincipal();
		final Collection<Application> res = this.applicationRepository.findAllSubmittedByCompany(principal.getId());
		Assert.notNull(res);
		return res;
	}

	public Collection<Application> findAllAcceptedByCompany() {
		final Company principal = this.companyService.findByPrincipal();
		final Collection<Application> res = this.applicationRepository.findAllAcceptedByCompany(principal.getId());
		Assert.notNull(res);
		return res;
	}

	public Collection<Application> findAllRejectedByCompany() {
		final Company principal = this.companyService.findByPrincipal();
		final Collection<Application> res = this.applicationRepository.findAllRejectedByCompany(principal.getId());
		Assert.notNull(res);
		return res;
	}

	public Collection<Application> findAllPendingByHacker() {
		final Hacker principal = this.hackerService.findByPrincipal();
		final Collection<Application> res = this.applicationRepository.findAllPendingByHacker(principal.getId());
		Assert.notNull(res);
		return res;
	}

	public Collection<Application> findAllSubmittedByHacker() {
		final Hacker principal = this.hackerService.findByPrincipal();
		final Collection<Application> res = this.applicationRepository.findAllSubmittedByHacker(principal.getId());
		Assert.notNull(res);
		return res;
	}

	public Collection<Application> findAllAcceptedByHacker() {
		final Hacker principal = this.hackerService.findByPrincipal();
		final Collection<Application> res = this.applicationRepository.findAllAcceptedByHacker(principal.getId());
		Assert.notNull(res);
		return res;
	}

	public Collection<Application> findAllRejectedByHacker() {
		final Hacker principal = this.hackerService.findByPrincipal();
		final Collection<Application> res = this.applicationRepository.findAllRejectedByHacker(principal.getId());
		Assert.notNull(res);
		return res;
	}

}
