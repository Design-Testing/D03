
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PositionRepository;
import domain.Actor;
import domain.Company;
import domain.Position;
import domain.Problem;
import forms.PositionForm;

@Service
@Transactional
public class PositionService {

	@Autowired
	private PositionRepository	positionRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CompanyService		companyService;

	@Autowired
	private Validator			validator;


	public Position create() {
		final Position position = new Position();
		position.setProblems(new ArrayList<Problem>());
		final Company company = this.companyService.findByPrincipal();
		position.setCompany(company);
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

	public Collection<Position> findAllFinalModeByCompany(final int id) {
		final Collection<Position> result = this.positionRepository.findAllFinalModeByCompany(id);
		Assert.notNull(result);
		return result;
	}

	public Collection<Position> findAllByCompany() {
		final Company company = this.companyService.findByPrincipal();
		final Collection<Position> result = this.positionRepository.findAllByCompany(company.getId());
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
		final Position result;
		if (position.getId() != 0) {
			Assert.isTrue(position.getCompany().equals(principal));
			Assert.isTrue(position.getMode().equals("DRAFT"), "This position can't be modified");
		} else {
			position.setMode("DRAFT");
			final String ticker = this.generateTicker(position.getCompany().getCommercialName());
			position.setTicker(ticker);
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

	public Collection<Position> findPositions(final String key) {
		final Collection<Position> res = this.positionRepository.findPositions(key);
		Assert.notNull(res);
		return res;
	}

	public Position toFinalMode(final int positionId) {
		final Position position = this.findOne(positionId);
		final Position result;
		Assert.isTrue(position.getProblems().size() >= 2, "Position must have 2 or more Problems associated.");
		position.setMode("FINAL");
		result = this.save(position);
		return result;
	}

	public Position toCancelMode(final int positionId) {
		final Position position = this.findOne(positionId);
		final Position result;
		Assert.isTrue(position.getMode().equals("FINAL"), "Para poner una posición en CANCELLED MODE debe de estar en FINAL MODE.");
		position.setMode("CANCELLED");
		result = this.positionRepository.save(position);
		return result;
	}

	public Position reconstruct(final PositionForm positionForm, final BindingResult binding) {
		Position result;

		Assert.isTrue(positionForm.getId() != 0);

		result = this.findOne(positionForm.getId());

		result.setId(positionForm.getId());
		result.setVersion(positionForm.getVersion());
		result.setTitle(positionForm.getTitle());
		result.setDescription(positionForm.getDescription());
		result.setProfile(positionForm.getProfile());
		result.setDeadline(positionForm.getDeadline());
		result.setSkills(positionForm.getSkills());
		result.setTechnologies(positionForm.getTechnologies());
		result.setSalary(positionForm.getSalary());

		this.validator.validate(result, binding);

		return result;
	}

	public void flush() {
		this.positionRepository.flush();
	}
}
