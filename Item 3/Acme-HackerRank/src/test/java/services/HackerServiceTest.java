
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
import domain.Hacker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class HackerServiceTest extends AbstractTest {

	// Services
	@Autowired
	private HackerService	hackerService;


	/* ========================= Test Login Hacker =========================== */
	@Test
	public void driverLoginHacker() {

		final Object testingData[][] = {
			{
				//				A: Acme Parade Req. X Titulo Use Case
				//				B: Test Positivo: Un chapter puede loguearse correctamente
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"hacker1", null
			}, {
				//Login usuario no registrado
				"HackerNoRegistrado", IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateLogin((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	private void templateLogin(final String hackerUsername, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(hackerUsername);
			this.unauthenticate();
			this.hackerService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/* ========================= Test Create and Save Hacker =========================== */

	@Test
	public void driverCreateAndSaveHacker() {
		final Collection<String> surnames = new ArrayList<>();
		surnames.add("Garcia");
		final Collection<String> surnames2 = new ArrayList<>();
		surnames2.add("Lanzas");
		final Object testingData[][] = {
			{
				//				A: Acme Parade Req. 7.1 Register to de system as a chapter
				//				B: Test Positivo: Creación correcta de un hacker
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"hacker1", "hacker1", "Hacker1", surnames, "garcia@gmail.es", "+34647307406", null
			}, {
				//				A: Acme Parade Req. 7.1 Register to de system as a chapter
				//				B: Test Negativo: Creación incorrecta de un hacker con telefono inválido
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				//TODO:Debe dar error en la creación por el teléfono.
				"hacker2", "hacker2", "Hacker1", surnames2, "lanzas@gmail.com", "mi telefono", null
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateCreateAndSave((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Collection<String>) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Class<?>) testingData[i][6]);
	}

	private void templateCreateAndSave(final String username, final String password, final String name, final Collection<String> surname, final String email, final String phone, final Class<?> expected) {

		Class<?> caught;
		Hacker hacker;
		final UserAccount userAccount;

		caught = null;

		try {
			hacker = this.hackerService.create();
			hacker.setName(name);
			hacker.setSurname(surname);
			hacker.setEmail(email);
			hacker.setPhone(phone);
			userAccount = hacker.getUserAccount();
			userAccount.setUsername(username);
			userAccount.setPassword(password);
			hacker.setUserAccount(userAccount);
			hacker = this.hackerService.save(hacker);
		} catch (final Throwable oops) {
			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/* ========================= Test Edit Hacker =========================== */

	@Test
	public void driverEditHacker() {
		final Collection<String> surnames = new ArrayList<>();
		surnames.add("Garcia");
		final Collection<String> surnames2 = new ArrayList<>();

		final Object testingData[][] = {
			{
				// 				A: Acme Madruga Req. 9.2 Edit his o her personal data
				//				B: Test Positivo: Edición correcta de los datos de un hacker
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"hacker1", "hacker1", "Hacker1", surnames, "garcia@gmail.es", "+34647307406", null
			}, {
				// 				A: Acme Madruga Req. 9.2 Edit his o her personal data
				//				B: Test Positivo: Edición correcta de los datos de un hacker con phone vacio
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"hacker1", "hacker1", "Hacker1", surnames, "garcia@gmail.es", "", null
			}, {
				// 				A: Acme Madruga Req. 9.2 Edit his o her personal data
				//				B: Test Negativo: Edición incorrecta de los datos de un hacker con mail inválido
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"hacker1", "hacker1", "Hacker1", surnames, "no tengo email", "+34647307406", ConstraintViolationException.class
			}, {
				// 				A: Acme Madruga Req. 9.2 Edit his o her personal data
				//				B: Test Negativo: Edición incorrecta de los datos de un hacker con name vacio
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"hacker1", "hacker1", "", surnames, "garcia@gmail.es", "+34647307406", ConstraintViolationException.class
			}, {
				// 				A: Acme Madruga Req. 9.2 Edit his o her personal data
				//				B: Test Negativo: Edición incorrecta de los datos de un hacker con apellidos vacio
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"hacker1", "hacker1", "Hacker1", surnames2, "garcia@gmail.es", "+34647307406", ConstraintViolationException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateEditHacker((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Collection<String>) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Class<?>) testingData[i][6]);
	}

	private void templateEditHacker(final String username, final String password, final String name, final Collection<String> surname, final String email, final String phone, final Class<?> expected) {
		Class<?> caught;
		Hacker hacker;
		hacker = this.hackerService.findOne(this.getEntityId(username));

		caught = null;
		try {
			super.authenticate(username);
			hacker.setName(name);
			hacker.setSurname(surname);
			hacker.setEmail(email);
			hacker.setPhone(phone);
			this.hackerService.save(hacker);
			this.unauthenticate();
			this.hackerService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);

	}

}
