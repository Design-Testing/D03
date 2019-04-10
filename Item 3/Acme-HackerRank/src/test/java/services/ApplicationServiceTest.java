
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	// Services
	@Autowired
	private ApplicationService	applicationService;


	@Test
	public void driverCreateSave() {
		final Object testingData[][] = {
			{
				//			A: Acme Hacker Rank Req. 10.1 -> Manage his or her applications, which includes listing them grouped by status, showing
				//			them, creating them, and updating them.
				//			B: Test Positivo: Hacker crea una nueva solicitud a una position
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", "position4", null
			}, {
				//			A: Acme Hacker Rank Req. 10.1 -> Manage his or her applications, which includes listing them grouped by status, showing
				//			them, creating them, and updating them.
				//			B: Test Negativo: Hacker crea una nueva solicitud a una position que esta en DRAFT MODE
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", "position3", IllegalArgumentException.class
			}, {
				//			A: Acme Hacker Rank Req. 10.1 -> Manage his or her applications, which includes listing them grouped by status, showing
				//			them, creating them, and updating them.
				//			B: Test Negativo: Hacker crea una nueva solicitud a una position en la que ese hacker ya tiene
				//			solicitudes a todos los problemas posibles.
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", "position1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateSave((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	protected void templateCreateSave(final String hacker, final String position, final Class<?> expected) {

		Class<?> caught = null;

		try {
			this.authenticate(hacker);

			final Integer positionId = this.getEntityId(position);
			final Application saved = this.applicationService.apply(positionId);

			Assert.isTrue(saved.getId() != 0);
			this.applicationService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();

		}
		super.checkExceptions(expected, caught);
	}

	@Test
	public void driverEdit() {
		final Object testingData[][] = {
			{
				//			A: Acme Hacker Rank Req. 10.1 -> Manage his or her applications, which includes listing them grouped by status, showing
				//			them, creating them, and UPDATING them.
				//			B: Test Positivo: 
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", "position4", null
			}, {
				//			A: Acme Hacker Rank Req. 10.1 -> Manage his or her applications, which includes listing them grouped by status, showing
				//			them, creating them, and UPDATING them.
				//			B: Test Negativo: 
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", "position3", IllegalArgumentException.class
			}, {
				//			A: Acme Hacker Rank Req. 10.1 -> Manage his or her applications, which includes listing them grouped by status, showing
				//			them, creating them, and UPDATING them.
				//			B: Test Negativo: 
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"hacker1", "position1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEdit((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][2]);
	}
	protected void templateEdit(final String hacker, final String application, final String position, final String explanation, final String link, final Class<?> expected) {

		Class<?> caught = null;

		try {
			this.authenticate(hacker);

			final Integer applicationId = this.getEntityId(application);
			final Application saved = this.applicationService.save(application, positionId)

			Assert.isTrue(saved.getId() != 0);
			this.applicationService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();

		}
		super.checkExceptions(expected, caught);
	}
	@Test
	public void driverAcceptApplication() {
		final Object testingData[][] = {
			{
				//			A: Acme Hacker Rank Req. 9.3 -> Manage the applications to their positions which includes listing 
				//			them grouped by status, showing them, and UPDATING them.
				//			B: Test Positivo: Company acepta una application
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"company2", "application3", null
			}, {
				//			A: Acme Hacker Rank Req. 9.3 -> Manage the applications to their positions which includes listing 
				//			them grouped by status, showing them, and UPDATING them.
				//			B: Test Negativo: Company acepta una application que no le pertenece
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"company1", "application3", IllegalArgumentException.class
			}, {
				//			A: Acme Hacker Rank Req. 9.3 -> Manage the applications to their positions which includes listing 
				//			them grouped by status, showing them, and UPDATING them.
				//			B: Test Negativo: Company acepta una application con estado PENDING
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"company1", "application1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateAcceptApplication((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	private void templateAcceptApplication(final String company, final String application, final Class<?> expected) {
		Class<?> caught = null;
		try {
			this.authenticate(company);
			final Integer applicationId = this.getEntityId(application);

			this.applicationService.acceptApplication(applicationId);
			this.applicationService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}

	@Test
	public void driverRejectApplication() {
		final Object testingData[][] = {
			{
				//			A: Acme Hacker Rank Req. 9.3 -> Manage the applications to their positions which includes listing 
				//			them grouped by status, showing them, and UPDATING them.
				//			B: Test Positivo: Company rechaza una application
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"company2", "application3", null
			}, {
				//			A: Acme Hacker Rank Req. 9.3 -> Manage the applications to their positions which includes listing 
				//			them grouped by status, showing them, and UPDATING them.
				//			B: Test Negativo: Company acepta una application que no le pertenece
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"company1", "application3", IllegalArgumentException.class
			}, {
				//			A: Acme Hacker Rank Req. 9.3 -> Manage the applications to their positions which includes listing 
				//			them grouped by status, showing them, and UPDATING them.
				//			B: Test Positivo: Company acepta una application con estado PENDING
				//			C: 97,95% Recorre 48 de las 49 lineas posibles
				//			D: cobertura de datos=6/405
				"company1", "application1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateRejectApplication((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	private void templateRejectApplication(final String company, final String application, final Class<?> expected) {
		Class<?> caught = null;
		try {
			this.authenticate(company);
			final Integer applicationId = this.getEntityId(application);

			this.applicationService.rejectApplication(applicationId);
			this.applicationService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);

	}

	//
	//	@Test
	//	public void driverDelete() {
	//
	//		final Object testingData[][] = {
	//			{
	//				//			A: Acme Parade Req. 3 -> Brotherhoods can manage their history
	//				//			B: Test Positivo: Brotherhood borra LegalRecord 
	//				//			C: 100% Recorre 78 de las 78 lineas posibles
	//				//			D: cobertura de datos=1/3
	//				"brotherhood2", null
	//			}, {
	//				//			A: Acme Parade Req. 3 -> Brotherhoods can manage their history
	//				//			B: Test Negativo: Member intenta borrar LegalRecord 
	//				//			C: 10,25% Recorre 8 de las 78 lineas posibles
	//				//			D: cobertura de datos=1/3
	//				"member1", IllegalArgumentException.class
	//			},
	//		};
	//
	//		for (int i = 0; i < testingData.length; i++)
	//			this.templateDelete((String) testingData[i][0], (Class<?>) testingData[i][1]);
	//	}
	//
	//	private void templateDelete(final String actor, final Class<?> expected) {
	//		Class<?> caught = null;
	//		try {
	//			this.authenticate(actor);
	//			final Brotherhood bro = this.brotherhoodService.findByPrincipal();
	//			final ArrayList<LegalRecord> lRecs = new ArrayList<LegalRecord>(bro.getHistory().getLegalRecords());
	//			final LegalRecord lRec = lRecs.get(0);
	//			this.legalRecordService.delete(lRec);
	//			this.legalRecordService.flush();
	//			this.unauthenticate();
	//		} catch (final Throwable oops) {
	//			caught = oops.getClass();
	//		}
	//
	//		super.checkExceptions(expected, caught);
	//
	//	}
}
