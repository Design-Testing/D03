
package controllers;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
import services.ActorService;
import services.CompanyService;
import services.UserAccountService;
import services.auxiliary.RegisterService;
import domain.Actor;
import domain.Company;
import forms.ActorForm;
import forms.CompanyForm;

@Controller
@RequestMapping("/company")
public class CompanyController extends AbstractController {

	@Autowired
	private CompanyService		companyService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private RegisterService		registerService;
	@Autowired
	private UserAccountService	userAccountService;


	// Constructors -----------------------------------------------------------

	public CompanyController() {
		super();
	}

	// LIST ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Collection<Company> companies = this.companyService.findAll();

		res = new ModelAndView("company/list");
		res.addObject("companies", companies);

		return res;
	}

	// CREATE ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result = new ModelAndView();
		final ActorForm company = new ActorForm();
		result = new ModelAndView("company/edit");
		result.addObject("actorForm", company);
		return result;
	}

	// DISPLAY -----------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int companyId) {
		final ModelAndView result;
		final Company company = this.companyService.findOne(companyId);
		if (company != null) {
			result = new ModelAndView("company/display");
			result.addObject("company", company);
			result.addObject("displayButtons", false);
		} else
			result = new ModelAndView("redirect:/misc/403.jsp");

		return result;

	}

	@RequestMapping(value = "/display2", method = RequestMethod.GET)
	public ModelAndView display2() {
		final ModelAndView result;
		final Company company = this.companyService.findByPrincipal();
		if (company != null) {
			result = new ModelAndView("company/display");
			result.addObject("company", company);
			result.addObject("displayButtons", true);
		} else
			result = new ModelAndView("redirect:/misc/403.jsp");

		return result;

	}

	// EDIT -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		result = new ModelAndView("company/edit");
		final Company company = this.companyService.findByPrincipal();
		final ActorForm actor = this.registerService.inyect(company);
		actor.setTermsAndCondicions(true);
		result.addObject("actorForm", actor);
		return result;
	}

	// SAVE -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CompanyForm companyForm, final BindingResult binding) {
		ModelAndView result;
		result = new ModelAndView("company/edit");
		Company company;
		if (binding.hasErrors()) {
			result.addObject("errors", binding.getAllErrors());
			companyForm.setTermsAndCondicions(false);
			result.addObject("companyForm", companyForm);
		} else
			try {
				final UserAccount ua = this.userAccountService.reconstruct(companyForm, Authority.COMPANY);
				company = this.companyService.reconstruct(companyForm, binding);
				company.setUserAccount(ua);
				this.registerService.saveCompany(company, binding);
				result.addObject("alert", "hacker.edit.correct");
				result.addObject("companyForm", companyForm);
			} catch (final ValidationException oops) {
				result = this.createEditModelAndView(companyForm, null);
			} catch (final Throwable e) {
				if (e.getMessage().contains("username is register"))
					result.addObject("alert", "company.edit.usernameIsUsed");
				result.addObject("errors", binding.getAllErrors());
				companyForm.setTermsAndCondicions(false);
				result.addObject("companyForm", companyForm);
			}
		return result;
	}

	// GDPR -----------------------------------------------------------
	@RequestMapping(value = "/deletePersonalData")
	public ModelAndView deletePersonalData() {
		final Actor principal = this.actorService.findByPrincipal();
		principal.setAddress("");
		principal.setEmail("");
		//principal.setName("");
		principal.setPhone("");
		principal.setPhoto("");
		//		principal.setScore(0.0);
		//		principal.setSpammer(false);
		//principal.setSurname("");

		//		final Authority ban = new Authority();
		//		ban.setAuthority(Authority.BANNED);
		//		principal.getUserAccount().getAuthorities().add(ban);
		this.actorService.save(principal);

		final ModelAndView result = new ModelAndView("redirect:../j_spring_security_logout");
		return result;
	}

	// ANCILLARY METHODS  ---------------------------------------------------------------		

	protected ModelAndView createEditModelAndView(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createEditModelAndView(actorForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm actorForm, final String messageCode) {
		final ModelAndView result;

		result = new ModelAndView("company/edit");
		result.addObject("actorForm", actorForm);

		result.addObject("message", messageCode);

		return result;
	}

}
