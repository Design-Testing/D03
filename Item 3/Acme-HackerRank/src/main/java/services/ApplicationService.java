
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.Authority;
import domain.Actor;
import domain.Company;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository	applicationRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private CompanyService			companyService;


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

	public Application save(final Application application) {
		Assert.notNull(application);
		final Actor principal = this.actorService.findByPrincipal();
		final Application result;
		final Boolean isCompany = this.actorService.checkAuthority(principal, Authority.COMPANY);
		final Boolean isHacker = this.actorService.checkAuthority(principal, Authority.HACKER);

		if (isHacker) {
			if (application.getId() == 0) {
				Assert.isTrue(isHacker);
				//TODO: Asignar un problema aleatorio
				application.setStatus("PENDING");

			} else {
				Assert.isTrue(application.getStatus() == "PENDING");
				Assert.isTrue(application.getHacker() == principal, "No puede actualizar una solicitud que no le pertenece.");
				application.setStatus("SUBMITTED");
				//TODO: Falta meterle el answer de la application
			}
		} else { //COMPANY
			Assert.isTrue(application.getPosition().getCompany() == principal, "No puede actualizar una solicitud que no le pertenece.");
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
		Assert.isTrue(application.getMode().equals("FINAL"), "La solicitud que desea aceptar no ha sido guardada en modo final.");
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
		Assert.isTrue(application.getMode().equals("FINAL"), "La solicitud que desea aceptar no ha sido guardada en modo final.");
		Assert.isTrue(application.getStatus().equals("SUBMITTED"), "No puede aceptar una solicitud que su estado sea distinto a Submitted");
		application.setStatus("REJECTED");
		result = this.applicationRepository.save(application);
		return result;
	}

}
