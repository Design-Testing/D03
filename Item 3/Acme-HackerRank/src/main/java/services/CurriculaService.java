
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
	private CurriculaRepository			curriculaRepository;

	@Autowired
	private HackerService				hackerService;

	@Autowired
	private PersonalDataService			personalDataService;

	@Autowired
	private EducationDataService		educationDataService;

	@Autowired
	private PositionDataService			positionDataService;

	@Autowired
	private MiscellaneousDataService	miscellaneousDataService;


	public Curricula create() {
		final Curricula curricula = new Curricula();
		final Hacker hacker = this.hackerService.findByPrincipal();

		final PersonalData personalData = this.personalDataService.create();

		personalData.setFullName(hacker.getName());
		personalData.setStatement("Statement " + hacker.getName());
		personalData.setPhone(hacker.getPhone());

		curricula.setPersonalRecord(personalData);

		final Collection<PositionData> positionData = new ArrayList<PositionData>();
		curricula.setPositions(positionData);

		final Collection<MiscellaneousData> miscellaneousData = new ArrayList<MiscellaneousData>();
		curricula.setMiscellaneous(miscellaneousData);

		final Collection<EducationData> educationData = new ArrayList<EducationData>();
		curricula.setEducations(educationData);

		return curricula;

	}

	public Curricula createForNewHacker() {
		final Curricula curricula = new Curricula();

		final PersonalData personalData = this.personalDataService.create();

		personalData.setFullName("full name");
		personalData.setStatement("statement");

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
		System.out.println(curricula);
		Assert.notNull(curricula);
		final Curricula res;
		final Hacker hacker = this.hackerService.findByPrincipal();
		System.out.println("hhhhhhhhh");
		if (curricula.getId() != 0) {
			System.out.println("rrrrrrrrrrrrrr");
			System.out.println(this.hackerService.findHackerByCurricula(curricula.getId()));
			System.out.println(hacker);
			Assert.isTrue(this.hackerService.findHackerByCurricula(curricula.getId()).equals(hacker), "logged actor doesnt match curricula's owner");
			System.out.println("wwwwwwwwwww");
		} else {
			System.out.println("rrrrrrrr");

			curricula.setHacker(hacker);
			System.out.println("kkkkkkkk");
		}
		System.out.println("aquiiiii");
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

	public Curricula makeCopyAndSave(final Curricula curricula) {
		Curricula result = this.create();
		result.setHacker(curricula.getHacker());

		System.out.println("aaaa");
		final PersonalData pd = this.personalDataService.makeCopyAndSave(curricula.getPersonalRecord());
		result.setPersonalRecord(pd);
		System.out.println("bbb");
		final Collection<EducationData> eds = new ArrayList<EducationData>();
		result.setEducations(eds);
		final Collection<MiscellaneousData> mds = new ArrayList<MiscellaneousData>();
		result.setMiscellaneous(mds);
		final Collection<PositionData> pds = new ArrayList<PositionData>();
		result.setPositions(pds);
		result = this.save(result);

		for (final EducationData ed : curricula.getEducations())
			eds.add(this.educationDataService.makeCopyAndSave(ed, result));
		result.setEducations(eds);
		System.out.println("eeee");

		for (final MiscellaneousData md : curricula.getMiscellaneous())
			mds.add(this.miscellaneousDataService.makeCopyAndSave(md, result));
		result.setMiscellaneous(mds);
		System.out.println("ffff");

		for (final PositionData pod : curricula.getPositions())
			pds.add(this.positionDataService.makeCopyAndSave(pod, result));
		result.setPositions(pds);

		System.out.println("mmmmmmmm");

		result.setHacker(null);
		Assert.notNull(result, "copy of curricula is null");
		System.out.println(this.hackerService.findHackerByCurricula(curricula.getId()));
		result = this.curriculaRepository.save(result);
		Assert.notNull(result, "retrieves copy curricula is null");

		return result;
	}
}
