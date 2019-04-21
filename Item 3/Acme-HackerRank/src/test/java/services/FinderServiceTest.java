
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
	"classpath:spring/junit.xml"
})
@Transactional
public class FinderServiceTest extends AbstractTest {

	@Autowired
	private FinderService	finderService;


	@Test
	public void driverCreateAndSaveFinder() {
		final Object testingData[][] = {
			{
				//				A: Acme HackerRank R.17.2. Manage finder
				//				B: Test Positivo: Un hacker puede actualizar su finder (todos los parametros de busqueda vacios)
				//				C: % Recorre 31 de las 31 lineas posibles
				//				D: % cobertura de datos= 8/32
				"hacker1", "", "", "", "", "", new ArrayList<Position>(), null
			}, {
				//				A: Acme HackerRank R.17.2. Manage finder
				//				B: Test Positivo: Un hacker puede actualizar su finder (todos los parametros de busqueda vacios menos keyword)
				//				C: % Recorre 31 de las 31 lineas posibles
				//				D: % cobertura de datos= 8/32
				"hacker1", "jotaunit", "", "", "", "", new ArrayList<Position>(), null
			}, {
				//				A: Acme HackerRank R.17.2. Manage finder
				//				B: Test Positivo: Un hacker puede actualizar su finder (todos los parametros de busqueda vacios menos min salary)
				//				C: % Recorre 31 de las 31 lineas posibles
				//				D: % cobertura de datos= 8/32
				"hacker1", "", "1000.0", "", "", "", new ArrayList<Position>(), null
			}, {
				//				A: Acme HackerRank R.17.2. Manage finder
				//				B: Test Positivo: Un hacker puede actualizar su finder (todos los parametros de busqueda vacios menos max salary)
				//				C: % Recorre 31 de las 31 lineas posibles
				//				D: % cobertura de datos= 8/32
				"hacker1", "", "", "1500.0", "", "", new ArrayList<Position>(), null
			}, {
				//				A: Acme HackerRank R.17.2. Manage finder
				//				B: Test Positivo: Un hacker puede actualizar su finder (todos los parametros de busqueda vacios menos min deadline)
				//				C: % Recorre 31 de las 31 lineas posibles
				//				D: % cobertura de datos= 8/32
				"hacker1", "", "", "", "2019-02-02 20:00", "", new ArrayList<Position>(), null
			}, {
				//				A: Acme HackerRank R.17.2. Manage finder
				//				B: Test Positivo: Un hacker puede actualizar su finder (todos los parametros de busqueda vacios menos max deadline)
				//				C: % Recorre 31 de las 31 lineas posibles
				//				D: % cobertura de datos= 8/32
				"hacker1", "", "", "", "", "2019-02-02 20:00", new ArrayList<Position>(), null
			}, {
				//				A: Acme HackerRank R.17.2. Manage finder
				//				B: Test Positivo: Un hacker puede actualizar su finder (todos los parametros de busqueda)
				//				C: % Recorre 31 de las 31 lineas posibles
				//				D: % cobertura de datos= 8/32
				"hacker1", "jotaunit", "200.0", "1000.0", "2019-02-02 10:00", "2019-02-02 20:00", new ArrayList<Position>(), null
			}, {
				//				A: Acme HackerRank R.17.2. Manage finder
				//				B: Test Negativo: Un hacker puede actualizar su finder. Ningun actor ajeno a el puede hacerlo
				//				C: % Recorre 6 de las 31 lineas posibles
				//				D: % cobertura de datos= 8/32
				"company1", "jotaunit", "200.0", "1000.0", "2019-02-02 10:00", "2019-02-02 20:00", new ArrayList<Position>(), IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateCreateAndSave((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Collection<Position>) testingData[i][6],
				(Class<?>) testingData[i][7]);
	}
	private void templateCreateAndSave(final String principal, final String keyword, final String minSalaryS, final String maxSalaryS, final String minDeadlineS, final String maxDeadlineS, final Collection<Position> positions, final Class<?> expected) {

		Class<?> caught = null;
		try {
			this.authenticate(principal);
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

			final Finder finder = this.finderService.create();
			finder.setKeyword(keyword);
			finder.setPositions(positions);
			finder.setMinSalary(minSalary);
			finder.setMaxSalary(maxSalary);
			finder.setMinDeadline(minDeadline);
			finder.setMaxDeadline(maxDeadline);
			this.finderService.save(finder);
			this.finderService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();

		}

		super.checkExceptions(expected, caught);
	}

}
