
package controllers.hacker;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.CurriculaService;
import services.HackerService;
import services.PositionService;
import controllers.AbstractController;
import domain.Application;
import domain.Curricula;
import domain.Hacker;
import domain.Position;
import forms.ApplicationForm;

@Controller
@RequestMapping("/application/hacker")
public class ApplicationHackerController extends AbstractController {

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private HackerService		hackerService;

	@Autowired
	private PositionService		positionService;

	@Autowired
	private CurriculaService	curriculaService;

	final String				lang	= LocaleContextHolder.getLocale().getLanguage();


	//go to view with the list of curriculas to select one and apply
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int positionId) {
		ModelAndView result = new ModelAndView();
		final Hacker hacker = this.hackerService.findByPrincipal();

		try {

			final Collection<Curricula> curriculas = this.curriculaService.findCurriculaByHacker(hacker.getId());

			result = new ModelAndView("position/apply");
			result.addObject("curriculas", curriculas);
			result.addObject("hacker", hacker);
			result.addObject("positionId", positionId);

		} catch (final Throwable oops) {
			result = new ModelAndView("administrator/error");
			result.addObject("trace", oops.getMessage());
		}

		return result;
	}

	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	public ModelAndView apply(@RequestParam final int positionId, @RequestParam final int curriculaId) {
		ModelAndView result = new ModelAndView();
		final Hacker hacker = this.hackerService.findByPrincipal();
		final Position position = this.positionService.findOne(positionId);

		try {
			final Curricula curricula = this.curriculaService.findOne(curriculaId);
			final Curricula copy = this.curriculaService.makeCopyAndSave(curricula);

			final Application application = this.applicationService.apply(positionId, copy.getId());
			result = this.listPending();

			result.addObject("application", application);
			result.addObject("hacker", hacker);
			result.addObject("position", position);

		} catch (final Throwable oops) {
			result = new ModelAndView("administrator/error");
			result.addObject("trace", oops.getMessage());
		}

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

		if (application != null && application.getHacker().equals(hacker)) {
			result = new ModelAndView("application/display");
			result.addObject("hacker", hacker);
			result.addObject("application", application);
			result.addObject("rol", "hacker");
		} else
			result = new ModelAndView("redirect:/misc/403.jsp");

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

	// EDIT --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;

		application = this.applicationService.findOne(applicationId);

		final Hacker hacker = this.hackerService.findByPrincipal();

		if ((application.getStatus().equals("PENDING") && application.getHacker() == hacker) && application.getExplanation() == null && application.getLink() == null)
			result = this.createEditModelAndView(application);
		else
			result = new ModelAndView("redirect:misc/403");

		return result;
	}

	// SAVE --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ApplicationForm applicationForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("application/edit");
			result.addObject("applicationForm", applicationForm);
			result.addObject("errors", binding.getAllErrors());
		} else
			try {
				final Application application = this.applicationService.reconstruct(applicationForm, binding);
				this.applicationService.save(application, application.getPosition().getId());
				result = this.listSubmitted();
			} catch (final ValidationException oops) {
				result = new ModelAndView("application/edit");
				result.addObject("applicationForm", applicationForm);
			} catch (final Throwable oops) {
				result = new ModelAndView("application/edit");
				result.addObject("message", "application.error");
			}

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
		result.addObject("applicationForm", this.constructPruned(application)); // this.constructPruned(parade));

		result.addObject("message", messageCode);

		return result;
	}

	public ApplicationForm constructPruned(final Application application) {
		final ApplicationForm pruned = new ApplicationForm();

		pruned.setId(application.getId());
		pruned.setVersion(application.getVersion());
		pruned.setExplanation(application.getExplanation());
		pruned.setLink(application.getLink());

		return pruned;
	}

}
