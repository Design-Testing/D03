
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import security.Authority;
import security.UserAccount;
import utilities.AbstractTest;
import domain.Company;
import domain.CreditCard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CompanyServiceTest2 extends AbstractTest {

	// Services
	@Autowired
	private CompanyService		companyService;

	@Autowired
	private UserAccountService	userAccountService;


	/* ========================= Test Login Administrator =========================== */
	@Test
	public void driverLoginAdministrator() {

		final Object testingData[][] = {
			{
				//				A: Acme HackerRank Login Use Case
				//				B: Test Positivo: Un administrador puede loguearse correctamente
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: % cobertura de datos= 2/2
				"company1", null
			}, {
				//Login usuario no registrado
				"CompanyNoRegistrado", IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateLogin((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	private void templateLogin(final String companyUsername, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			super.authenticate(companyUsername);
			this.unauthenticate();
			this.companyService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/* ========================= Test Create and Save Administrator =========================== */

	@Test
	public void driverCreateAndSaveCompany() {
		final Collection<String> surnames = new ArrayList<>();
		surnames.add("Garcia");
		final Collection<String> surnames1 = new ArrayList<>();
		surnames1.add("Garcia");
		surnames1.add("");
		final Collection<String> surnames2 = new ArrayList<>();
		surnames2.add("Lanzas");
		final CreditCard c = super.defaultCreditCard();
		final Object testingData[][] = {
			{
				//				A: Acme Hacker Rank Req. 7.1 Register to de system as a company
				//				B: Test Positivo: Creación correcta de un company
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				//				"company1", "company1", "Company1", surnames, "company1@gmail.es", "+34647307480", 0.21, c, "commercialName1", null
				//			}, {
				//				A: Acme Hacker Rank Req. 7.1 Register to de system as a company
				//				B: Test Negativo: Creación incorrecta de un company, con commercial name incorrecto
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"company1", "company1", "Company1", surnames, "company1@gmail.es", "+34647307480", 0.21, c, "", ConstraintViolationException.class
			}, {
				//				A: Acme Hacker Rank Req. 7.1 Register to de system as a company
				//				B: Test Negativo: Creación incorrecta de un company, con email incorrecto
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"company1", "company1", "Company1", surnames, "mi correo", "+34647307480", 0.21, c, "commercialName1", ConstraintViolationException.class
			//			}, {
			//				//				A: Acme Hacker Rank Req. 7.1 Register to de system as a company
			//				//				B: Test Negativo: Creación incorrecta de un company, con vat incorrecto (mayor que 1)
			//				//				C: % Recorre 8 de la 23 lineas posibles
			//				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
			//				"company1", "company1", "Company1", surnames, "company1@gmail.es", "+34647307480", 10.21, c, "commercialName1", ConstraintViolationException.class
			//			}, {
			//				//				A: Acme Hacker Rank Req. 7.1 Register to de system as a company
			//				//				B: Test Negativo: Creación incorrecta de un company, con vat incorrecto (negativo)
			//				//				C: % Recorre 8 de la 23 lineas posibles
			//				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
			//				"company1", "company1", "Company1", surnames, "company1@gmail.es", "+34647307480", -1.21, c, "commercialName1", ConstraintViolationException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.templateCreateAndSave((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Collection<String>) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Double) testingData[i][6],
				(CreditCard) testingData[i][7], (String) testingData[i][8], (Class<?>) testingData[i][9]);
	}
	private void templateCreateAndSave(final String username, final String password, final String name, final Collection<String> surname, final String email, final String phone, final Double vat, final CreditCard creditCard, final String commercialName,
		final Class<?> expected) {

		// para crear un administrador tienes que tener autoridad de administrador

		Class<?> caught;
		Company company;
		final UserAccount userAccount;

		caught = null;

		try {
			company = this.companyService.create();
			userAccount = new UserAccount();
			userAccount.setUsername(username);
			userAccount.setPassword(password);
			final Collection<Authority> authorities = new ArrayList<>();
			final Authority auth = new Authority();
			auth.setAuthority(Authority.COMPANY);
			authorities.add(auth);
			userAccount.setAuthorities(authorities);
			company.setUserAccount(userAccount);
			company.setName(name);
			company.setSurname(surname);
			company.setEmail(email);
			company.setPhone(phone);
			company.setCreditCard(creditCard);
			company.setVat(vat);
			company.setCommercialName(commercialName);
			company = this.companyService.save(company);
			this.companyService.flush();
			this.userAccountService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/* ========================= Test Create and Save Administrator =========================== */

	@Test
	public void driverEditCompany() {
		final Collection<String> surnames = new ArrayList<>();
		surnames.add("Garcia");
		final Collection<String> surnames1 = new ArrayList<>();
		surnames1.add("Garcia");
		surnames1.add("");
		final Collection<String> surnames2 = new ArrayList<>();
		surnames2.add("Lanzas");
		final CreditCard c = super.defaultCreditCard();
		final Object testingData[][] = {
			{
				//				A: Acme Hacker Rank Req. 7.1 Register to de system as a company
				//				B: Test Positivo: Creación correcta de un company
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"company1", "company1", "Company1", surnames, "company1@gmail.es", "+34647307480", 0.21, c, "commercialName1", null
			}, {
				//				A: Acme Hacker Rank Req. 7.1 Register to de system as a company
				//				B: Test Negativo: Creación incorrecta de un company, con vat incorrecto (negativo)
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"company1", "company1", "Company1", surnames, "company1@gmail.es", "+34647307480", -1.21, c, "commercialName1", ConstraintViolationException.class
			}, {
				//				A: Acme Hacker Rank Req. 7.1 Register to de system as a company
				//				B: Test Negativo: Creación incorrecta de un company, con vat incorrecto (mayor que 1)
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"company1", "company1", "Company1", surnames, "company1@gmail.es", "+34647307480", 10.21, c, "commercialName1", DataIntegrityViolationException.class
			}, {
				//				A: Acme Hacker Rank Req. 7.1 Register to de system as a company
				//				B: Test Negativo: Creación incorrecta de un company, con email incorrecto
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"company1", "company1", "Company1", surnames, "mi correo", "+34647307480", 0.21, c, "commercialName1", DataIntegrityViolationException.class
			}, {
				//				A: Acme Hacker Rank Req. 7.1 Register to de system as a company
				//				B: Test Negativo: Creación incorrecta de un company, con commercial name incorrecto
				//				C: % Recorre 8 de la 23 lineas posibles
				//				D: cobertura de datos=Combinaciones con sentido/numero atributos
				"company1", "company1", "Company1", surnames, "company1@gmail.es", "+34647307480", 0.21, c, "", DataIntegrityViolationException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.templateEdit((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Collection<String>) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Double) testingData[i][6],
				(CreditCard) testingData[i][7], (String) testingData[i][8], (Class<?>) testingData[i][9]);
	}
	private void templateEdit(final String username, final String password, final String name, final Collection<String> surname, final String email, final String phone, final Double vat, final CreditCard creditCard, final String commercialName,
		final Class<?> expected) {

		Class<?> caught;
		Company company;

		caught = null;

		try {

			super.authenticate(username);
			company = this.companyService.findByPrincipal();
			company.setName(name);
			company.setSurname(surname);
			company.setEmail(email);
			company.setPhone(phone);
			company.setCreditCard(creditCard);
			company.setVat(vat);
			company.setCommercialName(commercialName);
			company = this.companyService.save(company);
			this.companyService.flush();
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	//	@Test
	//	public void driverEditAndSaveAdministrator() {
	//		final Collection<String> surnames = new ArrayList<>();
	//		surnames.add("Garcia");
	//		final Collection<String> surnames1 = new ArrayList<>();
	//		surnames1.add("Garcia");
	//		surnames1.add("");
	//		final Collection<String> surnames2 = new ArrayList<>();
	//		surnames2.add("Lanzas");
	//		final CreditCard c = super.defaultCreditCard();
	//		final Object testingData[][] = {
	//			{
	//				//				A: Acme HackerRank Req. 11.1. Update administrator profile
	//				//				B: Test Positivo: Creación correcta de un admin
	//				//				C: % Recorre 54 de la 196 lineas posibles
	//				//				D: % cobertura de datos=8/32 (casos cubiertos / combinaciones posibles de atributos entre ellos)
	//				"admin1", "Administrator1", surnames, "garcia@us.es", "647307406", "0.1", c, null
	//			}, {
	//				//				A: Acme HackerRank Req. 11.1. Update administrator profile
	//				//				B: Test Negativo: Creación incorrecta de un admin con name en blanco
	//				//				C: % Recorre 54 de la 196 lineas posibles
	//				//				D: % cobertura de datos=8/32 (casos cubiertos / combinaciones posibles de atributos entre ellos)
	//				"admin1", "", surnames, "lanzas@gmail.com", "+34647307406", "0.1", c, ConstraintViolationException.class
	//			}
	//
	//		};
	//		for (int i = 0; i < testingData.length; i++)
	//			this.templateEditAndSave((String) testingData[i][0], (String) testingData[i][1], (Collection<String>) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (CreditCard) testingData[i][6],
	//				(Class<?>) testingData[i][7]);
	//	}
	//	private void templateEditAndSave(final String principal, final String name, final Collection<String> surname, final String email, final String phone, final String vat, final CreditCard creditCard, final Class<?> expected) {
	//
	//		// para crear un administrador tienes que tener autoridad de administrador
	//
	//		Class<?> caught;
	//		Administrator admin;
	//		final UserAccount userAccount;
	//
	//		caught = null;
	//
	//		try {
	//			super.authenticate(principal);
	//			admin = this.adminService.findByPrincipal();
	//			userAccount = new UserAccount();
	//			admin.setName(name);
	//			admin.setSurname(surname);
	//			admin.setEmail(email);
	//			admin.setPhone(phone);
	//			admin.setCreditCard(creditCard);
	//			admin.setVat(new Double(vat));
	//			admin = this.adminService.save(admin);
	//			this.adminService.flush();
	//			super.unauthenticate();
	//		} catch (final Throwable oops) {
	//			caught = oops.getClass();
	//
	//		}
	//
	//		this.checkExceptions(expected, caught);
	//	}

}
