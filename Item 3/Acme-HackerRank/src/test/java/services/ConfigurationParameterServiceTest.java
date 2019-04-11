
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

import utilities.AbstractTest;
import domain.ConfigurationParameters;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ConfigurationParameterServiceTest extends AbstractTest {

	// Services
	@Autowired
	private ConfigurationParametersService	configurationParametersService;

	@Autowired
	private AdministratorService			administratorService;


	//Test Save

	@Test
	public void driverEditAndSave() {
		final Collection<String> posWords = new ArrayList<>();
		posWords.add("good");
		final Collection<String> negWords = new ArrayList<>();
		negWords.add("not");
		final Collection<String> spamWords = new ArrayList<>();
		spamWords.add("viagra");
		final Collection<String> words = new ArrayList<>();
		words.add("noExisteEstaPalabra");
		final Object testingData[][] = {
			{
				//MaxFinderResults con valor min.
				"admin1", "Acme Hacker Rank", "https://i.imgur.com/7b8lu4b.png", "welcomeMessageEsp", "welcomeMessageEn", "+34", 1, 10, posWords, negWords, spamWords, null
			}, {
				//MaxFinderResults con valor min - 1, ConstraintViolationException.
				"admin1", "Acme Hacker Rank", "https://i.imgur.com/7b8lu4b.png", "welcomeMessageEsp", "welcomeMessageEn", "+34", 10, 9, null, negWords, spamWords, ConstraintViolationException.class
			}, {
				//MaxFinderResults con valor min + 1.
				"admin1", "Acme Hacker Rank", "https://i.imgur.com/7b8lu4b.png", "welcomeMessageEsp", "welcomeMessageEn", "+34", 10, 11, posWords, null, spamWords, null
			}, {
				//MaxFinderResults con valor max + 1, ConstraintViolationException.
				"admin1", "Acme Hacker Rank", "https://i.imgur.com/7b8lu4b.png", "welcomeMessageEsp", "welcomeMessageEn", "+34", 10, 101, posWords, negWords, words, ConstraintViolationException.class
			}, {
				//MaxFinderResults con valor max.
				"admin1", "Acme Hacker Rank", "https://i.imgur.com/7b8lu4b.png", "welcomeMessageEsp", "welcomeMessageEn", "+34", 10, 100, posWords, negWords, spamWords, null
			}, {
				//MaxFinderResults con valor max - 1.
				"admin1", "Acme Hacker Rank", "https://i.imgur.com/7b8lu4b.png", "welcomeMessageEsp", "welcomeMessageEn", "+34", 10, 99, posWords, negWords, spamWords, null
			}, {
				//FinderTime con valor min.
				"admin1", "Acme Hacker Rank", "https://i.imgur.com/7b8lu4b.png", "welcomeMessageEsp", "welcomeMessageEn", "+34", 1, 99, posWords, negWords, spamWords, null
			}, {
				//FinderTime con valor min - 1, ConstraintViolationException.
				"admin1", "Acme Hacker Rank", "https://i.imgur.com/7b8lu4b.png", "welcomeMessageEsp", "welcomeMessageEn", "+34", 0, 99, posWords, negWords, spamWords, ConstraintViolationException.class
			}, {
				//FinderTime con valor max.
				"admin1", "Acme Hacker Rank", "https://i.imgur.com/7b8lu4b.png", "welcomeMessageEsp", "welcomeMessageEn", "+34", 24, 99, posWords, negWords, spamWords, null
			}, {
				//FinderTime con valor max + 1, ConstraintViolationException.
				"admin1", "Acme Hacker Rank", "https://i.imgur.com/7b8lu4b.png", "welcomeMessageEsp", "welcomeMessageEn", "+34", 25, 99, posWords, negWords, spamWords, ConstraintViolationException.class
			}, {
				//WelcomeMessageEsp vacío, ConstraintViolationException.
				"admin1", "Acme Hacker Rank", "https://i.imgur.com/7b8lu4b.png", "", "welcomeMessageEn", "+34", 10, 99, posWords, negWords, spamWords, ConstraintViolationException.class
			}, {
				//WelcomeMessageEn vacío, ConstraintViolationException.
				"admin1", "Acme Hacker Rank", "https://i.imgur.com/7b8lu4b.png", "welcomeMessageEsp", "", "+34", 10, 99, posWords, negWords, spamWords, ConstraintViolationException.class
			}, {
				//CountryPhoneCode con valor diferente al Pattern, ConstraintViolationException.
				"admin1", "Acme Hacker Rank", "https://i.imgur.com/7b8lu4b.png", "welcomeMessageEsp", "welcomeMessageEn", "+345", 10, 99, posWords, negWords, spamWords, ConstraintViolationException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateEditAndSave((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Integer) testingData[i][6],
				(Integer) testingData[i][7], (Collection<String>) testingData[i][8], (Collection<String>) testingData[i][9], (Collection<String>) testingData[i][10], (Class<?>) testingData[i][11]);
	}
	private void templateEditAndSave(final String adminUsername, final String sysName, final String banner, final String welcomeMessageEsp, final String welcomeMessageEn, final String countryPhoneCode, final Integer finderTime,
		final Integer maxFinderResults, final Collection<String> posWords, final Collection<String> negWords, final Collection<String> spamWords, final Class<?> expected) {
		Class<?> caught;
		ConfigurationParameters cParameters;

		caught = null;

		try {
			super.authenticate(adminUsername);
			//			cParameters = this.configurationParametersService.findOne(44);
			cParameters = this.configurationParametersService.create();
			cParameters.setSysName(sysName);
			cParameters.setBanner(banner);
			cParameters.setWelcomeMessageEn(welcomeMessageEn);
			cParameters.setWelcomeMessageEsp(welcomeMessageEsp);
			cParameters.setCountryPhoneCode(countryPhoneCode);
			cParameters.setPositiveWords(posWords);
			cParameters.setNegativeWords(negWords);
			cParameters.setMaxFinderResults(maxFinderResults);
			cParameters.setFinderTime(finderTime);
			cParameters.setSpamWords(spamWords);
			this.configurationParametersService.save(cParameters);
			this.configurationParametersService.flush();
			//			this.unauthenticate();
			//			this.administratorService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	@Test
	public void driverAddWord() {
		final Object testingData[][] = {
			{
				"admin1", "hola", null
			}, {
				"admin1", null, IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateAddWord((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}
	private void templateAddWord(final String adminUsername, final String word, final Class<?> expected) {
		Class<?> caught;
		final ConfigurationParameters cParameters;

		caught = null;

		try {
			super.authenticate(adminUsername);
			//			cParameters = this.configurationParametersService.findOne(44);
			cParameters = this.configurationParametersService.create();
			this.configurationParametersService.addPositiveWord(word);
			this.configurationParametersService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverDeleteWord() {
		final Object testingData[][] = {
			{
				"admin1", "good", null
			}, {
				"admin1", null, IllegalArgumentException.class
			}, {
				"admin1", "hola", IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteWord((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}
	private void templateDeleteWord(final String adminUsername, final String word, final Class<?> expected) {
		Class<?> caught;
		final ConfigurationParameters cParameters;

		caught = null;

		try {
			super.authenticate(adminUsername);
			cParameters = this.configurationParametersService.findOne(44);
			//			cParameters = this.configurationParametersService.create();
			this.configurationParametersService.deletePositiveWord(word);
			this.configurationParametersService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	@Test
	public void driverCheckSpamWord() {
		final Collection<String> spamWord = new ArrayList<>();
		spamWord.add("viagra");
		final Collection<String> spamWord2 = new ArrayList<>();
		spamWord2.add("hola");
		final Object testingData[][] = {
			{
				"admin1", spamWord, null
			}, {
				//TODO:Debe fallar con null, y no falla.
				"admin1", spamWord2, null
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.templateCheckSpamWord((String) testingData[i][0], (Collection<String>) testingData[i][1], (Class<?>) testingData[i][2]);

	}
	private void templateCheckSpamWord(final String adminUsername, final Collection<String> words, final Class<?> expected) {
		Class<?> caught;
		final ConfigurationParameters cParameters;

		caught = null;

		try {
			super.authenticate(adminUsername);
			cParameters = this.configurationParametersService.findOne(44);
			//			cParameters = this.configurationParametersService.create();
			this.configurationParametersService.checkForSpamWords(words);
			this.configurationParametersService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

}
