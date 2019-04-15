
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import security.UserAccount;
import utilities.AbstractTest;
import domain.Administrator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class AdministratorServiceTest extends AbstractTest {

	// Services
	@Autowired
	private AdministratorService	adminService;


	/* ========================= Test Login Administrator =========================== */
	@Test
	public void driverLoginAdministrator() {

		final Object testingData[][] = {
			{
				//				A: Acme Parade Req. X Titulo Use Case
				//				B: Test Positivo: Un chapter puede loguearse correctamente
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"admin1", null
			}, {
				//Login usuario no registrado
				"AdministratorNoRegistrado", IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateLogin((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	private void templateLogin(final String adminUsername, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(adminUsername);
			this.unauthenticate();
			this.adminService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/* ========================= Test Create and Save Administrator =========================== */

	@Test
	public void driverCreateAndSaveAdministrator() {
		final Collection<String> surnames = new ArrayList<>();
		surnames.add("Garcia");
		final Collection<String> surnames2 = new ArrayList<>();
		surnames2.add("Lanzas");
		final Object testingData[][] = {
			{
				//				A: Acme Parade Req. 7.1 Register to de system as a chapter
				//				B: Test Positivo: Creación correcta de un admin
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"admin1", "admin1", "Administrator1", surnames, "garcia@gmail.es", "+34647307406", null
			}, {
				//				A: Acme Parade Req. 7.1 Register to de system as a chapter
				//				B: Test Negativo: Creación incorrecta de un admin con telefono inválido
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				//TODO:Debe dar error en la creación por el teléfono.
				"admin2", "admin2", "Administrator1", surnames2, "lanzas@gmail.com", "mi telefono", null
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateCreateAndSave((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Collection<String>) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Class<?>) testingData[i][6]);
	}

	private void templateCreateAndSave(final String username, final String password, final String name, final Collection<String> surname, final String email, final String phone, final Class<?> expected) {

		Class<?> caught;
		Administrator admin;
		final UserAccount userAccount;

		caught = null;

		try {
			admin = this.adminService.create();
			admin.setName(name);
			admin.setSurname(surname);
			admin.setEmail(email);
			admin.setPhone(phone);
			userAccount = admin.getUserAccount();
			userAccount.setUsername(username);
			userAccount.setPassword(password);
			admin.setUserAccount(userAccount);
			admin = this.adminService.save(admin);
		} catch (final Throwable oops) {
			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/* ========================= Test Edit Administrator =========================== */

	@Test
	public void driverEditAdministrator() {
		final Collection<String> surnames = new ArrayList<>();
		surnames.add("Garcia");
		final Collection<String> surnames2 = new ArrayList<>();

		final Object testingData[][] = {
			{
				// 				A: Acme Madruga Req. 9.2 Edit his o her personal data
				//				B: Test Positivo: Edición correcta de los datos de un admin
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"admin1", "admin1", "Administrator1", surnames, "garcia@gmail.es", "+34647307406", null
			}, {
				// 				A: Acme Madruga Req. 9.2 Edit his o her personal data
				//				B: Test Positivo: Edición correcta de los datos de un admin con phone vacio
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"admin1", "admin1", "Administrator1", surnames, "garcia@gmail.es", "", null
			}, {
				// 				A: Acme Madruga Req. 9.2 Edit his o her personal data
				//				B: Test Negativo: Edición incorrecta de los datos de un admin con mail inválido
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"admin1", "admin1", "Administrator1", surnames, "no tengo email", "+34647307406", ConstraintViolationException.class
			}, {
				// 				A: Acme Madruga Req. 9.2 Edit his o her personal data
				//				B: Test Negativo: Edición incorrecta de los datos de un admin con name vacio
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"admin1", "admin1", "", surnames, "garcia@gmail.es", "+34647307406", ConstraintViolationException.class
			}, {
				// 				A: Acme Madruga Req. 9.2 Edit his o her personal data
				//				B: Test Positivo: Edición correcta de los datos de un admin con apellidos vacio
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"admin1", "admin1", "Administrator1", surnames2, "garcia@gmail.es", "+34647307406", null
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateEditAdministrator((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Collection<String>) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Class<?>) testingData[i][6]);
	}

	private void templateEditAdministrator(final String username, final String password, final String name, final Collection<String> surname, final String email, final String phone, final Class<?> expected) {
		Class<?> caught;
		Administrator admin;
		admin = this.adminService.findOne(this.getEntityId(username));

		caught = null;
		try {
			super.authenticate(username);
			admin.setName(name);
			admin.setSurname(surname);
			admin.setEmail(email);
			admin.setPhone(phone);
			this.adminService.save(admin);
			this.unauthenticate();
			this.adminService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);

	}
}
