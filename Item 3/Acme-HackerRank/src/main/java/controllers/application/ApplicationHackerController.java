
package controllers.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.HackerService;
import services.PositionService;
import controllers.AbstractController;
import domain.Application;
import domain.Hacker;
import domain.Position;

@Controller
@RequestMapping("/application/hacker")
public class ApplicationHackerController extends AbstractController {

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private HackerService		hackerService;

	@Autowired
	private PositionService		positionService;

	final String				lang	= LocaleContextHolder.getLocale().getLanguage();


	// CREATE --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int positionId) {
		ModelAndView result = new ModelAndView();
		final Hacker hacker = this.hackerService.findByPrincipal();
		final Position position = this.positionService.findOne(positionId);

		final Application application = this.applicationService.apply(positionId);

		result = this.

		application = this.applicationService.create();
		result = this.createEditModelAndView(application);
		return result;
	}
	// DISPLAY --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int applicationId) {
		final ModelAndView result;
		final Application application;
		final Hacker hacker;

		application = this.applicationService.findOne(applicationId);
		hacker = this.hackerService.findByPrincipal();

		result = new ModelAndView("application/display");
		result.addObject("hacker", hacker);
		result.addObject("application", application);

		return result;
	}

	// LIST PENDING --------------------------------------------------------

	@RequestMapping(value = "/listPending", method = RequestMethod.GET)
	public ModelAndView listPending() {
		final ModelAndView result;
		final Collection<Application> applications;

		applications = this.applicationService.findAllPendingByHacker();

		String listApplications;
		String rol;

		listApplications = "listPending";
		rol = "hacker";

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);

		result.addObject("lang", this.lang);
		result.addObject("requetURI", "application/hacker/listPending.do");
		result.addObject("listApplications", listApplications);
		result.addObject("principalID", this.hackerService.findByPrincipal().getId());
		result.addObject("rol", rol);

		return result;
	}

	// LIST SUBMITTED --------------------------------------------------------

	@RequestMapping(value = "/listSubmitted", method = RequestMethod.GET)
	public ModelAndView listSubmitted() {
		final ModelAndView result;
		final Collection<Application> applications;

		applications = this.applicationService.findAllSubmittedByHacker();

		String listApplications;
		String rol;

		listApplications = "listSubmitted";
		rol = "hacker";

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);

		result.addObject("lang", this.lang);
		result.addObject("requetURI", "application/hacker/listSubmitted.do");
		result.addObject("listApplications", listApplications);
		result.addObject("principalID", this.hackerService.findByPrincipal().getId());
		result.addObject("rol", rol);

		return result;
	}

	// LIST REJECTED --------------------------------------------------------

	@RequestMapping(value = "/listRejected", method = RequestMethod.GET)
	public ModelAndView listRejected() {
		final ModelAndView result;
		final Collection<Application> applications;

		applications = this.applicationService.findAllRejectedByHacker();

		String listApplications;
		String rol;

		listApplications = "listRejected";
		rol = "hacker";

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);

		result.addObject("lang", this.lang);
		result.addObject("requetURI", "application/hacker/listRejected.do");
		result.addObject("listApplications", listApplications);
		result.addObject("principalID", this.hackerService.findByPrincipal().getId());
		result.addObject("rol", rol);

		return result;
	}

	// LIST ACCEPTED --------------------------------------------------------

	@RequestMapping(value = "/listAccepted", method = RequestMethod.GET)
	public ModelAndView listAccepted() {
		final ModelAndView result;
		final Collection<Application> applications;

		applications = this.applicationService.findAllAcceptedByHacker();

		String listApplications;
		String rol;

		listApplications = "listAccepted";
		rol = "hacker";

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);

		result.addObject("lang", this.lang);
		result.addObject("requetURI", "application/hacker/listAccepted.do");
		result.addObject("listApplications", listApplications);
		result.addObject("principalID", this.hackerService.findByPrincipal().getId());
		result.addObject("rol", rol);

		return result;
	}

	// ANCILLIARY METHODS --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Application application) {
		ModelAndView result;

		result = this.createEditModelAndView(application, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application, final String messageCode) {
		Assert.notNull(application);
		final ModelAndView result;

		result = new ModelAndView("application/edit");
		result.addObject("application", application); // this.constructPruned(parade));

		result.addObject("message", messageCode);

		return result;
	}

}
