
package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import utilities.AbstractTest;
import domain.Curricula;
import domain.EducationData;
import domain.Hacker;
import domain.PersonalData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class EducationDataServiceTest extends AbstractTest {

	// Services
	@Autowired
	private EducationDataService	educationDataService;

	@Autowired
	private HackerService			hackerService;

	@Autowired
	private CurriculaService		curriculaService;

	@Autowired
	private PersonalDataService		personalDataService;


	@Test
	public void driverCreateSave() throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//String stringFechaConHora = "2014-09-15 15:03:23";
		//Date fechaConHora = sdf.parse(stringFechaConHora);
		final Object testingData[][] = {
			{
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Positivo: Hacker crea EducationData con coleccion de laws vacia
				//			C: 100% Recorre 49 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", "degree1", "institution1", 5, sdf.parse("2014-09-15"), sdf.parse("2018-09-20"), null
			}
		//, {
		//			A: Acme Parade Req. 3 -> Hackers can manage their history
		//			B: Test Negativo: Un member intenta crear una EducationData
		//			C: 32,65% Recorre 16 de las 49 lineas posibles
		//			D: cobertura de datos=6/405
		//"hacker2", null, "institution1", 5, sdf.parse("2014-09-15 00:00:00"), sdf.parse("2018-09-20 00:00:00"), IllegalArgumentException.class
		//},
		//{
		//			A: Acme Parade Req. 3 -> Hackers can manage their history
		//			B: Test Positivo: Hacker crea EducationData con coleccion de laws con datos
		//			C: 100% Recorre 49 de las 49 lineas posibles
		//			D: cobertura de datos=6/405
		//"hacker1", "degree1", "", 3, sdf.parse("2014-09-15 00:00:00"), sdf.parse("2018-09-20 00:00:00"), IllegalArgumentException.class
		//}
		//,{
		//			A: Acme Parade Req. 3 -> Hackers can manage their history
		//			B: Test Positivo: Hacker crea EducationData con coleccion de laws con datos
		//			C: 100% Recorre 49 de las 49 lineas posibles
		//			D: cobertura de datos=6/405
		//"hacker1", "degree1", "institution2", 3, sdf.parse("2014-09-15 00:00:00"), sdf.parse("2013-09-20 00:00:00"), IllegalArgumentException.class
		//},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateSave((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Integer) testingData[i][3], (Date) testingData[i][4], (Date) testingData[i][5], (Class<?>) testingData[i][6]);
	}

	protected void templateCreateSave(final String user, final String degree, final String institution, final Integer mark, final Date startDate, final Date endDate, final Class<?> expected) {

		Class<?> caught = null;

		try {
			Curricula curricula = this.curriculaService.create();
			final PersonalData pd = curricula.getPersonalRecord();
			this.personalDataService.save(pd);
			curricula.setPersonalRecord(pd);
			curricula = this.curriculaService.save(curricula);
			this.authenticate(user);
			final EducationData lRec = this.educationDataService.create();
			lRec.setDegree(degree);
			lRec.setInstitution(institution);
			lRec.setMark(mark);
			lRec.setStartDate(startDate);
			lRec.setEndDate(endDate);
			final EducationData lRecSaved = this.educationDataService.save(lRec, curricula.getId());
			Assert.isTrue(lRecSaved.getId() != 0);
			this.educationDataService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

	@Test
	public void driverEdit() {
		final Collection<String> lawsVacio = new ArrayList<String>();
		final Collection<String> laws = new ArrayList<String>();
		laws.add("LawTest1");
		laws.add("LawTest2");
		final Object testingData[][] = {
			{
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Positivo: Hacker edita EducationData con laws vacio
				//			C: 100% Recorre 65 de las 65 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker2", "LegalTest", "descriptionTest", "Legal Record Test", 0.21, lawsVacio, null
			}, {
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Positivo: Hacker edita EducationData con laws con datos
				//			C: 100% Recorre 65 de las 65 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker2", "LegalTest", "descriptionTest", "Legal Record Test", 0.21, laws, null
			}, {
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Negativo: Un member intenta editar EducationData
				//			C: 12,3% Recorre 8 de las 65 lineas posibles
				//			D: cobertura de datos=6/405
				"member1", "LegalTest", "descriptionTest", "Legal Record Test", 0.21, null, IllegalArgumentException.class
			}, {
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Negativo: Hacker edita EducationData con title vacio
				//			C: 98,4% Recorre 64 de las 65 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker2", "", "descriptionTest", "Legal Record Test", 0.21, null, ConstraintViolationException.class
			}, {
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Negativo: Hacker edita EducationData con title null
				//			C: 98,4% Recorre 64 de las 65 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker2", null, "descriptionTest", "Legal Record Test", 0.21, null, ConstraintViolationException.class
			}, {
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Negativo: Hacker edita EducationData con description vacio
				//			C: 98,4% Recorre 64 de las 65 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker2", "LegalTest", "", "Legal Record Test", 0.21, null, ConstraintViolationException.class
			}, {
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Negativo: Hacker edita EducationData con description null
				//			C: 98,4% Recorre 64 de las 65 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker2", "LegalTest", null, "Legal Record Test", 0.21, null, ConstraintViolationException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEdit((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Integer) testingData[i][4], (Date) testingData[i][5], (Date) testingData[i][6], (Class<?>) testingData[i][7]);
	}

	private void templateEdit(final String user, final String degree, final String institution, final Integer mark, final Date startDate, final Date endDate, final Class<?> expected) {
		Class<?> caught = null;
		try {
			this.authenticate(user);
			final Hacker principal = this.hackerService.findByPrincipal();
			final Collection<Curricula> curriculas = this.curriculaService.findCurriculaByHacker(principal.getId());
			final Curricula curricula = curriculas.iterator().next();
			final EducationData lR = curricula.getEducations().iterator().next();
			lR.setInstitution(institution);
			lR.setDegree(degree);
			lR.setMark(mark);
			lR.setStartDate(startDate);
			lR.setEndDate(endDate);
			this.educationDataService.save(lR, curricula.getId());
			this.educationDataService.flush();
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
				//			B: Test Positivo: Hacker borra EducationData 
				//			C: 100% Recorre 78 de las 78 lineas posibles
				//			D: cobertura de datos=1/3
				"hacker2", null
			}, {
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Negativo: Member intenta borrar EducationData 
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
			final EducationData lRec = this.curriculaService.findCurriculaByHacker(hacker.getId()).iterator().next().getEducations().iterator().next();
			this.educationDataService.delete(lRec);
			this.educationDataService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}
}
