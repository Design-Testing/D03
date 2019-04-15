
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculaRepository;

@Service
@Transactional
public class CurriculaService {

	@Autowired
	private CurriculaRepository	curriculaRepository;


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

}
