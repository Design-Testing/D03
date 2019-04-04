
package Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.CompanyService;
import services.PositionService;
import utilities.AbstractTest;
import domain.Position;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class PositionServiceTest extends AbstractTest {

	@Autowired
	private PositionService	positionService;

	@Autowired
	private CompanyService	companyService;


	@Test
	public void createAndSaveDriver() {

		final Object testingData[][] = {
			{
				"company1", "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, null
			}, {
				"hacker1", "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, IllegalArgumentException.class
			}, {
				null, "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, IllegalArgumentException.class
			}, {
				"company1", "", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, ConstraintViolationException.class
			}, {
				"company1", null, "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, ConstraintViolationException.class
			}, {
				"company1", "Title", "", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, ConstraintViolationException.class
			}, {
				"company1", "Title", null, "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", "", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", null, "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", "2019-12-12 20:00", "", new ArrayList<String>(), new ArrayList<String>(), 15000.0, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", "2019-12-12 20:00", null, new ArrayList<String>(), new ArrayList<String>(), 15000.0, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", "2019-12-12 20:00", "Profile", null, new ArrayList<String>(), 15000.0, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), null, 15000.0, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), -1.0, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), null, ConstraintViolationException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateSave((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Date) testingData[i][3], (String) testingData[i][4], (Collection<String>) testingData[i][5], (Collection<String>) testingData[i][6],
				(Double) testingData[i][7], (Class<?>) testingData[i][8]);

	}

	protected void templateCreateSave(final String user, final String title, final String description, final Date deadline, final String profile, final Collection<String> skills, final Collection<String> technologies, final Double salary,
		final Class<?> expected) {

		Class<?> caught = null;

		try {
			this.authenticate(user);
			final Position pos = this.positionService.create();
			pos.setTitle(title);
			pos.setDescription(description);
			pos.setDeadline(deadline);
			pos.setProfile(profile);
			pos.setSkills(skills);
			pos.setTechnologies(technologies);
			pos.setSalary(salary);
			final Position incRecSaved = this.positionService.save(pos);
			Assert.isTrue(incRecSaved.getId() != 0);
			this.positionService.flush();
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
				"company1", "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, false, null
			}, {
				"company1", "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, true, IllegalArgumentException.class
			}, {
				"hacker1", "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, false, IllegalArgumentException.class
			}, {
				null, "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, false, IllegalArgumentException.class
			}, {
				"company1", "", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, false, ConstraintViolationException.class
			}, {
				"company1", null, "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, false, ConstraintViolationException.class
			}, {
				"company1", "Title", "", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, false, ConstraintViolationException.class
			}, {
				"company1", "Title", null, "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, false, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", "", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, false, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", null, "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, false, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", "2019-12-12 20:00", "", new ArrayList<String>(), new ArrayList<String>(), 15000.0, false, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", "2019-12-12 20:00", null, new ArrayList<String>(), new ArrayList<String>(), 15000.0, false, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", "2019-12-12 20:00", "Profile", null, new ArrayList<String>(), 15000.0, false, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), null, 15000.0, false, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), -1.0, false, ConstraintViolationException.class
			}, {
				"company1", "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), null, false, ConstraintViolationException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEdit((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Date) testingData[i][3], (String) testingData[i][4], (Collection<String>) testingData[i][5], (Collection<String>) testingData[i][6],
				(Double) testingData[i][7], (Boolean) testingData[i][8], (Class<?>) testingData[i][9]);

	}

	protected void templateEdit(final String user, final String title, final String description, final Date deadline, final String profile, final Collection<String> skills, final Collection<String> technologies, final Double salary, final Boolean prop,
		final Class<?> expected) {
		Class<?> caught = null;
		try {
			this.authenticate(user);
			final Position pos;
			if (!prop) {
				final Integer id = this.getEntityId("position1");
				pos = this.positionService.findOne(id);
			} else {
				final Integer id = this.getEntityId("position2");
				pos = this.positionService.findOne(id);
			}
			pos.setTitle(title);
			pos.setDescription(description);
			pos.setDeadline(deadline);
			pos.setProfile(profile);
			pos.setSkills(skills);
			pos.setTechnologies(technologies);
			pos.setSalary(salary);
			this.positionService.save(pos);
			this.positionService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}

	@Test
	public void edit2() {

		final Object testingData[][] = {
			{
				"company1", "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, false, null
			}, {
				"company1", "Title", "Description", "2019-12-12 20:00", "Profile", new ArrayList<String>(), new ArrayList<String>(), 15000.0, true, IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEdit2((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Date) testingData[i][3], (String) testingData[i][4], (Collection<String>) testingData[i][5], (Collection<String>) testingData[i][6],
				(Double) testingData[i][7], (Boolean) testingData[i][8], (Class<?>) testingData[i][9]);

	}

	protected void templateEdit2(final String user, final String title, final String description, final Date deadline, final String profile, final Collection<String> skills, final Collection<String> technologies, final Double salary, final Boolean prop,
		final Class<?> expected) {
		Class<?> caught = null;
		try {
			this.authenticate(user);
			final Position pos;
			if (!prop) {
				final Integer id = this.getEntityId("position1");
				pos = this.positionService.findOne(id);
			} else {
				final Integer id = this.getEntityId("position2");
				pos = this.positionService.findOne(id);
			}
			pos.setTitle(title);
			pos.setDescription(description);
			pos.setDeadline(deadline);
			pos.setProfile(profile);
			pos.setSkills(skills);
			pos.setTechnologies(technologies);
			pos.setSalary(salary);
			this.positionService.save(pos);
			this.positionService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}
}
