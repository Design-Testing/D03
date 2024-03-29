
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;
import domain.Hacker;
import domain.Position;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository				finderRepository;

	@Autowired
	private ConfigurationParametersService	configParamService;

	@Autowired
	private HackerService					hackerService;

	@Autowired
	private PositionService					positionService;


	/**
	 * The average, minimum, maximum and standard deviation of the number of positions per company
	 * 
	 * @author a8081
	 */
	public Double[] getStatisticsOfPositionsPerFinder() {
		final Double[] res = this.finderRepository.getStatisticsOfPositionsPerFinder();
		Assert.notNull(res);
		return res;
	}

	/**
	 * The ratio of empty versus non empty finders
	 * 
	 * @author a8081
	 * */
	public Double findRatioFinders() {
		return this.finderRepository.findRatioFinders();
	}

	//Metodos CRUD

	public Finder create() {
		final Finder finder = new Finder();
		finder.setKeyword("");
		finder.setMinSalary(null);
		finder.setMaxSalary(null);
		finder.setMinDeadline(null);
		finder.setMaxDeadline(null);
		final Collection<Position> ps = new ArrayList<Position>();
		finder.setCreationDate(new Date(System.currentTimeMillis()));
		finder.setPositions(ps);
		return finder;
	}

	public Collection<Finder> findAll() {
		final Collection<Finder> res = this.finderRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Finder findOne(final int finderId) {
		Assert.notNull(finderId);
		Assert.isTrue(finderId != 0);
		final Finder res = this.finderRepository.findOne(finderId);
		Assert.notNull(res);
		return res;
	}

	// Antes de guardar tengo que pasar por este metodo para setearle las nuevas procesiones segun los nuevos parametros
	public Finder find(final Finder finder) {
		this.hackerService.findByPrincipal();
		List<Position> result = new ArrayList<>(this.positionService.findPositions(finder.getKeyword(), finder.getMinSalary(), finder.getMaxSalary(), finder.getMinDeadline(), finder.getMaxDeadline()));
		final int maxResults = this.configParamService.find().getMaxFinderResults();
		if (result.size() > maxResults) {
			Collections.shuffle(result);
			result = result.subList(0, maxResults);
		}
		finder.setPositions(result);
		return this.save(finder);
	}

	public Finder save(final Finder finder) {
		final Hacker hacker = this.hackerService.findByPrincipal();
		Assert.notNull(finder);
		Assert.isTrue(finder.getId() != 0);
		Assert.isTrue(this.finderRepository.findHackerFinder(hacker.getId()).getId() == finder.getId(), "You're not owner of this finder, you cannot modify it");

		finder.setCreationDate(new Date(System.currentTimeMillis()));
		final Finder res = this.finderRepository.save(finder);
		Assert.notNull(res);

		hacker.setFinder(finder);
		this.hackerService.save(hacker);
		return res;
	}

	public Finder createForNewHacker() {
		final Finder finder = new Finder();
		finder.setKeyword("");
		finder.setMinSalary(null);
		finder.setMaxSalary(null);
		finder.setMinDeadline(null);
		finder.setMaxDeadline(null);
		final Collection<Position> ps = new ArrayList<Position>();
		finder.setCreationDate(new Date(System.currentTimeMillis()));
		finder.setPositions(ps);
		final Finder res = this.finderRepository.save(finder);
		return res;
	}

	public void delete(final Finder finder) {
		Assert.notNull(finder);
		final Hacker hacker = this.hackerService.findByPrincipal();
		Assert.isTrue(finder.getId() != 0);
		Assert.isTrue(this.finderRepository.exists(finder.getId()));
		Assert.isTrue(this.finderRepository.findHackerFinder(hacker.getId()).getId() == finder.getId(), "You're not owner of this finder, you cannot delete it");
		this.finderRepository.delete(finder);
	}

	public Finder findHackerFinder() {
		final Hacker principal = this.hackerService.findByPrincipal();

		final Finder finder = this.finderRepository.findHackerFinder(principal.getId());
		Assert.notNull(finder);

		// final int finderTime = this.configParamService.find().getFinderTime();
		// final LocalDateTime ldt = new LocalDateTime(finder.getCreationDate());
		// ldt.plusHours(finderTime);

		// if (ldt.isBefore(LocalDateTime.now()))
		if (this.clearCache(finder))
			this.clear(finder);

		return finder;
	}

	public Finder clear(final Finder finder) {
		final Hacker hacker = this.hackerService.findByPrincipal();
		final Finder result = this.finderRepository.findHackerFinder(hacker.getId());
		Assert.isTrue(result.equals(finder), "You're not owner of this finder");
		Assert.notNull(result);
		result.setKeyword("");
		result.setMinSalary(null);
		result.setMaxSalary(null);
		result.setMinDeadline(null);
		result.setMaxDeadline(null);
		finder.setCreationDate(new Date(System.currentTimeMillis()));
		result.setPositions(new ArrayList<Position>());
		final Finder saved = this.save(result);
		return saved;
	}

	public boolean clearCache(final Finder finder) {
		Assert.notNull(finder);

		final double update = finder.getCreationDate().getTime();
		final double current = new Date(System.currentTimeMillis()).getTime();
		final Double period = (current - update) / 3600000;
		final int max = this.configParamService.find().getFinderTime();

		return max <= period;
	}

	public void flush() {
		this.finderRepository.flush();

	}

}
