
package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Curricula;
import domain.Hacker;
import domain.PersonalData;
import domain.PositionData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class PositionDataServiceTest extends AbstractTest {

	// Services
	@Autowired
	private PositionDataService	positionDataService;

	@Autowired
	private HackerService		hackerService;

	@Autowired
	private CurriculaService	curriculaService;

	@Autowired
	private PersonalDataService	personalDataService;


	@Test
	public void driverCreateSave() throws ParseException {
		//final Collection<String> lawsVacio = new ArrayList<String>();
		//final Collection<String> laws = new ArrayList<String>();
		//laws.add("LawTest1");
		//laws.add("LawTest2");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//String stringFechaConHora = "2014-09-15 15:03:23";
		//Date fechaConHora = sdf.parse(stringFechaConHora);
		final Object testingData[][] = {
			{
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Positivo: Hacker crea PositionData con coleccion de laws vacia
				//			C: 100% Recorre 49 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", "degree1", "institution1", sdf.parse("2014-09-15"), sdf.parse("2018-09-20"), null
			}
		//, {
		//			A: Acme Parade Req. 3 -> Hackers can manage their history
		//			B: Test Negativo: Un member intenta crear una PositionData
		//			C: 32,65% Recorre 16 de las 49 lineas posibles
		//			D: cobertura de datos=6/405
		//"hacker2", null, "institution1",  sdf.parse("2014-09-15 00:00:00"), sdf.parse("2018-09-20 00:00:00"), IllegalArgumentException.class
		//},
		//{
		//			A: Acme Parade Req. 3 -> Hackers can manage their history
		//			B: Test Positivo: Hacker crea PositionData con coleccion de laws con datos
		//			C: 100% Recorre 49 de las 49 lineas posibles
		//			D: cobertura de datos=6/405
		//"hacker1", "degree1", "", sdf.parse("2014-09-15 00:00:00"), sdf.parse("2018-09-20 00:00:00"), IllegalArgumentException.class
		//}
		//,{
		//			A: Acme Parade Req. 3 -> Hackers can manage their history
		//			B: Test Positivo: Hacker crea PositionData con coleccion de laws con datos
		//			C: 100% Recorre 49 de las 49 lineas posibles
		//			D: cobertura de datos=6/405
		//"hacker1", "degree1", "institution2",  sdf.parse("2014-09-15 00:00:00"), sdf.parse("2013-09-20 00:00:00"), IllegalArgumentException.class
		//},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateSave((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Date) testingData[i][3], (Date) testingData[i][4], (Class<?>) testingData[i][5]);
	}

	protected void templateCreateSave(final String user, final String title, final String description, final Date startDate, final Date endDate, final Class<?> expected) {

		Class<?> caught = null;

		try {
			Curricula curricula = this.curriculaService.create();
			final PersonalData pd = curricula.getPersonalRecord();
			this.personalDataService.save(pd);
			curricula.setPersonalRecord(pd);
			curricula = this.curriculaService.save(curricula);
			this.authenticate(user);
			final PositionData lRec = this.positionDataService.create();
			lRec.setTitle(title);
			lRec.setDescription(description);
			lRec.setStartDate(startDate);
			lRec.setEndDate(endDate);
			final PositionData lRecSaved = this.positionDataService.save(lRec, curricula.getId());
			Assert.isTrue(lRecSaved.getId() != 0);
			this.positionDataService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	@Test
	public void driverEdit() throws ParseException {
		//final Collection<String> lawsVacio = new ArrayList<String>();
		//final Collection<String> laws = new ArrayList<String>();
		//laws.add("LawTest1");
		//laws.add("LawTest2");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//String stringFechaConHora = "2014-09-15 15:03:23";
		//Date fechaConHora = sdf.parse(stringFechaConHora);
		final Object testingData[][] = {
			{
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Positivo: Hacker crea PositionData con coleccion de laws vacia
				//			C: 100% Recorre 49 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", "degree1", "institution1", sdf.parse("2014-09-15"), sdf.parse("2018-09-20"), null
			}
		//, {
		//			A: Acme Parade Req. 3 -> Hackers can manage their history
		//			B: Test Negativo: Un member intenta crear una PositionData
		//			C: 32,65% Recorre 16 de las 49 lineas posibles
		//			D: cobertura de datos=6/405
		//"hacker2", null, "institution1",  sdf.parse("2014-09-15 00:00:00"), sdf.parse("2018-09-20 00:00:00"), IllegalArgumentException.class
		//},
		//{
		//			A: Acme Parade Req. 3 -> Hackers can manage their history
		//			B: Test Positivo: Hacker crea PositionData con coleccion de laws con datos
		//			C: 100% Recorre 49 de las 49 lineas posibles
		//			D: cobertura de datos=6/405
		//"hacker1", "degree1", "", sdf.parse("2014-09-15 00:00:00"), sdf.parse("2018-09-20 00:00:00"), IllegalArgumentException.class
		//}
		//,{
		//			A: Acme Parade Req. 3 -> Hackers can manage their history
		//			B: Test Positivo: Hacker crea PositionData con coleccion de laws con datos
		//			C: 100% Recorre 49 de las 49 lineas posibles
		//			D: cobertura de datos=6/405
		//"hacker1", "degree1", "institution2",  sdf.parse("2014-09-15 00:00:00"), sdf.parse("2013-09-20 00:00:00"), IllegalArgumentException.class
		//},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEdit((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Date) testingData[i][3], (Date) testingData[i][4], (Class<?>) testingData[i][5]);
	}

	private void templateEdit(final String user, final String title, final String description, final Date startDate, final Date endDate, final Class<?> expected) {
		Class<?> caught = null;
		try {
			this.authenticate(user);
			final Hacker principal = this.hackerService.findByPrincipal();
			final Collection<Curricula> curriculas = this.curriculaService.findCurriculaByHacker(principal.getId());
			final Curricula curricula = curriculas.iterator().next();
			final PositionData lR = curricula.getPositions().iterator().next();
			lR.setTitle(title);
			lR.setDescription(description);
			lR.setStartDate(startDate);
			lR.setEndDate(endDate);
			this.positionDataService.save(lR, curricula.getId());
			this.positionDataService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}

	@Test
	public void driverDelete() {

		final Object testingData[][] = {
			{
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Positivo: Hacker borra PositionData 
				//			C: 100% Recorre 78 de las 78 lineas posibles
				//			D: cobertura de datos=1/3
				"hacker2", null
			}, {
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Negativo: Member intenta borrar PositionData 
				//			C: 10,25% Recorre 8 de las 78 lineas posibles
				//			D: cobertura de datos=1/3
				"company1", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDelete((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	private void templateDelete(final String actor, final Class<?> expected) {
		Class<?> caught = null;
		try {
			this.authenticate(actor);
			final Hacker hacker = this.hackerService.findByPrincipal();
			final PositionData lRec = this.curriculaService.findCurriculaByHacker(hacker.getId()).iterator().next().getPositions().iterator().next();
			this.positionDataService.delete(lRec);
			this.positionDataService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}
}
