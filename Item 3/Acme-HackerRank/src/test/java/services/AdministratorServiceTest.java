
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
import domain.CreditCard;

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
				//				A: Acme HackerRank Login Use Case
				//				B: Test Positivo: Un administrador puede loguearse correctamente
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: % cobertura de datos= 2/2
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
		final Collection<String> surnames1 = new ArrayList<>();
		final Collection<String> surnames2 = new ArrayList<>();
		surnames2.add("Lanzas");
		final CreditCard c = super.defaultCreditCard();
		final Object testingData[][] = {
			{
				//				A: Acme HackerRank Req. 11.1. Create user accounts for new administrators
				//				B: Test Positivo: Creación correcta de un admin
				//				C: % Recorre 54 de la 196 lineas posibles
				//				D: % cobertura de datos=8/32 (casos cubiertos / combinaciones posibles de atributos entre ellos)
				"admin1", "admin3", "admin3", "Administrator1", surnames, "garcia@gmail.es", "+34647307406", "0.1", c, null
			}, {
				//				A: Acme HackerRank Req. 11.1. Create user accounts for new administrators
				//				B: Test Negacion: Creacion incorrecta de un admin. Para registrar un actor como administrador el actor logeado tiene que ser un adninistrador
				//				C: % Recorre 28 de la 196 lineas posibles
				//				D: % cobertura de datos=8/32 (casos cubiertos / combinaciones posibles de atributos entre ellos)
				"hacker1", "admin23", "admin23", "Administrator1", surnames, "garcia@gmail.es", "+34647307406", "0.1", c, IllegalArgumentException.class
			}, {
				//				A: Acme HackerRank Req. 11.1. Create user accounts for new administrators
				//				B: Test Negativo: Creación incorrecta de un admin con name en blanco
				//				C: % Recorre 54 de la 196 lineas posibles
				//				D: % cobertura de datos=8/32 (casos cubiertos / combinaciones posibles de atributos entre ellos)
				"admin1", "admin33", "admin33", "", surnames, "lanzas@gmail.com", "+34647307406", "0.1", c, ConstraintViolationException.class

			}, {
				//				A: Acme HackerRank Req. 11.1. Create user accounts for new administrators
				//				B: Test Negativo: Creación incorrecta de un admin. Vat fuera de rango
				//				C: % Recorre 51 de la 196 lineas posibles
				//				D: % cobertura de datos=8/32 (casos cubiertos / combinaciones posibles de atributos entre ellos)
				"admin1", "admin3", "admin3", "Administrator1", surnames, "garcia@gmail.com", "+34647307406", "3.1", c, ConstraintViolationException.class
			}, {
				//				A: Acme HackerRank Req. 11.1. Create user accounts for new administrators
				//				B: Test Negativo: Creación incorrecta de un admin con surnames vacio (tiene que tener al menos uno)
				//				C: % Recorre 54 de la 196 lineas posibles
				//				D: % cobertura de datos=8/32 (casos cubiertos / combinaciones posibles de atributos entre ellos)
				"admin1", "admin13", "admin13", "Administrator1", surnames1, "lanzas1@gmail.com", "+34647307406", "0.1", c, ConstraintViolationException.class
			}, {
				//				A: Acme HackerRank Req. 11.1. Create user accounts for new administrators
				//				B: Test Negativo: Creación incorrecta de un admin con email que no cumple patron
				//				C: % Recorre 54 de la 196 lineas posibles
				//				D: % cobertura de datos=8/32 (casos cubiertos / combinaciones posibles de atributos entre ellos)
				"admin1", "admin43", "admin43", "Administrator1", surnames2, "lanzasgmail.com", "+34647307406", "0.1", c, ConstraintViolationException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateCreateAndSave((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Collection<String>) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (CreditCard) testingData[i][8], (Class<?>) testingData[i][9]);
	}
	private void templateCreateAndSave(final String principal, final String username, final String password, final String name, final Collection<String> surname, final String email, final String phone, final String vat, final CreditCard creditCard,
		final Class<?> expected) {

		// para crear un administrador tienes que tener autoridad de administrador

		Class<?> caught;
		Administrator admin;
		final UserAccount userAccount;

		caught = null;

		try {
			super.authenticate(principal);
			admin = this.adminService.create();
			admin.setName(name);
			admin.setSurname(surname);
			admin.setEmail(email);
			admin.setPhone(phone);
			admin.setCreditCard(creditCard);
			admin.setVat(new Double(vat));
			userAccount = admin.getUserAccount();
			userAccount.setUsername(username);
			userAccount.setPassword(password);
			admin.setUserAccount(userAccount);
			admin = this.adminService.save(admin);
			this.adminService.flush();
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

}
