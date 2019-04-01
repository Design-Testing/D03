
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PositionRepository;
import security.Authority;
import domain.Actor;
import domain.Company;
import domain.Position;
import domain.Problem;

@Service
@Transactional
public class PositionService {

	@Autowired
	private PositionRepository	positionRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CompanyService		companyService;


	public Position create() {
		final Position position = new Position();
		position.setProblems(new ArrayList<Problem>());
		position.setMode("DRAFT");
		final Company company = this.companyService.findByPrincipal();
		position.setCompany(company);
		position.setTicker(this.generateTicker(company.getCommercialName()));
		return position;
	}

	public Collection<Position> findAll() {
		final Collection<Position> result = this.positionRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Collection<Position> findAllFinalMode() {
		final Collection<Position> result = this.positionRepository.findAllFinalMode();
		Assert.notNull(result);
		return result;
	}

	public Collection<Position> findAllFinalModeByBrotherhood(final int id) {
		final Collection<Position> result = this.positionRepository.findAllFinalModeByCompany(id);
		Assert.notNull(result);
		return result;
	}

	public Position findOne(final int positionId) {
		Assert.isTrue(positionId != 0);
		final Position res = this.positionRepository.findOne(positionId);
		Assert.notNull(res);
		return res;
	}

	public Position save(final Position position) {
		Assert.notNull(position);
		final Company principal = this.companyService.findByPrincipal();
		Assert.isTrue(position.getCompany().equals(principal));
		final Position result;
		final Position last = this.findOne(position.getId());
		final ArrayList<Problem> problems = new ArrayList<Problem>(position.getProblems());
		Assert.isTrue(!last.getMode().equals("CANCELLED"), "This position can't be modified");
		if (position.getMode().equals("DRAFT"))
			Assert.isTrue(!last.getMode().equals("FINAL"), "This position can't be modified");
		if (position.getMode().equals("FINAL")) {
			Assert.isTrue(last.getMode().equals("DRAFT"), "This position can't be modified");
			Assert.isTrue(problems.size() >= 2, "Position must have 2 or more Problems associated.");
		}
		result = this.positionRepository.save(position);
		return result;
	}

	public void delete(final Position position) {
		Assert.notNull(position);
		Assert.isTrue(position.getId() != 0);
		final Position retrieved = this.findOne(position.getId());
		final Company principal = this.companyService.findByPrincipal();
		Assert.isTrue(retrieved.getCompany().equals(principal));
		this.positionRepository.delete(position);
	}

	public Collection<Position> findAllByPrincipal() {
		Collection<Position> res = new ArrayList<>();
		final Actor principal = this.actorService.findByPrincipal();
		final Boolean isCompany = this.actorService.checkAuthority(principal, Authority.COMPANY);
		res = this.positionRepository.findAllPositionByCompanyId(principal.getUserAccount().getId());
		Assert.notNull(res);
		return res;
	}

	private String generateTicker(final String companyName) {
		String res = "";
		final Integer n1 = (int) Math.floor(Math.random() * 9 + 1);
		final Integer n2 = (int) Math.floor(Math.random() * 9 + 1);
		final Integer n3 = (int) Math.floor(Math.random() * 9 + 1);
		final Integer n4 = (int) Math.floor(Math.random() * 9 + 1);
		final String word = companyName.substring(0, 4).toUpperCase();
		final String ticker = word + '-' + n1 + n2 + n3 + n4;
		res = ticker;

		if (this.hasDuplicate(res))
			this.generateTicker(companyName);
		return res;
	}

	private boolean hasDuplicate(final String ticker) {

		boolean res = true;
		try {
			if (this.positionRepository.getPositionWithTicker(ticker).isEmpty())
				res = false;
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return res;
	}
}
