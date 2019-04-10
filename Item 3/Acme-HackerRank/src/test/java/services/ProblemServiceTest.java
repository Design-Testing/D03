
package Services;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.PositionService;
import services.ProblemService;
import utilities.AbstractTest;
import domain.Problem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ProblemServiceTest extends AbstractTest {

	@Autowired
	private ProblemService	problemService;

	@Autowired
	private PositionService	positionService;


	@Test
	public void createAndSaveDriver() {

		final Object testingData[][] = {
			{
				"company1", "Title", "Statement for problem", "hint for problem", "position1", null
			}, {
				"hacker1", "Title", "Statement for problem", "hint for problem", "position1", IllegalArgumentException.class
			}, {
				null, "Title", "Statement for problem", "hint for problem", "position1", IllegalArgumentException.class
			}, {
				"company1", "", "Statement for problem", "hint for problem", "position1", ConstraintViolationException.class
			}, {
				"company1", null, "Statement for problem", "hint for problem", "position1", ConstraintViolationException.class
			}, {
				"company1", "Title", "", "hint for problem", "position1", ConstraintViolationException.class
			}, {
				"company1", "Title", null, "hint for problem", "position1", ConstraintViolationException.class
			}, {
				"company1", "Title", "Statement for problem", "hint for problem", "position2", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateSave((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Class<?>) testingData[i][5]);

	}

	protected void templateCreateSave(final String user, final String title, final String statement, final String hint, final String position, final Class<?> expected) {

		Class<?> caught = null;

		try {
			this.authenticate(user);
			final Problem problem = this.problemService.create();
			problem.setTitle(title);
			problem.setStatement(statement);
			problem.setHint(hint);
			final Integer posId = this.getEntityId(position);
			final Problem saved = this.problemService.save(problem, posId);
			Assert.isTrue(saved.getId() != 0);
			this.problemService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}
	@Test
	public void edit() {

		final Object testingData[][] = {
			{
				"company1", "Title", "Statement for problem", "hint for problem", "problem1", "position1", null
			}, {
				"hacker1", "Title", "Statement for problem", "hint for problem", "problem1", "position1", IllegalArgumentException.class
			}, {
				null, "Title", "Statement for problem", "hint for problem", "problem1", "position1", IllegalArgumentException.class
			}, {
				"company1", "", "Statement for problem", "hint for problem", "problem1", "position1", ConstraintViolationException.class
			}, {
				"company1", null, "Statement for problem", "hint for problem", "problem1", "position1", ConstraintViolationException.class
			}, {
				"company1", "Title", "", "hint for problem", "problem1", "position1", ConstraintViolationException.class
			}, {
				"company1", "Title", null, "hint for problem", "problem1", "position1", ConstraintViolationException.class
			}, {
				"company1", "Title", "Statement for problem", "hint for problem", "problem3", "position2", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEdit((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Class<?>) testingData[i][6]);

	}

	protected void templateEdit(final String user, final String title, final String statement, final String hint, final String problemTest, final String position, final Class<?> expected) {
		Class<?> caught = null;
		try {
			this.authenticate(user);
			final Problem problem = this.problemService.findOne(this.getEntityId(problemTest));
			problem.setTitle(title);
			problem.setStatement(statement);
			problem.setHint(hint);
			final Integer posId = this.getEntityId(position);
			final Problem saved = this.problemService.save(problem, posId);
			Assert.isTrue(saved.getId() != 0);
			this.problemService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}

	@Test
	public void driverDelete() {

		final Object testingData[][] = {
			//			{
			//				//			A: Acme Parade Req. 3 -> Brotherhoods can manage their history
			//				//			B: Test Positivo: Brotherhood borra LegalRecord 
			//				//			C: 100% Recorre 78 de las 78 lineas posibles
			//				//			D: cobertura de datos=1/3
			//				"company1", null
			//			}, 
			{
				//			A: Acme Parade Req. 3 -> Brotherhoods can manage their history
				//			B: Test Negativo: Member intenta borrar LegalRecord 
				//			C: 10,25% Recorre 8 de las 78 lineas posibles
				//			D: cobertura de datos=1/3
				"hacker1", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDelete((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	private void templateDelete(final String actor, final Class<?> expected) {
		Class<?> caught = null;
		try {
			this.authenticate(actor);
			final Integer id = this.getEntityId("problem1");
			final Problem problem = this.problemService.findOne(id);
			this.problemService.delete(problem);
			this.problemService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}

}
