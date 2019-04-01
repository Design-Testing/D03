
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CompanyRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Company;

@Service
@Transactional
public class CompanyService {

	@Autowired
	private CompanyRepository	companyRepository;
	@Autowired
	private ActorService		actorService;


	//METODOS CRUD

	public Company create() {
		final Company company = new Company();
		final Collection<String> pictures = new ArrayList<>();
		company.setPictures(pictures);
		return company;
	}

	public Collection<Company> findAll() {
		final Collection<Company> companies = this.companyRepository.findAll();
		Assert.notNull(companies);
		return companies;
	}

	public Company findOne(final int companyId) {
		Assert.isTrue(companyId != 0);
		final Company company = this.companyRepository.findOne(companyId);
		Assert.notNull(company);
		return company;
	}

	public Company save(final Company company) {
		Assert.notNull(company);
		Company result;

		if (company.getId() == 0) {
			this.actorService.setAuthorityUserAccount(Authority.COMPANY, company);
			result = this.companyRepository.save(company);
		} else {
			final Actor principal = this.actorService.findByPrincipal();
			Assert.isTrue(principal.getId() == company.getId(), "You only can edit your info");
			result = (Company) this.actorService.save(company);
		}
		return company;
	}

	public void delete(final Company company) {
		Assert.notNull(company);
		Assert.isTrue(this.findByPrincipal().equals(company));
		Assert.isTrue(company.getId() != 0);
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(principal.getId() == company.getId(), "You only can edit your info");
		Assert.isTrue(this.companyRepository.exists(company.getId()));
		this.companyRepository.delete(company);
	}

	/* ========================= OTHER METHODS =========================== */

	public Company findByPrincipal() {
		final UserAccount user = LoginService.getPrincipal();
		Assert.notNull(user);

		final Company company = this.findByUserId(user.getId());
		Assert.notNull(company);
		final boolean bool = this.actorService.checkAuthority(company, Authority.COMPANY);
		Assert.isTrue(bool);

		return company;
	}

	public Company findByUserId(final int id) {
		Assert.isTrue(id != 0);
		final Company company = this.companyRepository.findByUserId(id);
		return company;
	}

	public Company findCompanyByProblem(final int problemId) {
		Company res;
		res = this.companyRepository.findCompanyByProblem(problemId);
		Assert.notNull(res);
		return res;
	}

	public Company findCompanyByPosition(final int positionId) {
		Company res;
		res = this.companyRepository.findCompanyByPosition(positionId);
		Assert.notNull(res);
		return res;
	}

	public void flush() {
		this.companyRepository.flush();
	}

}
