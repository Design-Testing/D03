
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository	finderRepository;


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

}
