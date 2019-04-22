
package services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.EducationData;
import domain.Hacker;

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


	@Test
	public void driverCreateSave1() {
		final Object testingData[][] = {
			{
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Positivo: Hacker crea EducationData con coleccion de laws vacia
				//			C: 100% Recorre 49 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", "LegalTest", "descriptionTest", "Legal Record Test", 0.21, lawsVacio, null
			}, {
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Negativo: Un member intenta crear una EducationData
				//			C: 32,65% Recorre 16 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"member1", "LegalTest", "descriptionTest", "Legal Record Test", 0.21, null, IllegalArgumentException.class
			}, {
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Positivo: Hacker crea EducationData con coleccion de laws con datos
				//			C: 100% Recorre 49 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", "LegalTest", "descriptionTest", "Legal Record Test", 0.21, laws, null
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateSave((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Double) testingData[i][4], (Collection<String>) testingData[i][5], (Class<?>) testingData[i][6]);
	}

	@Test
	public void driverCreateSave2() {
		final Object testingData[][] = {
			{
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Negativo: Hacker edita EducationData con title vacio
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", "", "descriptionTest", "Legal Record Test", 0.21, null, ConstraintViolationException.class
			}, {
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Negativo: Hacker edita EducationData con title null
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", null, "descriptionTest", "Legal Record Test", 0.21, null, ConstraintViolationException.class
			}, {
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Negativo: Hacker crea EducationData con description vacia
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", "LegalTest", "", "Legal Record Test", 0.21, null, ConstraintViolationException.class
			}, {
				//			A: Acme Parade Req. 3 -> Hackers can manage their history
				//			B: Test Negativo: Hacker crea EducationData con description null
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", "LegalTest", null, "Legal Record Test", 0.21, null, ConstraintViolationException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateSave((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Double) testingData[i][4], (Collection<String>) testingData[i][5], (Class<?>) testingData[i][6]);
	}
	protected void templateCreateSave(final String user, final String institution, final String degree, final Integer mark, final Date startDate, final Date endDate, final Class<?> expected) {

		Class<?> caught = null;

		try {
			this.authenticate(user);
			final EducationData lRec = this.educationDataService.create();
			lRec.setInstitution(institution);
			lRec.setDegree(degree);
			lRec.setMark(mark);
			lRec.setStartDate(startDate);

			if (laws != null)
				lRec.setLaws(laws);
			final EducationData lRecSaved = this.educationDataService.save(lRec);
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
			this.templateEdit((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Double) testingData[i][4], (Collection<String>) testingData[i][5], (Class<?>) testingData[i][6]);
	}

	private void templateEdit(final String user, final String title, final String description, final String legalName, final Double vat, final Collection<String> laws, final Class<?> expected) {
		Class<?> caught = null;
		try {
			this.authenticate(user);
			final Hacker principal = this.hackerService.findByPrincipal();
			final ArrayList<EducationData> lRecs = new ArrayList<EducationData>(principal.getHistory().getEducationDatas());
			final EducationData lR = lRecs.get(0);
			lR.setTitle(title);
			lR.setDescription(description);
			lR.setLegalName(legalName);
			lR.setVat(vat);
			if (laws != null)
				lR.setLaws(laws);
			this.educationDataService.save(lR);
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
				"member1", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDelete((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	private void templateDelete(final String actor, final Class<?> expected) {
		Class<?> caught = null;
		try {
			this.authenticate(actor);
			final Hacker bro = this.hackerService.findByPrincipal();
			final ArrayList<EducationData> lRecs = new ArrayList<EducationData>(bro.getHistory().getEducationDatas());
			final EducationData lRec = lRecs.get(0);
			this.educationDataService.delete(lRec);
			this.educationDataService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}

}
