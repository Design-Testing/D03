
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculaRepository;
import domain.Curricula;
import domain.EducationData;
import domain.Hacker;
import domain.MiscellaneousData;
import domain.PersonalData;
import domain.PositionData;

@Service
@Transactional
public class CurriculaService {

	@Autowired
	private CurriculaRepository	curriculaRepository;

	@Autowired
	private HackerService		hackerService;

	@Autowired
	private PersonalDataService	personalDataService;


	public Curricula create() {
		final Curricula curricula = new Curricula();
		final Hacker hacker = this.hackerService.findByPrincipal();

		final PersonalData personalData = this.personalDataService.create();
		personalData.setFullName(hacker.getName());
		personalData.setStatement("Statement " + hacker.getName());
		curricula.setPersonalRecord(personalData);

		final Collection<PositionData> positionData = new ArrayList<PositionData>();
		curricula.setPositions(positionData);

		final Collection<MiscellaneousData> miscellaneousData = new ArrayList<MiscellaneousData>();
		curricula.setMiscellaneous(miscellaneousData);

		final Collection<EducationData> educationData = new ArrayList<EducationData>();
		curricula.setEducations(educationData);

		return curricula;

	}

	public Collection<Curricula> findAll() {
		Collection<Curricula> res = new ArrayList<>();
		res = this.curriculaRepository.findAll();
		Assert.notNull(res, "Find all returns a null collection");
		return res;
	}

	public Curricula findOne(final int curriculaId) {
		Assert.isTrue(curriculaId != 0);
		final Curricula res = this.curriculaRepository.findOne(curriculaId);
		Assert.notNull(res);
		return res;
	}

	public Curricula save(final Curricula curricula) {
		Assert.notNull(curricula);
		final Curricula res;
		final Hacker hacker = this.hackerService.findByPrincipal();
		if (curricula.getId() != 0)
			Assert.isTrue(this.hackerService.findHackerByCurricula(curricula.getId()).equals(hacker));
		else
			curricula.setHacker(hacker);
		res = this.curriculaRepository.save(curricula);
		return res;
	}

	public void delete(final Curricula curricula) {
		Assert.notNull(curricula);
		Assert.isTrue(curricula.getId() != 0);
		final Hacker hacker = this.hackerService.findByPrincipal();
		final Curricula retrieved = this.findOne(curricula.getId());
		Assert.isTrue(this.hackerService.findHackerByCurricula(retrieved.getId()) == hacker, "Not possible to delete the curricula of other hacker.");
		this.curriculaRepository.delete(retrieved.getId());
	}

	/**
	 * The average, minimum, maximum and standard deviation of the number of curricula per hacker
	 * 
	 * @author a8081
	 */
	public Double[] getStatisticsOfCurriculaPerHacker() {
		final Double[] res = this.curriculaRepository.getStatisticsOfCurriculaPerHacker();
		Assert.notNull(res);
		return res;
	}

	public Collection<Curricula> findCurriculaByHacker(final int id) {
		final Collection<Curricula> result = this.curriculaRepository.findCurriculaByHacker(id);
		return result;
	}

	public Curricula findCurriculaByPersonalData(final int id) {
		final Curricula result = this.curriculaRepository.findCurriculaByPersonalData(id);
		Assert.notNull(result, "findCurriculaByPersonalData returns null");
		return result;
	}

	public Curricula findCurriculaByEducationData(final int id) {
		final Curricula result = this.curriculaRepository.findCurriculaByEducationData(id);
		Assert.notNull(result, "findCurriculaByEducationData returns null");
		return result;
	}

	public Curricula findCurriculaByPositionData(final int id) {
		final Curricula result = this.curriculaRepository.findCurriculaByPositionData(id);
		Assert.notNull(result, "findCurriculaByPositionData returns null");
		return result;
	}

	public Curricula findCurriculaByMiscellaneousData(final int id) {
		final Curricula result = this.curriculaRepository.findCurriculaByMiscellaneousData(id);
		Assert.notNull(result, "findCurriculaByMiscellanousData returns null");
		return result;
	}

}
