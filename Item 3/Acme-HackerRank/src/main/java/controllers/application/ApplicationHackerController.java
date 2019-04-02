
package controllers.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
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
		result = this.listPending();

		result.addObject("application", application);
		result.addObject("hacker", hacker);
		result.addObject("position", position);

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

	//	// EDIT --------------------------------------------------------
	//
	//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	//	public ModelAndView edit(@RequestParam final int applicationId) {
	//		ModelAndView result;
	//		Application application;
	//
	//		application = this.applicationService.findOne(applicationId);
	//
	//		final Hacker hacker = this.hackerService.findByPrincipal();
	//
	//		if ((application.getStatus().equals("PENDING") && application.getHacker() == hacker))
	//			result = this.createEditModelAndView(application);
	//		else
	//			result = new ModelAndView("redirect:/misc/403.jsp");
	//
	//		return result;
	//	}
	//
	//	// SAVE --------------------------------------------------------
	//
	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	//	public ModelAndView save(@Valid final Application application, final BindingResult binding) {
	//		ModelAndView result;
	//
	//		// final Parade parade = this.paradeService.reconstruct(pform, binding);
	//
	//		if (binding.hasErrors())
	//			result = this.createEditModelAndView(application);
	//		else
	//			try {
	//				this.paradeService.save(parade);
	//				result = new ModelAndView("redirect:list.do");
	//			} catch (final Throwable oops) {
	//				final Date current = new Date(System.currentTimeMillis());
	//				if (parade.getMoment().before(current))
	//					result = this.createEditModelAndView(parade, "parade.date.error");
	//				else if (parade.getBrotherhood().getArea() == null)
	//					result = this.createEditModelAndView(parade, "parade.area.error");
	//				else if (parade.getMode().equals("FINAL"))
	//					result = this.createEditModelAndView(parade, "parade.mode.error");
	//				else if (parade.getFloats().isEmpty())
	//					result = this.createEditModelAndView(parade, "parade.float.error");
	//				else
	//					result = this.createEditModelAndView(parade, "parade.commit.error");
	//			}
	//
	//		return result;
	//	}

	// ANCILLIARY METHODS --------------------------------------------------------

}
