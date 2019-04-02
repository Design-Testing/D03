
package controllers.company;

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
import controllers.AbstractController;
import domain.Application;
import domain.Company;

@Controller
@RequestMapping("/application/company")
public class ApplicationCompanyController extends AbstractController {

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private CompanyService		companyService;

	final String				lang	= LocaleContextHolder.getLocale().getLanguage();


	// CREATE --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Application application;

		// String lang = LocaleContextHolder.getLocale().getLanguage();

		application = this.applicationService.create();
		result = this.createEditModelAndView(application);
		return result;
	}

	// DISPLAY --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int applicationId) {
		final ModelAndView result;
		final Application application;
		final Company company;

		application = this.applicationService.findOne(applicationId);
		company = this.companyService.findByPrincipal();

		result = new ModelAndView("application/display");
		result.addObject("company", company);
		result.addObject("application", application);
	}

	// LIST SUBMITTED --------------------------------------------------------

	@RequestMapping(value = "/listSubmitted", method = RequestMethod.GET)
	public ModelAndView listSubmitted() {
		final ModelAndView result;
		final Collection<Application> applications;

		applications = this.applicationService.findAllSubmittedByCompany();

		String listApplications;
		String rol;

		listApplications = "listSubmitted";
		rol = "company";

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);

		result.addObject("lang", this.lang);
		result.addObject("requetURI", "application/company/listSubmitted.do");
		result.addObject("listApplications", listApplications);
		result.addObject("principalID", this.companyService.findByPrincipal().getId());
		result.addObject("rol", rol);

		return result;
	}

	// LIST REJECTED --------------------------------------------------------

	@RequestMapping(value = "/listRejected", method = RequestMethod.GET)
	public ModelAndView listRejected() {
		final ModelAndView result;
		final Collection<Application> applications;

		applications = this.applicationService.findAllRejectedByCompany();

		String listApplications;
		String rol;

		listApplications = "listRejected";
		rol = "company";

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);

		result.addObject("lang", this.lang);
		result.addObject("requetURI", "application/company/listRejected.do");
		result.addObject("listApplications", listApplications);
		result.addObject("principalID", this.companyService.findByPrincipal().getId());
		result.addObject("rol", rol);

		return result;
	}

	// LIST ACCEPTED --------------------------------------------------------

	@RequestMapping(value = "/listRejected", method = RequestMethod.GET)
	public ModelAndView listAccepted() {
		final ModelAndView result;
		final Collection<Application> applications;

		applications = this.applicationService.findAllAcceptedByCompany();

		String listApplications;
		String rol;

		listApplications = "listAccepted";
		rol = "company";

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);

		result.addObject("lang", this.lang);
		result.addObject("requetURI", "application/company/listAccepted.do");
		result.addObject("listApplications", listApplications);
		result.addObject("principalID", this.companyService.findByPrincipal().getId());
		result.addObject("rol", rol);

		return result;
	}

	// ACCEPT APPLICATION --------------------------------------------------------

	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam final int applicationId) {
		final ModelAndView result;
		final Application application = this.applicationService.findOne(applicationId);

		if (application == null || !application.getStatus().equals("SUBMITTED")) {
			result = this.createEditModelAndView(application, "application.commit.error");
			result.addObject("ok", false);
		} else {
			this.applicationService.acceptApplication(applicationId);
			result = this.listAccepted();
		}

		final String banner = this.configurationParametersService.findBanner();
		result.addObject("banner", banner);

		return result;
	}
	// REJECT PARADE --------------------------------------------------------

	@RequestMapping(value = "/reject", method = RequestMethod.GET)
	public ModelAndView reject(@RequestParam final int applicationId) {
		ModelAndView result;
		final Application application = this.applicationService.findOne(applicationId);

		if (application == null || !application.getStatus().equals("SUBMITTED")) {
			result = this.createEditModelAndView(application, "application.commit.error");
			result.addObject("ok", false);
		} else {
			this.applicationService.rejectApplication(applicationId);
			result = this.listAccepted();
		}

		final String banner = this.configurationParametersService.findBanner();
		result.addObject("banner", banner);

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

	// This method is not used because it doesn't make sense to have a pruned object in parade
	//	private ParadeForm constructPruned(final Parade parade) {
	//		final ParadeForm pruned = new ParadeForm();
	//		pruned.setId(parade.getId());
	//		pruned.setVersion(parade.getVersion());
	//		pruned.setTitle(parade.getTitle());
	//		pruned.setDescription(parade.getDescription());
	//		pruned.setMaxRows(parade.getMaxRows());
	//		pruned.setMaxColumns(parade.getMaxColumns());
	//		pruned.setMoment(parade.getMoment());
	//		pruned.setFloats(parade.getFloats());
	//		return pruned;
	//	}

	//	// EDIT --------------------------------------------------------
	//
	//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	//	public ModelAndView edit(@RequestParam final int applicationId) {
	//		ModelAndView result;
	//		Application application;
	//
	//		application = this.applicationService.findOne(applicationId);
	//
	//		final Company company = this.companyService.findByPrincipal();
	//
	//		if ((application.getStatus().equals("SUBMITTED") && this.companyService.findCompanyByApplication(application) == company))
	//			result = this.createEditModelAndView(application);
	//		else
	//			result = new ModelAndView("redirect:/misc/403.jsp");
	//
	//		return result;
	//	}
	//	
	//	// SAVE --------------------------------------------------------
	//
	//		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	//		public ModelAndView save(@Valid final Application application, final BindingResult binding) {
	//			ModelAndView result;
	//
	//			// final Parade parade = this.paradeService.reconstruct(pform, binding);
	//
	//			if (binding.hasErrors())
	//				result = this.createEditModelAndView(parade);
	//			else
	//				try {
	//					this.paradeService.save(parade);
	//					result = new ModelAndView("redirect:list.do");
	//				} catch (final Throwable oops) {
	//					final Date current = new Date(System.currentTimeMillis());
	//					if (parade.getMoment().before(current))
	//						result = this.createEditModelAndView(parade, "parade.date.error");
	//					else if (parade.getBrotherhood().getArea() == null)
	//						result = this.createEditModelAndView(parade, "parade.area.error");
	//					else if (parade.getMode().equals("FINAL"))
	//						result = this.createEditModelAndView(parade, "parade.mode.error");
	//					else if (parade.getFloats().isEmpty())
	//						result = this.createEditModelAndView(parade, "parade.float.error");
	//					else
	//						result = this.createEditModelAndView(parade, "parade.commit.error");
	//				}
	//
	//			return result;
	//		}
}
