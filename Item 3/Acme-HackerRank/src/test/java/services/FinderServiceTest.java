
package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Finder;
import domain.Position;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FinderServiceTest extends AbstractTest {

	@Autowired
	private FinderService	finderService;


	@Test
	public void driverCreateAndSaveFinder() {
		final Collection<Position> positions = new ArrayList<>();
		final Object testingData[][] = {};
		//		for (final int i = 0; i < testingData.length; i++)
		//			this.templateCreateAndSave((String) testingData[i][0], (String) testingData[i][1], (Collection<Position>) testingData[i][2], (Class<?>) testingData[i][8]);
	}
	private void templateCreateAndSave(final String keyword, final String minSalaryS, final String maxSalaryS, final String minDeadlineS, final String maxDeadlineS, final Collection<Position> positions, final Class<?> expected) {

		Class<?> caught;
		final Finder finder;

		caught = null;

		try {
			final Double minSalary = new Double(minSalaryS);
			final Double maxSalary = new Double(maxSalaryS);

			Date minDeadline;
			if (minDeadlineS != null)
				minDeadline = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(minDeadlineS);
			else
				minDeadline = null;

			Date maxDeadline;

			if (maxDeadlineS != null)
				maxDeadline = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).parse(maxDeadlineS);
			else
				maxDeadline = null;

			finder = this.finderService.create();
			finder.setKeyword(keyword);
			finder.setPositions(positions);
			finder.setMinSalary(minSalary);
			finder.setMaxSalary(maxSalary);
			finder.setMinDeadline(minDeadline);
			finder.setMaxDeadline(maxDeadline);
			this.finderService.save(finder);
			this.finderService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

}
