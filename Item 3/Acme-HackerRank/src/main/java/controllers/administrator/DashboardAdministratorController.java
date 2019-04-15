
package controllers.administrator;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CompanyService;
import services.ConfigurationParametersService;
import services.CurriculaService;
import services.FinderService;
import services.HackerService;
import services.MessageService;
import services.PositionService;
import controllers.AbstractController;

@Controller
@RequestMapping(value = "/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	@Autowired
	private PositionService					positionService;

	@Autowired
	private MessageService					messageService;

	@Autowired
	private HackerService					hackerService;

	@Autowired
	private CompanyService					companyService;

	@Autowired
	private FinderService					finderService;

	@Autowired
	private CurriculaService				curriculaService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public ModelAndView statistics() {
		final ModelAndView result;
		result = new ModelAndView("dashboard/statistics"); // -> list.jsp
		result.addObject("requestURI", "dashboard/admnistrator/statistics.do");

		// Finder
		final Double[] finderStatistics = this.finderService.getStatisticsOfPositionsPerFinder();
		final Double ratioFinders = this.finderService.findRatioFinders();
		result.addObject("averageResults", finderStatistics[0]);
		result.addObject("minResults", finderStatistics[1]);
		result.addObject("maxResults", finderStatistics[2]);
		result.addObject("desviationResults", finderStatistics[3]);
		result.addObject("ratioFinders", ratioFinders);

		// Curriculum
		final Double[] curriculaStatistics = this.curriculaService.getStatisticsOfCurriculaPerHacker();
		result.addObject("averageCurricula", curriculaStatistics[0]);
		result.addObject("minCurricula", curriculaStatistics[1]);
		result.addObject("maxCurricula", curriculaStatistics[2]);
		result.addObject("desviationCurricula", curriculaStatistics[3]);

		// Positions per company
		final Double[] positionsPerCompany = this.companyService.getStatisticsOfPositionsPerCompany();
		result.addObject("averagePositions", positionsPerCompany[0]);
		result.addObject("minPositions", positionsPerCompany[1]);
		result.addObject("maxPositions", positionsPerCompany[2]);
		result.addObject("desviationPositions", positionsPerCompany[3]);
		result.addObject("companyMorePositions", this.companyService.getCompaniesMorePositions().toArray()[0]);
		result.addObject("best", this.positionService.getBestPosition()[0]);
		result.addObject("worst", this.positionService.getWorstPosition()[0]);

		// Applications per hacker
		final Double[] applicationsPerHacker = this.hackerService.getStatisticsOfApplicationsPerHacker();
		result.addObject("averageHacker", applicationsPerHacker[0]);
		result.addObject("minHacker", applicationsPerHacker[1]);
		result.addObject("maxHacker", applicationsPerHacker[2]);
		result.addObject("desviationHacker", applicationsPerHacker[3]);
		result.addObject("hackerMoreApplications", this.hackerService.getHackersMoreApplications()[0]);

		return result;

	}

	@RequestMapping(value = "/dataBreach", method = RequestMethod.GET)
	public ModelAndView launchDeactivate() {
		ModelAndView result;

		this.messageService.dataBreachMessage();

		result = new ModelAndView("welcome/index");
		final String lang = LocaleContextHolder.getLocale().getLanguage();

		String mensaje = null;
		if (lang.equals("en"))
			mensaje = this.configurationParametersService.findWelcomeMessageEn();
		else if (lang.equals("es"))
			mensaje = this.configurationParametersService.findWelcomeMessageEsp();

		final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		final String moment = formatter.format(new Date());

		result = new ModelAndView("welcome/index");
		result.addObject("moment", moment);
		result.addObject("mensaje", mensaje);

		result.addObject("alert", "data.breach.notified");

		return result;

	}

}
